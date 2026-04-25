package Modelo;

import java.io.Serializable;

public class Instructor extends Usuario implements Serializable {

    private String genero;
    private String fechaNacimiento;

    public Instructor(String codigo, String nombre, String password,
                      String genero, String fechaNacimiento) {
        // El rol siempre será "Instructor"
        super(codigo, nombre, password, "Instructor");
        this.genero = genero;
        this.fechaNacimiento = fechaNacimiento;
    }

    // Getters
    public String getGenero() { return genero; }
    public String getFechaNacimiento() { return fechaNacimiento; }

    // Setters
    public void setGenero(String genero) { this.genero = genero; }
    public void setFechaNacimiento(String fechaNacimiento) { this.fechaNacimiento = fechaNacimiento; }

    @Override
    public String toString() {
        return getCodigo() + " - " + getNombre() +
               " - Género: " + genero +
               " - Fecha Nac: " + fechaNacimiento;
    }
}
