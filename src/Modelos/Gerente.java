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

public class Gerente extends Usuario {

	public Gerente(int id_usuario, String nombreCompleto, String user, String contraseña, String puesto,
			LocalDate fechaRegistro) {
		super(id_usuario, nombreCompleto, user, contraseña, puesto, fechaRegistro);
	}

	// ----------------------------------------- FUNCIONES PARA GESTIONAR PRODUCTOS
	// ----------------------------------------- //

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

	public static void agregarProducto() {
		JOptionPane.showMessageDialog(null, "Agregar producto");

		try {

			String codigoStr = JOptionPane.showInputDialog("Ingrese el ID del producto:");
			int codigo = Integer.parseInt(codigoStr);

			String nombre = JOptionPane.showInputDialog("Ingrese el nombre del producto:");

			String cantidadStr = JOptionPane.showInputDialog("Ingrese la cantidad del producto:");
			int cantidad = Integer.parseInt(cantidadStr);

			String precioStr = JOptionPane.showInputDialog("Ingrese el precio del producto:");
			double precio = Double.parseDouble(precioStr);

			Producto nuevoProducto = new Producto(codigo, nombre, cantidad, null, precio);
			Producto.agregarProducto(nuevoProducto);
			JOptionPane.showMessageDialog(null, "Producto agregado correctamente.");

		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	public static void eliminarProducto() {
		JOptionPane.showMessageDialog(null, "Eliminar producto");

		int id = Integer.parseInt(JOptionPane.showInputDialog("Ingrese el ID del producto a eliminar:"));

		try {

			Producto.eliminarProducto(id);
			JOptionPane.showMessageDialog(null, "Producto eliminado correctamente.");

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

			List<Producto> productosSeleccionados = new ArrayList<>(); // Lista para almacenar los productos seleccionados
			
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

	// ----------------------------------------- FUNCIONES PARA GESTIONAR ?
	// ----------------------------------------- //

	public void generarInformeVentas() {

		JOptionPane.showMessageDialog(null, "El gerente está generando un informe de ventas.");
	}

	public void realizarReunionPersonal() {

		JOptionPane.showMessageDialog(null, "El gerente está realizando una reunión con el personal.");
	}

	// ----------------------------------------- FUNCIONES PARA GESTIONAR REPORTES
	// ----------------------------------------- //

	public static Reporte SeleccionarReporte(ReporteControlador controlador) {
		String[] lista = new String[controlador.getAllReport().size()];

		for (int i = 0; i < lista.length; i++) {
			lista[i] = Integer.toString(controlador.getAllReport().get(i).getId_reporte());
		}
		String elegido = (String) JOptionPane.showInputDialog(null, "Elija el reporte que quiera editar", null, 0, null,
				lista, lista[0]);

		Reporte seleccionado = controlador.getReportById(Integer.parseInt(elegido));
		return seleccionado;
	}

	public static void generarReporte(ReporteControlador ReportControlador, String autor) {
		int id = ReportControlador.getLastReportId() + 1;

		String descripcion = JOptionPane.showInputDialog("Ingrese el problema");
		LocalDate fecha = LocalDate.now();
		ReportControlador.addReport(new Reporte(id, autor, descripcion, fecha));
	}

	// ----------------------------------------- FUNCIONES PARA GESTIONAR USUARIOS
	// ----------------------------------------- //

	public static void viewAllUser(UsuarioControlador controlador) {

		List<Usuario> usuarios = controlador.getAllUsers();

		StringBuilder usuariosTexto = new StringBuilder();
		for (Usuario usuario : usuarios) {
			usuariosTexto.append("ID: ").append(usuario.getId_usuario()).append("\n").append("Nombre Completo: ")
					.append(usuario.getNombreCompleto()).append("\n").append("Usuario: ").append(usuario.getUser())
					.append("\n").append("Puesto: ").append(usuario.getPuesto()).append("\n")
					.append("Fecha de Registro: ").append(usuario.getFechaRegistro()).append("\n")
					.append("----------------------------\n");
		}

		JOptionPane.showMessageDialog(null, usuariosTexto.toString());
	}

	public static void searchUserByID(UsuarioControlador controlador) {
		while (true) {
			try {
				String inputId = Usuario.pedirInputNoVacio("Ingrese el ID del usuario:");
				int idUsuario = Integer.parseInt(inputId);

				Usuario usuario = controlador.getUserById(idUsuario);

				if (usuario != null) {
					StringBuilder usuarioTexto = new StringBuilder();
					usuarioTexto.append("ID: ").append(usuario.getId_usuario()).append("\n").append("Nombre Completo: ")
							.append(usuario.getNombreCompleto()).append("\n").append("Usuario: ")
							.append(usuario.getUser()).append("\n").append("Puesto: ").append(usuario.getPuesto())
							.append("\n").append("Fecha de Registro: ").append(usuario.getFechaRegistro()).append("\n");

					JOptionPane.showMessageDialog(null, usuarioTexto.toString(), "Usuario Encontrado",
							JOptionPane.INFORMATION_MESSAGE);
					break; // Salir del bucle si se encuentra el usuario
				} else {
					JOptionPane.showMessageDialog(null, "Usuario no encontrado. Por favor, ingrese un ID válido",
							"Error", JOptionPane.ERROR_MESSAGE);
					break;
				}
			} catch (NumberFormatException e) {
				JOptionPane.showMessageDialog(null, "ID inválido. Por favor, ingrese un número entero", "Error",
						JOptionPane.ERROR_MESSAGE);
				break;
			}
		}
	}

	public static void addUser(UsuarioControlador controlador) {
	    JOptionPane.showMessageDialog(null, "Para agregar un nuevo usuario se solicitaran los siguientes datos: "
	            + "\nNombre Completo (Obligatorio) "
	            + "\nNombre de usuario(Obligatorio), se refiere al nombre que se le pedira al usuario para ingresar al sistema "
	            + "\nPuesto (Obligatorio), esto determinara los permisos del usuario");

	    String nombreCompleto = Usuario.pedirInputNoVacio("Ingrese el nombre completo");

	    String user;
	    boolean userExiste;
	    do {
	        user = Usuario.pedirInputNoVacio("Ingrese nombre de usuario");
	        userExiste = controlador.usernameExists(user);
	        if (userExiste) {
	            JOptionPane.showMessageDialog(null,
	                    "El nombre de usuario ingresado ya existe. Por favor, elija otro nombre de usuario", "Error",
	                    JOptionPane.ERROR_MESSAGE);
	            return; 
	        }
	    } while (userExiste);

	    String[] puestos = { "Almacenista", "Administrador", "Gerente" };
	    int opcionPuesto = JOptionPane.showOptionDialog(null, "Seleccione el puesto en la empresa:",
	            "Seleccionar Puesto", JOptionPane.DEFAULT_OPTION, JOptionPane.PLAIN_MESSAGE, null, puestos, puestos[0]);

	    String puesto = puestos[opcionPuesto];
	    int id_usuario = controlador.getLastUserId() + 1;
	    LocalDate fechaRegistro = LocalDate.now();
	    String contraseña = user;

	    controlador.addUser(new Usuario(id_usuario, nombreCompleto, user, contraseña, puesto, fechaRegistro));
	}


	public static void updateUser(UsuarioControlador controlador) {
		while (true) {
			try {
				String idStr = Usuario.pedirInputNoVacio("Ingrese el ID del usuario que desea actualizar:");
				int idUsuario = Integer.parseInt(idStr);
				Usuario usuarioExistente = controlador.getUserById(idUsuario);

				if (usuarioExistente == null) {
					JOptionPane.showMessageDialog(null, "Usuario no encontrado. Por favor, ingrese un ID válido",
							"Error", JOptionPane.ERROR_MESSAGE);
					break;
				}

				boolean actualizar = true;
				while (actualizar) {
					String[] opcionesActualizar = { "Actualizar Nombre Completo", "Actualizar Nombre de Usuario",
							"Actualizar Puesto", "Atrás" };
					int opcionActualizar = JOptionPane.showOptionDialog(null, "Seleccione el campo a actualizar:",
							"Actualizar Usuario", JOptionPane.DEFAULT_OPTION, JOptionPane.PLAIN_MESSAGE, null,
							opcionesActualizar, opcionesActualizar[0]);

					switch (opcionActualizar) {
					case 0:
						String nuevoNombreCompleto = Usuario.pedirInputNoVacio("Ingrese el nuevo nombre completo:");
						if (nuevoNombreCompleto.equals(usuarioExistente.getNombreCompleto())) {
							JOptionPane.showMessageDialog(null,
									"El nuevo nombre completo es igual al actual. Por favor, ingrese un nombre diferente.",
									"Error", JOptionPane.ERROR_MESSAGE);
						} else {
							usuarioExistente.setNombreCompleto(nuevoNombreCompleto);
							controlador.updateUser(usuarioExistente);
							JOptionPane.showMessageDialog(null, "Nombre completo actualizado exitosamente");
						}
						break;
					case 1:
						String nuevoUser;
						boolean nuevoUserExiste;
						do {
							nuevoUser = Usuario.pedirInputNoVacio("Ingrese el nuevo nombre de usuario:");
							nuevoUserExiste = controlador.usernameExists(nuevoUser);
							if (nuevoUserExiste && !nuevoUser.equals(usuarioExistente.getUser())) {
								JOptionPane.showMessageDialog(null,
										"El nombre de usuario ingresado ya existe. Por favor, elija otro nombre de usuario",
										"Error", JOptionPane.ERROR_MESSAGE);
								break;
							}
						} while (nuevoUserExiste && !nuevoUser.equals(usuarioExistente.getUser()));

						if (nuevoUser.equals(usuarioExistente.getUser())) {
							JOptionPane.showMessageDialog(null,
									"El nuevo nombre de usuario es igual al actual. Por favor, ingrese un nombre diferente",
									"Error", JOptionPane.ERROR_MESSAGE);
						} else {
							usuarioExistente.setUser(nuevoUser);
							usuarioExistente.setContraseña(nuevoUser);
							controlador.updateUser(usuarioExistente);
							JOptionPane.showMessageDialog(null, "Nombre de usuario actualizado exitosamente");
						}
						break;
					case 2:
						String[] nuevosPuestos = { "Almacenista", "Administrador", "Gerente" };
						int nuevoPuestoIndex = JOptionPane.showOptionDialog(null,
								"Seleccione el nuevo puesto en la empresa:", "Seleccionar Puesto",
								JOptionPane.DEFAULT_OPTION, JOptionPane.PLAIN_MESSAGE, null, nuevosPuestos,
								usuarioExistente.getPuesto());
						if (nuevoPuestoIndex != -1) {
							String nuevoPuesto = nuevosPuestos[nuevoPuestoIndex];
							if (nuevoPuesto.equals(usuarioExistente.getPuesto())) {
								JOptionPane.showMessageDialog(null,
										"El nuevo puesto es igual al actual. Por favor, seleccione un puesto diferente",
										"Error", JOptionPane.ERROR_MESSAGE);
							} else {
								usuarioExistente.setPuesto(nuevoPuesto);
								controlador.updateUser(usuarioExistente);
								JOptionPane.showMessageDialog(null, "Puesto actualizado exitosamente");
							}
						}
						break;
					case 3:
						actualizar = false;
						break;
					}
				}
				break;
			} catch (NumberFormatException e) {
				JOptionPane.showMessageDialog(null, "ID inválido. Por favor, ingrese un número entero", "Error",
						JOptionPane.ERROR_MESSAGE);
				break;
			}
		}
	}

	public static void deleteUser(UsuarioControlador controlador) {
		while (true) {
			String idStrEliminar = Usuario.pedirInputNoVacio("Ingrese el ID del usuario que desea eliminar:");
			int idUsuarioEliminar;
			try {
				idUsuarioEliminar = Integer.parseInt(idStrEliminar);
			} catch (NumberFormatException e) {
				JOptionPane.showMessageDialog(null, "ID inválido. Por favor, ingrese un número entero", "Error",
						JOptionPane.ERROR_MESSAGE);
				break;
			}

			Usuario usuarioExistenteEliminar = controlador.getUserById(idUsuarioEliminar);

			if (usuarioExistenteEliminar == null) {
				JOptionPane.showMessageDialog(null, "Usuario no encontrado. Por favor, ingrese un ID válido", "Error",
						JOptionPane.ERROR_MESSAGE);
				break;
			}

			int confirmacion = JOptionPane.showConfirmDialog(null,
					"¿Está seguro de que desea eliminar al usuario con ID " + idUsuarioEliminar + "?",
					"Confirmar Eliminación", JOptionPane.YES_NO_OPTION);
			if (confirmacion == JOptionPane.YES_OPTION) {
				controlador.deleteUser(idUsuarioEliminar);
				JOptionPane.showMessageDialog(null, "Usuario eliminado exitosamente");
			} else {
				JOptionPane.showMessageDialog(null, "Eliminación cancelada");
			}
			break;
		}
	}

}
