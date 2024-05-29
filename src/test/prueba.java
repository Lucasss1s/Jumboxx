package test;

import Modelos.Producto;
import Modelos.ProductoDAO;
import Modelos.Usuario;
import controladores.UsuarioControlador;

import org.junit.jupiter.api.Test;
import java.util.List;

import static org.junit.Assert.assertEquals;
import static org.junit.jupiter.api.Assertions.*;

public class prueba {
	

    private ProductoDAO productoDAO = new ProductoDAO();

    @Test
    public void pruebaCrearProductoConNombreNulo() {
        Producto producto = new Producto(11, null, 10, 100.0);
        boolean resultado = productoDAO.crearProducto(producto);
        assertFalse(resultado, "El producto no debería haberse creado con un nombre nulo.");
    }

    @Test
    public void pruebaCrearProductoConCantidadNegativa() {
        Producto producto = new Producto(12, "Producto Prueba", -10, 100.0);
        boolean resultado = productoDAO.crearProducto(producto);
        assertFalse(resultado, "El producto no debería haberse creado con una cantidad negativa.");
    }

    @Test
    public void pruebaCrearProductoConPrecioNegativo() {
        Producto producto = new Producto(13, "Producto Prueba", 10, -100.0);
        boolean resultado = productoDAO.crearProducto(producto);
        assertFalse(resultado, "El producto no debería haberse creado con un precio negativo.");
    }

 
//    @Test
//    public void pruebaObtenerProductoPorIdNoExistente() {
//        Producto producto = productoDAO.obtenerProductoPorId(9999); 
//        assertNull(producto, "El producto no debería existir con un ID no válido.");
//    }
//
//    @Test
//    public void pruebaEliminarProductoNoExistente() {
//        boolean resultado = productoDAO.eliminarProducto(9999); 
//        assertFalse(resultado, "El producto no debería haberse eliminado con un ID no válido.");
//    }
    
}
