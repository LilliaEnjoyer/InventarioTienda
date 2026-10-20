/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package dao;

/**
 *
 * @author samue
 */

import Conexion.Conexion;
import model.Producto;
import model.Categoria;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class ProductoDao {
    
    public boolean insertar(Producto p) {

        String sql = "INSERT INTO producto "+ "(nombre, precio, cantidad, id_categoria) "+ "VALUES (?, ?, ?, ?)";

        try (Connection con = Conexion.getConexion();
            PreparedStatement ps = con.prepareStatement(sql)) {
            ps.setString(1, p.getNombre());
            ps.setDouble(2, p.getPrecio());
            ps.setInt(3, p.getCantidad());
            ps.setInt(4, p.getCategoria().getIdCategoria());

        return ps.executeUpdate() > 0;

        } catch (SQLException e) {
            System.out.println("Error al insertar: " + e.getMessage());
            return false;
        }
    }
    
    public List<Producto> listar() {

    List<Producto> productos = new ArrayList<>();

    String sql = "SELECT p.*, c.nombre AS nombre_categoria "+ "FROM producto p "+ "JOIN categoria c "+ "ON p.id_categoria = c.id_categoria";

    try (Connection con = Conexion.getConexion();
         PreparedStatement ps = con.prepareStatement(sql);
         ResultSet rs = ps.executeQuery()) {

        while (rs.next()) {

            Categoria categoria = new Categoria(
                rs.getInt("id_categoria"),
                rs.getString("nombre_categoria")
            );

            Producto producto = new Producto(
                rs.getInt("id_producto"),
                rs.getString("nombre"),
                rs.getDouble("precio"),
                rs.getInt("cantidad"),
                categoria
            );

            productos.add(producto);
        }

    } catch (SQLException e) {
        System.out.println("Error al listar productos: "+ e.getMessage());
    }

    return productos;
    }
  
    public Producto buscarPorId(int id) {

    String sql = "SELECT p.*, c.nombre AS nombre_categoria "+ "FROM producto p "+ "JOIN categoria c "+ "ON p.id_categoria = c.id_categoria "+ "WHERE p.id_producto = ?";

    try (Connection con = Conexion.getConexion();
         PreparedStatement ps = con.prepareStatement(sql)) {

        ps.setInt(1, id);

        try (ResultSet rs = ps.executeQuery()) {

            if (rs.next()) {

                Categoria categoria = new Categoria(
                    rs.getInt("id_categoria"),
                    rs.getString("nombre_categoria")
                );

                return new Producto(
                    rs.getInt("id_producto"),
                    rs.getString("nombre"),
                    rs.getDouble("precio"),
                    rs.getInt("cantidad"),
                    categoria
                );
            }
        }

    } catch (SQLException e) {
        System.out.println("Error al buscar producto: "
                + e.getMessage());
    }

    return null;
    }
    
    public boolean actualizar(Producto p) {

        String sql = "UPDATE producto "+ "SET nombre = ?, precio = ?, cantidad = ?, "+ "id_categoria = ? "+ "WHERE id_producto = ?";

        try (Connection con = Conexion.getConexion();
            PreparedStatement ps = con.prepareStatement(sql)) {
            ps.setString(1, p.getNombre());
            ps.setDouble(2, p.getPrecio());
            ps.setInt(3, p.getCantidad());
            ps.setInt(4, p.getCategoria().getIdCategoria());
            ps.setInt(5, p.getIdProducto());
            return ps.executeUpdate() > 0;

        } catch (SQLException e) {
            System.out.println("Error al actualizar: "+ e.getMessage());
            return false;
        }
    }
    public boolean eliminar(int id) {

        String sql = "DELETE FROM producto "+"WHERE id_producto = ?";

        try (Connection con = Conexion.getConexion();
            PreparedStatement ps = con.prepareStatement(sql)) {
            ps.setInt(1, id);
            return ps.executeUpdate() > 0;
        } catch (SQLException e) {
            System.out.println("Error al eliminar: "+ e.getMessage());
            return false;
        }
    }
}
