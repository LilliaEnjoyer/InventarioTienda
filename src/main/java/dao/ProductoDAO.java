package dao;

import conexion.Conexion;
import model.Categoria;
import model.Producto;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

public class ProductoDAO {
    
    public boolean insertar(Producto p) {
        Conexion conexion = new Conexion();
        try (Connection con = conexion.getConexion();
             PreparedStatement ps = con.prepareStatement("INSERT INTO producto (nombre, precio, cantidad, id_categoria) VALUES (?, ?, ?, ?)")) {
            ps.setString(1, p.getNombre());
            ps.setDouble(2, p.getPrecio());
            ps.setInt(3, p.getCantidad());
            ps.setInt(4, p.getCategoria().getIdCategoria());
            ps.executeUpdate();
            return true;
        } catch (Exception e) {
            return false;
        }
    }

    public List<Producto> listar() {
        List<Producto> lista = new ArrayList<>();
        Conexion conexion = new Conexion();
        String sql = "SELECT p.id_producto, p.nombre, p.precio, p.cantidad, c.id_categoria, c.nombre AS nombre_categoria " +
                     "FROM producto p INNER JOIN categoria c ON p.id_categoria = c.id_categoria";
                     
        try (Connection con = conexion.getConexion();
             PreparedStatement ps = con.prepareStatement(sql);
             ResultSet rs = ps.executeQuery()) {
            
            while (rs.next()) {
                Categoria c = new Categoria(rs.getInt("id_categoria"), rs.getString("nombre_categoria"));
                Producto p = new Producto(rs.getInt("id_producto"), rs.getString("nombre"), 
                                          rs.getDouble("precio"), rs.getInt("cantidad"), c);
                lista.add(p);
            }
        } catch (Exception e) {
            System.out.println("Error al listar productos: " + e.getMessage());
        }
        return lista;
    }

    public boolean actualizar(Producto p) {
        Conexion conexion = new Conexion();
        try (Connection con = conexion.getConexion();
             PreparedStatement ps = con.prepareStatement("UPDATE producto SET nombre=?, precio=?, cantidad=?, id_categoria=? WHERE id_producto=?")) {
            ps.setString(1, p.getNombre());
            ps.setDouble(2, p.getPrecio());
            ps.setInt(3, p.getCantidad());
            ps.setInt(4, p.getCategoria().getIdCategoria());
            ps.setInt(5, p.getIdProducto());
            ps.executeUpdate();
            return true;
        } catch (Exception e) {
            return false;
        }
    }

    public boolean eliminar(int id) {
        Conexion conexion = new Conexion();
        try (Connection con = conexion.getConexion();
             PreparedStatement ps = con.prepareStatement("DELETE FROM producto WHERE id_producto=?")) {
            ps.setInt(1, id);
            ps.executeUpdate();
            return true;
        } catch (Exception e) {
            return false;
        }
    }
    
    public Producto buscarPorId(int id) {
        Conexion conexion = new Conexion();
        Producto p = null;
        String sql = "SELECT p.id_producto, p.nombre, p.precio, p.cantidad, c.id_categoria, c.nombre AS nombre_categoria " +
                     "FROM producto p INNER JOIN categoria c ON p.id_categoria = c.id_categoria WHERE p.id_producto=?";
                     
        try (Connection con = conexion.getConexion();
             PreparedStatement ps = con.prepareStatement(sql)) {
            ps.setInt(1, id);
            try (ResultSet rs = ps.executeQuery()) {
                if (rs.next()) {
                    Categoria c = new Categoria(rs.getInt("id_categoria"), rs.getString("nombre_categoria"));
                    p = new Producto(rs.getInt("id_producto"), rs.getString("nombre"), 
                                     rs.getDouble("precio"), rs.getInt("cantidad"), c);
                }
            }
        } catch (Exception e) {
             System.out.println("Error al buscar producto: " + e.getMessage());
        }
        return p;
    }
}