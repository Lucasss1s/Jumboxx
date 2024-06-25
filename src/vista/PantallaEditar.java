package vista;

import javax.swing.*;
import controladores.ProductoControlador;
import Modelos.Producto;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;

public class PantallaEditar extends JFrame {

    private JTextField nombreField;
    private JTextField precioField;
    private JLabel imagenLabel;
    private byte[] imagenData;
    private JLabel label_3;
    private JTextField inpCantidad;
    private Producto seleccionado;
    private ProductoTabla productoTabla;

    public PantallaEditar(Producto seleccionado, ProductoTabla productoTabla) {
        this.seleccionado = seleccionado;
        this.productoTabla = productoTabla;

        setIconImage(Toolkit.getDefaultToolkit().getImage(PantallaEditar.class.getResource("/img/Logo 2.png")));
        getContentPane().setBackground(new Color(0, 128, 192));
        setTitle("Editar Producto");
        setSize(479, 500);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setLocationRelativeTo(null);

        nombreField = new JTextField(seleccionado.getNombre());
        nombreField.setBounds(73, 6, 191, 40);
        precioField = new JTextField(String.valueOf(seleccionado.getPrecio()));
        precioField.setBounds(73, 61, 191, 40);
        imagenLabel = new JLabel();
        imagenLabel.setForeground(new Color(255, 255, 255));
        imagenLabel.setBackground(new Color(255, 255, 255));
        imagenLabel.setBounds(73, 112, 191, 164);

        JButton seleccionarImagenBtn = new JButton("Seleccionar Imagen");
        seleccionarImagenBtn.setBounds(33, 338, 125, 50);
        seleccionarImagenBtn.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                seleccionarImagen();
            }
        });

        JButton guardarBtn = new JButton("Guardar Cambios");
        guardarBtn.setBounds(168, 338, 137, 50);
        guardarBtn.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                editarProducto();
            }
        });
        getContentPane().setLayout(null);

        JLabel label = new JLabel("Nombre:");
        label.setFont(new Font("Impact", Font.ITALIC, 11));
        label.setForeground(new Color(255, 255, 255));
        label.setBounds(10, 1, 185, 50);
        getContentPane().add(label);
        getContentPane().add(nombreField);
        JLabel label_1 = new JLabel("Precio:");
        label_1.setFont(new Font("Impact", Font.ITALIC, 11));
        label_1.setForeground(new Color(255, 255, 255));
        label_1.setBackground(new Color(0, 128, 192));
        label_1.setBounds(10, 61, 185, 40);
        getContentPane().add(label_1);
        getContentPane().add(precioField);
        JLabel label_2 = new JLabel("Imagen:");
        label_2.setForeground(new Color(255, 255, 255));
        label_2.setFont(new Font("Impact", Font.ITALIC, 11));
        label_2.setBounds(10, 112, 185, 40);
        getContentPane().add(label_2);
        getContentPane().add(imagenLabel);
        getContentPane().add(seleccionarImagenBtn);
        getContentPane().add(guardarBtn);
        
        label_3 = new JLabel("Cantidad:");
        label_3.setForeground(new Color(255, 255, 255));
        label_3.setFont(new Font("Impact", Font.ITALIC, 11));
        label_3.setBackground(new Color(0, 128, 192));
        label_3.setBounds(10, 287, 185, 40);
        getContentPane().add(label_3);
        
        inpCantidad = new JTextField(String.valueOf(seleccionado.getCantidad()));
        inpCantidad.setBounds(73, 287, 191, 40);
        getContentPane().add(inpCantidad);

        JButton btnDescuento15 = new JButton("15% Descuento");
        btnDescuento15.setBounds(280, 61, 150, 40);
        btnDescuento15.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                aplicarDescuento(0.15);
            }
        });
        getContentPane().add(btnDescuento15);

        JButton btnDescuento30 = new JButton("30% Descuento");
        btnDescuento30.setBounds(280, 112, 150, 40);
        btnDescuento30.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                aplicarDescuento(0.30);
            }
        });
        getContentPane().add(btnDescuento30);

        JButton btnDescuento50 = new JButton("50% Descuento");
        btnDescuento50.setBounds(280, 163, 150, 40);
        btnDescuento50.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                aplicarDescuento(0.50);
            }
        });
        getContentPane().add(btnDescuento50);

        // Mostrar la imagen actual del producto si existe
        mostrarImagen(seleccionado.getImagen());
    }

    private void seleccionarImagen() {
        JFileChooser fileChooser = new JFileChooser();
        int result = fileChooser.showOpenDialog(this);
        if (result == JFileChooser.APPROVE_OPTION) {
            File file = fileChooser.getSelectedFile();
            imagenLabel.setText(file.getName());
            imagenData = leerImagen(file);
        }
    }

    private byte[] leerImagen(File file) {
        byte[] bFile = new byte[(int) file.length()];
        try (FileInputStream fileInputStream = new FileInputStream(file)) {
            fileInputStream.read(bFile);
        } catch (IOException e) {
            e.printStackTrace();
        }
        return bFile;
    }

    private void editarProducto() {
        String nombre = nombreField.getText();
        double precio = Double.parseDouble(precioField.getText());
        int cantidad = Integer.parseInt(inpCantidad.getText());
        Producto producto = new Producto(seleccionado.getId_producto(), nombre, cantidad, imagenData, precio);
        ProductoControlador controlador = new ProductoControlador();
        controlador.actualizarProducto(producto);

        JOptionPane.showMessageDialog(this, "Producto editado exitosamente");

        // Actualizar la tabla
        productoTabla.actualizarTabla();
        
        // Cerrar la ventana de editar producto
        dispose();
    }

    private void aplicarDescuento(double porcentaje) {
        double precioActual = Double.parseDouble(precioField.getText());
        double nuevoPrecio = precioActual - (precioActual * porcentaje);
        precioField.setText(String.format("%.2f", nuevoPrecio));
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
        JButton btnAtras = new JButton("Atrás");
        btnAtras.setBounds(331, 351, 102, 37);
        getContentPane().add(btnAtras);
        btnAtras.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                dispose();
                if (productoTabla != null) {
                    productoTabla.setVisible(true);
                }
            }
        });
    }
}
