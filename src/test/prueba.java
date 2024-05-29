package test;

import Modelos.Producto;
import Modelos.ProductoDAO;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

public class prueba {
    private ProductoDAO productoDAO = new ProductoDAO();

    @Test
    public void pruebaCrearProductoConNombreNulo() {
        productoDAO.eliminarTodosLosProductos(); 
        Producto producto = new Producto(11, null, 10, 100.0);
        boolean resultado = productoDAO.crearProducto(producto);
        assertFalse(resultado, "El producto no debería haberse creado con un nombre nulo.");
    }

    @Test
    public void pruebaCrearProductoConCantidadNegativa() {
        productoDAO.eliminarTodosLosProductos(); 
        Producto producto = new Producto(12, "Producto Prueba", -10, 100.0);
        boolean resultado = productoDAO.crearProducto(producto);
        assertFalse(resultado, "El producto no debería haberse creado con una cantidad negativa.");
    }

    @Test
    public void pruebaCrearProductoConPrecioNegativo() {
        productoDAO.eliminarTodosLosProductos(); 
        Producto producto = new Producto(13, "Producto Prueba", 10, -100.0);
        boolean resultado = productoDAO.crearProducto(producto);
        assertFalse(resultado, "El producto no debería haberse creado con un precio negativo.");
    }

    @Test
    public void pruebaObtenerProductoPorIdNoExistente() {
        productoDAO.eliminarTodosLosProductos();  
        Producto producto = productoDAO.obtenerProductoPorId(9999);
        assertNull(producto, "El producto no debería existir con un ID no válido.");
    }

    @Test
    public void pruebaEliminarProductoNoExistente() {
        productoDAO.eliminarTodosLosProductos();  
        boolean resultado = productoDAO.eliminarProducto(9999);
        assertFalse(resultado, "El producto no debería haberse eliminado con un ID no válido.");
    }

    @Test
    public void pruebaCrearProductoConIdDuplicado() {
        productoDAO.eliminarTodosLosProductos();  
        Producto producto1 = new Producto(8, "Producto Original", 10, 100.0);
        Producto producto2 = new Producto(8, "Producto Duplicado", 20, 200.0);
        productoDAO.crearProducto(producto1);
        boolean resultado = productoDAO.crearProducto(producto2);
        assertFalse(resultado, "No se debería permitir crear un producto con un ID duplicado.");
    }

    
    @Test
    public void pruebaObtenerTodosLosProductosVacio() {
        productoDAO.eliminarTodosLosProductos();  
        List<Producto> productos = productoDAO.obtenerTodosLosProductos();
        assertTrue(productos.isEmpty(), "La lista de productos debería estar vacía.");
    }

    
    @Test
    public void pruebaActualizarProductoConNombreNulo() {
        productoDAO.eliminarTodosLosProductos(); 
        Producto producto = new Producto(14, "Producto Actualizable", 10, 100.0);
        productoDAO.crearProducto(producto);
        producto.setNombre(null);
        boolean resultado = productoDAO.actualizarProducto(producto);
        assertFalse(resultado, "El producto no debería haberse actualizado con un nombre nulo.");
    }

    
    @Test
    public void pruebaEliminarProductoConIdNegativo() {
        productoDAO.eliminarTodosLosProductos(); 
        boolean resultado = productoDAO.eliminarProducto(-1);
        assertFalse(resultado, "El producto no debería haberse eliminado con un ID negativo.");
    }
}
