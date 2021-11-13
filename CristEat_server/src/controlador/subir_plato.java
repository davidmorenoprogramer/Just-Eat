/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controlador;

import static java.lang.Integer.parseInt;
import java.util.HashSet;
import java.util.Set;
import modelo.Cliente;
import modelo.NewHibernateUtil;
import modelo.Plato;
import modelo.Propietario;
import modelo.Restaurante;
import modelo.Usuario;
import org.hibernate.Session;

/**
 *
 * @author David
 */
public class subir_plato {
    Session sesion;
    int id;
    String nombre;
    String descripcion;
    double precio;
    Set platitos;
    
    public subir_plato(Session sesion, int id, String nombre, String descripcion, double precio) {
       this.sesion = sesion;
       this.id = id;
       this.nombre = nombre;
       this.descripcion = descripcion;
       this.precio = precio;
    }
    
    public void buscar(){
    
    sesion.beginTransaction();    
    Restaurante user = (Restaurante) sesion.load(Restaurante.class,id);
    System.out.println(user.getNombre());
    Plato p = new Plato(user,nombre,descripcion, precio);
    sesion.save(p);
    sesion.getTransaction().commit();
    
  
    }
            
}
