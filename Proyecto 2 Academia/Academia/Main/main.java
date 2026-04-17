package Main; // se importa el diseño el cual se encuentra en la carpeta Vista

import Modelo.Administrador;
import Modelo.Estudiante;
import Modelo.Instructor;
import Controlador.Sistema;
import Vista.Login;

// inicio de MVC  
public class main{

    public static void main(String[] args) {

        // Se crea el sistema con capacidad de usuarios
        Sistema sistema = new Sistema(10);

        // Usuario inicial (admin)
        Administrador admin = new Administrador(
                "admin",
                "Administrador",
                "1111",
                "2000-01-01",
                "M"
        );

        // Usuario estudiante de prueba
        Estudiante est = new Estudiante(
                "abcd",
                "Wen",
                "1234",
                "2002-05-10",
                "M",
                "2021001"
        );

        // Usuario instructor de prueba
        Instructor ins = new Instructor(
                "klmn",
                "kai",
                "4444",
                "1995-03-20",
                "M",
                "Programacion"
        );

        // Se agregan los usuarios al sistema
        sistema.agregarUsuario(admin);
        sistema.agregarUsuario(est);
        sistema.agregarUsuario(ins);

        // Abrir login pasando el sistema
        Login login = new Login(sistema);
        login.setVisible(true);
    }
}