package controladores;
import interfaz.UserRepository;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import Modelos.Usuario;
import Modelos.Usuario;
import interfaz.UserRepository;

public class UsuarioControlador implements UserRepository {
    private final Connection connection;

    public UsuarioControlador() {
        this.connection = DatabaseConnection.getInstance().getConnection();
    }

    @Override
    public List<Usuario> getAllUsers() {
        List<Usuario> users = new ArrayList<>();
        try {
            PreparedStatement statement = connection.prepareStatement("SELECT * FROM usuario ");
            ResultSet resultSet = statement.executeQuery();
       
            while (resultSet.next()) {
            	Usuario user = new Usuario(resultSet.getInt("UsuarioID"), resultSet.getString("NombreCompleto"), resultSet.getString("User"), 
            			resultSet.getString("Puesto"), resultSet.getDate("").toLocalDate());
                users.add(user);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return users;
    }

    @Override
    public Usuario getUserById(int id) {
    	Usuario user = null;
        try {
            PreparedStatement statement = connection.prepareStatement("SELECT * FROM users WHERE id = ?");
            statement.setInt(1, id);
            
            ResultSet resultSet = statement.executeQuery();
            
            if (resultSet.next()) {
                user = new Usuario (resultSet.getInt("UsuarioID"), resultSet.getString("NombreCompleto"), resultSet.getString("User"), 
            			resultSet.getString("Puesto"), resultSet.getDate("").toLocalDate());
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return user;
    }
    
	@Override
    public void addUser (Usuario usuario) {
        try {
            PreparedStatement statement = connection.prepareStatement("INSERT INTO users (name, user, puesto, fechaRegistro) VALUES (?, ?, ?, ?)");
            statement.setString(1, usuario.getNombreCompleto());
            statement.setString(2, usuario.getUser());
            statement.setString(3, usuario.getPuesto());
            statement.setDate(4, java.sql.Date.valueOf(usuario.getFechaRegistro()));
            
            int rowsInserted = statement.executeUpdate();
            if (rowsInserted > 0) {
                System.out.println("Usuario insertado exitosamente");
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

	@Override
    public void updateUser(Usuario usuario) {
        try {
            PreparedStatement statement = connection.prepareStatement("INSERT INTO users (name, user, puesto, fechaRegistro) VALUES (?, ?, ?, ?, ?)");
            statement.setString(1, usuario.getNombreCompleto());
            statement.setString(2, usuario.getUser());
            statement.setString(3, usuario.getPuesto());
            statement.setDate(4, java.sql.Date.valueOf(usuario.getFechaRegistro()));
            statement.setInt(5, usuario.getId_usuario());
            
            int rowsUpdated = statement.executeUpdate();
            if (rowsUpdated > 0) {
                System.out.println("Usuario actualizado exitosamente");
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void deleteUser(int id) {
        try {
            PreparedStatement statement = connection.prepareStatement("DELETE FROM users WHERE id = ?");
            statement.setInt(1, id);
            
            int rowsDeleted = statement.executeUpdate();
            if (rowsDeleted > 0) {
                System.out.println("Usuario eliminado exitosamente");
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
    
    @Override
    public Usuario getUserByUsernameAndPassword(String username, String password) {
        Usuario user = null;
        try {
            PreparedStatement statement = connection.prepareStatement("SELECT * FROM usuario WHERE User = ? AND Contraseña = ?");
            statement.setString(1, username);
            statement.setString(2, password);
            ResultSet resultSet = statement.executeQuery();
            
            if (resultSet.next()) {
                user = new Usuario(
                    resultSet.getInt("UsuarioID"),
                    resultSet.getString("NombreCompleto"),
                    resultSet.getString("User"),
                    resultSet.getString("Puesto"),
                    resultSet.getDate("FechaRegistro").toLocalDate()
                );
            }
            
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return user;
    }
    
    
    public void cerrarConexion() throws SQLException {
        if (connection != null && !connection.isClosed()) {
            connection.close();
        }
    }


  
}
