package vista;

import java.awt.BorderLayout;
import java.awt.EventQueue;
import java.util.List;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.border.EmptyBorder;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;
import javax.swing.table.DefaultTableModel;
import controladores.*;
import Modelos.*;
import javax.swing.JTable;
import javax.swing.ListSelectionModel;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.time.LocalDate;
import java.awt.event.ActionEvent;
import javax.swing.JComboBox;

public class verUsuario extends JFrame {

    private static final long serialVersionUID = 1L;
    private JPanel contentPane;
    private JTable table;
    private DefaultTableModel model;
    private UsuarioControlador controlador;
    private JLabel elemento;
    private Usuario seleccionado;
    private JButton buscarButton;

    /**
     * Launch the application.
     */
    public static void main(String[] args) {
        EventQueue.invokeLater(new Runnable() {
            public void run() {
                try {
                    verUsuario frame = new verUsuario();
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
    public verUsuario() {
        this.setVisible(true);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setBounds(100, 100, 909, 452);
        contentPane = new JPanel();
        contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));

        setContentPane(contentPane);

        // Inicializar controlador y usuario seleccionado
        controlador = new UsuarioControlador();

        // Crear la tabla y el modelo
        String[] columnNames = {"ID", "Nombre Completo", "Usuario", "Puesto", "Fecha de Registro"};
        model = new DefaultTableModel(columnNames, 0);
        table = new JTable(model);
        actualizarTabla();
        contentPane.setLayout(null);

        // Crear el JScrollPane y agregar la tabla
        JScrollPane scrollPane = new JScrollPane(table);
        scrollPane.setBounds(5, 64, 911, 190);
        contentPane.add(scrollPane);

        // Crear el JLabel para mostrar la selección
        elemento = new JLabel("Seleccionado:");
        elemento.setBounds(5, 5, 911, 14);
        contentPane.add(elemento);
        
        JComboBox<String> comboBox = new JComboBox<>();
        comboBox.setBounds(5, 30, 357, 22);
        comboBox.addItem("Administrador");
        comboBox.addItem("Gerente");
        comboBox.addItem("Almacenista");
        contentPane.add(comboBox);

        buscarButton = new JButton("Buscar por ID");
        buscarButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                searchUserByID();
            }
        });
        buscarButton.setBounds(362, 311, 187, 58);
        contentPane.add(buscarButton);

        JButton filtroButton = new JButton("Filtrar por Puesto");
        filtroButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                actualizarPorBusqueda((String)comboBox.getSelectedItem());
            }
        });
        filtroButton.setBounds(372, 26, 140, 30);
        contentPane.add(filtroButton);

        // Configurar el modelo de selección
        ListSelectionModel selectionModel = table.getSelectionModel();
        selectionModel.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);

        // Agregar un escuchador de selección
        selectionModel.addListSelectionListener(new ListSelectionListener() {
            @Override
            public void valueChanged(ListSelectionEvent e) {
                if (!e.getValueIsAdjusting()) {
                    int selectedRow = table.getSelectedRow();
                    if (selectedRow != -1) {
                        int id = (int) table.getValueAt(selectedRow, 0);
                        String nombreCompleto = (String) table.getValueAt(selectedRow, 1);
                        String user = (String) table.getValueAt(selectedRow, 2);
                        String puesto = (String) table.getValueAt(selectedRow, 3);
                        String fechaRegistro = (String) table.getValueAt(selectedRow, 4);
                        elemento.setText("Seleccionado: ID=" + id + ", Nombre Completo=" + nombreCompleto + ", Usuario=" + user + ", Puesto=" + puesto + ", Fecha de Registro=" + fechaRegistro);
                        seleccionado.setId_usuario(id);
                        seleccionado.setNombreCompleto(nombreCompleto);
                        seleccionado.setUser(user);
                        seleccionado.setPuesto(puesto);
                        seleccionado.setFechaRegistro(LocalDate.parse(fechaRegistro));
                    }
                }
            }
        });
    }

    private void actualizarTabla() {
        // Limpiar el modelo de la tabla
        model.setRowCount(0);

        // Obtener la lista actualizada de usuarios
        List<Usuario> usuarios = controlador.getAllUsers();

        // Agregar los datos al modelo
        for (Usuario usuario : usuarios) {
            model.addRow(new Object[]{
                usuario.getId_usuario(),
                usuario.getNombreCompleto(),
                usuario.getUser(),
                usuario.getPuesto(),
                usuario.getFechaRegistro().toString()
            });
        }
    }
    
    private void actualizarPorBusqueda(String criterio) {
        // Limpiar el modelo de la tabla
        model.setRowCount(0);

        // Obtener la lista actualizada de usuarios
        List<Usuario> usuarios = controlador.getAllUsers();

        // Agregar los datos al modelo
        for (Usuario usuario : usuarios) {
            if (usuario.getPuesto().equals(criterio)) {
                model.addRow(new Object[]{
                    usuario.getId_usuario(),
                    usuario.getNombreCompleto(),
                    usuario.getUser(),
                    usuario.getPuesto(),
                    usuario.getFechaRegistro().toString()
                });
            }
        }
    }

    private void searchUserByID() {
        while (true) {
            try {
                String inputId = Usuario.pedirInputNoVacio("Ingrese el ID del usuario:");
                int idUsuario = Integer.parseInt(inputId);

                Usuario usuario = controlador.getUserById(idUsuario);

                if (usuario != null) {
                    StringBuilder usuarioTexto = new StringBuilder();
                    usuarioTexto.append("ID: ").append(usuario.getId_usuario()).append("\n")
                            .append("Nombre Completo: ").append(usuario.getNombreCompleto()).append("\n")
                            .append("Usuario: ").append(usuario.getUser()).append("\n")
                            .append("Puesto: ").append(usuario.getPuesto()).append("\n")
                            .append("Fecha de Registro: ").append(usuario.getFechaRegistro()).append("\n");

                    JOptionPane.showMessageDialog(null, usuarioTexto.toString(), "Usuario Encontrado",
                            JOptionPane.INFORMATION_MESSAGE);
                    break; // Salir del bucle si se encuentra el usuario
                } else {
                    JOptionPane.showMessageDialog(null, "Usuario no encontrado. Por favor, ingrese un ID válido",
                            "Error", JOptionPane.ERROR_MESSAGE);
                    break;
                }
            } catch (NumberFormatException e) {
                JOptionPane.showMessageDialog(null, "ID inválido. Por favor, ingrese un número entero", "Error",
                        JOptionPane.ERROR_MESSAGE);
                break;
            }
        }
    }
}
