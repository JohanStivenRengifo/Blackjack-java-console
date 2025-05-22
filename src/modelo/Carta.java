package modelo;

/**
 * Representa una carta de la baraja de Blackjack.
 * Cada carta tiene un valor y un palo.
 */
public class Carta {
    
    // Constantes para los palos
    public static final String CORAZONES = "Corazones";
    public static final String DIAMANTES = "Diamantes";
    public static final String TREBOLES = "Tréboles";
    public static final String PICAS = "Picas";
    
    // Constantes para las cartas especiales
    public static final String AS = "A";
    public static final String JOTA = "J";
    public static final String REINA = "Q";
    public static final String REY = "K";
    
    private final String valor;
    private final String palo;
    
    /**
     * Constructor de la carta.
     * 
     * @param valor El valor de la carta (A, 2-10, J, Q, K).
     * @param palo El palo de la carta (Corazones, Diamantes, Tréboles, Picas).
     */
    public Carta(String valor, String palo) {
        this.valor = valor;
        this.palo = palo;
    }
    
    /**
     * Obtiene el valor de la carta.
     * 
     * @return El valor de la carta.
     */
    public String getValor() {
        return valor;
    }
    
    /**
     * Obtiene el palo de la carta.
     * 
     * @return El palo de la carta.
     */
    public String getPalo() {
        return palo;
    }
    
    /**
     * Calcula el valor numérico de la carta según las reglas del Blackjack.
     * - As: puede valer 1 u 11 (se determina en la lógica del juego)
     * - Cartas numéricas (2-10): valen su número
     * - Figuras (J, Q, K): valen 10
     * 
     * @return El valor numérico de la carta.
     */
    public int getValorNumerico() {
        if (valor.equals(AS)) {
            return 1; // El valor 11 se maneja en la lógica del juego
        } else if (valor.equals(JOTA) || valor.equals(REINA) || valor.equals(REY)) {
            return 10;
        } else {
            return Integer.parseInt(valor);
        }
    }
    
    /**
     * Verifica si la carta es un As.
     * 
     * @return true si la carta es un As, false en caso contrario.
     */
    public boolean esAs() {
        return valor.equals(AS);
    }
    
    /**
     * Representación en cadena de la carta.
     * 
     * @return Una representación en cadena de la carta (valor + palo).
     */
    @Override
    public String toString() {
        return valor + " de " + palo;
    }
    
    /**
     * Representación corta de la carta.
     * 
     * @return Una representación corta de la carta (valor + inicial del palo).
     */
    public String toStringCorto() {
        char inicialPalo = palo.charAt(0);
        return valor + inicialPalo;
    }
}