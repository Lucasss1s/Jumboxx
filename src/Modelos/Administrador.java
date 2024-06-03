package Modelos;

import java.sql.SQLException;

import java.time.LocalDate;

import java.util.List;
import java.util.ArrayList;

import javax.swing.ImageIcon;
import javax.swing.JOptionPane;

import controladores.DatabaseConnection;
import controladores.ReporteControlador;
import controladores.UsuarioControlador;

public class Administrador extends Usuario {

	public Administrador(int id_usuario, String nombreCompleto, String user, String contraseña, String puesto,
			LocalDate fechaRegistro) {
		super(id_usuario, nombreCompleto, user, contraseña, puesto, fechaRegistro);
	}

	public void gestionarCuentas() {

		JOptionPane.showMessageDialog(null, "El administrador está gestionando las cuentas del supermercado.");
	}

	public void configurarSistema() {

		JOptionPane.showMessageDialog(null, "El administrador está configurando el sistema del supermercado.");
	}

	public static void verStock() {
		try {
			List<Producto> todosLosProductos = Producto.obtenerTodosLosProductos();
			StringBuilder productList = new StringBuilder();

			for (Producto p : todosLosProductos) {
				productList.append(p).append("\n");
			}
			JOptionPane.showMessageDialog(null, productList.toString());

		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public static void actualizarProducto() {
		JOptionPane.showMessageDialog(null, "Actualizar producto");

		try {

			// Obtener un producto por su ID

			int id_actualizar;

			id_actualizar = Integer.parseInt(JOptionPane.showInputDialog("Ingrese el id del producto: "));
			Producto productoObtenido = Producto.obtenerProducto(id_actualizar);

			JOptionPane.showMessageDialog(null, "Producto obtenido: " + productoObtenido);

			// Actualizar el producto

			int pregunta;
			pregunta = Integer.parseInt(JOptionPane.showInputDialog("Seleccione que desea actualizar del producto "
					+ productoObtenido.getNombre() + "\n 0=Nombre // 1= Cantidad // 2= Precio // 3= Volver al menu"));

			if (pregunta == 0) {

				productoObtenido.setNombre(JOptionPane.showInputDialog("Escriba el nuevo nombre: "));
				Producto.actualizarProducto(productoObtenido);
				JOptionPane.showMessageDialog(null, "Producto actualizado correctamente.");
			}

			if (pregunta == 1) {

				productoObtenido
						.setCantidad(Integer.parseInt(JOptionPane.showInputDialog("Escriba la nueva cantidad: ")));
				Producto.actualizarProducto(productoObtenido);
				JOptionPane.showMessageDialog(null, "Producto actualizado correctamente.");

			}

			if (pregunta == 2) {

				productoObtenido
						.setPrecio(Double.parseDouble(JOptionPane.showInputDialog("Escriba el nuevo precio: ")));
				Producto.actualizarProducto(productoObtenido);
				JOptionPane.showMessageDialog(null, "Producto actualizado correctamente.");

			}

			else {
				JOptionPane.showMessageDialog(null, "Volviendo al menu");
			}

		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	public static void comprarProducto() {

		JOptionPane.showMessageDialog(null, "Realizar compra de productos a proveedores");

		try {
			List<Producto> todosLosProductos = Producto.obtenerTodosLosProductos();
			StringBuilder productList = new StringBuilder("Productos Disponibles:\n");

			for (Producto p : todosLosProductos) {
				productList.append(p.getId_producto()).append(". ").append(p.getNombre()).append(" - Cantidad: ")
						.append(p.getCantidad()).append(", Precio: $").append(p.getPrecio()).append("\n");
			}
			productList.append("\nIngrese el ID del producto que desea comprar, o 0 para finalizar la compra:");

			List<Producto> productosSeleccionados = new ArrayList<>(); // Lista para almacenar los productos
																		// seleccionados

			double totalCompra = 0; // Variable para almacenar el total de la compra

			while (true) {

				int idProducto = Integer.parseInt(JOptionPane.showInputDialog(productList.toString()));
				if (idProducto == 0)
					break;

				Producto producto = Producto.obtenerProducto(idProducto);
				if (producto != null) {
					int cantidad = Integer.parseInt(JOptionPane.showInputDialog(
							"Ingrese la cantidad a comprar del producto \"" + producto.getNombre() + "\":"));
					if (cantidad <= 0) {
						JOptionPane.showMessageDialog(null, "La cantidad debe ser mayor que cero.");
						continue;
					}

					if (cantidad > producto.getCantidad()) {
						JOptionPane.showMessageDialog(null,
								"No hay suficiente stock de \"" + producto.getNombre() + "\".");
						continue;
					}

					productosSeleccionados.add(producto);

					totalCompra += producto.getPrecio() * cantidad;

					producto.setCantidad(producto.getCantidad() - cantidad);
					Producto.actualizarProducto(producto);
				} else {
					JOptionPane.showMessageDialog(null, "El ID del producto ingresado no es válido.");
				}
			}

			StringBuilder selectedProductsList = new StringBuilder("Productos Seleccionados:\n");
			for (Producto p : productosSeleccionados) {
				selectedProductsList.append("- ").append(p.getNombre()).append(", Precio: $").append(p.getPrecio())
						.append("\n");
			}
			selectedProductsList.append("\nTotal de la compra: $" + totalCompra);
			JOptionPane.showMessageDialog(null, selectedProductsList.toString());

		} catch (SQLException e) {
			e.printStackTrace();
		}

	}

}