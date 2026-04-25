package Controlador;

import Modelo.Usuario;
import Modelo.Curso;

import java.io.*;

public class GestorDatos {

    // ================= GUARDAR =================
    public static void guardarUsuarios(Usuario[] usuarios, int cantidad) {
        try (ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream("usuarios.ser"))) {
            for (int i = 0; i < cantidad; i++) {
                if (usuarios[i] != null) {
                    oos.writeObject(usuarios[i]);
                }
            }
        } catch (IOException e) {
            System.out.println("Error al guardar usuarios: " + e.getMessage());
        }
    }

    public static void guardarCursos(Curso[] cursos, int cantidad) {
        try (ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream("cursos.ser"))) {
            for (int i = 0; i < cantidad; i++) {
                if (cursos[i] != null) {
                    oos.writeObject(cursos[i]);
                }
            }
        } catch (IOException e) {
            System.out.println("Error al guardar cursos: " + e.getMessage());
        }
    }

    // ================= CARGAR =================
    public static int cargarUsuarios(Usuario[] usuarios) {
        int contador = 0;
        try (ObjectInputStream ois = new ObjectInputStream(new FileInputStream("usuarios.ser"))) {
            while (true) {
                Usuario u = (Usuario) ois.readObject();
                usuarios[contador++] = u;
            }
        } catch (EOFException eof) {
            // Fin del archivo, es normal
        } catch (Exception e) {
            System.out.println("Error al cargar usuarios: " + e.getMessage());
        }
        return contador;
    }

    public static int cargarCursos(Curso[] cursos) {
        int contador = 0;
        try (ObjectInputStream ois = new ObjectInputStream(new FileInputStream("cursos.ser"))) {
            while (true) {
                Curso c = (Curso) ois.readObject();
                cursos[contador++] = c;
            }
        } catch (EOFException eof) {
        } catch (Exception e) {
            System.out.println("Error al cargar cursos: " + e.getMessage());
        }
        return contador;
    }
}
