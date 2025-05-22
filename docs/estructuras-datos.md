# Estructuras de Datos - Blackjack

Este documento describe las estructuras de datos implementadas para el juego de Blackjack, todas desarrolladas desde cero sin utilizar las clases predefinidas de Java.

## Lista Enlazada

**Archivo:** `estructuras.ListaEnlazada`

**Uso en el juego:** Representa la baraja de cartas.

**Características principales:**
- Implementación genérica con nodos enlazados unidireccionalmente
- Operaciones principales:
  - `agregar(T dato)`: Agrega un elemento al final de la lista
  - `eliminarPrimero()`: Elimina y devuelve el primer elemento (simula robar una carta)
  - `estaVacia()`: Verifica si la lista está vacía
  - `tamaño()`: Devuelve el número de elementos en la lista
- Incluye método recursivo para recorrer la lista

## Pila (Stack)

**Archivo:** `estructuras.Pila`

**Uso en el juego:** Almacena el historial de cartas jugadas.

**Características principales:**
- Implementación LIFO (Last In, First Out)
- Operaciones principales:
  - `apilar(T dato)`: Agrega un elemento a la cima de la pila
  - `desapilar()`: Elimina y devuelve el elemento en la cima
  - `verCima()`: Devuelve el elemento en la cima sin eliminarlo
  - `estaVacia()`: Verifica si la pila está vacía
- Incluye método recursivo para mostrar la pila en orden inverso

## Cola (Queue)

**Archivo:** `estructuras.Cola`

**Uso en el juego:** Controla los turnos entre jugador y dealer.

**Características principales:**
- Implementación FIFO (First In, First Out)
- Operaciones principales:
  - `encolar(T dato)`: Agrega un elemento al final de la cola
  - `desencolar()`: Elimina y devuelve el elemento al frente de la cola
  - `verFrente()`: Devuelve el elemento al frente sin eliminarlo
  - `estaVacia()`: Verifica si la cola está vacía
- Incluye método recursivo para mostrar la cola

## Árbol Binario de Decisión

**Archivo:** `estructuras.ArbolBinario`

**Uso en el juego:** Implementa la lógica de decisión del Dealer.

**Características principales:**
- Estructura jerárquica con nodos que tienen hasta dos hijos (izquierdo y derecho)
- Operaciones principales:
  - `insertar(Nodo<T> actual, T dato, boolean condicion)`: Inserta un nuevo nodo según una condición
  - `tomarDecision(int valor, int umbral)`: Toma una decisión basada en el valor proporcionado
  - Métodos de recorrido: `recorridoEnOrden()`, `recorridoPreorden()`, `recorridoPostorden()`
- Implementa la lógica del Dealer:
  - Rama izquierda: si puntaje < 17 → pedir carta
  - Rama derecha: si puntaje ≥ 17 → plantarse

## Tabla Hash

**Archivo:** `estructuras.TablaHash`

**Uso en el juego:** Almacena el estado de los jugadores.

**Características principales:**
- Implementación con resolución de colisiones por encadenamiento
- Operaciones principales:
  - `insertar(K clave, V valor)`: Inserta o actualiza un par clave-valor
  - `obtener(K clave)`: Obtiene el valor asociado a una clave
  - `eliminar(K clave)`: Elimina una entrada de la tabla
  - `contiene(K clave)`: Verifica si la tabla contiene una clave
- Incluye rehashing automático cuando el factor de carga supera el umbral
- Almacena información de los jugadores: nombre, puntaje, cartas en mano, estado actual

## Integración de las Estructuras

Las estructuras de datos se integran en el juego de la siguiente manera:

1. La **Lista Enlazada** se utiliza en la clase `Baraja` para almacenar y gestionar las cartas disponibles.
2. La **Pila** se utiliza en las clases `Jugador` y `Partida` para mantener un historial de las cartas jugadas.
3. La **Cola** se utiliza en la clase `Turno` para gestionar el orden de los jugadores.
4. El **Árbol Binario** se utiliza en la clase `Dealer` para tomar decisiones automáticas.
5. La **Tabla Hash** se utiliza en la clase `Blackjack` para almacenar y acceder rápidamente al estado de los jugadores.

Todas estas estructuras han sido implementadas desde cero, sin utilizar las clases predefinidas de Java, cumpliendo con los requisitos del proyecto.