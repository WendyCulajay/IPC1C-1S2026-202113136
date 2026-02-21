// Wendy culajay  Pacman 
import java.util.Scanner;
import java.util.Random;

public class Main {

    static Scanner sc = new Scanner(System.in);
    static Random rd = new Random();

    // Historial maximo aguardara 20 partidas
    static String[] usuarios = new String[20];
    static int[] puntosHistorial = new int[20];
    static int totalPartidas = 0;

    public static void main(String[] args) {
// opciones que se piden en el pdf de la practica 
        int opcion;

        do {
            System.out.println("\n==== PACMAN ====");
            System.out.println("1. Iniciar Juego");
            System.out.println("2. Historial");
            System.out.println("3. Salir");
            System.out.print("Opcion: ");
            opcion = sc.nextInt();
            sc.nextLine();

            switch (opcion) {
                case 1:
                    iniciarJuego();
                    break;
                case 2:
                    mostrarHistorial();
                    break;
            }

        } while (opcion != 3);
    }

        // inicio
    static void iniciarJuego() {

        System.out.print("Nombre de usuario: ");
        String nombre = sc.nextLine();

        System.out.print("Tipo de tablero (P/G): ");
        char tipo = sc.next().toUpperCase().charAt(0);

        int filas = (tipo == 'P') ? 5 : 10;
        int cols = (tipo == 'P') ? 6 : 10;

        char[][] tablero = new char[filas][cols];

        for (int i = 0; i < filas; i++)
            for (int j = 0; j < cols; j++)
                tablero[i][j] = ' ';

        int totalCasillas = filas * cols;

        int premios = pedirCantidad("Premios (0)", 1, (int)(totalCasillas * 0.4));
        int paredes = pedirCantidad("Paredes (X)", 1, (int)(totalCasillas * 0.2));
        int fantasmas = pedirCantidad("Fantasmas (@)", 1, (int)(totalCasillas * 0.2));

        // simbolos del juego 
        colocarAleatorio(tablero, premios, '0');
        colocarAleatorio(tablero, premios/3, '$'); 
        colocarAleatorio(tablero, paredes, 'X');
        colocarAleatorio(tablero, fantasmas, '@');

        System.out.print("Fila inicial: ");
        int pacF = sc.nextInt();
        System.out.print("Columna inicial: ");
        int pacC = sc.nextInt();

        if (pacF < 0 || pacF >= filas || pacC < 0 || pacC >= cols || tablero[pacF][pacC] != ' ') {
            pacF = 0;
            pacC = 0;
        }

        int vidas = 3;
        int puntos = 0;

// Bucle
        while (vidas > 0 && quedanPremios(tablero)) {

            mostrar(tablero, pacF, pacC, nombre, puntos, vidas);

            System.out.print("Mover (8=arriba,5=abajo,4=izq,6=der, F=fin): ");
            char mov = sc.next().toUpperCase().charAt(0);

            if (mov == 'F') break;

            int nf = pacF;
            int nc = pacC;

            if (mov == '8') nf--;
            if (mov == '5') nf++;
            if (mov == '4') nc--;
            if (mov == '6') nc++;

            // Bordes  
            if (nf < 0) nf = filas - 1;
            if (nf >= filas) nf = 0;
            if (nc < 0) nc = cols - 1;
            if (nc >= cols) nc = 0;

            // Pared
            if (tablero[nf][nc] == 'X') continue;

            // ghostings
            if (tablero[nf][nc] == '@') {
                vidas--;
                tablero[nf][nc] = ' ';
                System.out.println("¡Fantasma! Pierdes una vida.");
            }

            // Premios
            if (tablero[nf][nc] == '0') {
                puntos += 10;
                tablero[nf][nc] = ' ';
            }

            if (tablero[nf][nc] == '$') {
                puntos += 15;
                tablero[nf][nc] = ' ';
            }

            pacF = nf;
            pacC = nc;
        }

        System.out.println("\nFin del juego. Puntos: " + puntos);

        // Guardar historial
        if (totalPartidas < 20) {
            usuarios[totalPartidas] = nombre;
            puntosHistorial[totalPartidas] = puntos;
            totalPartidas++;
        }
    }

    static int pedirCantidad(String texto, int min, int max) {
        int n;
        do {
            System.out.print(texto + " (" + min + "-" + max + "): ");
            n = sc.nextInt();
        } while (n < min || n > max);
        return n;
    }

    static void colocarAleatorio(char[][] t, int cant, char simbolo) {
        int f, c;
        int filas = t.length;
        int cols = t[0].length;

        while (cant > 0) {
            f = rd.nextInt(filas);
            c = rd.nextInt(cols);

            if (t[f][c] == ' ') {
                t[f][c] = simbolo;
                cant--;
            }
        }
    }

    static void mostrar(char[][] t, int pf, int pc, String nombre, int puntos, int vidas) {
        System.out.println("\nJugador: " + nombre + "  Puntos: " + puntos + "  Vidas: " + vidas);

        for (int i = 0; i < t.length; i++) {
            for (int j = 0; j < t[0].length; j++) {
                if (i == pf && j == pc)
                    System.out.print("< ");
                else
                    System.out.print(t[i][j] + " ");
            }
            System.out.println();
        }
    }

    static boolean quedanPremios(char[][] t) {
        for (int i = 0; i < t.length; i++)
            for (int j = 0; j < t[0].length; j++)
                if (t[i][j] == '0' || t[i][j] == '$')
                    return true;
        return false;
    }

    static void mostrarHistorial() {
        System.out.println("\n=== HISTORIAL ===");
        for (int i = totalPartidas - 1; i >= 0; i--) {
            System.out.println(usuarios[i] + " - " + puntosHistorial[i]);
        }
    }
}
