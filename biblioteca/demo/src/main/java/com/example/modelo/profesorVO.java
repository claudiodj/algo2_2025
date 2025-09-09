package com.example.modelo;


public class profesorVO extends personaVO{

    private int idProfesor;
    private int legajo;
    private String materia;
    private int año;
    private char turno;

    public profesorVO(){}

    public profesorVO(int legajo, String materia, int año, char turno) {
        this.legajo = legajo;
        this.materia = materia;
        this.año = año;
        this.turno = turno;
    }
    public profesorVO(int idPersona, String nombre, String apellido, int dni, String email, int legajo, String materia,
            int año, char turno) {
        super(idPersona, nombre, apellido, dni, email);
        this.legajo = legajo;
        this.materia = materia;
        this.año = año;
        this.turno = turno;
    }
    public int getLegajo() {
        return legajo;
    }
    public void setLegajo(int legajo) {
        this.legajo = legajo;
    }
    public String getMateria() {
        return materia;
    }
    public void setMateria(String materia) {
        this.materia = materia;
    }
    public int getAño() {
        return año;
    }
    public void setAño(int año) {
        this.año = año;
    }
    public char getTurno() {
        return turno;
    }
    public void setTurno(char turno) {
        this.turno = turno;
    }
    public int getIdProfesor() {
        return idProfesor;
    }
    public void setIdProfesor(int idProfesor) {
        this.idProfesor = idProfesor;
    }

    
}
