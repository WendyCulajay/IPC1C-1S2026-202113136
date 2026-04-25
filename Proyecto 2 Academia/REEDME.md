# Proyecto Sancarlista Academy

Sistema académico desarrollado en Java con arquitectura MVC, interfaz gráfica en Swing y persistencia mediante archivos.  
El proyecto permite gestionar estudiantes, instructores, cursos, secciones y notas, incluyendo carga masiva desde archivos CSV.

## Objetivos
- Implementar un sistema académico con roles de Administrador, Estudiante e Instructor.
- Aplicar conceptos de Programación Orientada a Objetos: herencia, polimorfismo y encapsulamiento.
- Usar MVC para separar lógica, datos y vista.
- Incorporar persistencia mediante archivos serializados y carga masiva con CSV.
- Garantizar consistencia visual en la interfaz gráfica.

## Estructura del proyecto
- **Modelo**  
  Usuario, Estudiante, Instructor, Curso, Seccion, Nota
- **Controlador**  
  Sistema, GestorDatos
- **Vista**  
  MenuAdmin, MenuEstudiante, MenuInstructor, GestionCursos, GestionEstudiantes, GestionInstructores, GestionarSecciones

## Funcionalidades principales
- **Administrador**
  - Crear, actualizar y eliminar estudiantes, instructores y cursos.
  - Asignar notas a estudiantes.
  - Cargar datos masivamente desde archivos CSV.
- **Estudiante**
  - Iniciar sesión con su código y contraseña.
  - Consultar sus notas por curso.
- **Instructor**
  - Iniciar sesión con su código y contraseña.
  - Consultar los cursos y secciones asignadas.
- **Sistema**
  - Manejo de arreglos dinámicos para usuarios, cursos, secciones y notas.
  - Métodos para agregar, actualizar, eliminar y listar entidades.
  - Persistencia con `guardarDatos()` y `cargarDatos()`.

## Carga Masiva (CSV)
El sistema permite cargar datos desde archivos CSV con el siguiente formato:

- **Cursos (cursos.csv)**: 4 columnas