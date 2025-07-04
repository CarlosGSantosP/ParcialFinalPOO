package ClienteParcial;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.PrintWriter;
import java.net.Socket;
import java.util.Scanner;

public class ClienteCuadrado {

    public static void main(String[] args) {
        Socket socket = null;
        Scanner scanner = new Scanner(System.in);
        
        try {
            System.out.print("Ingresa tu nombre: ");
            String nombre = scanner.nextLine();

            System.out.print("Ingresa un número entero: ");
            int numero = Integer.parseInt(scanner.nextLine());

            // Conexión al servidor
            socket = new Socket("localhost", 5000);
            System.out.println("Conectado al servidor en puerto 5000.");

            // Flujos de entrada y salida
            InputStream inputStream = socket.getInputStream();
            OutputStream outputStream = socket.getOutputStream();

            PrintWriter writer = new PrintWriter(outputStream, true);
            BufferedReader reader = new BufferedReader(new InputStreamReader(inputStream));

            // Enviar datos
            writer.println(nombre);
            writer.println(numero);

            // Leer respuesta del servidor
            //String respuesta = reader.readLine();
            //System.out.println("Respuesta del servidor: " + respuesta);

            System.out.println("\n Respuesta del servidor: ");
            for (int i = 0; i < 2; i++) {
                String respuesta = reader.readLine();
                if (respuesta != null)
                    System.out.println(respuesta);
            }
            
        } catch (IOException e) {
            System.out.println("Error: " + e);
        } catch (NumberFormatException e) {
            System.out.println("Error: debes ingresar un número válido.");
        } finally {
            try {
                if (socket != null) {
                    socket.close();
                }
            } catch (IOException e) {
                System.out.println("Error al cerrar el socket: " + e);
            }
            scanner.close();
        }
    }
}
