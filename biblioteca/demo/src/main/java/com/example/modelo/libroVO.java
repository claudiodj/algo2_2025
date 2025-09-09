package com.example.modelo;

import java.io.Serializable;

public class libroVO implements Serializable {

    private int idLibro;
    private String nombre;
    private String autor;
    private String editorial;
    private int isbn;
    private String genero;
    private boolean discontinuo;
    
    public libroVO() {
    }

    public libroVO(int idLibro, String nombre, String autor, String editorial, int isbn, String genero,
            boolean discontinuo) {
        this.idLibro = idLibro;
        this.nombre = nombre;
        this.autor = autor;
        this.editorial = editorial;
        this.isbn = isbn;
        this.genero = genero;
        this.discontinuo = discontinuo;
    }



    public int getIdLibro() {
        return idLibro;
    }

    public void setIdLibro(int idLibro) {
        this.idLibro = idLibro;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getAutor() {
        return autor;
    }

    public void setAutor(String autor) {
        this.autor = autor;
    }

    public String getEditorial() {
        return editorial;
    }

    public void setEditorial(String editorial) {
        this.editorial = editorial;
    }

    public int getIsbn() {
        return isbn;
    }

    public void setIsbn(int isbn) {
        this.isbn = isbn;
    }

    public String getGenero() {
        return genero;
    }

    public void setGenero(String genero) {
        this.genero = genero;
    }

    public boolean isDiscontinuo() {
        return discontinuo;
    }

    public void setDiscontinuo(boolean discontinuo) {
        this.discontinuo = discontinuo;
    }

    @Override
    public String toString() {
        return "libroVO [idLibro=" + idLibro + ", nombre=" + nombre + ", autor=" + autor + ", editorial=" + editorial
                + ", isbn=" + isbn + ", genero=" + genero + ", discontinuo=" + discontinuo + "]";
    }

}


