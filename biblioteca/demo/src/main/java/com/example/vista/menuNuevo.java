package com.example.vista;

import com.example.modelo.alumno;
import com.example.modelo.bibliotecario;
import com.example.modelo.libros;
import com.example.modelo.prestamo;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.io.*;
import java.util.ArrayList;
import java.util.Date;

public class menuNuevo extends JFrame {

    private final ArrayList<libros> listaLibros = new ArrayList<>();
    private final ArrayList<alumno> listaAlumnos = new ArrayList<>();
    private final ArrayList<bibliotecario> listaBibliotecarios = new ArrayList<>();
    private final ArrayList<prestamo> listaPrestamos = new ArrayList<>();

    private static final String RUTA_ARCHIVOS = "C:\\java\\biblioteca\\";
    private static final String ARCHIVO_LIBROS = RUTA_ARCHIVOS + "libros.dat";
    private static final String ARCHIVO_ALUMNOS = RUTA_ARCHIVOS + "alumnos.dat";
    private static final String ARCHIVO_BIBLIOTECARIOS = RUTA_ARCHIVOS + "bibliotecarios.dat";
    private static final String ARCHIVO_PRESTAMOS = RUTA_ARCHIVOS + "prestamos.dat";

    // Hash para control de cambios
    private int hashLibros, hashAlumnos, hashBibliotecarios, hashPrestamos;

    public menuNuevo() {
        setTitle("Sistema de Biblioteca");
        setSize(650, 400);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(DO_NOTHING_ON_CLOSE);
        agregarMenu();
        cargarDatosDesdeLosArchivos();
        actualizarHashes();

        addWindowListener(new WindowAdapter() {
            @Override
            public void windowClosing(WindowEvent e) {
                confirmarSalida();
            }
        });
    }

    private void agregarMenu() {
        JMenuBar menuBar = new JMenuBar();

        JMenu menuArchivo = new JMenu("Archivo");
        JMenuItem guardarItem = new JMenuItem("Guardar datos");
        JMenuItem cargarItem = new JMenuItem("Cargar datos");
        guardarItem.addActionListener(e -> {
            guardarDatosALosArchivos();
            actualizarHashes();
        });
        cargarItem.addActionListener(e -> {
            cargarDatosDesdeLosArchivos();
            actualizarHashes();
            JOptionPane.showMessageDialog(this, "Datos cargados.");
        });
        menuArchivo.add(guardarItem);
        menuArchivo.add(cargarItem);

        JMenu menuLibros = new JMenu("Libros");
        JMenuItem cargarLibro = new JMenuItem("Cargar Libro");
        JMenuItem listarLibros = new JMenuItem("Listar Libros");
        JMenuItem buscarLibro = new JMenuItem("Buscar Libro por Nombre");
        cargarLibro.addActionListener(e -> mostrarFormularioCargarLibro());
        listarLibros.addActionListener(e -> mostrarListaLibros());
        buscarLibro.addActionListener(e -> buscarLibroDialog());
        menuLibros.add(cargarLibro);
        menuLibros.add(listarLibros);
        menuLibros.add(buscarLibro);

        JMenu menuAlumnos = new JMenu("Alumnos");
        JMenuItem cargarAlumno = new JMenuItem("Cargar Alumno");
        JMenuItem listarAlumnos = new JMenuItem("Listar Alumnos");
        cargarAlumno.addActionListener(e -> mostrarFormularioCargarAlumno());
        listarAlumnos.addActionListener(e -> mostrarListaAlumnos());
        menuAlumnos.add(cargarAlumno);
        menuAlumnos.add(listarAlumnos);

        JMenu menuBibliotecarios = new JMenu("Bibliotecarios");
        JMenuItem cargarBiblio = new JMenuItem("Cargar Bibliotecario");
        JMenuItem listarBiblio = new JMenuItem("Listar Bibliotecarios");
        cargarBiblio.addActionListener(e -> mostrarFormularioCargarBibliotecario());
        listarBiblio.addActionListener(e -> mostrarListaBibliotecarios());
        menuBibliotecarios.add(cargarBiblio);
        menuBibliotecarios.add(listarBiblio);

        JMenu menuPrestamos = new JMenu("Préstamos");
        JMenuItem registrarPrestamo = new JMenuItem("Registrar Préstamo");
        JMenuItem listarPrestamos = new JMenuItem("Listar Préstamos");
        registrarPrestamo.addActionListener(e -> registrarPrestamo());
        listarPrestamos.addActionListener(e -> mostrarListaPrestamos());
        menuPrestamos.add(registrarPrestamo);
        menuPrestamos.add(listarPrestamos);

        menuBar.add(menuArchivo);
        menuBar.add(menuLibros);
        menuBar.add(menuAlumnos);
        menuBar.add(menuBibliotecarios);
        menuBar.add(menuPrestamos);

        setJMenuBar(menuBar);
    }

    private void actualizarHashes() {
        hashLibros = listaLibros.hashCode();
        hashAlumnos = listaAlumnos.hashCode();
        hashBibliotecarios = listaBibliotecarios.hashCode();
        hashPrestamos = listaPrestamos.hashCode();
    }

    private boolean hayCambiosNoGuardados() {
        return hashLibros != listaLibros.hashCode() ||
               hashAlumnos != listaAlumnos.hashCode() ||
               hashBibliotecarios != listaBibliotecarios.hashCode() ||
               hashPrestamos != listaPrestamos.hashCode();
    }

    private void confirmarSalida() {
        if (hayCambiosNoGuardados()) {
            int option = JOptionPane.showConfirmDialog(this,
                "Hay datos modificados que no se guardaron. ¿Desea guardar antes de salir?",
                "Confirmar salida", JOptionPane.YES_NO_CANCEL_OPTION);

            if (option == JOptionPane.YES_OPTION) {
                guardarDatosALosArchivos();
                System.exit(0);
            } else if (option == JOptionPane.NO_OPTION) {
                System.exit(0);
            } // cancelar = no hacer nada
        } else {
            System.exit(0);
        }
    }

    // ==== Formulario cargar libro ====

    private void mostrarFormularioCargarLibro() {
        JDialog dialog = new JDialog(this, "Cargar Libro", true);
        dialog.setLayout(new GridLayout(7, 2, 5, 5));
        dialog.setSize(400, 300);
        dialog.setLocationRelativeTo(this);

        JTextField txtNombre = new JTextField();
        JTextField txtAutor = new JTextField();
        JTextField txtEditorial = new JTextField();
        JTextField txtISBN = new JTextField();
        JTextField txtGenero = new JTextField();
        JCheckBox chkDiscontinuo = new JCheckBox("Discontinuado");

        dialog.add(new JLabel("Nombre:"));
        dialog.add(txtNombre);
        dialog.add(new JLabel("Autor:"));
        dialog.add(txtAutor);
        dialog.add(new JLabel("Editorial:"));
        dialog.add(txtEditorial);
        dialog.add(new JLabel("ISBN (número):"));
        dialog.add(txtISBN);
        dialog.add(new JLabel("Género:"));
        dialog.add(txtGenero);
        dialog.add(new JLabel("¿Discontinuado?"));
        dialog.add(chkDiscontinuo);

        JButton btnGuardar = new JButton("Guardar");
        JButton btnCancelar = new JButton("Cancelar");

        btnGuardar.addActionListener(e -> {
            try {
                String nombre = txtNombre.getText().trim();
                String autor = txtAutor.getText().trim();
                String editorial = txtEditorial.getText().trim();
                int isbn = Integer.parseInt(txtISBN.getText().trim());
                String genero = txtGenero.getText().trim();
                boolean discontinuo = chkDiscontinuo.isSelected();

                if (nombre.isEmpty() || autor.isEmpty()) {
                    JOptionPane.showMessageDialog(dialog, "Nombre y Autor son obligatorios.");
                    return;
                }

                libros libro = new libros();
                libro.setNombre(nombre);
                libro.setAutor(autor);
                libro.setEditorial(editorial);
                libro.setIsbn(isbn);
                libro.setGenero(genero);
                libro.setDiscontinuo(discontinuo);

                listaLibros.add(libro);
                actualizarHashes();
                JOptionPane.showMessageDialog(dialog, "Libro agregado con éxito.");
                dialog.dispose();
            } catch (NumberFormatException ex) {
                JOptionPane.showMessageDialog(dialog, "ISBN debe ser un número válido.");
            }
        });

        btnCancelar.addActionListener(e -> dialog.dispose());

        dialog.add(btnGuardar);
        dialog.add(btnCancelar);

        dialog.setVisible(true);
    }

    // ==== Listar libros ====

    private void mostrarListaLibros() {
        if (listaLibros.isEmpty()) {
            JOptionPane.showMessageDialog(this, "No hay libros cargados.");
            return;
        }
        JTextArea area = new JTextArea(20, 50);
        area.setEditable(false);
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < listaLibros.size(); i++) {
            libros lib = listaLibros.get(i);
            sb.append(i).append(": ")
              .append(lib.getNombre()).append(", Autor: ").append(lib.getAutor())
              .append(", ISBN: ").append(lib.getIsbn())
              .append(", Editorial: ").append(lib.getEditorial())
              .append(", Género: ").append(lib.getGenero())
              .append(", Discontinuo: ").append(lib.isDiscontinuo() ? "Sí" : "No")
              .append("\n");
        }
        area.setText(sb.toString());
        JOptionPane.showMessageDialog(this, new JScrollPane(area), "Lista de Libros", JOptionPane.INFORMATION_MESSAGE);
    }

    // ==== Buscar libro ====

    private void buscarLibroDialog() {
        String nombreBuscado = JOptionPane.showInputDialog(this, "Ingrese nombre del libro a buscar:");
        if (nombreBuscado == null || nombreBuscado.trim().isEmpty()) return;

        for (libros lib : listaLibros) {
            if (lib.getNombre().equalsIgnoreCase(nombreBuscado.trim())) {
                String info = "Nombre: " + lib.getNombre() +
                        "\nAutor: " + lib.getAutor() +
                        "\nEditorial: " + lib.getEditorial() +
                        "\nISBN: " + lib.getIsbn() +
                        "\nGénero: " + lib.getGenero() +
                        "\nDiscontinuado: " + (lib.isDiscontinuo() ? "Sí" : "No");
                JOptionPane.showMessageDialog(this, info, "Libro encontrado", JOptionPane.INFORMATION_MESSAGE);
                return;
            }
        }
        JOptionPane.showMessageDialog(this, "No se encontró el libro.", "Buscar Libro", JOptionPane.WARNING_MESSAGE);
    }

    // ==== Formulario cargar alumno ====

    private void mostrarFormularioCargarAlumno() {
        JDialog dialog = new JDialog(this, "Cargar Alumno", true);
        dialog.setLayout(new GridLayout(7, 2, 5, 5));
        dialog.setSize(400, 300);
        dialog.setLocationRelativeTo(this);

        JTextField txtNombre = new JTextField();
        JTextField txtApellido = new JTextField();
        JTextField txtDni = new JTextField();
        JTextField txtEmail = new JTextField();
        JTextField txtLegajo = new JTextField();
        JTextField txtTurno = new JTextField();
        JTextField txtCarrera = new JTextField();

        dialog.add(new JLabel("Nombre:"));
        dialog.add(txtNombre);
        dialog.add(new JLabel("Apellido:"));
        dialog.add(txtApellido);
        dialog.add(new JLabel("DNI (número):"));
        dialog.add(txtDni);
        dialog.add(new JLabel("Email:"));
        dialog.add(txtEmail);
        dialog.add(new JLabel("Legajo (número):"));
        dialog.add(txtLegajo);
        dialog.add(new JLabel("Turno (M/T/N):"));
        dialog.add(txtTurno);
        dialog.add(new JLabel("Carrera:"));
        dialog.add(txtCarrera);

        JButton btnGuardar = new JButton("Guardar");
        JButton btnCancelar = new JButton("Cancelar");

        btnGuardar.addActionListener(e -> {
            try {
                String nombre = txtNombre.getText().trim();
                String apellido = txtApellido.getText().trim();
                int dni = Integer.parseInt(txtDni.getText().trim());
                String email = txtEmail.getText().trim();
                int legajo = Integer.parseInt(txtLegajo.getText().trim());
                char turno = txtTurno.getText().trim().toUpperCase().charAt(0);
                String carrera = txtCarrera.getText().trim();

                if (nombre.isEmpty() || apellido.isEmpty()) {
                    JOptionPane.showMessageDialog(dialog, "Nombre y Apellido son obligatorios.");
                    return;
                }

                alumno alu = new alumno();
                alu.setNombre(nombre);
                alu.setApellido(apellido);
                alu.setDni(dni);
                alu.setEmail(email);
                alu.setNroLegajo(legajo);
                alu.setTurno(turno);
                alu.setCarrera(carrera);

                listaAlumnos.add(alu);
                actualizarHashes();
                JOptionPane.showMessageDialog(dialog, "Alumno agregado con éxito.");
                dialog.dispose();
            } catch (Exception ex) {
                JOptionPane.showMessageDialog(dialog, "Revise los datos ingresados. " + ex.getMessage());
            }
        });

        btnCancelar.addActionListener(e -> dialog.dispose());

        dialog.add(btnGuardar);
        dialog.add(btnCancelar);

        dialog.setVisible(true);
    }

    // ==== Listar alumnos ====

    private void mostrarListaAlumnos() {
        if (listaAlumnos.isEmpty()) {
            JOptionPane.showMessageDialog(this, "No hay alumnos cargados.");
            return;
        }
        JTextArea area = new JTextArea(20, 50);
        area.setEditable(false);
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < listaAlumnos.size(); i++) {
            alumno alu = listaAlumnos.get(i);
            sb.append(i).append(": ")
              .append(alu.getNombre()).append(" ").append(alu.getApellido())
              .append(", DNI: ").append(alu.getDni())
              .append(", Email: ").append(alu.getEmail())
              .append(", Legajo: ").append(alu.getNroLegajo())
              .append(", Turno: ").append(alu.getTurno())
              .append(", Carrera: ").append(alu.getCarrera())
              .append("\n");
        }
        area.setText(sb.toString());
        JOptionPane.showMessageDialog(this, new JScrollPane(area), "Lista de Alumnos", JOptionPane.INFORMATION_MESSAGE);
    }

    // ==== Formulario cargar bibliotecario ====

    private void mostrarFormularioCargarBibliotecario() {
        JDialog dialog = new JDialog(this, "Cargar Bibliotecario", true);
        dialog.setLayout(new GridLayout(7, 2, 5, 5));
        dialog.setSize(400, 300);
        dialog.setLocationRelativeTo(this);

        JTextField txtNombre = new JTextField();
        JTextField txtApellido = new JTextField();
        JTextField txtDni = new JTextField();
        JTextField txtEmail = new JTextField();
        JTextField txtNroEmpleado = new JTextField();
        JTextField txtTurno = new JTextField();

        dialog.add(new JLabel("Nombre:"));
        dialog.add(txtNombre);
        dialog.add(new JLabel("Apellido:"));
        dialog.add(txtApellido);
        dialog.add(new JLabel("DNI (número):"));
        dialog.add(txtDni);
        dialog.add(new JLabel("Email:"));
        dialog.add(txtEmail);
        dialog.add(new JLabel("Nro Empleado (número):"));
        dialog.add(txtNroEmpleado);
        dialog.add(new JLabel("Turno (M/T/N):"));
        dialog.add(txtTurno);

        JButton btnGuardar = new JButton("Guardar");
        JButton btnCancelar = new JButton("Cancelar");

        btnGuardar.addActionListener(e -> {
            try {
                String nombre = txtNombre.getText().trim();
                String apellido = txtApellido.getText().trim();
                int dni = Integer.parseInt(txtDni.getText().trim());
                String email = txtEmail.getText().trim();
                int nroEmpleado = Integer.parseInt(txtNroEmpleado.getText().trim());
                char turno = txtTurno.getText().trim().toUpperCase().charAt(0);

                if (nombre.isEmpty() || apellido.isEmpty()) {
                    JOptionPane.showMessageDialog(dialog, "Nombre y Apellido son obligatorios.");
                    return;
                }

                bibliotecario bib = new bibliotecario();
                bib.setNombre(nombre);
                bib.setApellido(apellido);
                bib.setDni(dni);
                bib.setEmail(email);
                bib.setNroEmpleado(nroEmpleado);
                bib.setTurno(turno);

                listaBibliotecarios.add(bib);
                actualizarHashes();
                JOptionPane.showMessageDialog(dialog, "Bibliotecario agregado con éxito.");
                dialog.dispose();
            } catch (Exception ex) {
                JOptionPane.showMessageDialog(dialog, "Revise los datos ingresados. " + ex.getMessage());
            }
        });

        btnCancelar.addActionListener(e -> dialog.dispose());

        dialog.add(btnGuardar);
        dialog.add(btnCancelar);

        dialog.setVisible(true);
    }

    // ==== Listar bibliotecarios ====

    private void mostrarListaBibliotecarios() {
        if (listaBibliotecarios.isEmpty()) {
            JOptionPane.showMessageDialog(this, "No hay bibliotecarios cargados.");
            return;
        }
        JTextArea area = new JTextArea(20, 50);
        area.setEditable(false);
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < listaBibliotecarios.size(); i++) {
            bibliotecario bib = listaBibliotecarios.get(i);
            sb.append(i).append(": ")
              .append(bib.getNombre()).append(" ").append(bib.getApellido())
              .append(", DNI: ").append(bib.getDni())
              .append(", Email: ").append(bib.getEmail())
              .append(", NroEmpleado: ").append(bib.getNroEmpleado())
              .append(", Turno: ").append(bib.getTurno())
              .append("\n");
        }
        area.setText(sb.toString());
        JOptionPane.showMessageDialog(this, new JScrollPane(area), "Lista de Bibliotecarios", JOptionPane.INFORMATION_MESSAGE);
    }

    // ==== Registrar préstamo ====

    private void registrarPrestamo() {
        if (listaBibliotecarios.isEmpty() || listaLibros.isEmpty() || listaAlumnos.isEmpty()) {
            JOptionPane.showMessageDialog(this, "Debe haber al menos un bibliotecario, un libro y un alumno cargados.");
            return;
        }
        JDialog dialog = new JDialog(this, "Registrar Préstamo", true);
        dialog.setLayout(new GridLayout(5, 2, 5, 5));
        dialog.setSize(400, 250);
        dialog.setLocationRelativeTo(this);

        JComboBox<String> comboBiblio = new JComboBox<>();
        for (bibliotecario b : listaBibliotecarios) {
            comboBiblio.addItem(b.getNombre() + " " + b.getApellido());
        }

        JComboBox<String> comboLibro = new JComboBox<>();
        for (libros l : listaLibros) {
            comboLibro.addItem(l.getNombre());
        }

        JComboBox<String> comboAlumno = new JComboBox<>();
        for (alumno a : listaAlumnos) {
            comboAlumno.addItem(a.getNombre() + " " + a.getApellido());
        }

        JTextField txtFechaPrestamo = new JTextField(new Date().toString());
        txtFechaPrestamo.setEditable(false);

        dialog.add(new JLabel("Bibliotecario:"));
        dialog.add(comboBiblio);
        dialog.add(new JLabel("Libro:"));
        dialog.add(comboLibro);
        dialog.add(new JLabel("Alumno:"));
        dialog.add(comboAlumno);
        dialog.add(new JLabel("Fecha Préstamo:"));
        dialog.add(txtFechaPrestamo);

        JButton btnGuardar = new JButton("Guardar");
        JButton btnCancelar = new JButton("Cancelar");

        btnGuardar.addActionListener(e -> {
            int idxBiblio = comboBiblio.getSelectedIndex();
            int idxLibro = comboLibro.getSelectedIndex();
            int idxAlumno = comboAlumno.getSelectedIndex();

            if (idxBiblio < 0 || idxLibro < 0 || idxAlumno < 0) {
                JOptionPane.showMessageDialog(dialog, "Debe seleccionar todos los campos.");
                return;
            }
            prestamo p = new prestamo();
            p.setBibliotecario(listaBibliotecarios.get(idxBiblio));
            p.setLibro(listaLibros.get(idxLibro));
            p.setAlumno(listaAlumnos.get(idxAlumno));
            p.setFechaPrestamo(new Date());

            listaPrestamos.add(p);
            actualizarHashes();
            JOptionPane.showMessageDialog(dialog, "Préstamo registrado correctamente.");
            dialog.dispose();
        });

        btnCancelar.addActionListener(e -> dialog.dispose());

        dialog.add(btnGuardar);
        dialog.add(btnCancelar);

        dialog.setVisible(true);
    }

    // ==== Listar préstamos ====

    private void mostrarListaPrestamos() {
        if (listaPrestamos.isEmpty()) {
            JOptionPane.showMessageDialog(this, "No hay préstamos registrados.");
            return;
        }
        JTextArea area = new JTextArea(20, 60);
        area.setEditable(false);
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < listaPrestamos.size(); i++) {
            prestamo p = listaPrestamos.get(i);
            sb.append(i).append(": Libro: ").append(p.getLibro().getNombre())
              .append(", Alumno: ").append(p.getAlumno().getNombre()).append(" ").append(p.getAlumno().getApellido())
              .append(", Bibliotecario: ").append(p.getBibliotecario().getNombre()).append(" ").append(p.getBibliotecario().getApellido())
              .append(", Fecha: ").append(p.getFechaPrestamo())
              .append("\n");
        }
        area.setText(sb.toString());
        JOptionPane.showMessageDialog(this, new JScrollPane(area), "Lista de Préstamos", JOptionPane.INFORMATION_MESSAGE);
    }

    // ==== Guardar y cargar ====

    private void guardarDatosALosArchivos() {
        crearCarpetaSiNoExiste();

        try (ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream(ARCHIVO_LIBROS))) {
            oos.writeObject(listaLibros);
        } catch (IOException e) {
            JOptionPane.showMessageDialog(this, "Error guardando libros: " + e.getMessage());
        }
        try (ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream(ARCHIVO_ALUMNOS))) {
            oos.writeObject(listaAlumnos);
        } catch (IOException e) {
            JOptionPane.showMessageDialog(this, "Error guardando alumnos: " + e.getMessage());
        }
        try (ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream(ARCHIVO_BIBLIOTECARIOS))) {
            oos.writeObject(listaBibliotecarios);
        } catch (IOException e) {
            JOptionPane.showMessageDialog(this, "Error guardando bibliotecarios: " + e.getMessage());
        }
        try (ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream(ARCHIVO_PRESTAMOS))) {
            oos.writeObject(listaPrestamos);
        } catch (IOException e) {
            JOptionPane.showMessageDialog(this, "Error guardando préstamos: " + e.getMessage());
        }
        JOptionPane.showMessageDialog(this, "Datos guardados correctamente.");
    }

    @SuppressWarnings("unchecked")
    private void cargarDatosDesdeLosArchivos() {
        try (ObjectInputStream ois = new ObjectInputStream(new FileInputStream(ARCHIVO_LIBROS))) {
            listaLibros.clear();
            listaLibros.addAll((ArrayList<libros>) ois.readObject());
        } catch (IOException | ClassNotFoundException e) {
            // Puede no existir archivo la primera vez, no mostrar error
        }
        try (ObjectInputStream ois = new ObjectInputStream(new FileInputStream(ARCHIVO_ALUMNOS))) {
            listaAlumnos.clear();
            listaAlumnos.addAll((ArrayList<alumno>) ois.readObject());
        } catch (IOException | ClassNotFoundException e) {
        }
        try (ObjectInputStream ois = new ObjectInputStream(new FileInputStream(ARCHIVO_BIBLIOTECARIOS))) {
            listaBibliotecarios.clear();
            listaBibliotecarios.addAll((ArrayList<bibliotecario>) ois.readObject());
        } catch (IOException | ClassNotFoundException e) {
        }
        try (ObjectInputStream ois = new ObjectInputStream(new FileInputStream(ARCHIVO_PRESTAMOS))) {
            listaPrestamos.clear();
            listaPrestamos.addAll((ArrayList<prestamo>) ois.readObject());
        } catch (IOException | ClassNotFoundException e) {
        }
    }

    private void crearCarpetaSiNoExiste() {
        File carpeta = new File(RUTA_ARCHIVOS);
        if (!carpeta.exists()) {
            carpeta.mkdirs();
        }
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> new menuNuevo().setVisible(true));
    }
}
