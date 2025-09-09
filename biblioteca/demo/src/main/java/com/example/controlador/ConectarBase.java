package com.example.controlador;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

/*Conexcion con la base de datos y conexcion con el driver*/
public class ConectarBase {
    Connection conexion;
    
    public ConectarBase(){
        this.conexion = null;
    }
    
    public Connection conectar() throws InstantiationException,IllegalAccessException{
        try{
            Class.forName("com.mysql.cj.jdbc.Driver");
            String url = parametros.DRIVER + "://" + parametros.IP + ":"
                       + parametros.PUERTO + "/" + parametros.BASE;
            conexion = DriverManager.getConnection(url, parametros.USER, parametros.PASSWORD);
        System.out.println("Conexión establecida OK");
        }catch(ClassNotFoundException | SQLException e){
            System.out.println("Error en la conexión " + e);
            }
        return conexion;
    }
}
