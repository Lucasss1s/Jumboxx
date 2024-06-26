package Modelos;

public class Producto {
    private int id_producto;
    private String nombre;
    private int cantidad;
    private byte[] imagen;
    private double precio;
    private double precioConDescuento;
    private double porcentajeDescuento; // Nuevo campo para el porcentaje de descuento

    public Producto(int id_producto, String nombre, int cantidad, byte[] imagen, double precio) {
        this.id_producto = id_producto;
        this.nombre = nombre;
        this.cantidad = cantidad;
        this.imagen = imagen;
        this.precio = precio;
        this.precioConDescuento = precio; // Inicialmente igual al precio normal
        this.porcentajeDescuento = 0; // Inicialmente sin descuento
    }

    // Constructor alternativo sin imagen
    public Producto(int id_producto, String nombre, int cantidad, double precio) {
        this.id_producto = id_producto;
        this.nombre = nombre;
        this.cantidad = cantidad;
        this.precio = precio;
        this.precioConDescuento = precio; // Inicialmente igual al precio normal
        this.porcentajeDescuento = 0; // Inicialmente sin descuento
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

    public double getPrecioConDescuento() {
        return precioConDescuento;
    }

    public void setPrecioConDescuento(double precioConDescuento) {
        this.precioConDescuento = precioConDescuento;
    }

    public double getPorcentajeDescuento() {
        return porcentajeDescuento;
    }

    public void setPorcentajeDescuento(double porcentajeDescuento) {
        this.porcentajeDescuento = porcentajeDescuento;
    }

    @Override
    public String toString() {
        return "Producto [id_producto=" + id_producto + ", nombre=" + nombre + ", cantidad=" + cantidad + ", precio=" + precio
                + ", precioConDescuento=" + precioConDescuento + ", porcentajeDescuento=" + porcentajeDescuento + "]";
    }
}
