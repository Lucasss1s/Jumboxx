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

    public List<Producto> getAllProducts() {
        List<Producto> products = new ArrayList<>();
        try {
            PreparedStatement statement = connection.prepareStatement("SELECT * FROM producto");
            ResultSet resultSet = statement.executeQuery();

            while (resultSet.next()) {
            	
            	Producto product = new Producto(resultSet.getInt("ProductoID"), resultSet.getString("Nombre"),
                        resultSet.getInt("Precio"), resultSet.getInt("Stock"));

                products.add(product);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return products;
    }

    public void updateStock(int productId, int newStock) {
        try {
            PreparedStatement statement = connection.prepareStatement("UPDATE producto SET Stock = ? WHERE ProductoID = ?");
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
}
