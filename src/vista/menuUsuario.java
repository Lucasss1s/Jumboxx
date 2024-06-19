package vista;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import Modelos.*;
import controladores.UsuarioControlador;

public class menuUsuario extends JFrame {

    private JPanel contentPane;

    public menuUsuario(Usuario usuario, UsuarioControlador controlador) {
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setBounds(100, 100, 450, 300);
        contentPane = new JPanel();
        contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
        setContentPane(contentPane);
        contentPane.setLayout(new GridLayout(0, 1, 0, 10));

       
        JButton btnVerUsuarios = new JButton("Ver usuarios");
        btnVerUsuarios.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {      	  
            	verUsuario verUsuario = new verUsuario();
            	verUsuario.setVisible(true);
            	dispose();
            }
        });
        contentPane.add(btnVerUsuarios);

      
        JButton btnAgregarUsuario = new JButton("Agregar usuario");
        btnAgregarUsuario.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
               agregarUsuario agregarUsuario = new agregarUsuario();
               agregarUsuario.setVisible(true);
               dispose();
            }
        });
        contentPane.add(btnAgregarUsuario);

        
        JButton btnActualizarUsuario = new JButton("Actualizar usuario");
        btnActualizarUsuario.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                Gerente.updateUser(controlador);
            }
        });
        contentPane.add(btnActualizarUsuario);

        
        JButton btnEliminarUsuario = new JButton("Eliminar usuario");
        btnEliminarUsuario.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
            	Gerente.deleteUser(controlador);
            }
        });
        contentPane.add(btnEliminarUsuario);

        
        JButton btnAtras = new JButton("Atrás");
        btnAtras.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
               
                dispose();             
                MenuPrincipalGerente menuPrincipalGerente = new MenuPrincipalGerente(usuario, controlador);
				menuPrincipalGerente.setVisible(true);
            }
        });
        contentPane.add(btnAtras);
    }
}
