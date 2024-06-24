package vista;

import java.awt.Color;
import java.awt.EventQueue;
import java.awt.Font;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JTable;
import javax.swing.border.EmptyBorder;
import javax.swing.table.DefaultTableModel;
import javax.swing.JScrollPane;
import javax.swing.JButton;
import javax.swing.JTextField;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.List;
import controladores.PedidoControlador;
import Modelos.Pedido;
import Modelos.Cliente;
import java.util.LinkedList;
import java.time.LocalDate;

import Modelos.Pedido;

//import java.util.List;
//import controladores.PedidoControlador;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;


public class PedidosLista extends JFrame {

	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	private JTextField textField;
	private JTextField textField_1;
	private JTextField textFieldFecha;	
	private PedidosTabla pedidosTabla;
	private int contadorId = 1;


	/**
	 * Launch the application.
	 */
	
	public static void main(String[] args) {
        EventQueue.invokeLater(new Runnable() {
            public void run() {
                try {
                	PedidosLista frame = new PedidosLista(new PedidosTabla());
                    frame.setVisible(true);
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        });
    }


	/**
	 * Create the frame.
	 * @param pedidosTabla2 
	 */
	public PedidosLista(PedidosTabla pedidosTabla) {
		this.setVisible(true);
		this.pedidosTabla = pedidosTabla;
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 450, 300);
		contentPane = new JPanel();
		contentPane.setBackground(new Color(0, 64, 128));
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));

		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JLabel lblNewLabel = new JLabel("Realizar pedido");
		lblNewLabel.setBounds(118, 11, 189, 32);
		lblNewLabel.setForeground(new Color(255, 255, 255));
		lblNewLabel.setFont(new Font("Impact", Font.ITALIC, 25));
		contentPane.add(lblNewLabel);
		
		JLabel lblNewLabel_1 = new JLabel("Ingrese el nombre del producto");
		lblNewLabel_1.setBounds(96, 54, 235, 21);
		lblNewLabel_1.setFont(new Font("Impact", Font.ITALIC, 13));
		lblNewLabel_1.setForeground(new Color(255, 255, 255));
		contentPane.add(lblNewLabel_1);
		
		JLabel lblNewLabel_2 = new JLabel("Ingrese la cantidad que desee");
		lblNewLabel_2.setForeground(new Color(255, 255, 255));
		lblNewLabel_2.setFont(new Font("Impact", Font.ITALIC, 13));
		lblNewLabel_2.setBounds(96, 92, 219, 38);
		contentPane.add(lblNewLabel_2);
		
		JLabel lblNewLabel_3 = new JLabel("Ingrese la fecha del pedido (año-mes-dia)");
        lblNewLabel_3.setForeground(new Color(255, 255, 255));
        lblNewLabel_3.setFont(new Font("Impact", Font.ITALIC, 13));
        lblNewLabel_3.setBounds(96, 161, 235, 21);
        contentPane.add(lblNewLabel_3);
		
		textField = new JTextField();
		textField.setBounds(96, 75, 189, 20);
		contentPane.add(textField);
		textField.setColumns(10);
		
		textField_1 = new JTextField();
		textField_1.setBounds(96, 130, 189, 20);
		contentPane.add(textField_1);
		textField_1.setColumns(10);
		
		textFieldFecha = new JTextField();
        textFieldFecha.setBounds(96, 186, 189, 20);
        contentPane.add(textFieldFecha);
        textFieldFecha.setColumns(10);
		
		JButton btnNewButton = new JButton("Agregar pedido");
		btnNewButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                agregarPedido();
            }
            

			private void agregarPedido() {
				// TODO Auto-generated method stub
				int id_pedido = obtenerId_Pedido();
				String producto = obtenerProductoUsuario();
		        String cantidad = obtenerCantidadUsuario();
		        String fecha = obtenerFechaUsuario();
		        pedidosTabla.addPedidoToTable(id_pedido, producto, cantidad, fecha);
		        pedidosTabla.setVisible(true);
		        dispose(); // Cierra la ventana actual
			}


			
        });
		btnNewButton.setBounds(219, 217, 164, 32);
		contentPane.add(btnNewButton);
//        model = new DefaultTableModel();
//        model.addColumn("Producto");
//        model.addColumn("Cantidad");
        
//        controlador = new PedidoControlador();
//        cargarPedidosDesdeBD();
        
	}

	

//	private void cargarPedidosDesdeBD() {
//		List<Pedido> orders = controlador.searchPedidos("");
//        for (Pedido pedido : orders) {
//           model.addRow(new Object[]{pedido.getProductos(), pedido.getCantidad()});
		
//	}

//	}
	
	private int obtenerId_Pedido() {
		// TODO Auto-generated method stub
		return contadorId++;
	}

	private String obtenerProductoUsuario() {
		// TODO Auto-generated method stub
		return textField.getText();
	}
	
	private String obtenerCantidadUsuario() {
		// TODO Auto-generated method stub
		return textField_1.getText();
	}
	
	private String obtenerFechaUsuario() {
        return textFieldFecha.getText();
    }

}



