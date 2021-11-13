/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controlador;


import Vista.PCliente;
import Vista.PPropietario;
import Vista.ventana_registro;

/**
 *
 * @author David
 */
public class Controlador_conexion {
    
    public Controlador_conexion(String login, String pass, PPropietario propietario, PCliente cliente, ventana_registro ventana){
        
        new Controlador_conexionserver(login,pass, propietario, cliente,ventana ).th.start();
        
    }

    
}
