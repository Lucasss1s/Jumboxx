package controladores;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import Modelos.Reporte;

import interfaz.ReportRepository;

public class ReporteControlador implements ReportRepository{
	 private final Connection connection;

	    public ReporteControlador() {
	        this.connection = DatabaseConnection.getInstance().getConnection();
	    }

	    @Override
	    public List<Reporte> getAllReport() {
	        List<Reporte> users = new ArrayList<>();
	        try {
	            PreparedStatement statement = connection.prepareStatement("SELECT * FROM reporte ");
	            ResultSet resultSet = statement.executeQuery();
	       
	            while (resultSet.next()) {
	            	Reporte user = new Reporte(resultSet.getInt("id_reporte"), resultSet.getString("descripcion"), resultSet.getDate("fecha").toLocalDate());
	                users.add(user);
	            }
	        } catch (SQLException e) {
	            e.printStackTrace();
	        }
	        return users;
	    }

	    @Override
	    public Reporte getReportById(int id) {
	    	Reporte reporte = null;
	        try {
	            PreparedStatement statement = connection.prepareStatement("SELECT * FROM reporte WHERE id_reporte = ?");
	            statement.setInt(1, id);
	            
	            ResultSet resultSet = statement.executeQuery();
	            
	            if (resultSet.next()) {
	            	reporte = new Reporte(resultSet.getInt("id_reporte"), resultSet.getString("descripcion"), resultSet.getDate("fecha").toLocalDate());
	            }
	        } catch (SQLException e) {
	            e.printStackTrace();
	        }
	        return reporte;
	    }
	    
		@Override
	    public void addReport(Reporte Reporte) {
	        try {
	            PreparedStatement statement = connection.prepareStatement("INSERT INTO reporte (descripcion, fecha) VALUES (?, ?)");
	            statement.setString(1, Reporte.getDescripcion());
	            statement.setDate(2, java.sql.Date.valueOf(Reporte.getFecha()));
	            
	            int rowsInserted = statement.executeUpdate();
	            if (rowsInserted > 0) {
	                System.out.println("Reporte insertado exitosamente");
	            }
	        } catch (SQLException e) {
	            e.printStackTrace();
	        }
	    }

		@Override
	    public void updateReport(Reporte Reporte) {
	        try {
	            PreparedStatement statement = connection.prepareStatement("UPDATE reporte SET descripcion = ?, fecha = ? WHERE id = ?");
	            statement.setString(1, Reporte.getDescripcion());
	            statement.setDate(2, java.sql.Date.valueOf(Reporte.getFecha()));
	            statement.setInt(3, Reporte.getId_reporte());
	            
	            int rowsUpdated = statement.executeUpdate();
	            if (rowsUpdated > 0) {
	                System.out.println("Reporte actualizado exitosamente");
	            }
	        } catch (SQLException e) {
	            e.printStackTrace();
	        }
	    }

	    @Override
	    public void deleteReport(int id) {
	        try {
	            PreparedStatement statement = connection.prepareStatement("DELETE FROM reporte WHERE id = ?");
	            statement.setInt(1, id);
	            
	            int rowsDeleted = statement.executeUpdate();
	            if (rowsDeleted > 0) {
	                System.out.println("Reporte eliminado exitosamente");
	            }
	        } catch (SQLException e) {
	            e.printStackTrace();
	        }
	    }

	    
	   
}
