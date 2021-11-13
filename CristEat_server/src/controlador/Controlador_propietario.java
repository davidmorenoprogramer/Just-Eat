/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controlador;

import java.util.Iterator;
import java.util.List;
import modelo.NewHibernateUtil;
import modelo.Plato;
import modelo.Propietario;
import org.hibernate.Query;
import org.hibernate.Session;

/**
 *
 * @author David
 */



public class Controlador_propietario {
    Session session;
   public Controlador_propietario(Session sesion){
       session = sesion;
          
   }
   public String buscar_propietario(int id){
       session.beginTransaction();
      
       //Query query = session.createQuery("FROM Propietario Where id : id ");
       Propietario propietario = (Propietario) session.load(Propietario.class,id);
    
       String Cadenapropietario;
       Cadenapropietario = "#" + propietario.getRestaurante().getId();
       Cadenapropietario = Cadenapropietario + "#" + propietario.getRestaurante().getNombre();
       Cadenapropietario = Cadenapropietario + "#" + propietario.getRestaurante().getTipoComida();
       Cadenapropietario = Cadenapropietario + "#" + propietario.getRestaurante().getDireccion();
       Cadenapropietario = Cadenapropietario + "#" + propietario.getRestaurante().getValoracion();
       Cadenapropietario = Cadenapropietario + "#" + propietario.getRestaurante().getPedidoMinimo();
       Cadenapropietario = Cadenapropietario + "#" + propietario.getRestaurante().getHoraApertura();
       Cadenapropietario = Cadenapropietario + "#" + propietario.getRestaurante().getHoraCierre();
       Cadenapropietario = Cadenapropietario + "#" + propietario.getRestaurante().getPlatos().size();
       
      
       for (Iterator it = propietario.getRestaurante().getPlatos().iterator(); it.hasNext();){
           
           Plato p = (Plato) it.next();
           Cadenapropietario = Cadenapropietario + "#" + p.getId();
           Cadenapropietario = Cadenapropietario + "#" + p.getNombre();
           Cadenapropietario = Cadenapropietario + "#" + p.getDescripcion();
           Cadenapropietario = Cadenapropietario + "#" + p.getPrecio();

       }       
       session.getTransaction().commit();
       
       return Cadenapropietario;
   } 
}
