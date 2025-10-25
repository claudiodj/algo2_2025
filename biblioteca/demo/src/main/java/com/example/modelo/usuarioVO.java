package com.example.modelo;

import java.io.Serializable;

public class usuarioVO implements Serializable{

    int idUsuario;
    String usuario;
    String clave;
    
    public usuarioVO() {
    }

    public usuarioVO(int idUsuario, String usuario, String clave) {
        this.idUsuario = idUsuario;
        this.usuario = usuario;
        this.clave = clave;
    }

    public int getIdUsuario() {
        return idUsuario;
    }

    public void setIdUsuario(int idUsuario) {
        this.idUsuario = idUsuario;
    }

    public String getUsuario() {
        return usuario;
    }

    public void setUsuario(String usuario) {
        this.usuario = usuario;
    }

    public String getClave() {
        return clave;
    }

    public void setClave(String clave) {
        this.clave = clave;
    }

    

}
