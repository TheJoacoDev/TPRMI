# Trabajo Práctico: Invocación a Métodos Remotos (RMI)
## Sistemas Distribuidos - UTN FRCU (4to año)

Implementación completa de un sistema cliente-servidor distribuido utilizando **Java RMI (Remote Method Invocation)** para un **"Servidor de Información de Clientes"**, siguiendo estrictamente la estructura conceptual y técnica del material de cátedra (Ing. Ulises Rapallini).

---

## 📁 Estructura del Proyecto

El sistema está dividido en las siguientes clases y archivos:

| Archivo | Ejercicio | Rol en RMI | Descripción |
| :--- | :---: | :--- | :--- |
| [`ClienteRemoto.java`](file:///C:/Users/Joaquín%20Ferrari/source/repos/TheJoacoDev/TPRMI/ClienteRemoto.java) | **A** | Interfaz Remota | Extiende de `java.rmi.Remote`. Declara los métodos que pueden ser llamados remotamente, todos lanzando `RemoteException`. |
| [`Cliente.java`](file:///C:/Users/Joaquín%20Ferrari/source/repos/TheJoacoDev/TPRMI/Cliente.java) | **B.a** | Clase Remota | Extiende de `UnicastRemoteObject` e implementa `ClienteRemoto`. Contiene la lógica del negocio y la base de datos simulada en memoria. |
| [`ServidorObjetosRemoto.java`](file:///C:/Users/Joaquín%20Ferrari/source/repos/TheJoacoDev/TPRMI/ServidorObjetosRemoto.java) | **C** | Servidor de Objetos | Inicializa el registro RMI en el puerto 1099 y publica el objeto con `Naming.rebind("Cliente", cliente)`. |
| [`PruebaServer.java`](file:///C:/Users/Joaquín%20Ferrari/source/repos/TheJoacoDev/TPRMI/PruebaServer.java) | **C** | Launcher Servidor | Contiene el método `main` para arrancar y dejar activo el servidor de objetos remotos. |
| [`ClienteLocal.java`](file:///C:/Users/Joaquín%20Ferrari/source/repos/TheJoacoDev/TPRMI/ClienteLocal.java) | **D** | Utilidad Cliente | Gestiona la resolución y búsqueda de nombres con `Naming.lookup(...)` para obtener la referencia remota (`Stub`). |
| [`PruebaCliente.java`](file:///C:/Users/Joaquín%20Ferrari/source/repos/TheJoacoDev/TPRMI/PruebaCliente.java) | **D** | Launcher Cliente | Contiene el método `main`, declara la interfaz remota, busca el objeto en el servidor e invoca los métodos remotos. |

---

## 🛠️ Guía de Compilación y Ejecución

### 1. Compilación
Desde la terminal en el directorio raíz del proyecto:
```bash
javac *.java
```
*(O ejecutar el script `compilar.bat` en Windows)*.

---

### 2. Generación de Stubs y Skeletons con `rmic` (Ejercicio B.b)

En el esquema clásico de Java RMI, una vez compiladas las clases `.class`, se debe generar el par **Stub/Skeleton** para la clase remota ejecutando:

```bash
rmic Cliente
```

#### Explicación técnica:
- **`Cliente_Stub.class`**: Es el archivo proxy del lado del **Cliente**. Se encarga de serializar (*marshaling*) los parámetros de la llamada a través de la red hacia el servidor y de deserializar (*unmarshaling*) el valor de retorno o las excepciones.
- **`Cliente_Skel.class`**: Es el despachador (*skeleton*) del lado del **Servidor**. Escucha las peticiones enviadas por el stub, deserializa los parámetros, invoca el método real en el objeto `Cliente` y devuelve el resultado serializado al cliente.
- *Nota evolutiva de Java:* En versiones modernas (Java 5 en adelante), RMI incorpora generación dinámica de stubs en tiempo de ejecución (Dynamic Proxies), por lo que `rmic` pasó a ser opcional y fue descontinuado a partir de Java 15. Sin embargo, para entornos Java 1.1–8 y el modelo conceptual clásico de Sistemas Distribuidos, `rmic Cliente` es la herramienta estándar. Si se desea generar ambos archivos explícitamente en JDK 1.2-1.8, puede usarse `rmic -v1.1 Cliente`.

---

### 3. Registro y Ejecución del Servidor (Ejercicio C)

#### Opción Tradicional (por consola):
1. Iniciar el demonio de registro RMI en segundo plano:
   ```bash
   start rmiregistry
   ```
2. Ejecutar la clase del servidor:
   ```bash
   java PruebaServer
   ```

*(Nota: En [`ServidorObjetosRemoto.java`](file:///C:/Users/Joaquín%20Ferrari/source/repos/TheJoacoDev/TPRMI/ServidorObjetosRemoto.java) se incluyó `LocateRegistry.createRegistry(1099)` de forma programática con manejo de excepciones, por lo que si no se inició `rmiregistry` manualmente, el servidor lo levantará automáticamente sin arrojar error).*

---

### 4. Ejecución del Cliente (Ejercicio D)

En una segunda terminal o proceso:
```bash
java PruebaCliente
```

*(O ejecutar el script `ejecutar_cliente.bat` en Windows)*.

El cliente se conectará a `rmi://127.0.0.1/Cliente`, resolverá el Stub, e invocará múltiples métodos remotos mostrando las respuestas del servidor.

