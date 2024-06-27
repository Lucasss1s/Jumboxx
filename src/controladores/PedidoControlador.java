package controladores;

import java.sql.Connection;
import java.sql.DriverManager;
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
                    resultSet.getBoolean("estado"),
                    resultSet.getString("nombre"),
                    resultSet.getString("cantidad")
                );

                orders.add(order);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        System.out.println("Pedidos obtenidos: " + orders.size());
        return orders;
    }
	
	private Cliente getClienteById(int clienteId) {
		  Cliente cliente = null;
	        try {
	            PreparedStatement statement = connection.prepareStatement("SELECT * FROM cliente WHERE id_cliente = ?");
	            statement.setInt(1, clienteId);
	            ResultSet resultSet = statement.executeQuery();

	            if (resultSet.next()) {
	                cliente = new Cliente(
	                    resultSet.getInt("id_cliente"),
	                    resultSet.getString("nombre"),
	                    resultSet.getString("apellido"),
	                    resultSet.getString("direccion"),
	                    resultSet.getInt("telefono")
	                );
	            }
	        } catch (SQLException e) {
	            e.printStackTrace();
	        }
	        return cliente;
//		return null;
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

	public Pedido getPedidoById(int id) {
		Pedido pedido = null;
	    try {
	        PreparedStatement statement = connection.prepareStatement("SELECT * FROM pedido WHERE id_pedido = ?");
	        statement.setInt(1, id);
	        ResultSet resultSet = statement.executeQuery();
	        if (resultSet.next()) {
	            int clienteId = resultSet.getInt("id_cliente");
	            Cliente cliente = getClienteById(clienteId);

	            String productosStr = resultSet.getString("Productos");
	            LinkedList<String> productos = new LinkedList<>();
	            for (String producto : productosStr.split(",")) {
	                productos.add(producto);
	            }

	            pedido = new Pedido(
	                resultSet.getInt("id_pedido"),
	                cliente,
	                productos,
	                resultSet.getDate("fechaPedido").toLocalDate(),
	                resultSet.getDouble("total"),
	                resultSet.getBoolean("estado"),
	                resultSet.getString("nombre"),
	                resultSet.getString("cliente")
	            );
	        }
	    } catch (SQLException e) {
	        e.printStackTrace();
	    }
	    System.out.println("Pedido obtenido: " + pedido);
		return pedido;
	}

	public void deletePedido(int id_Pedido) {
		try {
            PreparedStatement statement = connection.prepareStatement("DELETE FROM pedido WHERE id_pedido = ?");
            statement.setInt(1, id_Pedido);
            statement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
		
	}

//	public List<Pedido> obtenerPedidosDesdeBD() {
//		List<Pedido> orders = new ArrayList<>();
//		try (Connection conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/tu_base_de_datos", "usuario", "contraseña")) {
            // Preparar la consulta SQL
//            String sql = "SELECT * FROM tabla_de_pedidos";
//            try (PreparedStatement stmt = conn.prepareStatement(sql)) {
                // Ejecutar la consulta y obtener los resultados
//                try (ResultSet rs = stmt.executeQuery()) {
                    // Recorrer los resultados y crear objetos Pedido
//                    while (rs.next()) {
//                        Pedido pedido = new Pedido(0, null, null, null, 0, true, "", "");
//                       pedido.setNombre(rs.getString("nombre"));
//                        pedido.setCantidad(rs.getString("cantidad"));
//                        orders.add(pedido);
//                    }
//                }
//            }
//       } catch (SQLException e) {
            // Manejar cualquier error de base de datos
//            e.printStackTrace();
//        }

//        return orders;
		
	 public List<Pedido> searchPedidos(String keyword) {
	        List<Pedido> result = new ArrayList<>();
	        try {
	            PreparedStatement statement = connection.prepareStatement(
	                "SELECT * FROM pedido WHERE " +
	                "id_cliente IN (SELECT id_cliente FROM cliente WHERE LOWER(nombre) LIKE ?) OR " +
	                "LOWER(productos) LIKE ? OR " +
	                "fechaPedido LIKE ? OR " +
	                "CAST(total AS VARCHAR) LIKE ? OR " +
	                "CAST(estado AS VARCHAR) LIKE ?"
	            );
	            String keywordPattern = "%" + keyword.toLowerCase() + "%";
	            statement.setString(1, keywordPattern);
	            statement.setString(2, keywordPattern);
	            statement.setString(3, keywordPattern);
	            statement.setString(4, keywordPattern);
	            statement.setString(5, keywordPattern);

	            ResultSet resultSet = statement.executeQuery();
	            while (resultSet.next()) {
	                int clienteId = resultSet.getInt("id_cliente");
	                Cliente cliente = getClienteById(clienteId);

	                String productosStr = resultSet.getString("Productos");
	                LinkedList<String> productos = new LinkedList<>();
	                for (String producto : productosStr.split(",")) {
	                    productos.add(producto);
	                }

	                Pedido pedido = new Pedido(
	                    resultSet.getInt("id_pedido"),
	                    cliente,
	                    productos,
	                    resultSet.getDate("fechaPedido").toLocalDate(),
	                    resultSet.getDouble("total"),
	                    resultSet.getBoolean("estado"),
	                    resultSet.getString("nombre"),
	                    resultSet.getString("cantidad")
	                );

	                result.add(pedido);
	            }
	        } catch (SQLException e) {
	            e.printStackTrace();
	        }
	        return result;
	}

	
	
}
