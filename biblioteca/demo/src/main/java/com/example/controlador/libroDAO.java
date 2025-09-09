package com.example.controlador;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;

import com.example.modelo.libroVO;

// Aqui implementamos los métodos CRUD y/o todo lo necesario que relacione libroVO con las operaciones de la base
// CRUD: Create, crear, insertar; Read, leer; Update, actualizar; Delete, borrar
public class libroDAO {

    public int insertarLibro(libroVO nuevoLibro) {

        // Como es un nuevo libro el campo id_libro tiene que ser cero para que el
        // autoincremental de la base le asigne el correspondiente
        nuevoLibro.setIdLibro(0);

        ConectarBase conexion = new ConectarBase();
        //--------------------------------------1 2 3 4 5 6 7
        String sql = "insert into libros values(?,?,?,?,?,?,?)"; // Sentencia SQL para insertar un nuevo libro.

        PreparedStatement ps = null;

        try {
            ps = conexion.conectar().prepareStatement(sql, Statement.RETURN_GENERATED_KEYS); // Prepara la
            ps.setInt(1, nuevoLibro.getIdLibro());
            ps.setString(2, nuevoLibro.getNombre());
            ps.setString(3, nuevoLibro.getAutor());
            ps.setString(4, nuevoLibro.getEditorial());
            ps.setInt(5, nuevoLibro.getIsbn());
            ps.setString(6, nuevoLibro.getGenero());
            ps.setBoolean(7, nuevoLibro.isDiscontinuo());

            ps.executeUpdate();
            ResultSet rs = ps.getGeneratedKeys(); // Obtiene el ID generado.
            rs.next(); // Baja una fila del resultset: allí está el id asignado en la base de datos.
            nuevoLibro.setIdLibro(rs.getInt(1)); // Asigna el ID generado al nuevo libro.
        } catch (Exception ex) {
            System.out.println(ex.getMessage()); // Manejo de excepciones genéricas.
        } finally {
            try {
                ps.close();
            } catch (Exception ex) {
            }
        }
        return nuevoLibro.getIdLibro(); // Retorna el ID del nuevo libro o -1 si ocurre un error.
    }

    public int borrarLibro(libroVO libro) {
       
        int borradoExitoso = 0;

        ConectarBase conexion = new ConectarBase();

        String sql = "delete from libros where idLibro = ?"; // Sentencia SQL para insertar un nuevo libro.

        PreparedStatement ps = null;

        try {
            ps = conexion.conectar().prepareStatement(sql, Statement.RETURN_GENERATED_KEYS); // Prepara la
            ps.setInt(1, libro.getIdLibro());
            
            ps.executeUpdate();
            borradoExitoso = 1;
        } catch (Exception ex) {
            System.out.println(ex.getMessage()); // Manejo de excepciones genéricas.
        } finally {
            try {
                ps.close();
            } catch (Exception ex) {
            }
        }
        return borradoExitoso; // Retorna 0 si falló y 1 si borró exitosamente
    }

public int actualizarLibro(libroVO libro) {

    // Se procede a actualizar los atributos del id_libro recibido
        
    int filasActualizadas = 0; // False: no se actualizo

    ConectarBase conexion = new ConectarBase();
        //                                   1          2              3         4           5                6                 7
    String sql = "update libros set nombre = ?, autor = ?, editorial = ?, isbn = ?, genero = ?, discontinuo = ? where idLibro = ? "; // Sentencia SQL para actualizar los atributos del idLibro

    PreparedStatement ps = null;

    try {
            ps = conexion.conectar().prepareStatement(sql, Statement.RETURN_GENERATED_KEYS); // Prepara la
            ps.setString(1, libro.getNombre());
            ps.setString(2, libro.getAutor());
            ps.setString(3, libro.getEditorial());
            ps.setInt(4, libro.getIsbn());
            ps.setString(5, libro.getGenero());
            ps.setBoolean(6, libro.isDiscontinuo());
            ps.setInt(7, libro.getIdLibro());
            
            ps.executeUpdate();
            filasActualizadas = ps.getUpdateCount(); 

        } catch (Exception ex) {
            System.out.println(ex.getMessage()); // Manejo de excepciones genéricas.
        } finally {
            try {
                ps.close();
            } catch (Exception ex) {
            }
        }
        return filasActualizadas; // Retorna 0 si ocurre un error o 1 si actualizo OK.
    }

public ArrayList<libroVO> leerLibros(String filtros){

    ArrayList<libroVO> listaLibros = new ArrayList<>();

    ConectarBase conexion = new ConectarBase();

    String sql = "select * from libros "; // Sentencia SQL para leer todos los libros

    PreparedStatement ps = null;
    ResultSet rs = null;

    try {
        if (filtros == null || filtros.isBlank()) {
            sql = sql + " where 1 = 1";
        } else  {
            sql = sql + " where " + filtros;
        }

        ps = conexion.conectar().prepareStatement(sql);

        rs = ps.executeQuery();    
        while (rs.next()) {
            libroVO libro = new libroVO();
            libro.setIdLibro(rs.getInt(1));
            libro.setNombre(rs.getString(2));
            libro.setAutor(rs.getString(3));
            libro.setEditorial(rs.getString(4));
            libro.setIsbn(rs.getInt(5));
            libro.setGenero(rs.getString(6));
            libro.setDiscontinuo(rs.getBoolean(7));

            listaLibros.add(libro);
        }

        } catch (Exception ex) {
            System.out.println(ex.getMessage()); // Manejo de excepciones genéricas.
        } finally {
            try {
                ps.close();
                //rs.close();
            } catch (Exception ex) {
                ex.printStackTrace();
            }
        }
    return listaLibros;
}    

}
