package vista;

import java.awt.EventQueue;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import Modelos.Reporte;
import controladores.ReporteControlador;
import javax.swing.JButton;
import java.awt.Color;
import javax.swing.JTextField;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.time.LocalDate;

public class ReporteForm extends JFrame {

    private static final long serialVersionUID = 1L;
    private JTextField descripcionField;
    private JPanel contentPane;
    private ReporteControlador controlador;
    private TablaReporte tablaReporte;

    /**
     * Launch the application.
     */
    public static void main(String[] args) {
        EventQueue.invokeLater(new Runnable() {
            public void run() {
                try {
                    ReporteControlador controlador = new ReporteControlador();
                    TablaReporte tablaReporte = new TablaReporte();
                    ReporteForm frame = new ReporteForm(null, controlador, tablaReporte);
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
    public ReporteForm(Reporte reporte, ReporteControlador controlador, TablaReporte tablaReporte) {
        this.controlador = controlador;
        this.tablaReporte = tablaReporte;

        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setBounds(100, 100, 486, 349);
        contentPane = new JPanel();
        contentPane.setBackground(new Color(0, 128, 255));
        contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));

        setContentPane(contentPane);
        contentPane.setLayout(null);

        descripcionField = new JTextField();
        descripcionField.setBounds(137, 135, 192, 20);
        contentPane.add(descripcionField);
        descripcionField.setColumns(10);

        JLabel lblAgregarDescripcion = new JLabel("Descripcion");
        lblAgregarDescripcion.setForeground(new Color(255, 255, 255));
        lblAgregarDescripcion.setFont(new Font("Microsoft JhengHei UI", Font.BOLD | Font.ITALIC, 16));
        lblAgregarDescripcion.setBounds(186, 110, 118, 14);
        contentPane.add(lblAgregarDescripcion);

        JButton btnGuardarReporte = new JButton("Guardar");
        btnGuardarReporte.setBounds(175, 196, 104, 33);
        contentPane.add(btnGuardarReporte);
        btnGuardarReporte.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                guardarReporte();
            }
        });
    }

    private void guardarReporte() {
        String descripcion = descripcionField.getText();
        if (descripcion.isEmpty()) {
            JOptionPane.showMessageDialog(this, "La descripción no puede estar vacía");
            return;
        }

        int id = controlador.getLastReportId() + 1;
        LocalDate fecha = LocalDate.now();
        String autor = "Gerente"; // Deberías cambiar esto por el autor correcto si lo tienes
        Reporte newReporte = new Reporte(id, autor, descripcion, fecha);

        controlador.addReport(newReporte);
        JOptionPane.showMessageDialog(this, "Reporte Agregado");

        // Actualizar la tabla y cerrar el formulario
        tablaReporte.actualizarTabla();
        dispose();
    }
}