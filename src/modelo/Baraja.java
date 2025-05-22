package modelo;

import estructuras.ListaEnlazada;
import java.util.Random;

/**
 * Representa la baraja de cartas para el juego de Blackjack.
 * Utiliza una Lista Enlazada para almacenar las cartas.
 */
public class Baraja {

    private ListaEnlazada<Carta> cartas;
    private Random random;

    /**
     * Constructor de la baraja.
     * Inicializa una baraja estándar de 52 cartas.
     */
    public Baraja() {
        this.cartas = new ListaEnlazada<>();
        this.random = new Random();
        inicializarBaraja();
    }

    /**
     * Inicializa la baraja con las 52 cartas estándar.
     */
    private void inicializarBaraja() {
        String[] palos = { Carta.CORAZONES, Carta.DIAMANTES, Carta.TREBOLES, Carta.PICAS };
        String[] valores = { Carta.AS, "2", "3", "4", "5", "6", "7", "8", "9", "10",
                Carta.JOTA, Carta.REINA, Carta.REY };

        for (String palo : palos) {
            for (String valor : valores) {
                cartas.agregar(new Carta(valor, palo));
            }
        }
    }

    /**
     * Mezcla las cartas de la baraja de manera aleatoria.
     * Implementa el algoritmo de Fisher-Yates para mezclar.
     */
    public void mezclar() {
        // Convertimos la lista enlazada a un arreglo para facilitar la mezcla
        Carta[] arregloCartas = new Carta[cartas.tamaño()];
        int indice = 0;

        // Extraemos todas las cartas a un arreglo
        while (!cartas.estaVacia()) {
            arregloCartas[indice++] = cartas.eliminarPrimero();
        }

        // Algoritmo de Fisher-Yates para mezclar
        for (int i = arregloCartas.length - 1; i > 0; i--) {
            int j = random.nextInt(i + 1);
            // Intercambiar arregloCartas[i] y arregloCartas[j]
            Carta temp = arregloCartas[i];
            arregloCartas[i] = arregloCartas[j];
            arregloCartas[j] = temp;
        }

        // Volvemos a agregar las cartas mezcladas a la lista enlazada
        for (Carta carta : arregloCartas) {
            cartas.agregar(carta);
        }
    }

    /**
     * Roba la carta superior de la baraja.
     * 
     * @return La carta robada o null si la baraja está vacía.
     */
    public Carta robarCarta() {
        return cartas.eliminarPrimero();
    }

    /**
     * Verifica si la baraja está vacía.
     * 
     * @return true si la baraja está vacía, false en caso contrario.
     */
    public boolean estaVacia() {
        return cartas.estaVacia();
    }

    /**
     * Devuelve el número de cartas restantes en la baraja.
     * 
     * @return El número de cartas en la baraja.
     */
    public int cartasRestantes() {
        return cartas.tamaño();
    }

    /**
     * Muestra el estado actual de la baraja.
     * 
     * @return Una representación en cadena de la baraja.
     */
    @Override
    public String toString() {
        return "Baraja [" + cartasRestantes() + " cartas]\n" + cartas.toString();
    }
}