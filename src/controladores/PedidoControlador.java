package controladores;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.LinkedList;
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
            	
            	int clienteId = resultSet.getInt("id_cliente");
                Cliente cliente = getClienteById(clienteId);

                String productosStr = resultSet.getString("Productos");
                LinkedList<String> productos = new LinkedList<>();
                for (String producto : productosStr.split(",")) {
                    productos.add(producto);
                }

                Pedido order = new Pedido(
                    resultSet.getInt("id_pedido"),
                    cliente,
                    productos,
                    resultSet.getDate("fechaPedido").toLocalDate(),
                    resultSet.getDouble("total"),
                    resultSet.getBoolean("estado")
                );

                orders.add(order);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return orders;
    }
	
	private Cliente getClienteById(int clienteId) {
		// TODO Auto-generated method stub
		return null;
	}

	 public void updatePedido(Pedido pedido) {
	        try {
	            PreparedStatement statement = connection.prepareStatement(
	                "UPDATE pedido SET id_cliente = ?, productos = ?, fechaPedido = ?, total = ?, estado = ? WHERE id_pedido = ?"
	            );
	            statement.setInt(1, pedido.getCliente().getId_cliente());
	            String productosStr = String.join(",", pedido.getProductos());
	            statement.setString(2, productosStr);
	            statement.setDate(3, java.sql.Date.valueOf(pedido.getFechaPedido()));
	            statement.setDouble(4, pedido.getTotal());
	            statement.setBoolean(5, pedido.isEstado());
	            statement.setInt(6, pedido.getId_Pedido());

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
