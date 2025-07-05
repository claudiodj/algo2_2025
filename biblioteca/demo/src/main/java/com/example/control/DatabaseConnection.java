package com.example.control;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DatabaseConnection {
    public static Connection getConnection() throws SQLException {
        // Debemos armar una conexión como "jdbc:mysql://localhost:3306/club"
        String URL = parametros.DRIVER + "://" + parametros.IP + ":" + parametros.PUERTO + "/" + parametros.BASE;
        return DriverManager.getConnection(URL, parametros.USER, parametros.PASSWORD);
    }
}

