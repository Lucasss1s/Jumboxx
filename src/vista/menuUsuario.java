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

    public menuUsuario(UsuarioControlador usuario) {
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setBounds(100, 100, 450, 300);
        contentPane = new JPanel();
        contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
        setContentPane(contentPane);
        contentPane.setLayout(new GridLayout(0, 1, 0, 10));

        // Botón para ver usuarios
        JButton btnVerUsuarios = new JButton("Ver usuarios");
        btnVerUsuarios.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
               Gerente.viewAllUser(usuario);
            }
        });
        contentPane.add(btnVerUsuarios);

        // Botón para agregar usuario
        JButton btnAgregarUsuario = new JButton("Agregar usuario");
        btnAgregarUsuario.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                Gerente.addUser(usuario);
            }
        });
        contentPane.add(btnAgregarUsuario);

        // Botón para actualizar usuario
        JButton btnActualizarUsuario = new JButton("Actualizar usuario");
        btnActualizarUsuario.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                Gerente.updateUser(usuario);
            }
        });
        contentPane.add(btnActualizarUsuario);

        // Botón para eliminar usuario
        JButton btnEliminarUsuario = new JButton("Eliminar usuario");
        btnEliminarUsuario.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
            	Gerente.deleteUser(usuario);
            }
        });
        contentPane.add(btnEliminarUsuario);

        // Botón para atrás
        JButton btnAtras = new JButton("Atrás");
        btnAtras.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                // Cerrar la ventana actual
                dispose();
                // Volver a la pantalla principal (Main)
                Main.main(null);
            }
        });
        contentPane.add(btnAtras);
    }
}
