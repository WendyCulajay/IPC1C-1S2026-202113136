package Controlador;

import Modelo.Usuario;
import Modelo.Curso;
import Modelo.Seccion;
import Modelo.Nota;

public class Sistema {

    private Usuario[] usuarios;
    private int contador;

    private Curso[] cursos;
    private int contadorCursos;

    private Seccion[] secciones;
    private int contadorSecciones;

    private Nota[] notas;
    private int contadorNotas;

    public Sistema() {
        this(100);
        usuarios[contador++] = new Usuario("admin", "Administrador", "IPC1C", "Administrador");
    }

    public Sistema(int capacidad) {
        usuarios = new Usuario[capacidad];
        cursos = new Curso[capacidad];
        secciones = new Seccion[capacidad];
        notas = new Nota[capacidad];

        contador = 0;
        contadorCursos = 0;
        contadorSecciones = 0;
        contadorNotas = 0;
    }

    // ================= USUARIOS =================
    private void crecerUsuarios() {
        Usuario[] nuevo = new Usuario[usuarios.length * 2];
        for (int i = 0; i < usuarios.length; i++) {
            nuevo[i] = usuarios[i];
        }
        usuarios = nuevo;
    }

    public Usuario[] obtenerUsuariosPorRol(String rol) {
        int count = 0;
        for (int i = 0; i < contador; i++) {
            if (usuarios[i] != null && usuarios[i].getRol().equalsIgnoreCase(rol)) {
                count++;
            }
        }

        Usuario[] resultado = new Usuario[count];
        int j = 0;
        for (int i = 0; i < contador; i++) {
            if (usuarios[i] != null && usuarios[i].getRol().equalsIgnoreCase(rol)) {
                resultado[j++] = usuarios[i];
            }
        }
        return resultado;
    }

    public String verificarDuplicados(String codigo, String nombre, String carnet) {
        for (int i = 0; i < contador; i++) {
            Usuario u = usuarios[i];
            if (u != null) {
                if (u.getCodigo().equalsIgnoreCase(codigo)) return "Código ya existe";
                if (u.getNombre().equalsIgnoreCase(nombre)) return "Nombre ya existe";
                if (carnet != null && u.getCarnet() != null &&
                    u.getCarnet().equalsIgnoreCase(carnet)) return "Carnet ya existe";
            }
        }
        return null;
    }

    public String agregarUsuario(Usuario usuario) {
        if (usuario == null) return "Usuario inválido";

        String error = verificarDuplicados(usuario.getCodigo(), usuario.getNombre(), usuario.getCarnet());
        if (error != null) return error;

        if (contador == usuarios.length) crecerUsuarios();

        usuarios[contador++] = usuario;
        return null;
    }

    public Usuario buscarUsuario(String codigo) {
        for (int i = 0; i < contador; i++) {
            if (usuarios[i] != null && usuarios[i].getCodigo().equalsIgnoreCase(codigo)) {
                return usuarios[i];
            }
        }
        return null;
    }

    public boolean actualizarUsuario(String codigo, String nombre, String password) {
        for (int i = 0; i < contador; i++) {
            if (usuarios[i] != null && usuarios[i].getCodigo().equalsIgnoreCase(codigo)) {
                usuarios[i].setNombre(nombre);
                usuarios[i].setPassword(password);
                return true;
            }
        }
        return false;
    }

    public boolean eliminarUsuario(String codigo) {
        for (int i = 0; i < contador; i++) {
            if (usuarios[i] != null && usuarios[i].getCodigo().equalsIgnoreCase(codigo)) {
                usuarios[i] = usuarios[contador - 1];
                usuarios[contador - 1] = null;
                contador--;
                return true;
            }
        }
        return false;
    }

    public Usuario login(String codigo, String password) {
        for (int i = 0; i < contador; i++) {
            if (usuarios[i] != null &&
                usuarios[i].getCodigo().equalsIgnoreCase(codigo) &&
                usuarios[i].getPassword().equals(password)) {
                return usuarios[i];
            }
        }
        return null;
    }

    // ================= CURSOS =================
    public String agregarCurso(Curso curso) {
        for (int i = 0; i < contadorCursos; i++) {
            if (cursos[i] != null && cursos[i].getCodigo().equals(curso.getCodigo())) {
                return "Error: código de curso duplicado";
            }
        }
        cursos[contadorCursos++] = curso;
        return null;
    }

    public boolean actualizarCurso(String codigo, String nombre, String descripcion, int creditos) {
    for (int i = 0; i < contadorCursos; i++) {
        if (cursos[i] != null && cursos[i].getCodigo().equals(codigo)) {
            cursos[i].setNombre(nombre);
            cursos[i].setDescripcion(descripcion);
            cursos[i].setCreditos(creditos);
            return true;
        }
    }
    return false;
}


    public boolean eliminarCurso(String codigo) {
        for (int i = 0; i < contadorCursos; i++) {
            if (cursos[i] != null && cursos[i].getCodigo().equals(codigo)) {
                cursos[i] = cursos[contadorCursos - 1];
                cursos[contadorCursos - 1] = null;
                contadorCursos--;
                return true;
            }
        }
        return false;
    }

    public String obtenerListaCursos() {
        StringBuilder sb = new StringBuilder("CURSOS:\n\n");
        for (int i = 0; i < contadorCursos; i++) {
            if (cursos[i] != null) {
                sb.append(cursos[i].toString()).append("\n");
            }
        }
        return sb.toString();
    }

    // ================= SECCIONES =================
    public boolean agregarSeccion(String codigo, String nombre, String codigoCurso) {
        Curso curso = buscarCurso(codigoCurso);
        if (curso == null) return false;

        for (int i = 0; i < contadorSecciones; i++) {
            if (secciones[i] != null && secciones[i].getCodigo().equals(codigo)) {
                return false;
            }
        }

        secciones[contadorSecciones++] = new Seccion(codigo, nombre, curso);
        return true;
    }

    public String obtenerListaSecciones() {
        StringBuilder sb = new StringBuilder("SECCIONES:\n\n");
        for (int i = 0; i < contadorSecciones; i++) {
            if (secciones[i] != null) {
                sb.append(secciones[i].toString()).append("\n");
            }
        }
        return sb.toString();
    }

    public Curso buscarCurso(String codigo) {
        for (int i = 0; i < contadorCursos; i++) {
            if (cursos[i] != null && cursos[i].getCodigo().equals(codigo)) {
                return cursos[i];
            }
        }
        return null;
    }

    // ================= NOTAS =================
    private void crecerNotas() {
        Nota[] nuevo = new Nota[notas.length * 2];
        for (int i = 0; i < notas.length; i++) {
            nuevo[i] = notas[i];
        }
        notas = nuevo;
    }

    public String asignarNota(String codEst, String codCurso, double valor) {
        if (buscarUsuario(codEst) == null) return "Estudiante no existe";
        if (buscarCurso(codCurso) == null) return "Curso no existe";

        for (int i = 0; i < contadorNotas; i++) {
            if (notas[i].getCodigoEstudiante().equalsIgnoreCase(codEst) &&
                notas[i].getCodigoCurso().equalsIgnoreCase(codCurso)) {
                notas[i].setValor(valor);
                return "Nota actualizada";
            }
        }

        if (contadorNotas == notas.length) crecerNotas();

        notas[contadorNotas++] = new Nota(codEst, codCurso, valor);
        return "Nota asignada";
    }

    public String obtenerNotasPorEstudiante(String cod) {
        StringBuilder sb = new StringBuilder("NOTAS:\n\n");
        for (int i = 0; i < contadorNotas; i++) {
            if (notas[i].getCodigoEstudiante().equalsIgnoreCase(cod)) {
                sb.append(notas[i].getCodigoCurso())
                  .append(" - ")
                  .append(notas[i].getValor())
                  .append("\n");
            }
        }
        return sb.toString();
    }

    // ================= PERSISTENCIA =================
    public void guardarDatos() {
        GestorDatos.guardarUsuarios(usuarios, contador);
        GestorDatos.guardarCursos(cursos, contadorCursos);
    }

    public void cargarDatos() {
        contador = GestorDatos.cargarUsuarios(usuarios);
        contadorCursos = GestorDatos.cargarCursos(cursos);
    }
}
