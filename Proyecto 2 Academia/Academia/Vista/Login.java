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

        setTitle("Login - Sancarlista Academy");
        setSize(500, 450); // Aumentado un poco para el combo box
        setLayout(null);
        getContentPane().setBackground(fondoBlanco);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        int x = 150;

        // Etiqueta de Codigo
        JLabel lblCodigo = new JLabel("Código:");
        lblCodigo.setBounds(x, 40, 100, 25);
        lblCodigo.setForeground(grisTexto);
        add(lblCodigo);

        JTextField txtCodigo = new JTextField();
        txtCodigo.setBounds(x, 65, 200, 25);
        add(txtCodigo);

        // Etiqueta de Password
        JLabel lblPassword = new JLabel("Password:");
        lblPassword.setBounds(x, 105, 100, 25);
        lblPassword.setForeground(grisTexto);
        add(lblPassword);

        JPasswordField txtPassword = new JPasswordField();
        txtPassword.setBounds(x, 130, 200, 25);
        add(txtPassword);

        // Opcion de Rol: Seleccionar Rol 
        JLabel lblRol = new JLabel("Entrar como:");
        lblRol.setBounds(x, 170, 100, 25);
        lblRol.setForeground(grisTexto);
        add(lblRol);

        String[] roles = {"Administrador", "Instructor", "Estudiante"};
        JComboBox<String> cbRoles = new JComboBox<>(roles);
        cbRoles.setBounds(x, 195, 200, 25);
        cbRoles.setBackground(Color.WHITE);
        add(cbRoles);

        // Boton Login
        JButton btnLogin = new JButton("Ingresar");
        btnLogin.setBounds(x + 40, 250, 120, 35);
        btnLogin.setBackground(rosado);
        btnLogin.setForeground(Color.WHITE);
        btnLogin.setFocusPainted(false);
        add(btnLogin);

        btnLogin.addActionListener(e -> {
            String codigo = txtCodigo.getText();
            String password = new String(txtPassword.getPassword());
            String rolSeleccionado = (String) cbRoles.getSelectedItem();

            // 1. Se busca si el usuario existe y la clave es correcta
            Usuario u = sistema.login(codigo, password);

            if (u != null) {
                // 2. Validacion de Rol: Se comprueba si el rol real coincide con el seleccionado
                if (u.getRol().equalsIgnoreCase(rolSeleccionado)) {
                    
                    // Redireccion segun rol
                    if (rolSeleccionado.equals("Administrador")) {
                        new MenuAdmin(u).setVisible(true);
                    } else if (rolSeleccionado.equals("Estudiante")) {
                        new MenuEstudiante(u).setVisible(true);
                    } else if (rolSeleccionado.equals("Instructor")) {
                        new MenuInstructor(u).setVisible(true);
                    }
                    
                    this.dispose(); // Cerrar login
                    
                } else {
                    // Si el usuario existe pero eligio un rol que no le pertenece
                    JOptionPane.showMessageDialog(this, 
                        "Acceso Denegado: Usted no tiene permisos de " + rolSeleccionado, 
                        "Error de Rol", JOptionPane.WARNING_MESSAGE);
                }

            } else {
                JOptionPane.showMessageDialog(this, "Código o contraseña incorrectos", "Error", JOptionPane.ERROR_MESSAGE);
            }
        });

        setLocationRelativeTo(null);
    }
}