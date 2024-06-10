package vista;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JLabel;
import java.awt.Font;
import javax.swing.JTextField;
import javax.swing.JButton;
import javax.swing.JTextArea;
import java.awt.Color;

public class PedidosLista extends JFrame {

	private static final long serialVersionUID = 1L;
	private JPanel contentPane;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					PedidosLista frame = new PedidosLista();
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
	public PedidosLista() {
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
		
		JButton btnNewButton = new JButton("Realizar pedido");
		btnNewButton.setBounds(143, 138, 145, 36);
		contentPane.add(btnNewButton);
		
		JLabel lblNewLabel = new JLabel("Click aqui para realizar su pedido");
		lblNewLabel.setForeground(new Color(255, 255, 255));
		lblNewLabel.setFont(new Font("Impact", Font.ITALIC, 20));
		lblNewLabel.setBounds(78, 101, 333, 26);
		contentPane.add(lblNewLabel);
	}
}
