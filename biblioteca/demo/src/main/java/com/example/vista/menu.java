package com.example.vista;

import javax.swing.*;

import com.example.controlador.libroDAO;
import com.example.modelo.alumnoVO;
import com.example.modelo.bibliotecarioVO;
import com.example.modelo.libroVO;
import com.example.modelo.prestamoVO;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.Date;

public class menu {
    private static ArrayList<libroVO> listaLibros = new ArrayList<>();
    private static ArrayList<alumnoVO> listaAlumnos = new ArrayList<>();
    private static ArrayList<bibliotecarioVO> listaBibliotecarios = new ArrayList<>();
    private static ArrayList<prestamoVO> listaPrestamos = new ArrayList<>();

    public static void main(String[] args) {
        // Crear y mostrar la ventana principal
        SwingUtilities.invokeLater(() -> {
            crearYMostrarGUI();
        });
    }

    private static void crearYMostrarGUI() {
        JFrame frame = new JFrame("Sistema de la Biblioteca del ISFT 182");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(1000, 800);
        frame.setLocationRelativeTo(null);

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
        
        itemCargarAlumnos.addActionListener(e -> mostrarFormularioAlumnos());
        itemListarAlumnos.addActionListener(e -> listarAlumnosEnVentana());
        
        menuAlumnos.add(itemCargarAlumnos);
        menuAlumnos.add(itemListarAlumnos);
        
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
        
        //itemGuardarDatos.addActionListener(e -> guardarDatosALosArchivos());
        itemSalir.addActionListener(e -> {
            revisarAntesDeSalir();
            frame.dispose();
        });
        
        //menuArchivos.add(itemGuardarDatos);
        //menuArchivos.addSeparator();
        menuArchivos.add(itemSalir);
        
        // Agregar menús a la barra
        menuBar.add(menuLibros);
        menuBar.add(menuAlumnos);
        menuBar.add(menuBibliotecarios);
        menuBar.add(menuPrestamos);
        menuBar.add(menuArchivos);
        
        frame.setJMenuBar(menuBar);
        
        // Panel de bienvenida
        JLabel lblBienvenida = new JLabel("Bienvenido al Sistema de Biblioteca", JLabel.CENTER);
        lblBienvenida.setFont(new Font("Arial", Font.BOLD, 24));
        panelPrincipal.add(lblBienvenida, BorderLayout.CENTER);
        
        frame.add(panelPrincipal);
        frame.setVisible(true);
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
            libroVO libro = new libroVO();
            libro.setNombre(txtNombre.getText());
            libro.setAutor(txtAutor.getText());
            libro.setEditorial(txtEditorial.getText());
            libro.setIsbn(Integer.parseInt(txtIsbn.getText()));
            libro.setGenero(txtGenero.getText());
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

            libros = libroDAO.leerLibros("id = " + txtIdLibro.getText());

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

            
            frameLibro.dispose();
        });
        
        panel.add(lblIdLibro);
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
        for (libroVO l : listaLibros) {
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

    private static void revisarAntesDeSalir() {
        // Implementar lógica para verificar cambios no guardados
        int opcion = JOptionPane.showConfirmDialog(null, 
            "¿Desea guardar los cambios antes de salir?", 
            "Confirmar salida", 
            JOptionPane.YES_NO_CANCEL_OPTION);
        
        if (opcion == JOptionPane.YES_OPTION) {
            //guardarDatosALosArchivos();
        } else if (opcion == JOptionPane.CANCEL_OPTION) {
            return; // No salir
        }
        
        System.exit(0);
    }
}