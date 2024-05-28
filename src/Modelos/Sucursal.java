package Modelos;

public class Sucursal {
	private int id_sucursal;
	private String nombre;
	private String direccion;
	private int telefono;

	public Sucursal(int id_sucursal, String nombre, String direccion, int telefono) {
		super();
		this.id_sucursal = id_sucursal;
		this.nombre = nombre;
		this.direccion = direccion;
		this.telefono = telefono;
	}

	public int getId_sucursal() {
		return id_sucursal;
	}

	public void setId_sucursal(int id_sucursal) {
		this.id_sucursal = id_sucursal;
	}

	public String getNombre() {
		return nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	public String getDireccion() {
		return direccion;
	}

	public void setDireccion(String direccion) {
		this.direccion = direccion;
	}

	public int getTelefono() {
		return telefono;
	}

	public void setTelefono(int telefono) {
		this.telefono = telefono;
	}

	@Override
	public String toString() {
		return "Sucursal [id_sucursal=" + id_sucursal + ", nombre=" + nombre + ", direccion=" + direccion
				+ ", telefono=" + telefono + "]";
	}

}
