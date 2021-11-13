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
public class traer_platos {
    
    public traer_platos(int id,Controlador_conexionserver conexion,String login){
        String mensaje; 
        mensaje = "PROTOCOLCRISTEAT1.0#"+ login + "#GET_PLATOS#" + id;
        conexion.enviarmensaje(mensaje);
    }    
}
