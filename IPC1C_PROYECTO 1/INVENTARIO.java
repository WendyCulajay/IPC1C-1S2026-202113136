import java.util.Scanner;

public class INVENTARIO{

    static Scanner sc = new Scanner(System.in);

    // ================= VECTORES =================
    static String[] nombres = new String[100];
    static String[] categorias = new String[100];
    static double[] precios = new double[100];
    static int[] stock = new int[100];
    static String[] codigos = new String[100];

    static int contadorProductos = 0;
    static int opcion = 0;

    // ================= MAIN =================
    public static void main(String[] args) {

        while (opcion != 7) {

            mostrarMenu();
            leerOpcion();

            switch (opcion) {
                case 1:
                    agregarProducto();
                    break;
                case 2:
                    buscarProducto();
                    break;
                case 3:
                    eliminarProducto();
                    break;
                case 4:
                    registrarVenta();
                    break;
                case 5:
                    generarReportes();
                    break;
                case 6:
                    verDatosEstudiante();
                    break;
                case 7:
                    System.out.println("Saliendo del sistema...");
                    break;
                default:
                    System.out.println("Opcion inválida.");
            }
        }
    }

    // ================= MENU =================
    public static void mostrarMenu() {
        System.out.println("\n===== SISTEMA INVENTARIO =====");
        System.out.println("1. Agregar Producto");
        System.out.println("2. Buscar Producto");
        System.out.println("3. Eliminar Producto");
        System.out.println("4. Registrar Venta");
        System.out.println("5. Generar Reportes");
        System.out.println("6. Ver Datos del Estudiante");
        System.out.println("7. Salir");
        System.out.print("Seleccione una opcion: ");
    }

    public static void leerOpcion() {
        opcion = sc.nextInt();
        sc.nextLine(); // limpiar buffer
    }

    // ================= AGREGAR PRODUCTO =================
    public static void agregarProducto() {

        if (contadorProductos >= 100) {
            System.out.println("Inventario lleno.");
            return;
        }

        System.out.println("\n--- AGREGAR PRODUCTO ---");

        System.out.print("Nombre del producto: ");
        String nombre = sc.nextLine();

        System.out.print("Categoría: ");
        String categoria = sc.nextLine();

        double precio;
        do {
            System.out.print("Precio: ");
            precio = sc.nextDouble();
            if (precio <= 0) {
                System.out.println("El precio debe ser mayor que 0.");
            }
        } while (precio <= 0);

        int cantidad;
        do {
            System.out.print("Cantidad en stock: ");
            cantidad = sc.nextInt();
            if (cantidad < 0) {
                System.out.println("La cantidad no puede ser negativa.");
            }
        } while (cantidad < 0);

        sc.nextLine(); // limpiar buffer

        String codigo;
        boolean codigoExiste;

        do {
            codigoExiste = false;
            System.out.print("Código del producto: ");
            codigo = sc.nextLine();

            for (int i = 0; i < contadorProductos; i++) {
                if (codigos[i].equalsIgnoreCase(codigo)) {
                    codigoExiste = true;
                    System.out.println("Ese código ya existe. Intente otro.");
                    break;
                }
            }

        } while (codigoExiste);

        nombres[contadorProductos] = nombre;
        categorias[contadorProductos] = categoria;
        precios[contadorProductos] = precio;
        stock[contadorProductos] = cantidad;
        codigos[contadorProductos] = codigo;

        contadorProductos++;

        System.out.println("Producto agregado correctamente.");
    }

    // ================= BUSCAR PRODUCTO =================
    public static void buscarProducto() {

        if (contadorProductos == 0) {
            System.out.println("No hay productos registrados.");
            return;
        }

        System.out.println("\n--- BUSCAR PRODUCTO ---");
        System.out.println("1. Buscar por código");
        System.out.println("2. Buscar por nombre");
        System.out.println("3. Buscar por categoría");
        System.out.print("Seleccione una opción: ");

        int opcionBusqueda = sc.nextInt();
        sc.nextLine(); // limpiar buffer

        boolean encontrado = false;

        switch (opcionBusqueda) {

            case 1:
                System.out.print("Ingrese el código: ");
                String codigoBuscado = sc.nextLine();

                for (int i = 0; i < contadorProductos; i++) {
                    if (codigos[i].equalsIgnoreCase(codigoBuscado)) {
                        mostrarProducto(i);
                        encontrado = true;
                        break;
                    }
                }
                break;

            case 2:
                System.out.print("Ingrese el nombre: ");
                String nombreBuscado = sc.nextLine();

                for (int i = 0; i < contadorProductos; i++) {
                    if (nombres[i].equalsIgnoreCase(nombreBuscado)) {
                        mostrarProducto(i);
                        encontrado = true;
                    }
                }
                break;

            case 3:
                System.out.print("Ingrese la categoría: ");
                String categoriaBuscada = sc.nextLine();

                for (int i = 0; i < contadorProductos; i++) {
                    if (categorias[i].equalsIgnoreCase(categoriaBuscada)) {
                        mostrarProducto(i);
                        encontrado = true;
                    }
                }
                break;

            default:
                System.out.println("Opción inválida.");
        }

        if (!encontrado) {
            System.out.println("No se encontró ningún producto.");
        }
    }

    public static void mostrarProducto(int i) {
        System.out.println("\n----- PRODUCTO ENCONTRADO -----");
        System.out.println("Nombre: " + nombres[i]);
        System.out.println("Categoría: " + categorias[i]);
        System.out.println("Precio: Q" + precios[i]);
        System.out.println("Stock: " + stock[i]);
        System.out.println("Código: " + codigos[i]);
    }

    // ================= ELIMINAR =================
    public static void eliminarProducto() {
        System.out.println("Función pendiente de implementar.");
    }

    // ================= VENTA =================
    public static void registrarVenta() {
        System.out.println("Función pendiente de implementar.");
    }

    // ================= REPORTES =================
    public static void generarReportes() {
        System.out.println("Función pendiente de implementar.");
    }

    // ================= DATOS ESTUDIANTE =================
    public static void verDatosEstudiante() {
        System.out.println("\n--- DATOS DEL ESTUDIANTE ---");
        System.out.println("Nombre: Wendy");
        System.out.println("Curso: IPC1");
        System.out.println("Proyecto 1 - Sistema de Inventario");
    }
}
