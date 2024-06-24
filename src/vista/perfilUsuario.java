package vista;

import java.awt.Color;
import java.awt.EventQueue;
import java.awt.Font;
import javax.swing.*;
import javax.swing.border.EmptyBorder;
import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;

import controladores.UsuarioControlador;
import Modelos.Usuario;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class perfilUsuario extends JFrame {

    private static final long serialVersionUID = 1L;
    private JPanel contentPane;
    private JLabel nombreCompletoLabel;
    private JLabel usuarioLabel;
    private JLabel puestoLabel;
    private JTextField nuevoNombreUsuarioField;
    private JPasswordField contraseñaActualField;
    private JPasswordField nuevaContraseñaField;
    private JLabel errorLabel;
    private Usuario usuarioActual;
    private UsuarioControlador controlador;
    private JButton cambiarNombreButton;
    private JButton cambiarContraseñaButton;
    private JButton atrasButton;

    public static void main(String[] args) {
        EventQueue.invokeLater(new Runnable() {
            public void run() {
                try {
                    UsuarioControlador controlador = new UsuarioControlador();
                    Usuario usuarioActual = null ; 
                    perfilUsuario frame = new perfilUsuario(controlador, usuarioActual);
                    frame.setVisible(true);
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        });
    }

    public perfilUsuario(UsuarioControlador controlador, Usuario usuarioActual) {
        this.controlador = controlador;
        this.usuarioActual = usuarioActual;

        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setBounds(100, 100, 500, 400);
        contentPane = new JPanel();
        contentPane.setBackground(new Color(0, 128, 192));
        contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
        setContentPane(contentPane);
        contentPane.setLayout(null);

        JLabel lblPerfil = new JLabel("Perfil del Usuario");
        lblPerfil.setFont(new Font("Tahoma", Font.BOLD, 16));
        lblPerfil.setBounds(164, 7, 148, 30);
        contentPane.add(lblPerfil);

        nombreCompletoLabel = new JLabel("Nombre Completo: " + usuarioActual.getNombreCompleto());
        nombreCompletoLabel.setBounds(50, 48, 400, 20);
        contentPane.add(nombreCompletoLabel);

        usuarioLabel = new JLabel("Usuario: " + usuarioActual.getUser());
        usuarioLabel.setBounds(50, 78, 400, 20);
        contentPane.add(usuarioLabel);

        puestoLabel = new JLabel("Puesto: " + usuarioActual.getPuesto());
        puestoLabel.setBounds(50, 108, 400, 20);
        contentPane.add(puestoLabel);

        JLabel lblNuevoNombreUsuario = new JLabel("Nuevo Nombre de Usuario:");
        lblNuevoNombreUsuario.setBounds(50, 148, 200, 20);
        contentPane.add(lblNuevoNombreUsuario);

        nuevoNombreUsuarioField = new JTextField();
        nuevoNombreUsuarioField.setBounds(250, 148, 200, 20);
        contentPane.add(nuevoNombreUsuarioField);
        nuevoNombreUsuarioField.setColumns(10);

        cambiarNombreButton = new JButton("Cambiar Nombre de Usuario");
        cambiarNombreButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                cambiarNombreUsuario();
            }
        });
        cambiarNombreButton.setBounds(150, 178, 200, 30);
        cambiarNombreButton.setEnabled(false);
        contentPane.add(cambiarNombreButton);

        JLabel lblContraseñaActual = new JLabel("Contraseña Actual:");
        lblContraseñaActual.setBounds(50, 228, 200, 20);
        contentPane.add(lblContraseñaActual);

        contraseñaActualField = new JPasswordField();
        contraseñaActualField.setBounds(250, 228, 200, 20);
        contentPane.add(contraseñaActualField);

        JLabel lblNuevaContraseña = new JLabel("Nueva Contraseña:");
        lblNuevaContraseña.setBounds(50, 258, 200, 20);
        contentPane.add(lblNuevaContraseña);

        nuevaContraseñaField = new JPasswordField();
        nuevaContraseñaField.setBounds(250, 258, 200, 20);
        contentPane.add(nuevaContraseñaField);

        cambiarContraseñaButton = new JButton("Cambiar Contraseña");
        cambiarContraseñaButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                cambiarContraseña();
            }
        });
        cambiarContraseñaButton.setBounds(150, 289, 200, 30);
        cambiarContraseñaButton.setEnabled(false);
        contentPane.add(cambiarContraseñaButton);

        errorLabel = new JLabel("");
        errorLabel.setForeground(Color.RED);
        errorLabel.setBounds(50, 340, 400, 20);
        contentPane.add(errorLabel);

        atrasButton = new JButton("Atrás");
        atrasButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                dispose(); 
            	if (usuarioActual.getPuesto().equalsIgnoreCase("Administrador")) {
					MenuPrincipalAdministrador MenuPrincipalAdministrador = new MenuPrincipalAdministrador(usuarioActual, controlador);
					MenuPrincipalAdministrador.setVisible(true);
				}
            	if (usuarioActual.getPuesto().equalsIgnoreCase("Almacenista")) {
            		MenuPrincipalAlmacenista MenuPrincipalAlmacenista = new MenuPrincipalAlmacenista(usuarioActual, controlador);
            		MenuPrincipalAlmacenista.setVisible(true);
				}
            }
        });
        atrasButton.setBounds(10, 330, 80, 30);
        contentPane.add(atrasButton);

        // Añadir listeners para habilitar/deshabilitar botones
        nuevoNombreUsuarioField.getDocument().addDocumentListener(new DocumentListener() {
            public void insertUpdate(DocumentEvent e) { habilitarBoton(); }
            public void removeUpdate(DocumentEvent e) { habilitarBoton(); }
            public void changedUpdate(DocumentEvent e) { habilitarBoton(); }
        });

        contraseñaActualField.getDocument().addDocumentListener(new DocumentListener() {
            public void insertUpdate(DocumentEvent e) { habilitarBoton(); }
            public void removeUpdate(DocumentEvent e) { habilitarBoton(); }
            public void changedUpdate(DocumentEvent e) { habilitarBoton(); }
        });

        nuevaContraseñaField.getDocument().addDocumentListener(new DocumentListener() {
            public void insertUpdate(DocumentEvent e) { habilitarBoton(); }
            public void removeUpdate(DocumentEvent e) { habilitarBoton(); }
            public void changedUpdate(DocumentEvent e) { habilitarBoton(); }
        });
    }

    private void habilitarBoton() {
        cambiarNombreButton.setEnabled(!nuevoNombreUsuarioField.getText().trim().isEmpty());
        cambiarContraseñaButton.setEnabled(
                contraseñaActualField.getPassword().length > 0 && 
                nuevaContraseñaField.getPassword().length > 0);
    }

    private void cambiarNombreUsuario() {
        String nuevoNombreUsuario = nuevoNombreUsuarioField.getText().trim();
        if (nuevoNombreUsuario.isEmpty()) {
            mostrarError("El nombre de usuario no puede estar vacío.", nuevoNombreUsuarioField);
            return;
        }

        boolean nuevoUserExiste = controlador.usernameExists(nuevoNombreUsuario);
        if (nuevoUserExiste) {
            mostrarError("El nombre de usuario ingresado ya existe. Por favor, elija otro nombre de usuario.", nuevoNombreUsuarioField);
        } else {
            usuarioActual.setUser(nuevoNombreUsuario);
            controlador.updateUser(usuarioActual);
            mostrarExito("Nombre de usuario actualizado exitosamente.");
            usuarioLabel.setText("Usuario: " + nuevoNombreUsuario);
            nuevoNombreUsuarioField.setText(""); // Limpiar campo
        }
    }

    private void cambiarContraseña() {
        String contraseñaActual = new String(contraseñaActualField.getPassword()).trim();
        if (!usuarioActual.getContraseña().equals(contraseñaActual)) {
            mostrarError("Contraseña incorrecta. Por favor, intente de nuevo.", contraseñaActualField);
            return;
        }

        String nuevaContraseña = new String(nuevaContraseñaField.getPassword()).trim();
        if (nuevaContraseña.length() < 3) {
            mostrarError("La nueva contraseña debe tener al menos 3 caracteres.", nuevaContraseñaField);
        } else if (nuevaContraseña.equals(usuarioActual.getContraseña())) {
            mostrarError("La nueva contraseña no puede ser igual a la contraseña actual.", nuevaContraseñaField);
        } else {
            usuarioActual.setContraseña(nuevaContraseña);
            controlador.updateUser(usuarioActual);
            mostrarExito("Contraseña actualizada exitosamente.");
            contraseñaActualField.setText(""); // Limpiar campo
            nuevaContraseñaField.setText(""); // Limpiar campo
        }
    }

    private void mostrarError(String mensaje, JComponent campo) {
        errorLabel.setText(mensaje);
        campo.setBorder(BorderFactory.createLineBorder(Color.RED));
    }

    private void mostrarExito(String mensaje) {
        errorLabel.setText("");
        JOptionPane.showMessageDialog(this, mensaje, "Éxito", JOptionPane.INFORMATION_MESSAGE);
        nuevoNombreUsuarioField.setBorder(BorderFactory.createLineBorder(Color.GRAY));
        contraseñaActualField.setBorder(BorderFactory.createLineBorder(Color.GRAY));
        nuevaContraseñaField.setBorder(BorderFactory.createLineBorder(Color.GRAY));
    }
}
