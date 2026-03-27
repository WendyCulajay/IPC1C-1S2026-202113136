package Recursos;

import java.io.Serializable;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

/**
 * Clase que representa una partida dentro del sistema.
 * 
 * Contiene la información del jugador, los puntos obtenidos,
 * el ganador de la partida y la fecha en que se jugó.
 * 
 * Implementa:
 * - Serializable: permite guardar objetos en archivos.
 * - Comparable: permite ordenar partidas por puntaje.
 * 
 * Autor: Wendy Culajay
 */
public class Partida implements Serializable, Comparable<Partida> {

    /** Jugador que participó en la partida */
    private Personaje jugador;

    /** Puntaje obtenido por el jugador */
    private int puntosJugador;

    /** Nombre del ganador de la partida */
    private String ganador;

    /** Fecha y hora en que se jugó la partida */
    private LocalDateTime fecha;

    /**
     * Constructor de la clase Partida.
     * 
     * Inicializa los atributos principales y asigna la fecha actual.
     * 
     * @param jugador        Personaje que participó en la partida
     * @param puntosJugador  Puntaje obtenido por el jugador
     * @param ganador        Nombre del ganador de la partida
     */
    public Partida(Personaje jugador, int puntosJugador, String ganador) {
        this.jugador = jugador;
        this.puntosJugador = puntosJugador;
        this.ganador = ganador;
        this.fecha = LocalDateTime.now();
    }

    /**
     * Obtiene el puntaje del jugador.
     * 
     * @return Puntaje obtenido en la partida
     */
    public int getPuntos() {
        return puntosJugador;
    }

    /**
     * Método para comparar partidas según su puntaje.
     * 
     * Se utiliza para ordenar las partidas de mayor a menor puntaje.
     * 
     * @param otra Otra partida con la cual se compara
     * @return valor negativo, cero o positivo según la comparación
     */
    @Override
    public int compareTo(Partida otra) {
        return Integer.compare(otra.puntosJugador, this.puntosJugador);
    }

    /**
     * Representación en texto de la partida.
     * 
     * Incluye el nombre del jugador, el puntaje y la fecha formateada.
     * 
     * @return Cadena con la información de la partida
     */
    @Override
    public String toString() {
        DateTimeFormatter dtf = DateTimeFormatter.ofPattern("dd/MM/yyyy HH:mm");
        return jugador.getNombre() + " - Pts: " + puntosJugador + 
               " (" + dtf.format(fecha) + ")";
    }
}