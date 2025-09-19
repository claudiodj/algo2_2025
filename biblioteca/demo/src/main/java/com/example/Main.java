package com.example;

import com.example.controlador.ConectarBase;
import com.example.controlador.libroDAO;
import com.example.modelo.alumnoVO;
import com.example.modelo.bibliotecarioVO;
import com.example.modelo.libroVO;
import com.example.modelo.personaVO;
import com.example.modelo.prestamoVO;

import java.util.ArrayList;
import java.util.Date;
import java.util.Scanner;

public class Main {
    
    public static void main(String[] args) {

    libroVO libro = new libroVO();

    libroDAO libroDAO = new libroDAO();

    /*
    libro.setAutor("Mabel Arteaga");
    libro.setNombre("La playa 2");
    libro.setGenero("Poema");
    libro.setIsbn(9883366);
    libro.setEditorial("Galerna");
    libro.setDiscontinuo(false);

    int valorId = libroDAO.insertarLibro(libro);


    if (valorId == 0) {
        System.out.println("El libro no se inserto");
    } else {
        System.out.println("El libro se inserto con el id: " + valorId);
    }

    libro.setIdLibro(valorId);

    libro.setGenero("Ensayo");
    libro.setEditorial("Sur");

    int valorActualizacion = libroDAO.actualizarLibro(libro);

    if (valorActualizacion == 0) {
        System.out.println("El libro no se ACTUALIZO");
    } else {
        System.out.println("El libro se actualizo correctamente");
    }


    int seBorro = libroDAO.borrarLibro(libro);

        if (seBorro == 0) {
        System.out.println("El libro no se borró");
    } else {
        System.out.println("El libro se borró ");
    }
        */

    ArrayList<libroVO> listaDeLibros = new ArrayList<>();
    
    listaDeLibros = libroDAO.leerLibros(" isbn <= 789798777 and editorial like '%erna%'");  //  " nombre like 'La%'"
    
    for (libroVO lib : listaDeLibros){
        System.out.println(lib.toString());
    }

    listaDeLibros = libroDAO.leerLibros("");  //  " nombre like 'La%'"
    
    for (libroVO lib : listaDeLibros){
        System.out.println(lib.toString());
    }

    }
    

}