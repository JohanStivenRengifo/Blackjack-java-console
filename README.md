# Plan de Trabajo - Proyecto Black Jack (Consola Java)

## Equipo
- **Joha**
- **Alix**
- **Julian**
- **Sebastian**
- **Tatiana**

## Objetivos
- Juego de Black Jack en consola (Java)
- Jugador vs Máquina
- Uso de: Árboles, Pilas, Colas, Tabla Hash, Listas enlazadas y doblemente enlazadas
- Sin interfaz gráfica
- Trabajo colaborativo en GitHub, usando ramas y Pull Requests

---

## Día 1: Planeación y Entorno

- [ ] Crear issues y asignar tareas en GitHub.
- [ ] Reunión breve para aclarar dudas y definir reglas del juego.
- [ ] Configurar el entorno: estructura del repo, README, gitignore, estructura base de carpetas.

---

## División de Tareas (Por Persona)

### 1. **Joha**  
**Responsable de la lógica principal y manejo de pilas**
- Implementar la lógica del juego Black Jack (turnos, reglas básicas).
- Usar una **pila** para el manejo de cartas jugadas/descartadas.
- Documentar el flujo principal del juego.

### 2. **Alix**  
**Responsable de las estructuras de cartas y manejo de listas**
- Implementar la clase `Carta` y la baraja usando **lista doblemente enlazada**.
- Implementar la mano de cada jugador como **lista enlazada simple**.
- Métodos para barajar, repartir, agregar y eliminar cartas.

### 3. **Julian**  
**Responsable de colas y control de turnos**
- Implementar una **cola** para el flujo de turnos (jugador y máquina).
- Controlar el flujo de juego, alternancia de turnos y prioridades.
- Integrar la cola con la lógica principal.

### 4. **Sebastian**  
**Responsable de la tabla hash y registro de partidas**
- Crear una **tabla hash** para registrar resultados de partidas (victorias, derrotas).
- Permitir consultar el historial de partidas.
- Métodos para serializar/deserializar si hay persistencia.

### 5. **Tatiana**  
**Responsable del árbol y estadísticas**
- Implementar un **árbol binario** para estadísticas (por ejemplo: puntajes máximos/minimos, historial de manos ganadoras).
- Métodos para recorrer y mostrar estadísticas.
- Integrar el árbol con el registro de partidas.

---

## Día 2-6: Desarrollo

- Cada uno trabaja en una **rama propia** (`feature/<nombre-tarea>`).
- Al terminar una parte, hacer **Pull Request** para revisión y merge a `main`.
- Revisar y comentar los PRs de los compañeros.
- Integrar avances progresivamente.
- Mantener comunicación diaria para resolver bloqueos.

---

## Día 7: Integración y Pruebas

- Unir todos los módulos.
- Probar el juego de inicio a fin.
- Corregir bugs y pulir detalles.
- Mejorar documentación y comentarios en el código.

---

## Día 8: Entrega Final

- Revisar que todo esté en el `main`.
- Subir entrega manual y audiovisual (instrucciones de uso en el README).
- Preparar demo en video mostrando el funcionamiento y la estructura de datos implementada.
- Limpiar el repositorio (quitar ramas obsoletas, archivos temporales).

---

## Buenas Prácticas

- **Commits atómicos y descriptivos.**
- Hacer Pull Request para cada aporte importante.
- Revisar el código de los compañeros.
- Documentar el código y las estructuras de datos usadas.
- Resolver conflictos de merge colaborativamente.

---

## Propuesta de Estructura de Carpetas

```
blackjack-java-console/
│
├── src/
│   ├── main/
│   │   ├── java/
│   │   │   ├── blackjack/
│   │   │   │   ├── App.java
│   │   │   │   ├── carta/
│   │   │   │   ├── baraja/
│   │   │   │   ├── estructuras/
│   │   │   │   │   ├── pila/
│   │   │   │   │   ├── cola/
│   │   │   │   │   ├── lista/
│   │   │   │   │   ├── arbol/
│   │   │   │   │   ├── tablaHash/
│   │   │   │   ├── juego/
│   │   │   │   ├── estadisticas/
│   ├── test/
│
├── README.md
└── .gitignore
```

---

## Seguimiento

- Usar Issues y Proyectos de GitHub para seguimiento de tareas.
- Actualizar el README con instrucciones de compilación, ejecución y explicación de estructuras de datos.

---