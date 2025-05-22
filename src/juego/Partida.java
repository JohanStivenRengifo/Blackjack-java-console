package juego;

import estructuras.Pila;
import modelo.Carta;
import modelo.Jugador;

/**
 * Representa una partida individual de Blackjack.
 * Almacena información sobre la partida como las cartas jugadas y el resultado.
 */
public class Partida {

    private int id;
    private Jugador ganador;
    private Pila<JugadaCarta> historialCartas;
    private String resultado;
    private static int contadorPartidas = 0;

    /**
     * Clase interna para representar una carta jugada con información adicional
     */
    private static class JugadaCarta {
        private Carta carta;
        private String nombreJugador;
        private int puntajeResultante;

        public JugadaCarta(Carta carta, String nombreJugador, int puntajeResultante) {
            this.carta = carta;
            this.nombreJugador = nombreJugador;
            this.puntajeResultante = puntajeResultante;
        }

        @Override
        public String toString() {
            return nombreJugador + " jugó " + carta + " (Puntaje: " + puntajeResultante + ")";
        }
    }

    /**
     * Constructor de la partida.
     */
    public Partida() {
        this.id = ++contadorPartidas;
        this.historialCartas = new Pila<>();
        this.ganador = null;
        this.resultado = "En curso";
    }

    /**
     * Obtiene el ID de la partida.
     * 
     * @return El ID de la partida.
     */
    public int getId() {
        return id;
    }

    /**
     * Registra una carta jugada en el historial.
     * 
     * @param carta   La carta a registrar.
     * @param jugador El jugador que jugó la carta.
     */
    public void registrarCartaJugada(Carta carta, Jugador jugador) {
        JugadaCarta jugada = new JugadaCarta(carta, jugador.getNombre(), jugador.calcularPuntaje());
        historialCartas.apilar(jugada);
        System.out.println(jugada.toString());
    }

    /**
     * Establece el ganador de la partida.
     * 
     * @param ganador El jugador ganador.
     * @param razon   La razón por la que ganó.
     */
    public void establecerGanador(Jugador ganador, String razon) {
        this.ganador = ganador;
        this.resultado = ganador != null ? ganador.getNombre() + " ganó: " + razon : "Empate: " + razon;

        // Incrementar contador de partidas ganadas si hay un ganador
        if (ganador != null) {
            ganador.incrementarPartidasGanadas();
        }
    }

    /**
     * Obtiene el ganador de la partida.
     * 
     * @return El jugador ganador o null si fue empate.
     */
    public Jugador getGanador() {
        return ganador;
    }

    /**
     * Obtiene el resultado de la partida.
     * 
     * @return Una descripción del resultado de la partida.
     */
    public String getResultado() {
        return resultado;
    }

    /**
     * Obtiene el historial de cartas jugadas.
     * 
     * @return La pila con el historial de cartas.
     */
    public Pila<JugadaCarta> getHistorialCartas() {
        return historialCartas;
    }

    /**
     * Muestra el historial de cartas jugadas en orden inverso (desde la primera
     * hasta la última).
     * 
     * @return Una representación en cadena del historial de cartas.
     */
    public String mostrarHistorialCartas() {
        StringBuilder sb = new StringBuilder();
        sb.append("\n=== Historial de Jugadas ===\n");
        String historial = historialCartas.mostrarInverso();
        sb.append(historial);
        sb.append("\n=== Fin del Historial ===\n");
        return sb.toString();
    }

    /**
     * Muestra el resumen de la partida.
     * 
     * @return Una representación en cadena de la partida.
     */
    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("\n====================================\n");
        sb.append("        RESUMEN DE LA PARTIDA #").append(id).append("\n");
        sb.append("====================================\n");
        sb.append("Resultado: ").append(resultado).append("\n");
        sb.append("Total de jugadas: ").append(historialCartas.tamaño()).append("\n");
        if (ganador != null) {
            sb.append("Ganador: ").append(ganador.getNombre())
                    .append(" (").append(ganador.getPartidasGanadas()).append(" victorias)\n");
        } else {
            sb.append("Resultado: Empate\n");
        }
        sb.append("====================================\n");

        return sb.toString();
    }
}