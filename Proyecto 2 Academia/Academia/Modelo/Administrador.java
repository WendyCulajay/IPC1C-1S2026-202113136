package Modelo;

public class Administrador extends Usuario {

    public Administrador(String codigo, String nombre, String password, String fechaNacimiento, String genero) {
        super(codigo, nombre, password, fechaNacimiento, genero);
    }

    @Override
    public String getRol() {
        return "Administrador";
    }
}