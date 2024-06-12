package vista;

import java.awt.BorderLayout;
import java.awt.EventQueue;
import java.awt.Image;
import java.util.List;
import javax.swing.*;
import javax.swing.border.EmptyBorder;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;
import javax.swing.table.DefaultTableModel;
import controladores.ProductoControlador;
import Modelos.Producto;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class ProductoTabla extends JFrame {
    private static final long serialVersionUID = 1L;
    private JPanel contentPane;
    private JTable table;
    private DefaultTableModel model;
    private ProductoControlador controlador;
    private JLabel imagenLabel;
    private Producto seleccionado;
    private JTextField filtrar;

    public static void main(String[] args) {
        EventQueue.invokeLater(new Runnable() {
            public void run() {
                try {
                    ProductoTabla frame = new ProductoTabla();
                    frame.setVisible(true);
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        });
    }

    public ProductoTabla() {
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setBounds(100, 100, 909, 452);
        contentPane = new JPanel();
        contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
        setContentPane(contentPane);

        controlador = new ProductoControlador();
        seleccionado = new Producto(0, "", 0, null, 0);

        String[] columnNames = {"ID", "Nombre", "Precio", "Cantidad"};
        model = new DefaultTableModel(columnNames, 0);
        table = new JTable(model);
        actualizarTabla();
        contentPane.setLayout(null);

        JScrollPane scrollPane = new JScrollPane(table);
        scrollPane.setBounds(5, 5, 600, 300);
        contentPane.add(scrollPane);

        imagenLabel = new JLabel();
        imagenLabel.setBounds(620, 5, 250, 250);
        contentPane.add(imagenLabel);

        ListSelectionModel selectionModel = table.getSelectionModel();
        selectionModel.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);

        selectionModel.addListSelectionListener(new ListSelectionListener() {
            @Override
            public void valueChanged(ListSelectionEvent e) {
                if (!e.getValueIsAdjusting()) {
                    int selectedRow = table.getSelectedRow();
                    if (selectedRow != -1) {
                        int id = (int) table.getValueAt(selectedRow, 0);
                        seleccionado = controlador.getProductById(id);
                        mostrarImagen(seleccionado.getImagen());
                    }
                }
            }
        });

        JButton btnEliminar = new JButton("Eliminar");
        btnEliminar.setBounds(620, 270, 120, 30);
        contentPane.add(btnEliminar);
        btnEliminar.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (seleccionado.getId_producto() != 0) {
                    controlador.eliminarProducto(seleccionado.getId_producto());
                    JOptionPane.showMessageDialog(null, "Producto eliminado");
                    actualizarTabla();
                    imagenLabel.setIcon(null);
                } else {
                    JOptionPane.showMessageDialog(null, "Seleccione un producto");
                }
            }
        });

        JButton btnEditar = new JButton("Editar");
        btnEditar.setBounds(750, 270, 120, 30);
        contentPane.add(btnEditar);
        
        filtrar = new JTextField();
        filtrar.setBounds(15, 316, 86, 20);
        contentPane.add(filtrar);
        filtrar.setColumns(10);
        
        JLabel lblNewLabel = new JLabel("Criterio");
        lblNewLabel.setBounds(127, 319, 62, 14);
        contentPane.add(lblNewLabel);
        
        JButton btnNewButton = new JButton("Filtrar");
        btnNewButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                Filtrar(filtrar.getText());
            }
        });
        btnNewButton.setBounds(238, 316, 89, 23);
        contentPane.add(btnNewButton);
        btnEditar.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (seleccionado.getId_producto() != 0) {
                    // Aquí puedes llamar a tu ventana de edición, pasando el producto seleccionado
                    // new EditarProducto(seleccionado).setVisible(true);
                    JOptionPane.showMessageDialog(null, "Funcionalidad de editar aún no implementada");
                } else {
                    JOptionPane.showMessageDialog(null, "Seleccione un producto");
                }
            }
        });
    }

    private void actualizarTabla() {
        model.setRowCount(0);
        List<Producto> productos = controlador.getAllProducts();
        for (Producto producto : productos) {
            model.addRow(new Object[]{producto.getId_producto(), producto.getNombre(), producto.getPrecio(), producto.getCantidad()});
        }
    }

    private void Filtrar(String criterio) {
        model.setRowCount(0);
        List<Producto> productos = controlador.getAllProducts();
        for (Producto producto : productos) {
            if (producto.getNombre().contains(criterio)) {
                model.addRow(new Object[]{producto.getId_producto(), producto.getNombre(), producto.getPrecio(), producto.getCantidad()});
            }
        }
    }

    private void mostrarImagen(byte[] imagen) {
        if (imagen != null) {
            ImageIcon icon = new ImageIcon(imagen);
            Image img = icon.getImage();
            Image scaledImg = img.getScaledInstance(imagenLabel.getWidth(), imagenLabel.getHeight(), Image.SCALE_SMOOTH);
            imagenLabel.setIcon(new ImageIcon(scaledImg));
        } else {
            imagenLabel.setIcon(null);
        }
    }
}
