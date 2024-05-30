package vista;

import java.sql.SQLException;

import java.time.LocalDate;
import java.util.List;
import java.util.ArrayList;

import javax.swing.ImageIcon;
import javax.swing.JOptionPane;

import com.mysql.jdbc.Connection;

import Modelos.Administrador;
import Modelos.Almacenista;
import Modelos.Cliente;
import Modelos.Deposito;
import Modelos.Gerente;
import Modelos.Producto;
import Modelos.Usuario;
import Modelos.Venta;
import Modelos.Reporte;
import controladores.UsuarioControlador;
import controladores.ReporteControlador;
import controladores.DatabaseConnection;

public class Main {

	public static void main(String[] args) {
		// TODO Auto-generated method stub

 	    UsuarioControlador controlador = new UsuarioControlador();
 	    
 	   List<Deposito> depositos = new ArrayList<>();
       depositos.add(new Deposito(1, 100, "Direccion 1"));
       depositos.add(new Deposito(2, 150, "Direccion 2"));
       depositos.add(new Deposito(3, 200, "Direccion 3"));
       depositos.add(new Deposito(4, 250, "Direccion 4"));
       depositos.add(new Deposito(5, 300, "Direccion 5"));
 	    
		ImageIcon icon = new ImageIcon(Main.class.getResource("/img/Logo.png"));
		JOptionPane.showMessageDialog(null, "¡Bienvenido \n         a               \n  Mayorista      \n  Jumbox!",
				"Hola", JOptionPane.INFORMATION_MESSAGE, icon);		
		
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
					boolean salir = false;
					do {
					    String[] opciones = { "Pedidos", "Stock", "Reportes", "Depositos", "Usuarios", "Salir" };
					    int opcionSeleccionada = JOptionPane.showOptionDialog(null, "Menu", null, 0, 3, null, opciones, opciones[0]);

					    switch (opcionSeleccionada) {
					        case 0:
					            String[] opciones1 = { "Ver Pedido", "Generar Pedido", "Salir" };
					            int opcionSeleccionada1 = JOptionPane.showOptionDialog(null, "Menu", null, 0, 3, null, opciones1, opciones1[0]);
					            switch (opcionSeleccionada1) {
					                case 0:
					                    JOptionPane.showMessageDialog(null, "Pedidos");
					                    break;
					                case 1:
					                    JOptionPane.showMessageDialog(null, "Realizar Pedido");
					                    break;
					                case 2:
					                    salir = true;
					                    break;
					            }
					            break;

					        case 1:
					            String[] opciones2 = { "Ver Stock", "Actualizar Stock", "Agregar producto", "Eliminar producto", "Comprar", "Salir" };
					            int opcionSeleccionada2 = JOptionPane.showOptionDialog(null, "Menu", null, 0, 3, null, opciones2, opciones2[0]);
					            switch (opcionSeleccionada2) {
					                case 0:
					                    Gerente.verStock();
					                    break;
					                case 1:
					                    Gerente.actualizarProducto();
					                    break;
					                case 2:
					                    Gerente.agregarProducto();
					                    break;
					                case 3:
					                    Gerente.eliminarProducto();
					                    break;
					                case 4:
					                    Gerente.comprarProducto();
					                    break;
					                case 5:
					                    salir = true;
					                    break;
					            }
					            break;

					        case 2:
					            ReporteControlador ReportControlador = new ReporteControlador();
					            String[] opciones3 = { "Ver Reportes", "Generar Reporte", "Editar reporte", "Eliminar reporte", "Salir" };
					            int opcionSeleccionada3 = JOptionPane.showOptionDialog(null, "Menu", null, 0, 3, null, opciones3, opciones3[0]);
					            switch (opcionSeleccionada3) {
					                case 0:
					                    JOptionPane.showMessageDialog(null, ReportControlador.getAllReport());
					                    break;
					                case 1:
					                    Gerente.generarReporte(ReportControlador);
					                    break;
					                case 2:
					                    Reporte nuevo = Gerente.SeleccionarReporte(ReportControlador);
					                    String nuevaDescripcion = JOptionPane.showInputDialog("Ingrese el nuevo problema: " + nuevo.getDescripcion());
					                    nuevo.setDescripcion(nuevaDescripcion);
					                    ReportControlador.updateReport(nuevo);
					                    break;
					                case 3:
					                    Reporte otro = Gerente.SeleccionarReporte(ReportControlador);
					                    ReportControlador.deleteReport(otro.getId_reporte());
					                    break;
					                case 4:
					                    salir = true;
					                    break;
					            }
					            break;

					        case 3:
				                   StringBuilder listaDepositos = new StringBuilder("Lista de Depósitos:\n");
				                    for (Deposito deposito : depositos) {
				                        listaDepositos.append("ID: ").append(deposito.getId())
				                                      .append(", Dirección: ").append(deposito.getDireccion())
				                                      .append(", Stock: ").append(deposito.getStock()).append("\n");
				                    }
				                    JOptionPane.showMessageDialog(null, listaDepositos.toString());
					            break;
					        case 4:
					            boolean salirUsuarios = false;
					            while (!salirUsuarios) {
					                String[] users = { "Ver usuario", "Agregar usuario", "Actualizar usuario", "Eliminar usuario", "Atrás" };
					                int opcUsers = JOptionPane.showOptionDialog(null, "Menu", null, 0, 3, null, users, users[0]);

					                switch (opcUsers) {
					                    case 0:
					                        boolean salirVerUsuarios = false;
					                        while (!salirVerUsuarios) {
					                            String[] usersID = { "Ver todos los usuarios", "Buscar usuario", "Atrás" };
					                            int opcUsersID = JOptionPane.showOptionDialog(null, "Menu", null, 0, 3, null, usersID, usersID[0]);

					                            switch (opcUsersID) {
					                                case 0:
					                                    Gerente.viewAllUser(controlador);
					                                    break;
					                                case 1:
					                                    Gerente.searchUserByID(controlador);
					                                    break;
					                                case 2:
					                                    salirVerUsuarios = true;
					                                    break;
					                            }
					                        }
					                        break;
					                    case 1:
					                        Gerente.addUser(controlador);
					                        break;
					                    case 2:
					                        Gerente.updateUser(controlador);
					                        break;
					                    case 3:
					                        Gerente.deleteUser(controlador);
					                        break;
					                    case 4:
					                        salirUsuarios = true;
					                        break;
					                }
					            }
					            break;

					        case 5:
					            System.exit(0);
					            break;
					    }
					
					} while (!salir);
				}
				if (usuarioAutenticado.getPuesto().equalsIgnoreCase("Administrador") ) {
					boolean salir1 = false;
					do {
						String[] opciones = { "Pedidos", "Stock", "Reportes", "Perfil", "Salir" };
						int opcionSeleccionada = JOptionPane.showOptionDialog(null, "Menu", null, 0, 3, null, opciones,
								opciones[0]);

						switch (opcionSeleccionada) {
						case 0:

							String[] opciones1 = { "Ver Pedido", "Generar Pedido", "Salir" };
							int opcionSeleccionada1 = JOptionPane.showOptionDialog(null, "Menu", null, 0, 3, null, opciones1,
									opciones1[0]);
							switch (opcionSeleccionada1) {
							case 0:
								JOptionPane.showMessageDialog(null, "Pedidos");
								break;
							case 1:
								JOptionPane.showMessageDialog(null, "Realizar Pedido");
								break;
							case 2:
								salir1 = true;
								break;
							}

							break;
						case 1:
							String[] opciones2 = { "Ver Stock", "Actualizar Stock", "Comprar", "Salir" };
							int opcionSeleccionada2 = JOptionPane.showOptionDialog(null, "Menu", null, 0, 3, null, opciones2,
									opciones2[0]);
							switch (opcionSeleccionada2) {
							case 0:
								Administrador.verStock();
								break;
							case 1:
								Administrador.actualizarProducto();
								break;
							case 2:
								Administrador.comprarProducto();
								break;
							case 3:
								salir1 = true;
								break;
							}
							break;
						case 2:
							ReporteControlador ReportControlador = new ReporteControlador();
							String[] opciones3 = { "Ver Reportes", "Generar Reporte", "Salir" };
							int opcionSeleccionada3 = JOptionPane.showOptionDialog(null, "Menu", null, 0, 3, null, opciones3,
									opciones3[0]);
							switch (opcionSeleccionada3) {
							case 0:
								JOptionPane.showMessageDialog(null, ReportControlador.getAllReport());
								break;
							case 1:
								int id= ReportControlador.getLastReportId()+1;
								
								String descripcion = JOptionPane.showInputDialog("Ingrese el problema");
								LocalDate fecha = LocalDate.now();
								ReportControlador.addReport(new Reporte(id,descripcion,fecha));
								break;
							case 2:
								salir1 = true;
								break;

							}
							break;
						case 3:
		                     Administrador.updateProfile(controlador, usuarioAutenticado);
		                     break;
						case 4:
							System.exit(0);
							break;
						}

					} while (salir1 = true);
				}
				
				
				if (usuarioAutenticado.getPuesto().equalsIgnoreCase("Almacenista") ) {
				    boolean salir = false;
			        do {
			            String[] opciones = { "Pedidos", "Stock", "Reportes", "Perfil", "Salir" };
			            int opcionSeleccionada = JOptionPane.showOptionDialog(null, "Menu", null, 0, 3, null, opciones, opciones[0]);

			            switch (opcionSeleccionada) {
			                case 0:
			                    String[] opciones1 = { "Ver Pedido", "Salir" };
			                    int opcionSeleccionada1 = JOptionPane.showOptionDialog(null, "Menu", null, 0, 3, null, opciones1, opciones1[0]);
			                    switch (opcionSeleccionada1) {
			                        case 0:
			                            JOptionPane.showMessageDialog(null, "Pedidos");
			                            break;
			                        case 1:
			                            salir = true;
			                            break;
			                    }
			                    break;
			                case 1:
			                    String[] opciones2 = { "Ver Stock", "Actualizar Stock", "Salir" };
			                    int opcionSeleccionada2 = JOptionPane.showOptionDialog(null, "Menu", null, 0, 3, null, opciones2, opciones2[0]);
			                    switch (opcionSeleccionada2) {
			                        case 0:
			                            Almacenista.verStock();
			                            break;
			                        case 1:
			                            Almacenista.actualizarProducto();
			                            break;
			                        case 2:
			                            salir = true;
			                            break;
			                    }
			                    break;
			                case 2:
			                	ReporteControlador ReportControlador = new ReporteControlador();
			                    String[] opciones3 = { "Ver Reportes", "Salir" };
			                    int opcionSeleccionada3 = JOptionPane.showOptionDialog(null, "Menu", null, 0, 3, null, opciones3, opciones3[0]);
			                    switch (opcionSeleccionada3) {
			                        case 0:
			                        	JOptionPane.showMessageDialog(null, ReportControlador.getAllReport());
			                            break;
			                        case 1:
			                            salir = true;
			                            break;
			                    }
			                    break;
			                case 3:
			                     Almacenista.updateProfile(controlador, usuarioAutenticado);
			                    break;
			                case 4:
			                    System.exit(0);
			                    break;
			            }
			        } while (!salir);
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

		
		

	}

	
}
