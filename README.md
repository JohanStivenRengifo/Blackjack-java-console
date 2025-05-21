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

## Día 1: Planeación y Entorno

- [x] Crear issues y asignar tareas en GitHub
- [x] Implementar lógica principal del juego

## Flujo del Juego

### Estructura de Datos

- **Mazo Principal (deck)**: Implementado como una `Stack<Card>` que contiene las cartas disponibles para jugar
- **Pila de Descartes (discardPile)**: Implementado como una `Stack<Card>` que almacena las cartas jugadas
- **Manos de Jugadores**: Implementadas como `List<Card>` para mantener las cartas actuales de cada jugador

### Reglas Implementadas

1. El valor de las cartas:
   - Cartas numéricas (2-10): Valor nominal
   - Figuras (J, Q, K): 10 puntos
   - As (A): 1 u 11 puntos (se ajusta automáticamente)

2. Flujo del juego:
   - Se reparten 2 cartas a cada jugador
   - El jugador decide si pedir más cartas ("hit") o plantarse ("stand")
   - El dealer debe pedir carta si tiene menos de 17
   - Gana quien se acerque más a 21 sin pasarse

### Manejo de Cartas

1. **Inicio del Juego**:
   - Se crea y mezcla el mazo inicial
   - Las cartas se van tomando del tope de la pila (deck)

2. **Durante el Juego**:
   - Las cartas se reparten desde el mazo principal
   - Si el mazo se agota, se reciclan las cartas descartadas

3. **Final de cada Ronda**:
   - Las cartas usadas van a la pila de descartes
   - Se puede iniciar una nueva ronda con las mismas cartas

### Características Especiales

- Manejo automático del valor del As
- Barajeo automático cuando se agota el mazo
- Sistema de turnos interactivo
- Interfaz de consola clara y fácil de usar

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