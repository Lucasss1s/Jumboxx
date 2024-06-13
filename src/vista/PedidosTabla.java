package vista;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JTable;
import javax.swing.border.EmptyBorder;
import javax.swing.table.DefaultTableModel;
import javax.swing.JScrollPane;

public class PedidosTabla extends JFrame {

	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	private JTable table;
    private DefaultTableModel model;

	/**
	 * Launch the application.
	 */
//	public static void main(String[] args) {
//		EventQueue.invokeLater(new Runnable() {
//			public void run() {
//				try {
//					PedidosTabla frame = new PedidosTabla();
//					frame.setVisible(true);
//				} catch (Exception e) {
//					e.printStackTrace();
//				}
//			}
//		});
//	}

	/**
	 * Create the frame.
	 */
	public PedidosTabla(String productoUsuario, String cantidadUsuario) {
		this.setVisible(true);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 450, 300);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JScrollPane scrollPane = new JScrollPane();
        scrollPane.setBounds(10, 11, 414, 239);
        contentPane.add(scrollPane);
        
        table = new JTable();
        model = new DefaultTableModel();
        model.addColumn("Producto");
        model.addColumn("Cantidad");
        table.setModel(model);
        scrollPane.setViewportView(table);
        
        
//        model.addRow(new Object[]{"Producto del usuario", "1"});
//        String productoUsuario = obtenerProductoUsuario();
//        String cantidadUsuario = obtenerCantidadUsuario();
        model.addRow(new Object[]{productoUsuario, cantidadUsuario});
        
        
		
	}

//	private String obtenerCantidadUsuario() {
		// TODO Auto-generated method stub
//		return null;
//	}

//	private String obtenerProductoUsuario() {
		// TODO Auto-generated method stub
//		return null;
//	}
	

}
