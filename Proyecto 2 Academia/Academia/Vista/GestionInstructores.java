package Vista;

import Modelo.Instructor;
import Modelo.Usuario;
import Controlador.Sistema;

import javax.swing.*;
import java.awt.*;
import java.io.*;

public class GestionInstructores extends JFrame {

    private final Sistema sistema;
    private final Color rosado = new Color(216, 112, 147);
    private final Color grisTexto = new Color(112, 128, 144);

    public GestionInstructores(Sistema sistema) {
        this.sistema = sistema;

        setTitle("Gestión de Instructores");
        setSize(420, 700);
        setLayout(null);
        getContentPane().setBackground(Color.WHITE);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);

        // Campos
        JTextField txtCod = crearCampo("Código", 20);
        JTextField txtNom = crearCampo("Nombre", 70);
        JTextField txtPass = crearCampo("Password", 120);
        JTextField txtGenero = crearCampo("Género", 170);
        JTextField txtFechaNac = crearCampo("Fecha Nacimiento", 220);

        // Botones
        JButton btnGuardar = crearBoton("Guardar", 280);
        JButton btnActualizar = crearBoton("Actualizar", 320);
        JButton btnEliminar = crearBoton("Eliminar", 360);
        JButton btnListar = crearBoton("Listar", 400);
        JButton btnCargaCSV = crearBoton("Carga Masiva (CSV)", 440);
        JButton btnVolver = crearBoton("Volver", 480);

        add(txtCod); add(txtNom); add(txtPass); add(txtGenero); add(txtFechaNac);
        add(btnGuardar); add(btnActualizar); add(btnEliminar);
        add(btnListar); add(btnCargaCSV); add(btnVolver);

        // ===== GUARDAR =====
        btnGuardar.addActionListener(e -> {
            if (camposVacios(txtCod, txtNom, txtPass, txtGenero, txtFechaNac)) return;

            Instructor inst = new Instructor(
                    txtCod.getText(),
                    txtNom.getText(),
                    txtPass.getText(),
                    txtGenero.getText(),
                    txtFechaNac.getText()
            );

            String error = sistema.agregarUsuario(inst);

            if (error == null) {
                JOptionPane.showMessageDialog(this, "Instructor registrado");
                sistema.guardarDatos();
                limpiar(txtCod, txtNom, txtPass, txtGenero, txtFechaNac);
            } else {
                JOptionPane.showMessageDialog(this, error);
            }
        });

        // ===== ACTUALIZAR =====
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

        // ===== ELIMINAR =====
        btnEliminar.addActionListener(e -> {
            int confirm = JOptionPane.showConfirmDialog(this, "¿Eliminar?");
            if (confirm == JOptionPane.YES_OPTION) {
                boolean ok = sistema.eliminarUsuario(txtCod.getText());

                if (ok) {
                    JOptionPane.showMessageDialog(this, "Eliminado");
                    sistema.guardarDatos();
                    limpiar(txtCod, txtNom, txtPass, txtGenero, txtFechaNac);
                } else {
                    JOptionPane.showMessageDialog(this, "No encontrado");
                }
            }
        });

        // ===== LISTAR =====
        btnListar.addActionListener(e -> {
            Usuario[] lista = sistema.obtenerUsuariosPorRol("Instructor");
            StringBuilder sb = new StringBuilder("INSTRUCTORES:\n\n");

            if (lista != null) {
                for (Usuario u : lista) {
                    if (u != null) {
                        Instructor inst = (Instructor) u;
                        sb.append(inst.getCodigo()).append(" - ")
                          .append(inst.getNombre()).append(" - Género: ")
                          .append(inst.getGenero()).append(" - Fecha Nac: ")
                          .append(inst.getFechaNacimiento()).append("\n");
                    }
                }
            }
            JOptionPane.showMessageDialog(this, sb.toString());
        });

        // ===== CARGA MASIVA CSV =====
        btnCargaCSV.addActionListener(e -> cargarCSV());

        // ===== VOLVER =====
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
                    if (d.length == 5) {
                        Instructor inst = new Instructor(d[0], d[1], d[2], d[3], d[4]);
                        sistema.agregarUsuario(inst);
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
