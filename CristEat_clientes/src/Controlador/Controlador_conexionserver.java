/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controlador;

import Modelo.Plato;
import Modelo.Propietario;
import Modelo.Restaurante;
import Vista.Log;
import Vista.PCliente;
import Vista.PPropietario;
import Vista.ventana_registro;
import java.io.BufferedReader;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import static java.lang.Integer.parseInt;
import java.net.Socket;
import java.net.UnknownHostException;
import java.util.ArrayList;
import java.util.Objects;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author David
 */
public class Controlador_conexionserver implements Runnable {
     Thread th;
     String mensaje;   
     String login;
     String pass;
     String clienteopropietario;
     ArrayList<Restaurante> restaurantes;
     ArrayList<Plato> platos;
     private String state = "Login";
     PPropietario propietario;
     PrintWriter out;
     boolean enviado = false;
     PCliente cliente;
     String id;
     int id_restauranteacambiar;
     private BufferedReader in;
     ventana_registro ventana;
     String fromUser;
     private String fromServer;
     byte[] bytes = new byte[512];
     byte[] bytes2 = new byte[512];
     File file;
     File file2;
     FileOutputStream fileout2;
     FileOutputStream fileout;
     ByteArrayOutputStream output;
     ByteArrayOutputStream output2;
     Log log;
     
    public Controlador_conexionserver(String login, String pass,PPropietario propietario, PCliente cliente, ventana_registro ventana)  {
         restaurantes = new ArrayList<>();
         platos = new ArrayList<>();
         this.login = login;
         this.pass = pass;
         th = new Thread(this,"2");
         this.ventana = ventana;
         this.cliente = cliente;
         this.propietario = propietario;
         log = new Log();
         log.setVisible(true);
        
    }
   public void enviarmensaje(String m){
       out.println(m);
       log.log(m);
   }
   
   public void cargarfoto(String mensaje) throws FileNotFoundException, IOException{
       
       
       
   
   }

    @Override
    public void run() {
        String hostName = "localhost";
        int portNumber = 5555;

        
            Socket kkSocket = null;
         try {
             kkSocket = new Socket(hostName, portNumber);
         } catch (IOException ex) {
             Logger.getLogger(Controlador_conexionserver.class.getName()).log(Level.SEVERE, null, ex);
         }
         try {
             out = new PrintWriter(kkSocket.getOutputStream(), true);
         } catch (IOException ex) {
             Logger.getLogger(Controlador_conexionserver.class.getName()).log(Level.SEVERE, null, ex);
         }
           
         {
            try {
                setIn(new BufferedReader(
                        new InputStreamReader(kkSocket.getInputStream())));
            } catch (IOException ex) {
                Logger.getLogger(Controlador_conexionserver.class.getName()).log(Level.SEVERE, null, ex);
            }
            BufferedReader stdIn =
                new BufferedReader(new InputStreamReader(System.in));
           
            try {
                // while de espera el login
                
                 if (getState().equals("Login")){
                        mensaje = "PROTOCOLCRISTEAT1.0#LOGIN" + "#" + login + "#" + pass;
                        setState("sendlogin");
                        enviarmensaje(mensaje);
                    }
                    
                while ((fromServer = getIn().readLine()) != null) {
                    
                    log.log(fromServer);
                    System.out.println("Server: " + getFromServer());
                    String[] part = getFromServer().split("#");
                    
                    if (getState().equals("sendlogin")){
                        
                        
                        if (part[0].equals("PROTOCOLCRISTEAT1.0") && part[1].equals("SERVER") && part[2].equals("LOGIN_CORRECT")&& part[3].equals("PROPIETARIO")){
                            CrearYañadirplatos crear = new CrearYañadirplatos(getFromServer(), platos);
                            id = part[6];
                            Restaurante p = new Restaurante(part[7],part[8]); 
                            propietario = new PPropietario(platos, this,parseInt(id),login,p);         
                            propietario.setVisible(true);
                            ventana.dispose();
                            this.setState("comprobarpyc");
                            
                            
                        }
                        if(part[0].equals("PROTOCOLCRISTEAT1.0") && part[1].equals("SERVER") && part[2].equals("LOGIN_CORRECT")&& part[3].equals("CLIENTE")){
                            CrearYañadirRestaurantes crear = new CrearYañadirRestaurantes(getFromServer(), restaurantes);
                            cliente = new PCliente(restaurantes, getState(),this, part[4]);
                            cliente.setVisible(true);
                            ventana.dispose();
                             this.setState("comprobarpyc");
                            
                        }
                        if(part[0].equals("PROTOCOLCRISTEAT1.0") && part[1].equals("SERVER") && part[2].equals("ERROR")&& part[3].equals("BAD_LOGIN")){
                            System.out.println("bad login");
                            
                        }
                    }
                    if(state.equals("comprobarpyc") && part[3].equals("CHANGE_STATE") && part[4].equals("OFFLINE")){
                            
                        int idres = parseInt(part[5]);
                       
                            for (int i = 0; i < restaurantes.size(); i++) {
                                
                                
                                if(Objects.equals(restaurantes.get(i).getId(), idres)) {
                                    cliente.eliminar_tablarestaurantes(i);
                                    restaurantes.remove(i);
                                    
                                }
                           }
                            
                            enviarmensaje("PROTOCOLCRISTEAT1.0#"+ login + "#RECEIVED_CHANGE_STATE#OFFLINE#"+ part[5] + "#" + id);
                            
                    }
                    else if (state.equals("comprobarpyc") && part[3].equals("CHANGE_STATE") && part[4].equals("ONLINE")){
                        //ID_RESTAURANTE#nombreRestaurante#tipoComida#direccion#valoracion#pedidoMinimo#horaApertura#horaCierre";
                        //int id , String nombre, String tipoComida, String direccion, double valoracion, double pedidoMinimo, String horaApertura, String horaCierre
                        int idres = parseInt(part[5]);
                        Restaurante r = new Restaurante(Integer.parseInt(part[5]), part[6], part[7],
           part[8], Double.parseDouble(part[9]), Double.parseDouble(part[10]) , part[11], part[12]);
                        restaurantes.add(r);
                        cliente.rellenar_tablarestaurantes(restaurantes);  
                        enviarmensaje("PROTOCOLCRISTEAT1.0#"+ login + "#RECEIVED_CHANGE_STATE#ONLINE#"+ part[5] + "#" + id);
                    
                    }
                    else if (state.equals("comprobarpyc") && part[2].equals("RESPONSE_GET_PLATOS")){
                        CrearYañadirplatos_cliente p = new CrearYañadirplatos_cliente(getFromServer());
                        cliente.insertar_platos(p.añadirplatos_cliente());
                        
                    }
                    else if (part[2].equals("STARTING_MULTIMEDIA_TRANSMISSION")){
                        
                        
                        bytes = new byte[512];
                        bytes2 = new byte[512];
                        file = new File(".\\fotos\\foto.jpg");
                        file2 = new File(".\\fotos\\video.mp4");
                        fileout2 = new FileOutputStream(file2);
                        fileout = new FileOutputStream(file);
                        output = new ByteArrayOutputStream();
                        output2 = new ByteArrayOutputStream();
                        //cargarfoto(getFromServer());
                        
                        
                    }  
                    else if (part[2].equals("RESPONSE_MULTIMEDIA_PHOTO")){
                         System.out.println("Decodificando: " + part[6]);
                         String decodificar = part[6];
                         bytes = java.util.Base64.getDecoder().decode(decodificar.getBytes()); 
                         fileout.write(bytes);
                         
                    }
                    else if (part[2].equals("RESPONSE_MULTIMEDIA_VIDEO")){
                        
                         System.out.println("Decodificando: " + part[6]);
                         String decodificar2 = part[6];
                         bytes = java.util.Base64.getDecoder().decode(decodificar2.getBytes()); 
                         fileout2.write(bytes);
                         
                    }
                    else if (part[2].equals("ENDING_MULTIMEDIA_TRANSMISSION")){
                        try {
            
                            System.out.println("terminada");
                                fileout.close();
                                fileout2.close();
                                cliente.ver_imagen();
                            }catch (Exception e) {}       
                    
                    
                    }
                   
                }
            } catch (IOException ex) {
                Logger.getLogger(Controlador_conexionserver.class.getName()).log(Level.SEVERE, null, ex);
            }
            

             
         }
    
        
        
    }

    /**
     * @return the state
     */
    public String getState() {
        return state;
    }

    /**
     * @param state the state to set
     */
    public void setState(String state) {
        this.state = state;
    }

    /**
     * @return the fromServer
     */
    public String getFromServer() {
        return fromServer;
    }

    /**
     * @param fromServer the fromServer to set
     */
    public void setFromServer(String fromServer) {
        this.fromServer = fromServer;
    }

    /**
     * @return the in
     */
    public BufferedReader getIn() {
        return in;
    }

    /**
     * @param in the in to set
     */
    public void setIn(BufferedReader in) {
        this.in = in;
    }

    
}
         
        

    

    

