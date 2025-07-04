/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 */

package com.mycompany.socketsparcial;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.PrintWriter;
import java.net.ServerSocket;
import java.net.Socket;

/**
 *
 * @author carlosgeradosantos
 */
public class ServidorCuadrado {

    public static void main(String[] args) {
        try{
            ServerSocket serverSocket = new ServerSocket(5000);
            System.out.println("Esperando conexion...");
            //aceptar cliente
            Socket socketCliente = serverSocket.accept();
            System.out.println("Cliente conectado: " + socketCliente.getInetAddress());
            //flujo de datos
            InputStream inputStream = socketCliente.getInputStream();
            OutputStream outputStream = socketCliente.getOutputStream();
            //lectura y escritura
            BufferedReader reader = new BufferedReader(new InputStreamReader(inputStream));
            PrintWriter writer = new PrintWriter(outputStream, true);
            //cliente nos envia un mensaje
            String msg = reader.readLine();
            System.out.println("Mensaje del cliente: " + msg);
            //enviar al cliente
            writer.println("Este mensaje se ha enviado desde el servidor");
            socketCliente.close();
            serverSocket.close();
            
        }
        catch(IOException e){
            System.out.println(e);
        }
    }
}


