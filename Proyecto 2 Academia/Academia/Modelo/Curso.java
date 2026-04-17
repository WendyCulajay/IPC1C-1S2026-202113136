package Modelo;

// Clase que representa un curso dentro del sistema
public class Curso {

    private String codigo;
    private String nombre;
    private int creditos;

    // Constructor
    public Curso(String codigo, String nombre, int creditos) {
        this.codigo = codigo;
        this.nombre = nombre;
        this.creditos = creditos;
    }

    // Getters y setters

    public String getCodigo() {
        return codigo;
    }

    public void setCodigo(String codigo) {
        this.codigo = codigo;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public int getCreditos() {
        return creditos;
    }

    public void setCreditos(int creditos) {
        this.creditos = creditos;
    }

    // Mostrar información básica del curso
    public String mostrarInfo() {
        return "Curso: " + nombre + " | Codigo: " + codigo + " | Creditos: " + creditos;
    }
} 