package vista;

import java.awt.Color;
import java.awt.EventQueue;
import javax.swing.*;
import javax.swing.border.EmptyBorder;
import controladores.UsuarioControlador;
import Modelos.Usuario;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.time.LocalDate;

public class actualizarUsuario extends JFrame {

    private static final long serialVersionUID = 1L;
    private JPanel contentPane;
    private JTextField idField;
    private JTextField nombreCompletoField;
    private JTextField userField;
    private JComboBox<String> puestoComboBox;
    private JLabel errorLabel;
    private UsuarioControlador controlador;
    private Usuario seleccionado;
    private JButton actualizarButton;

    public static void main(String[] args) {
        EventQueue.invokeLater(new Runnable() {
            public void run() {
                try {
                    actualizarUsuario frame = new actualizarUsuario();
                    frame.setVisible(true);
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        });
    }

    public actualizarUsuario() {
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setBounds(100, 100, 500, 400);
        contentPane = new JPanel();
        contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
        setContentPane(contentPane);
        contentPane.setLayout(null);

        controlador = new UsuarioControlador();

        JLabel lblId = new JLabel("ID del Usuario:");
        lblId.setBounds(25, 53, 120, 20);
        contentPane.add(lblId);

        idField = new JTextField();
        idField.setBounds(155, 53, 200, 20);
        contentPane.add(idField);
        idField.setColumns(10);

        JLabel lblNombreCompleto = new JLabel("Nombre Completo:");
        lblNombreCompleto.setBounds(25, 89, 120, 20);
        contentPane.add(lblNombreCompleto);

        nombreCompletoField = new JTextField();
        nombreCompletoField.setBounds(155, 89, 200, 20);
        nombreCompletoField.setEditable(false);
        contentPane.add(nombreCompletoField);
        nombreCompletoField.setColumns(10);
        nombreCompletoField.addKeyListener(new KeyAdapter() {
            public void keyReleased(KeyEvent e) {
                verificarCambios();
            }
        });

        JLabel lblUser = new JLabel("Nombre de Usuario:");
        lblUser.setBounds(25, 139, 120, 20);
        contentPane.add(lblUser);

        userField = new JTextField();
        userField.setBounds(155, 139, 200, 20);
        userField.setEditable(false);
        contentPane.add(userField);
        userField.setColumns(10);
        userField.addKeyListener(new KeyAdapter() {
            public void keyReleased(KeyEvent e) {
                verificarCambios();
            }
        });

        JLabel lblPuesto = new JLabel("Puesto:");
        lblPuesto.setBounds(25, 189, 120, 20);
        contentPane.add(lblPuesto);

        puestoComboBox = new JComboBox<>();
        puestoComboBox.setBounds(155, 189, 200, 20);
        puestoComboBox.addItem("Almacenista");
        puestoComboBox.addItem("Administrador");
        puestoComboBox.addItem("Gerente");
        puestoComboBox.setEnabled(false);
        contentPane.add(puestoComboBox);
        puestoComboBox.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                verificarCambios();
            }
        });

        errorLabel = new JLabel("");
        errorLabel.setForeground(Color.RED);
        errorLabel.setBounds(25, 223, 330, 20);
        contentPane.add(errorLabel);

        JButton buscarButton = new JButton("Buscar");
        buscarButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                buscarUsuario();
            }
        });
        buscarButton.setBounds(374, 53, 100, 20);
        contentPane.add(buscarButton);

        actualizarButton = new JButton("Actualizar Usuario");
        actualizarButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                actualizarUsuario();
            }
        });
        actualizarButton.setBounds(189, 259, 140, 30);
        actualizarButton.setEnabled(false);
        contentPane.add(actualizarButton);

        JButton volverButton = new JButton("Volver");
        volverButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                volver();
            }
        });
        volverButton.setBounds(25, 259, 100, 30);
        contentPane.add(volverButton);
    }

    private void buscarUsuario() {
        String idStr = idField.getText().trim();
        if (idStr.isEmpty()) {
            errorLabel.setText("ID es obligatorio");
            return;
        }
        try {
            int idUsuario = Integer.parseInt(idStr);
            Usuario usuarioExistente = controlador.getUserById(idUsuario);
            if (usuarioExistente == null) {
                errorLabel.setText("Usuario no encontrado");
                resetFormulario();
                return;
            }
            errorLabel.setText("");
            nombreCompletoField.setText(usuarioExistente.getNombreCompleto());
            userField.setText(usuarioExistente.getUser());
            puestoComboBox.setSelectedItem(usuarioExistente.getPuesto());
            nombreCompletoField.setEditable(true);
            userField.setEditable(true);
            puestoComboBox.setEnabled(true);
            seleccionado = usuarioExistente;
            verificarCambios();
        } catch (NumberFormatException e) {
            errorLabel.setText("ID inválido. Ingrese un número entero");
        }
    }

    private void actualizarUsuario() {
        String nombreCompleto = nombreCompletoField.getText().trim();
        String user = userField.getText().trim();
        String puesto = (String) puestoComboBox.getSelectedItem();
        boolean userExiste;

        if (nombreCompleto.isEmpty()) {
            errorLabel.setText("Nombre Completo es obligatorio");
            return;
        } else {
            errorLabel.setText("");
        }

        if (user.isEmpty()) {
            errorLabel.setText("Nombre de Usuario es obligatorio");
            return;
        } else {
            userExiste = controlador.usernameExists(user);
            if (userExiste && !user.equals(seleccionado.getUser())) {
                errorLabel.setText("El nombre de usuario ya existe");
                return;
            } else {
                errorLabel.setText("");
            }
        }

        seleccionado.setNombreCompleto(nombreCompleto);
        seleccionado.setUser(user);
        seleccionado.setPuesto(puesto);

        controlador.updateUser(seleccionado);
        JOptionPane.showMessageDialog(this, "Usuario actualizado exitosamente");
        resetFormulario();
    }

    private void resetFormulario() {
        idField.setText("");
        nombreCompletoField.setText("");
        userField.setText("");
        errorLabel.setText("");
        puestoComboBox.setSelectedIndex(0);
        nombreCompletoField.setEditable(false);
        userField.setEditable(false);
        puestoComboBox.setEnabled(false);
        actualizarButton.setEnabled(false);
    }

    private void verificarCambios() {
        if (seleccionado != null) {
            String nombreCompleto = nombreCompletoField.getText().trim();
            String user = userField.getText().trim();
            String puesto = (String) puestoComboBox.getSelectedItem();
            boolean cambios = !nombreCompleto.equals(seleccionado.getNombreCompleto()) ||
                              !user.equals(seleccionado.getUser()) ||
                              !puesto.equals(seleccionado.getPuesto());
            actualizarButton.setEnabled(cambios);
        }
    }

    private void volver() {
        menuUsuario menuUsuario = new menuUsuario(seleccionado, controlador);
        menuUsuario.setVisible(true);
        dispose();
    }
}
