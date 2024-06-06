package vista;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import Modelos.Usuario;
import javax.swing.JLabel;
import javax.swing.JTextField;
import java.awt.Font;
import javax.swing.JPasswordField;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.awt.Label;
import java.awt.Color;
import java.awt.Button;
import java.awt.Toolkit;
import javax.swing.JLayeredPane;
import java.awt.Panel;
import java.awt.Canvas;
import java.awt.TextArea;
import javax.swing.ImageIcon;

public class PantallaIniciarSeccion extends JFrame {

    private static final long serialVersionUID = 1L;
    private JPanel contentPane;
    private JTextField inpNombre;
    private JPasswordField inpContraseña;

   
    public static void main(String[] args) {
        EventQueue.invokeLater(new Runnable() {
            public void run() {
                try {
                    PantallaIniciarSeccion frame = new PantallaIniciarSeccion();
                    frame.setVisible(true);
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        });
    }

   
    public PantallaIniciarSeccion() {
    	setIconImage(Toolkit.getDefaultToolkit().getImage(PantallaIniciarSeccion.class.getResource("/img/Logo.png")));
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setBounds(100, 100, 607, 337);
        contentPane = new JPanel();
        contentPane.setBackground(new Color(0, 64, 128));
        contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));

        setContentPane(contentPane);
        contentPane.setLayout(null);

        JLabel lblNombre = new JLabel("ID Usuario");
        lblNombre.setForeground(new Color(255, 255, 255));
        lblNombre.setFont(new Font("Impact", Font.ITALIC, 20));
        lblNombre.setBounds(390, 26, 91, 26);
        contentPane.add(lblNombre);

        inpNombre = new JTextField();
        inpNombre.setBounds(345, 74, 205, 28);
        contentPane.add(inpNombre);
        inpNombre.setColumns(10);

        inpContraseña = new JPasswordField();
        inpContraseña.setBounds(345, 165, 205, 28);
        contentPane.add(inpContraseña);
        

        JLabel lblError = new JLabel("Error Usuario");
        lblError.setForeground(new Color(255, 0, 0));
        lblError.setBounds(390, 102, 271, 26);
        contentPane.add(lblError);
        lblError.setVisible(false);
        
        JLabel lblContrasea = new JLabel("Contraseña");
        lblContrasea.setBackground(new Color(255, 255, 255));
        lblContrasea.setForeground(new Color(255, 255, 255));
        lblContrasea.setFont(new Font("Impact", Font.ITALIC, 20));
        lblContrasea.setBounds(390, 126, 101, 28);
        contentPane.add(lblContrasea);
        
        JLabel lblContraseñaError = new JLabel("Error Contraseña");
        lblContraseñaError.setForeground(new Color(255, 0, 0));
        lblContraseñaError.setBounds(411, 204, 139, 19);
        contentPane.add(lblContraseñaError);
        lblContraseñaError.setVisible(false);

        JButton btnRegistrase = new JButton("Registrase");
        btnRegistrase.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                Registro registro = new Registro();
                dispose();
            }
        });
        btnRegistrase.setFont(new Font("Tahoma", Font.PLAIN, 30));
        btnRegistrase.setBounds(50, 357, 193, 50);
        contentPane.add(btnRegistrase);
        
        Button button = new Button("Iniciar");
        button.addActionListener(new ActionListener() {
        	public void actionPerformed(ActionEvent e) {
        		
        		
        		String respuesta = Usuario.id_usuario(inpNombre.getText(), new String(inpContraseña.getPassword()));
                if(respuesta.equals("Ingresa")) {
                    Home home = new Home(inpNombre.getText());
                    dispose();
                } else {
                    lblError.setText(respuesta);
                    lblError.setVisible(true);
                }
        	}
        });
        button.setBounds(271, 248, 84, 28);
        contentPane.add(button);
        
        JLabel lblNewLabel = new JLabel("");
        lblNewLabel.setIcon(new ImageIcon(PantallaIniciarSeccion.class.getResource("/img/Logo 2.png")));
        lblNewLabel.setBounds(10, 48, 210, 176);
        contentPane.add(lblNewLabel);
        
        JLabel lblNewLabel_1 = new JLabel("Bienvenido!");
        lblNewLabel_1.setFont(new Font("Impact", Font.ITALIC, 20));
        lblNewLabel_1.setForeground(new Color(255, 255, 255));
        lblNewLabel_1.setBounds(50, 26, 101, 26);
        contentPane.add(lblNewLabel_1);
        
       
        
        
    }
}