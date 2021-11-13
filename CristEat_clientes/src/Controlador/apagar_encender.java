/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controlador;
    //String apaga = mensaje = "PROTOCOLCRISTEAT1.0#" +"#CHANGE_STATE#OFFLINE#";

/**
 *
 * @author David
 */
public class apagar_encender {
    int id;
    String apaga;
    String login;
    Controlador_conexionserver conexion;
    
    public apagar_encender(int id, String login,Controlador_conexionserver conexion){
        this.id = id;
        this.login=login;
        this.conexion = conexion;
         
        
    }
    public void apagar(){
        
        apaga = "PROTOCOLCRISTEAT1.0#" + login +"#CHANGE_STATE#OFFLINE#" + id;
        conexion.enviarmensaje(apaga);
        conexion.setState("change");
    }
    public void encender(){
        apaga = "PROTOCOLCRISTEAT1.0#" + login +"#CHANGE_STATE#ONLINE#" + id;
        conexion.enviarmensaje(apaga);
        conexion.setState("change");
    }
    
}
