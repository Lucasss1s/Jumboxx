package Modelos;

import java.time.LocalDate;
import javax.swing.JOptionPane;
import controladores.UsuarioControlador;

public class Almacenista extends Usuario {

    public Almacenista(int id_usuario, String nombreCompleto, String user, String contraseña, String puesto,
                       LocalDate fechaRegistro) {
        super(id_usuario, nombreCompleto, user, contraseña, puesto, fechaRegistro);
    }

    public static void mostrarMenu(Usuario usuario) {
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
                            JOptionPane.showMessageDialog(null, "Stock");
                            break;
                        case 1:
                            JOptionPane.showMessageDialog(null, "Realizar compra de productos a proveedores");
                            break;
                        case 2:
                            salir = true;
                            break;
                    }
                    break;
                case 2:
                    String[] opciones3 = { "Ver Reportes", "Salir" };
                    int opcionSeleccionada3 = JOptionPane.showOptionDialog(null, "Menu", null, 0, 3, null, opciones3, opciones3[0]);
                    switch (opcionSeleccionada3) {
                        case 0:
                            JOptionPane.showMessageDialog(null, "Reportes");
                            break;
                        case 1:
                            salir = true;
                            break;
                    }
                    break;
                case 3:
                	 UsuarioControlador controlador = new UsuarioControlador();
                     Usuario usuarioActual = controlador.getUserById(usuario.getId_usuario());

                     StringBuilder perfilTexto = new StringBuilder();
                     perfilTexto.append("Nombre Completo: ").append(usuarioActual.getNombreCompleto()).append("\n")
                             .append("Usuario: ").append(usuarioActual.getUser()).append("\n")
                             .append("Puesto: ").append(usuarioActual.getPuesto()).append("\n");

                     JOptionPane.showMessageDialog(null, perfilTexto.toString(), "Perfil", JOptionPane.INFORMATION_MESSAGE);

                     boolean cambiarDatos = true;
                     while (cambiarDatos) {
                         String[] opcionesPerfil = { "Cambiar Nombre de Usuario", "Cambiar Contraseña", "Atrás" };
                         int opcionPerfil = JOptionPane.showOptionDialog(null, "Seleccione una opción", null, 0, 3, null, opcionesPerfil, opcionesPerfil[0]);

                         switch (opcionPerfil) {
                             case 0:
                                 String nuevoNombreUsuario;
                                 boolean nuevoUserExiste;
                                 do {
                                     nuevoNombreUsuario = Usuario.pedirInputNoVacio("Ingrese el nuevo nombre de usuario:");
                                     nuevoUserExiste = controlador.usernameExists(nuevoNombreUsuario);
                                     if (nuevoUserExiste) {
                                         JOptionPane.showMessageDialog(null, "El nombre de usuario ingresado ya existe. Por favor, elija otro nombre de usuario.", "Error", JOptionPane.ERROR_MESSAGE);
                                     }
                                 } while (nuevoUserExiste);

                                 while (true) {
                                     String contraseñaActual = Usuario.pedirInputNoVacio("Ingrese su contraseña actual para confirmar:");
                                     if (usuarioActual.getContraseña().equals(contraseñaActual)) {
                                         usuarioActual.setUser(nuevoNombreUsuario);
                                         controlador.updateUser(usuarioActual);
                                         JOptionPane.showMessageDialog(null, "Nombre de usuario actualizado exitosamente.");
                                         break;
                                     } else {
                                         int retry = JOptionPane.showConfirmDialog(null, "Contraseña incorrecta. ¿Desea intentar de nuevo?", "Error", JOptionPane.YES_NO_OPTION, JOptionPane.ERROR_MESSAGE);
                                         if (retry != JOptionPane.YES_OPTION) {
                                             break;
                                         }
                                     }
                                 }
                                 break;
                             case 1:
                                 while (true) {
                                     String contraseñaActual = Usuario.pedirInputNoVacio("Ingrese su contraseña actual para confirmar:");
                                     if (usuarioActual.getContraseña().equals(contraseñaActual)) {
                                         String nuevaContraseña = Usuario.pedirInputNoVacio("Ingrese la nueva contraseña:");
                                         usuarioActual.setContraseña(nuevaContraseña);
                                         controlador.updateUser(usuarioActual);
                                         JOptionPane.showMessageDialog(null, "Contraseña actualizada exitosamente.");
                                         break;
                                     } else {
                                         int retry = JOptionPane.showConfirmDialog(null, "Contraseña incorrecta. ¿Desea intentar de nuevo?", "Error", JOptionPane.YES_NO_OPTION, JOptionPane.ERROR_MESSAGE);
                                         if (retry != JOptionPane.YES_OPTION) {
                                             break;
                                         }
                                     }
                                 }
                                 break;
                             case 2:
                                 cambiarDatos = false;
                                 break;
                         }
                     }
                    break;
                case 4:
                    System.exit(0);
                    break;
            }
        } while (!salir);
    }




}
