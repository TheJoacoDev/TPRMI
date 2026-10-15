/**
 * ============================================================================
 * TRABAJO PRACTICO: Invocacion a Metodos Remotos (RMI)
 * Sistemas Distribuidos - UTN FRCU
 * ============================================================================
 * Ejercicio C: Clase principal PruebaServer.
 * 
 * Punto de entrada (main) para levantar y activar el servidor de objetos remotos.
 */
public class PruebaServer {

    public static void main(String[] arg) {
        System.out.println(">>> Iniciando PruebaServer...");
        ServidorObjetosRemoto sr = new ServidorObjetosRemoto();
        sr.ActivarObjetos();
    }
}

