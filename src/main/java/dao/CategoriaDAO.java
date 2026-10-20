package dao;

import conexion.Conexion;
import model.Categoria;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

public class CategoriaDAO {
    public List<Categoria> listar() {
        List<Categoria> lista = new ArrayList<>();
        Conexion conexion = new Conexion();
        
        try (Connection con = conexion.getConexion();
             PreparedStatement ps = con.prepareStatement("SELECT * FROM categoria");
             ResultSet rs = ps.executeQuery()) {
            
            while (rs.next()) {
                Categoria c = new Categoria();
                c.setIdCategoria(rs.getInt("id_categoria"));
                c.setNombre(rs.getString("nombre"));
                lista.add(c);
            }
        } catch (Exception e) {
            System.out.println("Error al listar categorías: " + e.getMessage());
        }
        return lista;
    }
}