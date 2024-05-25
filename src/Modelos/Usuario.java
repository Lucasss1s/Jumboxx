package Modelos;

import java.sql.Date;
import java.time.LocalDate;

import javax.swing.JOptionPane;

public class Usuario {

	private int id_usuario;
	private String nombreCompleto;
	private String user;
	private String puesto;
	private LocalDate fechaRegistro;

	public Usuario(int id_usuario, String nombreCompleto, String user, String puesto, LocalDate fechaRegistro) {
		super();
		this.id_usuario = id_usuario;
		this.nombreCompleto = nombreCompleto;
		this.user = user;
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
                JOptionPane.showMessageDialog(null, "Error: Este campo no puede estar vacío.");
            }
        } while (input == null || input.trim().isEmpty());
        return input.trim();
           
    }
	
    
	@Override
	public String toString() {
		return "Usuario [id_usuario=" + id_usuario + ", nombreCompleto=" + nombreCompleto + ", user=" + user
				+ ", puesto=" + puesto + ", fechaRegistro=" + fechaRegistro + "]";
	}


}