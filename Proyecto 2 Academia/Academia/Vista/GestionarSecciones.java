package Vista;

import Controlador.Sistema;
import javax.swing.*;
import java.awt.*;

public class GestionarSecciones extends JFrame {

    private final Sistema sistema;
    private final Color rosado = new Color(216, 112, 147); 
    private final Color grisTexto = new Color(112, 128, 144);

    public GestionarSecciones(Sistema sistema) {
        this.sistema = sistema;

        setTitle("Sancarlista Academy - Gestión de Secciones");
        setSize(500, 450);
        setLayout(null);
        getContentPane().setBackground(Color.WHITE);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);

        // Título
        JLabel lblTitulo = new JLabel("Administración de Secciones", SwingConstants.CENTER);
        lblTitulo.setBounds(50, 30, 400, 30);
        lblTitulo.setFont(new Font("Arial", Font.BOLD, 18));
        lblTitulo.setForeground(grisTexto);
        add(lblTitulo);

        // Botones
        JButton btnCrear = crearBoton("Agregar Nueva Sección", 100);
        JButton btnListar = crearBoton("Listado de Secciones", 170);
        JButton btnVolver = crearBoton("Regresar al Menú", 240);

        add(btnCrear); add(btnListar); add(btnVolver);

        // ================= EVENTOS =================

        // Crear Sección
        btnCrear.addActionListener(e -> {
            String codigo = JOptionPane.showInputDialog(this, "Ingrese código de la sección:");
            if (esVacio(codigo, "Código inválido")) return;

            String nombre = JOptionPane.showInputDialog(this, "Ingrese nombre de la sección:");
            if (esVacio(nombre, "Nombre inválido")) return;

            String codigoCurso = JOptionPane.showInputDialog(this, "Ingrese código del curso:");
            if (esVacio(codigoCurso, "Código de curso inválido")) return;

            // Validar que el curso exista
            if (sistema.buscarCurso(codigoCurso) == null) {
                JOptionPane.showMessageDialog(this, "El curso no existe");
                return;
            }

            boolean exito = sistema.agregarSeccion(codigo, nombre, codigoCurso);

            if (exito) {
                JOptionPane.showMessageDialog(this, "Sección agregada correctamente");
                sistema.guardarDatos();
            } else {
                JOptionPane.showMessageDialog(this, "Error: código duplicado");
            }
        });

        // Listar Secciones
        btnListar.addActionListener(e -> {
            String lista = sistema.obtenerListaSecciones();
            JOptionPane.showMessageDialog(this, lista, "Listado de Secciones", JOptionPane.INFORMATION_MESSAGE);
        });

        // Volver
        btnVolver.addActionListener(e -> dispose());

        setLocationRelativeTo(null);
        setVisible(true);
    }

    // ================= MÉTODOS AUXILIARES =================

    private JButton crearBoton(String texto, int y) {
        JButton boton = new JButton(texto);
        boton.setBounds(100, y, 300, 45);
        boton.setBackground(rosado);
        boton.setForeground(Color.WHITE);
        boton.setFocusPainted(false);
        boton.setFont(new Font("Arial", Font.BOLD, 13));
        return boton;
    }

    private boolean esVacio(String valor, String mensaje) {
        if (valor == null || valor.trim().isEmpty()) {
            JOptionPane.showMessageDialog(this, mensaje);
            return true;
        }
        return false;
    }
}
