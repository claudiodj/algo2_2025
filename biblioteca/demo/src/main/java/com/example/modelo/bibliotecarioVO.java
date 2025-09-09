package com.example.modelo;

import java.util.Date;

public class bibliotecarioVO extends personaVO {

    int idBibliotecario;
    int nroEmpleado;
    char turno;

    public bibliotecarioVO(){}

    public bibliotecarioVO(int idPersona, String nombre, String apellido, int dni, String email, int idBibliotecario,
            int nroEmpleado, char turno) {
        super(idPersona, nombre, apellido, dni, email);
        this.idBibliotecario = idBibliotecario;
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

    

    public String prestarLibro(libroVO libro, alumnoVO alumno) {
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
    
    public String catalogarLibro(libroVO libro) {
        // Validaciones básicas
        if (libro == null) {
            return "Error: El libro no puede ser nulo";
        }

        
        return null;


    }

    

    @Override
    public String toString() {
        return "bibliotecarioVO [idBibliotecario=" + idBibliotecario + ", nroEmpleado=" + nroEmpleado + ", turno="
                + turno + "]";
    }

    public int getIdBibliotecario() {
        return idBibliotecario;
    }

    public void setIdBibliotecario(int idBibliotecario) {
        this.idBibliotecario = idBibliotecario;
    }

    
}
