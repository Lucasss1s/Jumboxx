package vista;

import java.awt.EventQueue;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JButton;
import javax.swing.JTextField;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.List;
import controladores.PedidoControlador;
import Modelos.Pedido;

public class PedidosFrame extends JFrame {
    private JPanel contentPane;
    private JTable table;
    private JTextField textFieldBuscar;
    private DefaultTableModel tableModel;
    private PedidoControlador controlador;

    public static void main(String[] args) {
        EventQueue.invokeLater(new Runnable() {
            public void run() {
                try {
                	PedidosFrame frame = new PedidosFrame();
                    frame.setVisible(true);
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        });
    }

    public PedidosFrame() {
        controlador = new PedidoControlador();
        
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setBounds(100, 100, 600, 400);
        contentPane = new JPanel();
        setContentPane(contentPane);
        contentPane.setLayout(null);

        JScrollPane scrollPane = new JScrollPane();
        scrollPane.setBounds(10, 50, 560, 200);
        contentPane.add(scrollPane);

        tableModel = new DefaultTableModel(new Object[]{"ID", "Cliente", "Productos", "Fecha", "Total", "Estado"}, 0);
        table = new JTable(tableModel);
        scrollPane.setViewportView(table);

        JButton btnMostrar = new JButton("Mostrar");
        btnMostrar.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                mostrarPedidos();
            }
        });
        btnMostrar.setBounds(10, 10, 100, 30);
        contentPane.add(btnMostrar);

        JButton btnGuardar = new JButton("Guardar");
        btnGuardar.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                guardarPedido();
            }
        });
        btnGuardar.setBounds(120, 10, 100, 30);
        contentPane.add(btnGuardar);

        JButton btnEditar = new JButton("Editar");
        btnEditar.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                editarPedido();
            }
        });
        btnEditar.setBounds(230, 10, 100, 30);
        contentPane.add(btnEditar);

        JButton btnEliminar = new JButton("Eliminar");
        btnEliminar.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                eliminarPedido();
            }
        });
        btnEliminar.setBounds(340, 10, 100, 30);
        contentPane.add(btnEliminar);

        JLabel lblBuscar = new JLabel("Buscar:");
        lblBuscar.setBounds(10, 260, 50, 30);
        contentPane.add(lblBuscar);

        textFieldBuscar = new JTextField();
        textFieldBuscar.setBounds(70, 260, 200, 30);
        contentPane.add(textFieldBuscar);
        textFieldBuscar.setColumns(10);

        JButton btnBuscar = new JButton("Buscar");
        btnBuscar.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                buscarPedido();
            }
        });
        btnBuscar.setBounds(280, 260, 100, 30);
        contentPane.add(btnBuscar);
    }

    private void mostrarPedidos() {
        List<Pedido> pedidos = controlador.getAllOrders();
        tableModel.setRowCount(0); // Limpiar tabla
        for (Pedido pedido : pedidos) {
            tableModel.addRow(new Object[]{pedido.getId_Pedido(), pedido.getCliente().getNombre(),
                    pedido.getProductos().toString(), pedido.getFechaPedido(), pedido.getTotal(), pedido.isEstado()});
        }
    }

    private void guardarPedido() {
        // Implementar lógica para guardar pedido
    }

    private void editarPedido() {
        // Implementar lógica para editar pedido
    }

    private void eliminarPedido() {
        int selectedRow = table.getSelectedRow();
        if (selectedRow != -1) {
            int id = (int) tableModel.getValueAt(selectedRow, 0);
            controlador.deletePedido(id);
            mostrarPedidos();
        } else {
            JOptionPane.showMessageDialog(this, "Seleccione un pedido para eliminar.");
        }
    }

    private void buscarPedido() {
        String keyword = textFieldBuscar.getText();
        // Implementar lógica para buscar pedido
    }
}