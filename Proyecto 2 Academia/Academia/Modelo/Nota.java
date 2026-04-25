package Modelo;
import java.io.Serializable;

public class Nota implements Serializable {
    private String codigoEstudiante;
    private String codigoCurso;
    private double valor;

    public Nota(String codigoEstudiante, String codigoCurso, double valor) {
        this.codigoEstudiante = codigoEstudiante;
        this.codigoCurso = codigoCurso;
        this.valor = valor;
    }

    // Métodos para que el  Sistema  funcione correctamente
    public void setValor(double valor) { this.valor = valor; }
    public double getValor() { return valor; }

    public String getCodigoEstudiante() { return codigoEstudiante; }
    public String getCodigoCurso() { return codigoCurso; }
}