package juego;

import estructuras.ListaEnlazada;
import estructuras.TablaHash;
import modelo.Baraja;
import modelo.Carta;
import modelo.Dealer;
import modelo.Jugador;

import java.util.Scanner;

/**
 * Clase principal que implementa la lógica del juego Blackjack.
 * Integra todas las estructuras de datos y clases del modelo.
 */
public class Blackjack {

    private Baraja baraja;
    private Jugador jugador;
    private Dealer dealer;
    private Turno turno;
    private TablaHash<String, Jugador> estadoJugadores;
    private Scanner scanner;
    private boolean juegoEnCurso;

    /**
     * Constructor del juego Blackjack.
     */
    public Blackjack() {
        this.baraja = new Baraja();
        this.scanner = new Scanner(System.in);
        this.turno = new Turno();
        this.estadoJugadores = new TablaHash<>();
        this.juegoEnCurso = false;
    }

    /**
     * Inicia el juego de Blackjack.
     */
    public void iniciar() {
        System.out.println("====================================");
        System.out.println("   BIENVENIDO AL JUEGO BLACKJACK   ");
        System.out.println("====================================");

        // Solicitar nombre del jugador
        System.out.print("\nIngrese su nombre: ");
        String nombreJugador = scanner.nextLine().trim();
        if (nombreJugador.isEmpty()) {
            nombreJugador = "Jugador";
        }

        // Inicializar jugadores
        jugador = new Jugador(nombreJugador);
        dealer = new Dealer();

        // Registrar jugadores en la tabla hash
        estadoJugadores.insertar(jugador.getNombre(), jugador);
        estadoJugadores.insertar(dealer.getNombre(), dealer);

        // Iniciar el bucle principal del juego
        juegoEnCurso = true;
        while (juegoEnCurso) {
            jugarRonda();
            preguntarNuevaRonda();
        }

        // Mostrar estadísticas finales
        mostrarEstadisticasFinales();
        System.out.println("\n¡Gracias por jugar! ¡Hasta pronto!");
        scanner.close();
    }

    /**
     * Ejecuta una ronda completa del juego.
     */
    private void jugarRonda() {
        System.out.println("\n====================================");
        System.out.println("        NUEVA RONDA DE JUEGO        ");
        System.out.println("====================================");

        // Preparar la baraja
        baraja = new Baraja();
        baraja.mezclar();

        // Limpiar manos de los jugadores
        jugador.limpiarMano();
        dealer.limpiarMano();

        // Repartir cartas iniciales (2 a cada uno)
        for (int i = 0; i < 2; i++) {
            jugador.recibirCarta(baraja.robarCarta());
            dealer.recibirCarta(baraja.robarCarta());
        }

        // Configurar el orden de los turnos (primero jugador, luego dealer)
        turno.reiniciarTurnos(jugador, dealer);

        // Mostrar estado inicial
        mostrarEstadoJuego();

        // Verificar si hay Blackjack inicial
        if (verificarBlackjackInicial()) {
            return;
        }

        // Turno del jugador
        jugarTurnoJugador();

        // Si el jugador no se pasó, juega el dealer
        if (!jugador.sePaso()) {
            dealer.jugarTurnoAutomatico(baraja);
        }

        // Determinar el ganador
        determinarGanador();
    }

    /**
     * Ejecuta el turno del jugador humano.
     */
    private void jugarTurnoJugador() {
        System.out.println("\n--- Turno de " + jugador.getNombre() + " ---");

        boolean turnoTerminado = false;
        while (!turnoTerminado) {
            System.out.println("\nTu mano actual: ");
            ListaEnlazada<Carta> manoJugador = jugador.getMano();
            for (int i = 0; i < manoJugador.tamaño(); i++) {
                System.out.println("- " + manoJugador.obtenerElemento(i));
            }
            System.out.println("Puntaje actual: " + jugador.calcularPuntaje());

            // Mostrar opciones
            System.out.println("\n¿Qué deseas hacer?");
            System.out.println("1. Pedir carta");
            System.out.println("2. Plantarse");
            System.out.print("Selecciona una opción (1-2): ");

            int opcion;
            try {
                opcion = Integer.parseInt(scanner.nextLine().trim());
            } catch (NumberFormatException e) {
                opcion = 0; // Valor inválido
            }

            switch (opcion) {
                case 1: // Pedir carta
                    if (!baraja.estaVacia()) {
                        Carta nuevaCarta = baraja.robarCarta();
                        jugador.recibirCarta(nuevaCarta);
                        System.out.println("\nHas recibido: " + nuevaCarta);

                        // Verificar si se pasó de 21
                        if (jugador.sePaso()) {
                            System.out.println("¡Te has pasado de 21! Tu puntaje: " + jugador.calcularPuntaje());
                            turnoTerminado = true;
                        }
                    } else {
                        System.out.println("No quedan cartas en la baraja.");
                        turnoTerminado = true;
                    }
                    break;

                case 2: // Plantarse
                    jugador.plantarse();
                    System.out.println("Te plantas con " + jugador.calcularPuntaje() + " puntos.");
                    turnoTerminado = true;
                    break;

                default:
                    System.out.println("Opción no válida. Intenta de nuevo.");
            }
        }
    }

    /**
     * Verifica si hay un Blackjack inicial (21 puntos con 2 cartas).
     * 
     * @return true si se encontró un Blackjack inicial, false en caso contrario.
     */
    private boolean verificarBlackjackInicial() {
        boolean jugadorTieneBlackjack = jugador.tieneBlackjack();
        boolean dealerTieneBlackjack = dealer.tieneBlackjack();

        if (jugadorTieneBlackjack || dealerTieneBlackjack) {
            System.out.println("\n¡Blackjack inicial!");

            // Mostrar todas las cartas del jugador
            System.out.println("\nCartas de " + jugador.getNombre() + ":");
            ListaEnlazada<Carta> manoJugador = jugador.getMano();
            for (int i = 0; i < manoJugador.tamaño(); i++) {
                System.out.println("- " + manoJugador.obtenerElemento(i));
            }

            // Mostrar todas las cartas del dealer
            System.out.println("\nCartas del Dealer:");
            ListaEnlazada<Carta> manoDealer = dealer.getMano();
            for (int i = 0; i < manoDealer.tamaño(); i++) {
                System.out.println("- " + manoDealer.obtenerElemento(i));
            }

            // Determinar el resultado
            if (jugadorTieneBlackjack && dealerTieneBlackjack) {
                System.out.println("\n¡Empate! Ambos tienen Blackjack.");
            } else if (jugadorTieneBlackjack) {
                System.out.println("\n¡" + jugador.getNombre() + " gana con Blackjack!");
                jugador.incrementarPartidasGanadas();
            } else {
                System.out.println("\n¡El Dealer gana con Blackjack!");
                dealer.incrementarPartidasGanadas();
            }

            return true;
        }

        return false;
    }

    /**
     * Determina el ganador de la ronda actual.
     */
    private void determinarGanador() {
        System.out.println("\n====================================");
        System.out.println("         RESULTADO DE LA RONDA      ");
        System.out.println("====================================");

        int puntajeJugador = jugador.calcularPuntaje();
        int puntajeDealer = dealer.calcularPuntaje();

        System.out.println(jugador.getNombre() + ": " + puntajeJugador + " puntos");
        System.out.println("Dealer: " + puntajeDealer + " puntos");

        // Determinar el ganador
        if (jugador.sePaso()) {
            System.out.println("\n¡El Dealer gana! " + jugador.getNombre() + " se pasó de 21.");
            dealer.incrementarPartidasGanadas();
        } else if (dealer.sePaso()) {
            System.out.println("\n¡" + jugador.getNombre() + " gana! El Dealer se pasó de 21.");
            jugador.incrementarPartidasGanadas();
        } else if (puntajeJugador > puntajeDealer) {
            System.out.println("\n¡" + jugador.getNombre() + " gana con mayor puntaje!");
            jugador.incrementarPartidasGanadas();
        } else if (puntajeDealer > puntajeJugador) {
            System.out.println("\n¡El Dealer gana con mayor puntaje!");
            dealer.incrementarPartidasGanadas();
        } else {
            System.out.println("\n¡Empate! Ambos tienen el mismo puntaje.");
        }
    }

    /**
     * Muestra el estado actual del juego.
     */
    private void mostrarEstadoJuego() {
        System.out.println("\n--- Estado Actual del Juego ---");
        System.out.println(jugador);
        System.out.println(dealer);
        System.out.println("Cartas restantes en la baraja: " + baraja.cartasRestantes());
    }

    /**
     * Pregunta al jugador si desea jugar otra ronda.
     */
    private void preguntarNuevaRonda() {
        System.out.println("\n¿Deseas jugar otra ronda?");
        System.out.println("1. Sí");
        System.out.println("2. No");
        System.out.print("Selecciona una opción (1-2): ");

        int opcion;
        try {
            opcion = Integer.parseInt(scanner.nextLine().trim());
        } catch (NumberFormatException e) {
            opcion = 2; // Por defecto, salir
        }

        juegoEnCurso = (opcion == 1);
    }

    /**
     * Muestra las estadísticas finales del juego.
     */
    private void mostrarEstadisticasFinales() {
        System.out.println("\n====================================");
        System.out.println("         ESTADÍSTICAS FINALES       ");
        System.out.println("====================================");
        System.out.println(jugador.getNombre() + ": " + jugador.getPartidasGanadas() + " partidas ganadas");
        System.out.println("Dealer: " + dealer.getPartidasGanadas() + " partidas ganadas");

        // Determinar el ganador general
        if (jugador.getPartidasGanadas() > dealer.getPartidasGanadas()) {
            System.out.println("\n¡" + jugador.getNombre() + " es el ganador general!");
        } else if (dealer.getPartidasGanadas() > jugador.getPartidasGanadas()) {
            System.out.println("\n¡El Dealer es el ganador general!");
        } else {
            System.out.println("\n¡Empate en el marcador general!");
        }
    }
}