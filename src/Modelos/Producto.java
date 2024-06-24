package Modelos;

public class Producto {
    private int id_producto;
    private String nombre;
    private int cantidad;
    private byte[] imagen;
    private double precio;

    public Producto(int id_producto, String nombre, int cantidad, byte[] imagen, double precio) {
        this.id_producto = id_producto;
        this.nombre = nombre;
        this.cantidad = cantidad;
        this.imagen = imagen;
        this.precio = precio;
    }
    public Producto(int id_producto, String nombre, int cantidad, double precio) {
        this.id_producto = id_producto;
        this.nombre = nombre;
        this.cantidad = cantidad;
   
        this.precio = precio;
    }
    // Getters y setters
    public int getId_producto() {
        return id_producto;
    }

    public void setId_producto(int id_producto) {
        this.id_producto = id_producto;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public int getCantidad() {
        return cantidad;
    }

    public void setCantidad(int cantidad) {
        this.cantidad = cantidad;
    }

    public byte[] getImagen() {
        return imagen;
    }

    public void setImagen(byte[] imagen) {
        this.imagen = imagen;
    }

    public double getPrecio() {
        return precio;
    }

    public void setPrecio(double precio) {
        this.precio = precio;
    }

    @Override
    public String toString() {
        return "Producto [id_producto=" + id_producto + ", nombre=" + nombre + ", cantidad=" + cantidad + ", precio=" + precio
                + "]";
    }
}
