package Modelo;

public class Instructor extends Usuario {

    private String especialidad;

    public Instructor(String codigo, String nombre, String password, String fechaNacimiento, String genero, String especialidad) {
        super(codigo, nombre, password, fechaNacimiento, genero);
        this.especialidad = especialidad;
    }

    @Override
    public String getRol() {
        return "Instructor";
    }
}