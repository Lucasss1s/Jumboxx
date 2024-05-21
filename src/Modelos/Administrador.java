package Modelos;
import javax.swing.JOptionPane;

public class Administrador extends Usuario {
	 
    public Administrador(int id_persona, String nombre, String apellido) {
		super(id_persona, nombre, apellido);
	}
    
    
    public void mostrarMenu() {
    	boolean salir = false;
    	do {	
        String[] opciones = { "Pedidos", "Stock", "Reportes", "Salir"};
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
     		System.exit(0);
     		break;
        }
   
    	} while (salir = true);
     
    }
	
	public void gestionarCuentas() {
    	
    	JOptionPane.showMessageDialog(null,"El administrador está gestionando las cuentas del supermercado.");
    }

   
    public void configurarSistema() {
    	
    	JOptionPane.showMessageDialog(null,"El administrador está configurando el sistema del supermercado.");
    }
}