package Controlador;

import Modelo.Usuario;

public class Sistema {

    private Usuario[] usuarios;
    private int contador;

    public Sistema(int capacidad) {
        usuarios = new Usuario[capacidad];
        contador = 0;
    }

    public boolean agregarUsuario(Usuario usuario) {
        if (contador < usuarios.length) {
            usuarios[contador++] = usuario;
            return true;
        }
        return false;
    }

    public Usuario login(String codigo, String password) {
        for (int i = 0; i < contador; i++) {
            if (usuarios[i].login(codigo, password)) {
                return usuarios[i];
            }
        }
        return null;
    }
}