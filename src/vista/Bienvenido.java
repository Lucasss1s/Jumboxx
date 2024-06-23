package vista;

import javax.swing.JFrame;

import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import Modelos.Usuario;
import controladores.UsuarioControlador;

import java.awt.Color;
import java.awt.Font;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class Bienvenido extends JFrame {

    private static final long serialVersionUID = 1L;
    private JPanel contentPane;

    /**
     * Create the frame.
     */
    public Bienvenido(Usuario usuario, UsuarioControlador controlador) {
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setBounds(100, 100, 450, 300);
        contentPane = new JPanel();
        contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
        setContentPane(contentPane);
        contentPane.setLayout(null);

        JLabel lblNewLabel = new JLabel("Bienvenido " + usuario.getNombreCompleto());
        lblNewLabel.setFont(new Font("Tahoma", Font.PLAIN, 27));
        lblNewLabel.setBounds(74, 22, 350, 56);
        contentPane.add(lblNewLabel);

        JLabel lblUsuario = new JLabel("Usuario: " + usuario.getUser());
        lblUsuario.setFont(new Font("Tahoma", Font.PLAIN, 20));
        lblUsuario.setBounds(124, 89, 300, 20);
        contentPane.add(lblUsuario);

        JLabel lblPuesto = new JLabel("Puesto: " + usuario.getPuesto());
        lblPuesto.setFont(new Font("Tahoma", Font.PLAIN, 20));
        lblPuesto.setBounds(124, 130, 300, 20);
        contentPane.add(lblPuesto);

        JButton btnOk = new JButton("OK");
        btnOk.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
               
            	dispose();
//                Main.setUsuarioAutenticado(usuario);
//                Main.main(null);
            	if (usuario.getPuesto().equalsIgnoreCase("Gerente") ) {
		            MenuPrincipalGerente menuPrincipalGerente = new MenuPrincipalGerente(usuario, controlador);
					menuPrincipalGerente.setVisible(true);
				}
            	if (usuario.getPuesto().equalsIgnoreCase("Administrador")) {
					MenuPrincipalAdministrador MenuPrincipalAdministrador = new MenuPrincipalAdministrador(usuario, controlador);
					MenuPrincipalAdministrador.setVisible(true);
				}
            	if (usuario.getPuesto().equalsIgnoreCase("Almacenista")) {
            		MenuPrincipalAlmacenista MenuPrincipalAlmacenista = new MenuPrincipalAlmacenista(usuario, controlador);
            		MenuPrincipalAlmacenista.setVisible(true);
				}
               
            }
        });
        
        btnOk.setFont(new Font("Tahoma", Font.PLAIN, 16));
        btnOk.setBounds(159, 189, 100, 40);
        contentPane.add(btnOk);
    }
}
