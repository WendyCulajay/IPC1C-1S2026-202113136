package Vista;

import Modelo.Usuario;
import javax.swing.*;
import java.awt.*;

public class MenuAdmin extends JFrame {

    public MenuAdmin(Usuario usuario) {
        
        Color fondoBlanco = Color.WHITE;
        Color rosado = Color.decode("#D87093");
        Color grisTexto = Color.decode("#708090");

        setTitle("Sancarlista Academy - Panel de Administración");
        setSize(500, 500);
        setLayout(null);
        getContentPane().setBackground(fondoBlanco);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        // Encabezado
        JLabel lblTitulo = new JLabel("Bienvenido: " + usuario.getNombre());
        lblTitulo.setBounds(50, 20, 400, 30);
        lblTitulo.setFont(new Font("Arial", Font.BOLD, 18));
        lblTitulo.setForeground(grisTexto);
        add(lblTitulo);

        // Botones de Gestion  
        
        JButton btnInstructores = crearBoton("Gestión de Instructores", 80, rosado);
        add(btnInstructores);

        JButton btnEstudiantes = crearBoton("Gestión de Estudiantes", 140, rosado);
        add(btnEstudiantes);

        JButton btnCursos = crearBoton("Gestión de Cursos", 200, rosado);
        add(btnCursos);

        JButton btnSecciones = crearBoton("Gestión de Secciones", 260, rosado);
        add(btnSecciones);

        // Boton Cerrar Sesion 
        JButton btnLogout = new JButton("Cerrar Sesión");
        btnLogout.setBounds(150, 360, 200, 35);
        btnLogout.setBackground(Color.DARK_GRAY);
        btnLogout.setForeground(Color.WHITE);
        btnLogout.addActionListener(e -> {
            // Ventana de Login
            JOptionPane.showMessageDialog(this, "Sesión cerrada correctamente.");
            this.dispose(); 
        });
        add(btnLogout);

        setLocationRelativeTo(null);
    }

    // Metodo para estandarizar la creación de botones
    private JButton crearBoton(String texto, int y, Color color) {
        JButton boton = new JButton(texto);
        boton.setBounds(100, y, 300, 40);
        boton.setBackground(color);
        boton.setForeground(Color.WHITE);
        boton.setFocusPainted(false);
        boton.setFont(new Font("Arial", Font.BOLD, 13));
        return boton;
    }
}