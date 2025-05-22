package modelo;

import estructuras.Pila;
import estructuras.ListaEnlazada;

/**
 * Representa un jugador en el juego de Blackjack.
 * Mantiene el control de su nombre, mano de cartas, puntaje y estado.
 */
public class Jugador {

    private String nombre;
    private ListaEnlazada<Carta> mano;
    private Pila<Carta> historialCartas;
    private boolean plantado;
    private int partidasGanadas;

    /**
     * Constructor del jugador.
     * 
     * @param nombre El nombre del jugador.
     */
    public Jugador(String nombre) {
        this.nombre = nombre;
        this.mano = new ListaEnlazada<>();
        this.historialCartas = new Pila<>();
        this.plantado = false;
        this.partidasGanadas = 0;
    }

    /**
     * Obtiene el nombre del jugador.
     * 
     * @return El nombre del jugador.
     */
    public String getNombre() {
        return nombre;
    }

    /**
     * Agrega una carta a la mano del jugador y al historial.
     * 
     * @param carta La carta a agregar.
     */
    public void recibirCarta(Carta carta) {
        mano.agregar(carta);
        historialCartas.apilar(carta);
    }

    /**
     * Calcula el puntaje actual de la mano del jugador según las reglas del
     * Blackjack.
     * Los Ases pueden valer 1 u 11, eligiendo el valor que más beneficie al
     * jugador.
     * 
     * @return El puntaje de la mano.
     */
    public int calcularPuntaje() {
        int puntaje = 0;
        int ases = 0;

        // Recorrer todas las cartas en la mano
        for (int i = 0; i < mano.tamaño(); i++) {
            Carta carta = mano.obtenerElemento(i);
            if (carta.esAs()) {
                ases++;
            } else {
                puntaje += carta.getValorNumerico();
            }
        }

        // Optimizar el valor de los Ases
        for (int i = 0; i < ases; i++) {
            if (puntaje + 11 <= 21) {
                puntaje += 11;
            } else {
                puntaje += 1;
            }
        }

        return puntaje;
    }

    /**
     * Verifica si el jugador se ha pasado de 21 puntos.
     * 
     * @return true si el jugador se ha pasado, false en caso contrario.
     */
    public boolean sePaso() {
        return calcularPuntaje() > 21;
    }

    /**
     * Verifica si el jugador tiene Blackjack (21 puntos con 2 cartas).
     * 
     * @return true si el jugador tiene Blackjack, false en caso contrario.
     */
    public boolean tieneBlackjack() {
        return mano.tamaño() == 2 && calcularPuntaje() == 21;
    }

    /**
     * Marca al jugador como plantado (no quiere más cartas).
     */
    public void plantarse() {
        this.plantado = true;
    }

    /**
     * Verifica si el jugador está plantado.
     * 
     * @return true si el jugador está plantado, false en caso contrario.
     */
    public boolean estaPantado() {
        return plantado;
    }

    /**
     * Obtiene la mano actual del jugador.
     * 
     * @return La lista de cartas en la mano del jugador.
     */
    public ListaEnlazada<Carta> getMano() {
        return mano;
    }

    /**
     * Obtiene el historial de cartas del jugador.
     * 
     * @return La pila con el historial de cartas.
     */
    public Pila<Carta> getHistorialCartas() {
        return historialCartas;
    }

    /**
     * Incrementa el contador de partidas ganadas.
     */
    public void incrementarPartidasGanadas() {
        partidasGanadas++;
    }

    /**
     * Obtiene el número de partidas ganadas.
     * 
     * @return El número de partidas ganadas.
     */
    public int getPartidasGanadas() {
        return partidasGanadas;
    }

    /**
     * Limpia la mano del jugador para una nueva ronda.
     */
    public void limpiarMano() {
        while (!mano.estaVacia()) {
            mano.eliminarPrimero();
        }
        plantado = false;
    }

    /**
     * Muestra el estado actual del jugador.
     * 
     * @return Una representación en cadena del jugador.
     */
    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append(nombre).append(" (Puntaje: ").append(calcularPuntaje()).append(")\n");
        sb.append("Mano: ");

        for (int i = 0; i < mano.tamaño(); i++) {
            Carta carta = mano.obtenerElemento(i);
            sb.append(carta);
            if (i < mano.tamaño() - 1) {
                sb.append(", ");
            }
        }

        sb.append("\nEstado: ").append(plantado ? "Plantado" : "Jugando");
        sb.append("\nPartidas ganadas: ").append(partidasGanadas);

        return sb.toString();
    }
}