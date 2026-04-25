package Main;

import Controlador.Sistema;
import Vista.Login;
import javax.swing.SwingUtilities;

public class main {    
    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            Sistema sistema = new Sistema();
            new Login(sistema);
        });
    }
}