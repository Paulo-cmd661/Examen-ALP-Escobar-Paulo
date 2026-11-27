# Análisis del problema

## Datos del estudiante
* **Estudiante:** Paulo Josue Escobar Bautista
* **Asignatura:** Algoritmos y Lógica de Programación
* **Variante asignada:** D *(cambia esta letra si te tocó otra)*
* **Límite mínimo de aprobación:** 14 *(ajusta este número según tu variante)*

## Descripción del problema
La Universidad Técnica de Ambato requiere un sistema de consola llamado **AulaLogic** para llevar el control de las calificaciones de los estudiantes de una materia. El programa debe permitir registrar alumnos con sus tres notas, consultar su información individual, sacar estadísticas generales del curso y guardar todo en un archivo de texto (`datos/estudiantes.txt`) para que los datos no se pierdan al cerrar la aplicación[cite: 1].

## Entradas
* **Opción del menú principal:** Un número del 1 al 6 para elegir la acción a realizar[cite: 1].
* **Datos de cada estudiante:**
  * Código único (`String`)[cite: 1].
  * Nombre completo (`String`)[cite: 1].
  * Tres calificaciones enteras (`int`) entre 0 y 20[cite: 1].
* **Código de búsqueda:** El código del estudiante que se desea consultar en la opción 3[cite: 1].
* **Archivo de respaldo:** Los registros guardados previamente en `datos/estudiantes.txt` que se leen automáticamente al abrir el programa[cite: 1].

## Proceso
* **Validación de datos:** Se verifica que el código y el nombre no queden vacíos ni tengan punto y coma (`;`), que el código no esté repetido y que cada nota esté entre 0 y 20[cite: 1]. También se controlan errores si se ingresan letras en lugar de números[cite: 1].
* **Cálculos por estudiante:** Se suman las tres notas y se divide para `3.0` (para obtener decimales y evitar división entera), se identifica la nota más alta y la más baja, y se compara el promedio contra el límite de aprobación para definir si queda `APROBADO` o `REPROBADO`[cite: 1].
* **Cálculos del curso y ordenamiento:** Si existen estudiantes registrados, se cuenta el total, cuántos aprobaron y cuántos reprobaron, se saca el promedio general del grupo, el porcentaje de aprobados, quién tiene el mejor y el peor promedio, quiénes están por debajo del promedio general y se ordena la lista de mayor a menor promedio[cite: 1].
* **Persistencia:** Se lee el archivo línea por línea separando los campos por `;` al iniciar, y al guardar se reescribe el archivo completo para no duplicar registros[cite: 1].

## Salidas
* **En consola:**
  * El menú principal repetitivo y mensajes claros de confirmación o advertencia[cite: 1].
  * El listado general de estudiantes con sus notas, promedio, nota máxima, nota mínima y estado[cite: 1].
  * La ficha detallada de un estudiante cuando se busca por código[cite: 1].
  * El reporte académico completo con todos los indicadores y el ranking ordenado de mayor a menor[cite: 1].
* **En archivo:**
  * El archivo `datos/estudiantes.txt` actualizado con una línea por estudiante siguiendo el formato `codigo;nombre;nota1;nota2;nota3` (por ejemplo: `PE-007;Anthony Escobar;14;16;19`).
