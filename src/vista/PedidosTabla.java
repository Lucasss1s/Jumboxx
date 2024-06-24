package vista;

import java.awt.EventQueue;

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
import javax.swing.JScrollPane;

public class PedidosTabla extends JFrame {

	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	private JTable table;
    private DefaultTableModel tableModel;
    private JTextField textFieldBuscar;
    private PedidoControlador controlador;
    private int contadorId = 1;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					PedidosTabla frame = new PedidosTabla();
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
	public PedidosTabla() {
//		this.setVisible(true);
		controlador = new PedidoControlador();
        
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setBounds(100, 100, 600, 400);
        contentPane = new JPanel();
        contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
        setContentPane(contentPane);
        contentPane.setLayout(null);

        JScrollPane scrollPane = new JScrollPane();
        scrollPane.setBounds(10, 50, 560, 200);
        contentPane.add(scrollPane);
		
        
        tableModel = new DefaultTableModel(new Object[]{"ID", "Producto", "Cantidad" , "Fecha"}, 0);
        table = new JTable(tableModel);
        scrollPane.setViewportView(table);
        
        JButton btnMostrar = new JButton("Mostrar");
        btnMostrar.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                mostrarPedidos();
            }
        });
        btnMostrar.setBounds(10, 10, 100, 30);
        contentPane.add(btnMostrar);
        
        JButton btnGuardar = new JButton("Guardar");
        btnGuardar.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                guardarPedido();
            }
        });
        btnGuardar.setBounds(120, 10, 100, 30);
        contentPane.add(btnGuardar);

        JButton btnEditar = new JButton("Editar");
        btnEditar.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                editarPedido();
            }
        });
        btnEditar.setBounds(230, 10, 100, 30);
        contentPane.add(btnEditar);

        JButton btnEliminar = new JButton("Eliminar");
        btnEliminar.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                eliminarPedido();
            }
        });
        btnEliminar.setBounds(340, 10, 100, 30);
        contentPane.add(btnEliminar);

        JLabel lblBuscar = new JLabel("Buscar por ID:");
        lblBuscar.setBounds(10, 260, 50, 30);
        contentPane.add(lblBuscar);

        textFieldBuscar = new JTextField();
        textFieldBuscar.setBounds(70, 260, 200, 30);
        contentPane.add(textFieldBuscar);
        textFieldBuscar.setColumns(10);

        JButton btnBuscar = new JButton("Buscar");
        btnBuscar.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                buscarPedido();
            }
        });
        btnBuscar.setBounds(280, 260, 100, 30);
        contentPane.add(btnBuscar);
    }
	
	public void addPedidoToTable(int id, String producto, String cantidad, String fecha) {
        tableModel.addRow(new Object[]{id, producto, cantidad, fecha});
    }

    private void mostrarPedidos() {
        List<Pedido> pedidos = controlador.getAllOrders();
        JOptionPane.showMessageDialog(null, "Pedido: " + pedidos);
        tableModel.setRowCount(0); // Limpiar tabla
        for (Pedido pedido : pedidos) {
            tableModel.addRow(new Object[]{pedido.getId_Pedido(), pedido.getProductos(), pedido.getCantidad(), pedido.getFechaPedido().toString()});
        }
    }

    private void guardarPedido() {
        // Implementar lógica para guardar pedido
    	try {
        String nombreCliente = JOptionPane.showInputDialog("Ingrese el nombre del cliente:");
        Cliente cliente = new Cliente(0, nombreCliente, null, null, 0); // Aquí deberías implementar lógica para crear o buscar el cliente.
        String productosStr = JOptionPane.showInputDialog("Ingrese su producto:");
        LinkedList<String> productos = new LinkedList<>();
        for (String producto : productosStr.split(",")) {
            productos.add(producto);
        }
        String cantidad = JOptionPane.showInputDialog("Ingrese la cantidad:");
//        Pedido nuevoPedido = new Pedido(contadorId++, cliente, productos, LocalDate.now(), cantidad, true, "", "");
        Pedido nuevoPedido = new Pedido(contadorId++, cliente, productos, LocalDate.now(), 0, true, "", "");
        controlador.updatePedido(nuevoPedido);
        JOptionPane.showMessageDialog(null, "Pedido guardado: " + nuevoPedido);
        mostrarPedidos();
    
    } catch (Exception e) {
   	e.printStackTrace();
        JOptionPane.showMessageDialog(this, "Error al guardar el pedido: " + e.getMessage());
        }
    }

    private void editarPedido() {
        int selectedRow = table.getSelectedRow();
        if (selectedRow != -1) {
            int id = (int) tableModel.getValueAt(selectedRow, 0);
            Pedido pedido = controlador.getPedidoById(id);
            if (pedido != null) {
            	String nombreCliente = JOptionPane.showInputDialog("Ingrese el nombre del cliente:", pedido.getCliente().getNombre());
                Cliente cliente = new Cliente(pedido.getCliente().getId_cliente(), nombreCliente, null, null, 0); // Aquí deberías implementar lógica para actualizar el cliente.
                String productosStr = JOptionPane.showInputDialog("Ingrese los productos separados por comas:", pedido.getProductos());
                LinkedList<String> productos = new LinkedList<>();
                for (String producto : productosStr.split(",")) {
                    productos.add(producto);
                }
                String cantidad = JOptionPane.showInputDialog("Ingrese la cantidad:", pedido.getCantidad());
                LocalDate fechaPedido = LocalDate.parse(JOptionPane.showInputDialog("Ingrese la fecha (año-mes-dia):", pedido.getFechaPedido().toString()));
                pedido.setCliente(cliente);
                pedido.setProductos(productos);
                pedido.setCantidad(cantidad);
                pedido.setFechaPedido(fechaPedido);
                controlador.updatePedido(pedido);
                JOptionPane.showMessageDialog(null, "Pedido editado: " + pedido);
                mostrarPedidos();
			} else {
				JOptionPane.showMessageDialog(this, "No se encontró el pedido con el ID proporcionado.");
			}
           
        } else {
            JOptionPane.showMessageDialog(this, "Seleccione un pedido para editar.");
        }
    }

    private void eliminarPedido() {
        int selectedRow = table.getSelectedRow();
        if (selectedRow != -1) {
            int id = (int) tableModel.getValueAt(selectedRow, 0);
            controlador.deletePedido(id);
            mostrarPedidos();
        } else {
            JOptionPane.showMessageDialog(this, "Seleccione un pedido para eliminar.");
        }
    }

    private void buscarPedido() {
    	try {
    	int id = Integer.parseInt(textFieldBuscar.getText());
    	Pedido pedido = controlador.getPedidoById(id);
//    	String keyword = textFieldBuscar.getText();
//        JOptionPane.showMessageDialog(null, "Buscar pedidos con id: " + keyword);
//        List<Pedido> pedidos = controlador.searchPedidos(keyword);
        tableModel.setRowCount(0); // Limpiar tabla
//        for (Pedido pedido : pedidos) {
//        	String productosStr = String.join(", ", pedido.getProductos());
//            tableModel.addRow(new Object[]{pedido.getId_Pedido(), pedido.getCliente().getNombre(), pedido.getProductos(), pedido.getCantidad(), pedido.getFechaPedido().toString()});
//        }
        if (pedido != null) {
        	tableModel.addRow(new Object[]{pedido.getId_Pedido(), pedido.getCliente().getNombre(), pedido.getProductos(), pedido.getCantidad(), pedido.getFechaPedido().toString()});
		} else {
			JOptionPane.showMessageDialog(this, "No se encontró un pedido con el ID proporcionado.");
		}
    } catch (NumberFormatException e) {
//NumberFormat
//    		e.printStackTrace();
//            JOptionPane.showMessageDialog(this, "Error al buscar el pedido: " + e.getMessage());
    	JOptionPane.showMessageDialog(this, "Ingrese un ID válido.");
		}
    }
    
    
    
}
        
//        model.addRow(new Object[]{"Producto del usuario", "1"});
//        String productoUsuario = obtenerProductoUsuario();
//        String cantidadUsuario = obtenerCantidadUsuario();
//        model.addRow(new Object[]{productoUsuario, cantidadUsuario});
        
        
		
//	}

//	private String obtenerCantidadUsuario() {
		// TODO Auto-generated method stub
//		return null;
//	}

//	private String obtenerProductoUsuario() {
		// TODO Auto-generated method stub
//		return null;
//	}
	


//}

//}

