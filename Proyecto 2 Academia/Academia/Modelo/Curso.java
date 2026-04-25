package Modelo;

import java.io.Serializable;

public class Curso implements Serializable {

    private String codigo;
    private String nombre;
    private String descripcion;
    private int creditos;

    public Curso(String codigo, String nombre, String descripcion, int creditos) {
        this.codigo = codigo;
        this.nombre = nombre;
        this.descripcion = descripcion;
        this.creditos = creditos;
    }

    // Getters
    public String getCodigo() { return codigo; }
    public String getNombre() { return nombre; }
    public String getDescripcion() { return descripcion; }
    public int getCreditos() { return creditos; }

    // Setters
    public void setCodigo(String codigo) { this.codigo = codigo; }
    public void setNombre(String nombre) { this.nombre = nombre; }
    public void setDescripcion(String descripcion) { this.descripcion = descripcion; }
    public void setCreditos(int creditos) {
        if (creditos >= 0) { 
            this.creditos = creditos;
        }
    }

    @Override
    public String toString() {
        return codigo + " - " + nombre + " (" + creditos + " créditos) | " + descripcion;
    }
}
