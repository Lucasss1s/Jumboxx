package vista;
import javax.swing.ImageIcon;
import javax.swing.JOptionPane;
import Modelos.Administrador;
import Modelos.Almacenista;
import Modelos.BaseDatos;
import Modelos.Cliente;
import Modelos.Deposito;
import Modelos.Gerente;
import Modelos.Producto;
import Modelos.Sucursal;
import Modelos.Venta;
import controladores.UsuarioControlador;

public class Main {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
	
		BaseDatos bd = new BaseDatos();
		UsuarioControlador controlador = new UsuarioControlador();
		
		bd.getAlmacenistas().add(new Almacenista(2, "Ana", "López"));
		bd.getAdministrador().add(new Administrador(3, "Elena", "García"));
		
		Gerente gerente = new Gerente(1, "Carlos", "Martínez");
		Cliente cliente1 = new Cliente(4, "Juan", "Gomez", "Moreno 850", 44661122);
		
		Venta venta1 = new Venta(1, cliente1, null, null, 1000);            
        Sucursal sucursal1 = new Sucursal(1,"Carrefur","Santa fe 1240",445551112);
        Producto productos = new Producto(1,"Coca-cola",20,500);
        Deposito deposito = new Deposito(1,productos,20,"Corrientes 2040");
                      
        
		ImageIcon icon = new ImageIcon(Main.class.getResource("/img/Logo.png"));
		JOptionPane.showMessageDialog(null, "¡Bienvenido \n         a               \n  Mayorista      \n  Jumbox!", "Hola",
				JOptionPane.INFORMATION_MESSAGE, icon);
     
        int selecId = Integer.parseInt(JOptionPane.showInputDialog("Ingrese su ID:"));
		Almacenista almacenistaSelec = bd.BuscarAlmacenista(selecId);
		Administrador administradorSelec = bd.BuscarAdministrador(selecId);
		
        if (almacenistaSelec != null) {
        	JOptionPane.showMessageDialog(null, "Bienvenid@: " + almacenistaSelec.getNombre());
     
        	almacenistaSelec.mostrarMenu();
        	
		} 
        
        
        
        if (administradorSelec != null) {
        	JOptionPane.showMessageDialog(null, "Bienvenid@: " + administradorSelec.getNombre());
     
        	administradorSelec.mostrarMenu();
    	    
		}
        
        
        
        
        if (selecId == gerente.getId_persona()) {
        	JOptionPane.showMessageDialog(null, "Bienvenid@: " + gerente.getNombre());
        	
        	gerente.mostrarMenu();
        	
		}
        
        //
     
        System.out.println("a");
        
    }
}
