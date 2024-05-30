package test;

import static org.junit.Assert.assertEquals;


import java.time.LocalDate;

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
	    

	    @Test
	    public void PruebaAgregarUsuario() {
	        Usuario nuevoUsuario = new Usuario(0, "Nombre Prueba", "userPrueba", "contraseñaPrueba", "Puesto Prueba", LocalDate.now());
	        controlador.addUser(nuevoUsuario);

	        Usuario usuarioAgregado = controlador.getUserByUsernameAndPassword("userPrueba", "contraseñaPrueba");
	        assertEquals("Nombre Prueba", usuarioAgregado.getNombreCompleto());
	        assertEquals("userPrueba", usuarioAgregado.getUser());
	        assertEquals("contraseñaPrueba", usuarioAgregado.getContraseña());
	        assertEquals("Puesto Prueba", usuarioAgregado.getPuesto());
	    }

	    @Test
	    public void PruebaEliminarUsuario() {
	        Usuario usuarioAEliminar = controlador.getUserByUsernameAndPassword("userPrueba", "contraseñaPrueba");
	        if (usuarioAEliminar != null) {
	            controlador.deleteUser(usuarioAEliminar.getId_usuario());

	            Usuario usuarioEliminado = controlador.getUserById(usuarioAEliminar.getId_usuario());
	            assertEquals(null, usuarioEliminado);
	        } else {
	            assertEquals(true, false); 
	        }
	    }
	    
}
