package vista;

import java.awt.EventQueue;

import javax.swing.JButton;
import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import Modelos.Producto;

import javax.swing.JLabel;
import javax.swing.JTextField;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.awt.Color;

public class PantallaEditar extends JFrame {

	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	private JTextField textField;
	private JTextField textField_1;
	private JTextField textField_2;
	private JTextField textField_3;

	public PantallaEditar(Producto producto) {
		this.setVisible(true);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 450, 300);
		contentPane = new JPanel();
		contentPane.setBackground(new Color(0, 128, 192));
		contentPane.setForeground(new Color(0, 0, 0));
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));

		setContentPane(contentPane);
		contentPane.setLayout(null);

		JLabel lblNewLabel = new JLabel("Descripción Producto");
		lblNewLabel.setForeground(new Color(255, 255, 255));
		lblNewLabel.setFont(new Font("Impact", Font.ITALIC, 16));
		lblNewLabel.setBounds(146, 0, 149, 27);
		contentPane.add(lblNewLabel);

		textField = new JTextField();
		textField.setBounds(165, 49, 209, 20);
		textField.setText(producto.getNombre());
		contentPane.add(textField);
		textField.setColumns(10);

		JLabel lblNombre = new JLabel("Nombre");
		lblNombre.setForeground(new Color(255, 255, 255));
		lblNombre.setFont(new Font("Impact", Font.ITALIC, 16));
		lblNombre.setBounds(33, 52, 86, 17);
		contentPane.add(lblNombre);

		JLabel lblCantidad = new JLabel("Cantidad");
		lblCantidad.setForeground(Color.WHITE);
		lblCantidad.setFont(new Font("Impact", Font.ITALIC, 16));
		lblCantidad.setBounds(33, 99, 86, 17);
		contentPane.add(lblCantidad);

		JLabel lblPrecio = new JLabel("Precio");
		lblPrecio.setForeground(Color.WHITE);
		lblPrecio.setFont(new Font("Impact", Font.ITALIC, 16));
		lblPrecio.setBounds(33, 146, 86, 17);
		contentPane.add(lblPrecio);

		JLabel lblImagen = new JLabel("Imagen");
		lblImagen.setForeground(Color.WHITE);
		lblImagen.setFont(new Font("Impact", Font.ITALIC, 16));
		lblImagen.setBounds(33, 198, 86, 17);
		contentPane.add(lblImagen);

		// Campo de texto para la cantidad
		textField = new JTextField();
		textField.setText(String.valueOf(producto.getCantidad()));
		textField.setColumns(10);
		textField.setBounds(165, 96, 209, 20);
		contentPane.add(textField);

		// Campo de texto para el precio
		textField = new JTextField();
		textField.setText(String.valueOf(producto.getPrecio()));
		textField.setColumns(10);
		textField.setBounds(165, 143, 209, 20);
		contentPane.add(textField);

		// Campo de texto para la imagen (ruta del archivo)
		textField = new JTextField();
		textField.setText((String) null);
		textField.setColumns(10);
		textField.setBounds(165, 195, 140, 20);
		contentPane.add(textField);

		// Botón para seleccionar la imagen
		JButton btnSeleccionarImagen = new JButton("Seleccionar");
		btnSeleccionarImagen.setBounds(315, 195, 105, 20);
		btnSeleccionarImagen.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				JFileChooser chooser = new JFileChooser();
				int result = chooser.showOpenDialog(null);
				if (result == JFileChooser.APPROVE_OPTION) {
					File file = chooser.getSelectedFile();
					String filename = file.getAbsolutePath();
					textField.setText(filename);
				}
			}
		});
		contentPane.add(btnSeleccionarImagen);

	}

}