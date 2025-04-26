package com.example.modelo;

public class bibliotecario extends persona {

    int nroEmpleado;
    char turno;
    
    public bibliotecario(int nroEmpleado, char turno) {
        this.nroEmpleado = nroEmpleado;
        this.turno = turno;
    }
    public bibliotecario(String nombre, String apellido, int dni, String email, int nroEmpleado, char turno) {
        super(nombre, apellido, dni, email);
        this.nroEmpleado = nroEmpleado;
        this.turno = turno;
    }



    
}
