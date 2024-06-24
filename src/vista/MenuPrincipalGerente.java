package vista;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import controladores.UsuarioControlador;
import Modelos.Usuario;
import java.net.URL;

public class MenuPrincipalGerente extends JFrame {

    private JButton pedidosButton;
    private JButton stockButton;
    private JButton reportesButton;
    private JButton depositosButton;
    private JButton usuariosButton;
    private JButton salirButton;

    public MenuPrincipalGerente(Usuario usuario, UsuarioControlador controlador) {
        setIconImage(Toolkit.getDefaultToolkit().getImage(MenuPrincipalGerente.class.getResource("/img/Logo 2.png")));
        setTitle("Menú Principal - Gerente");
        setSize(616, 428);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);

        JPanel panel = new JPanel();
        panel.setBackground(new Color(0, 128, 192));
        panel.setLayout(null);

        pedidosButton = createButton("Pedidos", "/img/pedido.png", 43, 33, usuario, controlador);
        panel.add(pedidosButton);

        stockButton = createButton("Stock", "/img/productos.png", 43, 84, usuario, controlador);
        panel.add(stockButton);

        reportesButton = createButton("Reportes", "/img/reporte.png", 43, 135, usuario, controlador);
        panel.add(reportesButton);

        depositosButton = createButton("Depositos", "/img/pedido.png", 43, 186, usuario, controlador);
        panel.add(depositosButton);

        usuariosButton = createButton("Usuarios", "/img/usuarios.png", 43, 237, usuario, controlador);
        panel.add(usuariosButton);

        salirButton = createButton("Salir", "/img/salir.png", 43, 288, usuario, controlador);
        panel.add(salirButton);

        getContentPane().add(panel);
        
        JLabel lblNewLabel = new JLabel("New label");
        lblNewLabel.setIcon(new ImageIcon(MenuPrincipalGerente.class.getResource("/img/Logo 2.png")));
        lblNewLabel.setBounds(389, 33, 181, 127);
        panel.add(lblNewLabel);
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
                        JOptionPane.showMessageDialog(MenuPrincipalGerente.this, "Opción Pedidos seleccionada");
                        break;
                    case "Stock":
                        dispose();
                        ProductoTabla productoTablaFrame = new ProductoTabla();
                        productoTablaFrame.setVisible(true);
                        break;
                    case "Reportes":
                    	 dispose();
                         TablaReporte tablaReporte = new TablaReporte();
                         tablaReporte.setVisible(true);
                        break;
                    case "Depositos":
                        JOptionPane.showMessageDialog(MenuPrincipalGerente.this, "Opción Depositos seleccionada");
                        break;
                    case "Usuarios":
                        dispose();
                        menuUsuario menuUsuarioFrame = new menuUsuario(usuario, controlador);
                        menuUsuarioFrame.setVisible(true);
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
                    new MenuPrincipalGerente(usuario, controlador);
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        });
    }
}
