package vista;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import Modelos.Usuario;

import javax.swing.JLabel;
import java.awt.Font;
import javax.swing.JTextField;
import javax.swing.JPasswordField;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.awt.Color;

public class Registro extends JFrame {

	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	private JTextField textField;
	private JPasswordField passwordField;

	 
	public Registro() {
		this.setVisible(true);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 436, 690);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));

		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JLabel lblNombre = new JLabel("Nombre");
		lblNombre.setBounds(83, 101, 215, 28);
		lblNombre.setFont(new Font("Tahoma", Font.PLAIN, 30));
		contentPane.add(lblNombre);
		
		textField = new JTextField();
		textField.setBounds(79, 144, 215, 38);
		textField.setColumns(10);
		contentPane.add(textField);
		
		JLabel lblContrasea = new JLabel("Contraseña");
		lblContrasea.setBounds(83, 193, 215, 28);
		lblContrasea.setFont(new Font("Tahoma", Font.PLAIN, 30));
		contentPane.add(lblContrasea);
		
		passwordField = new JPasswordField();
		passwordField.setBounds(83, 231, 208, 38);
		contentPane.add(passwordField);
		
		JLabel lblError = new JLabel("");
		lblError.setForeground(Color.RED);
		lblError.setFont(new Font("Tahoma", Font.PLAIN, 24));
		lblError.setBounds(10, 414, 389, 75);
		contentPane.add(lblError);
		
		JButton btnRegistrase = new JButton("Registrase");
		btnRegistrase.setBounds(83, 312, 193, 50);
		btnRegistrase.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				String respuesta = Usuario.Registrarse(textField.getText(),passwordField.getText() );
				 if(respuesta.equals("Ok")) {
					 Home home = new Home(textField.getText());
					 dispose();
				 }else {
					 lblError.setText(respuesta);
						lblError.setVisible(true);

				 }
				}
				
			
		});
		btnRegistrase.setFont(new Font("Tahoma", Font.PLAIN, 30));
		contentPane.add(btnRegistrase);
		
		
	}

}
