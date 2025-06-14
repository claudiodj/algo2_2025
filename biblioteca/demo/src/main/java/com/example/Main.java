package com.example;

import com.example.modelo.alumno;
import com.example.modelo.bibliotecario;
import com.example.modelo.libros;
import com.example.modelo.persona;
import com.example.modelo.prestamo;

import java.util.ArrayList;
import java.util.Date;
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
    private static ArrayList<prestamo> listaPrestamos = new ArrayList<>();

    // ----- en este punto tienen que poner la ruta donde ustedes dejaran los
    // archivos
    private static final String RUTA_ARCHIVOS = "C:\\java\\biblioteca\\";
    private static final String ARCHIVO_LIBROS = RUTA_ARCHIVOS + "libros.dat";
    private static final String ARCHIVO_PERSONAS = RUTA_ARCHIVOS + "personas.dat";
    private static final String ARCHIVO_ALUMNOS = RUTA_ARCHIVOS + "alumnos.dat";
    private static final String ARCHIVO_BIBLIOTECARIOS = RUTA_ARCHIVOS + "bibliotecarios.dat";
    private static final String ARCHIVO_PRESTAMOS = RUTA_ARCHIVOS + "prestamos.dat";

    private static int hash_listaLibros;
    private static int hash_listaAlumnos;
    private static int hash_listaPersonas;
    private static int hash_listaBibliotecarios;
    private static int hash_listaPrestamos;

    public static void main(String[] args) {

        cargarDatosDesdeLosArchivos(); // Traerá todo lo que esté en los archivos a sus correspondientes arrays

        hash_listaLibros = listaLibros.hashCode();
        hash_listaAlumnos = listaAlumnos.hashCode();
        hash_listaPersonas = listaPersonas.hashCode();
        hash_listaBibliotecarios = listaBibliotecarios.hashCode();
        hash_listaPrestamos = listaPrestamos.hashCode();
        System.out.println("Hash ni bien lei el archivo libros: " + hash_listaLibros);
        System.out.println("Hash ni bien lei el archivo alumnos: " + hash_listaAlumnos);

        int opcion;
        do {
            mostrarMenu();
            opcion = leerOpcion();
            procesarOpcion(opcion);
        } while (opcion != 99);

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
        // System.out.println("9) Cargar Datos de Archivos a Memoria");
        System.out.println("10) Guardar Datos de Memoria a Archivos");
        System.out.println("11) Registrar prestamo de libro");
        System.out.println("12) Listar Prestamos");
        System.out.println("13) Registrar devolucion de libro");
        System.out.println("14) Buscar Libros por Nombre");
        System.out.println("15) Buscar Libros por Autor");

        System.out.println("99) Salir");
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
             * case 3 -> cargarPersonas();
             * case 4 -> listarPersonas();
             */
            case 5 -> cargarAlumnos();
            case 6 -> listarAlumnos(listaAlumnos);
            case 7 -> cargarBibliotecarios();
            case 8 -> listarBibliotecarios(listaBibliotecarios);
            // case 9 -> cargarDatosDesdeLosArchivos(); // Traerá todo lo que esté en los
            // archivos a sus correspondientes arrays
            case 10 -> guardarDatosALosArchivos(); // Llevará todo lo que esté en los arrays a sus correspondientes archivos
            case 11 -> prestarLibro();
            case 12 -> listarPrestamos(listaPrestamos);
            case 13 -> recuperarLibro();
            case 14 -> buscarLibroPorNombre();
            case 15 -> buscarLibroPorAutor();
            case 99 -> revisarAntesDeSalir(hash_listaLibros, hash_listaAlumnos, hash_listaBibliotecarios, hash_listaPrestamos);
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

    private static void listarLibros(ArrayList<libros> listaLibros) {

        if (listaLibros.size() == 0) {

            System.out.println("No hay libros cargados en la lista!");

        } else {
            System.out.println("Cantidad de libros de la lista : " + listaLibros.size());
            // Mostrar todos los libros ingresados
            System.out.println("\n--- Lista de libros ingresados ---");
            for (libros lib : listaLibros) {
                System.out.println("Indice en la lista: " + listaLibros.indexOf(lib));
                System.out.println(lib);
            }
            System.err.println("Hash actual: " + listaLibros.hashCode());
        }
    }

private static void buscarLibroPorNombre(){

System.out.println("Ingrese nombre del libro a buscar: ");

        String nombreBuscado = (teclado.nextLine());

        int resultado = buscarLibro(listaLibros, nombreBuscado);

        if (resultado < 0) {
            System.out.println("El libro: " + nombreBuscado + " no se ha encontrado!");
            }else{
                System.out.println("El libro: " + nombreBuscado + " se ha encontrado!");
                System.out.println(listaLibros.get(resultado));
                }
        }

    private static int buscarLibro(ArrayList<libros> listaLibros, String nombreBuscado) {

        int indiceLibro = -1;

        if (listaLibros.size() == 0) {

            System.out.println("No hay libros cargados en la lista!");

        } else {

            for (libros lib : listaLibros) {
                if (lib.getNombre().equals(nombreBuscado)) {
                    indiceLibro = listaLibros.indexOf(lib);

                    //System.out.println("Encontrado! Indice en la lista: " + listaLibros.indexOf(lib));
                    //System.out.println(lib);
                } else {
                    //System.out.println("No Encontrado!");
                }
            }
        }

        return indiceLibro;
    }

        private static void buscarLibroPorAutor(){

        System.out.println("Ingrese nombre del Autor a buscar: ");

        String nombreBuscadoAutor = (teclado.nextLine());

        ArrayList<Integer> resultado = buscarPorAutor(listaLibros, nombreBuscadoAutor);

        if (resultado.size() == 0) {
            System.out.println("El Autor: " + nombreBuscadoAutor + " no se ha encontrado!");
            }else{
                System.out.println("El autor: " + nombreBuscadoAutor + " se ha encontrado!");
                for(int indiceLibro : resultado)
                System.out.println(listaLibros.get(indiceLibro));
                }
        }

        private static ArrayList<Integer> buscarPorAutor(ArrayList<libros> listaLibros, String nombreBuscado) {

        ArrayList<Integer> librosDeAutor = new ArrayList<>();

        if (listaLibros.size() == 0) {

            System.out.println("No hay libros cargados en la lista!");

        } else {

            for (libros lib : listaLibros) {
                if (lib.getAutor().equals(nombreBuscado)) {
                    librosDeAutor.add(listaLibros.indexOf(lib)) ;

                    //System.out.println("Encontrado! Indice en la lista: " + listaLibros.indexOf(lib));
                    //System.out.println(lib);
                } else {
                    //System.out.println("No Encontrado!");
                }
            }
        }
        return librosDeAutor;
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
            alumno.setCarrera(teclado.nextLine());

            listaAlumnos.add(alumno);
            System.out.print("¿Desea ingresar otro alumno? (s/n): ");
            opcion = teclado.nextLine();
        } while (opcion.equalsIgnoreCase("s"));
    }

    private static void listarAlumnos(ArrayList<alumno> listaAlumnos) {

        if (listaAlumnos.size() == 0) {

            System.out.println("No hay alumnos cargados en la lista!");

        } else {
            System.out.println("Cantidad de alumnos de la lista : " + listaAlumnos.size());
            // Mostrar todos los alumnos ingresados
            System.out.println("\n--- Lista de alumnos ingresados ---");
            for (alumno alu : listaAlumnos) {
                System.out.println("Indice en la lista: " + listaAlumnos.indexOf(alu));
                System.out.println(alu);
            }
        }
    }

    private static void cargarBibliotecarios() {
        String opcion;
        do {
            bibliotecario bib = new bibliotecario();

            System.out.println("Ingrese nombre del bibliotecario: ");
            bib.setNombre(teclado.nextLine());
            System.out.print("Ingrese apellido del bibliotecario: ");
            bib.setApellido(teclado.nextLine());
            System.out.print("Ingrese DNI (número): ");
            bib.setDni(teclado.nextInt());
            teclado.nextLine(); // limpiar buffer
            System.out.print("Ingrese email: ");
            bib.setEmail(teclado.nextLine());
            System.out.print("Ingrese Número de Empleado: ");
            bib.setNroEmpleado(teclado.nextInt());
            teclado.nextLine(); // limpiar buffer
            System.out.print("Ingrese turno ( M / T / N ): ");
            bib.setTurno(teclado.nextLine().charAt(0));

            listaBibliotecarios.add(bib);
            System.out.print("¿Desea ingresar otro bibliotecario? (s/n): ");
            opcion = teclado.nextLine();
        } while (opcion.equalsIgnoreCase("s"));
    }

    private static void listarBibliotecarios(ArrayList<bibliotecario> listaBibliotecarios) {

        if (listaBibliotecarios.size() == 0) {

            System.out.println("No hay bibliotecarios cargados en la lista!");

        } else {
            System.out.println("Cantidad de bibliotecarios de la lista : " + listaBibliotecarios.size());
            // Mostrar todos los bibliotecarios ingresados
            System.out.println("\n--- Lista de bibliotecarios ingresados ---");
            for (bibliotecario bib : listaBibliotecarios) {
                System.out.println("Indice en la lista: " + listaBibliotecarios.indexOf(bib));
                System.out.println(bib);
            }
        }
    }

    @SuppressWarnings("unchecked")
    private static void cargarDatosDesdeLosArchivos() {

        // En esta parte realizo la carga de ARCHIVO_LIBROS a la listaLibros
        try (ObjectInputStream ois = new ObjectInputStream(new FileInputStream(ARCHIVO_LIBROS))) {
            listaLibros = (ArrayList<libros>) ois.readObject();
            System.out.println("Datos de libros cargados desde " + ARCHIVO_LIBROS);
        } catch (IOException | ClassNotFoundException e) {
            System.err.println("Error al cargar libros: " + e.getMessage());
        }

        // En esta parte realizo la carga de ARCHIVO_ALUMNOS a la listaAlumnos
        try (ObjectInputStream ois = new ObjectInputStream(new FileInputStream(ARCHIVO_ALUMNOS))) {
            listaAlumnos = (ArrayList<alumno>) ois.readObject();
            System.out.println("Datos de alumnos cargados desde " + ARCHIVO_ALUMNOS);
        } catch (IOException | ClassNotFoundException e) {
            System.err.println("Error al cargar alumnos: " + e.getMessage());
        }

        // En esta parte realizo la carga de ARCHIVO_BIBLIOTECARIOS a la
        // listaBibliotecarios
        try (ObjectInputStream ois = new ObjectInputStream(new FileInputStream(ARCHIVO_BIBLIOTECARIOS))) {
            listaBibliotecarios = (ArrayList<bibliotecario>) ois.readObject();
            System.out.println("Datos de bibliotecarios cargados desde " + ARCHIVO_BIBLIOTECARIOS);
        } catch (IOException | ClassNotFoundException e) {
            System.err.println("Error al cargar bibliotecarios: " + e.getMessage());
        }

        // En esta parte realizo la carga de ARCHIVO_PRESTAMOSS a la
        // listaPrestamos
        try (ObjectInputStream ois = new ObjectInputStream(new FileInputStream(ARCHIVO_PRESTAMOS))) {
            listaPrestamos = (ArrayList<prestamo>) ois.readObject();
            System.out.println("Datos de prestamos cargados desde " + ARCHIVO_PRESTAMOS);
        } catch (IOException | ClassNotFoundException e) {
            System.err.println("Error al cargar prestamos: " + e.getMessage());
        }
    }

    private static void guardarDatosALosArchivos() {
        try (ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream(ARCHIVO_LIBROS))) {
            oos.writeObject(listaLibros);
            System.out.println("Datos de libros guardados en " + ARCHIVO_LIBROS);
            // ------ Antes de seguir actualizo el hash ya que puede diferir del leido
            // originalmente
            hash_listaLibros = listaLibros.hashCode();
        } catch (IOException e) {
            System.err.println("Error al guardar libros: " + e.getMessage());
        }

        try (ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream(ARCHIVO_ALUMNOS))) {
            oos.writeObject(listaAlumnos);
            System.out.println("Datos de alumnos guardados en " + ARCHIVO_ALUMNOS);
        } catch (IOException e) {
            System.err.println("Error al guardar alumnos: " + e.getMessage());
        }

        try (ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream(ARCHIVO_BIBLIOTECARIOS))) {
            oos.writeObject(listaBibliotecarios);
            System.out.println("Datos de bibliotecarios guardados en " + ARCHIVO_BIBLIOTECARIOS);
        } catch (IOException e) {
            System.err.println("Error al guardar bibliotecarios: " + e.getMessage());
        }

        try (ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream(ARCHIVO_PRESTAMOS))) {
            oos.writeObject(listaPrestamos);
            System.out.println("Datos de prestamos guardados en " + ARCHIVO_PRESTAMOS);
        } catch (IOException e) {
            System.err.println("Error al guardar prestamos: " + e.getMessage());
        }
        
    }

    private static void prestarLibro() {

        listarBibliotecarios(listaBibliotecarios);
        System.out.println("Ingrese nro bibliotecario (indice)");
        int indiceBiblio = Integer.parseInt(teclado.nextLine());

        listarLibros(listaLibros);
        System.out.println("Ingrese nro libro a prestar (indice)");
        int indiceLibro = Integer.parseInt(teclado.nextLine());

        listarAlumnos(listaAlumnos);
        System.out.println("Ingrese nro alumno que retira (indice)");
        int indiceAlumno = Integer.parseInt(teclado.nextLine());

        prestamo prestamoLibro = new prestamo(listaBibliotecarios.get(indiceBiblio), listaLibros.get(indiceLibro),
                listaAlumnos.get(indiceAlumno), new Date(), null);
        listaPrestamos.add(prestamoLibro);

    }

    private static void recuperarLibro() {

        listarPrestamosSinDevolucion(listaPrestamos);
        System.out.println("Ingrese nro prestamo (indice) del prestamo que se esta devolviendo");
        int indicePrestamo = Integer.parseInt(teclado.nextLine());

        listaPrestamos.get(indicePrestamo).setFechaDevolucion(new Date());
        System.out.println("Listo! Quedó registrada la devolución");
        }

    private static void listarPrestamos(ArrayList<prestamo> listaPrestamos) {

        if (listaPrestamos.size() == 0) {

            System.out.println("No hay libros prestados en la lista!");

        } else {
            System.out.println("Cantidad de prestamos registrados en la lista : " + listaPrestamos.size());
            // Mostrar todos los prestamos realizados
            System.out.println("\n--- Lista de prestamos realizados ---");
            for (prestamo pre : listaPrestamos) {
                System.out.println("Indice en la lista: " + listaPrestamos.indexOf(pre));
                //System.out.println(pre);
                System.out.println("Bibliotecario que prestó : " + pre.getBibliotecario().getNombre() + ", " + pre.getBibliotecario().getApellido());
                System.out.println("Alumno que recibió : " + pre.getAlumno().getNombre() + ", " + pre.getAlumno().getApellido());
                System.out.println("Libro : " + pre.getLibro().getNombre() + ", autor: " + pre.getLibro().getAutor());
            }
        }
    }

    private static void listarPrestamosSinDevolucion(ArrayList<prestamo> listaPrestamos) {

        if (listaPrestamos.size() == 0) {
            System.out.println("No hay libros prestados en la lista!");
        } else {
            System.out.println("Cantidad de prestamos registrados en la lista pendientes de devolver: " + listaPrestamos.size());
            // Mostrar todos los prestamos realizados
            System.out.println("\n--- Lista de prestamos realizados pendientes de devolucion ---");
            for (prestamo pre : listaPrestamos) {
                if (pre.getFechaDevolucion() == null) {
                System.out.println("-- Indice en la lista: " + listaPrestamos.indexOf(pre));
                //System.out.println(pre);
                System.out.println("Bibliotecario que prestó : " + pre.getBibliotecario().getNombre() + ", " + pre.getBibliotecario().getApellido());
                System.out.println("Alumno que recibió : " + pre.getAlumno().getNombre() + ", " + pre.getAlumno().getApellido());
                System.out.println("Libro : " + pre.getLibro().getNombre() + ", autor: " + pre.getLibro().getAutor());
                System.out.println("Fecha de prestamo : " + pre.getFechaPrestamo());
                }
            }
        }
    }

    private static void revisarAntesDeSalir(int hash_anterior_lib, int hash_anterior_alu, int hash_anterior_biblio, int hash_anterior_pres) {

        int hash_listaLibros_actual = listaLibros.hashCode();
        int hash_listaAlumnos_actual = listaAlumnos.hashCode();
        int hash_listaBibliotecarios_actual = listaBibliotecarios.hashCode();
        int hash_listaPrestamos_actual = listaPrestamos.hashCode();
        if (hash_anterior_lib != hash_listaLibros_actual || hash_anterior_alu != hash_listaAlumnos_actual || hash_anterior_biblio != hash_listaBibliotecarios_actual || hash_anterior_pres != hash_listaPrestamos_actual) {
            System.out.println("Seguro quiere salir? Las listas actuales no fueron guardadas a archivo");

            String opcion_si_no;
            System.out.print("¿Desea guardar los datos? (s/n): ");
            opcion_si_no = teclado.nextLine();
            if (opcion_si_no.equals("s")) {
                guardarDatosALosArchivos();
            }
        }

    }

}