package com.example.modelo;

import java.util.Date;

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

    public int getNroEmpleado() {
        return nroEmpleado;
    }
    public void setNroEmpleado(int nroEmpleado) {
        this.nroEmpleado = nroEmpleado;
    }
    public char getTurno() {
        return turno;
    }
    public void setTurno(char turno) {
        this.turno = turno;
    }

    public String prestarLibro(libros libro, alumno alumno) {
        // Validaciones básicas
        if (libro == null) {
            return "Error: El libro no puede ser nulo";
        }
        
        if (alumno == null) {
            return "Error: El alumno no puede ser nulo";
        }
 
        // Registrar la fecha del préstamo 
        Date fechaPrestamo = new Date();
        
        // Retornar datos
        return "Préstamo registrado:\n" +
               "Libro: " + libro.getNombre() + " (ISBN: " + libro.getIsbn() + ")\n" +
               "Prestado a: " + alumno.getNombre() + ", " + alumno.getApellido() + 
               " (Legajo: " + alumno.getNroLegajo() + ")\n" +
               "Fecha: " + fechaPrestamo.toString() + "\n" +
               "Responsable: " + this.getNombre() + " (Empleado N°: " + this.nroEmpleado + ")";
    }
    
    public String catalogarLibro(libros libro) {
        // Validaciones básicas
        if (libro == null) {
            return "Error: El libro no puede ser nulo";
        }

        
        return null;


    }
    
}
