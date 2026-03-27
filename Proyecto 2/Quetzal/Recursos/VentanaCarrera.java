package Recursos;

import javax.swing.*;
import java.awt.*;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

/**
 * Clase que representa la ventana de la carrera mágica.
 * 
 * Gestiona la lógica del juego en tiempo real, incluyendo:
 * - Movimiento del jugador
 * - Generación de obstáculos y premios
 * - Detección de colisiones
 * - Sistema de puntuación
 * 
 * Autor: Wendy Culajay
 */
public class VentanaCarrera extends JFrame {

    /** Personaje que participa en la carrera */
    private Personaje corredor;

    /** Referencia al historial de partidas de la ventana principal */
    private List<String> historialRef;

    /** Posición del jugador (mago) en la pantalla */
    private int xMago = 50, yMago = 200;

    /** Puntos acumulados durante la partida */
    private int puntos = 0;

    /** Control del estado del juego */
    private boolean juegoActivo = true;

    /** Lista de elementos mágicos (obstáculos y premios) */
    private List<ElementoMagico> elementos = new ArrayList<>();

    /**
     * Constructor de la ventana de carrera.
     * 
     * @param corredor  Personaje seleccionado por el usuario
     * @param historial Lista compartida para guardar resultados
     */
    public VentanaCarrera(Personaje corredor, List<String> historial) {
        this.corredor = corredor;
        this.historialRef = historial;

        setTitle("CARRERA MÁGICA: " + corredor.getNombre());
        setSize(800, 500);
        setDefaultCloseOperation(DISPOSE_ON_CLOSE);
        setLocationRelativeTo(null);
        setResizable(false);

        // Permitir captura de teclado
        this.setFocusable(true);
        this.requestFocusInWindow();

        /**
         * Panel principal donde se dibuja toda la escena del juego
         */
        JPanel panelJuego = new JPanel() {
            @Override
            protected void paintComponent(Graphics g) {
                super.paintComponent(g);
                dibujarEscena(g);
            }
        };

        panelJuego.setBackground(Principal.NEGRO_MAGICO);
        add(panelJuego);

        /**
         * Listener de teclado para controlar el movimiento del jugador
         */
        addKeyListener(new KeyAdapter() {
            @Override
            public void keyPressed(KeyEvent e) {
                if (!juegoActivo) return;

                int velocidad = 25;

                // Movimiento hacia arriba
                if (e.getKeyCode() == KeyEvent.VK_UP && yMago > 10) {
                    yMago -= velocidad;
                }

                // Movimiento hacia abajo
                if (e.getKeyCode() == KeyEvent.VK_DOWN && yMago < 400) {
                    yMago += velocidad;
                }

                // Actualizar pantalla
                repaint();
            }
        });

        iniciarCicloJuego();
    }

    /**
     * Inicia el ciclo principal del juego en un hilo separado.
     * 
     * Controla:
     * - Generación de elementos
     * - Movimiento de objetos
     * - Colisiones
     * - Actualización de puntaje
     */
    private void iniciarCicloJuego() {
        new Thread(() -> {
            Random rng = new Random();

            while (juegoActivo) {
                try {
                    Thread.sleep(30);

                    // Generación aleatoria de elementos (5% probabilidad)
                    if (rng.nextInt(100) < 5) {
                        String tipo = (rng.nextInt(10) < 7) ? "VOLDEMORT" : "SNITCH";
                        elementos.add(new ElementoMagico(800, rng.nextInt(400) + 20, tipo));
                    }

                    // Recorrer elementos
                    for (int i = 0; i < elementos.size(); i++) {
                        ElementoMagico el = elementos.get(i);

                        // Movimiento hacia la izquierda
                        el.x -= 8;

                        // Creación de áreas de colisión
                        Rectangle rectMago = new Rectangle(xMago, yMago, 40, 40);
                        Rectangle rectEl = new Rectangle(el.x, el.y, 30, 30);

                        // Detección de colisiones
                        if (rectMago.intersects(rectEl)) {
                            if (el.tipo.equals("SNITCH")) {
                                puntos += 500;
                                elementos.remove(i);
                            } else {
                                finalizarJuego("¡CHOCASTE CON VOLDEMORT!");
                                return;
                            }
                        }

                        // Eliminar elementos fuera de pantalla
                        if (el != null && el.x < -50) {
                            elementos.remove(i);
                        }
                    }

                    // Incremento de puntos por tiempo/supervivencia
                    puntos += 2;

                    repaint();

                } catch (Exception e) {
                    break;
                }
            }
        }).start();
    }

    /**
     * Dibuja todos los elementos visuales del juego.
     * 
     * @param g Objeto Graphics utilizado para renderizar
     */
    private void dibujarEscena(Graphics g) {
        Graphics2D g2 = (Graphics2D) g;

        // Suavizado de gráficos
        g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);

        // Dibujar jugador
        g2.setColor(Principal.LILA_CLARO);
        g2.fillOval(xMago, yMago, 45, 45);

        g2.setColor(Color.WHITE);
        g2.drawOval(xMago, yMago, 45, 45);
        g2.drawString(" " + corredor.getNombre(), xMago, yMago - 10);

        // Dibujar elementos mágicos
        for (ElementoMagico el : elementos) {
            if (el.tipo.equals("VOLDEMORT")) {
                g2.setColor(Color.RED);
                g2.fillOval(el.x, el.y, 30, 30);
                g2.setColor(Color.WHITE);
                g2.drawString("", el.x + 8, el.y + 20);
            } else {
                g2.setColor(Color.YELLOW);
                g2.fillOval(el.x, el.y, 25, 25);
                g2.setColor(Color.BLACK);
                g2.drawString("", el.x + 5, el.y + 18);
            }
        }

        // Mostrar puntaje
        g2.setColor(Color.WHITE);
        g2.setFont(new Font("Arial Black", Font.BOLD, 20));
        g2.drawString("PUNTOS: " + puntos, 30, 40);
    }

    /**
     * Finaliza la partida y muestra el resultado al usuario.
     * 
     * @param motivo Mensaje indicando la razón del final del juego
     */
    private void finalizarJuego(String motivo) {
        juegoActivo = false;

        // Registrar resultado
        String registro = corredor.getNombre() + " - " + puntos + " pts";
        historialRef.add(registro);

        JOptionPane.showMessageDialog(this, motivo + "\nTotal acumulado: " + puntos);

        // Cerrar ventana y regresar al menú principal
        this.dispose();
    }

    /**
     * Clase interna que representa un elemento dentro del juego.
     * Puede ser un obstáculo o un premio.
     */
    class ElementoMagico {
        int x, y;
        String tipo;

        /**
         * Constructor del elemento mágico.
         * 
         * @param x    Posición horizontal
         * @param y    Posición vertical
         * @param tipo Tipo de elemento ("VOLDEMORT" o "SNITCH")
         */
        ElementoMagico(int x, int y, String tipo) {
            this.x = x;
            this.y = y;
            this.tipo = tipo;
        }
    }
}