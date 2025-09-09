package com.example.vista;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import com.example.modelo.alumnoVO;
import com.example.modelo.bibliotecarioVO;
import com.example.modelo.libroVO;
import com.example.modelo.prestamoVO;
import java.util.ArrayList;
import java.util.Date;

public class nuevoMenu extends JFrame {
    private ArrayList<libroVO> listaLibros = new ArrayList<>();
    private ArrayList<alumnoVO> listaAlumnos = new ArrayList<>();
    private ArrayList<bibliotecarioVO> listaBibliotecarios = new ArrayList<>();
    private ArrayList<prestamoVO> listaPrestamos = new ArrayList<>();

    public nuevoMenu() {
        setTitle("Sistema de Biblioteca");
        setSize(800, 600);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);
        
        cargarDatosDesdeLosArchivos();
        crearMenuPrincipal();
    }

    private void crearMenuPrincipal() {
        JPanel panel = new JPanel(new GridLayout(0, 1, 10, 10));
        panel.setBorder(BorderFactory.createEmptyBorder(20, 20, 20, 20));

        // Botones del menú principal
        JButton btnCargarLibros = new JButton("1. Cargar Libros");
        JButton btnListarLibros = new JButton("2. Listar Libros");
        JButton btnCargarAlumnos = new JButton("5. Cargar Alumnos");
        JButton btnListarAlumnos = new JButton("6. Listar Alumnos");
        JButton btnCargarBibliotecarios = new JButton("7. Cargar Bibliotecarios");
        JButton btnListarBibliotecarios = new JButton("8. Listar Bibliotecarios");
        JButton btnGuardarDatos = new JButton("10. Guardar Datos");
        JButton btnPrestarLibro = new JButton("11. Prestar Libro");
        JButton btnListarPrestamos = new JButton("12. Listar Préstamos");
        JButton btnBuscarLibro = new JButton("14. Buscar Libro");
        JButton btnSalir = new JButton("99. Salir");

        // Agregar acciones a los botones
        btnCargarLibros.addActionListener(e -> mostrarFormularioLibros());
        btnListarLibros.addActionListener(e -> listarLibros());
        //btnCargarAlumnos.addActionListener(e -> mostrarFormularioAlumnos());
        //btnListarAlumnos.addActionListener(e -> listarAlumnos());
        //btnCargarBibliotecarios.addActionListener(e -> mostrarFormularioBibliotecarios());
        //btnListarBibliotecarios.addActionListener(e -> listarBibliotecarios());
        btnGuardarDatos.addActionListener(e -> guardarDatosALosArchivos());
        //btnPrestarLibro.addActionListener(e -> prestarLibro());
        //btnListarPrestamos.addActionListener(e -> listarPrestamos());
        btnBuscarLibro.addActionListener(e -> buscarLibro());
        btnSalir.addActionListener(e -> salir());

        // Agregar botones al panel
        panel.add(btnCargarLibros);
        panel.add(btnListarLibros);
        panel.add(btnCargarAlumnos);
        panel.add(btnListarAlumnos);
        panel.add(btnCargarBibliotecarios);
        panel.add(btnListarBibliotecarios);
        panel.add(btnGuardarDatos);
        panel.add(btnPrestarLibro);
        panel.add(btnListarPrestamos);
        panel.add(btnBuscarLibro);
        panel.add(btnSalir);

        add(panel);
    }

    private void mostrarFormularioLibros() {
        JDialog dialog = new JDialog(this, "Cargar Libros", true);
        dialog.setSize(500, 400);
        dialog.setLayout(new GridLayout(0, 2, 10, 10));
        dialog.setLocationRelativeTo(this);

        JTextField txtNombre = new JTextField();
        JTextField txtAutor = new JTextField();
        JTextField txtEditorial = new JTextField();
        JTextField txtIsbn = new JTextField();
        JTextField txtGenero = new JTextField();
        JCheckBox chkDiscontinuo = new JCheckBox("Discontinuado");

        dialog.add(new JLabel("Nombre:"));
        dialog.add(txtNombre);
        dialog.add(new JLabel("Autor:"));
        dialog.add(txtAutor);
        dialog.add(new JLabel("Editorial:"));
        dialog.add(txtEditorial);
        dialog.add(new JLabel("ISBN:"));
        dialog.add(txtIsbn);
        dialog.add(new JLabel("Género:"));
        dialog.add(txtGenero);
        dialog.add(new JLabel());
        dialog.add(chkDiscontinuo);

        JButton btnGuardar = new JButton("Guardar");
        btnGuardar.addActionListener(e -> {
            libroVO libro = new libroVO();
            libro.setNombre(txtNombre.getText());
            libro.setAutor(txtAutor.getText());
            libro.setEditorial(txtEditorial.getText());
            libro.setIsbn(Integer.parseInt(txtIsbn.getText()));
            libro.setGenero(txtGenero.getText());
            libro.setDiscontinuo(chkDiscontinuo.isSelected());
            
            listaLibros.add(libro);
            JOptionPane.showMessageDialog(dialog, "Libro guardado con éxito!");
            dialog.dispose();
        });

        JButton btnCancelar = new JButton("Cancelar");
        btnCancelar.addActionListener(e -> dialog.dispose());

        JPanel panelBotones = new JPanel();
        panelBotones.add(btnGuardar);
        panelBotones.add(btnCancelar);
        
        dialog.add(new JLabel());
        dialog.add(panelBotones);
        
        dialog.setVisible(true);
    }

    private void listarLibros() {
        if (listaLibros.isEmpty()) {
            JOptionPane.showMessageDialog(this, "No hay libros cargados en la lista!");
            return;
        }

        DefaultListModel<String> model = new DefaultListModel<>();
        for (libroVO libro : listaLibros) {
            model.addElement(libro.getNombre() + " - " + libro.getAutor());
        }

        JList<String> list = new JList<>(model);
        JScrollPane scrollPane = new JScrollPane(list);
        
        JDialog dialog = new JDialog(this, "Listado de Libros", true);
        dialog.setSize(500, 400);
        dialog.add(scrollPane);
        dialog.setLocationRelativeTo(this);
        dialog.setVisible(true);
    }

    private void buscarLibro() {
        String nombreBuscado = JOptionPane.showInputDialog(this, "Ingrese el nombre del libro a buscar:");
        if (nombreBuscado == null || nombreBuscado.trim().isEmpty()) return;

        StringBuilder resultado = new StringBuilder();
        for (libroVO libro : listaLibros) {
            if (libro.getNombre().equalsIgnoreCase(nombreBuscado)) {
                resultado.append("Libro encontrado:\n");
                resultado.append(libro.toString()).append("\n\n");
            }
        }

        if (resultado.length() == 0) {
            resultado.append("No se encontró el libro: ").append(nombreBuscado);
        }

        JOptionPane.showMessageDialog(this, resultado.toString(), "Resultado de búsqueda", JOptionPane.INFORMATION_MESSAGE);
    }

    // Implementaciones similares para los otros métodos (mostrarFormularioAlumnos, listarAlumnos, etc.)
    // ...

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            nuevoMenu mainSwing = new nuevoMenu();
            mainSwing.setVisible(true);
        });
    }

    // Mantén los métodos originales para manejo de archivos y lógica de negocio
    private void cargarDatosDesdeLosArchivos() {
        // Implementación igual a la original
    }

    private void guardarDatosALosArchivos() {
        // Implementación igual a la original
    }

    private void salir() {
        // Implementar lógica para revisar cambios antes de salir
        int confirmacion = JOptionPane.showConfirmDialog(
            this, 
            "¿Está seguro que desea salir?", 
            "Confirmar salida", 
            JOptionPane.YES_NO_OPTION
        );
        
        if (confirmacion == JOptionPane.YES_OPTION) {
            System.exit(0);
        }
    }
}