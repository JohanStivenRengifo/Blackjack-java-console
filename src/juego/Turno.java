package juego;

import estructuras.Cola;
import modelo.Jugador;

/**
 * Gestiona los turnos de juego utilizando una Cola (FIFO).
 * Controla el orden en que los jugadores y el dealer realizan sus acciones.
 */
public class Turno {

    private Cola<Jugador> colaTurnos;

    /**
     * Constructor de la clase Turno.
     */
    public Turno() {
        this.colaTurnos = new Cola<>();
    }

    /**
     * Agrega un jugador a la cola de turnos.
     * 
     * @param jugador El jugador a agregar.
     */
    public void agregarJugador(Jugador jugador) {
        colaTurnos.encolar(jugador);
    }

    /**
     * Obtiene el siguiente jugador en la cola de turnos sin eliminarlo.
     * 
     * @return El siguiente jugador o null si no hay más jugadores.
     */
    public Jugador verSiguienteJugador() {
        return colaTurnos.verFrente();
    }

    /**
     * Obtiene y elimina el siguiente jugador de la cola de turnos.
     * 
     * @return El siguiente jugador o null si no hay más jugadores.
     */
    public Jugador siguienteJugador() {
        return colaTurnos.desencolar();
    }

    /**
     * Verifica si la cola de turnos está vacía.
     * 
     * @return true si no hay más jugadores en la cola, false en caso contrario.
     */
    public boolean sinJugadores() {
        return colaTurnos.estaVacia();
    }

    /**
     * Reinicia la cola de turnos con los jugadores proporcionados.
     * 
     * @param jugadores Los jugadores para reiniciar la cola.
     */
    public void reiniciarTurnos(Jugador... jugadores) {
        // Vaciar la cola actual
        while (!colaTurnos.estaVacia()) {
            colaTurnos.desencolar();
        }

        // Agregar los jugadores en el orden proporcionado
        for (Jugador jugador : jugadores) {
            colaTurnos.encolar(jugador);
        }
    }

    /**
     * Muestra el estado actual de la cola de turnos.
     * 
     * @return Una representación en cadena de la cola de turnos.
     */
    @Override
    public String toString() {
        return "Orden de turnos: " + colaTurnos.toString();
    }
}