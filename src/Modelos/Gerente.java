package Modelos;

import javax.swing.JOptionPane;
import controladores.ProductoControlador;
import controladores.UsuarioControlador;

import java.time.LocalDate;
import java.util.List;

public class Gerente extends Usuario {

    public Gerente(int id_usuario, String nombreCompleto, String user, String contraseña, String puesto, LocalDate fechaRegistro) {
        super(id_usuario, nombreCompleto, user, contraseña, puesto, fechaRegistro);
    }

    public static void mostrarMenu() {
        boolean salir = false;
        do {
            String[] opciones = { "Pedidos", "Stock", "Reportes", "Almacenes", "Usuarios", "Salir" };
            int opcionSeleccionada = JOptionPane.showOptionDialog(null, "Menu", null, 0, 3, null, opciones, opciones[0]);

            switch (opcionSeleccionada) {
                case 0:
                    mostrarSubMenuPedidos();
                    break;
                case 1:
                    mostrarSubMenuStock();
                    break;
                case 2:
                    mostrarSubMenuReportes();
                    break;
                case 3:
                    JOptionPane.showMessageDialog(null, "Almacenes");
                    break;
                case 4:
                    mostrarSubMenuUsuarios();
                    break;
                case 5:
                    salir = true;
                    break;
            }
        } while (!salir);
    }

    private static void mostrarSubMenuPedidos() {
        String[] opciones = { "Ver Pedido", "Generar Pedido", "Salir" };
        int opcionSeleccionada = JOptionPane.showOptionDialog(null, "Menu", null, 0, 3, null, opciones, opciones[0]);
        switch (opcionSeleccionada) {
            case 0:
                JOptionPane.showMessageDialog(null, "Pedidos");
                break;
            case 1:
                JOptionPane.showMessageDialog(null, "Realizar Pedido");
                break;
            case 2:
                // Volver al menú anterior
                break;
        }
    }

    private static void mostrarSubMenuStock() {
        String[] opciones = { "Ver Stock", "Actualizar Stock", "Agregar producto", "Eliminar producto", "Comprar", "Salir" };
        int opcionSeleccionada = JOptionPane.showOptionDialog(null, "Menu", null, 0, 3, null, opciones, opciones[0]);
        switch (opcionSeleccionada) {
            case 0:
                verStock();
                break;
            case 1:
                actualizarStock();
                break;
            case 2:
                agregarProducto();
                break;
            case 3:
                eliminarProducto();
                break;
            case 4:
                comprarProductos();
                break;
            case 5:
                // Volver al menú anterior
                break;
        }
    }

    private static void mostrarSubMenuReportes() {
        String[] opciones = { "Ver Reportes", "Generar Reporte", "Salir" };
        int opcionSeleccionada = JOptionPane.showOptionDialog(null, "Menu", null, 0, 3, null, opciones, opciones[0]);
        switch (opcionSeleccionada) {
            case 0:
                JOptionPane.showMessageDialog(null, "Reportes");
                break;
            case 1:
                JOptionPane.showMessageDialog(null, "Realizar Reporte");
                break;
            case 2:
                // Volver al menú anterior
                break;
        }
    }

    private static void mostrarSubMenuUsuarios() {
        UsuarioControlador controlador = new UsuarioControlador();
        boolean salirUsuarios = false;
        while (!salirUsuarios) {
            String[] opciones = { "Agregar Usuario", "Ver Usuarios", "Actualizar Usuario", "Eliminar Usuario", "Salir" };
            int opcionSeleccionada = JOptionPane.showOptionDialog(null, "Menu", null, 0, 3, null, opciones, opciones[0]);
            switch (opcionSeleccionada) {
                case 0:
                    agregarUsuario(controlador);
                    break;
                case 1:
                    verUsuarios(controlador);
                    break;
                case 2:
                    actualizarUsuario(controlador);
                    break;
                case 3:
                    eliminarUsuario(controlador);
                    break;
                case 4:
                    salirUsuarios = true;
                    break;
            }
        }
    }

    private static void verStock() {
        JOptionPane.showMessageDialog(null, "Ver Stock");
    }

    private static void actualizarStock() {
        JOptionPane.showMessageDialog(null, "Actualizar Stock");
    }

    private static void agregarProducto() {
        JOptionPane.showMessageDialog(null, "Agregar Producto");
    }

    private static void eliminarProducto() {
        JOptionPane.showMessageDialog(null, "Eliminar Producto");
    }

    private static void comprarProductos() {
        ProductoControlador productoControlador = new ProductoControlador();
        List<Producto> productos = productoControlador.getAllProducts();

        String[] nombresProductos = new String[productos.size()];
        for (int i = 0; i < productos.size(); i++) {
            nombresProductos[i] = productos.get(i).getNombre() + " - Stock: " + productos.get(i).getStock();
        }

        String productoSeleccionado = (String) JOptionPane.showInputDialog(null, "Seleccione un producto para comprar",
                "Comprar Productos", JOptionPane.QUESTION_MESSAGE, null, nombresProductos, nombresProductos[0]);

        if (productoSeleccionado != null) {
            for (Producto producto : productos) {
                if (productoSeleccionado.contains(producto.getNombre())) {
                    int cantidadComprar = Integer.parseInt(JOptionPane.showInputDialog("Ingrese la cantidad a comprar:"));
                    producto.setStock(producto.getStock() + cantidadComprar);
                    productoControlador.updateStock(producto.getIdProducto(), producto.getStock());
                    JOptionPane.showMessageDialog(null, "Compra realizada con éxito. Nuevo stock: " + producto.getStock());
                    break;
                }
            }
        }
    }

    private static void verUsuarios(UsuarioControlador controlador) {
        boolean salirVerUsuarios = false;
        while (!salirVerUsuarios) {
            String[] usersID = { "Ver todos los usuarios", "Buscar usuario por ID", "Atrás" };
            int opcUsersID = JOptionPane.showOptionDialog(null, "Menu", null, 0, 3, null, usersID, usersID[0]);

            switch (opcUsersID) {
                case 0:
                    List<Usuario> usuarios = controlador.getAllUsers();
                    StringBuilder userList = new StringBuilder();
                    for (Usuario usuario : usuarios) {
                        userList.append(usuario).append("\n");
                    }
                    JOptionPane.showMessageDialog(null, userList.toString());
                    break;
                case 1:
                    int id = Integer.parseInt(JOptionPane.showInputDialog("Ingrese el ID del usuario a buscar:"));
                    Usuario usuario = controlador.getUserById(id);
                    if (usuario != null) {
                        JOptionPane.showMessageDialog(null, usuario.toString());
                    } else {
                        JOptionPane.showMessageDialog(null, "Usuario no encontrado.");
                    }
                    break;
                case 2:
                    salirVerUsuarios = true;
                    break;
            }
        }
    }

    private static void agregarUsuario(UsuarioControlador controlador) {
        String nombreCompleto = JOptionPane.showInputDialog("Ingrese el nombre completo del usuario:");
        String user = JOptionPane.showInputDialog("Ingrese el nombre de usuario:");
        String contraseña = JOptionPane.showInputDialog("Ingrese la contraseña:");
        String puesto = JOptionPane.showInputDialog("Ingrese el puesto del usuario:");
        LocalDate fechaRegistro = LocalDate.now();
        Usuario usuario = new Usuario(0, nombreCompleto, user, contraseña, puesto, fechaRegistro);
        controlador.addUser(usuario);
        JOptionPane.showMessageDialog(null, "Usuario agregado correctamente.");
    }

    private static void actualizarUsuario(UsuarioControlador controlador) {
        int id = Integer.parseInt(JOptionPane.showInputDialog("Ingrese el ID del usuario a actualizar:"));
        Usuario usuario = controlador.getUserById(id);
        if (usuario != null) {
            String nombreCompleto = JOptionPane.showInputDialog("Ingrese el nuevo nombre completo del usuario:", usuario.getNombreCompleto());
            String user = JOptionPane.showInputDialog("Ingrese el nuevo nombre de usuario:", usuario.getUser());
            String contraseña = JOptionPane.showInputDialog("Ingrese la nueva contraseña:", usuario.getContraseña());
            String puesto = JOptionPane.showInputDialog("Ingrese el nuevo puesto del usuario:", usuario.getPuesto());
            usuario.setNombreCompleto(nombreCompleto);
            usuario.setUser(user);
            usuario.setContraseña(contraseña);
            usuario.setPuesto(puesto);
            controlador.updateUser(usuario);
            JOptionPane.showMessageDialog(null, "Usuario actualizado correctamente.");
        } else {
            JOptionPane.showMessageDialog(null, "Usuario no encontrado.");
        }
    }

    private static void eliminarUsuario(UsuarioControlador controlador) {
        int id = Integer.parseInt(JOptionPane.showInputDialog("Ingrese el ID del usuario a eliminar:"));
        controlador.deleteUser(id);
        JOptionPane.showMessageDialog(null, "Usuario eliminado correctamente.");
    }
}
