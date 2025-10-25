package com.example.vista;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JTextField;
import javax.swing.SwingUtilities;

import java.awt.*;

import com.example.controlador.usuarioDAO;
import com.example.modelo.usuarioVO;

public class login {

        public static void main(String[] args) {
        // Crear y mostrar la ventana principal
        SwingUtilities.invokeLater(() -> {
            crearYMostrarGUILogin();
        });
    }

    private static void crearYMostrarGUILogin() {
        JFrame frameLogin = new JFrame("LOGIN al Sistema de la Biblioteca del ISFT 182");
        frameLogin.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frameLogin.setSize(500, 400);
        frameLogin.setLocationRelativeTo(null);

        // Panel principal con BorderLayout
        JPanel panelPrincipal = new JPanel(new BorderLayout());
        
        // Panel de bienvenida
        JLabel lblBienvenida = new JLabel("Control de Acceso al Sistema de Biblioteca", JLabel.CENTER);
        lblBienvenida.setFont(new Font("Arial", Font.BOLD, 18));
        panelPrincipal.add(lblBienvenida, BorderLayout.SOUTH);

        JPanel panel = new JPanel(new GridLayout(0, 2, 20, 20));
        
        JLabel lblUsuario = new JLabel("Usuario:");
        lblUsuario.setHorizontalAlignment(0);
        JTextField txtUsuario = new JTextField();
        txtUsuario.setHorizontalAlignment(0);
        
        JLabel lblClave = new JLabel("Clave:");
        lblClave.setHorizontalAlignment(0);
        JPasswordField txtClave = new JPasswordField();
        txtClave.setHorizontalAlignment(0);
        
        JButton btnLogin = new JButton("LOGIN");

        btnLogin.addActionListener(e -> {
            //SE DEBE CONSTRUIR UN OBJETO TIPO usuarioVO con los datos de Usuario y Clave
            //SE DEBE INVOCAR AL dao usuarioDAO con el metodo de busqueda y 
            //si retorna TRUE cerrar el login abrir el sistema
            //si retorna FALSE mostrar un cartel que indique "Usuario o Clave inválidas"
            
            System.out.println("Btn Login");

            usuarioVO usuarioLogin = new usuarioVO();

            usuarioLogin.setUsuario(txtUsuario.getText());
            usuarioLogin.setClave(new String(txtClave.getPassword()));

            usuarioDAO usuDAO = new usuarioDAO();

            int existe = usuDAO.leerUsuario(usuarioLogin);

            if (existe == 0) {
                System.out.println("Login incorrecto!");
                JOptionPane.showMessageDialog(frameLogin, "Login incorrecto!","Verifique!",2);                
            } else {
                System.out.println("Login Correcto!");
                //Cerrar el login
                frameLogin.dispose();

                //Abrir el menú principal
                menu.crearYMostrarGUI();
            }

        });
        
        panel.add(lblUsuario);
        panel.add(txtUsuario);
        panel.add(lblClave);
        panel.add(txtClave);
        panel.add(new JLabel());
        panel.add(btnLogin);
        panelPrincipal.add(panel);

        frameLogin.add(panelPrincipal);
        frameLogin.setVisible(true);
        
    }

}
