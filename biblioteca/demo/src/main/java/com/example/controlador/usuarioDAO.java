package com.example.controlador;

import java.sql.PreparedStatement;
import java.sql.ResultSet;

import com.example.modelo.usuarioVO;

public class usuarioDAO {

    public int leerUsuario(usuarioVO usuario){

        int existe = 0;
        // aqui debe implementarse el algoritmo de busqueda en la tabla usuarios y 
        // devolver id (mayor a cero) si se encuentra o 0 si no

        ConectarBase conexion = new ConectarBase();

        String sql = "select * from usuarios where usuario = ? and clave = ? "; 

        try {
        
            PreparedStatement ps = conexion.conectar().prepareStatement(sql);

            ps.setString(1, usuario.getUsuario());
            ps.setString(2, usuario.getClave());

            ResultSet rs = ps.executeQuery();    
            
            while (rs.next()) {
            
                existe = rs.getInt(1);
            }
            ps.close();

            } catch (Exception ex) {
                System.out.println(ex.getMessage()); // Manejo de excepciones genéricas.
            } finally {
                try {
                //ps.close();
                //rs.close();
                } catch (Exception ex) {
                    ex.printStackTrace();
            }
        }



        return existe;
        
    }
}
