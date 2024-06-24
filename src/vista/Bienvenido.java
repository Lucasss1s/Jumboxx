package vista;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import Modelos.Usuario;
import controladores.UsuarioControlador;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class Bienvenido extends JFrame {

    private static final long serialVersionUID = 1L;
    private JPanel contentPane;

    /**
     * Create the frame.
     */
    public Bienvenido(Usuario usuario, UsuarioControlador controlador) {
    	setIconImage(Toolkit.getDefaultToolkit().getImage(Bienvenido.class.getResource("/img/Logo 2.png")));
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setBounds(100, 100, 500, 350);
        contentPane = new JPanel();
        contentPane.setBorder(new EmptyBorder(10, 10, 10, 10));
        setContentPane(contentPane);
        contentPane.setLayout(null);

        JLabel lblBienvenido = new JLabel("Bienvenido, " + usuario.getNombreCompleto() + "!");
        lblBienvenido.setForeground(new Color(255, 255, 255));
        lblBienvenido.setFont(new Font("Tahoma", Font.BOLD, 24));
        lblBienvenido.setBounds(50, 20, 400, 50);
        lblBienvenido.setHorizontalAlignment(SwingConstants.CENTER);
        contentPane.add(lblBienvenido);

        JLabel lblUsuario = new JLabel("Usuario: " + usuario.getUser());
        lblUsuario.setForeground(new Color(255, 255, 255));
        lblUsuario.setFont(new Font("Tahoma", Font.PLAIN, 18));
        lblUsuario.setBounds(160, 97, 300, 30);
        contentPane.add(lblUsuario);

        JLabel lblPuesto = new JLabel("Puesto: " + usuario.getPuesto());
        lblPuesto.setForeground(new Color(255, 255, 255));
        lblPuesto.setFont(new Font("Tahoma", Font.PLAIN, 18));
        lblPuesto.setBounds(160, 139, 300, 30);
        contentPane.add(lblPuesto);

        JButton btnOk = new JButton("OK");
        btnOk.setFont(new Font("Tahoma", Font.PLAIN, 18));
        btnOk.setBounds(194, 207, 100, 40);
        contentPane.add(btnOk);

        btnOk.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                dispose();

                switch (usuario.getPuesto().toLowerCase()) {
                    case "gerente":
                        MenuPrincipalGerente menuPrincipalGerente = new MenuPrincipalGerente(usuario, controlador);
                        menuPrincipalGerente.setVisible(true);
                        break;
                    case "administrador":
                        MenuPrincipalAdministrador menuPrincipalAdministrador = new MenuPrincipalAdministrador(usuario, controlador);
                        menuPrincipalAdministrador.setVisible(true);
                        break;
                    case "almacenista":
                        MenuPrincipalAlmacenista menuPrincipalAlmacenista = new MenuPrincipalAlmacenista(usuario, controlador);
                        menuPrincipalAlmacenista.setVisible(true);
                        break;
                    default:
                        JOptionPane.showMessageDialog(Bienvenido.this, "Puesto desconocido", "Error", JOptionPane.ERROR_MESSAGE);
                        break;
                }
            }
        });

        // UX improvements
        btnOk.setBackground(new Color(0, 153, 76));
        btnOk.setForeground(Color.WHITE);
        btnOk.setFocusPainted(false);
        btnOk.setCursor(new Cursor(Cursor.HAND_CURSOR));

        btnOk.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                btnOk.setBackground(new Color(0, 102, 51));
            }

            public void mouseExited(java.awt.event.MouseEvent evt) {
                btnOk.setBackground(new Color(0, 153, 76));
            }
        });

        contentPane.setBackground(new Color(0, 128, 192));
    }

    public static void main(String[] args) {
        EventQueue.invokeLater(new Runnable() {
            public void run() {
                try {
                    UsuarioControlador controlador = new UsuarioControlador();
                    Usuario usuario = controlador.getUserById(1);
                    new Bienvenido(usuario, controlador);
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        });
    }
}
