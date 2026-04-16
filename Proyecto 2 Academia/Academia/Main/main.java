package Main; // se import el diseño el cual se encuentra en la carpeta Vista

import Modelo.Usuario; // se importa el codigo de el usuario el cual esta en Modelo
import Vista.Login; // se importa el codigo del login el cual esta en Login


// inicio de MVC  
public class main {

    public static void main(String[] args) {

        // Usuario inicial (admin)
        Usuario usuario = new Usuario(
                "admin",
                "Administrador",
                "1234",
                "2000-01-01",
                "M"
        );

        // Abrir login
        Login login = new Login(usuario);
        login.setVisible(true);
    }
}