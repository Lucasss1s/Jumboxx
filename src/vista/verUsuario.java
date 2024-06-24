package vista;

import java.awt.Color;
import java.awt.EventQueue;
import java.util.List;
import javax.swing.*;
import javax.swing.border.EmptyBorder;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;
import javax.swing.table.DefaultTableModel;
import controladores.UsuarioControlador;
import Modelos.Usuario;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.time.LocalDate;

public class verUsuario extends JFrame {

    private static final long serialVersionUID = 1L;
    private JPanel contentPane;
    private JTable table;
    private DefaultTableModel model;
    private UsuarioControlador controlador;
    private JLabel elemento;
    private Usuario seleccionado;
    private JTextField inpBuscarID;
    private JLabel lblErrorBusqueda;

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
        contentPane.setBackground(new Color(255, 255, 255));
        contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
        setContentPane(contentPane);

        controlador = new UsuarioControlador();

        String[] columnNames = {"ID", "Nombre Completo", "Usuario", "Puesto", "Fecha de Registro"};
        model = new DefaultTableModel(columnNames, 0);
        table = new JTable(model);
        actualizarTabla();
        contentPane.setLayout(null);

        JScrollPane scrollPane = new JScrollPane(table);
        scrollPane.setBounds(5, 79, 880, 268);
        contentPane.add(scrollPane);

        elemento = new JLabel("Seleccionado:");
        elemento.setBounds(5, 5, 880, 14);
        contentPane.add(elemento);

        JComboBox<String> comboBox = new JComboBox<>();
        comboBox.setBounds(5, 30, 150, 22);
        comboBox.addItem("Administrador");
        comboBox.addItem("Gerente");
        comboBox.addItem("Almacenista");
        contentPane.add(comboBox);

        JButton filtroButton = new JButton("Filtrar por Puesto");
        filtroButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                actualizarPorBusqueda((String) comboBox.getSelectedItem());
            }
        });
        filtroButton.setBounds(170, 30, 140, 22);
        contentPane.add(filtroButton);

        inpBuscarID = new JTextField();
        inpBuscarID.setBounds(622, 30, 100, 22);
        contentPane.add(inpBuscarID);
        inpBuscarID.setColumns(10);

        JButton buscarButton = new JButton("Buscar por ID");
        buscarButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                searchUserByID(inpBuscarID.getText());
            }
        });
        buscarButton.setBounds(732, 30, 140, 22);
        contentPane.add(buscarButton);

        lblErrorBusqueda = new JLabel("");
        lblErrorBusqueda.setForeground(Color.RED);
        lblErrorBusqueda.setBounds(622, 55, 250, 14);
        contentPane.add(lblErrorBusqueda);

        JButton resetButton = new JButton("Resetear");
        resetButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                resetTabla();
            }
        });
        resetButton.setBounds(376, 369, 140, 22);
        contentPane.add(resetButton);
        
        JButton btnNewButton = new JButton("Volver");
        btnNewButton.addActionListener(new ActionListener() {
        	public void actionPerformed(ActionEvent e) {
        		volver();
        	}
        });
        btnNewButton.setBounds(28, 369, 89, 23);
        contentPane.add(btnNewButton);
  
        ListSelectionModel selectionModel = table.getSelectionModel();
        selectionModel.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
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
                        seleccionado = new Usuario(id, nombreCompleto, user, puesto, null, LocalDate.parse(fechaRegistro));
                    }
                }
            }
        });
    }

    private void actualizarTabla() {
        model.setRowCount(0);
        List<Usuario> usuarios = controlador.getAllUsers();
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
        model.setRowCount(0);
        List<Usuario> usuarios = controlador.getAllUsers();
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

    private void searchUserByID(String inputId) {
        try {
            int idUsuario = Integer.parseInt(inputId);
            Usuario usuario = controlador.getUserById(idUsuario);

            if (usuario != null) {
                model.setRowCount(0);
                model.addRow(new Object[]{
                    usuario.getId_usuario(),
                    usuario.getNombreCompleto(),
                    usuario.getUser(),
                    usuario.getPuesto(),
                    usuario.getFechaRegistro().toString()
                });
                lblErrorBusqueda.setText("");
            } else {
                lblErrorBusqueda.setText("Usuario no encontrado. Por favor, ingrese un ID válido");
            }
        } catch (NumberFormatException e) {
            lblErrorBusqueda.setText("ID inválido. Por favor, ingrese un número entero");
        }
    }

    private void resetTabla() {
        lblErrorBusqueda.setText("");
        inpBuscarID.setText("");
        actualizarTabla();
    }
    
    private void volver() {
   	 menuUsuario menuUsuario = new menuUsuario(seleccionado, controlador);
   	 menuUsuario.setVisible(true);
   	 dispose();
   }
}
