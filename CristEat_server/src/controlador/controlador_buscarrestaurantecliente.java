/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controlador;

import static java.lang.Integer.parseInt;
import modelo.Cliente;
import modelo.NewHibernateUtil;
import modelo.Propietario;
import modelo.Restaurante;
import modelo.Usuario;
import org.hibernate.Session;

/**
 *
 * @author David
 */
public class controlador_buscarrestaurantecliente {
    Session sesion;
    public controlador_buscarrestaurantecliente(Session sesion) {
       this.sesion = sesion;
    }
    
    public String buscar(String id){
        //ID_RESTAURANTE#nombreRestaurante#tipoComida#direccion#valoracion#pedidoMinimo#horaApertura#horaCierre";
    int idrestaurante = parseInt(id);
        
    String mensaje= "";
    sesion.beginTransaction();
    Restaurante user = (Restaurante) sesion.load(Restaurante.class,idrestaurante);
    sesion.getTransaction().commit();
  
    
    mensaje = mensaje + "" + user.getId() + "#";
    mensaje = mensaje + "" + user.getNombre() + "#";
    mensaje = mensaje + "" + user.getTipoComida() + "#";
    mensaje = mensaje + "" + user.getDireccion() + "#";
    mensaje = mensaje + "" + user.getValoracion() + "#";
    mensaje = mensaje + "" + user.getPedidoMinimo() + "#";
    mensaje = mensaje + "" + user.getHoraApertura() + "#";
    mensaje = mensaje + "" + user.getHoraCierre();
        
    return mensaje;
    }
            
}
