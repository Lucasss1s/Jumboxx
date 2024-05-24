package vista;

import java.sql.SQLException;
import java.util.List;
import java.util.ArrayList;

import javax.swing.ImageIcon;
import javax.swing.JOptionPane;

import com.mysql.jdbc.Connection;

import Modelos.Administrador;
import Modelos.Almacenista;
import Modelos.BaseDatos;
import Modelos.Cliente;
import Modelos.Deposito;
import Modelos.Gerente;
import Modelos.Producto;
import Modelos.Sucursal;
import Modelos.Usuario;
import Modelos.Venta;
import controladores.UsuarioControlador;
import controladores.DatabaseConnection;

public class Main {

	public static void main(String[] args) {
		// TODO Auto-generated method stub

		BaseDatos bd = new BaseDatos();
		UsuarioControlador controlador = new UsuarioControlador();

		Cliente cliente1 = new Cliente(4, "Juan", "Gomez", "Moreno 850", 44661122);

		Venta venta1 = new Venta(1, cliente1, null, null, 1000);
		Sucursal sucursal1 = new Sucursal(1, "Carrefur", "Santa fe 1240", 445551112);
		Producto productos = new Producto(1, "Coca-cola", 20, 500);
		Deposito deposito = new Deposito(1, productos, 20, "Corrientes 2040");

		ImageIcon icon = new ImageIcon(Main.class.getResource("/img/Logo.png"));
		JOptionPane.showMessageDialog(null, "¡Bienvenido \n         a               \n  Mayorista      \n  Jumbox!",
				"Hola", JOptionPane.INFORMATION_MESSAGE, icon);

//		String username = "Lucass"; // Nombre de usuario ingresado
//        String password = "123"; // Contraseña ingresada
//
//        Usuario usuarioAutenticado = controlador.getUserByUsernameAndPassword(username, password);
//        JOptionPane.showMessageDialog(null, usuarioAutenticado.getUser() + " " + usuarioAutenticado.getPuesto());

		try {
			JOptionPane.showMessageDialog(null, "Bienvenido a Jumbox");

			String username = Usuario.pedirInputNoVacio("Ingrese su nombre de usuario:");
			String password = Usuario.pedirInputNoVacio("Ingrese su contraseña:");

			Usuario usuarioAutenticado = controlador.getUserByUsernameAndPassword(username, password);

			if (usuarioAutenticado != null) {
				JOptionPane.showMessageDialog(null,
						"Bienvenido, " + usuarioAutenticado.getNombreCompleto() + "!\n" + "Usuario: "
								+ usuarioAutenticado.getUser() + "\n" + "Puesto: " + usuarioAutenticado.getPuesto());
			} else {
				JOptionPane.showMessageDialog(null, "Usuario y/o contraseña incorrectos.");
			}

		} finally {
			if (controlador != null) {
				try {
					controlador.cerrarConexion();
				} catch (SQLException e) {
					e.printStackTrace();
				}
			}
		}

		try {
			// Agregar un producto
			Producto nuevoProducto = new Producto(11, "Leche", 10, 2.5);
			Producto.agregarProducto(nuevoProducto);
			System.out.println("Producto agregado correctamente.");

		//	Obtener un producto por su ID
		//	Producto productoObtenido = Producto.obtenerProducto(11);
		//	System.out.println("Producto obtenido: " + productoObtenido);

			// Actualizar el producto
		//	productoObtenido.setNombre("Cargador");
		//	Producto.actualizarProducto(productoObtenido);
		//	System.out.println("Producto actualizado correctamente.");

			// Obtener todos los productos
		//	List<Producto> todosLosProductos = Producto.obtenerTodosLosProductos();
		//	System.out.println("Todos los productos:");
		//	for (Producto p : todosLosProductos) {
		//		//System.out.println(p);
		//	}

			// Eliminar el producto
		//	Producto.eliminarProducto(11);
		//	System.out.println("Producto eliminado correctamente.");
			
		} catch (SQLException e) {
			e.printStackTrace();
		}

	}

}
