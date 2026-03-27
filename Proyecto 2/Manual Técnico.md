# Manual Técnico

## Sistema: *Quetzal Escobas - Carrera de Magos*

**Autor:** Wendy Culajay
**Curso:** IPC1
**Lenguaje:** Java (Swing)

---

##  1. Descripción General

El sistema **Quetzal Escobas** es una aplicación de escritorio desarrollada en Java utilizando la librería Swing. Permite simular una carrera de escobas mágicas entre personajes, así como registrar resultados y visualizar estadísticas.

---

##  2. Arquitectura del Sistema

El sistema está estructurado bajo un enfoque modular orientado a objetos:

```
Recursos/
│
├── Principal.java          ← Ventana principal (menú)
├── Personaje.java         ← Modelo de corredor
├── VentanaCarrera.java    ← Simulación de carrera
```

###  Componentes principales

* **Principal**

  * Clase principal del sistema
  * Administra la interfaz gráfica principal
  * Controla la navegación entre funcionalidades

* **Personaje**

  * Representa a un corredor
  * Contiene atributos como nombre

* **VentanaCarrera**

  * Simula la carrera entre personajes
  * Registra resultados en el historial

---

##  3. Interfaz Gráfica

La interfaz está construida con **Java Swing** y presenta:

###  Características:

* Fondo con degradado personalizado
* Botones estilizados
* Uso de colores temáticos:

  * Negro mágico
  * Lila oscuro
  * Lila claro

###  Componentes utilizados:

* `JFrame`
* `JPanel`
* `JButton`
* `JLabel`
* `JDialog`
* `JOptionPane`

---

##  4. Funcionalidades

###  4.1 Jugar

Permite iniciar una carrera seleccionando un personaje disponible.

```java
private void iniciarCarrera()
```

* Muestra un cuadro de selección (`JOptionPane`)
* Abre la ventana de carrera

---

###  4.2 Crear Personaje

Permite ingresar un nuevo corredor al sistema.

```java
btnCrear.addActionListener(...)
```

* Solicita nombre
* Agrega a la lista de personajes

---

###  4.3 Ver Tops y Gráfica

Muestra estadísticas de partidas jugadas.

```java
private void mostrarEstadisticas()
```

Incluye:

* Gráfica de barras
* Historial de partidas
* Exportación de reporte

---

###  4.4 Exportar Reporte

```java
private void exportarReporte()
```

* Genera un reporte en formato imprimible
* Utiliza `JTextArea.print()`
* Incluye:

  * Encabezado
  * Historial de partidas

---

###  4.5 Salir

Finaliza la ejecución del programa:

```java
System.exit(0);
```

---

##  5. Sistema de Gráficas

La gráfica se genera manualmente utilizando `Graphics2D`.

###  Método principal:

```java
private void dibujarBarras(Graphics g, int w, int h)
```

###  Funcionamiento:

* Dibuja eje base
* Genera barras dinámicas según historial
* Máximo de 5 registros

---

##  6. Personalización Visual

###  Colores definidos:

```java
public static final Color NEGRO_MAGICO = new Color(15, 10, 25);
public static final Color LILA_OSCURO = new Color(75, 0, 130);
public static final Color LILA_CLARO = new Color(147, 0, 234);
```

###  Fondo degradado:

Uso de `GradientPaint` para efecto visual:

```java
GradientPaint gp = new GradientPaint(0, 0, LILA_OSCURO, getWidth(), getHeight(), NEGRO_MAGICO);
```

---

##  7. Manejo de Datos

###  Estructuras utilizadas:

```java
private List<Personaje> personajes;
private List<String> historialPartidas;
```

* `personajes`: almacena corredores
* `historialPartidas`: guarda resultados

---

##  8. Flujo del Sistema

1. Inicio del programa
2. Visualización del menú principal
3. Selección de opción:

   * Jugar
   * Crear personaje
   * Ver estadísticas
4. Ejecución de acción
5. Registro de resultados

---

##  9. Ejecución del Programa

###  Método principal:

```java
public static void main(String[] args)
```

###  Ejecución:

```bash
javac Principal.java
java Principal
```

---

##  10. Consideraciones Técnicas

* Requiere Java JDK 8 o superior
* Uso de Swing (no JavaFX)
* Manejo básico de eventos
* No utiliza base de datos (datos en memoria)

---

##  11. Posibles Mejoras

* Guardado en archivos (persistencia)
* Exportación real a PDF (con librerías como iText)
* Mejora de gráficos (librerías externas)
* Animaciones en carrera

---

##  12. Conclusión

El sistema demuestra el uso de interfaces gráficas en Java, manejo de eventos, estructuras dinámicas y personalización visual, siendo una base sólida para aplicaciones más complejas.

---



