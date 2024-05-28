package vista;

import java.sql.SQLException;

import java.util.List;
import java.util.ArrayList;

import javax.swing.ImageIcon;
import javax.swing.JOptionPane;

import java.sql.Connection;

import Modelos.Administrador;
import Modelos.Almacenista;
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

		UsuarioControlador controlador = new UsuarioControlador();

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
				
				
				if (usuarioAutenticado.getPuesto().equalsIgnoreCase("Gerente") ) {
					Gerente.mostrarMenu();
				}
				
				
				if (usuarioAutenticado.getPuesto().equalsIgnoreCase("Administrador") ) {
					Administrador.mostrarMenu();
				}
				
				
				if (usuarioAutenticado.getPuesto().equalsIgnoreCase("Almacenista") ) {
					Almacenista.mostrarMenu();
				}
				
								
				
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

	//	try {
			// Agregar un producto
//			Producto nuevoProducto = new Producto(11, "Leche", 10, 2.5);
//			Producto.agregarProducto(nuevoProducto);
//			System.out.println("Producto agregado correctamente.");
//

			// Eliminar el producto
//			Producto.eliminarProducto(11);
//			System.out.println("Producto eliminado correctamente.");
		
//		} catch (SQLException e) {
//			e.printStackTrace();
//		}

	}

}
