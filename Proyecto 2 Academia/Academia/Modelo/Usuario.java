package Modelo;
import java.io.Serializable;

public class Usuario implements Serializable {
    private String codigo;
    private String nombre;
    private String password;
    private String rol;
    private String carnet;

    // Constructor 
    public Usuario(String codigo, String nombre, String password, String rol) {
        this.codigo = codigo;
        this.nombre = nombre;
        this.password = password;
        this.rol = rol;
    }

    // --- GETTERS ---
    public String getCodigo() { return codigo; }
    public String getNombre() { return nombre; }
    public String getPassword() { return password; }
    public String getRol() { return rol; }
    public String getCarnet() { return carnet; }

    // --- SETTERS (¡Estos son obligatorios para poder actualizar datos!) ---
    public void setNombre(String nombre) { 
        this.nombre = nombre; 
    }

    public void setPassword(String password) { 
        this.password = password; 
    }

    public void setCarnet(String carnet) { 
        this.carnet = carnet; 
    }
}