public class Estudiante {
    public static final double NOTA_MINIMA_APROBACION = 14.0;

    private String codigo;
    private String nombre;
    private int[] notas;

    public Estudiante(String codigo, String nombre, int[] notas) {
        this.codigo = codigo;
        this.nombre = nombre;
        this.notas = notas;
    }

    public String getCodigo() {
        return codigo;
    }

    public void setCodigo(String codigo) {
        this.codigo = codigo;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public int[] getNotas() {
        return notas;
    }

    public void setNotas(int[] notas) {
        this.notas = notas;
    }

    public double calcularPromedio() {
        if (notas == null || notas.length == 0) {
            return 0.0;
        }
        int suma = 0;
        for (int nota : notas) {
            suma += nota;
        }
        return suma / 3.0; 
    }

    public int obtenerNotaMaxima() {
        int max = notas[0];
        for (int i = 1; i < notas.length; i++) {
            if (notas[i] > max) {
                max = notas[i];
            }
        }
        return max;
    }

    public int obtenerNotaMinima() {
        int min = notas[0];
        for (int i = 1; i < notas.length; i++) {
            if (notas[i] < min) {
                min = notas[i];
            }
        }
        return min;
    }

    public String obtenerEstado() {
        return calcularPromedio() >= NOTA_MINIMA_APROBACION ? "APROBADO" : "REPROBADO";
    }

    public String toFormatoArchivo() {
        return codigo + ";" + nombre + ";" + notas[0] + ";" + notas[1] + ";" + notas[2];
    }

    @Override
    public String toString() {
        return String.format(
            "Código: %-8s | Nombre: %-22s | Notas: [%2d, %2d, %2d] | Prom: %5.2f | Max: %2d | Min: %2d | Estado: %s",
            codigo, nombre, notas[0], notas[1], notas[2],
            calcularPromedio(), obtenerNotaMaxima(), obtenerNotaMinima(), obtenerEstado()
        );
    }
}
