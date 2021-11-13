/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controlador;

/**
 *
 * @author David
 */
public class controlador_pedirinfoplatos {
    public controlador_pedirinfoplatos(int id,Controlador_conexionserver conexion, String login){
        String mensaje; 
        
        mensaje = "PROTOCOLCRISTEAT1.0#"+ login + "#GET_DETAIL#"+ id;
        conexion.enviarmensaje(mensaje);
    
    }
}
