import java.rmi.Naming;
import java.rmi.RemoteException;
import java.rmi.registry.LocateRegistry;

/**
 * ============================================================================
 * TRABAJO PRACTICO: Invocacion a Metodos Remotos (RMI)
 * Sistemas Distribuidos - UTN FRCU
 * ============================================================================
 * Ejercicio C: Servidor de Objetos Remoto.
 * 
 * Esta clase se encarga de:
 * 1. Inicializar o vincularse con el daemon RMI (rmiregistry) en el puerto por defecto 1099.
 * 2. Instanciar el objeto remoto en la memoria del servidor.
 * 3. Publicar el objeto remoto en el registro RMI mediante la clase Naming.rebind(...)
 *    para que quede disponible a ser consumido por clientes remotos.
 */
public class ServidorObjetosRemoto {

    public ServidorObjetosRemoto() {
        /*
         * Opcional segun el apunte de catedra:
         * System.setSecurityManager(new RMISecurityManager());
         */
    }

    /**
     * Activa los objetos remotos y los publica en el registro RMI.
     */
    public void ActivarObjetos() {
        try {
            // Inicializa el registro de RMI en el puerto 1099 de manera programatica
            // por si el usuario no ejecuto previamente 'rmiregistry' desde la terminal.
            try {
                LocateRegistry.createRegistry(1099);
                System.out.println("[Registro] Servicio RMI Registry inicializado en el puerto 1099.");
            } catch (RemoteException re) {
                System.out.println("[Registro] RMI Registry ya detectado y en ejecucion en el puerto 1099.");
            }

            // 1. Crea la instancia del objeto remoto en el Servidor
            Cliente cliente = new Cliente();

            // 2. Registra el objeto en el servidor de nombres para ser invocado remotamente.
            // Se publica bajo el nombre logico "Cliente" (o "rmi://127.0.0.1:1099/Cliente").
            Naming.rebind("Cliente", cliente);

            System.out.println("==========================================================");
            System.out.println("  Servidor de Informacion de Clientes ACTIVO con exito!   ");
            System.out.println("  Objeto remoto registrado bajo el nombre: 'Cliente'       ");
            System.out.println("  Esperando peticiones de clientes remotos...              ");
            System.out.println("==========================================================");

        } catch (Exception e) {
            System.out.println("\nERROR en Servidor: " + e);
            e.printStackTrace();
        }
    }
}

