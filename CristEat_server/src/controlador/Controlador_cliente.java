/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controlador;

import java.util.List;
import modelo.Cliente;
import modelo.NewHibernateUtil;
import modelo.Restaurante;
import org.hibernate.Session;

/**
 *
 * @author David
 */
public class Controlador_cliente {
    Session session;
    public Controlador_cliente(Session sesion){
        session = sesion; 
          
    
    }
    public String encontrar_cliente(int id){
        session.beginTransaction();
        
        Cliente cliente = (Cliente) session.load(Cliente.class,id);
    
        String Cadenacliente;
        Cadenacliente = "#" + cliente.getId();
        Cadenacliente = Cadenacliente + "#" + cliente.getUsuario().getLogin();
        Cadenacliente = Cadenacliente + "#" + cliente.getUsuario().getPassword();
        Cadenacliente = Cadenacliente + "#" + cliente.getNumTarjetaCredito();
        
        List<Restaurante> restaurantes = new Controlador_restaurantesconectados(session).conectados();
        Cadenacliente = Cadenacliente + "#" + restaurantes.size();
        
        for (int i = 0; i < restaurantes.size(); i++) {
            Cadenacliente = Cadenacliente + "#" + restaurantes.get(i).getId();
            Cadenacliente = Cadenacliente + "#" + restaurantes.get(i).getNombre();
            Cadenacliente = Cadenacliente + "#" + restaurantes.get(i).getTipoComida();
            Cadenacliente = Cadenacliente + "#" + restaurantes.get(i).getDireccion();
            Cadenacliente = Cadenacliente + "#" + restaurantes.get(i).getValoracion();
            Cadenacliente = Cadenacliente + "#" + restaurantes.get(i).getPedidoMinimo();
            Cadenacliente = Cadenacliente + "#" + restaurantes.get(i).getHoraApertura();
            Cadenacliente = Cadenacliente + "#" + restaurantes.get(i).getHoraCierre();
        }
        
        
        
        
        return Cadenacliente;
    
    } 
}
