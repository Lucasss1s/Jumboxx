package vista;

import java.awt.Color;
import java.awt.EventQueue;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

public class PedidosMenu extends JFrame {

	private static final long serialVersionUID = 1L;
	private JPanel contentPane;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					PedidosMenu frame = new PedidosMenu();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the frame.
	 */
	public PedidosMenu() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 450, 300);
		contentPane = new JPanel();
		contentPane.setBackground(new Color(0, 64, 128));
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));

		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JLabel lblPedido = new JLabel("Pedidos");
		lblPedido.setForeground(new Color(255, 255, 255));
		lblPedido.setFont(new Font("Impact", Font.ITALIC, 30));
		lblPedido.setBounds(177, 0, 123, 74);
		contentPane.add(lblPedido);
		
		JLabel lblNewLabel = new JLabel("Click aqui para realizar su pedido");
		lblNewLabel.setForeground(new Color(255, 255, 255));
		lblNewLabel.setFont(new Font("Impact", Font.ITALIC, 20));
		lblNewLabel.setBounds(78, 101, 333, 26);
		contentPane.add(lblNewLabel);
		
		JButton btnNewButton = new JButton("Realizar pedido");
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				PedidosTabla pedidosTabla = new PedidosTabla();
				PedidosLista nuevo = new PedidosLista(pedidosTabla);
				nuevo.setVisible(true);
				dispose();
			}
		});
		btnNewButton.setBounds(142, 160, 145, 36);
		contentPane.add(btnNewButton);
		
		
	
	}

}
