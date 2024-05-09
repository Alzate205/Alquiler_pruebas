package utils;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class ConexionBaseDatos {
    private static String url = "jdbc:mysql://localhost:3306/alquiler_vehiculos";
    private static String username = "root";
    private static String password = "admin";
    public static Connection getConnection() throws SQLException {
        return DriverManager.getConnection(url, username, password);
    }
}

