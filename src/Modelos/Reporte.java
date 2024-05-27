package Modelos;

import java.time.LocalDate;

import javax.swing.JOptionPane;

import controladores.ReporteControlador;
import controladores.UsuarioControlador;

public class Reporte {

	private int id_reporte;
	private String descripcion;
	private LocalDate fecha;
	
	public Reporte(int id_reporte, String descripcion, LocalDate fecha) {
		super();
		this.id_reporte = id_reporte;
		this.descripcion = descripcion;
		this.fecha = fecha;
	}
	public int getId_reporte() {
		return id_reporte;
	}
	public void setId_reporte(int id_reporte) {
		this.id_reporte = id_reporte;
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
		return "Reporte [id_reporte=" + id_reporte + ", descripcion=" + descripcion + ", fecha=" + fecha + "]";
	}
	
	private static Reporte SeleccionarReporte(ReporteControlador controlador ) {
		String[] lista = new String[controlador.getAllReport().size()];
		
		for (int i = 0; i < lista.length; i++) {
			lista[i] = Integer.toString( controlador.getAllReport().get(i).getId_reporte());
		}
		String elegido = (String)JOptionPane.showInputDialog(null, "Elija el reporte que quiera editar", null, 0, null, lista, lista[0]);
		
		Reporte seleccionado =  controlador.getReportById(Integer.parseInt(elegido));
		 return seleccionado;
	}
}
