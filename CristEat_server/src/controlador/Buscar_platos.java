/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controlador;

import java.util.Iterator;
import java.util.List;
import modelo.Plato;
import modelo.Restaurante;
import org.hibernate.Query;
import org.hibernate.Session;

/**
 *
 * @author David
 */
public class Buscar_platos {
    Session sesion;
    public Buscar_platos(Session sesion){
        this.sesion = sesion;
    
    
    }
    
    public String busca_infobasica(int id){
    
        sesion.beginTransaction();
        Restaurante user = (Restaurante) sesion.load(Restaurante.class,id);
        sesion.getTransaction().commit();
        String mensaje = "#" + user.getPlatos().size();
        
         for (Iterator it = user.getPlatos().iterator(); it.hasNext();){
            Plato p = (Plato) it.next();
            
            mensaje = mensaje + "#" + p.getId();
            mensaje = mensaje + "#" + p.getNombre();
            mensaje = mensaje + "#" + p.getDescripcion();
            mensaje = mensaje + "#" + p.getPrecio();
         }
        
        
        
        return mensaje;
        
    }
}
