package Vista;

import Modelo.Usuario;
import javax.swing.*;
import java.awt.*;

// Ventana para el estudiante
public class MenuEstudiante extends JFrame {

    public MenuEstudiante(Usuario usuario) {

        Color fondoBlanco = Color.WHITE;
        Color rosado = Color.decode("#D87093");
        Color grisTexto = Color.decode("#708090");

        setTitle("Menu Estudiante");
        setSize(400, 300);
        setLayout(null);
        getContentPane().setBackground(fondoBlanco);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        JLabel lblTitulo = new JLabel("Bienvenido Estudiante: " + usuario.getNombre());
        lblTitulo.setBounds(60, 30, 300, 25);
        lblTitulo.setForeground(grisTexto);
        add(lblTitulo);

        JButton btnVerCursos = new JButton("Ver Cursos");
        btnVerCursos.setBounds(110, 100, 160, 30);
        btnVerCursos.setBackground(rosado);
        btnVerCursos.setForeground(Color.WHITE);
        btnVerCursos.setFocusPainted(false);
        add(btnVerCursos);

        setLocationRelativeTo(null);
    }
}