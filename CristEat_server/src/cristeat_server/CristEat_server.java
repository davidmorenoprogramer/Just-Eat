/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package cristeat_server;

import controlador.controlador_usuario;
import java.io.IOException;
import java.net.ServerSocket;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author David
 */
public class CristEat_server {
    Thread conectora;
    int puerto;
    /**
     * @param args the command line arguments
     */
    javax.swing.JTextArea log;
    ArrayList<Multiserverthead> clientes;
   
    
    public CristEat_server(int puerto, javax.swing.JTextArea log){
        this.log = log;
        this.puerto = puerto;
        clientes = new ArrayList<Multiserverthead>();
       
        
    
    }
    //public static void main(String[] args) {
     public void startserver(){ 
       
        
        log.setText("Servidor iniciado");
        conectora = new Thread(new Hebranuevaconexion(puerto, log));
        conectora.start();
        
        
        
    }
    
    
    
}
