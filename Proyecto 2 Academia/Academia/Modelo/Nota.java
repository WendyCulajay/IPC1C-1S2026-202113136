package Modelo;

// Clase que representa una nota de un estudiante en un curso
public class Nota {

    private String codigoCurso;
    private String carnetEstudiante;
    private double nota;

    // Constructor
    public Nota(String codigoCurso, String carnetEstudiante, double nota) {
        this.codigoCurso = codigoCurso;
        this.carnetEstudiante = carnetEstudiante;
        this.nota = nota;
    }

    // Getters y setters

    public String getCodigoCurso() {
        return codigoCurso;
    }

    public void setCodigoCurso(String codigoCurso) {
        this.codigoCurso = codigoCurso;
    }

    public String getCarnetEstudiante() {
        return carnetEstudiante;
    }

    public void setCarnetEstudiante(String carnetEstudiante) {
        this.carnetEstudiante = carnetEstudiante;
    }

    public double getNota() {
        return nota;
    }

    public void setNota(double nota) {
        this.nota = nota;
    }

    // Mostrar información
    public String mostrarInfo() {
        return "Curso: " + codigoCurso +
               " | Estudiante: " + carnetEstudiante +
               " | Nota: " + nota;
    }
}