package Modelo;

// Clase que representa a un usuario del sistema
public class Usuario {

    // Datos básicos del usuario
    protected String codigo;
    protected String nombre;
    protected String password;

    // Datos adicionales
    protected String fechaNacimiento;
    protected String genero;

    // Constructor para inicializar los datos
    public Usuario(String codigo, String nombre, String password, String fechaNacimiento, String genero) {
        this.codigo = codigo;
        this.nombre = nombre;
        this.password = password;
        this.fechaNacimiento = fechaNacimiento;
        this.genero = genero;
    }

    // Getters y setters (encapsulamiento)

    public String getCodigo() {
        return codigo;
    }

    public void setCodigo(String codigo) {
        this.codigo = codigo;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getFechaNacimiento() {
        return fechaNacimiento;
    }

    public void setFechaNacimiento(String fechaNacimiento) {
        this.fechaNacimiento = fechaNacimiento;
    }

    public String getGenero() {
        return genero;
    }

    public void setGenero(String genero) {
        this.genero = genero;
    }

    // Método para verificar login
    public boolean login(String codigo, String password) {
        if (codigo == null || password == null) {
            return false;
        }
        return this.codigo.equals(codigo) && this.password.equals(password);
    }

    // Método para comparar códigos
    public boolean esCodigo(String codigo) {
        return this.codigo.equals(codigo);
    }

    // Método que devuelve el rol (para herencia después)
    public String getRol() {
        return "Usuario";
    }

    // Mostrar información básica
    public String mostrarInfo() {
        return "Codigo: " + codigo +
               " | Nombre: " + nombre +
               " | Rol: " + getRol();
    }
}