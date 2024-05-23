package interfaz;

import java.util.List;

import Modelos.Reporte;


public interface ReportRepository {

	 List<Reporte> getAllUsers(); 
	    
	 Reporte getReportById(int id); //llama solo a uno, por su id
	    
	 void addReport(Reporte report); //añade usuarios a la bdd
	    
	 void updateReport(Reporte report); //actualiza los usuarios de la bdd
	    
	 void deleteReport(int id); //eliminar usuarios de la bdd
}
