package Recursos;
import java.awt.*;

/**
 * Clase Elemento - Proyecto Quetzal Escobas
 * @author Wendy Culajay / 202113136
 * * Nota de 2do año: Aplicamos POO para que cada objeto sepa cómo dibujarse a sí mismo.
 */
public class Elemento {
    
    // Se definió un Enum para los tipos de objetos. 
    // Es mejor que usar constantes sueltas porque agrupa comportamiento y datos (simbolo, puntos).
    public enum Tipo { 
        SNITCH('S', 150), 
        BLUDGER('B', -2000), 
        QUAFFLE('Q', 10); 
        
        char simbolo; 
        int puntos;

        // Constructor del Enum (privado por defecto en Java)
        Tipo(char s, int p) { 
            this.simbolo = s; 
            this.puntos = p; 
        }
    }
    
    // Atributos privados: Aplicando el principio de encapsulamiento
    private Tipo tipo;
    private int posicion; // Valor de 0 a 100 para manejarlo como porcentaje de la pista

    // Constructor sobrecargado
    public Elemento(Tipo t, int pos) {
        this.tipo = t;
        this.posicion = pos;
    }

    /**
     * Método para dibujar el elemento en el componente gráfico.
     * @param g Objeto Graphics2D (se hace cast desde Graphics en el paintComponent)
     * @param xPista Coordenada X inicial de la pista
     * @param yPista Coordenada Y inicial de la pista
     * @param ancho Ancho total de la pista (para calcular la regla de 3 de la posición)
     * @param alto Alto de la pista para centrar el objeto
     */
    public void dibujar(Graphics2D g, int xPista, int yPista, int ancho, int alto) {
        // Cálculo de la posición X relativa al ancho de la pantalla (Responsive básico)
        int x = xPista + (int)(ancho * posicion / 100.0);
        
        // Se centra en el eje Y de la pista
        int y = yPista + (alto / 2) - 20;
        
        // --- Renderizado del objeto ---
        
        // 1. Se dibuja un "aura" o brillo con transparencia (Alpha = 100)
        g.setColor(new Color(255, 255, 255, 100));
        g.fillOval(x - 10, y - 10, 30, 30);
        
        // 2. Lógica de colores según el Tipo (Usando operador ternario anidado)
        // Ojo: Si escalamos a más tipos, sería mejor usar un switch para que sea legible.
        g.setColor(tipo == Tipo.SNITCH ? Color.YELLOW : (tipo == Tipo.BLUDGER ? Color.BLACK : Color.RED));
        g.fillOval(x - 5, y - 5, 20, 20);
        
        // 3. Se dibuja el símbolo representativo (char) para identificarlo rápido
        g.setColor(Color.WHITE);
        // Tip de Inge: String.valueOf para convertir el char del Enum y que drawString no de error
        g.drawString(String.valueOf(tipo.simbolo), x, y + 10);
    }
    
    // Getters necesarios para el cálculo de colisiones en la lógica de la Carrera
    public Tipo getTipo() { return tipo; }
    public int getPosicion() { return posicion; }
    
    // TODO: Implementar setters si se requiere movimiento dinámico de los elementos
} // <--- ¡Aquí faltaba cerrar la clase!