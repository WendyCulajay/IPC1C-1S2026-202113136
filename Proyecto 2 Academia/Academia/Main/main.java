package Main; // se importa el diseño el cual se encuentra en la carpeta vista

import Modelo.Administrador;
import Modelo.Estudiante;
import Modelo.Instructor;
import Controlador.Sistema;
import Vista.Login;

// Inicio de MVC  
public class main {

    public static void main(String[] args) {

        // Sistema con capacidad de usuarios
        Sistema sistema = new Sistema(25);

        // Usuario inicial (admin)
        Administrador admin = new Administrador(
                "admin",
                "Administrador",
                "IPC1C",
                "2000-01-01",
                "M"
        );

        // Usuario estudiante de prueba
        Estudiante est = new Estudiante(
                "abcd",
                "Rose",
                "1234",
                "2004-05-10",
                "F",
                "2021001"
        );

        // Usuario instructor de prueba
        Instructor ins = new Instructor(
                "klmn",
                "Kai",
                "4444",
                "1995-03-20",
                "M",
                "Programacion"
        );

        
        sistema.agregarUsuario(admin);
        sistema.agregarUsuario(est);
        sistema.agregarUsuario(ins);
        
        
        sistema.agregarUsuario(new Estudiante("est2", "Lisa", "1234", "2003-01-01", "F", "2021002"));
        sistema.agregarUsuario(new Estudiante("est3", "Felix", "1234", "2002-01-01", "M", "2021003"));
        sistema.agregarUsuario(new Estudiante("est4", "Liz", "1234", "2004-01-01", "F", "2021004"));
        sistema.agregarUsuario(new Estudiante("est5", "Kevin", "1234", "2001-01-01", "M", "2021005"));
        sistema.agregarUsuario(new Estudiante("est6", "Jennie", "1234", "2003-01-01", "F", "2021006"));
        sistema.agregarUsuario(new Estudiante("est7", "Hyunjin", "1234", "2000-01-01", "M", "2021007"));
        sistema.agregarUsuario(new Estudiante("est8", "Jisoo", "1234", "2004-01-01", "F", "2021008"));
        sistema.agregarUsuario(new Estudiante("est9", "Bangchan", "1234", "1999-01-01", "M", "2021009"));
        sistema.agregarUsuario(new Estudiante("est10", "Sana", "1234", "2003-01-01", "F", "2021010"));

        
        sistema.agregarUsuario(new Instructor("ins1", "Marisol", "1234", "1985-01-01", "M", "Sistemas"));
        sistema.agregarUsuario(new Instructor("ins2", "Diego", "1234", "1990-01-01", "M", "Fisica"));

        
        Login login = new Login(sistema);
        login.setVisible(true);
    }
}