package Modelos;
import java.util.Date;
import java.util.LinkedList;

public class Venta {

	private int id_venta;
	private Cliente cliente;
	private LinkedList<String> productos; 
	private Date fechaPedido;
	private double precio_final;
	
	public Venta(int id_venta, Cliente cliente, LinkedList<String> productos, Date fechaPedido, double precio_final) {
		super();
		this.id_venta = id_venta;
		this.cliente = cliente;
		this.productos = productos;
		this.fechaPedido = fechaPedido;
		this.precio_final = precio_final;
	}

	public int getId_venta() {
		return id_venta;
	}

	public void setId_venta(int id_venta) {
		this.id_venta = id_venta;
	}

	public Cliente getCliente() {
		return cliente;
	}

	public void setCliente(Cliente cliente) {
		this.cliente = cliente;
	}

	public LinkedList<String> getProductos() {
		return productos;
	}

	public void setProductos(LinkedList<String> productos) {
		this.productos = productos;
	}

	public Date getFechaPedido() {
		return fechaPedido;
	}

	public void setFechaPedido(Date fechaPedido) {
		this.fechaPedido = fechaPedido;
	}

	public double getPrecio_final() {
		return precio_final;
	}

	public void setPrecio_final(double precio_final) {
		this.precio_final = precio_final;
	}

	@Override
	public String toString() {
		return "Venta [id_venta=" + id_venta + ", cliente=" + cliente + ", productos=" + productos + ", fechaPedido="
				+ fechaPedido + ", precio_final=" + precio_final + "]";
	}
	
}