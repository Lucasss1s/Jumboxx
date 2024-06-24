package vista;

import java.awt.EventQueue;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.time.LocalDate;
import java.util.List;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.ListSelectionModel;
import javax.swing.border.EmptyBorder;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;
import javax.swing.table.DefaultTableModel;


import Modelos.Reporte;
import vista.EditarReporte;
import controladores.ReporteControlador;
import java.awt.Color;
import java.awt.Font;

public class TablaReporte extends JFrame {

    private static final long serialVersionUID = 1L;
    private JPanel contentPane;
    private JTable table;
    private DefaultTableModel model;
    private ReporteControlador controlador;
    private JLabel elemento;
    private JButton EditarReporte;
    private JButton ReporteForm;
    private JButton btnMenuGerente;
    private JTextField filtrar;
    private JButton btnFiltrar;

    /**
     * Launch the application.
     */
    public static void main(String[] args) {
        EventQueue.invokeLater(new Runnable() {
            public void run() {
                try {
                    TablaReporte frame = new TablaReporte();
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
    public TablaReporte() {
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setBounds(100, 100, 944, 523);
        contentPane = new JPanel();
        contentPane.setBackground(new Color(0, 128, 192));
        contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));

        setContentPane(contentPane);

        // Inicializar controlador y usuario seleccionado
        controlador = new ReporteControlador();
        Reporte seleccionado = new Reporte();

        // Crear la tabla y el modelo
        String[] columnNames = {"ID", "Autor", "Descripcion", "Fecha"};
        model = new DefaultTableModel(columnNames, 0);
        table = new JTable(model);
        actualizarTabla();
        contentPane.setLayout(null);

        // Crear el JScrollPane y agregar la tabla
        JScrollPane scrollPane = new JScrollPane(table);
        scrollPane.setBounds(5, 19, 911, 190);
        contentPane.add(scrollPane);

        // Crear el JLabel para mostrar la selección
        elemento = new JLabel("Seleccionado:");
        elemento.setFont(new Font("Microsoft New Tai Lue", Font.BOLD, 11));
        elemento.setForeground(new Color(255, 255, 255));
        elemento.setBounds(5, 5, 911, 14);
        contentPane.add(elemento);

        JButton btnEliminar = new JButton("Eliminar");
        btnEliminar.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                if (seleccionado.getId_reporte() != 0) {
                    controlador.deleteReport(seleccionado.getId_reporte());
                    JOptionPane.showMessageDialog(null, "Reporte eliminado");
                    actualizarTabla();
                } else {
                    JOptionPane.showMessageDialog(null, "Seleccione un reporte para Eliminar");
                }
            }
        });
        btnEliminar.setBounds(20, 377, 142, 44);
        contentPane.add(btnEliminar);

        EditarReporte = new JButton("Editar");
        EditarReporte.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                if (seleccionado.getId_reporte() != 0) {
                    EditarReporte editar = new EditarReporte(seleccionado, controlador);
                    editar.setVisible(true);
                } else {
                    JOptionPane.showMessageDialog(null, "Seleccione un reporte para editar");
                }
            }
        });
        EditarReporte.setBounds(268, 377, 142, 44);
        contentPane.add(EditarReporte);

        ReporteForm = new JButton("Agregar");
        ReporteForm.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                ReporteForm agregar = new ReporteForm(null, controlador, TablaReporte.this);
                agregar.setVisible(true);
            }
        });
        ReporteForm.setBounds(494, 377, 142, 44);
        contentPane.add(ReporteForm);
        
        btnMenuGerente = new JButton("Volver");
        btnMenuGerente.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
            	 dispose();
                MenuPrincipalGerente menuGerente = new MenuPrincipalGerente(null, null);
                menuGerente.setVisible(true);
               
            }
        });
        
        filtrar = new JTextField();
        filtrar.setBounds(76, 220, 86, 20);
        contentPane.add(filtrar);
        filtrar.setColumns(10);
        
        JLabel lblNewLabel = new JLabel("Criterio:");
        lblNewLabel.setFont(new Font("Microsoft New Tai Lue", Font.PLAIN, 15));
        lblNewLabel.setForeground(new Color(255, 255, 255));
        lblNewLabel.setBounds(15, 223, 62, 14);
        contentPane.add(lblNewLabel);
        
        
        
        
        btnMenuGerente.setBounds(717, 377, 142, 44);
        contentPane.add(btnMenuGerente);
        
        btnFiltrar = new JButton("Filtrar");
        btnFiltrar.addActionListener(new ActionListener() {
        	public void actionPerformed(ActionEvent e) {
        		
        		Filtrar(filtrar.getText());
        		
        	}
        });
        btnFiltrar.setBounds(174, 219, 62, 20);
        contentPane.add(btnFiltrar);

        // Configurar el modelo de selección
        ListSelectionModel selectionModel = table.getSelectionModel();
        selectionModel.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);

        // Agregar un escuchador de selección
        selectionModel.addListSelectionListener(new ListSelectionListener() {
            @Override
            public void valueChanged(ListSelectionEvent e) {
                if (!e.getValueIsAdjusting()) {
                    int selectedRow = table.getSelectedRow();
                    if (selectedRow != -1) {
                        int id = (int) table.getValueAt(selectedRow, 0);
                        String autor = (String) table.getValueAt(selectedRow, 1);
                        String descripcion = (String) table.getValueAt(selectedRow, 2);
                        LocalDate fecha = (LocalDate) table.getValueAt(selectedRow, 3);
                        elemento.setText("Seleccionado: ID=" + id + ", Nombre=" + autor + ", Mail=" + descripcion + "Rol=" + fecha);
                        seleccionado.setAutor(autor);
                        seleccionado.setDescripcion(descripcion);
                        seleccionado.setId_reporte(id);
                        seleccionado.setFecha(fecha);
                    }
                }
            }
        });
    }

    public void actualizarTabla() {
        // Limpiar el modelo de la tabla
        model.setRowCount(0);

        // Obtener la lista actualizada de reportes
        List<Reporte> reportes = controlador.getAllReport();

        // Agregar los datos al modelo
        for (Reporte reporte : reportes) {
            model.addRow(
                new Object[]{
                    reporte.getId_reporte(),
                    reporte.getAutor(),
                    reporte.getDescripcion(),
                    reporte.getFecha()
                }
            );
        }
    }
    private void Filtrar(String criterio) {
        // Limpiar el modelo de la tabla
        model.setRowCount(0);

        // Obtener la lista actualizada de productos
        List<Reporte> reportes = controlador.getAllReport();

        // Agregar los datos al modelo
        for (Reporte reporte : reportes) {
        	if(reporte.getAutor().contains(criterio)) {
                model.addRow(new Object[]{reporte.getId_reporte(), reporte.getAutor(), reporte.getDescripcion(), reporte.getFecha()});
        	}
        }
    }
}