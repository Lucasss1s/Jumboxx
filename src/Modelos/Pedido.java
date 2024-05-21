package Modelos;
import java.time.LocalDate;
import java.util.LinkedList;


public class Pedido {
	private int id_pedido;
	private Cliente cliente;
	private LinkedList<String> Productos = new LinkedList<String>();
	private LocalDate fechaPedido;
	private double total;
	private boolean estado;
	
	public Pedido(int id_Pedido, Cliente cliente, LinkedList<String> productos, LocalDate fechaPedido, double total,
			boolean estado) {
		super();
		this.id_pedido = id_Pedido;
		this.cliente = cliente;
		Productos = productos;
		this.fechaPedido = fechaPedido;
		this.total = total;
		this.estado = estado;
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

	@Override
	public String toString() {
		return "Pedido [id_pedido=" + id_pedido + ", cliente=" + cliente + ", Productos=" + Productos + ", fechaPedido="
				+ fechaPedido + ", total=" + total + ", estado=" + estado + "]";
	}

	
	
	
	
}
