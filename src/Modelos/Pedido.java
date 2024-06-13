package Modelos;

import java.time.LocalDate;
import java.util.LinkedList;
import javax.swing.JOptionPane;


public class Pedido {
	private int id_pedido;
	private Cliente cliente;
	private LinkedList<String> Productos = new LinkedList<String>();
	private LocalDate fechaPedido;
	private double total;
	private boolean estado;
	private String nombre;
	private String cantidad;

	public Pedido(int id_Pedido, Cliente cliente, LinkedList<String> productos, LocalDate fechaPedido, double total,
			boolean estado, String cantidad, String nombre) {
		super();
		this.id_pedido = id_Pedido;
		this.cliente = cliente;
		Productos = productos;
		this.fechaPedido = fechaPedido;
		this.total = total;
		this.estado = estado;
		this.cantidad = cantidad;
		this.nombre = nombre;
	}

	public int getId_Pedido() {
		return id_pedido;
	}

	public void setId_Pedido(int id_Pedido) {
		this.id_pedido = id_Pedido;
	}

	public Cliente getCliente() {
		return cliente;
	}

	public void setCliente(Cliente cliente) {
		this.cliente = cliente;
	}

	public LinkedList<String> getProductos() {
		return Productos;
	}

	public void setProductos(LinkedList<String> productos) {
		Productos = productos;
	}

	public LocalDate getFechaPedido() {
		return fechaPedido;
	}

	public void setFechaPedido(LocalDate fechaPedido) {
		this.fechaPedido = fechaPedido;
	}

	public double getTotal() {
		return total;
	}

	public void setTotal(double total) {
		this.total = total;
	}

	public boolean isEstado() {
		return estado;
	}

	public void setEstado(boolean estado) {
		this.estado = estado;
	}
	

	public String getNombre() {
		return nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	public String getCantidad() {
		return cantidad;
	}

	public void setCantidad(String cantidad) {
		this.cantidad = cantidad;
	}

	@Override
	public String toString() {
		return "Pedido [id_pedido=" + id_pedido + ", cliente=" + cliente + ", Productos=" + Productos + ", fechaPedido="
				+ fechaPedido + ", total=" + total + ", estado=" + estado + "]";
	}
	
	public static Pedido generarPedido() {
		LinkedList<String> productos = new LinkedList<>();
        double total = 0.0;
        boolean continuar = true;
        Cliente cliente = new Cliente(0, null, null, null, 0);

        while (continuar) {
            String producto = JOptionPane.showInputDialog("Ingrese el nombre del producto:");
            String cantidadStr = JOptionPane.showInputDialog("Ingrese la cantidad para " + producto + ":");
            int cantidad = Integer.parseInt(cantidadStr);
            
            for (int i = 0; i < cantidad; i++) {
                productos.add(producto);
            }
            
            double precioPorProducto = 10.0; 
            total += cantidad * precioPorProducto;

            int continuarSeleccion = JOptionPane.showConfirmDialog(null, "¿Desea agregar otro producto?", "Generar Pedido",
                    JOptionPane.YES_NO_OPTION);
            if (continuarSeleccion != JOptionPane.YES_OPTION) {
                continuar = false;
            }
        }
        Pedido nuevoPedido = new Pedido(0, cliente, productos, LocalDate.now(), total, true); 
        JOptionPane.showMessageDialog(null, "Pedido generado: " + nuevoPedido.toString());
        return nuevoPedido;
	}
	
	public static void generarOrdenVentaMayorista(Pedido pedido, Cliente mayorista, LinkedList<String> productos, double total) {
		pedido.cliente = mayorista;
		pedido.Productos = productos;
		pedido.total = total;
		pedido.fechaPedido = LocalDate.now();
		pedido.estado = true; 
		JOptionPane.showMessageDialog(null, "Orden de venta para mayorista generada: " + pedido.toString());
	}

	public static String visualizarEstadoOrdenVenta(Pedido pedido) {
		return pedido.estado ? "Activa" : "Inactiva";
	}

	public static void registrarOrdenDevolucion(Pedido pedido, LinkedList<String> productosDevueltos) {
		for (String producto : productosDevueltos) {
			if (pedido.Productos.contains(producto)) {
				pedido.Productos.remove(producto);
			}
		}
		pedido.total -= calcularTotalDevolucion(productosDevueltos); 
		JOptionPane.showMessageDialog(null, "Orden de devolución registrada: " + pedido.toString());
	}

	private static double calcularTotalDevolucion(LinkedList<String> productosDevueltos) {
		double valorPorProducto = 10.0; 
		return productosDevueltos.size() * valorPorProducto;
	}

	public static void actualizarEstadoOrdenVenta(Pedido pedido, boolean nuevoEstado) {
		pedido.estado = nuevoEstado;
		JOptionPane.showMessageDialog(null, "Estado de la orden de venta actualizado: " + pedido.toString());
	}

	public static void actualizarEstadoOrdenDevolucion(Pedido pedido, boolean nuevoEstado) {
		pedido.estado = nuevoEstado;
		JOptionPane.showMessageDialog(null, "Estado de la orden de devolución actualizado: " + pedido.toString());
	}

	public static void generarOrdenReposicionProductos(Pedido pedido, LinkedList<String> productosReposicion) {
		pedido.Productos.addAll(productosReposicion);
		pedido.total += calcularTotalReposicion(productosReposicion); 
		JOptionPane.showMessageDialog(null, "Orden de reposición de productos generada: " + pedido.toString());
	}

	private static double calcularTotalReposicion(LinkedList<String> productosReposicion) {
		double valorPorProducto = 10.0; 
		return productosReposicion.size() * valorPorProducto;
	}


}
