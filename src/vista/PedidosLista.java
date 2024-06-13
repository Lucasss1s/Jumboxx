package vista;

import java.awt.Color;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JLabel;
import java.awt.Font;
import javax.swing.JTextField;
import javax.swing.JButton;
import javax.swing.JTable;
import javax.swing.JScrollPane;
import javax.swing.table.DefaultTableModel;

import Modelos.Pedido;

import java.util.List;
import controladores.PedidoControlador;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class PedidosLista extends JFrame {

	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	private JTextField textField;
	private JTextField textField_1;
	private DefaultTableModel model;
	private PedidoControlador controlador;

	/**
	 * Launch the application.
	 */


	/**
	 * Create the frame.
	 */
	public PedidosLista() {
		this.setVisible(true);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 450, 300);
		contentPane = new JPanel();
		contentPane.setBackground(new Color(0, 64, 128));
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));

		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JLabel lblNewLabel = new JLabel("Realizar pedido");
		lblNewLabel.setBounds(128, 11, 189, 32);
		lblNewLabel.setForeground(new Color(255, 255, 255));
		lblNewLabel.setFont(new Font("Impact", Font.ITALIC, 25));
		contentPane.add(lblNewLabel);
		
		JLabel lblNewLabel_1 = new JLabel("Ingrese el nombre del producto");
		lblNewLabel_1.setBounds(118, 54, 235, 21);
		lblNewLabel_1.setFont(new Font("Impact", Font.ITALIC, 14));
		lblNewLabel_1.setForeground(new Color(255, 255, 255));
		contentPane.add(lblNewLabel_1);
		
		JLabel lblNewLabel_2 = new JLabel("Ingrese la cantidad que desee");
		lblNewLabel_2.setForeground(new Color(255, 255, 255));
		lblNewLabel_2.setFont(new Font("Impact", Font.ITALIC, 14));
		lblNewLabel_2.setBounds(118, 117, 219, 38);
		contentPane.add(lblNewLabel_2);
		
		textField = new JTextField();
		textField.setBounds(118, 86, 189, 20);
		contentPane.add(textField);
		textField.setColumns(10);
		
		textField_1 = new JTextField();
		textField_1.setBounds(118, 157, 189, 20);
		contentPane.add(textField_1);
		textField_1.setColumns(10);
		
		JButton btnNewButton = new JButton("Agregar pedido");
		btnNewButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                String producto = obtenerProductoUsuario();
                String cantidad = obtenerCantidadUsuario();
                PedidosTabla pedidosTabla = new PedidosTabla(producto, cantidad);
                pedidosTabla.setVisible(true);
            }
        });
		btnNewButton.setBounds(63, 210, 274, 40);
		contentPane.add(btnNewButton);
        model = new DefaultTableModel();
        model.addColumn("Producto");
        model.addColumn("Cantidad");
        
        controlador = new PedidoControlador();
        cargarPedidosDesdeBD();
        
	}

	

	private void cargarPedidosDesdeBD() {
		List<Pedido> orders = controlador.obtenerPedidosDesdeBD();
        for (Pedido pedido : orders) {
            model.addRow(new Object[]{pedido.getProductos(), pedido.getCantidad()});
		
	}

	}

	private String obtenerProductoUsuario() {
		// TODO Auto-generated method stub
		return textField.getText();
	}
	
	private String obtenerCantidadUsuario() {
		// TODO Auto-generated method stub
		return textField_1.getText();
	}
}
