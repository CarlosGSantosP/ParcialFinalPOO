/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package ServerHilosParcial;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;

/**
 *
 * @author carlosgeradosantos
 */
public class ServerHilos {
    
    public static void main(String[] args) {
        try{
            ServerSocket server = new ServerSocket(5000);
            System.out.println("Esperando conexiones....");
            
            while(true){
                Socket cliente = server.accept();
                System.out.println("Se ha conectado desde: " + cliente.getInetAddress());
                
                //para crear nuevo hilo
                new Thread(new ManejadorCliente(cliente)).start();
            }
        }
        catch(IOException e){
            System.out.println(e);
        }
    }
    
}

