# Casos de prueba — Sistema AulaLogic

- **Estudiante:** Paulo Josue Escobar Bautista
- **Variante asignada:** D
- **Promedio mínimo de aprobación:** 14

## Resumen de pruebas (CP01 – CP08)

| Caso | Situación comprobada | Entrada | Resultado esperado | Resultado obtenido | Estado |
| :--- | :--- | :--- | :--- | :--- | :--- |
| **CP01** | Registro correcto de un estudiante | Código `PE-007`, Nombre `Anthony Escobar`, Notas `14`, `16`, `19` | Se registra el estudiante y aparece en la lista/reporte | Registrado sin errores en la lista en memoria | Exitoso |
| **CP02** | Intento de registrar un código duplicado | Intentar registrar otro alumno con código `PE-007` | Rechaza el código repetido y pide ingresar uno nuevo | Mostró mensaje de código existente y pidió otro código | Exitoso |
| **CP03** | Calificación fuera del rango permitido | Calificación `-1` o `25` | Rechaza la nota y solicita nuevamente un valor entre `0` y `20` | Pidió nuevamente la calificación válida entre `0` y `20` | Exitoso |
| **CP04** | Promedio igual al límite de aprobación | Tres notas cuyo promedio sea `14.00` (ej. `14`, `14`, `14`) | Calcula el promedio exacto (`14.00`) y asigna estado `APROBADO` | Calculó `14.00` y asignó estado `APROBADO` | Exitoso |
| **CP05** | Búsqueda de un estudiante existente | Buscar código `PE-007` en opción `3` | Muestra código, nombre, 3 notas, promedio, máx., mín. y estado | Mostró todos los datos y cálculos del estudiante | Exitoso |
| **CP06** | Búsqueda de un estudiante inexistente | Buscar código `E999` en opción `3` | Muestra mensaje informativo indicando que no existe | Mostró mensaje indicando que no se encontró el código | Exitoso |
| **CP07** | Reporte sin estudiantes registrados | Elegir opción `4` cuando no hay registros | Avisa que no hay estudiantes y evita división entre cero | Mostró aviso sin errores ni división por cero | Exitoso |
| **CP08** | Guardado y recuperación al reiniciar | Guardar (`5` o salir con `6`), cerrar y abrir el programa | Guarda en `datos/estudiantes.txt` y recupera los datos al iniciar | Guardó en `datos/estudiantes.txt` y cargó los registros al iniciar | Exitoso |

## Observaciones de ejecución
- Los registros se almacenan en `datos/estudiantes.txt` con el formato `codigo;nombre;nota1;nota2;nota3`.
- Al cerrar el programa con la opción `6`, se guardan automáticamente los cambios antes de finalizar.
