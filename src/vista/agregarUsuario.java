package vista;

import java.awt.Color;
import java.awt.EventQueue;
import javax.swing.*;
import javax.swing.border.EmptyBorder;
import controladores.UsuarioControlador;
import Modelos.Usuario;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.time.LocalDate;

public class agregarUsuario extends JFrame {

    private static final long serialVersionUID = 1L;
    private JPanel contentPane;
    private JTextField nombreCompletoField;
    private JTextField userField;
    private JComboBox<String> puestoComboBox;
    private JLabel errorLabelNombre;
    private JLabel errorLabelUser;
    private UsuarioControlador controlador;
    private Usuario seleccionado;

    /**
     * Launch the application.
     */
    public static void main(String[] args) {
        EventQueue.invokeLater(new Runnable() {
            public void run() {
                try {
                    agregarUsuario frame = new agregarUsuario();
                    frame.setVisible(true);
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        });
    }

    /**
     * Create the frame.
     */
    public agregarUsuario() {
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setBounds(100, 100, 500, 400);
        contentPane = new JPanel();
        contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
        setContentPane(contentPane);
        contentPane.setLayout(null);

        controlador = new UsuarioControlador();

        JLabel lblNombreCompleto = new JLabel("Nombre Completo:");
        lblNombreCompleto.setBounds(83, 66, 120, 20);
        contentPane.add(lblNombreCompleto);

        nombreCompletoField = new JTextField();
        nombreCompletoField.setBounds(213, 66, 200, 20);
        contentPane.add(nombreCompletoField);
        nombreCompletoField.setColumns(10);

        errorLabelNombre = new JLabel("");
        errorLabelNombre.setForeground(Color.RED);
        errorLabelNombre.setBounds(213, 86, 200, 20);
        contentPane.add(errorLabelNombre);

        JLabel lblUser = new JLabel("Nombre de Usuario:");
        lblUser.setBounds(83, 116, 120, 20);
        contentPane.add(lblUser);

        userField = new JTextField();
        userField.setBounds(213, 116, 200, 20);
        contentPane.add(userField);
        userField.setColumns(10);

        errorLabelUser = new JLabel("");
        errorLabelUser.setForeground(Color.RED);
        errorLabelUser.setBounds(213, 136, 200, 20);
        contentPane.add(errorLabelUser);

        JLabel lblPuesto = new JLabel("Puesto:");
        lblPuesto.setBounds(83, 166, 120, 20);
        contentPane.add(lblPuesto);

        puestoComboBox = new JComboBox<>();
        puestoComboBox.setBounds(213, 166, 200, 20);
        puestoComboBox.addItem("Almacenista");
        puestoComboBox.addItem("Administrador");
        puestoComboBox.addItem("Gerente");
        contentPane.add(puestoComboBox);

        JButton agregarButton = new JButton("Agregar Usuario");
        agregarButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                agregarUsuario();
            }
        });
        agregarButton.setBounds(247, 236, 140, 30);
        contentPane.add(agregarButton);

        JButton volverButton = new JButton("Volver");
        volverButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                volver();
            }
        });
        volverButton.setBounds(83, 236, 100, 30);
        contentPane.add(volverButton);
    }

    private void agregarUsuario() {
        String nombreCompleto = nombreCompletoField.getText().trim();
        String user = userField.getText().trim();
        String puesto = (String) puestoComboBox.getSelectedItem();
        boolean userExiste;

        if (nombreCompleto.isEmpty()) {
            errorLabelNombre.setText("Nombre Completo es obligatorio");
            return;
        } else {
            errorLabelNombre.setText("");
        }

        if (user.isEmpty()) {
            errorLabelUser.setText("Nombre de Usuario es obligatorio");
            return;
        } else {
            userExiste = controlador.usernameExists(user);
            if (userExiste) {
                errorLabelUser.setText("El nombre de usuario ya existe");
                return;
            } else {
                errorLabelUser.setText("");
            }
        }

        int id_usuario = controlador.getLastUserId() + 1;
        LocalDate fechaRegistro = LocalDate.now();
        String contraseña = user;

        controlador.addUser(new Usuario(id_usuario, nombreCompleto, user, contraseña, puesto, fechaRegistro));
        JOptionPane.showMessageDialog(this, "Usuario agregado exitosamente");
        resetFormulario();
    }

    private void resetFormulario() {
        nombreCompletoField.setText("");
        userField.setText("");
        errorLabelNombre.setText("");
        errorLabelUser.setText("");
        puestoComboBox.setSelectedIndex(0);
    }

    private void volver() {
        menuUsuario menuUsuario = new menuUsuario(seleccionado, controlador);
        menuUsuario.setVisible(true);
        dispose();
    }
}
