package Modelos;

import java.time.LocalDate;

import javax.swing.JOptionPane;

import controladores.ReporteControlador;

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
}