/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controlador;

import java.util.List;
import modelo.NewHibernateUtil;
import modelo.Restaurante;
import modelo.Usuario;
import org.hibernate.Query;
import org.hibernate.Session;

/**
 *
 * @author David
 */
public class Controlador_restaurantesconectados {
    Session session;
    public Controlador_restaurantesconectados(Session sesion){
    
        session = sesion;
    
    }
    public List conectados(){
        
        
        boolean abierto = true;
        Query query = session.createQuery("FROM Restaurante where abierto = :abierto");
        query.setParameter("abierto",abierto);
        List<Restaurante> restaurantes = query.list();
       
        
        return restaurantes;
        
    }
}
