package modelo;
// Generated 05-feb-2019 8:13:07 by Hibernate Tools 4.3.1



/**
 * Propietario generated by hbm2java
 */
public class Propietario  implements java.io.Serializable {


     private int id;
     private Restaurante restaurante;
     private Usuario usuario;

    public Propietario() {
    }

    public Propietario(Restaurante restaurante, Usuario usuario) {
       this.restaurante = restaurante;
       this.usuario = usuario;
    }
   
    public int getId() {
        return this.id;
    }
    
    public void setId(int id) {
        this.id = id;
    }
    public Restaurante getRestaurante() {
        return this.restaurante;
    }
    
    public void setRestaurante(Restaurante restaurante) {
        this.restaurante = restaurante;
    }
    public Usuario getUsuario() {
        return this.usuario;
    }
    
    public void setUsuario(Usuario usuario) {
        this.usuario = usuario;
    }




}


