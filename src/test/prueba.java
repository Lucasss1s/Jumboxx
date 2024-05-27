package test;

import Modelos.Producto;
import Modelos.ProductoDAO;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertNull;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.util.List;

public class prueba {
    private ProductoDAO productoDAO = new ProductoDAO();

 
    @Test
    public void pruebaCrearProductoConNombreNulo() {
        Producto producto = new Producto(11, null, 10, 100.0);
        boolean resultado = productoDAO.crearProducto(producto);
        assertFalse(resultado, "El producto no debería haberse creado con un nombre nulo.");
    }

   
}

