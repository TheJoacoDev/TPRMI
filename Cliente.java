import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;
import java.util.HashMap;
import java.util.Map;

/**
 * ============================================================================
 * TRABAJO PRACTICO: Invocacion a Metodos Remotos (RMI)
 * Sistemas Distribuidos - UTN FRCU
 * ============================================================================
 * Ejercicio B.a: Implementacion de la Clase Remota "Cliente".
 * 
 * Esta clase representa el Objeto Remoto de "Informacion de Clientes" que reside
 * en la memoria del servidor.
 * 
 * Requisitos tecnicos de RMI:
 * 1. Extiende de java.rmi.server.UnicastRemoteObject para permitir comunicacion punto a punto.
 * 2. Implementa la interfaz remota ClienteRemoto.
 * 3. Posee un constructor que declara RemoteException (debido al constructor de UnicastRemoteObject).
 * 4. Implementa todos los metodos remotos definidos en la interfaz.
 */
public class Cliente extends UnicastRemoteObject implements ClienteRemoto {

    /**
     * Estructura interna para almacenar informacion de un cliente en memoria.
     */
    private static class InformacionCliente {
        int id;
        String nombre;
        String dni;
        String telefono;
        String direccion;
        double saldo;

        public InformacionCliente(int id, String nombre, String dni, String telefono, String direccion, double saldo) {
            this.id = id;
            this.nombre = nombre;
            this.dni = dni;
            this.telefono = telefono;
            this.direccion = direccion;
            this.saldo = saldo;
        }
    }

    // Mapa en memoria que simula la base de datos de clientes en el servidor
    private Map<Integer, InformacionCliente> repositorioClientes;

    /**
     * Constructor de la clase remota.
     * Exporta automaticamente el objeto remoto para escuchar llamadas entrantes.
     * 
     * @throws RemoteException Si ocurre un error al exportar el objeto remoto.
     */
    public Cliente() throws RemoteException {
        super();
        repositorioClientes = new HashMap<Integer, InformacionCliente>();
        inicializarBaseDatosSimulada();
    }

    /**
     * Carga registros iniciales de prueba en la base de datos en memoria.
     */
    private void inicializarBaseDatosSimulada() {
        repositorioClientes.put(1, new InformacionCliente(
            1, "Juan Perez", "32145879", "03442-421122", "San Martin 450, Concepcion del Uruguay", 45250.75
        ));
        repositorioClientes.put(2, new InformacionCliente(
            2, "Maria Gonzalez", "36985214", "03442-435566", "Urquiza 820, Concepcion del Uruguay", 128900.00
        ));
        repositorioClientes.put(3, new InformacionCliente(
            3, "Carlos Ramirez", "28741369", "011-45678912", "Av. de Mayo 1150, CABA", 9850.50
        ));
        repositorioClientes.put(4, new InformacionCliente(
            4, "Ana Laura Lopez", "41258963", "0343-4219876", "Pellegrini 310, Parana", 73400.25
        ));
    }

    /**
     * Retorna los datos completos de un cliente formateados como cadena de texto.
     */
    @Override
    public String obtenerDatosCliente(int idCliente) throws RemoteException {
        System.out.println("[Servidor] Solicitud remota recibida: obtenerDatosCliente(ID: " + idCliente + ")");
        InformacionCliente info = repositorioClientes.get(idCliente);
        
        if (info != null) {
            return "--------------------------------------------------\n" +
                   "          FICHA DE DATOS DEL CLIENTE              \n" +
                   "--------------------------------------------------\n" +
                   "  ID Cliente : " + info.id + "\n" +
                   "  Nombre     : " + info.nombre + "\n" +
                   "  DNI        : " + info.dni + "\n" +
                   "  Telefono   : " + info.telefono + "\n" +
                   "  Direccion  : " + info.direccion + "\n" +
                   "  Saldo Cta  : $" + String.format("%.2f", info.saldo) + "\n" +
                   "--------------------------------------------------";
        } else {
            return "[Servidor] AVISO: No se encontro ningun cliente con el ID: " + idCliente;
        }
    }

    /**
     * Retorna el saldo disponible para un cliente dado su ID.
     */
    @Override
    public String obtenerSaldoCliente(int idCliente) throws RemoteException {
        System.out.println("[Servidor] Solicitud remota recibida: obtenerSaldoCliente(ID: " + idCliente + ")");
        InformacionCliente info = repositorioClientes.get(idCliente);
        
        if (info != null) {
            return "Cliente: " + info.nombre + " | Saldo Disponible: $" + String.format("%.2f", info.saldo);
        } else {
            return "[Servidor] AVISO: Cliente inexistente para el ID: " + idCliente;
        }
    }

    /**
     * Busca los datos de un cliente a traves de su numero de DNI.
     */
    @Override
    public String buscarClientePorDni(String dni) throws RemoteException {
        System.out.println("[Servidor] Solicitud remota recibida: buscarClientePorDni(DNI: " + dni + ")");
        
        if (dni != null) {
            String dniBuscado = dni.trim();
            for (InformacionCliente info : repositorioClientes.values()) {
                if (info.dni.equalsIgnoreCase(dniBuscado)) {
                    return "Coincidencia: [ID " + info.id + "] " + info.nombre + 
                           " (Tel: " + info.telefono + ", Saldo: $" + String.format("%.2f", info.saldo) + ")";
                }
            }
        }
        return "[Servidor] AVISO: No se encontro cliente con el DNI: " + dni;
    }
}

