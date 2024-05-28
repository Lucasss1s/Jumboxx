package vista;

import java.sql.SQLException;
import javax.swing.ImageIcon;
import javax.swing.JOptionPane;
import Modelos.Administrador;
import Modelos.Almacenista;
import Modelos.Gerente;
import Modelos.Usuario;
import controladores.UsuarioControlador;

public class Main {

    public static void main(String[] args) {
        UsuarioControlador controlador = new UsuarioControlador();

        ImageIcon icon = new ImageIcon(Main.class.getResource("/img/Logo.png"));
        JOptionPane.showMessageDialog(null, "¡Bienvenido \n         a               \n  Mayorista      \n  Jumbox!",
                "Hola", JOptionPane.INFORMATION_MESSAGE, icon);

        try {
            JOptionPane.showMessageDialog(null, "Bienvenido a Jumbox");

            String username = Usuario.pedirInputNoVacio("Ingrese su nombre de usuario:");
            String password = Usuario.pedirInputNoVacio("Ingrese su contraseña:");

            Usuario usuarioAutenticado = controlador.getUserByUsernameAndPassword(username, password);

            if (usuarioAutenticado != null) {
                JOptionPane.showMessageDialog(null,
                        "Bienvenido, " + usuarioAutenticado.getNombreCompleto() + "!\n" + "Usuario: "
                                + usuarioAutenticado.getUser() + "\n" + "Puesto: " + usuarioAutenticado.getPuesto());

                switch (usuarioAutenticado.getPuesto().toLowerCase()) {
                    case "gerente":
                        Gerente.mostrarMenu();
                        break;
                    case "administrador":
                        Administrador.mostrarMenu();
                        break;
                    case "almacenista":
                        Almacenista.mostrarMenu();
                        break;
                    default:
                        JOptionPane.showMessageDialog(null, "Puesto no reconocido.");
                        break;
                }
            } else {
                JOptionPane.showMessageDialog(null, "Usuario y/o contraseña incorrectos.");
            }

        } finally {
            if (controlador != null) {
                try {
                    controlador.cerrarConexion();
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            }
        }
    }
}
