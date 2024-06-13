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
import java.awt.Color;
import java.awt.Toolkit;
import java.awt.Font;

public class ProductoTabla extends JFrame {
    private static final long serialVersionUID = 1L;
    private JPanel contentPane;
    private JTable table;
    private DefaultTableModel model;
    private ProductoControlador controlador;
    private JLabel imagenLabel;
    private Producto seleccionado;
    private JTextField searchField;

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
        setIconImage(Toolkit.getDefaultToolkit().getImage(ProductoTabla.class.getResource("/img/Logo 2.png")));
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setBounds(100, 100, 909, 452);
        contentPane = new JPanel();
        contentPane.setBackground(new Color(0, 128, 192));
        contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
        setContentPane(contentPane);

        controlador = new ProductoControlador();
        seleccionado = null;

        String[] columnNames = {"ID", "Nombre", "Precio", "Cantidad"};
        model = new DefaultTableModel(columnNames, 0);
        table = new JTable(model);
        actualizarTabla();
        contentPane.setLayout(null);

        JScrollPane scrollPane = new JScrollPane(table);
        scrollPane.setBounds(10, 42, 600, 300);
        contentPane.add(scrollPane);

        imagenLabel = new JLabel();
        imagenLabel.setBounds(620, 42, 250, 250);
        contentPane.add(imagenLabel);

        // Campo de búsqueda
        searchField = new JTextField();
        searchField.setBounds(110, 353, 305, 25);
        contentPane.add(searchField);
        
        // Botón de búsqueda
        JButton searchButton = new JButton("Buscar");
        searchButton.setBounds(425, 353, 80, 25);
        contentPane.add(searchButton);
        searchButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String searchText = searchField.getText();
                buscarProducto(searchText);
            }
        });

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
                        if (seleccionado != null) {
                            mostrarImagen(seleccionado.getImagen());
                            JOptionPane.showMessageDialog(null, "Producto seleccionado: " + seleccionado.getNombre());
                        } else {
                            imagenLabel.setIcon(null);
                            JOptionPane.showMessageDialog(null, "Producto no encontrado para el ID: " + id);
                        }
                    }
                }
            }
        });

        JButton btnEliminar = new JButton("Eliminar");
        btnEliminar.setBounds(620, 312, 120, 30);
        contentPane.add(btnEliminar);
        btnEliminar.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (seleccionado != null && seleccionado.getId_producto() != 0) {
                    controlador.eliminarProducto(seleccionado.getId_producto());
                    JOptionPane.showMessageDialog(null, "Producto eliminado");
                    actualizarTabla();
                    imagenLabel.setIcon(null);
                    seleccionado = null;
                } else {
                    JOptionPane.showMessageDialog(null, "Seleccione un producto");
                }
            }
        });

        JButton btnEditar = new JButton("Editar");
        btnEditar.setBounds(750, 312, 120, 30);
        contentPane.add(btnEditar);

        JLabel lblNewLabel = new JLabel("Productos");
        lblNewLabel.setFont(new Font("Impact", Font.ITALIC, 15));
        lblNewLabel.setForeground(new Color(255, 255, 255));
        lblNewLabel.setBounds(275, 11, 71, 30);
        contentPane.add(lblNewLabel);
        btnEditar.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (seleccionado != null && seleccionado.getId_producto() != 0) {
                    // Obtener los nuevos valores de la fila seleccionada en la tabla
                    int selectedRow = table.getSelectedRow();
                    if (selectedRow != -1) {
                        int id = (int) table.getValueAt(selectedRow, 0);
                        String nuevoNombre = (String) table.getValueAt(selectedRow, 1);
                        double nuevoPrecio = (double) table.getValueAt(selectedRow, 2);
                        int nuevaCantidad = (int) table.getValueAt(selectedRow, 3);

                        // Actualizar el producto en la base de datos
                        seleccionado.setNombre(nuevoNombre);
                        seleccionado.setPrecio(nuevoPrecio);
                        seleccionado.setCantidad(nuevaCantidad);
                        controlador.actualizarProducto(seleccionado);

                        JOptionPane.showMessageDialog(null, "Producto actualizado en la base de datos");
                        actualizarTabla();
                    }
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

    private void buscarProducto(String nombre) {
        model.setRowCount(0);
        List<Producto> productos = controlador.buscarProductosPorNombre(nombre);
        for (Producto producto : productos) {
            model.addRow(new Object[]{producto.getId_producto(), producto.getNombre(), producto.getPrecio(), producto.getCantidad()});
        }
    }
}
