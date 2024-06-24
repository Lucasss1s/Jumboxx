package vista;

import java.awt.*;
import java.awt.event.*;
import java.util.List;
import javax.swing.*;
import javax.swing.border.EmptyBorder;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;
import javax.swing.table.DefaultTableModel;
import controladores.ProductoControlador;
import Modelos.Producto;

public class ProductoTabla extends JFrame {
    private JPanel contentPane;
    private JTable table;
    private DefaultTableModel model;
    private ProductoControlador controlador;
    private JLabel imagenLabel;
    private Producto seleccionado;
    private JTextField searchField;
    private JFrame menuPrincipal; // Instancia de la ventana principal

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

        searchField = new JTextField();
        searchField.setBounds(110, 353, 305, 25);
        contentPane.add(searchField);

        JButton searchButton = new JButton("Buscar");
        searchButton.setBounds(10, 353, 80, 25);
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
        btnEditar.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (seleccionado != null && seleccionado.getId_producto() != 0) {
                    // Abrir PantallaEditar con el producto seleccionado y pasar la referencia a ProductoTabla
                    PantallaEditar pantallaEditar = new PantallaEditar(seleccionado, ProductoTabla.this);
                    pantallaEditar.setVisible(true);
                } else {
                    JOptionPane.showMessageDialog(null, "Seleccione un producto");
                }
            }
        });

        JButton btnAgregar = new JButton("Agregar Producto");
        btnAgregar.setBounds(620, 353, 250, 30);
        contentPane.add(btnAgregar);
        btnAgregar.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                ProductoForm productoFormFrame = new ProductoForm(ProductoTabla.this);  // Pasar la referencia
                productoFormFrame.setVisible(true);
            }
        });

        JButton btnAtras = new JButton("Atrás");
        btnAtras.setBounds(511, 353, 100, 30);
        contentPane.add(btnAtras);
        btnAtras.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
              	 dispose();
                 MenuPrincipalGerente menuGerente = new MenuPrincipalGerente(null, null);
                 menuGerente.setVisible(true);
            }
        });

        JLabel lblNewLabel = new JLabel("Productos");
        lblNewLabel.setFont(new Font("Impact", Font.ITALIC, 15));
        lblNewLabel.setForeground(new Color(255, 255, 255));
        lblNewLabel.setBounds(275, 11, 71, 30);
        contentPane.add(lblNewLabel);
    }

    // Método para establecer la ventana principal
    public void setMenuPrincipal(JFrame menuPrincipal) {
        this.menuPrincipal = menuPrincipal;
    }

    // Método para actualizar la tabla de productos
    public void actualizarTabla() {
        model.setRowCount(0);
        List<Producto> productos = controlador.getAllProducts();
        for (Producto producto : productos) {
            model.addRow(new Object[]{producto.getId_producto(), producto.getNombre(), producto.getPrecio(), producto.getCantidad()});
        }
    }

    // Método para mostrar la imagen del producto seleccionado
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

    // Método para buscar productos por nombre
    private void buscarProducto(String nombre) {
        model.setRowCount(0);
        List<Producto> productos = controlador.buscarProductosPorNombre(nombre);
        for (Producto producto : productos) {
            model.addRow(new Object[]{producto.getId_producto(), producto.getNombre(), producto.getPrecio(), producto.getCantidad()});
        }
    }
}
