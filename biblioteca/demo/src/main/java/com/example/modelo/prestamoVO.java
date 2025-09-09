package com.example.modelo;

import java.io.Serializable;
import java.util.Date;

public class prestamoVO implements Serializable{

    int idPrestamo;
    bibliotecarioVO bibliotecario;
    libroVO libro;
    alumnoVO alumno;
    Date fechaPrestamo;
    Date fechaDevolucion;

    public prestamoVO(){}

    public prestamoVO(bibliotecarioVO bibliotecario, libroVO libro, alumnoVO alumno,
            Date fechaPrestamo, Date fechaDevolucion) {
        this.bibliotecario = bibliotecario;
        this.libro = libro;
        this.alumno = alumno;
        this.fechaPrestamo = fechaPrestamo;
        this.fechaDevolucion = fechaDevolucion;
    }

    public bibliotecarioVO getBibliotecario() {
        return bibliotecario;
    }

    public void setBibliotecario(bibliotecarioVO bibliotecario) {
        this.bibliotecario = bibliotecario;
    }

    public libroVO getLibro() {
        return libro;
    }

    public void setLibro(libroVO libro) {
        this.libro = libro;
    }

    public alumnoVO getAlumno() {
        return alumno;
    }

    public void setAlumno(alumnoVO alumno) {
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

    public int getIdPrestamo() {
        return idPrestamo;
    }

    public void setIdPrestamo(int idPrestamo) {
        this.idPrestamo = idPrestamo;
    }


    
    

}
