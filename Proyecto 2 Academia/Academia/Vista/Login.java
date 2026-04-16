package Vista;

import Modelo.Usuario;
import javax.swing.*;
import java.awt.*;

// Clase que representara la ventana de inicio de sesión del sistema
// Forma parte de la capa Vista en el MVC
public class Login extends JFrame {

    // Referencia al usuario del sistema (modelo)
    private Usuario usuario;

    // Constructor qpara validar credenciales
    public Login(Usuario usuario) {
        this.usuario = usuario;

        // colores para personalizar la interfaz
        Color fondoBlanco = Color.WHITE;
        Color rosado = Color.decode("#D87093");
        Color grisTexto = Color.decode("#708090");

        // Configuración general de la ventana
        setTitle("Login - Sancarlista Academy");
        setSize(500, 400); // tamaño de ventana
        setLayout(null); // Se utiliza posicionamiento absoluto
        getContentPane().setBackground(fondoBlanco);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        // Coordenadas base para centrar
        int centroX = 150;
        int espacioY = 40;

        // Etiqueta para el campo de código
        JLabel lblCodigo = new JLabel("Codigo:");
        lblCodigo.setBounds(centroX, espacioY, 100, 25);
        lblCodigo.setForeground(grisTexto);
        add(lblCodigo);

        // Campo de texto para ingresar el código del usuario
        JTextField txtCodigo = new JTextField();
        txtCodigo.setBounds(centroX, espacioY + 25, 200, 25);
        add(txtCodigo);

        // Etiqueta para el campo de contraseña
        JLabel lblPassword = new JLabel("Password:");
        lblPassword.setBounds(centroX, espacioY + 70, 100, 25);
        lblPassword.setForeground(grisTexto);
        add(lblPassword);

        // Campo de contraseña  y  oculta los caracteres ingresados con puntitos
        JPasswordField txtPassword = new JPasswordField();
        txtPassword.setBounds(centroX, espacioY + 95, 200, 25);
        add(txtPassword);

        // Botón para iniciar sesión
        JButton btnLogin = new JButton("Login");
        btnLogin.setBounds(centroX + 40, espacioY + 150, 120, 35);
        btnLogin.setBackground(rosado);
        btnLogin.setForeground(Color.WHITE);
        btnLogin.setFocusPainted(false); // Quita el borde de enfoque
        add(btnLogin);

        btnLogin.addActionListener(e -> {

            // Se obtienen los datos ingresados por el usuario
            String codigo = txtCodigo.getText();
            String password = new String(txtPassword.getPassword());

            // Validación de credenciales usando el método del modelo
            if (usuario.login(codigo, password)) {

                // Mensaje de acceso exitoso
                JOptionPane.showMessageDialog(null,
                        "Bienvenido " + usuario.getNombre());

            } else {

                // Mensaje de error en caso de credenciales incorrectas
                JOptionPane.showMessageDialog(null,
                        "Error: datos incorrectos");
            }
        });

        setLocationRelativeTo(null);
    }
}