package vista;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import Modelos.Reporte;

import controladores.ReporteControlador;

import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTextField;
import java.awt.Color;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;

public class EditarReporte extends JFrame {

	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	private JTextField InpDescripcion;
	private ReporteControlador controlador;

	/**
	 * Launch the application.
	 */
	

	/**
	 * Create the frame.
	 */
	public EditarReporte(Reporte reporte, ReporteControlador controlador) {
		this.setVisible(true);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 450, 300);
		contentPane = new JPanel();
		contentPane.setBackground(new Color(0, 128, 192));
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));

		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JLabel lblDescripcion = new JLabel("Descripcion");
		lblDescripcion.setForeground(new Color(255, 255, 255));
		lblDescripcion.setFont(new Font("Microsoft PhagsPa", Font.BOLD, 16));
		lblDescripcion.setBounds(168, 66, 91, 34);
		contentPane.add(lblDescripcion);
		
		InpDescripcion = new JTextField();
		InpDescripcion.setBounds(142, 111, 143, 34);
		contentPane.add(InpDescripcion);
		InpDescripcion.setColumns(10);
		InpDescripcion.setText(reporte.getDescripcion());
		
		JButton btnEditar = new JButton("Editar");
		btnEditar.setBounds(156, 195, 116, 34);
		contentPane.add(btnEditar);
		
		btnEditar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
                if (InpDescripcion.getText().isEmpty()) {
                    JOptionPane.showMessageDialog(null, "No puede dejar el espacio vacío");
                } else {
                    reporte.setDescripcion(InpDescripcion.getText());
                    if (controlador.updateReportWithSuccessCheck(reporte)) {
                        
                        TablaReporte tablaReporte = new TablaReporte();
                        tablaReporte.setVisible(true);
                        dispose();
                    } else {
                        JOptionPane.showMessageDialog(null, "No se pudo editar");
                    }
                }
            }
        });
	}
}
