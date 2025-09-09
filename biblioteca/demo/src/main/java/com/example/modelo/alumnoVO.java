package com.example.modelo;

public class alumnoVO extends personaVO {

    int idAlumno;
    int nroLegajo;
    char turno;
    String carrera;

    public alumnoVO(){
    }
    


    





    public alumnoVO(int idPersona, String nombre, String apellido, int dni, String email, int idAlumno, int nroLegajo,
            char turno, String carrera) {
        super(idPersona, nombre, apellido, dni, email);
        this.idAlumno = idAlumno;
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

    public int getIdAlumno() {
        return idAlumno;
    }

    public void setIdAlumno(int idAlumno) {
        this.idAlumno = idAlumno;
    }

    @Override
    public String toString() {
        return "alumnoVO [idAlumno=" + idAlumno + ", nroLegajo=" + nroLegajo + ", turno=" + turno + ", carrera="
                + carrera + "]";
    }

    
    
}
