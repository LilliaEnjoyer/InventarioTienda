/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 */

package github.com.lilliaenjoyer.programacioni.inventariotienda;

import Conexion.Conexion;
import java.sql.Connection;
import model.Categoria;
import model.Producto;

/**
 *
 * @author samue
 */
public class InventarioTienda {

    public static void main(String[] args) {
        Connection conexion = Conexion.getConexion();  
    
    if (conexion != null) {
        System.out.println("Conexión exitosa");

        try {
            conexion.close();
        } catch (java.sql.SQLException e) {
            System.out.println(e.getMessage());
        }
        
    } else {
        System.out.println("No se pudo conectar");
    }    
    
    }
}