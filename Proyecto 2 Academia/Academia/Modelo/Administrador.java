package Modelo;

import java.io.Serializable;

public class Administrador extends Usuario implements Serializable {

    public Administrador(String codigo, String nombre, String password) {
        // El rol siempre será "Administrador"
        super(codigo, nombre, password, "Administrador");
    }


}
