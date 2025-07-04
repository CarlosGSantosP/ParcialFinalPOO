package ServerHilosParcial;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.PrintWriter;
import java.net.Socket;


public class ManejadorCliente implements Runnable {
    private Socket cliente;

    public ManejadorCliente(Socket cliente) {
        this.cliente = cliente;
    }

    @Override
    public void run() {
        String nombre = "Desconocido";  // Valor por defecto
        try {
            InputStream input = cliente.getInputStream();
            OutputStream output = cliente.getOutputStream();

            BufferedReader reader = new BufferedReader(new InputStreamReader(input));
            PrintWriter writer = new PrintWriter(output, true);

            // Leer nombre
            nombre = reader.readLine();
            if (nombre == null) {
                throw new IOException("Cliente cerró antes de enviar el nombre.");
            }
            System.out.println("Cliente " + nombre + " conectado");

            // Leer número
            String numeroStr = reader.readLine();
            if (numeroStr == null) {
                throw new IOException("Cliente cerró antes de enviar el número.");
            }
            int numero = Integer.parseInt(numeroStr);
            int cuadrado = numero * numero;

            // Fecha y hora actual
          

            // Enviar respuesta
            writer.println("¡Bienvenido, " + nombre + "!");
            writer.println("El cuadrado de " + numero + " es " + cuadrado + ".");
            

        } catch (IOException | NumberFormatException e) {
            System.out.println("Error al atender cliente " + nombre + ": " + e.getMessage());
        } finally {
            try {
                if (cliente != null && !cliente.isClosed()) {
                    cliente.close();
                }
            } catch (IOException e) {
                System.out.println("Error al cerrar socket: " + e.getMessage());
            }
            System.out.println("Cliente " + nombre + " desconectado");
        }
    }
}
