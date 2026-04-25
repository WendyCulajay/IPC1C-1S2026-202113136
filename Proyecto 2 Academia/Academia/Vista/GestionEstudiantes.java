package Vista;

import Modelo.Estudiante;
import Modelo.Usuario;
import Controlador.Sistema;
import javax.swing.*;
import java.awt.*;
import java.io.*;

public class GestionEstudiantes extends JFrame {

    private final Sistema sistema;
    private final Color rosado = new Color(216, 112, 147);
    private final Color grisTexto = new Color(112, 128, 144);

    public GestionEstudiantes(Sistema sistema) {
        this.sistema = sistema;

        setTitle("Gestión de Estudiantes");
        setSize(420, 750);
        setLayout(null);
        getContentPane().setBackground(Color.WHITE);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);

        // Campos
        JTextField txtCod = crearCampo("Código", 20);
        JTextField txtNom = crearCampo("Nombre", 70);
        JTextField txtPass = crearCampo("Password", 120);
        JTextField txtCarnet = crearCampo("Carnet", 170);
        JTextField txtGenero = crearCampo("Género", 220);
        JTextField txtFechaNac = crearCampo("Fecha Nacimiento", 270);

        // Botones
        JButton btnGuardar = crearBoton("Guardar", 330);
        JButton btnActualizar = crearBoton("Actualizar", 370);
        JButton btnEliminar = crearBoton("Eliminar", 410);
        JButton btnListar = crearBoton("Listar", 450);
        JButton btnCargaCSV = crearBoton("Carga Masiva (CSV)", 490);
        JButton btnVolver = crearBoton("Volver", 530);

        // Añadir componentes
        add(txtCod); add(txtNom); add(txtPass); add(txtCarnet);
        add(txtGenero); add(txtFechaNac);
        add(btnGuardar); add(btnActualizar); add(btnEliminar);
        add(btnListar); add(btnCargaCSV); add(btnVolver);

        // ================= EVENTOS =================

        // Guardar
        btnGuardar.addActionListener(e -> {
            if (camposVacios(txtCod, txtNom, txtPass, txtCarnet, txtGenero, txtFechaNac)) return;

            Estudiante est = new Estudiante(
                    txtCod.getText(),
                    txtNom.getText(),
                    txtPass.getText(),
                    txtGenero.getText(),
                    txtFechaNac.getText(),
                    txtCarnet.getText()
            );

            String error = sistema.agregarUsuario(est);

            if (error == null) {
                JOptionPane.showMessageDialog(this, "Estudiante registrado");
                sistema.guardarDatos();
                limpiar(txtCod, txtNom, txtPass, txtCarnet, txtGenero, txtFechaNac);
            } else {
                JOptionPane.showMessageDialog(this, error);
            }
        });

        // Actualizar
        btnActualizar.addActionListener(e -> {
            boolean ok = sistema.actualizarUsuario(
                    txtCod.getText(),
                    txtNom.getText(),
                    txtPass.getText()
            );

            if (ok) {
                JOptionPane.showMessageDialog(this, "Actualizado");
                sistema.guardarDatos();
            } else {
                JOptionPane.showMessageDialog(this, "No encontrado");
            }
        });

        // Eliminar
        btnEliminar.addActionListener(e -> {
            int confirm = JOptionPane.showConfirmDialog(this, "¿Eliminar?");
            if (confirm == JOptionPane.YES_OPTION) {
                boolean ok = sistema.eliminarUsuario(txtCod.getText());

                if (ok) {
                    JOptionPane.showMessageDialog(this, "Eliminado");
                    sistema.guardarDatos();
                    limpiar(txtCod, txtNom, txtPass, txtCarnet, txtGenero, txtFechaNac);
                } else {
                    JOptionPane.showMessageDialog(this, "No encontrado");
                }
            }
        });

        // Listar
        btnListar.addActionListener(e -> {
            Usuario[] lista = sistema.obtenerUsuariosPorRol("Estudiante");
            StringBuilder sb = new StringBuilder("ESTUDIANTES:\n\n");

            if (lista != null) {
                for (Usuario u : lista) {
                    if (u != null) {
                        Estudiante est = (Estudiante) u;
                        sb.append(est.getCodigo()).append(" - ")
                          .append(est.getNombre()).append(" - Carnet: ")
                          .append(est.getCarnet()).append(" - Género: ")
                          .append(est.getGenero()).append(" - Fecha Nac: ")
                          .append(est.getFechaNacimiento()).append("\n");
                    }
                }
            }
            JOptionPane.showMessageDialog(this, sb.toString());
        });

        // Carga Masiva CSV
        btnCargaCSV.addActionListener(e -> cargarCSV());

        // Volver
        btnVolver.addActionListener(e -> dispose());

        setLocationRelativeTo(null);
        setVisible(true);
    }

    // ================= MÉTODOS AUXILIARES =================

    private void cargarCSV() {
        JFileChooser fileChooser = new JFileChooser();

        if (fileChooser.showOpenDialog(this) == JFileChooser.APPROVE_OPTION) {
            try (BufferedReader br = new BufferedReader(new FileReader(fileChooser.getSelectedFile()))) {
                String linea;
                while ((linea = br.readLine()) != null) {
                    String[] d = linea.split(",");
                    // Esperamos: codigo,nombre,password,genero,fechaNacimiento,carnet
                    if (d.length == 6) {
                        Estudiante est = new Estudiante(d[0], d[1], d[2], d[3], d[4], d[5]);
                        sistema.agregarUsuario(est);
                    }
                }
                sistema.guardarDatos();
                JOptionPane.showMessageDialog(this, "Carga completa");
            } catch (Exception ex) {
                JOptionPane.showMessageDialog(this, "Error en CSV");
            }
        }
    }

    private boolean camposVacios(JTextField... campos) {
        for (JTextField c : campos) {
            if (c.getText().trim().isEmpty()) {
                JOptionPane.showMessageDialog(this, "Campos vacíos");
                return true;
            }
        }
        return false;
    }

    private void limpiar(JTextField... campos) {
        for (JTextField c : campos) c.setText("");
    }

    private JTextField crearCampo(String t, int y) {
        JTextField f = new JTextField();
        f.setBounds(100, y, 200, 40);
        f.setBorder(BorderFactory.createTitledBorder(t));
        return f;
    }

    private JButton crearBoton(String t, int y) {
        JButton b = new JButton(t);
        b.setBounds(100, y, 200, 35);
        b.setBackground(rosado);
        b.setForeground(Color.WHITE);
        b.setFocusPainted(false);
        b.setFont(new Font("Arial", Font.BOLD, 14));
        return b;
    }
}
