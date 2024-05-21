package Modelos;
import java.time.LocalDate;

public class Devolucion {
	
	private int id_devolcion;
	private int id_pedido;
	private int id_cliente;
	private LocalDate fecha_devolucion;
	private String motivo;
	
	public Devolucion(int id_devolcion, int id_pedido, int id_cliente, LocalDate fecha_devolucion, String motivo) {
		super();
		this.id_devolcion = id_devolcion;
		this.id_pedido = id_pedido;
		this.id_cliente = id_cliente;
		this.fecha_devolucion = fecha_devolucion;
		this.motivo = motivo;
	}

	public int getId_devolcion() {
		return id_devolcion;
	}

	public void setId_devolcion(int id_devolcion) {
		this.id_devolcion = id_devolcion;
	}

	public int getId_pedido() {
		return id_pedido;
	}

	public void setId_pedido(int id_pedido) {
		this.id_pedido = id_pedido;
	}

	public int getId_cliente() {
		return id_cliente;
	}

	public void setId_cliente(int id_cliente) {
		this.id_cliente = id_cliente;
	}

	public LocalDate getFecha_devolucion() {
		return fecha_devolucion;
	}

	public void setFecha_devolucion(LocalDate fecha_devolucion) {
		this.fecha_devolucion = fecha_devolucion;
	}

	public String getMotivo() {
		return motivo;
	}

	public void setMotivo(String motivo) {
		this.motivo = motivo;
	}

	@Override
	public String toString() {
		return "Devolucion [id_devolcion=" + id_devolcion + ", id_pedido=" + id_pedido + ", id_cliente=" + id_cliente
				+ ", fecha_devolucion=" + fecha_devolucion + ", motivo=" + motivo + "]";
	}
	
	

}
