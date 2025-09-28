package com.example.modelo;

public class alumnoVO {

    int idAlumno;
    String nombre;
    String apellido;
    int dni;
    String email;
    int nroLegajo;
    char turno;
    String carrera;
    
    public alumnoVO() {
    }

    public alumnoVO(int idAlumno, String nombre, String apellido, int dni, String email, int nroLegajo, char turno,
            String carrera) {
        this.idAlumno = idAlumno;
        this.apellido = apellido;
        this.nombre = nombre;
        this.dni = dni;
        this.email = email;
        this.nroLegajo = nroLegajo;
        this.turno = turno;
        this.carrera = carrera;
    }

    public int getIdAlumno() {
        return idAlumno;
    }

    public void setIdAlumno(int idAlumno) {
        this.idAlumno = idAlumno;
    }

    public String getApellido() {
        return apellido;
    }

    public void setApellido(String apellido) {
        this.apellido = apellido;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
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

    public int getNroLegajo() {
        return nroLegajo;
    }

    public void setNroLegajo(int nroLegajo) {
        this.nroLegajo = nroLegajo;
    }

    public char getTurno() {
        return turno;
    }

    public void setTurno(char turno) {
        this.turno = turno;
    }

    public String getCarrera() {
        return carrera;
    }

    public void setCarrera(String carrera) {
        this.carrera = carrera;
    }

    @Override
    public String toString() {
        return "alumnoVO [idAlumno=" + idAlumno + ", apellido=" + apellido + ", nombre=" + nombre + ", dni=" + dni
                + ", email=" + email + ", nroLegajo=" + nroLegajo + ", turno=" + turno + ", carrera=" + carrera + "]";
    }

    

}
