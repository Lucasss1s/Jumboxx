package controladores;

import Modelos.Producto;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class ProductoControlador {
    private final Connection connection;

    public ProductoControlador() {
        this.connection = DatabaseConnection.getInstance().getConnection();
    }

    public void agregarProducto(Producto producto) {
        String sql = "INSERT INTO productos (id_producto, nombre, cantidad, imagen, precio) VALUES (?, ?, ?, ?, ?)";
        try (PreparedStatement statement = connection.prepareStatement(sql)) {
            statement.setInt(1, producto.getId_producto());
            statement.setString(2, producto.getNombre());
            statement.setInt(3, producto.getCantidad());
            statement.setBytes(4, producto.getImagen());
            statement.setDouble(5, producto.getPrecio());
            statement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public Producto getProductById(int id) {
        String sql = "SELECT * FROM productos WHERE id_producto = ?";
        try (PreparedStatement statement = connection.prepareStatement(sql)) {
            statement.setInt(1, id);
            ResultSet resultSet = statement.executeQuery();
            if (resultSet.next()) {
                return new Producto(
                    resultSet.getInt("id_producto"),
                    resultSet.getString("nombre"),
                    resultSet.getInt("cantidad"),
                    resultSet.getBytes("imagen"),
                    resultSet.getDouble("precio")
                );
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    public void eliminarProducto(int id_producto) {
        String sql = "DELETE FROM productos WHERE id_producto = ?";
        try (PreparedStatement statement = connection.prepareStatement(sql)) {
            statement.setInt(1, id_producto);
            statement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public List<Producto> getAllProducts() {
        List<Producto> products = new ArrayList<>();
        String sql = "SELECT * FROM productos";
        try (PreparedStatement statement = connection.prepareStatement(sql);
             ResultSet resultSet = statement.executeQuery()) {
            while (resultSet.next()) {
                Producto product = new Producto(
                    resultSet.getInt("id_producto"),
                    resultSet.getString("nombre"),
                    resultSet.getInt("cantidad"),
                    resultSet.getBytes("imagen"),
                    resultSet.getDouble("precio")
                );
                products.add(product);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return products;
    }

    public void updateStock(int productId, int newStock) {
        String sql = "UPDATE productos SET cantidad = ? WHERE id_producto = ?";
        try (PreparedStatement statement = connection.prepareStatement(sql)) {
            statement.setInt(1, newStock);
            statement.setInt(2, productId);
            statement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void closeConnection() throws SQLException {
        if (connection != null && !connection.isClosed()) {
            connection.close();
        }
    }
    public void actualizarProducto(Producto producto) {
        String sql = "UPDATE productos SET nombre = ?, cantidad = ?, imagen = ?, precio = ? WHERE id_producto = ?";
        try (PreparedStatement statement = connection.prepareStatement(sql)) {
            statement.setString(1, producto.getNombre());
            statement.setInt(2, producto.getCantidad());
            statement.setBytes(3, producto.getImagen());
            statement.setDouble(4, producto.getPrecio());
            statement.setInt(5, producto.getId_producto());
            statement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

}
