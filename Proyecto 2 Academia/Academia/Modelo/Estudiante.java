package Modelo;

import java.io.Serializable;

public class Estudiante extends Usuario implements Serializable {

    private String carnet;
    private String genero;
    private String fechaNacimiento;

    public Estudiante(String codigo, String nombre, String password,
                      String genero, String fechaNacimiento, String carnet) {
        // El rol siempre será "Estudiante"
        super(codigo, nombre, password, "Estudiante");
        this.genero = genero;
        this.fechaNacimiento = fechaNacimiento;
        this.carnet = carnet;
    }

    // Getters
    public String getCarnet() { return carnet; }
    public String getGenero() { return genero; }
    public String getFechaNacimiento() { return fechaNacimiento; }

    // Setters con validación básica
    public void setCarnet(String carnet) {
        if (carnet != null && !carnet.trim().isEmpty()) {
            this.carnet = carnet;
        }
    }

    public void setGenero(String genero) {
        if (genero != null && !genero.trim().isEmpty()) {
            this.genero = genero;
        }
    }

    public void setFechaNacimiento(String fechaNacimiento) {
        if (fechaNacimiento != null && !fechaNacimiento.trim().isEmpty()) {
            this.fechaNacimiento = fechaNacimiento;
        }
    }

    @Override
    public String toString() {
        return "Código: " + getCodigo() +
               " | Nombre: " + getNombre() +
               " | Carnet: " + carnet +
               " | Género: " + genero +
               " | Fecha Nac: " + fechaNacimiento;
    }
}