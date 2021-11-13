package Modelo;
// Generated 05-feb-2019 8:13:07 by Hibernate Tools 4.3.1



/**
 * Usuario generated by hbm2java
 */
public class Usuario  implements java.io.Serializable {


     private Integer id;
     private String login;
     private String password;
     private Cliente cliente;
     private Propietario propietario;

    public Usuario() {
    }

	
    public Usuario(String login, String password) {
        this.login = login;
        this.password = password;
    }
    public Usuario(String login, String password, Cliente cliente, Propietario propietario) {
       this.login = login;
       this.password = password;
       this.cliente = cliente;
       this.propietario = propietario;
    }
   
    public Integer getId() {
        return this.id;
    }
    
    public void setId(Integer id) {
        this.id = id;
    }
    public String getLogin() {
        return this.login;
    }
    
    public void setLogin(String login) {
        this.login = login;
    }
    public String getPassword() {
        return this.password;
    }
    
    public void setPassword(String password) {
        this.password = password;
    }
    public Cliente getCliente() {
        return this.cliente;
    }
    
    public void setCliente(Cliente cliente) {
        this.cliente = cliente;
    }
    public Propietario getPropietario() {
        return this.propietario;
    }
    
    public void setPropietario(Propietario propietario) {
        this.propietario = propietario;
    }




}


