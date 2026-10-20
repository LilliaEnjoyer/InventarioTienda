package model;

public class Producto {
    private int idProducto;
    private String nombre;
    private double precio;
    private int cantidad;
    private Categoria categoria; 

    public Producto() {
    }

    public Producto(int idProducto, String nombre, double precio, int cantidad, Categoria categoria) {
        this.idProducto = idProducto;
        this.nombre = nombre;
        setPrecio(precio); // Usa el setter para aplicar la validación
        setCantidad(cantidad); // Usa el setter para aplicar la validación
        this.categoria = categoria;
    }

    public int getIdProducto() { return idProducto; }
    public void setIdProducto(int idProducto) { this.idProducto = idProducto; }

    public String getNombre() { return nombre; }
    public void setNombre(String nombre) { this.nombre = nombre; }

    public double getPrecio() { return precio; }
    public void setPrecio(double precio) {
        if (precio > 0) {
            this.precio = precio;
        } else {
            System.out.println("Error: El precio debe ser mayor que cero.");
        }
    }

    public int getCantidad() { return cantidad; }
    public void setCantidad(int cantidad) {
        if (cantidad >= 0) {
            this.cantidad = cantidad;
        } else {
            System.out.println("Error: La cantidad no puede ser negativa.");
        }
    }

    public Categoria getCategoria() { return categoria; }
    public void setCategoria(Categoria categoria) { this.categoria = categoria; }
}