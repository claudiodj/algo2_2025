package com.example;

import com.example.modelo.libros;

public class Main {
    public static void main(String[] args) {
        System.out.println("Hello world!");
        // aca voy a declarar la variable libro1 vacia
    libros libro1 = new libros();
    /*
     * aca declaro la variable libro2
     * con los datos
     * de cada 
     * parametro
     */
    libros libro2 = new libros("El Principito","Saint Exupery","Galerna",123,"Fantasia",false);

    System.out.println("libro 1: " + libro1.toString());
    System.out.println("libro 2: " + libro2.toString());

    libro1.setNombre("Cien años de soledad");
    //libro1.nombre = "Cien años de soledad";
    libro1.setAutor("Gabriel García Márquez");

    System.out.println("El autor del libro1 es : " + libro1.getAutor());

    System.out.println("libro 1: " + libro1.toString());
    
        
    }
}