package com.example.modelo;

import java.io.Serializable;

public class persona implements Serializable{

    private String nombre;
    private String apellido;
    private int dni;
    private String email;

    public persona() {
    }

    public persona(String nombre, String apellido, int dni, String email) {
        this.nombre = nombre;
        this.apellido = apellido;
        this.dni = dni;
        this.email = email;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getApellido() {
        return apellido;
    }

    public void setApellido(String apellido) {
        this.apellido = apellido;
    }

    public int getDni() {
        return dni;
    }

    public void setDni(int dni) {
        this.dni = dni;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    @Override
    public String toString() {
        return "persona [nombre=" + nombre + ", apellido=" + apellido + ", dni=" + dni + ", email=" + email + "]";
    }

    
    
}
