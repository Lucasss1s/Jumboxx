package Modelos;
import java.time.LocalDate;

import javax.swing.JOptionPane;

public class Almacenista  extends Usuario{
	  
  
	
	public Almacenista(int id_usuario, String nombreCompleto, String user, String puesto, LocalDate fechaRegistro) {
		super(id_usuario, nombreCompleto, user, puesto, fechaRegistro);
	}


	public void gestionarInventario() {
    	
    	JOptionPane.showMessageDialog(null,"El almacenista está gestionando el inventario.");
    }

        
    public static void mostrarMenu() {
    	boolean salir = false;
    	do {	
        String[] opciones = { "Pedidos", "Stock", "Reportes", "Salir"};
        int opcionSeleccionada = JOptionPane.showOptionDialog(null, "Menu", null, 0, 3, null, opciones, opciones[0]);

        	
        switch (opcionSeleccionada) {
        case 0:
        		
        	String[] opciones1 = { "Ver Pedido", "Salir"};
            int opcionSeleccionada1 = JOptionPane.showOptionDialog(null, "Menu", null, 0, 3, null, opciones1, opciones1[0]);
            switch (opcionSeleccionada1) {
			case 0:
				JOptionPane.showMessageDialog(null, "Pedidos");
				break;
			case 1:
				salir = true;
				break;
			}
        	  
            break;
        case 1:
            String[] opciones2 = { "Ver Stock", "Actualizar Stock", "Salir"};
            int opcionSeleccionada2 = JOptionPane.showOptionDialog(null, "Menu", null, 0, 3, null, opciones2, opciones2[0]);
            switch (opcionSeleccionada2) {
			case 0:
				JOptionPane.showMessageDialog(null, "Stock");
				break;
			case 1:
				JOptionPane.showMessageDialog(null, "Realizar compra de productos a proveedores");
				break;
			case 2:
				salir = true;
				break;
			}
            break;
        case 2:
            String[] opciones3 = { "Ver Reportes", "Salir"};
            int opcionSeleccionada3 = JOptionPane.showOptionDialog(null, "Menu", null, 0, 3, null, opciones3, opciones3[0]);
            switch (opcionSeleccionada3) {
			case 0:
				JOptionPane.showMessageDialog(null, "Reportes");
				break;
			case 1:
				salir = true;
				break;
			}
            break;
     	case 3:
     		System.exit(0);
     		break;
        }
   
    	} while (salir = true);
    }
    
    
    
    
}
	

