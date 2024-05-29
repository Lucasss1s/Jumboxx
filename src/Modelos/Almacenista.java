package Modelos;

import java.time.LocalDate;
import javax.swing.JOptionPane;

import controladores.ReporteControlador;
import controladores.UsuarioControlador;

public class Almacenista extends Usuario {

    public Almacenista(int id_usuario, String nombreCompleto, String user, String contraseña, String puesto,
                       LocalDate fechaRegistro) {
        super(id_usuario, nombreCompleto, user, contraseña, puesto, fechaRegistro);
    }

    


}
