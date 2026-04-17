package Vista;

import Modelo.Usuario;
import Controlador.Sistema;
import javax.swing.*;
import java.awt.*;

public class Login extends JFrame {

    private Sistema sistema;

    public Login(Sistema sistema) {
        this.sistema = sistema;

        Color fondoBlanco = Color.WHITE;
        Color rosado = Color.decode("#D87093");
        Color grisTexto = Color.decode("#708090");

        setTitle("Login");
        setSize(500, 400);
        setLayout(null);
        getContentPane().setBackground(fondoBlanco);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        int x = 150;

        JLabel lblCodigo = new JLabel("Codigo:");
        lblCodigo.setBounds(x, 50, 100, 25);
        lblCodigo.setForeground(grisTexto);
        add(lblCodigo);

        JTextField txtCodigo = new JTextField();
        txtCodigo.setBounds(x, 75, 200, 25);
        add(txtCodigo);

        JLabel lblPassword = new JLabel("Password:");
        lblPassword.setBounds(x, 120, 100, 25);
        lblPassword.setForeground(grisTexto);
        add(lblPassword);

        JPasswordField txtPassword = new JPasswordField();
        txtPassword.setBounds(x, 145, 200, 25);
        add(txtPassword);

        JButton btnLogin = new JButton("Login");
        btnLogin.setBounds(x + 40, 200, 120, 35);
        btnLogin.setBackground(rosado);
        btnLogin.setForeground(Color.WHITE);
        btnLogin.setFocusPainted(false);
        add(btnLogin);

        btnLogin.addActionListener(e -> {

            String codigo = txtCodigo.getText();
            String password = new String(txtPassword.getPassword());

            Usuario u = sistema.login(codigo, password);

            if (u != null) {

                String rol = u.getRol();

                if (rol.equalsIgnoreCase("Administrador")) {
                    new MenuAdmin(u).setVisible(true);
                } else if (rol.equalsIgnoreCase("Estudiante")) {
                    new MenuEstudiante(u).setVisible(true);
                } else if (rol.equalsIgnoreCase("Instructor")) {
                    new MenuInstructor(u).setVisible(true);
                }

                dispose();

            } else {
                JOptionPane.showMessageDialog(null, "Error: datos incorrectos");
            }
        });

        setLocationRelativeTo(null);
    }
}