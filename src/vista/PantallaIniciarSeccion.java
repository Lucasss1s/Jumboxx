package vista;

import javax.swing.*;
import javax.swing.border.EmptyBorder;

import java.awt.*;
import java.awt.event.*;
import Modelos.Usuario;
import controladores.UsuarioControlador;

public class PantallaIniciarSeccion extends JFrame {

    private JPanel contentPane;
    private JTextField inpNombre;
    private JPasswordField inpContraseña;

    public static void main(String[] args) {
        EventQueue.invokeLater(new Runnable() {
            public void run() {
                try {
                    PantallaIniciarSeccion frame = new PantallaIniciarSeccion();
                    frame.setVisible(true);
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        });
    }

    public PantallaIniciarSeccion() {
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setBounds(100, 100, 650, 400);
        contentPane = new JPanel();
        contentPane.setBackground(new Color(0, 64, 128));
        contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
        setContentPane(contentPane);
        contentPane.setLayout(null);

        JLabel lblNombre = new JLabel("Nombre de usuario");
        lblNombre.setForeground(Color.WHITE);
        lblNombre.setFont(new Font("Impact", Font.ITALIC, 20));
        lblNombre.setBounds(363, 26, 160, 26);
        contentPane.add(lblNombre);

        inpNombre = new JTextField();
        inpNombre.setBounds(345, 74, 205, 28);
        contentPane.add(inpNombre);
        inpNombre.setColumns(10);

        inpContraseña = new JPasswordField();
        inpContraseña.setBounds(345, 166, 205, 28);
        contentPane.add(inpContraseña);

        JLabel lblError = new JLabel("");
        lblError.setForeground(Color.RED);
        lblError.setBounds(345, 205, 205, 26);
        contentPane.add(lblError);

        JLabel lblContraseña = new JLabel("Contraseña");
        lblContraseña.setForeground(Color.WHITE);
        lblContraseña.setFont(new Font("Impact", Font.ITALIC, 20));
        lblContraseña.setBounds(390, 126, 101, 28);
        contentPane.add(lblContraseña);

        Button button = new Button("Iniciar sesión");

        button.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                UsuarioControlador controlador = new UsuarioControlador();
                String username = inpNombre.getText();
                String password = new String(inpContraseña.getPassword()); 
                Usuario usuarioAutenticado = controlador.getUserByUsernameAndPassword(username, password);
                if (usuarioAutenticado != null) {
                    Bienvenido bienvenidoFrame = new Bienvenido(usuarioAutenticado, controlador);
                    bienvenidoFrame.setVisible(true);
                    dispose();
                } else {
                    lblError.setText("Usuario o contraseña incorrectos");
                    lblError.setVisible(true);
                }
            }
        });
        
        button.setBounds(407, 235, 84, 28);
        contentPane.add(button);

        JLabel lblNewLabel = new JLabel("");
        lblNewLabel.setIcon(new ImageIcon(PantallaIniciarSeccion.class.getResource("/img/Logo 2.png")));
        lblNewLabel.setBounds(40, 102, 205, 182);
        contentPane.add(lblNewLabel);

        JLabel lblNewLabel_1 = new JLabel("Bienvenido!");
        lblNewLabel_1.setFont(new Font("Impact", Font.ITALIC, 30));
        lblNewLabel_1.setForeground(Color.WHITE);
        lblNewLabel_1.setBounds(53, 47, 159, 66);
        contentPane.add(lblNewLabel_1);
    }
}
