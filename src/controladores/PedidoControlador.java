package controladores;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import Modelos.Pedido;
import Modelos.Cliente;

public class PedidoControlador {
	private final Connection connection;

	public PedidoControlador() {
		this.connection = DatabaseConnection.getInstance().getConnection();
	}
	
	public List<Pedido> getAllOrders() {
        List<Pedido> orders = new ArrayList<>();
        try {
            PreparedStatement statement = connection.prepareStatement("SELECT * FROM pedido");
            ResultSet resultSet = statement.executeQuery();

            while (resultSet.next()) {
            	
            	Pedido order = new Pedido(resultSet.getInt("id_pedido"), resultSet.get("Cliente"),
            			resultSet.getString("Productos"), resultSet.getDate("fechaPedido"), resultSet.getDouble("total"), resultSet.getBoolean("estado"));

                orders.add(order);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return orders;
    }
	
	public void updateStock(int pedidoId) {
        try {
            PreparedStatement statement = connection.prepareStatement("SELECT * FROM pedido WHERE PedidoID = ?");
            statement.setInt(1, pedidoId);
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
