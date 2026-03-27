package Recursos;

import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;
import java.util.List;

/**
 * Clase Principal del sistema Quetzal Escobas.
 * 
 * Esta clase representa la ventana principal del programa,
 * encargada de mostrar el menú y gestionar la navegación
 * entre las diferentes funcionalidades del sistema.
 * 
 * Autor: Wendy Culajay
 */
public class Principal extends JFrame {

    /** Colores personalizados para la interfaz */
    public static final Color NEGRO_MAGICO = new Color(15, 10, 25);
    public static final Color LILA_OSCURO = new Color(75, 0, 130);
    public static final Color LILA_CLARO = new Color(147, 0, 234);

    /** Lista de personajes disponibles para la carrera */
    private List<Personaje> personajes = new ArrayList<>();

    /** Historial de partidas jugadas */
    private List<String> historialPartidas = new ArrayList<>();

    /**
     * Constructor de la clase Principal.
     * Inicializa los personajes por defecto y configura la ventana.
     */
    public Principal() {
        personajes.add(new Personaje("Harry"));
        personajes.add(new Personaje("Hermione"));

        setTitle("Quetzal Escobas - Wendy Culajay");
        setSize(700, 600);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setLocationRelativeTo(null);
        initUI();
    }

    /**
     * Inicializa todos los componentes gráficos de la interfaz.
     */
    private void initUI() {

        // Panel principal con fondo degradado
        JPanel fondoMagico = new JPanel(new BorderLayout()) {
            @Override
            protected void paintComponent(Graphics g) {
                super.paintComponent(g);

                // Configuración de renderizado suave
                Graphics2D g2d = (Graphics2D) g;
                g2d.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);

                // Creación del degradado de fondo
                GradientPaint gp = new GradientPaint(
                        0, 0, LILA_OSCURO,
                        getWidth(), getHeight(), NEGRO_MAGICO);

                g2d.setPaint(gp);
                g2d.fillRect(0, 0, getWidth(), getHeight());
            }
        };

        // Título principal del sistema
        JLabel titulo = new JLabel("CARRERA DE ESCOBAS", SwingConstants.CENTER);
        titulo.setFont(new Font("Arial Black", Font.BOLD, 30));
        titulo.setForeground(LILA_CLARO);
        titulo.setBorder(BorderFactory.createEmptyBorder(30, 0, 0, 0));

        // Panel que contiene los botones del menú
        JPanel botonesPanel = new JPanel(new GridLayout(4, 1, 15, 15));
        botonesPanel.setOpaque(false);
        botonesPanel.setBorder(BorderFactory.createEmptyBorder(50, 200, 50, 200));

        // Creación de botones
        JButton btnJugar = crearBoton("Jugar");
        JButton btnCrear = crearBoton("Crear Personaje");
        JButton btnTop = crearBoton("Tops y Gráfica");
        JButton btnSalir = crearBoton("Salir");

        // Asignación de eventos a los botones
        btnJugar.addActionListener(e -> iniciarCarrera());

        btnCrear.addActionListener(e -> {
            String nom = JOptionPane.showInputDialog(this, "Nombre del Mago:");
            if (nom != null && !nom.isEmpty()) {
                personajes.add(new Personaje(nom));
            }
        });

        btnTop.addActionListener(e -> mostrarEstadisticas());

        btnSalir.addActionListener(e -> System.exit(0));

        // Agregar botones al panel
        botonesPanel.add(btnJugar);
        botonesPanel.add(btnCrear);
        botonesPanel.add(btnTop);
        botonesPanel.add(btnSalir);

        // Agregar componentes al panel principal
        fondoMagico.add(titulo, BorderLayout.NORTH);
        fondoMagico.add(botonesPanel, BorderLayout.CENTER);

        setContentPane(fondoMagico);
    }

    /**
     * Crea un botón con estilo personalizado.
     * 
     * @param texto Texto del botón
     * @return JButton estilizado
     */
    private JButton crearBoton(String texto) {
        JButton btn = new JButton(texto);
        btn.setFont(new Font("Arial", Font.BOLD, 16));
        btn.setBackground(LILA_OSCURO);
        btn.setForeground(Color.WHITE);
        return btn;
    }

    /**
     * Muestra la ventana de estadísticas con gráfica de barras.
     */
    private void mostrarEstadisticas() {

        // Validación de historial vacío
        if (historialPartidas.isEmpty()) {
            JOptionPane.showMessageDialog(this, "¡Juega una partida para ver los resultados!");
            return;
        }

        // Creación de ventana modal
        JDialog ventana = new JDialog(this, "Estadísticas Quetzal", true);
        ventana.setSize(500, 550);
        ventana.setLayout(new BorderLayout());

        // Panel donde se dibuja la gráfica
        JPanel grafica = new JPanel() {
            @Override
            protected void paintComponent(Graphics g) {
                super.paintComponent(g);
                dibujarBarras(g, getWidth(), getHeight());
            }
        };

        grafica.setBackground(NEGRO_MAGICO);

        // Botón para exportar reporte
        JButton btnPdf = crearBoton("Exportar Reporte a PDF");
        btnPdf.addActionListener(e -> exportarReporte());

        ventana.add(grafica, BorderLayout.CENTER);
        ventana.add(btnPdf, BorderLayout.SOUTH);
        ventana.setLocationRelativeTo(this);
        ventana.setVisible(true);
    }

    /**
     * Dibuja una gráfica de barras basada en el historial de partidas.
     * 
     * @param g Objeto Graphics
     * @param w Ancho del panel
     * @param h Alto del panel
     */
    private void dibujarBarras(Graphics g, int w, int h) {
        Graphics2D g2 = (Graphics2D) g;

        int x = 60;
        int yBase = h - 80;

        // Dibujar eje horizontal
        g2.setColor(Color.WHITE);
        g2.drawLine(x, yBase, w - 60, yBase);

        // Dibujar barras (máximo 5)
        for (int i = 0; i < Math.min(historialPartidas.size(), 5); i++) {
            g2.setColor(LILA_CLARO);

            int alto = (i + 1) * 40 + 20;
            g2.fillRect(x + 20 + (i * 70), yBase - alto, 40, alto);

            g2.setColor(Color.WHITE);
            g2.drawString("P" + (i + 1), x + 30 + (i * 70), yBase + 20);
        }
    }

    /**
     * Genera un reporte imprimible con el historial de partidas.
     */
    private void exportarReporte() {
        JTextArea areaReporte = new JTextArea();
        areaReporte.setFont(new Font("Monospaced", Font.PLAIN, 12));

        // Encabezado del reporte
        areaReporte.append("REPORTE DE TOPS - QUETZAL ESCOBAS\n");
        areaReporte.append("Wendy Culajay - IPC1\n");
        areaReporte.append("------------------------------------------\n");

        // Contenido del historial
        for (String registro : historialPartidas) {
            areaReporte.append(registro + "\n");
        }

        try {
            areaReporte.print(
                new java.text.MessageFormat("Reporte Quetzal"),
                new java.text.MessageFormat("Página {0}"),
                true, null, null, true
            );
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /**
     * Inicia una carrera seleccionando un personaje.
     */
    private void iniciarCarrera() {
        if (personajes.isEmpty()) return;

        Personaje sel = (Personaje) JOptionPane.showInputDialog(
                this,
                "Selecciona Corredor:",
                "Carrera",
                JOptionPane.PLAIN_MESSAGE,
                null,
                personajes.toArray(),
                personajes.get(0)
        );

        if (sel != null) {
            new VentanaCarrera(sel, historialPartidas).setVisible(true);
        }
    }

    /**
     * Método principal que inicia la aplicación.
     */
    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> new Principal().setVisible(true));
    }
}