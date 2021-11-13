/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package cristeat_server;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author David
 */
public class Hebranuevaconexion implements Runnable {
    
    ArrayList <Multiserverthead> clientes = new ArrayList();
    boolean listening = true;
    int portNumber;
    ServerSocket serverSocket;
    javax.swing.JTextArea log;
    
    
    public synchronized void aceptarconexion() {
        

        try {
            clientes.add(new Multiserverthead(serverSocket.accept(),log, clientes));
            clientes.get(clientes.size()-1).thr.start();
            
        } catch (IOException ex) {
            log.setText(log.getText() + "\n" + "No se ha podido establecer la conexion");
            
        }      
        notifyAll();
    }
    public synchronized void eliminar_conexion(){
    
         for (int i = 0 ; i < clientes.size();i++){
            
            if(clientes.get(i).getcadelete() == true){
                clientes.remove(i);
                System.out.println(log.getText() + "\n" + "Se ha eliminado el cliente: " + i);
            }
        
        }
         notifyAll();
    }
    
    public Hebranuevaconexion(int puerto, javax.swing.JTextArea log){
       this.portNumber = puerto;
        
        this.log = log;
        try {
            this.serverSocket = new ServerSocket(portNumber);
        } catch (IOException ex) {
            log.setText(log.getText() + "\n" + "el puerto no es correcto");
        }
       
        
    }
    
    
    @Override
    public void run() {
        
       while(listening){
            
           
               aceptarconexion();
               
          
            
           
           
           
       }
      
       
    }
    
    
}
