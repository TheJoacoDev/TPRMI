/**
 * ============================================================================
 * TRABAJO PRACTICO: Invocacion a Metodos Remotos (RMI)
 * Sistemas Distribuidos - UTN FRCU
 * ============================================================================
 * Ejercicio D: Clase principal PruebaCliente.
 * 
 * Cumple con los siguientes requerimientos fundamentales de RMI:
 * 1. Declara la variable con el tipo de la INTERFAZ remota (ClienteRemoto),
 *    NUNCA con la clase de implementacion del servidor (Cliente), manteniendo
 *    el desacoplamiento entre cliente y servidor.
 * 2. Utiliza la clase ClienteLocal para conectarse y obtener la referencia remota (Stub).
 * 3. Ejecuta llamadas a los metodos remotos para consultar informacion de clientes.
 * 4. Maneja adecuadamente las excepciones de red y comunicacion.
 */
public class PruebaCliente {

    public static void main(String[] arg) {

        // 1. Declaracion de la Interfaz a utilizar en el Cliente (NO se instancia la clase concreta)
        ClienteRemoto clienteRemoto;

        // 2. Instancia de la clase de utilidad local
        ClienteLocal cl = new ClienteLocal();

        String NombreServerRemoto = "127.0.0.1";
        String NombreObjetoRemoto = "Cliente";

        System.out.println("Buscando servicio RMI en " + NombreServerRemoto + "...");

        // 3. Busca el objeto en el Server Remoto
        Object re = cl.getRemoteObject("rmi://" + NombreServerRemoto + "/" + NombreObjetoRemoto);

        if (re == null) {
            System.out.println("No se pudo obtener la referencia remota. Asegurese de que PruebaServer este ejecutandose.");
            return;
        }

        // 4. Casteo al tipo de la Interfaz Remota
        clienteRemoto = (ClienteRemoto) re;

        // 5. Invocacion de los metodos remotos y captura de excepciones
        try {
            System.out.println("\n--------------------------------------------------------------");
            System.out.println(" INVOCACION 1: Consulta de informacion completa del Cliente 1");
            System.out.println("--------------------------------------------------------------");
            String info1 = clienteRemoto.obtenerDatosCliente(1);
            System.out.println(info1);

            System.out.println("\n--------------------------------------------------------------");
            System.out.println(" INVOCACION 2: Consulta del saldo del Cliente 1");
            System.out.println("--------------------------------------------------------------");
            String saldo1 = clienteRemoto.obtenerSaldoCliente(1);
            System.out.println(saldo1);

            System.out.println("\n--------------------------------------------------------------");
            System.out.println(" INVOCACION 3: Busqueda de cliente por DNI ('36985214')");
            System.out.println("--------------------------------------------------------------");
            String busquedaDni = clienteRemoto.buscarClientePorDni("36985214");
            System.out.println(busquedaDni);

            System.out.println("\n--------------------------------------------------------------");
            System.out.println(" INVOCACION 4: Consulta de cliente inexistente (ID 99)");
            System.out.println("--------------------------------------------------------------");
            String info99 = clienteRemoto.obtenerDatosCliente(99);
            System.out.println(info99);

        } catch (Exception e) {
            System.out.println("ERROR al llamar al metodo remoto: " + e);
            e.printStackTrace();
        }
    }
}

