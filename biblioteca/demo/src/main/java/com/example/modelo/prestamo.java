package com.example.modelo;

import java.io.Serializable;
import java.util.Date;

public class prestamo implements Serializable{

    bibliotecario bibliotecario;
    libros libro;
    alumno alumno;
    Date fechaPrestamo;
    Date fechaDevolucion;

    public prestamo(){}

    public prestamo(bibliotecario bibliotecario, libros libro, alumno alumno,
            Date fechaPrestamo, Date fechaDevolucion) {
        this.bibliotecario = bibliotecario;
        this.libro = libro;
        this.alumno = alumno;
        this.fechaPrestamo = fechaPrestamo;
        this.fechaDevolucion = fechaDevolucion;
    }

    public bibliotecario getBibliotecario() {
        return bibliotecario;
    }

    public void setBibliotecario(bibliotecario bibliotecario) {
        this.bibliotecario = bibliotecario;
    }

    public libros getLibro() {
        return libro;
    }

    public void setLibro(libros libro) {
        this.libro = libro;
    }

    public alumno getAlumno() {
        return alumno;
    }

    public void setAlumno(alumno alumno) {
        this.alumno = alumno;
    }

    public Date getFechaPrestamo() {
        return fechaPrestamo;
    }

    public void setFechaPrestamo(Date fechaPrestamo) {
        this.fechaPrestamo = fechaPrestamo;
    }

    public Date getFechaDevolucion() {
        return fechaDevolucion;
    }

    public void setFechaDevolucion(Date fechaDevolucion) {
        this.fechaDevolucion = fechaDevolucion;
    }

    @Override
    public String toString() {
        return "prestamo [bibliotecario=" + bibliotecario + ", libro=" + libro + ", alumno=" + alumno
                + ", fechaPrestamo=" + fechaPrestamo + ", fechaDevolucion=" + fechaDevolucion + "]";
    }

    

}
