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

public class ProductoForm extends JFrame {
    
    private JTextField nombreField;
    private JTextField precioField;
    private JLabel imagenLabel;
    private byte[] imagenData;
    private JLabel label_3;
    private JTextField inpCantidad;
    private ProductoTabla productoTabla;  // Referencia a ProductoTabla

    public ProductoForm(ProductoTabla productoTabla) {  // Modificar el constructor
        this.productoTabla = productoTabla;  // Asignar la referencia

        setIconImage(Toolkit.getDefaultToolkit().getImage(ProductoForm.class.getResource("/img/Logo 2.png")));
        getContentPane().setBackground(new Color(0, 128, 192));
        setTitle("Agregar Producto");
        setSize(481, 358);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);

        nombreField = new JTextField();
        nombreField.setBounds(73, 6, 191, 40);
        precioField = new JTextField();
        precioField.setBounds(73, 61, 191, 40);
        imagenLabel = new JLabel();
        imagenLabel.setForeground(new Color(255, 255, 255));
        imagenLabel.setBackground(new Color(255, 255, 255));
        imagenLabel.setBounds(73, 112, 191, 40);

        JButton seleccionarImagenBtn = new JButton("Seleccionar Imagen");
        seleccionarImagenBtn.setBounds(32, 247, 185, 50);
        seleccionarImagenBtn.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                seleccionarImagen();
            }
        });

        JButton guardarBtn = new JButton("Guardar Producto");
        guardarBtn.setBounds(246, 247, 185, 50);
        guardarBtn.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                guardarProducto();
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
        label_3.setBounds(10, 177, 185, 40);
        getContentPane().add(label_3);
        
        inpCantidad = new JTextField();
        inpCantidad.setBounds(73, 177, 191, 40);
        getContentPane().add(inpCantidad);
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

    private void guardarProducto() {
        String nombre = nombreField.getText();
        double precio = Double.parseDouble(precioField.getText());
        int cantidad = Integer.parseInt(inpCantidad.getText());
        Producto producto = new Producto(0, nombre, cantidad, imagenData, precio);
        ProductoControlador controlador = new ProductoControlador();
        controlador.agregarProducto(producto);

        JOptionPane.showMessageDialog(this, "Producto guardado exitosamente");

        // Actualizar la tabla
        productoTabla.actualizarTabla();
        
        // Cerrar la ventana de agregar producto
        dispose();
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(new Runnable() {
            @Override
            public void run() {
                new ProductoForm(null).setVisible(true);  // Pasa null si no hay instancia de ProductoTabla
            }
        });
    }
}
