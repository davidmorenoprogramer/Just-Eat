/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package cristeat_server;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author David
 */
public class Multiserverthead implements Runnable {
   
    private Socket socket = null;
    boolean candelete = false;
    Thread thr;
    public static int contador_broad;
    PrintWriter broad;
    PrintWriter out;
    boolean cliente = false; 
    
    javax.swing.JTextArea log;
    ArrayList <Multiserverthead> clientes = new ArrayList();
     
    public Multiserverthead(Socket socket,javax.swing.JTextArea log, ArrayList <Multiserverthead> clientes) {
        this.log = log;
        this.clientes = clientes;
        thr = new Thread(this, "" + socket.getInetAddress() + "id en base de datos: " + clientes.size());
        this.socket = socket;
        log.setText( log.getText() + "\n" + "Cliente" + socket.getInetAddress() + " Conectado");
    }

    public boolean getcadelete(){
    
        return candelete;
    }
    
    public synchronized void enviarmensaje(String mensaje, PrintWriter broad){
    
        out.println(mensaje);
        log.setText( log.getText() + "\n" + mensaje);
    }
    
    
    
    public void run() {

       
        try {
            out = new PrintWriter(socket.getOutputStream(), true);
        } catch (IOException ex) {
            Logger.getLogger(Multiserverthead.class.getName()).log(Level.SEVERE, null, ex);
        }
            BufferedReader in = null;
        try {
            in = new BufferedReader(
                    new InputStreamReader(
                            socket.getInputStream()));
        } catch (IOException ex) {
            Logger.getLogger(Multiserverthead.class.getName()).log(Level.SEVERE, null, ex);
        }
       
            String inputLine, outputLine;
            KnockKnockProtocol kkp = new KnockKnockProtocol(log, socket.getInetAddress(), broad,this,out);
        try {
            outputLine = kkp.processInput(null,clientes);
        } catch (IOException ex) {
            Logger.getLogger(Multiserverthead.class.getName()).log(Level.SEVERE, null, ex);
        }
            broad = out;
            
        try {
            while ((inputLine = in.readLine()) != null) {
                log.setText(log.getText() + "\n" + inputLine );  
                outputLine = kkp.processInput(inputLine,clientes);
                if (outputLine!=null){
                    enviarmensaje(outputLine, out);
                     
                }
                
               
                
                
                
            }
        } catch (IOException ex) {
            Logger.getLogger(Multiserverthead.class.getName()).log(Level.SEVERE, null, ex);
        }
            
        try {
            socket.close();
        } catch (IOException ex) {
            Logger.getLogger(Multiserverthead.class.getName()).log(Level.SEVERE, null, ex);
        }
            
        
    }

    /**
     * @return the broadcast
     */
  

    /**
     * @return the cliente
     */
    public boolean isCliente() {
        return cliente;
    }

    /**
     * @param cliente the cliente to set
     */
    public void setCliente(boolean cliente) {
        this.cliente = cliente;
    }
}
