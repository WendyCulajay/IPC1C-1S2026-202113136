package Vista;

import Controlador.Sistema;
import Modelo.Usuario;

import javax.swing.*;
import java.awt.*;

public class MenuInstructor extends JFrame {

    private final Sistema sistema;
    private final Usuario instructor;
    private final Color rosado = new Color(216, 112, 147); 
    private final Color grisTexto = new Color(112, 128, 144);

    public MenuInstructor(Sistema sistema, Usuario instructor) {
        this.sistema = sistema;
        this.instructor = instructor;

        setTitle("Menú Instructor");
        setSize(400, 500);
        setLayout(null);
        getContentPane().setBackground(Color.WHITE);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);

        // Título
        JLabel titulo = new JLabel("Instructor: " + instructor.getNombre(), SwingConstants.CENTER);
        titulo.setBounds(50, 20, 300, 30);
        titulo.setFont(new Font("Arial", Font.BOLD, 20));
        titulo.setForeground(grisTexto);
        add(titulo);

        // Botones
        JButton btnNotas = crearBoton("Gestionar Notas", 100);
        JButton btnCursos = crearBoton("Ver Cursos Asignados", 160);
        JButton btnSalir = crearBoton("Cerrar Sesión", 220);

        add(btnNotas); add(btnCursos); add(btnSalir);

        // Acción: Gestionar Notas
        btnNotas.addActionListener(e -> {
            String est = JOptionPane.showInputDialog(this, "Código Estudiante:");
            String cur = JOptionPane.showInputDialog(this, "Código Curso:");
            String val = JOptionPane.showInputDialog(this, "Nota:");

            try {
                String r = sistema.asignarNota(est, cur, Double.parseDouble(val));
                JOptionPane.showMessageDialog(this, r);
                sistema.guardarDatos();
            } catch (Exception ex) {
                JOptionPane.showMessageDialog(this, "Error en datos");
            }
        });

        // Acción: Ver Cursos Asignados
        btnCursos.addActionListener(e -> {
            String lista = sistema.obtenerListaCursos();
            JOptionPane.showMessageDialog(this, lista);
        });

        // Acción: Cerrar Sesión
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
