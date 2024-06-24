package vista;

import javax.swing.*;
import Modelos.*;
import controladores.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.net.URL;

public class MenuPrincipalAdministrador extends JFrame {

    private JButton pedidosButton;
    private JButton stockButton;
    private JButton reportesButton;
    private JButton perfilButton;
    private JButton salirButton;

    public MenuPrincipalAdministrador(Usuario usuario, UsuarioControlador controlador) {

        setTitle("Menú Principal - Almacenista");
        setSize(400, 400);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);

        JPanel panel = new JPanel();
        panel.setLayout(null);

        pedidosButton = createButton("Pedidos", "/img/pedido.png", 43, 50, usuario, controlador);
        panel.add(pedidosButton);

        stockButton = createButton("Stock", "/img/productos.png", 43, 100, usuario, controlador);
        panel.add(stockButton);

        reportesButton = createButton("Reportes", "/img/reporte.png", 43, 150, usuario, controlador);
        panel.add(reportesButton);

        perfilButton = createButton("Perfil", "/img/perfil.png", 43, 200, usuario, controlador);
        panel.add(perfilButton);

        salirButton = createButton("Salir", "/img/salir.png", 43, 250, usuario, controlador);
        panel.add(salirButton);

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
            System.err.println("No se encontro el archivo: " + imagePath);
            return null;
        }
    }

    private void addButtonActions(JButton button, String opcion, Usuario usuario, UsuarioControlador controlador) {
        button.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                switch (opcion) {
                    case "Pedidos":
                        JOptionPane.showMessageDialog(MenuPrincipalAdministrador.this, "Opción Pedidos seleccionada");
                        break;
                    case "Stock":
                        JOptionPane.showMessageDialog(MenuPrincipalAdministrador.this, "Opción Stock seleccionada");
                        break;
                    case "Reportes":
                        JOptionPane.showMessageDialog(MenuPrincipalAdministrador.this, "Opción Reportes seleccionada");
                        break;
                    case "Perfil":
                        perfilUsuario perfilUsuarioFrame = new perfilUsuario(controlador, usuario);
                        perfilUsuarioFrame.setVisible(true);
                        dispose();
                        break;
                    case "Salir":
                        button.setEnabled(false);
                        System.exit(0);
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
                    new MenuPrincipalAdministrador(usuario, controlador);
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        });
    }
}
