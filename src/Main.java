import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        GestionAcademica gestion = new GestionAcademica();
        GestorArchivos gestorArchivos = new GestorArchivos();

        // Recuperación automática al iniciar el programa
        gestion.setEstudiantes(gestorArchivos.cargarEstudiantes());

        int opcion = 0;
        do {
            mostrarMenu();
            String entrada = scanner.nextLine().trim();

            try {
                opcion = Integer.parseInt(entrada);
            } catch (NumberFormatException e) {
                opcion = -1;
            }

            switch (opcion) {
                case 1:
                    registrarNuevoEstudiante(scanner, gestion);
                    break;
                case 2:
                    gestion.listarEstudiantes();
                    break;
                case 3:
                    buscarEstudiante(scanner, gestion);
                    break;
                case 4:
                    gestion.generarReporteAcademico();
                    break;
                case 5:
                    gestorArchivos.guardarEstudiantes(gestion.getEstudiantes());
                    break;
                case 6:
                    System.out.println("Guardando registros antes de salir...");
                    gestorArchivos.guardarEstudiantes(gestion.getEstudiantes());
                    System.out.println("Saliendo del sistema AulaLogic. ¡Hasta luego!");
                    break;
                default:
                    System.out.println("Opción incorrecta. Por favor, ingrese un número del 1 al 6.");
                    break;
            }
        } while (opcion != 6);

        scanner.close();
    }

    private static void mostrarMenu() {
        System.out.println("\n=====================================");
        System.out.println("        SISTEMA AULALOGIC");
        System.out.println("=====================================");
        System.out.println("1. Registrar estudiante");
        System.out.println("2. Listar estudiantes");
        System.out.println("3. Buscar estudiante");
        System.out.println("4. Generar reporte academico");
        System.out.println("5. Guardar registros");
        System.out.println("6. Salir");
        System.out.println("=====================================");
        System.out.print("Seleccione una opcion: ");
    }

    private static void registrarNuevoEstudiante(Scanner scanner, GestionAcademica gestion) {
        System.out.println("\n--- REGISTRO DE ESTUDIANTE ---");

        String codigo;
        while (true) {
            System.out.print("Ingrese el código del estudiante: ");
            codigo = scanner.nextLine().trim();

            if (codigo.isEmpty() || codigo.contains(";")) {
                System.out.println("Error: El código no puede estar vacío ni contener punto y coma (;).");
            } else if (gestion.existeCodigo(codigo)) {
                System.out.println("Error: Ya existe un estudiante registrado con el código '" + codigo + "'.");
            } else {
                break;
            }
        }

        String nombre;
        while (true) {
            System.out.print("Ingrese el nombre completo: ");
            nombre = scanner.nextLine().trim();

            if (nombre.isEmpty() || nombre.contains(";")) {
                System.out.println("Error: El nombre no puede estar vacío ni contener punto y coma (;).");
            } else {
                break;
            }
        }

        int[] notas = new int[3];
        for (int i = 0; i < 3; i++) {
            notas[i] = leerCalificacion(scanner, i + 1);
        }

        Estudiante estudiante = new Estudiante(codigo, nombre, notas);
        if (gestion.registrarEstudiante(estudiante)) {
            System.out.println("Estudiante registrado exitosamente.");
        } else {
            System.out.println("No se pudo registrar al estudiante.");
        }
    }

    private static int leerCalificacion(Scanner scanner, int numeroNota) {
        while (true) {
            System.out.print("Ingrese la calificación " + numeroNota + " (0 - 20): ");
            String entrada = scanner.nextLine().trim();
            try {
                int nota = Integer.parseInt(entrada);
                if (nota >= 0 && nota <= 20) {
                    return nota;
                } else {
                    System.out.println("Error: La calificación debe estar entre 0 y 20.");
                }
            } catch (NumberFormatException e) {
                System.out.println("Error: Debe ingresar un número entero válido.");
            }
        }
    }

    private static void buscarEstudiante(Scanner scanner, GestionAcademica gestion) {
        System.out.println("\n--- BUSCAR ESTUDIANTE ---");
        System.out.print("Ingrese el código del estudiante a buscar: ");
        String codigo = scanner.nextLine().trim();

        Estudiante encontrado = gestion.buscarPorCodigo(codigo);
        if (encontrado == null) {
            System.out.println("No se encontró ningún estudiante con el código '" + codigo + "'.");
        } else {
            System.out.println("Estudiante encontrado:");
            System.out.println("Código        : " + encontrado.getCodigo());
            System.out.println("Nombre        : " + encontrado.getNombre());
            int[] n = encontrado.getNotas();
            System.out.println("Calificaciones: " + n[0] + ", " + n[1] + ", " + n[2]);
            System.out.printf("Promedio      : %.2f%n", encontrado.calcularPromedio());
            System.out.println("Nota máxima   : " + encontrado.obtenerNotaMaxima());
            System.out.println("Nota mínima   : " + encontrado.obtenerNotaMinima());
            System.out.println("Estado        : " + encontrado.obtenerEstado());
        }
    }
}