package test;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertNull;

import java.time.LocalDate;
import java.util.LinkedList;

import org.junit.jupiter.api.Test;

import Modelos.Cliente;
import Modelos.Pedido;
import controladores.PedidoControlador;

public class pruebaPedido {

    PedidoControlador controlador = new PedidoControlador();


    @Test
    public void pruebaIdNegativo() {
        boolean flag = false;
        Pedido pedido = controlador.getPedidoById(999);
        if (pedido != null) {
            flag = true;
        }
        assertEquals(false, flag);
    }
    
    

    @Test
    public void pruebaGenerarOrdenVentaMayorista() {
        Cliente mayorista = new Cliente(2, "Mayorista 1", "Gonzalo", "Vadone", 1112345678);
        LinkedList<String> productos = new LinkedList<>();
        productos.add("Producto Mayorista 1");

        Pedido pedido = new Pedido(1, new Cliente(1, "Cliente 1", "Lucas", "Frangolini", 0), new LinkedList<>(), LocalDate.now(), 0.0, false);
        Pedido.generarOrdenVentaMayorista(pedido, mayorista, productos, 150.0);

        assertEquals("Mayorista 1", pedido.getCliente().getNombre());
        assertEquals(1, pedido.getProductos().size());
        assertEquals("Producto Mayorista 1", pedido.getProductos().get(0));
        assertEquals(150.0, pedido.getTotal(), 0.01);
        assertEquals(LocalDate.now(), pedido.getFechaPedido());
        assertEquals(true, pedido.isEstado());
    }
    
    

  
    }



