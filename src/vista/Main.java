package vista;

import javax.swing.ImageIcon;

import javax.swing.JOptionPane;
import java.util.List;

import Modelos.Administrador;
import Modelos.Almacenista;
import Modelos.BaseDatos;
import Modelos.Cliente;
import Modelos.Deposito;
import Modelos.Gerente;
import Modelos.Producto;
import Modelos.ProductoDAO;
import Modelos.Sucursal;
import Modelos.Venta;
import controladores.UsuarioControlador;

public class Main {

    public static void main(String[] args) {
        
        BaseDatos bd = new BaseDatos();
        UsuarioControlador controlador = new UsuarioControlador();

        bd.getAlmacenistas().add(new Almacenista(2, "Ana", "López"));
        bd.getAdministrador().add(new Administrador(3, "Elena", "García"));

        Gerente gerente = new Gerente(1, "Carlos", "Martínez");
        Cliente cliente1 = new Cliente(4, "Juan", "Gomez", "Moreno 850", 44661122);

        Venta venta1 = new Venta(1, cliente1, null, null, 1000);            
        Sucursal sucursal1 = new Sucursal(1,"Carrefur","Santa fe 1240",445551112);
        Producto productos = new Producto(1,"Coca-cola",20,500);
        Deposito deposito = new Deposito(1,productos,20,"Corrientes 2040");

        ImageIcon icon = new ImageIcon(Main.class.getResource("/img/Logo.png"));
        JOptionPane.showMessageDialog(null, "¡Bienvenido \n         a               \n  Mayorista      \n  Jumbox!", "Hola",
                JOptionPane.INFORMATION_MESSAGE, icon);

        int selecId = Integer.parseInt(JOptionPane.showInputDialog("Ingrese su ID:"));
        Almacenista almacenistaSelec = bd.BuscarAlmacenista(selecId);
        Administrador administradorSelec = bd.BuscarAdministrador(selecId);

        if (almacenistaSelec != null) {
            JOptionPane.showMessageDialog(null, "Bienvenid@: " + almacenistaSelec.getNombre());
            almacenistaSelec.mostrarMenu();
        } 

        if (administradorSelec != null) {
            JOptionPane.showMessageDialog(null, "Bienvenid@: " + administradorSelec.getNombre());
            administradorSelec.mostrarMenu();
        }

        if (selecId == gerente.getId_persona()) {
            JOptionPane.showMessageDialog(null, "Bienvenid@: " + gerente.getNombre());
            gerente.mostrarMenu();
            mostrarMenuProductos();
        }

        System.out.println("a");
    }

    private static void mostrarMenuProductos() {
        ProductoDAO productoDAO = new ProductoDAO();
        String[] opciones = {"Crear Producto", "Ver Producto por ID", "Ver Todos los Productos", "Actualizar Producto", "Eliminar Producto", "Salir"};
        int opcion;

        do {
            opcion = JOptionPane.showOptionDialog(null, "Seleccione una opción", "Gestión de Productos",
                    JOptionPane.DEFAULT_OPTION, JOptionPane.INFORMATION_MESSAGE, null, opciones, opciones[0]);

            switch (opcion) {
                case 0:
                    crearProducto(productoDAO);
                    break;
                case 1:
                    verProductoPorId(productoDAO);
                    break;
                case 2:
                    verTodosLosProductos(productoDAO);
                    break;
                case 3:
                    actualizarProducto(productoDAO);
                    break;
                case 4:
                    eliminarProducto(productoDAO);
                    break;
                case 5:
                    JOptionPane.showMessageDialog(null, "Saliendo del menú de productos.");
                    break;
                default:
                    JOptionPane.showMessageDialog(null, "Opción no válida.");
            }
        } while (opcion != 5);
    }

    private static void crearProducto(ProductoDAO productoDAO) {
        try {
            int id = Integer.parseInt(JOptionPane.showInputDialog("Ingrese ID del producto:"));
            String nombre = JOptionPane.showInputDialog("Ingrese nombre del producto:");
            int cantidad = Integer.parseInt(JOptionPane.showInputDialog("Ingrese cantidad del producto:"));
            double precio = Double.parseDouble(JOptionPane.showInputDialog("Ingrese precio del producto:"));

            Producto producto = new Producto(id, nombre, cantidad, precio);
            if (productoDAO.crearProducto(producto)) {
                JOptionPane.showMessageDialog(null, "Producto creado exitosamente.");
            } else {
                JOptionPane.showMessageDialog(null, "Error al crear el producto.");
            }
        } catch (NumberFormatException e) {
            JOptionPane.showMessageDialog(null, "Entrada no válida.");
        }
    }

    private static void verProductoPorId(ProductoDAO productoDAO) {
        try {
            int id = Integer.parseInt(JOptionPane.showInputDialog("Ingrese ID del producto:"));
            Producto producto = productoDAO.obtenerProductoPorId(id);
            if (producto != null) {
                JOptionPane.showMessageDialog(null, producto.toString());
            } else {
                JOptionPane.showMessageDialog(null, "Producto no encontrado.");
            }
        } catch (NumberFormatException e) {
            JOptionPane.showMessageDialog(null, "Entrada no válida.");
        }
    }

    private static void verTodosLosProductos(ProductoDAO productoDAO) {
        List<Producto> productos = productoDAO.obtenerTodosLosProductos();
        if (productos.isEmpty()) {
            JOptionPane.showMessageDialog(null, "No hay productos disponibles.");
        } else {
            StringBuilder sb = new StringBuilder();
            for (Producto producto : productos) {
                sb.append(producto.toString()).append("\n");
            }
            JOptionPane.showMessageDialog(null, sb.toString());
        }
    }

    private static void actualizarProducto(ProductoDAO productoDAO) {
        try {
            int id = Integer.parseInt(JOptionPane.showInputDialog("Ingrese ID del producto a actualizar:"));
            String nombre = JOptionPane.showInputDialog("Ingrese nuevo nombre del producto:");
            int cantidad = Integer.parseInt(JOptionPane.showInputDialog("Ingrese nueva cantidad del producto:"));
            double precio = Double.parseDouble(JOptionPane.showInputDialog("Ingrese nuevo precio del producto:"));

            Producto producto = new Producto(id, nombre, cantidad, precio);
            if (productoDAO.actualizarProducto(producto)) {
                JOptionPane.showMessageDialog(null, "Producto actualizado exitosamente.");
            } else {
                JOptionPane.showMessageDialog(null, "Error al actualizar el producto.");
            }
        } catch (NumberFormatException e) {
            JOptionPane.showMessageDialog(null, "Entrada no válida.");
        }
    }

    private static void eliminarProducto(ProductoDAO productoDAO) {
        try {
            int id = Integer.parseInt(JOptionPane.showInputDialog("Ingrese ID del producto a eliminar:"));
            if (productoDAO.eliminarProducto(id)) {
                JOptionPane.showMessageDialog(null, "Producto eliminado exitosamente.");
            } else {
                JOptionPane.showMessageDialog(null, "Error al eliminar el producto.");
            }
        } catch (NumberFormatException e) {
            JOptionPane.showMessageDialog(null, "Entrada no válida.");
        }
    }
}
