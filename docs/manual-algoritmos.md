# Manual de Algoritmos y Funcionalidad - Blackjack

## 🎮 Visión General del Juego

El Blackjack implementado es una versión para consola que simula el juego de cartas entre un jugador y el dealer (la banca). El objetivo es obtener un puntaje lo más cercano posible a 21 sin pasarse.

## 🛠️ Estructuras de Datos Implementadas

### 1. Lista Enlazada (Baraja)
**Archivo:** `estructuras/ListaEnlazada.java`

#### Algoritmos Principales:
- **Agregar carta:** O(1) cuando se agrega al inicio, O(n) al final
- **Robar carta:** O(1) - elimina y retorna la primera carta
- **Recorrido:** O(n) - implementa Iterator para permitir el uso de for-each

```java
public void agregar(T dato) {
    Nodo<T> nuevoNodo = new Nodo<>(dato);
    if (cabeza == null) {
        cabeza = nuevoNodo;
    } else {
        Nodo<T> actual = cabeza;
        while (actual.siguiente != null) {
            actual = actual.siguiente;
        }
        actual.siguiente = nuevoNodo;
    }
    tamaño++;
}
```

### 2. Pila (Historial de Cartas)
**Archivo:** `estructuras/Pila.java`

#### Algoritmos Principales:
- **Apilar (push):** O(1) - agrega una carta al tope
- **Desapilar (pop):** O(1) - elimina y retorna la carta del tope
- **Ver tope:** O(1) - consulta sin eliminar

```java
public void apilar(T dato) {
    Nodo<T> nuevoNodo = new Nodo<>(dato);
    nuevoNodo.siguiente = cima;
    cima = nuevoNodo;
    tamaño++;
}
```

### 3. Cola (Gestión de Turnos)
**Archivo:** `estructuras/Cola.java`

#### Algoritmos Principales:
- **Encolar:** O(1) - agrega un jugador al final
- **Desencolar:** O(1) - retira el primer jugador
- **Ver frente:** O(1) - consulta el siguiente jugador

```java
public void encolar(T dato) {
    Nodo<T> nuevoNodo = new Nodo<>(dato);
    if (estaVacia()) {
        frente = nuevoNodo;
    } else {
        ultimo.siguiente = nuevoNodo;
    }
    ultimo = nuevoNodo;
    tamaño++;
}
```

### 4. Árbol Binario de Decisión (Dealer)
**Archivo:** `estructuras/ArbolBinario.java`

#### Algoritmos Principales:
- **Toma de decisión:** O(1) - decide si pedir carta basado en el puntaje
- **Recorrido del árbol:** O(n) para mostrar el árbol completo

```java
public Nodo<T> tomarDecision(int puntaje) {
    if (estaVacio()) {
        return null;
    }
    return puntaje < 17 ? raiz.getIzquierdo() : raiz.getDerecho();
}
```

### 5. Tabla Hash (Estado de Jugadores)
**Archivo:** `estructuras/TablaHash.java`

#### Algoritmos Principales:
- **Insertar:** O(1) promedio, O(n) peor caso
- **Buscar:** O(1) promedio, O(n) peor caso
- **Eliminar:** O(1) promedio, O(n) peor caso

```java
public void insertar(K clave, V valor) {
    if (tamaño >= umbralRehash) {
        rehash();
    }
    int indice = obtenerIndice(clave);
    Entrada<K, V> nuevaEntrada = new Entrada<>(clave, valor);
    nuevaEntrada.siguiente = tabla[indice];
    tabla[indice] = nuevaEntrada;
    tamaño++;
}
```

## 🎲 Mecánicas del Juego

### Inicio de Partida
1. Se crea una nueva baraja (ListaEnlazada)
2. Se mezcla la baraja usando Fisher-Yates
3. Se reparten 2 cartas a cada jugador
4. Se verifica Blackjack inicial

### Turno del Jugador
1. Muestra cartas y puntaje actual
2. Opciones:
   - Pedir carta (hit)
   - Plantarse (stand)
3. Verifica si se pasó de 21

### Turno del Dealer
1. Revela carta oculta
2. Usa árbol de decisión:
   - Si puntaje < 17: pide carta
   - Si puntaje ≥ 17: se planta
3. Verifica si se pasó de 21

### Determinación del Ganador
1. Si alguien se pasó de 21, pierde automáticamente
2. Si nadie se pasó, gana el más cercano a 21
3. En caso de empate, se declara empate

## 🔧 Algoritmos Especiales

### Mezcla de Cartas (Fisher-Yates)
```java
public void mezclar() {
    Carta[] arregloCartas = new Carta[cartas.tamaño()];
    // Llenar arreglo
    for (int i = arregloCartas.length - 1; i > 0; i--) {
        int j = random.nextInt(i + 1);
        Carta temp = arregloCartas[i];
        arregloCartas[i] = arregloCartas[j];
        arregloCartas[j] = temp;
    }
    // Reconstruir lista
}
```

### Cálculo de Puntaje con Ases
```java
public int calcularPuntaje() {
    int puntaje = 0;
    int ases = 0;
    // Sumar cartas no-As
    // Optimizar valor de Ases (1 u 11)
    return puntaje;
}
```

## 🎯 Uso del Programa

1. Ejecutar `Main.java`
2. Ingresar nombre del jugador
3. En cada turno:
   - Ver estado actual
   - Elegir acción
   - Esperar turno del dealer
4. Al final de cada ronda:
   - Ver resultado
   - Decidir si jugar otra ronda

## 🔍 Complejidad Temporal

- **Inicio del juego:** O(n) donde n es el número de cartas
- **Mezclar baraja:** O(n) usando Fisher-Yates
- **Turno del jugador:** O(1) por acción
- **Turno del dealer:** O(1) por decisión
- **Cálculo de puntaje:** O(n) donde n es el número de cartas en mano

## 📊 Uso de Memoria

- **Lista Enlazada (Baraja):** O(n) donde n es el número de cartas
- **Pila (Historial):** O(m) donde m es el número de jugadas
- **Cola (Turnos):** O(p) donde p es el número de jugadores
- **Árbol Binario:** O(1) - estructura fija para el dealer
- **Tabla Hash:** O(k) donde k es el número de jugadores activos
