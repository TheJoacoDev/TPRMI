import java.rmi.Remote;
import java.rmi.RemoteException;

/**
 * ============================================================================
 * TRABAJO PRACTICO: Invocacion a Metodos Remotos (RMI)
 * Sistemas Distribuidos - UTN FRCU
 * ============================================================================
 * Ejercicio A: Definicion de la Interfaz Remota "ClienteRemoto".
 * 
 * Representa el contrato para el "Servidor de Informacion de Clientes".
 * Define las firmas de los metodos que los clientes podran invocar a traves de la red.
 * 
 * Requisitos tecnicos de RMI para interfaces remotas:
 * 1. Debe extender directa o indirectamente de java.rmi.Remote.
 * 2. Todos los metodos deben declarar que lanzan java.rmi.RemoteException.
 */
public interface ClienteRemoto extends Remote {

    /**
     * Retorna los datos completos de un cliente registrado a partir de su ID.
     * 
     * @param idCliente Identificador unico del cliente.
     * @return Cadena formateada con la informacion completa del cliente.
     * @throws RemoteException Si ocurre un error de comunicacion en la capa RMI.
     */
    String obtenerDatosCliente(int idCliente) throws RemoteException;

    /**
     * Retorna el saldo actual de la cuenta del cliente indicado.
     * 
     * @param idCliente Identificador unico del cliente.
     * @return Cadena con el nombre y saldo disponible del cliente.
     * @throws RemoteException Si ocurre un error de comunicacion en la capa RMI.
     */
    String obtenerSaldoCliente(int idCliente) throws RemoteException;

    /**
     * Busca la informacion basica de un cliente a traves de su numero de DNI.
     * 
     * @param dni Numero de documento del cliente a buscar.
     * @return Cadena con los datos del cliente encontrado o mensaje de no coincidencia.
     * @throws RemoteException Si ocurre un error de comunicacion en la capa RMI.
     */
    String buscarClientePorDni(String dni) throws RemoteException;
}

