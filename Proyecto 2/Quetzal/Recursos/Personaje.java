package Recursos;
import java.io.Serializable;

/**
 * Clase Personaje - Proyecto Quetzal Escobas
 * @author Wendy Culajay / 202113136
  */
public class Personaje implements Serializable {
    
    // El atributo es private para aplicar el encapsulamiento. 
    // No quiero que otras clases modifiquen el nombre directamente sin pasar por lógica de control.
    private String nombre;

    /**
     * Constructor de la clase.
     * @param nombre Identificador del mago o bruja que competirá.
     */
    public Personaje(String nombre) {
        this.nombre = nombre;
    }

    /**
     * Método Getter. 
     * Es la forma estándar y segura de recuperar el atributo privado.
     * @return El nombre del personaje.
     */
    public String getNombre() {
        return nombre;
    }

    /**
     * Sobrescritura del método toString.
     * Super útil para cuando lleno JLists o JComboBoxes en la interfaz, 
     * ya que Swing usa este método para mostrar el texto en los componentes.
     */
    @Override
    public String toString() {
        return nombre;
    }
}