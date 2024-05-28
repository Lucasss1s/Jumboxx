package Modelos;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class Producto {

	private int id_producto;
	private String nombre;
	private int cantidad;
	private double precio;

	public Producto(int id_producto, String nombre, int cantidad, double precio) {
		super();
		this.id_producto = id_producto;
		this.nombre = nombre;
		this.cantidad = cantidad;
		this.precio = precio;
	}

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

	public double getPrecio() {
		return precio;
	}

	public void setPrecio(double precio) {
		this.precio = precio;
	}

	@Override
	public String toString() {
		return "Producto [id_producto=" + id_producto + ", nombre=" + nombre + ", cantidad=" + cantidad + ", precio="
				+ precio + "]";
	}

	public static Connection getConnection() throws SQLException {
		String URL = "jdbc:mysql://localhost:3306/supermercado";
		String USER = "root";
		String PASSWORD = "";
		return DriverManager.getConnection(URL, USER, PASSWORD);
	}

	public static void agregarProducto(Producto producto) throws SQLException {
		String sql = "INSERT INTO productos (id_producto, nombre, cantidad, precio) VALUES (?, ?, ?, ?)";
		try (Connection conn = getConnection(); PreparedStatement statement = conn.prepareStatement(sql)) {
			statement.setInt(1, producto.getId_producto());
			statement.setString(2, producto.getNombre());
			statement.setInt(3, producto.getCantidad());
			statement.setDouble(4, producto.getPrecio());
			statement.executeUpdate();
		}
	}

	public static Producto obtenerProducto(int id) throws SQLException {
		String sql = "SELECT * FROM productos WHERE id_producto = ?";
		try (Connection conn = getConnection(); PreparedStatement statement = conn.prepareStatement(sql)) {
			statement.setInt(1, id);
			try (ResultSet resultSet = statement.executeQuery()) {
				if (resultSet.next()) {
					return new Producto(resultSet.getInt("id_producto"), resultSet.getString("nombre"),
							resultSet.getInt("cantidad"), resultSet.getDouble("precio"));
				}
			}
		}
		return null;
	}

	public static List<Producto> obtenerTodosLosProductos() throws SQLException {
		List<Producto> productos = new ArrayList<>();
		String sql = "SELECT * FROM productos";
		try (Connection conn = getConnection();
				PreparedStatement statement = conn.prepareStatement(sql);
				ResultSet resultSet = statement.executeQuery()) {
			while (resultSet.next()) {
				Producto producto = new Producto(resultSet.getInt("id_producto"), resultSet.getString("nombre"),
						resultSet.getInt("cantidad"), resultSet.getDouble("precio"));
				productos.add(producto);
			}
		}
		return productos;
	}

	public static void actualizarProducto(Producto producto) throws SQLException {
		String sql = "UPDATE productos SET nombre = ?, cantidad = ?, precio = ? WHERE id_producto = ?";
		try (Connection conn = getConnection(); PreparedStatement statement = conn.prepareStatement(sql)) {
			statement.setString(1, producto.getNombre());
			statement.setInt(2, producto.getCantidad());
			statement.setDouble(3, producto.getPrecio());
			statement.setInt(4, producto.getId_producto());
			statement.executeUpdate();
		}
	}

	public static void eliminarProducto(int id) throws SQLException {
		String sql = "DELETE FROM productos WHERE id_producto = ?";
		try (Connection conn = getConnection(); PreparedStatement statement = conn.prepareStatement(sql)) {
			statement.setInt(1, id);
			statement.executeUpdate();
		}
	}
}
