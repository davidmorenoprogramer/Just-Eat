/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controlador;

import java.util.List;
import modelo.NewHibernateUtil;
import modelo.Propietario;
import modelo.Usuario;
import org.hibernate.HibernateException;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.Transaction;

/**
 *
 * @author David
 */

public class controlador_usuario {
    Session session;
    Usuario user;
    public controlador_usuario(Session sesion){
        session = sesion;
        
    }
    public boolean consultar_propietario(int id){
        boolean encontrado=false;
        session.beginTransaction();
        Propietario Query;
        Query query = session.createQuery("FROM Propietario");
        List<Propietario> propietario = query.list();
        session.getTransaction().commit();
        
        for (Propietario p : propietario) {
            if (p.getId() == id){              
                encontrado = true;     
            }
        }
        
        return encontrado;
        
    }
    public int consultar_login(String login, String password){
        session.beginTransaction();
        boolean encontrado = false;
        Usuario Query;
        Query query = session.createQuery("FROM Usuario");
        List<Usuario> usuarios = query.list();
        for (Usuario usuario : usuarios) {
            if ((usuario.getPassword().equals(password)&& (usuario.getLogin().equals(login)))){
                
                encontrado = true;
                user = usuario;
            }
 
        }
        
        
        session.getTransaction().commit();
        
        if (encontrado == true){
           return user.getId();
        }
        else{
            return -1;
        
        }
       
    
}

    public int consultar_si_es_poc(String login, String password){
        
        String cadena;
        int id = consultar_login(login,password);
        
        if (id == -1){
            return -1;
        
        }
        else {
            return id;
           
        }    

        
    }
    
}
