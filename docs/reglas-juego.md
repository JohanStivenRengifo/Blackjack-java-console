# Reglas del Juego - Blackjack

Este documento describe las reglas del juego de Blackjack implementadas en este proyecto.

## Objetivo del Juego

El objetivo del Blackjack es obtener una mano con un valor total lo más cercano posible a 21 sin pasarse. El jugador compite únicamente contra el Dealer (la casa), no contra otros jugadores.

## Valor de las Cartas

- **Cartas numéricas (2-10)**: Valen su valor nominal.
- **Figuras (J, Q, K)**: Valen 10 puntos cada una.
- **As (A)**: Puede valer 1 u 11 puntos, según lo que más convenga al jugador.

## Desarrollo del Juego

1. **Inicio de la partida**:
   - Se reparten 2 cartas a cada jugador, incluido el Dealer.
   - Las cartas del jugador se muestran boca arriba.
   - Solo se muestra una carta del Dealer, la otra permanece oculta hasta que termine el turno del jugador.

2. **Blackjack natural**:
   - Si un jugador recibe un As y una carta de valor 10 (10, J, Q o K) en las dos primeras cartas, tiene un "Blackjack" natural.
   - Un Blackjack natural gana automáticamente, a menos que el Dealer también tenga Blackjack, en cuyo caso es un empate.

3. **Turno del jugador**:
   - El jugador decide si "pedir" más cartas o "plantarse" con las que tiene.
   - Puede pedir tantas cartas como desee, siempre que no se pase de 21.
   - Si el total supera 21, el jugador "se pasa" y pierde automáticamente.
   - Cuando el jugador decide plantarse, su turno termina.

4. **Turno del Dealer**:
   - El Dealer revela su carta oculta.
   - El Dealer debe seguir reglas fijas: pedir carta si su total es menor a 17, y plantarse si es 17 o más.
   - Si el Dealer se pasa de 21, el jugador gana automáticamente (si no se pasó antes).

5. **Determinación del ganador**:
   - Si el jugador se pasó de 21, el Dealer gana.
   - Si el Dealer se pasó de 21, el jugador gana.
   - Si ninguno se pasó, gana quien tenga el total más alto.
   - Si ambos tienen el mismo total, es un empate.

## Reglas Específicas de esta Implementación

- No se permite dividir pares (split).
- No se permite doblar la apuesta (double down).
- No se utiliza la regla de rendición (surrender).
- El Dealer siempre se planta en 17 o más (incluso si es un 17 "suave" con un As).
- Los Ases se cuentan como 11 puntos a menos que esto haga que el total supere 21, en cuyo caso valen 1 punto.

## Estructura de Turnos

En esta implementación, los turnos se gestionan mediante una estructura de Cola (FIFO):

1. El jugador humano siempre juega primero.
2. El Dealer juega después del jugador humano.
3. Los turnos se reinician en cada nueva ronda de juego.

## Lógica de Decisión del Dealer

El Dealer utiliza un Árbol Binario de Decisión para determinar sus acciones:

- Si el puntaje del Dealer es menor a 17, siempre pedirá otra carta (rama izquierda del árbol).
- Si el puntaje del Dealer es 17 o mayor, siempre se plantará (rama derecha del árbol).

Esta lógica es fija y no cambia durante el juego, siguiendo las reglas estándar del Blackjack de casino.