package vista;

import javax.swing.*;

import Modelos.*;
import controladores.*;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class MenuPrincipalGerente extends JFrame {

    public MenuPrincipalGerente(Usuario usuario, UsuarioControlador controlador) {

        setTitle("Menú Principal");
        setSize(400, 300);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);

        JPanel panel = new JPanel();
        panel.setLayout(new GridLayout(6, 1));

        String[] opciones = {"Pedidos", "Stock", "Reportes", "Depositos", "Usuarios", "Salir"};

        for (String opcion : opciones) {
            JButton button = new JButton(opcion);
            button.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    if (opcion.equals("Pedidos")) {
                        JOptionPane.showMessageDialog(MenuPrincipalGerente.this, "Opción Pedidos seleccionada");
                    } else if (opcion.equals("Stock")) {
                        JOptionPane.showMessageDialog(MenuPrincipalGerente.this, "Opción Stock seleccionada");
                    } else if (opcion.equals("Reportes")) {
                    	dispose();
                    	TablaReporte pantallaReporte = new TablaReporte();
                    	pantallaReporte.setVisible(true);
                    } else if (opcion.equals("Depositos")) {
                        JOptionPane.showMessageDialog(MenuPrincipalGerente.this, "Opción Depositos seleccionada");
                    } else if (opcion.equals("Usuarios")) {
                    	dispose();
                        menuUsuario menuUsuarioFrame = new menuUsuario(usuario, controlador);
                        menuUsuarioFrame.setVisible(true);                     
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
