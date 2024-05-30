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

public class Almacenista extends Usuario {

	public Almacenista(int id_usuario, String nombreCompleto, String user, String contraseña, String puesto,
			LocalDate fechaRegistro) {
		super(id_usuario, nombreCompleto, user, contraseña, puesto, fechaRegistro);
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

}