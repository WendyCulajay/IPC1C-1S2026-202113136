package Vista;

import Controlador.Sistema;
import javax.swing.*;
import java.awt.*;

public class MenuAdmin extends JFrame {

    private final Sistema sistema;
    private final Color rosado = new Color(216, 112, 147);
    private final Color grisTexto = new Color(112, 128, 144);

    public MenuAdmin(Sistema sistema) {
        this.sistema = sistema;

        setTitle("Menú Administrador");
        setSize(400, 600);
        setLayout(null);
        getContentPane().setBackground(Color.WHITE);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);

        // Título
        JLabel titulo = new JLabel("Administrador", SwingConstants.CENTER);
        titulo.setBounds(50, 20, 300, 30);
        titulo.setFont(new Font("Arial", Font.BOLD, 22));
        titulo.setForeground(grisTexto);
        add(titulo);

        // Botones
        JButton btnEst = crearBoton("Gestionar Estudiantes", 80);
        JButton btnInst = crearBoton("Gestionar Instructores", 130);
        JButton btnCursos = crearBoton("Gestionar Cursos", 180);
        JButton btnSecciones = crearBoton("Gestionar Secciones", 230);
        JButton btnNotas = crearBoton("Asignar Notas", 280);
        JButton btnSalir = crearBoton("Cerrar Sesión", 350);

        add(btnEst); add(btnInst); add(btnCursos);
        add(btnSecciones); add(btnNotas); add(btnSalir);

        // Acciones
        btnEst.addActionListener(e -> new GestionEstudiantes(sistema));
        btnInst.addActionListener(e -> new GestionInstructores(sistema));
        btnCursos.addActionListener(e -> new GestionCursos(sistema));
        btnSecciones.addActionListener(e -> new GestionarSecciones(sistema));

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