package Modelos;
import java.time.LocalDate;
import java.util.List;

import javax.swing.JOptionPane;
import controladores.DatabaseConnection;
import controladores.UsuarioControlador;

public class Gerente extends Usuario {

    public Gerente(int id_usuario, String nombreCompleto, String user, String puesto, LocalDate fechaRegistro) {
		super(id_usuario, nombreCompleto, user, puesto, fechaRegistro);
	}

	public static void mostrarMenu() {
    	boolean salir = false;
    	do {	
        String[] opciones = { "Pedidos", "Stock", "Reportes", "Almacenes" , "Usuarios" ,"Salir"};
        int opcionSeleccionada = JOptionPane.showOptionDialog(null, "Menu", null, 0, 3, null, opciones, opciones[0]);

        	
        switch (opcionSeleccionada) {
        case 0:
        		
        	String[] opciones1 = { "Ver Pedido", "Generar Pedido", "Salir"};
            int opcionSeleccionada1 = JOptionPane.showOptionDialog(null, "Menu", null, 0, 3, null, opciones1, opciones1[0]);
            switch (opcionSeleccionada1) {
			case 0:
				JOptionPane.showMessageDialog(null, "Pedidos");
				break;
			case 1:
				JOptionPane.showMessageDialog(null, "Realizar Pedido");
				break;
			case 2:
				salir = true;
				break;
			}
        	  
            break;
        case 1:
            String[] opciones2 = { "Ver Stock", "Actualizar Stock", "Comprar", "Salir"};
            int opcionSeleccionada2 = JOptionPane.showOptionDialog(null, "Menu", null, 0, 3, null, opciones2, opciones2[0]);
            switch (opcionSeleccionada2) {
			case 0:
				JOptionPane.showMessageDialog(null, "Stock");
				break;
			case 1:
				JOptionPane.showMessageDialog(null, "Actualizar stock");
				break;
			case 2:
				JOptionPane.showMessageDialog(null, "Realizar compra de productos a proveedores");
				break;
			case 3:
				salir = true;
				break;
			}
            break;
        case 2:
            String[] opciones3 = { "Ver Reportes", "Generar Reporte", "Salir"};
            int opcionSeleccionada3 = JOptionPane.showOptionDialog(null, "Menu", null, 0, 3, null, opciones3, opciones3[0]);
            switch (opcionSeleccionada3) {
			case 0:
				JOptionPane.showMessageDialog(null, "Reportes");
				break;
			case 1:
				JOptionPane.showMessageDialog(null, "Realizar Reporte");
				break;
			case 2:
				salir = true;
				break;

			}
            break;
     	case 3:
     		JOptionPane.showMessageDialog(null, "Almacenes");
     		break;
     	case 4:
     		UsuarioControlador controlador = new UsuarioControlador();
     		
     		 String[] users = { "Ver usuarios", "Agregar usuarios", "Actualizar usuarios", "Salir"};
             int opcUsers = JOptionPane.showOptionDialog(null, "Menu", null, 0, 3, null, users, users[0]);
     		
             
             switch (opcUsers) {
			case 0:
				
				break;
			case 1:
				
				break;
			case 2:
				
				break;
			case 3:
				
				break;
			default:
				
				break;
			}
            List<Usuario> usuarios = controlador.getAllUsers();
            
            // Crear una cadena de texto para mostrar los usuarios
            StringBuilder usuariosTexto = new StringBuilder();
            for (Usuario usuario : usuarios) {
                usuariosTexto.append("ID: ").append(usuario.getId_usuario()).append("\n")
                             .append("Nombre Completo: ").append(usuario.getNombreCompleto()).append("\n")
                             .append("Usuario: ").append(usuario.getUser()).append("\n")
                             .append("Puesto: ").append(usuario.getPuesto()).append("\n")
                             .append("Fecha de Registro: ").append(usuario.getFechaRegistro()).append("\n")
                             .append("----------------------------\n");
            }
            
            // Mostrar los usuarios en un JOptionPane
            JOptionPane.showMessageDialog(null, usuariosTexto.toString());
     		
     		break;	
     	case 5:
     		System.exit(0);
     		break;
        }
   
    	} while (salir = true);
    }
   
    public void generarInformeVentas() {
        
        JOptionPane.showMessageDialog(null,"El gerente está generando un informe de ventas.");
    }

    
    public void realizarReunionPersonal() {
    	
    	JOptionPane.showMessageDialog(null,"El gerente está realizando una reunión con el personal.");
    }
}
