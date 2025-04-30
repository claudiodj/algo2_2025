package com.example.modelo;

public class alumno extends persona{

    int nroLegajo;
    char turno;
    String carrera;

    public alumno(){

    }
    
    public alumno(int nroLegajo, char turno, String carrera) {
        this.nroLegajo = nroLegajo;
        this.turno = turno;
        this.carrera = carrera;
    }
    public alumno(String nombre, String apellido, int dni, String email, int nroLegajo, char turno, String carrera) {
        super(nombre, apellido, dni, email);
        this.nroLegajo = nroLegajo;
        this.turno = turno;
        this.carrera = carrera;
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

    
    
}
