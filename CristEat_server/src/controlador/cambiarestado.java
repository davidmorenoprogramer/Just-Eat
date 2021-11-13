/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controlador;

import static java.lang.Integer.parseInt;
import modelo.Restaurante;
import org.hibernate.Session;

/**
 *
 * @author David
 */
public class cambiarestado {
     Session sesion;
     
     public cambiarestado(Session sesion,String id,String estado){
         sesion.beginTransaction();
         
         
         int idrestaurante = parseInt(id);
         Restaurante user = (Restaurante) sesion.load(Restaurante.class,idrestaurante);
         
         if (estado.equals("ONLINE")){
             System.out.println("estado en cambio online");
             user.setAbierto(true);
         }
         if (estado.equals("OFFLINE")){
             System.out.println("estado en cambio ofline");
            user.setAbierto(false);
         }
         
         
          sesion.update(user);
          sesion.getTransaction().commit();
     
     }
}
