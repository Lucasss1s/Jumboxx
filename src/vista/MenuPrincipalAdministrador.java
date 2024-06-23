package vista;

import javax.swing.*;


import Modelos.*;
import controladores.*;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class MenuPrincipalAdministrador extends JFrame {

    public MenuPrincipalAdministrador(Usuario usuario, UsuarioControlador controlador) {

        setTitle("Menú Principal");
        setSize(400, 300);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);

        JPanel panel = new JPanel();
        panel.setLayout(new GridLayout(6, 1));

        String[] opciones = {"Pedidos", "Stock", "Reportes", "Perfil", "Salir"};

        for (String opcion : opciones) {
            JButton button = new JButton(opcion);
            button.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    if (opcion.equals("Pedidos")) {
                        JOptionPane.showMessageDialog(MenuPrincipalAdministrador.this, "Opción Pedidos seleccionada");
                    } else if (opcion.equals("Stock")) {
                        JOptionPane.showMessageDialog(MenuPrincipalAdministrador.this, "Opción Stock seleccionada");
                    } else if (opcion.equals("Reportes")) {
                        JOptionPane.showMessageDialog(MenuPrincipalAdministrador.this, "Opción Reportes seleccionada");
                    } else if (opcion.equals("Perfil")) {
                        perfilUsuario perfilUsuario = new perfilUsuario(controlador, usuario);
                        perfilUsuario.setVisible(true);
                        dispose();                                       
                    } else if (opcion.equals("Salir")) {
                        System.exit(0);
                    }
                }
            });
            panel.add(button);
        }

        add(panel);
        setVisible(true);
    }

}
