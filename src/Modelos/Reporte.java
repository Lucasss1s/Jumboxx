package Modelos;

import java.time.LocalDate;

import javax.swing.JOptionPane;

import controladores.ReporteControlador;
import controladores.UsuarioControlador;

public class Reporte {

	private int id_reporte;
	private String autor;
	private String descripcion;
	private LocalDate fecha;
	
	public Reporte(int id_reporte, String autor, String descripcion, LocalDate fecha) {
		super();
		this.id_reporte = id_reporte;
		this.autor = autor;
		this.descripcion = descripcion;
		this.fecha = fecha;
	}

	public int getId_reporte() {
		return id_reporte;
	}

	public void setId_reporte(int id_reporte) {
		this.id_reporte = id_reporte;
	}

	public String getAutor() {
		return autor;
	}

	public void setAutor(String autor) {
		this.autor = autor;
	}

	public String getDescripcion() {
		return descripcion;
	}

	public void setDescripcion(String descripcion) {
		this.descripcion = descripcion;
	}

	public LocalDate getFecha() {
		return fecha;
	}

	public void setFecha(LocalDate fecha) {
		this.fecha = fecha;
	}

	@Override
	public String toString() {
		return "Reporte [id_reporte=" + id_reporte + ", autor=" + autor + ", descripcion=" + descripcion + ", fecha="
				+ fecha + "]";
	}
	
	
}
