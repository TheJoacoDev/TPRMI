@echo off
echo ===================================================
echo   Compilando Trabajo Practico RMI - Clientes
echo ===================================================

javac ClienteRemoto.java Cliente.java ServidorObjetosRemoto.java PruebaServer.java ClienteLocal.java PruebaCliente.java

if %ERRORLEVEL% equ 0 (
    echo.
    echo [OK] Compilacion completada con exito.
    echo.
    echo Si usas Java 8 o anterior y deseas generar los stubs estaticos:
    echo    rmic Cliente
    echo.
) else (
    echo.
    echo [ERROR] Hubo un error durante la compilacion.
    echo Asegurate de tener el comando 'javac' configurado en la variable PATH del sistema.
    echo.
)
pause

