/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package dao;

import Conexion.Conexion;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import model.Categoria;

/**
 *
 * @author samue
 */
public class Categoriadao {
    
    public List<Categoria> listar(){
    List<Categoria> categorias = new ArrayList<>();
    String sql = "SELECT * FROM categoria";

        try (Connection con = Conexion.getConexion();PreparedStatement ps = con.prepareStatement(sql);ResultSet rs = ps.executeQuery()) {

            while (rs.next()) {

                Categoria categoria = new Categoria(
                    rs.getInt("id_categoria"),
                    rs.getString("nombre")
                );

                categorias.add(categoria);
            }

        } catch (SQLException e) {
            System.out.println("Error al listar categorías: "
                    + e.getMessage());
        }

        return categorias;
    }
}

