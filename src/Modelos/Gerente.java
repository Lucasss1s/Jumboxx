package Modelos;
import java.time.LocalDate;


import java.util.List;

import javax.swing.ImageIcon;
import javax.swing.JOptionPane;

import controladores.DatabaseConnection;
import controladores.UsuarioControlador;

public class Gerente extends Usuario {

	public Gerente(int id_usuario, String nombreCompleto, String user, String contraseña, String puesto,
			LocalDate fechaRegistro) {
		super(id_usuario, nombreCompleto, user, contraseña, puesto, fechaRegistro);
	}

	public static void mostrarMenu() {
    	boolean salir = false;
    	do {	
        String[] opciones = { "Pedidos", "Stock", "Reportes", "Almacenes" , "Usuarios" ,"Salir"};
        int opcionSeleccionada = JOptionPane.showOptionDialog(null, "Menu", null, 0, 3, null, opciones, opciones[0]);

        	
        switch (opcionSeleccionada) {
        case 0:
        		
        	String[] opciones1 = { "Ver Pedido", "Generar Pedido", "Salir"};
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
            String[] opciones2 = { "Ver Stock", "Actualizar Stock", "Comprar", "Salir"};
            int opcionSeleccionada2 = JOptionPane.showOptionDialog(null, "Menu", null, 0, 3, null, opciones2, opciones2[0]);
            switch (opcionSeleccionada2) {
			case 0:
				JOptionPane.showMessageDialog(null, "Stock");
				break;
			case 1:
				JOptionPane.showMessageDialog(null, "Actualizar stock");
				break;
			case 2:
				JOptionPane.showMessageDialog(null, "Realizar compra de productos a proveedores");
				break;
			case 3:
				salir = true;
				break;
			}
            break;
        case 2:
            String[] opciones3 = { "Ver Reportes", "Generar Reporte", "Salir"};
            int opcionSeleccionada3 = JOptionPane.showOptionDialog(null, "Menu", null, 0, 3, null, opciones3, opciones3[0]);
            switch (opcionSeleccionada3) {
			case 0:
				JOptionPane.showMessageDialog(null, "Reportes");
				break;
			case 1:
				JOptionPane.showMessageDialog(null, "Realizar Reporte");
				break;
			case 2:
				salir = true;
				break;

			}
            break;
     	case 3:
     		JOptionPane.showMessageDialog(null, "Almacenes");
     		break;
     	case 4:
     	    UsuarioControlador controlador = new UsuarioControlador();

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
     	                            List<Usuario> usuarios = controlador.getAllUsers();

     	                            // Crear una cadena de texto para mostrar los usuarios
     	                            StringBuilder usuariosTexto = new StringBuilder();
     	                            for (Usuario usuario : usuarios) {
     	                                usuariosTexto.append("ID: ").append(usuario.getId_usuario()).append("\n")
     	                                    .append("Nombre Completo: ").append(usuario.getNombreCompleto()).append("\n")
     	                                    .append("Usuario: ").append(usuario.getUser()).append("\n")
     	                                    .append("Puesto: ").append(usuario.getPuesto()).append("\n")
     	                                    .append("Fecha de Registro: ").append(usuario.getFechaRegistro()).append("\n")
     	                                    .append("----------------------------\n");
     	                            }

     	                            JOptionPane.showMessageDialog(null, usuariosTexto.toString());
     	                            break;
     	                        case 1:
     	                            while (true) {
     	                                try {
     	                                    String inputId = Usuario.pedirInputNoVacio("Ingrese el ID del usuario:");
     	                                    int idUsuario = Integer.parseInt(inputId);

     	                                    Usuario usuario = controlador.getUserById(idUsuario);

     	                                    if (usuario != null) {
     	                                        StringBuilder usuarioTexto = new StringBuilder();
     	                                        usuarioTexto.append("ID: ").append(usuario.getId_usuario()).append("\n")
     	                                            .append("Nombre Completo: ").append(usuario.getNombreCompleto()).append("\n")
     	                                            .append("Usuario: ").append(usuario.getUser()).append("\n")
     	                                            .append("Puesto: ").append(usuario.getPuesto()).append("\n")
     	                                            .append("Fecha de Registro: ").append(usuario.getFechaRegistro()).append("\n");

     	                                        JOptionPane.showMessageDialog(null, usuarioTexto.toString(), "Usuario Encontrado", JOptionPane.INFORMATION_MESSAGE);
     	                                        break; // Salir del bucle si se encuentra el usuario
     	                                    } else {
     	                                        JOptionPane.showMessageDialog(null, "Usuario no encontrado. Por favor, ingrese un ID válido.", "Error", JOptionPane.ERROR_MESSAGE);
     	                                    }
     	                                } catch (NumberFormatException e) {
     	                                    JOptionPane.showMessageDialog(null, "ID inválido. Por favor, ingrese un número entero.", "Error", JOptionPane.ERROR_MESSAGE);
     	                                } catch (Exception e) {
     	                                    JOptionPane.showMessageDialog(null, "Ocurrió un error inesperado: " + e.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
     	                                }
     	                            }
     	                            break;
     	                        case 2:
     	                            salirVerUsuarios = true;
     	                            break;
    
     	                    }
     	                }
     	                break;
     	            case 1:
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
     	            	        JOptionPane.showMessageDialog(null, "El nombre de usuario ingresado ya existe. Por favor, elija otro nombre de usuario.", "Error", JOptionPane.ERROR_MESSAGE);
     	            	    }
     	            	} while (userExiste);

     	            	String[] puestos = { "Almacenista", "Administrador", "Gerente" };
     	            	int opcionPuesto = JOptionPane.showOptionDialog(null, "Seleccione el puesto en la empresa:", "Seleccionar Puesto",
     	            	        JOptionPane.DEFAULT_OPTION, JOptionPane.PLAIN_MESSAGE, null, puestos, puestos[0]); 	            	

     	            	String puesto = puestos[opcionPuesto];
     	            	int id_usuario = controlador.getLastUserId() + 1;
     	            	LocalDate fechaRegistro = LocalDate.now();
     	            	String contraseña = user;

     	            	controlador.addUser(new Usuario(id_usuario, nombreCompleto, user, contraseña, puesto, fechaRegistro));
     	            	
     	                break;
     	            case 2:
     	            	while (true) {
                            try {
                                String idStr = Usuario.pedirInputNoVacio("Ingrese el ID del usuario que desea actualizar:");
                                int idUsuario = Integer.parseInt(idStr);
                                Usuario usuarioExistente = controlador.getUserById(idUsuario);

                                if (usuarioExistente == null) {
                                    JOptionPane.showMessageDialog(null, "Usuario no encontrado. Por favor, ingrese un ID válido.", "Error", JOptionPane.ERROR_MESSAGE);
                                    continue;
                                }

                                boolean actualizar = true;
                                while (actualizar) {
                                    String[] opcionesActualizar = { "Actualizar Nombre Completo", "Actualizar Nombre de Usuario", "Actualizar Puesto", "Atrás" };
                                    int opcionActualizar = JOptionPane.showOptionDialog(null, "Seleccione el campo a actualizar:", "Actualizar Usuario",
                                            JOptionPane.DEFAULT_OPTION, JOptionPane.PLAIN_MESSAGE, null, opcionesActualizar, opcionesActualizar[0]);

                                    switch (opcionActualizar) {
                                        case 0:
                                            String nuevoNombreCompleto = Usuario.pedirInputNoVacio("Ingrese el nuevo nombre completo:");
                                            usuarioExistente.setNombreCompleto(nuevoNombreCompleto);
                                            controlador.updateUser(usuarioExistente);
                                            JOptionPane.showMessageDialog(null, "Nombre completo actualizado exitosamente.");
                                            break;
                                        case 1:
                                            String nuevoUser;
                                            boolean nuevoUserExiste;
                                            do {
                                                nuevoUser = Usuario.pedirInputNoVacio("Ingrese el nuevo nombre de usuario:");
                                                nuevoUserExiste = controlador.usernameExists(nuevoUser);
                                                if (nuevoUserExiste && !nuevoUser.equals(usuarioExistente.getUser())) {
                                                    JOptionPane.showMessageDialog(null, "El nombre de usuario ingresado ya existe. Por favor, elija otro nombre de usuario.", "Error", JOptionPane.ERROR_MESSAGE);
                                                }
                                            } while (nuevoUserExiste && !nuevoUser.equals(usuarioExistente.getUser()));
                                            usuarioExistente.setUser(nuevoUser);
                                            usuarioExistente.setContraseña(nuevoUser);
                                            controlador.updateUser(usuarioExistente);
                                            JOptionPane.showMessageDialog(null, "Nombre de usuario actualizado exitosamente.");
                                            break;
                                        case 2:
                                            String[] nuevosPuestos = { "Almacenista", "Administrador", "Gerente" };
                                            int nuevoPuestoIndex = JOptionPane.showOptionDialog(null, "Seleccione el nuevo puesto en la empresa:", "Seleccionar Puesto",
                                                    JOptionPane.DEFAULT_OPTION, JOptionPane.PLAIN_MESSAGE, null, nuevosPuestos, usuarioExistente.getPuesto());
                                            if (nuevoPuestoIndex != -1) {
                                                usuarioExistente.setPuesto(nuevosPuestos[nuevoPuestoIndex]);
                                                controlador.updateUser(usuarioExistente);
                                                JOptionPane.showMessageDialog(null, "Puesto actualizado exitosamente.");
                                            } else {
                                                JOptionPane.showMessageDialog(null, "Debe seleccionar un puesto válido. Inténtelo nuevamente.", "Error", JOptionPane.ERROR_MESSAGE);
                                            }
                                            break;
                                        case 3:
                                            actualizar = false;
                                            break;
                                    }
                                }
                                break;
                            } catch (NumberFormatException e) {
                                JOptionPane.showMessageDialog(null, "ID inválido. Por favor, ingrese un número entero.", "Error", JOptionPane.ERROR_MESSAGE);
                            } catch (Exception e) {
                                JOptionPane.showMessageDialog(null, "Ocurrió un error inesperado: " + e.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
                            }
                        }
     	                break;
     	           case 3:
    	               // Implementar eliminar usuario
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
   
    	} while (salir = true);
    }
   
    public void generarInformeVentas() {
        
        JOptionPane.showMessageDialog(null,"El gerente está generando un informe de ventas.");
    }

    
    public void realizarReunionPersonal() {
    	
    	JOptionPane.showMessageDialog(null,"El gerente está realizando una reunión con el personal.");
    }
}
