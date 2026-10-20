


package Conexion;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */

/**
 *
 * @author samue
 */
public class Conexion {
    private static String url = "jdbc:mysql://localhost:3306/tienda";
    private static String usuario = "root";
    private static String contrasenna = "";
    
    public static Connection getConexion() {
    
        try {
            return DriverManager.getConnection(url, usuario, contrasenna);
        } catch (SQLException e) {
            System.out.println("Error de conexión: " + e.getMessage());
            return null;
        }
    }
}
