import java.rmi.Naming;
import java.rmi.RemoteException;

/**
 * ============================================================================
 * TRABAJO PRACTICO: Invocacion a Metodos Remotos (RMI)
 * Sistemas Distribuidos - UTN FRCU
 * ============================================================================
 * Ejercicio D: Clase de utilidad ClienteLocal.
 * 
 * Se encarga de gestionar la conexion con el servidor RMI remoto utilizando
 * el metodo Naming.lookup(url_rmi), retornando la referencia remota del objeto (Stub).
 */
public class ClienteLocal {

    public ClienteLocal() {
        /*
         * Opcional segun el apunte de catedra:
         * System.setSecurityManager(new RMISecurityManager());
         */
    }

    /**
     * Busca el objeto remoto en el registro RMI a traves de su URL.
     * 
     * @param url_rmi Direccion del objeto en formato "rmi://<IP_o_Host>/<NombreObjeto>"
     * @return El objeto remoto (de tipo Remote/Object) si la busqueda fue exitosa, o null si fallo.
     */
    public Object getRemoteObject(String url_rmi) {
        Object obj = null;
        try {
            // Busca el objeto remoto y lo retorna si tiene exito
            obj = Naming.lookup(url_rmi);
            System.out.println("\nBusqueda en Server Remoto Exitosa !!");
        } catch (RemoteException re) {
            System.out.println("\nERROR remoto: " + re);
        } catch (Exception e) {
            System.out.println("\nERROR ?: " + e);
        }
        return obj;
    }
}

