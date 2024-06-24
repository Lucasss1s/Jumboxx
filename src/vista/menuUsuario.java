package vista;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import Modelos.Usuario;
import controladores.UsuarioControlador;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.net.URL;

public class menuUsuario extends JFrame {

    private JButton verUsuariosButton;
    private JButton agregarUsuarioButton;
    private JButton actualizarUsuarioButton;
    private JButton eliminarUsuarioButton;
    private JButton atrasButton;

    public menuUsuario(Usuario usuario, UsuarioControlador controlador) {
    	setIconImage(Toolkit.getDefaultToolkit().getImage(menuUsuario.class.getResource("/img/Logo 2.png")));
        setTitle("Menú de Usuarios");
        setSize(400, 400);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);

        JPanel panel = new JPanel();
        panel.setBackground(new Color(0, 128, 192));
        panel.setLayout(null);

        verUsuariosButton = createButton("Ver usuarios", "/img/verUsuario.png", 43, 50, usuario, controlador);
        panel.add(verUsuariosButton);

        agregarUsuarioButton = createButton("Agregar usuario", "/img/agregarUsuario.png", 43, 100, usuario, controlador);
        panel.add(agregarUsuarioButton);

        actualizarUsuarioButton = createButton("Actualizar usuario", "/img/actualizarUsuario.png", 43, 150, usuario, controlador);
        panel.add(actualizarUsuarioButton);

        eliminarUsuarioButton = createButton("Eliminar usuario", "/img/eliminarUsuario.png", 43, 200, usuario, controlador);
        panel.add(eliminarUsuarioButton);

        atrasButton = createButton("Atrás", "/img/salir.png", 43, 250, usuario, controlador);
        panel.add(atrasButton);

        getContentPane().add(panel);
        setVisible(true);
    }

    private JButton createButton(String text, String imagePath, int x, int y, Usuario usuario, UsuarioControlador controlador) {
        JButton button = new JButton();
        button.setLayout(new BorderLayout());
        button.setBounds(x, y, 300, 40);
        button.setFont(new Font("Tahoma", Font.PLAIN, 14));
        button.setFocusPainted(false);
        button.setToolTipText("Seleccione " + text);

        JLabel label = new JLabel(text);
        label.setHorizontalAlignment(SwingConstants.LEFT);
        label.setFont(new Font("Tahoma", Font.PLAIN, 14));

        ImageIcon icon = resizeIcon(imagePath, 30, 30);
        JLabel iconLabel = new JLabel(icon);
        iconLabel.setHorizontalAlignment(SwingConstants.RIGHT);

        button.add(label, BorderLayout.WEST);
        button.add(iconLabel, BorderLayout.EAST);

        addButtonActions(button, text, usuario, controlador);
        button.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                button.setBackground(Color.LIGHT_GRAY);
            }

            public void mouseExited(java.awt.event.MouseEvent evt) {
                button.setBackground(UIManager.getColor("control"));
            }
        });

        return button;
    }

    private ImageIcon resizeIcon(String imagePath, int width, int height) {
        URL imgURL = getClass().getResource(imagePath);
        if (imgURL != null) {
            ImageIcon icon = new ImageIcon(imgURL);
            Image img = icon.getImage();
            Image resizedImg = img.getScaledInstance(width, height, Image.SCALE_SMOOTH);
            return new ImageIcon(resizedImg);
        } else {
            System.err.println("No se encontró el archivo: " + imagePath);
            return null;
        }
    }

    private void addButtonActions(JButton button, String option, Usuario usuario, UsuarioControlador controlador) {
        button.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                switch (option) {
                    case "Ver usuarios":
                        verUsuario verUsuario = new verUsuario();
                        verUsuario.setVisible(true);
                        dispose();
                        break;
                    case "Agregar usuario":
                        agregarUsuario agregarUsuario = new agregarUsuario();
                        agregarUsuario.setVisible(true);
                        dispose();
                        break;
                    case "Actualizar usuario":
                        actualizarUsuario actualizarUsuario = new actualizarUsuario();
                        actualizarUsuario.setVisible(true);
                        dispose();
                        break;
                    case "Eliminar usuario":
                        eliminarUsuario eliminarUsuario = new eliminarUsuario();
                        eliminarUsuario.setVisible(true);
                        dispose();
                        break;
                    case "Atrás":
                        dispose();
                        MenuPrincipalGerente menuPrincipalGerente = new MenuPrincipalGerente(usuario, controlador);
                        menuPrincipalGerente.setVisible(true);
                        break;
                }
            }
        });

        button.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                button.setBackground(Color.LIGHT_GRAY);
            }

            public void mouseExited(java.awt.event.MouseEvent evt) {
                button.setBackground(UIManager.getColor("control"));
            }
        });
    }

    public static void main(String[] args) {
        EventQueue.invokeLater(new Runnable() {
            public void run() {
                try {
                    UsuarioControlador controlador = new UsuarioControlador();
                    Usuario usuario = controlador.getUserById(1);
                    new menuUsuario(usuario, controlador);
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        });
    }
}
