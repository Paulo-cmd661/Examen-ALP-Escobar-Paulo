import java.util.ArrayList;

public class GestionAcademica {
    private ArrayList<Estudiante> estudiantes;

    public GestionAcademica() {
        this.estudiantes = new ArrayList<>();
    }

    public ArrayList<Estudiante> getEstudiantes() {
        return estudiantes;
    }

    public void setEstudiantes(ArrayList<Estudiante> estudiantes) {
        this.estudiantes = estudiantes;
    }

    public boolean existeCodigo(String codigo) {
        for (Estudiante e : estudiantes) {
            if (e.getCodigo().equalsIgnoreCase(codigo.trim())) {
                return true;
            }
        }
        return false;
    }

    public boolean registrarEstudiante(Estudiante nuevo) {
        if (nuevo == null || existeCodigo(nuevo.getCodigo())) {
            return false;
        }
        estudiantes.add(nuevo);
        return true;
    }

    public Estudiante buscarPorCodigo(String codigo) {
        for (Estudiante e : estudiantes) {
            if (e.getCodigo().equalsIgnoreCase(codigo.trim())) {
                return e;
            }
        }
        return null;
    }

    public void listarEstudiantes() {
        if (estudiantes.isEmpty()) {
            System.out.println("No hay estudiantes registrados en el sistema.");
            return;
        }
        System.out.println("\n--- LISTADO DE ESTUDIANTES ---");
        for (Estudiante e : estudiantes) {
            System.out.println(e.toString());
        }
    }

    public void generarReporteAcademico() {
        if (estudiantes.isEmpty()) {
            System.out.println("No se puede generar el reporte: no hay estudiantes registrados.");
            return;
        }

        int total = estudiantes.size();
        int aprobados = 0;
        int reprobados = 0;
        double sumaPromedios = 0.0;

        Estudiante mayorPromedio = estudiantes.get(0);
        Estudiante menorPromedio = estudiantes.get(0);

        for (Estudiante e : estudiantes) {
            double prom = e.calcularPromedio();
            sumaPromedios += prom;

            if (e.obtenerEstado().equals("APROBADO")) {
                aprobados++;
            } else {
                reprobados++;
            }

            if (prom > mayorPromedio.calcularPromedio()) {
                mayorPromedio = e;
            }
            if (prom < menorPromedio.calcularPromedio()) {
                menorPromedio = e;
            }
        }

        double promedioGeneral = sumaPromedios / total;
        double porcentajeAprobados = (aprobados * 100.0) / total;

        System.out.println("\n=====================================");
        System.out.println("         REPORTE ACADÉMICO");
        System.out.println("=====================================");
        System.out.printf("Límite de aprobación (Variante): %.2f%n", Estudiante.NOTA_MINIMA_APROBACION);
        System.out.println("Total de estudiantes           : " + total);
        System.out.println("Estudiantes aprobados          : " + aprobados);
        System.out.println("Estudiantes reprobados         : " + reprobados);
        System.out.printf("Promedio general del curso     : %.2f%n", promedioGeneral);
        System.out.printf("Porcentaje de aprobados        : %.2f%%%n", porcentajeAprobados);
        System.out.printf("Mayor promedio                 : %s (%s) - %.2f%n",
                mayorPromedio.getNombre(), mayorPromedio.getCodigo(), mayorPromedio.calcularPromedio());
        System.out.printf("Menor promedio                 : %s (%s) - %.2f%n",
                menorPromedio.getNombre(), menorPromedio.getCodigo(), menorPromedio.calcularPromedio());

        System.out.println("\n--- Estudiantes con promedio inferior al promedio general ---");
        boolean hayInferiores = false;
        for (Estudiante e : estudiantes) {
            if (e.calcularPromedio() < promedioGeneral) {
                System.out.printf("- %s (%s): %.2f%n", e.getNombre(), e.getCodigo(), e.calcularPromedio());
                hayInferiores = true;
            }
        }
        if (!hayInferiores) {
            System.out.println("Ningún estudiante tiene promedio inferior al general.");
        }

        // Ordenamiento de mayor a menor promedio (copia para no alterar orden original si no se desea)
        ArrayList<Estudiante> ordenados = new ArrayList<>(estudiantes);
        for (int i = 0; i < ordenados.size() - 1; i++) {
            for (int j = 0; j < ordenados.size() - 1 - i; j++) {
                if (ordenados.get(j).calcularPromedio() < ordenados.get(j + 1).calcularPromedio()) {
                    Estudiante temp = ordenados.get(j);
                    ordenados.set(j, ordenados.get(j + 1));
                    ordenados.set(j + 1, temp);
                }
            }
        }

        System.out.println("\n--- Listado ordenado de mayor a menor promedio ---");
        for (int i = 0; i < ordenados.size(); i++) {
            Estudiante e = ordenados.get(i);
            System.out.printf("%d. %s (%s) - Promedio: %.2f [%s]%n",
                    (i + 1), e.getNombre(), e.getCodigo(), e.calcularPromedio(), e.obtenerEstado());
        }
        System.out.println("=====================================");
    }
}