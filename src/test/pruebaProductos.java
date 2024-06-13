package test;

import Modelos.Producto;

import Modelos.ProductoRepositorio;
import org.junit.jupiter.api.Test;
import javax.swing.JOptionPane;
import java.util.List;
import static org.junit.jupiter.api.Assertions.*;

public class pruebaProductos {
	
    private ProductoRepositorio productoRepo = new ProductoRepositorio();

    @Test
    public void pruebaCrearProductoConNombreNulo() {
        boolean flag = false;
        Producto producto = new Producto(11, null, 10, 100.0);
        if (!productoRepo.crearProducto(producto)) {
            flag = true;
        }
        assertEquals(true, flag);
        JOptionPane.showMessageDialog(null, "Test 'Prueba CrearProductoConNombreNulo' "
        		+ "\n           Se ejecutado correctamente.");
    }

    @Test
    public void pruebaCrearProductoConCantidadNegativa() {
        boolean flag = false;
        Producto producto = new Producto(12, "Producto Prueba", -10, 100.0);
        if (!productoRepo.crearProducto(producto)) {
            flag = true;
        }
        assertEquals(true, flag);
        JOptionPane.showMessageDialog(null, "Test 'Prueba CrearProductoConCantidadNegativa' "
        		+ "\n           Se ejecutado correctamente.");
    }

    @Test
    public void pruebaCrearProductoConPrecioNegativo() {
        boolean flag = false;
        Producto producto = new Producto(13, "Producto Prueba", 10, -100.0);
        if (!productoRepo.crearProducto(producto)) {
            flag = true;
        }
        assertEquals(true, flag);
        JOptionPane.showMessageDialog(null, "Test 'Prueba CrearProductoConPrecioNegativo' "
        		+ "\n           Se ejecutado correctamente.");
    }

    @Test
    public void pruebaObtenerProductoPorIdNoExistente() {
        boolean flag = false;
        Producto producto = productoRepo.obtenerProductoPorId(9999);
        if (producto == null) {
            flag = true;
        }
        assertEquals(true, flag);
        JOptionPane.showMessageDialog(null, "Test 'Prueba ObtenerProductoPorIdNoExistente' "
        		+ "\n           Se ejecutado correctamente.");
    }

}

