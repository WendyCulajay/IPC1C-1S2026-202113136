package Vista;

import Modelo.Usuario;
import javax.swing.*;
import java.awt.*;

// Ventana principal para el administrador
public class MenuAdmin extends JFrame {

    public MenuAdmin(Usuario usuario) {

        Color fondoBlanco = Color.WHITE;
        Color rosado = Color.decode("#D87093");
        Color grisTexto = Color.decode("#708090");

        setTitle("Menu Administrador");
        setSize(400, 300);
        setLayout(null);
        getContentPane().setBackground(fondoBlanco);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        JLabel lblTitulo = new JLabel("Bienvenido Admin: " + usuario.getNombre());
        lblTitulo.setBounds(80, 30, 300, 25);
        lblTitulo.setForeground(grisTexto);
        add(lblTitulo);

        JButton btnUsuarios = new JButton("Gestionar Usuarios");
        btnUsuarios.setBounds(100, 80, 180, 30);
        btnUsuarios.setBackground(rosado);
        btnUsuarios.setForeground(Color.WHITE);
        btnUsuarios.setFocusPainted(false);
        add(btnUsuarios);

        JButton btnCursos = new JButton("Gestionar Cursos");
        btnCursos.setBounds(100, 130, 180, 30);
        btnCursos.setBackground(rosado);
        btnCursos.setForeground(Color.WHITE);
        btnCursos.setFocusPainted(false);
        add(btnCursos);

        setLocationRelativeTo(null);
    }
}