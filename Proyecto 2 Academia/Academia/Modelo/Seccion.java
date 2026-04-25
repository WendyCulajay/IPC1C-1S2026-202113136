package Modelo;

import java.io.Serializable;

public class Seccion implements Serializable {

    private String codigo;      // Código único de la sección  
    private String nombre;      // Nombre descriptivo de la sección  
    private Curso curso;        // curso al que pertenece

    public Seccion(String codigo, String nombre, Curso curso) {
        this.codigo = codigo;
        this.nombre = nombre;
        this.curso = curso;
    }

    // Getters
    public String getCodigo() { return codigo; }
    public String getNombre() { return nombre; }
    public Curso getCurso() { return curso; }

    // Setters
    public void setCodigo(String codigo) { this.codigo = codigo; }
    public void setNombre(String nombre) { this.nombre = nombre; }
    public void setCurso(Curso curso) { this.curso = curso; }

    @Override
    public String toString() {
        return "Sección " + codigo + " - " + nombre +
               " | Curso: " + (curso != null ? curso.getNombre() : "Sin curso");
    }
}
