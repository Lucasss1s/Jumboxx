package Modelos;
import java.util.LinkedList;

public class BaseDatos {
	
	private LinkedList<Almacenista> almacenista = new LinkedList<Almacenista>();
	private LinkedList<Administrador> administrador = new LinkedList<Administrador>();
		
	public LinkedList<Almacenista> getAlmacenistas() {
		return almacenista;
	}
	public void setAlmacenistas(LinkedList<Almacenista> almacenistas) {
		this.almacenista = almacenista;
	}
	public LinkedList<Administrador> getAdministrador() {
		return administrador;
	}
	public void setAdministrador(LinkedList<Administrador> administrador) {
		this.administrador = administrador;
	}
	
	
	
	public Almacenista BuscarAlmacenista(int id_persona) {
		for (Almacenista almacenista : almacenista) {
            if (almacenista.getId_persona() ==  id_persona) {
                return almacenista;
            }
		}
        return null;
		}

	public Administrador BuscarAdministrador(int id_persona) {
		for (Administrador administrador : administrador) {
            if (administrador.getId_persona() ==  id_persona) {
                return administrador;
            }
		}
        return null;
		}
	
	
}
