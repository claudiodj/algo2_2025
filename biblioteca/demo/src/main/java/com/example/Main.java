package com.example;

import com.example.modelo.alumno;
import com.example.modelo.bibliotecario;
import com.example.modelo.libros;
import com.example.modelo.persona;
import java.util.ArrayList;
import java.util.Scanner;

public class Main {
    private static final Scanner teclado = new Scanner(System.in);
    private static ArrayList<libros> listaLibros = new ArrayList<>();
    private static ArrayList<persona> listaPersonas = new ArrayList<>();
    private static ArrayList<alumno> listaAlumnos = new ArrayList<>();
    private static ArrayList<bibliotecario> listaBibliotecarios = new ArrayList<>();

    public static void main(String[] args) {
        int opcion;
        do {
            mostrarMenu();
            opcion = leerOpcion();
            procesarOpcion(opcion);
        } while (opcion != 9);
        
        teclado.close();
        System.out.println("Salimos del programa");
    }

    private static void mostrarMenu() {
        System.out.println("\n===== MENÚ PRINCIPAL =====");
        System.out.println("1) Cargar Libros");
        System.out.println("2) Listar Libros");
        System.out.println("3) Cargar Personas");
        System.out.println("4) Listar Personas");
        System.out.println("5) Cargar Alumnos");
        System.out.println("6) Listar Alumnos");
        System.out.println("7) Cargar Bibliotecarios");
        System.out.println("8) Listar Bibliotecarios");
        System.out.println("9) Salir");
        System.out.print("Ingrese una opción: ");
    }

    private static int leerOpcion() {
        try {
            return Integer.parseInt(teclado.nextLine());
        } catch (NumberFormatException e) {
            return -1; // Opción inválida
        }
    }

    private static void procesarOpcion(int opcion) {
        switch (opcion) {
            case 1 -> cargarLibros();
            case 2 -> listarLibros(listaLibros);
            /*case 3 -> cargarPersonas();
            case 4 -> listarPersonas();
            case 5 -> cargarAlumnos();
            case 6 -> listarAlumnos();
            case 7 -> cargarBibliotecarios();
            case 8 -> listarBibliotecarios();
            */
            case 9 -> System.out.println("Saliendo del sistema...");
            default -> System.out.println("Opción no válida. Intente nuevamente.");
        }
    }

     private static void cargarLibros() {
        String opcion;
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
    }

    private static void listarLibros(ArrayList<libros> listaLibros){

        if (listaLibros.size() == 0) {

            System.out.println("No hay libros cargados en la lista!");
            
        } else {
        System.out.println("Cantidad de libros de la lista : " + listaLibros.size());
        // Mostrar todos los libros ingresados
        System.out.println("\n--- Lista de libros ingresados ---");
        for (libros lib : listaLibros) {
            System.out.println(lib);
        }
    }


    }

    // Implementar los demás métodos (listarLibros, cargarPersonas, etc.)...
}