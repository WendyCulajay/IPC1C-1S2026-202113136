package Modelo;

public class Usuario {

    protected String codigo;
    protected String nombre;
    protected String password;
    protected String fechaNacimiento;
    protected String genero;

    public Usuario(String codigo, String nombre, String password, String fechaNacimiento, String genero) {
        this.codigo = codigo;
        this.nombre = nombre;
        this.password = password;
        this.fechaNacimiento = fechaNacimiento;
        this.genero = genero;
    }

    public String getCodigo() {
        return codigo;
    }

    public String getNombre() {
        return nombre;
    }

    public boolean login(String codigo, String password) {
        return this.codigo.equals(codigo) && this.password.equals(password);
    }

    public String getRol() {
        return "Usuario";
    }
}