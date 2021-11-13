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
public class CrearYañadirplatos  {
   ArrayList<Plato> platos; 
   String mensaje;
   //1#1#ejemplonombre#ejemplofood#ejemplodireccion#5.0#10:30#23:00

   public CrearYañadirplatos(String mensaje,ArrayList<Plato> platos) {
       this.mensaje = mensaje;
       this.platos = new ArrayList<>();
       this.platos = platos;
       //int id, Restaurante restaurante, String nombre, String descripcion, double precio
       //PROTOCOLCRISTEAT1.0#SERVER#LOGIN_CORRECT#PROPIETARIO#1#ejemplonombre#ejemplofood#ejemplodireccion#5.0#10.0#10:30#23:00#true#5#5#plato5#ejemplodescripcion#2.0#2#plato2#ejemplodescripcion#2.5#3#plato3#ejemplodescripcion#2.5#1#plato1#ejemplodescripcion#2.5#4#plato4#ejemplodescripcion#2.5
       String[] part = mensaje.split("#");
       Restaurante r = new Restaurante(part[7], part[9]);
       
       for (int i = 0; i < Integer.parseInt(part[14]) ; i++) {
           
           Plato p = new Plato(Integer.parseInt(part[15 + (4 * i)]), r, part[16 + (4 * i)], part[17 + (4 * i)], Double.parseDouble(part[18 + (4 * i)]));
           platos.add(p);
       }
       
    }

    
    
}
