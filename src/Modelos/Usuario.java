package Modelos;

import java.sql.Date;
import java.time.LocalDate;

import javax.swing.JOptionPane;

import controladores.UsuarioControlador;

public class Usuario {

	private int id_usuario;
	private String nombreCompleto;
	private String user;
	private String contraseña;
	private String puesto;
	private LocalDate fechaRegistro;

	public Usuario(int id_usuario, String nombreCompleto, String user, String contraseña, String puesto,
			LocalDate fechaRegistro) {
		super();
		this.id_usuario = id_usuario;
		this.nombreCompleto = nombreCompleto;
		this.user = user;
		this.contraseña = contraseña;
		this.puesto = puesto;
		this.fechaRegistro = fechaRegistro;
	}

	public int getId_usuario() {
		return id_usuario;
	}

	public void setId_usuario(int id_usuario) {
		this.id_usuario = id_usuario;
	}

	public String getNombreCompleto() {
		return nombreCompleto;
	}

	public void setNombreCompleto(String nombreCompleto) {
		this.nombreCompleto = nombreCompleto;
	}

	public String getUser() {
		return user;
	}

	public void setUser(String user) {
		this.user = user;
	}

	public String getContraseña() {
		return contraseña;
	}

	public void setContraseña(String contraseña) {
		this.contraseña = contraseña;
	}

	public String getPuesto() {
		return puesto;
	}

	public void setPuesto(String puesto) {
		this.puesto = puesto;
	}

	public LocalDate getFechaRegistro() {
		return fechaRegistro;
	}

	public void setFechaRegistro(LocalDate fechaRegistro) {
		this.fechaRegistro = fechaRegistro;
	}

	public static String pedirInputNoVacio(String mensaje) {
		String input;
		do {
			input = JOptionPane.showInputDialog(mensaje);
			if (input == null || input.trim().isEmpty()) {
				JOptionPane.showMessageDialog(null, "Error: Este campo no puede estar vacío", "Error",
						JOptionPane.INFORMATION_MESSAGE);
			}
		} while (input == null || input.trim().isEmpty());
		return input.trim();
	}
	
	public static void updateProfile(UsuarioControlador controlador, Usuario usuarioActual) {
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
    }

	@Override
	public String toString() {
		return "Usuario [id_usuario=" + id_usuario + ", nombreCompleto=" + nombreCompleto + ", user=" + user
				+ ", puesto=" + puesto + ", fechaRegistro=" + fechaRegistro + "]";
	}

}