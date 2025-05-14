package com.example;

import com.example.modelo.alumno;
import com.example.modelo.bibliotecario;
import com.example.modelo.libros;
import com.example.modelo.persona;

import java.util.ArrayList;
import java.util.Scanner;

// --- Clases nuevas que importamos para MANEJAR ARCHIVOS
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;

public class Main {
    private static final Scanner teclado = new Scanner(System.in);
    private static ArrayList<libros> listaLibros = new ArrayList<>();
    private static ArrayList<persona> listaPersonas = new ArrayList<>();
    private static ArrayList<alumno> listaAlumnos = new ArrayList<>();
    private static ArrayList<bibliotecario> listaBibliotecarios = new ArrayList<>();

    //----- en este punto tienen que poner la ruta donde ustedes dejaran los archivos
    private static final String ARCHIVO_LIBROS = "C:\\java\\biblioteca\\libros.dat";
    private static final String ARCHIVO_PERSONAS = "C:\\java\\biblioteca\\personas.dat";
    private static final String ARCHIVO_ALUMNOS = "C:\\java\\biblioteca\\alumnos.dat";
    private static final String ARCHIVO_BIBLIOTECARIOS = "C:\\java\\biblioteca\\bibliotecarios.dat";


    public static void main(String[] args) {
        int opcion;
        do {
            mostrarMenu();
            opcion = leerOpcion();
            procesarOpcion(opcion);
        } while (opcion != 11);
        
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
        System.out.println("9) Cargar Datos");
        System.out.println("10) Guardar Datos");
        
        System.out.println("11) Salir");
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
            /*
            case 3 -> cargarPersonas();
            case 4 -> listarPersonas();
            */
            case 5 -> cargarAlumnos();
            case 6 -> listarAlumnos(listaAlumnos);
            /*
            case 7 -> cargarBibliotecarios();
            case 8 -> listarBibliotecarios();
            */
            case 9 -> cargarDatos();    // Traerá todo lo que esté en los archivos a sus correspondientes arrays
            case 10 -> guardarDatos();  // Llevará todo lo que esté en los arrays a sus correspondientes archivos
            
            case 11 -> System.out.println("Saliendo del sistema...");
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

    private static void cargarAlumnos() {
        String opcion;
        do {
            alumno alumno = new alumno();

            System.out.println("Ingrese nombre del alumno: ");
            alumno.setNombre(teclado.nextLine());
            System.out.print("Ingrese apellido del alumno: ");
            alumno.setApellido(teclado.nextLine());
            System.out.print("Ingrese DNI (número): ");
            alumno.setDni(teclado.nextInt());
            teclado.nextLine(); // limpiar buffer
            System.out.print("Ingrese email: ");
            alumno.setEmail(teclado.nextLine());
            System.out.print("Ingrese Legajo (número): ");
            alumno.setNroLegajo(teclado.nextInt());
            teclado.nextLine(); // limpiar buffer
            System.out.print("Ingrese turno ( M / T / N ): ");
            alumno.setTurno(teclado.nextLine().charAt(0));
            System.out.print("Ingrese carrera: ");
            alumno.setEmail(teclado.nextLine());

            listaAlumnos.add(alumno);
            System.out.print("¿Desea ingresar otro alumno? (s/n): ");
            opcion = teclado.nextLine();
        } while (opcion.equalsIgnoreCase("s"));
    }

    private static void listarAlumnos(ArrayList<alumno> listaAlumnos){

        if (listaAlumnos.size() == 0) {

            System.out.println("No hay alumnos cargados en la lista!");
            
        } else {
        System.out.println("Cantidad de alumnos de la lista : " + listaAlumnos.size());
        // Mostrar todos los alumnos ingresados
        System.out.println("\n--- Lista de alumnos ingresados ---");
        for (alumno alu : listaAlumnos) {
            System.out.println(alu);
        }
    }
    }


        @SuppressWarnings("unchecked")
        private static void cargarDatos() {
        try (ObjectInputStream ois = new ObjectInputStream(new FileInputStream(ARCHIVO_LIBROS))) {
            listaLibros = (ArrayList<libros>) ois.readObject();
            System.out.println("Datos de libros cargados desde " + ARCHIVO_LIBROS);
        } catch (IOException | ClassNotFoundException e) {
            System.err.println("Error al cargar libros: " + e.getMessage());
        }
        
        try (ObjectInputStream ois = new ObjectInputStream(new FileInputStream(ARCHIVO_ALUMNOS))) {
            listaAlumnos = (ArrayList<alumno>) ois.readObject();
            System.out.println("Datos de alumnos cargados desde " + ARCHIVO_ALUMNOS);
        } catch (IOException | ClassNotFoundException e) {
            System.err.println("Error al cargar alumnos: " + e.getMessage());
        }

        // Repetir para los otros ArrayList
    }
    
    private static void guardarDatos() {
        try (ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream(ARCHIVO_LIBROS))) {
            oos.writeObject(listaLibros);
            System.out.println("Datos de libros guardados en " + ARCHIVO_LIBROS);
        } catch (IOException e) {
            System.err.println("Error al guardar libros: " + e.getMessage());
        }

        try (ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream(ARCHIVO_ALUMNOS))) {
            oos.writeObject(listaAlumnos);
            System.out.println("Datos de alumnos guardados en " + ARCHIVO_ALUMNOS);
        } catch (IOException e) {
            System.err.println("Error al guardar alumnos: " + e.getMessage());
        }

        // Repetir para los otros ArrayList (personas, bibliotecarios, etc.)
    }

}