/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package model;

/**
 *
 * @author samue
 */
public class Producto {
    private int idProducto;
    private String nombre;
    private double precio;
    private int cantidad;
    private Categoria categoria;


    public Producto() {
    }

    public Producto(int idProducto, String nombre,double precio, int cantidad,Categoria categoria) {

        this.idProducto = idProducto;
        this.nombre = nombre;
        setPrecio(precio);
        setCantidad(cantidad);
        this.categoria = categoria;
    }


    public int getIdProducto() {
        return idProducto;
    }

    public double getPrecio() {
        return precio;
    }

    public int getCantidad() {
        return cantidad;
    }

    public Categoria getCategoria() {
        return categoria;
    }

    public String getNombre() {
        return nombre;
    }
    

    public void setIdProducto(int idProducto) {
        this.idProducto = idProducto;
    }

    public void setPrecio(double precio) {
        if (precio > 0) {
            this.precio = precio;
        } else {
            throw new IllegalArgumentException(
                "El precio debe ser mayor que cero"
            );
        }
    }

    public void setCantidad(int cantidad) {
        if (cantidad >= 0) {
            this.cantidad = cantidad;
        } else {
            throw new IllegalArgumentException(
                "La cantidad no puede ser negativa"
            );
        }
    }

    public void setCategoria(Categoria categoria) {
        this.categoria = categoria;
    }
    
}
