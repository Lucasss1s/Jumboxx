package controladores;

import interfaz.UserRepository;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import javax.swing.JOptionPane;
import Modelos.Usuario;

public class UsuarioControlador implements UserRepository {
    private final Connection connection;

    public UsuarioControlador() {
        this.connection = DatabaseConnection.getInstance().getConnection();
    }

    @Override
    public List<Usuario> getAllUsers() {
        List<Usuario> users = new ArrayList<>();
        try {
            PreparedStatement statement = connection.prepareStatement("SELECT * FROM usuario");
            ResultSet resultSet = statement.executeQuery();

            while (resultSet.next()) {
                Usuario user = new Usuario(resultSet.getInt("UsuarioID"), resultSet.getString("NombreCompleto"),
                        resultSet.getString("User"), resultSet.getString("Contraseña"), resultSet.getString("Puesto"),
                        resultSet.getDate("FechaRegistro").toLocalDate());
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
            PreparedStatement statement = connection.prepareStatement("SELECT * FROM usuario WHERE UsuarioID = ?");
            statement.setInt(1, id);

            ResultSet resultSet = statement.executeQuery();

            if (resultSet.next()) {
                user = new Usuario(resultSet.getInt("UsuarioID"), resultSet.getString("NombreCompleto"),
                        resultSet.getString("User"), resultSet.getString("Contraseña"), resultSet.getString("Puesto"),
                        resultSet.getDate("FechaRegistro").toLocalDate());
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return user;
    }

    @Override
    public void addUser(Usuario usuario) {
        try {
            PreparedStatement statement = connection.prepareStatement(
                    "INSERT INTO usuario (NombreCompleto, User, Contraseña, Puesto, FechaRegistro) VALUES (?, ?, ?, ?, ?)");
            statement.setString(1, usuario.getNombreCompleto());
            statement.setString(2, usuario.getUser());
            statement.setString(3, usuario.getContraseña());
            statement.setString(4, usuario.getPuesto());
            statement.setDate(5, java.sql.Date.valueOf(usuario.getFechaRegistro()));

            int rowsInserted = statement.executeUpdate();
            if (rowsInserted > 0) {
                JOptionPane.showMessageDialog(null, "Se agregó a " + usuario.getUser() + " con éxito");
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public int getLastUserId() {
        int ultimoId = 0;
        try {
            PreparedStatement statement = connection.prepareStatement("SELECT MAX(UsuarioID) AS max_id FROM usuario");
            ResultSet resultSet = statement.executeQuery();

            if (resultSet.next()) {
                ultimoId = resultSet.getInt("max_id");
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return ultimoId;
    }

    @Override
    public boolean usernameExists(String username) {
        try {
            PreparedStatement statement = connection.prepareStatement("SELECT * FROM usuario WHERE User = ?");
            statement.setString(1, username);
            ResultSet resultSet = statement.executeQuery();

            return resultSet.next();
        } catch (SQLException e) {
            e.printStackTrace();
            return true;
        }
    }

    @Override
    public void updateUser(Usuario usuario) {
        try {
            PreparedStatement statement = connection.prepareStatement(
                    "UPDATE usuario SET NombreCompleto = ?, User = ?, Contraseña = ?, Puesto = ?, FechaRegistro = ? WHERE UsuarioID = ?");
            statement.setString(1, usuario.getNombreCompleto());
            statement.setString(2, usuario.getUser());
            statement.setString(3, usuario.getContraseña());
            statement.setString(4, usuario.getPuesto());
            statement.setDate(5, java.sql.Date.valueOf(usuario.getFechaRegistro()));
            statement.setInt(6, usuario.getId_usuario());

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
            PreparedStatement statement = connection.prepareStatement("DELETE FROM usuario WHERE UsuarioID = ?");
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
            PreparedStatement statement = connection
                    .prepareStatement("SELECT * FROM usuario WHERE User = ? AND Contraseña = ?");
            statement.setString(1, username);
            statement.setString(2, password);
            ResultSet resultSet = statement.executeQuery();

            if (resultSet.next()) {
                user = new Usuario(resultSet.getInt("UsuarioID"), resultSet.getString("NombreCompleto"),
                        resultSet.getString("User"), resultSet.getString("Contraseña"), resultSet.getString("Puesto"),
                        resultSet.getDate("FechaRegistro").toLocalDate());
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }
        return user;
    }

    @Override
    public void cerrarConexion() throws SQLException {
        if (connection != null && !connection.isClosed()) {
            connection.close();
        }
    }
}
