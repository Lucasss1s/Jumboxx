package test;

import static org.junit.Assert.assertEquals;

import java.time.LocalDate;

import org.junit.jupiter.api.Test;

import Modelos.Reporte;
import Modelos.Usuario;
import controladores.ReporteControlador;

public class pruebaReporte {
	
	ReporteControlador controlador = new ReporteControlador();
	
	@Test
	public void pruebaIdPositivo () {
		boolean flag = false;
        Reporte reporte = controlador.getReportById(1);
        if (reporte != null) {
            flag = true;
        }
        assertEquals(true, flag);
	}
	
	 @Test
	    public void pruebaIdNull(){
	        boolean flag = false;
	        Reporte reporte = controlador.getReportById(999);
	        if (reporte != null) {
	            flag = true;
	        }
	        assertEquals(false, flag);
	    }
	 @Test
	    public void pruebaAgregarReporte(){
		 String autor = "Gerente";
	        Reporte NuevoReporte = new Reporte(0, autor, "Nuevo Reporte", LocalDate.now());
	        controlador.addReport(NuevoReporte);
	       
	        Reporte reporteAgregado = controlador.getReportById(0);
	        
	        assertEquals(0, reporteAgregado.getId_reporte());
	        assertEquals(autor, reporteAgregado.getAutor());
	        assertEquals("Nuevo Reporte", reporteAgregado.getDescripcion());
	        assertEquals(LocalDate.now(), reporteAgregado.getFecha());
	     
	    }
	 @Test
	    public void pruebaEliminarReporte() {
	        Reporte ReporteAEliminar = controlador.getReportById(0);
	        if (ReporteAEliminar != null) {
	            controlador.deleteReport(ReporteAEliminar.getId_reporte());

	            Reporte ReporteEliminado = controlador.getReportById(ReporteAEliminar.getId_reporte());
	            assertEquals(null, ReporteEliminado);
	        } else {
	            assertEquals(true, false); 
	        }
	    }

}
