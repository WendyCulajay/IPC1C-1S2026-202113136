package Vista;

import Controlador.Sistema;
import Modelo.Usuario;

import javax.swing.*;
import java.awt.*;

public class Login extends JFrame {

    private final Sistema sistema;

    public Login(Sistema sistema) {
        this.sistema = sistema;

        setTitle("Sancarlista Academy - Login");
        setSize(400, 350);
        setLayout(null);
        getContentPane().setBackground(Color.WHITE);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        // Colores corporativos
        Color rosado = new Color(216, 112, 147); 
        Color gris = new Color(112, 128, 144);

        // Título
        JLabel lblTitulo = new JLabel("Iniciar Sesión", SwingConstants.CENTER);
        lblTitulo.setBounds(50, 20, 300, 30);
        lblTitulo.setFont(new Font("Arial", Font.BOLD, 18));
        lblTitulo.setForeground(gris);
        add(lblTitulo);

        // Campos
        JTextField txtCodigo = new JTextField();
        txtCodigo.setBounds(100, 80, 200, 40);
        txtCodigo.setBorder(BorderFactory.createTitledBorder("Código"));
        add(txtCodigo);

        JPasswordField txtPassword = new JPasswordField();
        txtPassword.setBounds(100, 140, 200, 40);
        txtPassword.setBorder(BorderFactory.createTitledBorder("Password"));
        add(txtPassword);

        // Botón Login
        JButton btnLogin = new JButton("Ingresar");
        btnLogin.setBounds(100, 210, 200, 40);
        btnLogin.setBackground(rosado);
        btnLogin.setForeground(Color.WHITE);
        btnLogin.setFocusPainted(false);
        add(btnLogin);

        // ================= LOGIN =================
        btnLogin.addActionListener(e -> {
            String codigo = txtCodigo.getText().trim();
            String password = new String(txtPassword.getPassword()).trim();

            if (codigo.isEmpty() || password.isEmpty()) {
                JOptionPane.showMessageDialog(this, "Campos vacíos");
                return;
            }

            Usuario u = sistema.login(codigo, password);

            if (u != null) {
                JOptionPane.showMessageDialog(this, "Bienvenido " + u.getNombre());

                // 🔥 POLIMORFISMO: abrir menú según rol
                switch (u.getRol().toLowerCase()) {
                    case "administrador":
                        new MenuAdmin(sistema).setVisible(true);
                        break;
                    case "instructor":
                        new MenuInstructor(sistema, u).setVisible(true);
                        break;
                    case "estudiante":
                        new MenuEstudiante(sistema, u).setVisible(true);
                        break;
                    default:
                        JOptionPane.showMessageDialog(this, "Rol desconocido");
                        break;
                }

                this.dispose();
            } else {
                JOptionPane.showMessageDialog(this, "Credenciales incorrectas");
            }
        });

        setLocationRelativeTo(null);
        setVisible(true);
    }
}