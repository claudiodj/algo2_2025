package com.example.vista;

import javax.swing.*;

import com.example.controlador.alumnoDAO;
import com.example.controlador.libroDAO;
import com.example.modelo.alumnoVO;
import com.example.modelo.bibliotecarioVO;
import com.example.modelo.libroVO;
import com.example.modelo.prestamoVO;

import java.awt.*;
import java.util.ArrayList;
import java.util.Date;

public class menu {
    //private static ArrayList<libroVO> listaLibros = new ArrayList<>(); // VER combo en prestamos, aprox linea 532
    private static ArrayList<alumnoVO> listaAlumnos = new ArrayList<>();
    private static ArrayList<bibliotecarioVO> listaBibliotecarios = new ArrayList<>();
    private static ArrayList<prestamoVO> listaPrestamos = new ArrayList<>();

    public static void crearYMostrarGUI() {
        JFrame frameMenu = new JFrame("Sistema de la Biblioteca del ISFT 182");
        frameMenu.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frameMenu.setSize(1000, 800);
        frameMenu.setLocationRelativeTo(null);

        // Panel principal con BorderLayout
        JPanel panelPrincipal = new JPanel(new BorderLayout());
        
        // Barra de menú
        JMenuBar menuBar = new JMenuBar();
        
        // Menú Libros
        JMenu menuLibros = new JMenu("Libros");
        JMenuItem itemCargarLibros = new JMenuItem("Cargar Libros");
        JMenuItem itemListarLibros = new JMenuItem("Listar Libros");
        JMenuItem itemBorrarLibro = new JMenuItem("Borrar Libro");
        JMenuItem itemEditarLibro = new JMenuItem("Editar Libro");
        
        itemCargarLibros.addActionListener(e -> mostrarFormularioLibros());
        itemListarLibros.addActionListener(e -> listarLibrosEnVentana());
        itemBorrarLibro.addActionListener(e -> borrarLibro());
        itemEditarLibro.addActionListener(e -> editarLibroEnVentana());
        
        menuLibros.add(itemCargarLibros);
        menuLibros.add(itemListarLibros);
        menuLibros.add(itemBorrarLibro);
        menuLibros.add(itemEditarLibro);
        
        // Menú Alumnos
        JMenu menuAlumnos = new JMenu("Alumnos");
        JMenuItem itemCargarAlumnos = new JMenuItem("Cargar Alumnos");
        JMenuItem itemListarAlumnos = new JMenuItem("Listar Alumnos");
        JMenuItem itemBorrarAlumno = new JMenuItem("Borrar Alumno");
        JMenuItem itemEditarAlumno = new JMenuItem("Editar Alumno");
        
        itemCargarAlumnos.addActionListener(e -> mostrarFormularioAlumnos());
        itemListarAlumnos.addActionListener(e -> listarAlumnosEnVentana());
        //itemBorrarAlumno.addActionListener(e -> borrarAlumnoEnVentana());
        //itemEditarAlumno.addActionListener(e -> editarAlumnoEnVentana());
        
        menuAlumnos.add(itemCargarAlumnos);
        menuAlumnos.add(itemListarAlumnos);
        menuAlumnos.add(itemBorrarAlumno);
        menuAlumnos.add(itemEditarAlumno);
        
        // Menú Bibliotecarios
        JMenu menuBibliotecarios = new JMenu("Bibliotecarios");
        JMenuItem itemCargarBibliotecarios = new JMenuItem("Cargar Bibliotecarios");
        JMenuItem itemListarBibliotecarios = new JMenuItem("Listar Bibliotecarios");
        
        itemCargarBibliotecarios.addActionListener(e -> mostrarFormularioBibliotecarios());
        itemListarBibliotecarios.addActionListener(e -> listarBibliotecariosEnVentana());
        
        menuBibliotecarios.add(itemCargarBibliotecarios);
        menuBibliotecarios.add(itemListarBibliotecarios);
        
        // Menú Préstamos
        JMenu menuPrestamos = new JMenu("Préstamos");
        JMenuItem itemRegistrarPrestamo = new JMenuItem("Registrar Préstamo");
        JMenuItem itemListarPrestamos = new JMenuItem("Listar Préstamos");
        
        itemRegistrarPrestamo.addActionListener(e -> mostrarFormularioPrestamos());
        itemListarPrestamos.addActionListener(e -> listarPrestamosEnVentana());
        
        menuPrestamos.add(itemRegistrarPrestamo);
        menuPrestamos.add(itemListarPrestamos);
        
        // Menú Archivos
        JMenu menuArchivos = new JMenu("Acciones");
        //JMenuItem itemGuardarDatos = new JMenuItem("Guardar Datos");
        JMenuItem itemSalir = new JMenuItem("Salir");

        menuArchivos.add(itemSalir);

        itemSalir.addActionListener(e -> {
            // cerrar la ventana actual
            frameMenu.dispose();
    
            // abrir nuevamente el login
            login.main(null);;
        });



        // Agregar menús a la barra
        menuBar.add(menuLibros);
        menuBar.add(menuAlumnos);
        menuBar.add(menuBibliotecarios);
        menuBar.add(menuPrestamos);
        menuBar.add(menuArchivos);
        
        frameMenu.setJMenuBar(menuBar);
        
        // Panel de bienvenida
        JLabel lblBienvenida = new JLabel("Bienvenido al Sistema de Biblioteca", JLabel.CENTER);
        lblBienvenida.setFont(new Font("Arial", Font.BOLD, 24));
        panelPrincipal.add(lblBienvenida, BorderLayout.CENTER);
        
        frameMenu.add(panelPrincipal);
        frameMenu.setVisible(true);
    }

    // Métodos para mostrar formularios
    private static void mostrarFormularioLibros() {
        JFrame frameLibro = new JFrame("Cargar Libro");
        frameLibro.setSize(500, 650);
        frameLibro.setLocationRelativeTo(null);
        
        JPanel panel = new JPanel(new GridLayout(0, 2, 10, 10));
        
        JLabel lblNombre = new JLabel("Nombre:");
        JTextField txtNombre = new JTextField();
        
        JLabel lblAutor = new JLabel("Autor:");
        JTextField txtAutor = new JTextField();
        
        JLabel lblEditorial = new JLabel("Editorial:");
        JTextField txtEditorial = new JTextField();
        
        JLabel lblIsbn = new JLabel("ISBN:");
        JTextField txtIsbn = new JTextField();
        
        JLabel lblGenero = new JLabel("Género:");
        JTextField txtGenero = new JTextField();
        
        JLabel lblDiscontinuo = new JLabel("Discontinuado:");
        JCheckBox chkDiscontinuo = new JCheckBox();
        
        JButton btnGuardar = new JButton("Guardar");

        btnGuardar.addActionListener(e -> {

        String nombre = txtNombre.getText().trim();
        String autor = txtAutor.getText().trim();
        String editorial = txtEditorial.getText().trim();
        String isbnText = txtIsbn.getText().trim();
        String genero = txtGenero.getText().trim();

        // Expresiones regulares
        String regexTexto = "^[a-zA-ZáéíóúÁÉÍÓÚñÑüÜ\\s]+$";  // Solo letras y espacios
        String regexIsbn = "^\\d{4,6}$";           // ISBN de entre 4 y 6 dígitos
    
        // Validaciones
        if (!nombre.matches(regexTexto)) {
            JOptionPane.showMessageDialog(frameLibro, "El nombre solo puede contener letras y espacios");
            txtNombre.setBackground(Color.yellow);
            txtNombre.requestFocusInWindow();
            return;
        } else{
            txtNombre.setBackground(Color.white);    
        }

        if (!autor.matches(regexTexto)) {
            JOptionPane.showMessageDialog(frameLibro, "El autor solo puede contener letras y espacios");
            txtAutor.setBackground(Color.ORANGE);
            txtAutor.requestFocusInWindow();
            return;
        } else{
            txtAutor.setBackground(Color.white);
        }

        if (!editorial.matches(regexTexto)) {
            JOptionPane.showMessageDialog(frameLibro, "La editorial solo puede contener letras y espacios");
            return;
        }

        if (!isbnText.matches(regexIsbn)) {
            JOptionPane.showMessageDialog(frameLibro, "El ISBN debe ser numérico de entre 4 y 6 dígitos");
            return;
        }

        if (!genero.matches(regexTexto)) {
            JOptionPane.showMessageDialog(frameLibro, "El género solo puede contener letras y espacios");
            return;
        }

            // Si todo es válido, creo el objeto y guardo
        libroVO libro = new libroVO();
        libro.setNombre(nombre);
        libro.setAutor(autor);
        libro.setEditorial(editorial);
        libro.setIsbn(Integer.parseInt(isbnText));
        libro.setGenero(genero);
        libro.setDiscontinuo(chkDiscontinuo.isSelected());

            libroDAO libroDAO = new libroDAO();

            int idLibro = libroDAO.insertarLibro(libro);
            //listaLibros.add(libro);
            if (idLibro > 0) {
                JOptionPane.showMessageDialog(frameLibro, "Libro guardado exitosamente con id: " + idLibro);    
            } else {
                JOptionPane.showMessageDialog(frameLibro, "Libro NO guardado!");
            }
            
            frameLibro.dispose();
        });
        
        panel.add(lblNombre);
        panel.add(txtNombre);
        panel.add(lblAutor);
        panel.add(txtAutor);
        panel.add(lblEditorial);
        panel.add(txtEditorial);
        panel.add(lblIsbn);
        panel.add(txtIsbn);
        panel.add(lblGenero);
        panel.add(txtGenero);
        panel.add(lblDiscontinuo);
        panel.add(chkDiscontinuo);
        panel.add(new JLabel());
        panel.add(btnGuardar);
        
        frameLibro.add(new JScrollPane(panel));
        frameLibro.setVisible(true);
    }

    private static void borrarLibro(){
        //HAY QUE DESARROLLAR ESTE CODIGO
        System.out.println("Se pidio la acción de borrar un libro");


        JFrame frameBorrarLibro = new JFrame("Borrar Libro indicando Id");
        frameBorrarLibro.setSize(400, 250);
        frameBorrarLibro.setLocationRelativeTo(null);
                
        JPanel panel = new JPanel(new GridLayout(0, 2, 10, 10));
        
        JLabel lblIdABorrar = new JLabel("   Id a borrar:");
        JTextField txtIdABorrar = new JTextField();
        
        JButton btnBorrar = new JButton("Borrar");
        btnBorrar.addActionListener(e -> {
            libroVO libro = new libroVO();
            libro.setIdLibro(Integer.parseInt(txtIdABorrar.getText()));
            
            libroDAO libroDAO = new libroDAO();

            int borradoExitoso = libroDAO.borrarLibro(libro);
            
            if (borradoExitoso > 0) {
                JOptionPane.showMessageDialog(frameBorrarLibro, "Libro borrado exitosamente");    
            } else {
                JOptionPane.showMessageDialog(frameBorrarLibro, "Libro NO borrado!");
            }
            
            frameBorrarLibro.dispose();
        });
        
        panel.add(lblIdABorrar);
        panel.add(txtIdABorrar);
        panel.add(new JLabel());
        panel.add(btnBorrar);
        
        frameBorrarLibro.add(new JScrollPane(panel));
        frameBorrarLibro.setVisible(true);
    }

    private static void editarLibroEnVentana() {
        System.out.println("Accion de editar requerida");

        JFrame frameLibro = new JFrame("Editar Libro");
        frameLibro.setSize(500, 650);
        frameLibro.setLocationRelativeTo(null);
        
        JPanel panel = new JPanel(new GridLayout(0, 2, 10, 10));
        
        JLabel lblIdLibro = new JLabel("ID:");
        JTextField txtIdLibro = new JTextField();

        JLabel lblNombre = new JLabel("Nombre:");
        JTextField txtNombre = new JTextField();
        
        JLabel lblAutor = new JLabel("Autor:");
        JTextField txtAutor = new JTextField();
        
        JLabel lblEditorial = new JLabel("Editorial:");
        JTextField txtEditorial = new JTextField();
        
        JLabel lblIsbn = new JLabel("ISBN:");
        JTextField txtIsbn = new JTextField();
        
        JLabel lblGenero = new JLabel("Género:");
        JTextField txtGenero = new JTextField();
        
        JLabel lblDiscontinuo = new JLabel("Discontinuado:");
        JCheckBox chkDiscontinuo = new JCheckBox();
        
        JButton btnBuscar = new JButton("Buscar");
        btnBuscar.addActionListener(e -> {
                        
            ArrayList<libroVO> libros = new ArrayList<>();
            libroDAO libroDAO = new libroDAO();

            libros = libroDAO.leerLibros("idLibro = " + txtIdLibro.getText());

            if (libros.size() == 0) {
                JOptionPane.showMessageDialog(frameLibro, "No se encontro libro con id: " + txtIdLibro.getText());
            } else {
                JOptionPane.showMessageDialog(frameLibro, "Libro encontrado!");

                libroVO libEncontrado = new libroVO();

                libEncontrado.setNombre(libros.get(0).getNombre());
                libEncontrado.setAutor(libros.get(0).getAutor());
                libEncontrado.setEditorial(libros.get(0).getEditorial());
                libEncontrado.setGenero(libros.get(0).getGenero());
                libEncontrado.setIsbn(libros.get(0).getIsbn());
                libEncontrado.setDiscontinuo(libros.get(0).isDiscontinuo());

                txtNombre.setText(libEncontrado.getNombre());
                txtAutor.setText(libEncontrado.getAutor());
                txtEditorial.setText(libEncontrado.getEditorial());
                txtGenero.setText(libEncontrado.getGenero());
                txtIsbn.setText(libEncontrado.getIsbn()+"");
                if (libEncontrado.isDiscontinuo() == true) {
                    chkDiscontinuo.setEnabled(true);
                }
            }
            //frameLibro.dispose();
        });
        
        panel.add(lblIdLibro);
        panel.add(txtIdLibro);
        panel.add(lblNombre);
        panel.add(txtNombre);
        panel.add(lblAutor);
        panel.add(txtAutor);
        panel.add(lblEditorial);
        panel.add(txtEditorial);
        panel.add(lblIsbn);
        panel.add(txtIsbn);
        panel.add(lblGenero);
        panel.add(txtGenero);
        panel.add(lblDiscontinuo);
        panel.add(chkDiscontinuo);
        panel.add(new JLabel());
        panel.add(btnBuscar);
        
        frameLibro.add(new JScrollPane(panel));
        frameLibro.setVisible(true);

    }

    private static void listarLibrosEnVentana() {
        JFrame frameLista = new JFrame("Listado de Libros");
        frameLista.setSize(600, 400);
        frameLista.setLocationRelativeTo(null);
        
        libroDAO libroDAO = new libroDAO();

        String[] columnNames = {"Id", "Nombre", "Autor", "Editorial", "ISBN", "Género", "Discontinuo"};

        ArrayList<libroVO> listLib = new ArrayList<>();

        listLib = libroDAO.leerLibros(null);

        Object[][] dato = new Object[listLib.size()][7];
        
        for (int i = 0; i < listLib.size(); i++) {
            libroVO libro = listLib.get(i);
            dato[i][0] = libro.getIdLibro();
            dato[i][1] = libro.getNombre();
            dato[i][2] = libro.getAutor();
            dato[i][3] = libro.getEditorial();
            dato[i][4] = libro.getIsbn();
            dato[i][5] = libro.getGenero();
            dato[i][6] = libro.isDiscontinuo() ? "Sí" : "No";
        }
        
        JTable table = new JTable(dato, columnNames);
        JScrollPane scrollPane = new JScrollPane(table);
        
        frameLista.add(scrollPane);
        frameLista.setVisible(true);
    }

    // Implementar métodos similares para alumnos, bibliotecarios y préstamos
    private static void mostrarFormularioAlumnos() {
        // Similar a mostrarFormularioLibros pero con campos de alumno
        JFrame frameAlumno = new JFrame("Cargar Alumno");
        frameAlumno.setSize(500, 650);
        frameAlumno.setLocationRelativeTo(null);
        
        JPanel panel = new JPanel(new GridLayout(0, 2, 10, 10));
        
        JLabel lblNombre = new JLabel("Nombre:");
        JTextField txtNombre = new JTextField();
        
        JLabel lblApellido = new JLabel("Apellido:");
        JTextField txtApellido = new JTextField();
        
        JLabel lblDni = new JLabel("DNI:");
        JTextField txtDni = new JTextField();
        
        JLabel lblEmail = new JLabel("Email:");
        JTextField txtEmail = new JTextField();
        
        JLabel lblNroLegajo = new JLabel("Nro Legajo:");
        JTextField txtNroLegajo = new JTextField();

        JLabel lblTurno = new JLabel("Turno:\n");
        // Definicion de opciones únicas para TURNO: hacemos uso de RADIO BUTTON
        JRadioButton turnoManana;
        JRadioButton turnoTarde;
        JRadioButton turnoNoche;
        ButtonGroup grupoTurnos;

        // Opciones de Turno
        turnoManana = new JRadioButton("Mañana", true);
        turnoTarde = new JRadioButton("Tarde");
        turnoNoche = new JRadioButton("Noche");

        // Agrupar los botones de radio
        grupoTurnos = new ButtonGroup();
        grupoTurnos.add(turnoManana);
        grupoTurnos.add(turnoTarde);
        grupoTurnos.add(turnoNoche);

        JLabel lblCarrera = new JLabel("Carrera:");
        JTextField txtCarrera = new JTextField();

        JButton btnGuardar = new JButton("Guardar");
        btnGuardar.addActionListener(e -> {
            alumnoVO alumno = new alumnoVO();
            alumno.setNombre(txtNombre.getText());
            alumno.setApellido(txtApellido.getText());
            alumno.setDni(Integer.parseInt(txtDni.getText()));
            alumno.setEmail(txtEmail.getText());
            alumno.setNroLegajo(Integer.parseInt(txtNroLegajo.getText()));

            char turnoSeleccionado = 'M'; // Mañana, a menos que se seleccione otro 

            if (turnoTarde.isSelected()) {
                turnoSeleccionado = 'T';
                } else if (turnoNoche.isSelected()) {
                    turnoSeleccionado = 'N';
                    } 

            alumno.setTurno(turnoSeleccionado);
            alumno.setCarrera(txtCarrera.getText());
                        
            alumnoDAO alumnoDAO = new alumnoDAO();

            int idAlumno = alumnoDAO.insertarAlumno(alumno);
            
            if (idAlumno > 0) {
                JOptionPane.showMessageDialog(frameAlumno, "Alumno guardado exitosamente con id: " + idAlumno);    
            } else {
                JOptionPane.showMessageDialog(frameAlumno, "Alumno NO guardado!");
            }
            
            frameAlumno.dispose();
        });
        
        // ------------ AQUI se arma el orden en que aparecen los componentes en el panel del formulario:
        panel.add(lblNombre);
        panel.add(txtNombre);
        panel.add(lblApellido);
        panel.add(txtApellido);
        panel.add(lblDni);
        panel.add(txtDni);
        panel.add(lblEmail);
        panel.add(txtEmail);
        panel.add(lblNroLegajo);
        panel.add(txtNroLegajo);
        // Agrega los botones de radio al panel
        panel.add(lblTurno);
        panel.add(turnoManana);
        panel.add(turnoTarde);
        panel.add(turnoNoche);

        panel.add(lblCarrera);
        panel.add(txtCarrera);
        panel.add(new JLabel());
        panel.add(btnGuardar);
        
        frameAlumno.add(new JScrollPane(panel));
        frameAlumno.setVisible(true);
    }

    private static void listarAlumnosEnVentana() {
        // Similar a listarLibrosEnVentana pero con datos de alumnos
    }

    private static void mostrarFormularioBibliotecarios() {
        // Similar a mostrarFormularioLibros pero con campos de bibliotecario
    }

    private static void listarBibliotecariosEnVentana() {
        // Similar a listarLibrosEnVentana pero con datos de bibliotecarios
    }

    private static void mostrarFormularioPrestamos() {
        JFrame framePrestamo = new JFrame("Registrar Préstamo");
        framePrestamo.setSize(500, 300);
        framePrestamo.setLocationRelativeTo(null);
        
        JPanel panel = new JPanel(new GridLayout(0, 2, 10, 10));
        
        // Combo box para seleccionar bibliotecario
        JLabel lblBibliotecario = new JLabel("Bibliotecario:");
        JComboBox<String> cbBibliotecarios = new JComboBox<>();

        for (bibliotecarioVO b : listaBibliotecarios) {
            cbBibliotecarios.addItem(b.getNombre() + " " + b.getApellido());
        }
        
        // Combo box para seleccionar libro
        JLabel lblLibro = new JLabel("Libro:");
        JComboBox<String> cbLibros = new JComboBox<>();

        // Instancio el DAO
        libroDAO libDAO = new libroDAO();
        
        // Declaro un ArrayList<libroVO> y lo cargo el Arraylist listaLibros con leerLibros
        ArrayList<libroVO> listaLibros = libDAO.leerLibros(null);

        for (libroVO l : libDAO.leerLibros(null)) {
            cbLibros.addItem(l.getNombre());
        }
        
        // Combo box para seleccionar alumno
        JLabel lblAlumno = new JLabel("Alumno:");
        JComboBox<String> cbAlumnos = new JComboBox<>();
        for (alumnoVO a : listaAlumnos) {
            cbAlumnos.addItem(a.getNombre() + " " + a.getApellido());
        }
        
        JButton btnRegistrar = new JButton("Registrar Préstamo");
        btnRegistrar.addActionListener(e -> {
            int indiceBiblio = cbBibliotecarios.getSelectedIndex();
            int indiceLibro = cbLibros.getSelectedIndex();
            int indiceAlumno = cbAlumnos.getSelectedIndex();
            
            if (indiceBiblio >= 0 && indiceLibro >= 0 && indiceAlumno >= 0) {
                prestamoVO prestamoLibro = new prestamoVO(
                    listaBibliotecarios.get(indiceBiblio),
                    listaLibros.get(indiceLibro),
                    listaAlumnos.get(indiceAlumno),
                    new Date(),
                    null
                );
                listaPrestamos.add(prestamoLibro);
                JOptionPane.showMessageDialog(framePrestamo, "Préstamo registrado exitosamente!");
                framePrestamo.dispose();
            } else {
                JOptionPane.showMessageDialog(framePrestamo, "Debe seleccionar bibliotecario, libro y alumno", "Error", JOptionPane.ERROR_MESSAGE);
            }
        });
        
        panel.add(lblBibliotecario);
        panel.add(cbBibliotecarios);
        panel.add(lblLibro);
        panel.add(cbLibros);
        panel.add(lblAlumno);
        panel.add(cbAlumnos);
        panel.add(new JLabel());
        panel.add(btnRegistrar);
        
        framePrestamo.add(panel);
        framePrestamo.setVisible(true);
    }

    private static void listarPrestamosEnVentana() {
        JFrame frameLista = new JFrame("Listado de Préstamos");
        frameLista.setSize(800, 400);
        frameLista.setLocationRelativeTo(null);
        
        String[] columnNames = {"Bibliotecario", "Alumno", "Libro", "Fecha Préstamo", "Fecha Devolución"};
        Object[][] data = new Object[listaPrestamos.size()][5];
        
        for (int i = 0; i < listaPrestamos.size(); i++) {
            prestamoVO p = listaPrestamos.get(i);
            data[i][0] = p.getBibliotecario().getNombre() + " " + p.getBibliotecario().getApellido();
            data[i][1] = p.getAlumno().getNombre() + " " + p.getAlumno().getApellido();
            data[i][2] = p.getLibro().getNombre();
            data[i][3] = p.getFechaPrestamo();
            data[i][4] = p.getFechaDevolucion() != null ? p.getFechaDevolucion() : "Pendiente";
        }
        
        JTable table = new JTable(data, columnNames);
        JScrollPane scrollPane = new JScrollPane(table);
        
        frameLista.add(scrollPane);
        frameLista.setVisible(true);
    }

    public static void volverLogin() {
        System.out.println("volver");
        //frameMenu.setVisible(false);
        login.main(null);
    }

}