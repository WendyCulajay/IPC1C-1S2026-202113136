package Vista;

import Modelo.Usuario;
import javax.swing.*;
import java.awt.*;

public class MenuInstructor extends JFrame {

    public MenuInstructor(Usuario usuario) {

        Color fondoBlanco = Color.WHITE;
        Color rosado = Color.decode("#D87093");
        Color grisTexto = Color.decode("#708090");

        setTitle("Menu Instructor");
        setSize(400, 300);
        setLayout(null);
        getContentPane().setBackground(fondoBlanco);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        JLabel lblTitulo = new JLabel("Bienvenido Instructor: " + usuario.getNombre());
        lblTitulo.setBounds(60, 30, 300, 25);
        lblTitulo.setForeground(grisTexto);
        add(lblTitulo);

        JButton btnCursos = new JButton("Gestionar Cursos");
        btnCursos.setBounds(100, 100, 180, 30);
        btnCursos.setBackground(rosado);
        btnCursos.setForeground(Color.WHITE);
        btnCursos.setFocusPainted(false);
        add(btnCursos);

        btnCursos.addActionListener(e -> {
            JOptionPane.showMessageDialog(this, "Abriendo gestión de cursos...");
        });

        setLocationRelativeTo(null);
    }
}