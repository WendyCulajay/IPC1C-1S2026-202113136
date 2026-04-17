package Modelo;

public class Estudiante extends Usuario {

    private String carnet;

    public Estudiante(String codigo, String nombre, String password, String fechaNacimiento, String genero, String carnet) {
        super(codigo, nombre, password, fechaNacimiento, genero);
        this.carnet = carnet;
    }

    @Override
    public String getRol() {
        return "Estudiante";
    }
}