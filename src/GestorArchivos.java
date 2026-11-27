import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;

public class GestorArchivos {
    private static final String CARPETA = "datos";
    private static final String RUTA_ARCHIVO = "datos/estudiantes.txt";

    public void guardarEstudiantes(ArrayList<Estudiante> estudiantes) {
        File directorio = new File(CARPETA);
        if (!directorio.exists()) {
            directorio.mkdirs();
        }

        try (BufferedWriter writer = new BufferedWriter(new FileWriter(RUTA_ARCHIVO, false))) {
            for (Estudiante e : estudiantes) {
                writer.write(e.toFormatoArchivo());
                writer.newLine();
            }
            System.out.println("Registros guardados correctamente en " + RUTA_ARCHIVO);
        } catch (IOException e) {
            System.out.println("Error al guardar los registros en el archivo: " + e.getMessage());
        }
    }

    public ArrayList<Estudiante> cargarEstudiantes() {
        ArrayList<Estudiante> lista = new ArrayList<>();
        File archivo = new File(RUTA_ARCHIVO);

        if (!archivo.exists()) {
            System.out.println("Aviso: No se encontró " + RUTA_ARCHIVO + ". Se iniciará con una lista vacía.");
            return lista;
        }

        try (BufferedReader reader = new BufferedReader(new FileReader(archivo))) {
            String linea;
            int numeroLinea = 0;
            while ((linea = reader.readLine()) != null) {
                numeroLinea++;
                if (linea.trim().isEmpty()) {
                    continue;
                }

                String[] partes = linea.split(";");
                if (partes.length != 5) {
                    System.out.println("Línea " + numeroLinea + " ignorada (formato inválido): " + linea);
                    continue;
                }

                try {
                    String codigo = partes[0].trim();
                    String nombre = partes[1].trim();
                    int n1 = Integer.parseInt(partes[2].trim());
                    int n2 = Integer.parseInt(partes[3].trim());
                    int n3 = Integer.parseInt(partes[4].trim());

                    if (codigo.isEmpty() || nombre.isEmpty()
                            || n1 < 0 || n1 > 20 || n2 < 0 || n2 > 20 || n3 < 0 || n3 > 20) {
                        System.out.println("Línea " + numeroLinea + " ignorada (datos fuera de rango).");
                        continue;
                    }

                    boolean duplicado = false;
                    for (Estudiante existente : lista) {
                        if (existente.getCodigo().equalsIgnoreCase(codigo)) {
                            duplicado = true;
                            break;
                        }
                    }

                    if (!duplicado) {
                        lista.add(new Estudiante(codigo, nombre, new int[]{n1, n2, n3}));
                    }
                } catch (NumberFormatException ex) {
                    System.out.println("Línea " + numeroLinea + " ignorada (error numérico): " + linea);
                }
            }
            System.out.println("Se recuperaron " + lista.size() + " registro(s) desde " + RUTA_ARCHIVO);
        } catch (IOException e) {
            System.out.println("Error al leer el archivo: " + e.getMessage());
        }

        return lista;
    }
}
