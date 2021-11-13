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
public class subir_platos {
    
    public subir_platos(int idrestaurante,Controlador_conexionserver conexion,String login, String nombre, String descripcion, String precio){
        String mensaje; 
        mensaje = "PROTOCOLCRISTEAT1.0#"+login+"#SUBIR_INFO_PLATO#" + idrestaurante + "#" + nombre + "#" + descripcion + "#" + precio;
        conexion.enviarmensaje(mensaje);
    }    
}
