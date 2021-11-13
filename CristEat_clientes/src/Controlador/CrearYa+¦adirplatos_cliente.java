/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controlador;

import Modelo.Plato;
import Modelo.Restaurante;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author David
 */
public class CrearYañadirplatos_cliente  {
   ArrayList<Plato> platos; 
   String mensaje;
   //1#1#ejemplonombre#ejemplofood#ejemplodireccion#5.0#10:30#23:00

    public CrearYañadirplatos_cliente(String fromServer) {
        this.mensaje = fromServer;
    }

   public ArrayList<Plato> añadirplatos_cliente() {
       
       this.platos = new ArrayList<>();
       
       //int id, Restaurante restaurante, String nombre, String descripcion, double precio
       //PROTOCOLCRISTEAT1.0#SERVER#LOGIN_CORRECT#PROPIETARIO#1#ejemplonombre#ejemplofood#ejemplodireccion#5.0#10.0#10:30#23:00#true#5#5#plato5#ejemplodescripcion#2.0#2#plato2#ejemplodescripcion#2.5#3#plato3#ejemplodescripcion#2.5#1#plato1#ejemplodescripcion#2.5#4#plato4#ejemplodescripcion#2.5
       String[] part = mensaje.split("#");
       Restaurante r = new Restaurante(Integer.parseInt(part[3]));
       
       
       for (int i = 0; i < Integer.parseInt(part[4]); i++) {
         
         Plato p = new Plato(Integer.parseInt(part[5 + (4 * i)]), r, part[6 + (4 * i)], part[7 + (4 * i)], Double.parseDouble(part[8 + (4 * i)]));
         platos.add(p);
           
       }
       return platos;
    }

    
    
}
