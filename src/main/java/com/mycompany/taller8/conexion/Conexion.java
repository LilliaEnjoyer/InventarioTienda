package conexion;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class Conexion {
    public Connection getConexion() {
        Connection con = null;
        try {
            con = DriverManager.getConnection("jdbc:mysql://localhost:3306/tienda", "root", "");
            System.out.println("Conexion exitosa");
        } catch (SQLException e) {
            System.out.println("Error al conectar: " + e.getMessage());
        }
        return con;
    }
}
