package Vista;

import Modelo.Curso;
import Controlador.Sistema;

import javax.swing.*;
import java.awt.*;
import java.io.*;

public class GestionCursos extends JFrame {

    private final Sistema sistema;
    private final Color rosado = new Color(216, 112, 147);
    private final Color grisTexto = new Color(112, 128, 144);

    public GestionCursos(Sistema sistema) {
        this.sistema = sistema;

        setTitle("Gestión de Cursos");
        setSize(420, 600);
        setLayout(null);
        getContentPane().setBackground(Color.WHITE);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);

        // Campos
        JTextField txtCod = crearCampo("Código", 20);
        JTextField txtNom = crearCampo("Nombre Curso", 70);
        JTextField txtDesc = crearCampo("Descripción", 120);
        JTextField txtCred = crearCampo("Créditos", 170);

        // Botones
        JButton btnGuardar = crearBoton("Guardar Curso", 230);
        JButton btnActualizar = crearBoton("Actualizar Curso", 270);
        JButton btnEliminar = crearBoton("Eliminar Curso", 310);
        JButton btnCargaCSV = crearBoton("Carga Masiva (CSV)", 350);
        JButton btnVolver = crearBoton("Regresar", 390);

        add(txtCod); add(txtNom); add(txtDesc); add(txtCred);
        add(btnGuardar); add(btnActualizar); add(btnEliminar);
        add(btnCargaCSV); add(btnVolver);

        // ================= GUARDAR =================
        btnGuardar.addActionListener(e -> {
            try {
                if (camposVacios(txtCod, txtNom, txtDesc, txtCred)) return;

                Curso c = new Curso(
                        txtCod.getText(),
                        txtNom.getText(),
                        txtDesc.getText(),
                        Integer.parseInt(txtCred.getText())
                );

                String error = sistema.agregarCurso(c);

                if (error == null) {
                    JOptionPane.showMessageDialog(this, "Curso registrado");
                    sistema.guardarDatos();
                    limpiar(txtCod, txtNom, txtDesc, txtCred);
                } else {
                    JOptionPane.showMessageDialog(this, error, "Error", JOptionPane.ERROR_MESSAGE);
                }

            } catch (NumberFormatException ex) {
                JOptionPane.showMessageDialog(this, "Créditos debe ser número");
            }
        });

        // ================= ACTUALIZAR =================
        btnActualizar.addActionListener(e -> {
            try {
                if (camposVacios(txtCod, txtNom, txtDesc, txtCred)) return;

                boolean ok = sistema.actualizarCurso(
                        txtCod.getText(),
                        txtNom.getText(),
                        txtDesc.getText(),
                        Integer.parseInt(txtCred.getText())
                );

                if (ok) {
                    JOptionPane.showMessageDialog(this, "Curso actualizado");
                    sistema.guardarDatos();
                } else {
                    JOptionPane.showMessageDialog(this, "Curso no encontrado");
                }

            } catch (NumberFormatException ex) {
                JOptionPane.showMessageDialog(this, "Créditos inválidos");
            }
        });


        // ================= ELIMINAR =================
        btnEliminar.addActionListener(e -> {
            String codigo = txtCod.getText();

            if (codigo.trim().isEmpty()) {
                JOptionPane.showMessageDialog(this, "Ingrese código");
                return;
            }

            int confirm = JOptionPane.showConfirmDialog(this, "¿Eliminar curso?", "Confirmar", JOptionPane.YES_NO_OPTION);

            if (confirm == JOptionPane.YES_OPTION) {
                boolean ok = sistema.eliminarCurso(codigo);

                if (ok) {
                    JOptionPane.showMessageDialog(this, "Curso eliminado");
                    sistema.guardarDatos();
                    limpiar(txtCod, txtNom, txtDesc, txtCred);
                } else {
                    JOptionPane.showMessageDialog(this, "Curso no encontrado");
                }
            }
        });

        // ================= CSV =================
        btnCargaCSV.addActionListener(e -> cargarCSV());

        // ================= VOLVER =================
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
                    if (d.length == 4) {                         try {
                            sistema.agregarCurso(new Curso(d[0], d[1], d[2], Integer.parseInt(d[3])));
                        } catch (NumberFormatException ex) {
                            JOptionPane.showMessageDialog(this, "Créditos inválidos en CSV");
                        }
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
        for (JTextField c : campos) {
            c.setText("");
        }
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
