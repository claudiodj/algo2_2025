package com.example;

import com.example.modelo.alumno;
import com.example.modelo.libros;
import com.example.modelo.persona;
import java.util.ArrayList;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {

        Scanner teclado = new Scanner(System.in);

        ArrayList<libros> listaLibros = new ArrayList<>();
        ArrayList<persona> listaPersonas = new ArrayList<>();
        ArrayList<alumno> listaAlumnos = new ArrayList<>();

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

        System.out.println("Cantidad de libros de la lista : " + listaLibros.size());
        // Mostrar todos los libros ingresados
        System.out.println("\n--- Lista de libros ingresados ---");
        for (libros lib : listaLibros) {
            System.out.println(lib);
        }

        // Ingreso de personas
        do {
            persona p = new persona();

            System.out.print("Ingrese nombre de la persona: ");
            p.setNombre(teclado.nextLine());

            System.out.print("Ingrese apellido: ");
            p.setApellido(teclado.nextLine());

            System.out.print("Ingrese DNI: ");
            p.setDni(teclado.nextInt());

            teclado.nextLine(); // limpiar buffer

            System.out.print("Ingrese email: ");
            p.setEmail(teclado.nextLine());

            listaPersonas.add(p);

            System.out.print("¿Desea ingresar otra persona? (s/n): ");
            opcion = teclado.nextLine();

        } while (opcion.equalsIgnoreCase("s"));

        System.out.println("Cantidad de personas agregadas a la lista: " + listaPersonas.size());
        System.out.println("\n--- Lista de personas ingresadas ---");
        for (persona p : listaPersonas) {
            System.out.println(p);
        }

        // Ingreso de alumnos
        do {

            alumno alumno = new alumno();

            System.out.println("Ingrese nombre del alumno: ");
            alumno.setNombre(teclado.nextLine());

            System.out.print("Ingrese apellido del alumno: ");
            alumno.setApellido(teclado.nextLine());

            System.out.print("Ingrese email del alumno: ");
            alumno.setEmail(teclado.nextLine());

            System.out.print("Ingrese DNI (número) del alumno: ");
            alumno.setDni(teclado.nextInt());

            teclado.nextLine(); // limpiar buffer

            System.out.print("Ingrese Legajo (número) del alumno: ");
            alumno.setNroLegajo(teclado.nextInt());

            teclado.nextLine(); // limpiar buffer
            
            System.out.print("Ingrese Turno del alumno (m/t/n): ");
            alumno.setTurno(teclado.nextLine().charAt(0));

            System.out.print("Ingrese Carrera del alumno: ");
            alumno.setCarrera(teclado.nextLine());

            listaAlumnos.add(alumno);

            System.out.print("¿Desea ingresar otro alumno? (s/n): ");
            opcion = teclado.nextLine();

        } while (opcion.equalsIgnoreCase("s"));

        System.out.println("Cantidad de alumnos de la lista : " + listaAlumnos.size());
        // Mostrar todos los alumnos ingresados
        System.out.println("\n--- Lista de alumnos ingresados ---");
        for (alumno alumn : listaAlumnos) {
            System.out.println(alumn);
        }

        
        teclado.close();

    }
}