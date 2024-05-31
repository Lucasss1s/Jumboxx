package Modelos;

import controladores.DatabaseConnection;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class ProductoRepositorio {
    public boolean crearProducto(Producto producto) {
        if (producto == null) {
            return false;
        }

        if (producto.getNombre() == null || producto.getNombre().isEmpty()) {
            return false;
        }

        if (producto.getCantidad() < 0) {
            return false;
        }

        if (producto.getPrecio() < 0) {
            return false;
        }

        String sql = "INSERT INTO productos (id_producto, nombre, cantidad, precio) VALUES (?, ?, ?, ?)";
        try (Connection conn = DatabaseConnection.getInstance().getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setInt(1, producto.getId_producto());
            stmt.setString(2, producto.getNombre());
            stmt.setInt(3, producto.getCantidad());
            stmt.setDouble(4, producto.getPrecio());
            stmt.executeUpdate();
            return true;
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }

    public Producto obtenerProductoPorId(int id) {
        if (id <= 0) {
            return null;
        }

        String sql = "SELECT * FROM productos WHERE id_producto = ?";
        try (Connection conn = DatabaseConnection.getInstance().getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setInt(1, id);
            ResultSet rs = stmt.executeQuery();
            if (rs.next()) {
                return new Producto(
                        rs.getInt("id_producto"),
                        rs.getString("nombre"),
                        rs.getInt("cantidad"),
                        rs.getDouble("precio")
                );
            } else {
                return null;
            }
        } catch (SQLException e) {
            e.printStackTrace();
            return null;
        }
    }

    public List<Producto> obtenerTodosLosProductos() {
        List<Producto> productos = new ArrayList<>();
        String sql = "SELECT * FROM productos";
        try (Connection conn = DatabaseConnection.getInstance().getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql);
             ResultSet rs = stmt.executeQuery()) {
            while (rs.next()) {
                productos.add(new Producto(
                        rs.getInt("id_producto"),
                        rs.getString("nombre"),
                        rs.getInt("cantidad"),
                        rs.getDouble("precio")
                ));
            }
            return productos;
        } catch (SQLException e) {
            e.printStackTrace();
            return productos;
        }
    }

    public boolean actualizarProducto(Producto producto) {
        if (producto == null) {
            return false;
        }

        if (producto.getId_producto() <= 0) {
            return false;
        }

        if (producto.getNombre() == null || producto.getNombre().isEmpty()) {
            return false;
        }

        if (producto.getCantidad() < 0) {
            return false;
        }

        if (producto.getPrecio() < 0) {
            return false;
        }

        String sql = "UPDATE productos SET nombre = ?, cantidad = ?, precio = ? WHERE id_producto = ?";
        try (Connection conn = DatabaseConnection.getInstance().getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setString(1, producto.getNombre());
            stmt.setInt(2, producto.getCantidad());
            stmt.setDouble(3, producto.getPrecio());
            stmt.setInt(4, producto.getId_producto());
            stmt.executeUpdate();
            return true;
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }

    public boolean eliminarProducto(int id) {
        if (id <= 0) {
            return false;
        }

        String sql = "DELETE FROM productos WHERE id_producto = ?";
        try (Connection conn = DatabaseConnection.getInstance().getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setInt(1, id);
            int rowsAffected = stmt.executeUpdate();
            return rowsAffected > 0;
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }
    
    public boolean eliminarTodosLosProductos() {
        String sql = "DELETE FROM productos";
        try (Connection conn = DatabaseConnection.getInstance().getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.executeUpdate();
            return true;
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
        
    }
}

