/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controlador;

import Modelo.Restaurante;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author David
 */
public class CrearYañadirRestaurantes  {
   ArrayList<Restaurante> restaurantes; 
   String mensaje;
   //1#1#ejemplonombre#ejemplofood#ejemplodireccion#5.0#10:30#23:00

   public CrearYañadirRestaurantes(String mensaje,ArrayList<Restaurante> restaurantes) {
       this.mensaje = mensaje;
       this.restaurantes = new ArrayList<>();
       this.restaurantes = restaurantes;
       String[] part = mensaje.split("#");
       System.out.println("de cliente a servidor: " + mensaje);
       for (int i = 0; i < Integer.parseInt(part[8]) ; i++) {
           
           Restaurante r = new Restaurante(Integer.parseInt(part[9]), part[10], part[11],
           part[12], Double.parseDouble(part[13]), Double.parseDouble(part[14]) , part[15], part[16]);
           restaurantes.add(r);
       }
       
    }
    

    
    
}
