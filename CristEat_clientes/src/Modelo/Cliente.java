package Modelo;
// Generated 05-feb-2019 8:13:07 by Hibernate Tools 4.3.1



/**
 * Cliente generated by hbm2java
 */
public class Cliente  implements java.io.Serializable {


     private int id;
     private Usuario usuario;
     private int numTarjetaCredito;

    public Cliente() {
    }

    public Cliente(Usuario usuario, int numTarjetaCredito) {
       this.usuario = usuario;
       this.numTarjetaCredito = numTarjetaCredito;
    }
   
    public int getId() {
        return this.id;
    }
    
    public void setId(int id) {
        this.id = id;
    }
    public Usuario getUsuario() {
        return this.usuario;
    }
    
    public void setUsuario(Usuario usuario) {
        this.usuario = usuario;
    }
    public int getNumTarjetaCredito() {
        return this.numTarjetaCredito;
    }
    
    public void setNumTarjetaCredito(int numTarjetaCredito) {
        this.numTarjetaCredito = numTarjetaCredito;
    }




}


