package com.example;

import com.example.modelo.libros;
import com.example.modelo.persona;

public class Main {
    public static void main(String[] args) {
        System.out.println("Hello world!");
        // aca voy a declarar la variable libro1 vacia
    libros libro1 = new libros();
    libros libro3 = new libros();
    /*
     * aca declaro la variable libro2
     * con los datos
     * de cada 
     * parametro
     */
    libros libro2 = new libros("El Principito","Saint Exupery","Galerna",123,"Fantasia",false);

    System.out.println("libro 1: " + libro1.toString());
    System.out.println("libro 2: " + libro2.toString());

persona persona1 = new persona();

persona1.setNombre("Pepe");
persona1.setApellido("Biondi");

persona1.setDni(1234);
persona1.setEmail("pepe_biondi@pepe.com");

System.out.println(persona1.toString());

    libro1.setNombre("Cien años de soledad");
    //libro1.nombre = "Cien años de soledad";

    persona persona5 = new persona();

    persona5.setNombre("Juan");

    System.out.println(persona5.toString());

    //libro1.nombre = "El capitan Garfio";
    //libro1.autor = "Celine Dione";

    libro1.setAutor("Gabriel García Márquez");

    System.out.println("El autor del libro1 es : " + libro1.getAutor());

    System.out.println("libro 1: " + libro1.toString());
    
    System.out.println("El nombre del libro1 es : " + libro1.getNombre());
        
    }
}