/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package cristeat_server;

/**
 *
 * @author David
 */
import controlador.Buscar_platos;
import controlador.Controlador_cliente;
import controlador.Controlador_propietario;
import controlador.cambiarestado;
import controlador.controlador_buscarrestaurantecliente;
import controlador.controlador_usuario;
import controlador.subir_plato;
import java.net.*;
import java.io.*;
import static java.lang.Double.parseDouble;
import static java.lang.Integer.parseInt;
import static java.lang.System.in;
import java.util.ArrayList;
import java.util.Base64;
import java.util.regex.Pattern;
import javax.swing.JTextArea;
import modelo.NewHibernateUtil;
import org.hibernate.Session;

public class KnockKnockProtocol {
    private static final int WAITING = 0;
    private static final int SENTKNOCKKNOCK = 1;
    private static final int SENDLOGIN = 2;
    
    String mensaje;
    Multiserverthead multi;
    int contador_clientes=0;
    Session sesion;
    javax.swing.JTextArea log;
    InetAddress adress;
    public PrintWriter out;
    public PrintWriter outprincipal;
    //log.setText( log.getText() + "Cliente " + socket.getInetAddress() + " " + inputLine);
    private static final int NUMJOKES = 5;
    
    private int state = WAITING;
    public KnockKnockProtocol(javax.swing.JTextArea log, InetAddress inetAddress, PrintWriter out, Multiserverthead multi,PrintWriter outprincipal){
        this.log = log;
        this.out = out;
        this.outprincipal= outprincipal;
        this.adress = inetAddress;
        this.multi = multi;
    }
    synchronized void aumentobroad(){
        Multiserverthead.contador_broad++;
    }
    
    synchronized void changecliente(ArrayList <Multiserverthead> clientes){
         clientes.get(clientes.size() - 1).cliente = true;
    
    }
    
    public void enviarmensaje(String mensaje){
    
        outprincipal.println(mensaje);
        log.setText(log.getText() + "\n" + mensaje);
    }
    synchronized String broadcast(ArrayList <Multiserverthead> clientes, String id, String[] part){
        
            String cadena=null; 
            if(part[3].equals("OFFLINE")){
                cadena = "PROTOCOLCRISTEAT1.0#SERVER#BROADCAST#CHANGE_STATE#OFFLINE#" + id;
            }
            else if(part[3].equals("ONLINE")){
                sesion = NewHibernateUtil.getSessionFactory().openSession();

                controlador_buscarrestaurantecliente c= new controlador_buscarrestaurantecliente(sesion);
                String mensaje = c.buscar(part[4]);
                sesion.close();
                cadena = "PROTOCOLCRISTEAT1.0#SERVER#BROADCAST#CHANGE_STATE#ONLINE#" + mensaje;
                
            }
            
            System.out.println(clientes.size());
            for (int i = 0; i < clientes.size(); i++) {

                        
                        if (clientes.get(i).isCliente()){
                           clientes.get(i).enviarmensaje(cadena, out);
                           contador_clientes++;
                            System.out.println("numero clientes");
                        }           
            }
            
            while(contador_clientes != Multiserverthead.contador_broad ){}
            return "PROTOCOLCRISTEAT1.0#SERVER#CONFIRMED_STATE_CHANGE";
    }
    
 
    public String processInput(String theInput,ArrayList <Multiserverthead> clientes) throws FileNotFoundException, IOException {
        String theOutput = null;
        
        if (state == WAITING) {
            
           state = SENTKNOCKKNOCK;
           
        } else if (state == SENTKNOCKKNOCK) {
            
            if (theInput != null){
                String[] part = theInput.split("#");
                
                 
                if (part[1].equals("LOGIN")){
                    
                    sesion = NewHibernateUtil.getSessionFactory().openSession();
                    int id = new controlador_usuario(sesion).consultar_si_es_poc(part[2],part[3]);
                    if (id== -1){
                        theOutput = "PROTOCOLCRISTEAT1.0#SERVER#ERROR#BAD_LOGIN";
                        log.setText(log.getText() + " PROTOCOLCRISTEAT1.0#SERVER#ERROR#BAD_LOGIN " + " A cliente: " + adress);
                    }
                    else{
                        
                        if (new controlador_usuario(sesion).consultar_propietario(id)){
                           
                            String cadena = "PROTOCOLCRISTEAT1.0#SERVER#LOGIN_CORRECT#PROPIETARIO#" + id + "#" + part[2];
                            cadena = cadena + new Controlador_propietario(sesion).buscar_propietario(id);
                            log.setText(log.getText() + "PROTOCOLCRISTEAT1.0#SERVER#LOGIN_CORRECT#PROPIETARIO" + " A cliente: " + adress);
                            theOutput = cadena;
                            state = SENDLOGIN;
                        }
                        else {
                    
                            changecliente(clientes);
                            String cadena = "PROTOCOLCRISTEAT1.0#SERVER#LOGIN_CORRECT#CLIENTE";
                            cadena = cadena + new Controlador_cliente(sesion).encontrar_cliente(id);
                            log.setText(log.getText() + "PROTOCOLCRISTEAT1.0#SERVER#LOGIN_CORRECT#CLIENTE" + " A cliente: " + adress);
                            theOutput = cadena;
                            state = SENDLOGIN;
                            
                        }
                    }
                    sesion.close();
                }
               
            }
                        
             
            }
        else if (state == SENDLOGIN){
                String[] part = theInput.split("#");
                 if ((part[2].equals("CHANGE_STATE"))){
                     
                   sesion = NewHibernateUtil.getSessionFactory().openSession();
                   cambiarestado c = new cambiarestado(sesion,part[4],part[3]);
                   sesion.close();
                   theOutput = broadcast(clientes,part[4],part);
                   
                }
                
                if (part[2].equals("RECEIVED_CHANGE_STATE")){
                    //System.out.println("recibido");
                     aumentobroad();
                     
                }   
                if (part[2].equals("GET_PLATOS")){
                   sesion = NewHibernateUtil.getSessionFactory().openSession();
                   String mensaje = "PROTOCOLCRISTEAT1.0#SERVER#RESPONSE_GET_PLATOS#" + part[3];
                   Buscar_platos platos = new Buscar_platos(sesion);
                   mensaje = mensaje + platos.busca_infobasica(parseInt(part[3]));
                   sesion.close();
                   theOutput = mensaje;
                }
                if (part[2].equals("SUBIR_INFO_PLATO")){
                    sesion = NewHibernateUtil.getSessionFactory().openSession();
                    
                    subir_plato s = new subir_plato(sesion,parseInt(part[3]),part[4],part[5],parseDouble(part[6]));
                    s.buscar();
                    sesion.close();
                    enviarmensaje("PROTOCOLCRISTEAT1.0#SERVER#RECEIVED_SUBIR_FOTO_PLATO#"+part[3]);
                }
                
                if (part[2].equals("GET_DETAIL")){

                    
                    //ruta en bd
                    String ruta = ".\\DATA\\"+ part[3] + "\\foto.png";
                    String rutavideo = ".\\DATA\\"+ part[3] + "\\video.mp4";
                    
                    File file = new File(ruta);
                    File file2 = new File(rutavideo);
                    
                    enviarmensaje("PROTOCOLCRISTEAT1.0#SERVER#STARTING_MULTIMEDIA_TRANSMISSION#" + part[3]);
                    long tamanio = file.length();
                    long tamanio2 = file2.length();
                    //variable donde se guardan los bytes codificados
                    String codificado = null;
                    String codificado2 = null;
                    InputStream inputStream = null;
                    InputStream input2 = null;
                    try {
                        //guadamos los bytes en una variable
                       inputStream = new FileInputStream(file);
                       input2 = new FileInputStream(file2);
                       
                    } catch (Exception e) {
                    }
                    //creamos un buffer con el tamaño de los paquetes
                    byte[] buffer = new byte[512];
                    byte[] buffer2 = new byte[512];
                    int bytesRead;
                    int bytesRead2;
                    ByteArrayOutputStream output = new ByteArrayOutputStream();
                    ByteArrayOutputStream output2 = new ByteArrayOutputStream();
                    
                    try {
                        
                        //guardamos en el buffer 
                        while ((bytesRead = inputStream.read(buffer)) != -1) {
                            output.write(buffer);
                            codificado = Base64.getEncoder().encodeToString(buffer);
                            enviarmensaje("PROTOCOLCRISTEAT1.0#SERVER#RESPONSE_MULTIMEDIA_PHOTO#" 
                            + part[3] + "#" + tamanio + "#" + buffer.length
                            + "#" + codificado);
                           
                        }

                    } catch (IOException e) {
                        System.out.println("No encuentra la foto");
                    }               
                    inputStream.close();
                    
                      try {
                        
                        //guardamos en el buffer 
                        while ((bytesRead2 = input2.read(buffer2)) != -1) {
                            output2.write(buffer2);
                            codificado2 = Base64.getEncoder().encodeToString(buffer2);
                            enviarmensaje("PROTOCOLCRISTEAT1.0#SERVER#RESPONSE_MULTIMEDIA_VIDEO#" 
                            + part[3] + "#" + tamanio2 + "#" + buffer2.length
                            + "#" + codificado2);
                           
                        }

                    } catch (IOException e) {
                        System.out.println("No encuentra el video");
                    }
                    
                    input2.close();
                    enviarmensaje("PROTOCOLCRISTEAT1.0#SERVER#ENDING_MULTIMEDIA_TRANSMISSION#" + part[3]);
                                   
                    }                 
                }
            

            return theOutput;
    }

    
}
