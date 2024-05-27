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
        Producto producto = new Producto(11, null, 10, 100.0);
        boolean resultado = productoDAO.crearProducto(producto);
        assertFalse(resultado, "El producto no debería haberse creado con un nombre nulo.");
    }

  /*  @Test
    public void pruebaObtenerProductoPorIdExistente() {
        Producto producto = productoDAO.obtenerProductoPorId(1);
        assertNotNull(producto, "El producto debería existir.");
        assertEquals("Portátil", producto.getNombre(), "El nombre del producto debería ser 'Portátil'.");
    }

    @Test
    public void pruebaObtenerTodosLosProductos() {
        List<Producto> productos = productoDAO.obtenerTodosLosProductos();
        assertEquals(10, productos.size(), "Debería haber diez productos.");
    }

    @Test
    public void pruebaActualizarProductoExitoso() {
        Producto productoActualizado = new Producto(1, "Portátil Actualizado", 60, 899.99);
        boolean resultado = productoDAO.actualizarProducto(productoActualizado);
        assertTrue(resultado, "El producto debería haberse actualizado exitosamente.");

        Producto producto = productoDAO.obtenerProductoPorId(1);
        assertNotNull(producto, "El producto actualizado debería existir.");
        assertEquals("Portátil Actualizado", producto.getNombre(), "El nombre del producto debería ser 'Portátil Actualizado'.");
        assertEquals(60, producto.getCantidad(), "La cantidad del producto debería ser 60.");
        assertEquals(899.99, producto.getPrecio(), 0.01, "El precio del producto debería ser 899.99.");
    }

    @Test
    public void pruebaEliminarProductoExitoso() {
        boolean resultado = productoDAO.eliminarProducto(1);
        assertTrue(resultado, "El producto debería haberse eliminado exitosamente.");

        Producto producto = productoDAO.obtenerProductoPorId(1);
        assertNull(producto, "El producto no debería existir después de la eliminación.");*/
    }


