package Vista;

import Controlador.Sistema;
import Modelo.Usuario;

import javax.swing.*;
import java.awt.*;

public class MenuEstudiante extends JFrame {

    private final Sistema sistema;
    private final Usuario estudiante;
    private final Color rosado = new Color(216, 112, 147); 
    private final Color grisTexto = new Color(112, 128, 144);

    public MenuEstudiante(Sistema sistema, Usuario estudiante) {
        this.sistema = sistema;
        this.estudiante = estudiante;

        setTitle("Menú Estudiante");
        setSize(400, 500);
        setLayout(null);
        getContentPane().setBackground(Color.WHITE);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);

        // Título
        JLabel titulo = new JLabel("Estudiante: " + estudiante.getNombre(), SwingConstants.CENTER);
        titulo.setBounds(50, 20, 300, 30);
        titulo.setFont(new Font("Arial", Font.BOLD, 20));
        titulo.setForeground(grisTexto);
        add(titulo);

        // Botones
        JButton btnCursos = crearBoton("Ver Cursos", 100);
        JButton btnNotas = crearBoton("Ver Mis Notas", 160);
        JButton btnSalir = crearBoton("Cerrar Sesión", 220);

        add(btnCursos); add(btnNotas); add(btnSalir);

        // Acciones
        btnCursos.addActionListener(e -> {
            JOptionPane.showMessageDialog(this, sistema.obtenerListaCursos());
        });

        btnNotas.addActionListener(e -> {
            String notas = sistema.obtenerNotasPorEstudiante(estudiante.getCodigo());
            JOptionPane.showMessageDialog(this, notas);
        });

        btnSalir.addActionListener(e -> {
            new Login(sistema);
            dispose();
        });

        setLocationRelativeTo(null);
        setVisible(true);
    }

    // Método auxiliar para crear botones uniformes
    private JButton crearBoton(String texto, int y) {
        JButton btn = new JButton(texto);
        btn.setBounds(50, y, 300, 40);
        btn.setBackground(rosado);
        btn.setForeground(Color.WHITE);
        btn.setFocusPainted(false);
        btn.setFont(new Font("Arial", Font.BOLD, 14));
        return btn;
    }
}
