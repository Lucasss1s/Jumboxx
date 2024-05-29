package test;

import static org.junit.Assert.assertEquals;

import javax.swing.JOptionPane;

import org.junit.jupiter.api.Test;

import Modelos.Usuario;
import controladores.UsuarioControlador;

public class pruebaUsuario {

	 UsuarioControlador controlador = new UsuarioControlador();
	 
	    @Test
	    public void PruebaIniciarSesiónPositivo() {
	        boolean flag = false;	        
	        Usuario iniciarSesionP = null;     
	        iniciarSesionP = controlador.getUserByUsernameAndPassword("Lucass", "123");
	            if (iniciarSesionP != null) {
	                flag = true;
	        }
	        assertEquals(true, flag);
	    }
	    
	    @Test
	    public void PruebaIniciarSesiónNegativo() {
	        boolean flag = false;
	        Usuario iniciarSesionP = null;
	        iniciarSesionP = controlador.getUserByUsernameAndPassword("----", "-----");
	            if (iniciarSesionP != null) {
	                flag = true;
	        }
	        assertEquals(false, flag);
	    }
	    
}
