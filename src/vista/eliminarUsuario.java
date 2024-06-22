package vista;

import java.awt.Color;
import java.awt.EventQueue;
import java.awt.Font;
import javax.swing.*;
import javax.swing.border.EmptyBorder;
import controladores.UsuarioControlador;
import Modelos.Usuario;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class eliminarUsuario extends JFrame {

    private static final long serialVersionUID = 1L;
    private JPanel contentPane;
    private JTextField idField;
    private JLabel errorLabel;
    private JLabel usuarioDetalles;
    private UsuarioControlador controlador;
    private JButton eliminarButton;
    private Usuario seleccionado;

    public static void main(String[] args) {
        EventQueue.invokeLater(new Runnable() {
            public void run() {
                try {
                    eliminarUsuario frame = new eliminarUsuario();
                    frame.setVisible(true);
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        });
    }

    public eliminarUsuario() {
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setBounds(100, 100, 500, 300);
        contentPane = new JPanel();
        contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
        setContentPane(contentPane);
        contentPane.setLayout(null);

        controlador = new UsuarioControlador();

        JLabel lblId = new JLabel("ID del Usuario:");
        lblId.setBounds(50, 30, 120, 20);
        contentPane.add(lblId);

        idField = new JTextField();
        idField.setBounds(180, 30, 200, 20);
        contentPane.add(idField);
        idField.setColumns(10);

        errorLabel = new JLabel("");
        errorLabel.setForeground(Color.RED);
        errorLabel.setBounds(50, 60, 330, 20);
        contentPane.add(errorLabel);

        usuarioDetalles = new JLabel("");
        usuarioDetalles.setFont(new Font("Tahoma", Font.PLAIN, 12));
        usuarioDetalles.setBounds(50, 90, 400, 60);
        contentPane.add(usuarioDetalles);

        JButton buscarButton = new JButton("Buscar Usuario");
        buscarButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                buscarUsuario();
            }
        });
        buscarButton.setBounds(50, 170, 150, 30);
        contentPane.add(buscarButton);

        eliminarButton = new JButton("Eliminar Usuario");
        eliminarButton.setEnabled(false);
        eliminarButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                eliminarUsuario();
            }
        });
        eliminarButton.setBounds(220, 170, 150, 30);
        contentPane.add(eliminarButton);

        JButton volverButton = new JButton("Volver");
        volverButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                volver();
            }
        });
        volverButton.setBounds(50, 220, 100, 30);
        contentPane.add(volverButton);

        JButton limpiarButton = new JButton("Limpiar");
        limpiarButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                limpiarCampos();
            }
        });
        limpiarButton.setBounds(270, 220, 100, 30);
        contentPane.add(limpiarButton);
    }

    private void buscarUsuario() {
        String idStr = idField.getText().trim();
        if (idStr.isEmpty()) {
            errorLabel.setText("ID es obligatorio");
            return;
        }
        int idUsuario;
        try {
            idUsuario = Integer.parseInt(idStr);
        } catch (NumberFormatException e) {
            errorLabel.setText("ID inválido. Por favor, ingrese un número entero");
            return;
        }

        Usuario usuarioExistente = controlador.getUserById(idUsuario);
        if (usuarioExistente == null) {
            errorLabel.setText("Usuario no encontrado. Por favor, ingrese un ID válido");
            usuarioDetalles.setText("");
            eliminarButton.setEnabled(false);
        } else {
            errorLabel.setText("");
            usuarioDetalles.setText("<html>ID: " + usuarioExistente.getId_usuario() + "<br>"
                    + "Nombre: " + usuarioExistente.getNombreCompleto() + "<br>"
                    + "Usuario: " + usuarioExistente.getUser() + "<br>"
                    + "Puesto: " + usuarioExistente.getPuesto() + "</html>");
            eliminarButton.setEnabled(true);
        }
    }

    private void eliminarUsuario() {
        int idUsuario = Integer.parseInt(idField.getText().trim());

        int confirmacion = JOptionPane.showConfirmDialog(this,
                "¿Está seguro de que desea eliminar al usuario con ID " + idUsuario + "?",
                "Confirmar Eliminación", JOptionPane.YES_NO_OPTION);
        if (confirmacion == JOptionPane.YES_OPTION) {
            controlador.deleteUser(idUsuario);
            JOptionPane.showMessageDialog(this, "Usuario eliminado exitosamente");
            limpiarCampos();
        }
    }

    private void limpiarCampos() {
        idField.setText("");
        errorLabel.setText("");
        usuarioDetalles.setText("");
        eliminarButton.setEnabled(false);
    }

    private void volver() {
        menuUsuario menuUsuario = new menuUsuario(seleccionado, controlador);
        menuUsuario.setVisible(true);
        dispose();
    }
}
