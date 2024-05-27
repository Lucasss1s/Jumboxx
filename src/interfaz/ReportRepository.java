package interfaz;

import java.util.List;

import Modelos.Reporte;


public interface ReportRepository {

	 List<Reporte> getAllReport(); 
	    
	 Reporte getReportById(int id); 
	    
	 void addReport(Reporte report); 
	    
	 void updateReport(Reporte report); 
	    
	 void deleteReport(int id); 
	 
	 int getLastReportId();
}
