package modelo;

import estructuras.ArbolBinario;

/**
 * Representa al Dealer (la casa) en el juego de Blackjack.
 * Extiende la clase Jugador y utiliza un Árbol Binario para tomar decisiones.
 */
public class Dealer extends Jugador {

    private static final int UMBRAL_PLANTARSE = 17;
    private ArbolBinario<String> arbolDecision;

    /**
     * Constructor del Dealer.
     */
    public Dealer() {
        super("Dealer");
        this.arbolDecision = new ArbolBinario<>();
    }

    /**
     * Decide automáticamente si el Dealer debe pedir otra carta o plantarse
     * basado en el árbol de decisión.
     * 
     * @return true si debe pedir carta, false si debe plantarse.
     */
    public boolean debePedirCarta() {
        int puntajeActual = calcularPuntaje();
        ArbolBinario.Nodo<String> decision = arbolDecision.tomarDecision(puntajeActual);

        return decision != null && decision.getDato().equals("Pedir carta");
    }

    /**
     * Ejecuta el turno automático del Dealer según las reglas del Blackjack.
     * El Dealer pide cartas hasta alcanzar al menos 17 puntos.
     * 
     * @param baraja La baraja de la cual robar cartas.
     */
    public void jugarTurnoAutomatico(Baraja baraja) {
        // Mostrar la primera carta del Dealer
        System.out.println("\nTurno del Dealer:");
        System.out.println("Cartas iniciales del Dealer: " + getMano().obtenerElemento(0) + " y [Carta oculta]");

        // Revelar la carta oculta
        System.out.println("El Dealer revela su carta oculta: " + getMano().obtenerElemento(1));
        System.out.println("Puntaje actual del Dealer: " + calcularPuntaje());

        // El Dealer pide cartas según su árbol de decisión
        while (debePedirCarta() && !baraja.estaVacia()) {
            Carta nuevaCarta = baraja.robarCarta();
            recibirCarta(nuevaCarta);
            System.out.println("El Dealer toma una carta: " + nuevaCarta);
            System.out.println("Puntaje actual del Dealer: " + calcularPuntaje());

            // Si se pasa de 21, termina su turno
            if (sePaso()) {
                System.out.println("¡El Dealer se ha pasado de 21!");
                break;
            }
        }

        // Si no se pasó y no pidió más cartas, se planta
        if (!sePaso()) {
            plantarse();
            System.out.println("El Dealer se planta con " + calcularPuntaje() + " puntos.");
        }
    }

    /**
     * Muestra el estado actual del Dealer, ocultando la segunda carta si el juego
     * está en curso.
     * 
     * @param revelarTodasLasCartas true para mostrar todas las cartas, false para
     *                              ocultar la segunda.
     * @return Una representación en cadena del Dealer.
     */
    public String toString(boolean revelarTodasLasCartas) {
        if (revelarTodasLasCartas || estaPantado() || sePaso() || getMano().tamaño() <= 1) {
            return super.toString();
        }

        // Ocultar la segunda carta durante el juego
        StringBuilder sb = new StringBuilder();
        sb.append(getNombre()).append(" (Puntaje: ?)");
        sb.append("\nMano: ").append(getMano().obtenerElemento(0)).append(", [Carta oculta]");
        sb.append("\nEstado: Jugando");

        return sb.toString();
    }

    @Override
    public String toString() {
        return toString(false);
    }
}