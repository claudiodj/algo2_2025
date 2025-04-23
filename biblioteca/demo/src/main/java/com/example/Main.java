package com.example;

import com.example.modelo.libros;
import com.example.modelo.persona;
import java.util.ArrayList;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {

        Scanner teclado = new Scanner(System.in);

        ArrayList<libros> listaLibros = new ArrayList<>();
        ArrayList<persona> listaPersonas = new ArrayList<>();

        String opcion;

        // Ingreso de libros
        do {

            libros libro = new libros();

            System.out.println("Ingrese nombre del libro: ");
            libro.setNombre(teclado.nextLine());

            System.out.print("Ingrese autor: ");
            libro.setAutor(teclado.nextLine());

            System.out.print("Ingrese editorial: ");
            libro.setEditorial(teclado.nextLine());

            System.out.print("Ingrese ISBN (número): ");
            libro.setIsbn(teclado.nextInt());

            teclado.nextLine(); // limpiar buffer

            System.out.print("Ingrese género: ");
            libro.setGenero(teclado.nextLine());

            System.out.print("¿Está discontinuado? (s/n): ");
            if (teclado.nextLine().equalsIgnoreCase("s")) {
                libro.setDiscontinuo(true);
            }

            listaLibros.add(libro);

            System.out.print("¿Desea ingresar otro libro? (s/n): ");
            opcion = teclado.nextLine();

        } while (opcion.equalsIgnoreCase("s"));

        // Mostrar todos los libros ingresados
        System.out.println("\n--- Lista de libros ingresados ---");
        for (libros lib : listaLibros) {
            System.out.println(lib);
        }

        /*
         * 
         * // Ingreso de personas
         * do {
         * persona p = new persona();
         * 
         * System.out.print("Ingrese nombre de la persona: ");
         * p.setNombre(scanner.nextLine());
         * 
         * System.out.print("Ingrese apellido: ");
         * p.setApellido(scanner.nextLine());
         * 
         * System.out.print("Ingrese DNI: ");
         * p.setDni(scanner.nextInt());
         * 
         * scanner.nextLine(); // limpiar buffer
         * 
         * System.out.print("Ingrese email: ");
         * p.setEmail(scanner.nextLine());
         * 
         * listaPersonas.add(p);
         * 
         * System.out.print("¿Desea ingresar otra persona? (s/n): ");
         * opcion = scanner.nextLine();
         * 
         * } while (opcion.equalsIgnoreCase("s"));
         * 
         * System.out.println("\n--- Lista de personas ingresadas ---");
         * for (persona p : listaPersonas) {
         * System.out.println(p);
         * }
         * 
         */

        // System.out.println("Hello world!");
        // aca voy a declarar la variable libro1 vacia
        // libros libro1 = new libros();
        // libros libro3 = new libros();
        /*
         * aca declaro la variable libro2
         * con los datos
         * de cada
         * parametro
         */
        // libros libro2 = new libros("El Principito","Saint
        // Exupery","Galerna",123,"Fantasia",false);

        // System.out.println("libro 1: " + libro1.toString());
        // System.out.println("libro 2: " + libro2.toString());

        // persona persona1 = new persona();

        // persona1.setNombre("Pepe");
        // persona1.setApellido("Biondi");

        // persona1.setDni(1234);
        // persona1.setEmail("pepe_biondi@pepe.com");

        // System.out.println(persona1.toString());

        // libro1.setNombre("Cien años de soledad");
        // libro1.nombre = "Cien años de soledad";

        // persona persona5 = new persona();

        // persona5.setNombre("Juan");

        // System.out.println(persona5.toString());

        // libro1.nombre = "El capitan Garfio";
        // libro1.autor = "Celine Dione";

        // libro1.setAutor("Gabriel García Márquez");

        // System.out.println("El autor del libro1 es : " + libro1.getAutor());

        // System.out.println("libro 1: " + libro1.toString());

        // System.out.println("El nombre del libro1 es : " + libro1.getNombre());

        teclado.close();

    }
}