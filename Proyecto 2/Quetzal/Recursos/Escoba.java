package Recursos;
import java.io.Serializable;

/**
 * Clase Escoba - Proyecto Quetzal Escobas
 * @author Wendy Culajay / 20211313 
 */
public class Escoba implements Serializable {
    
    // Atributo privado para cumplir con el estándar de JavaBeans y seguridad de datos
    private String nombre;

    /**
     * Constructor para inicializar el objeto Escoba.
     * @param nombre El modelo de la escoba (ej. "Nimbus 2000", "Saeta de Fuego")
     */
    public Escoba(String nombre) {
        this.nombre = nombre;
    }

    /**
     * Método Getter: Como el atributo es private, este es el único 
     * punto de acceso público para leer el nombre.
     * @return El nombre/modelo de la escoba
     */
    public String getNombre() {
        return nombre;
    }
    
    // Tip de Sistemas: Podríamos agregar un método toString() si luego 
    // queremos mostrar el nombre directamente en un JComboBox.
    @Override
    public String toString() {
        return nombre;
    }
}