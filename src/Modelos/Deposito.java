package Modelos;

public class Deposito {
	private int id_Deposito;
	private Producto producto;
	private int cantidad;
	private String direccion;
	
	public Deposito(int id_Deposito, Producto producto, int cantidad, String direccion) {
		super();
		this.id_Deposito = id_Deposito;
		this.producto = producto;
		this.cantidad = cantidad;
		this.direccion = direccion;
	}

	public int getId_Deposito() {
		return id_Deposito;
	}

	public void setId_Deposito(int id_Deposito) {
		this.id_Deposito = id_Deposito;
	}

	public Producto getProducto() {
		return producto;
	}

	public void setProducto(Producto producto) {
		this.producto = producto;
	}

	public int getCantidad() {
		return cantidad;
	}

	public void setCantidad(int cantidad) {
		this.cantidad = cantidad;
	}

	public String getDireccion() {
		return direccion;
	}

	public void setDireccion(String direccion) {
		this.direccion = direccion;
	}

	@Override
	public String toString() {
		return "Deposito [id_Deposito=" + id_Deposito + ", producto=" + producto + ", cantidad=" + cantidad
				+ ", direccion=" + direccion + "]";
	}
	
	
	
	
}
