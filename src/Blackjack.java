import java.util.*;

public class Blackjack {
    private Stack<Card> deck; // Mazo principal
    private Stack<Card> discardPile; // Pila de descartes
    private List<Card> playerHand; // Mano del jugador
    private List<Card> dealerHand; // Mano del dealer
    private Scanner scanner;

    public Blackjack() {
        deck = new Stack<>();
        discardPile = new Stack<>();
        playerHand = new ArrayList<>();
        dealerHand = new ArrayList<>();
        scanner = new Scanner(System.in);
        initializeDeck();
    }

    // Inicializa y mezcla el mazo
    private void initializeDeck() {
        String[] suits = { "♠", "♥", "♦", "♣" };
        String[] ranks = { "A", "2", "3", "4", "5", "6", "7", "8", "9", "10", "J", "Q", "K" };

        for (String suit : suits) {
            for (String rank : ranks) {
                deck.push(new Card(suit, rank));
            }
        }
        Collections.shuffle(deck);
    }

    // Obtiene el valor de una mano
    private int getHandValue(List<Card> hand) {
        int value = 0;
        int aces = 0;

        for (Card card : hand) {
            if (card.getRank().equals("A")) {
                aces++;
                value += 11;
            } else if (card.getRank().matches("K|Q|J")) {
                value += 10;
            } else {
                value += Integer.parseInt(card.getRank());
            }
        }

        // Ajustar el valor de los Ases si es necesario
        while (value > 21 && aces > 0) {
            value -= 10;
            aces--;
        }

        return value;
    }

    // Repartir una carta desde el mazo
    private Card dealCard() {
        if (deck.isEmpty()) {
            System.out.println("Barajando las cartas descartadas...");
            while (!discardPile.isEmpty()) {
                deck.push(discardPile.pop());
            }
            Collections.shuffle(deck);
        }
        return deck.pop();
    }

    // Jugar un turno del jugador
    private boolean playerTurn() {
        while (true) {
            System.out.println("\nTu mano: " + playerHand + " (Valor: " + getHandValue(playerHand) + ")");
            System.out.println("Carta visible del dealer: " + dealerHand.get(0));
            System.out.print("¿Quieres otra carta? (s/n): ");

            String choice = scanner.nextLine().toLowerCase();
            if (choice.equals("s")) {
                Card card = dealCard();
                playerHand.add(card);
                System.out.println("Recibes: " + card);

                if (getHandValue(playerHand) > 21) {
                    System.out.println("¡Te has pasado de 21! Has perdido.");
                    return false;
                }
            } else if (choice.equals("n")) {
                return true;
            }
        }
    }

    // Jugar el turno del dealer
    private void dealerTurn() {
        System.out.println("\nTurno del dealer:");
        System.out.println("Mano del dealer: " + dealerHand);

        while (getHandValue(dealerHand) < 17) {
            Card card = dealCard();
            dealerHand.add(card);
            System.out.println("Dealer toma: " + card);
        }
    }

    // Determinar el ganador
    private void determineWinner() {
        int playerValue = getHandValue(playerHand);
        int dealerValue = getHandValue(dealerHand);

        System.out.println("\nTu mano final: " + playerHand + " (Valor: " + playerValue + ")");
        System.out.println("Mano final del dealer: " + dealerHand + " (Valor: " + dealerValue + ")");

        if (playerValue > 21) {
            System.out.println("Has perdido - te pasaste de 21");
        } else if (dealerValue > 21) {
            System.out.println("¡Has ganado! El dealer se pasó de 21");
        } else if (playerValue > dealerValue) {
            System.out.println("¡Has ganado!");
        } else if (dealerValue > playerValue) {
            System.out.println("El dealer gana");
        } else {
            System.out.println("Empate");
        }
    }

    // Descartar las cartas al final de la ronda
    private void discardHands() {
        while (!playerHand.isEmpty()) {
            discardPile.push(playerHand.remove(0));
        }
        while (!dealerHand.isEmpty()) {
            discardPile.push(dealerHand.remove(0));
        }
    }

    // Método principal para jugar
    public void play() {
        System.out.println("¡Bienvenido al Blackjack!");

        while (true) {
            // Repartir cartas iniciales
            playerHand.add(dealCard());
            dealerHand.add(dealCard());
            playerHand.add(dealCard());
            dealerHand.add(dealCard());

            // Turno del jugador
            if (playerTurn()) {
                // Turno del dealer si el jugador no se pasó
                dealerTurn();
                determineWinner();
            }

            // Descartar las manos
            discardHands();

            System.out.print("\n¿Quieres jugar otra ronda? (s/n): ");
            if (!scanner.nextLine().toLowerCase().equals("s")) {
                break;
            }
        }

        System.out.println("¡Gracias por jugar!");
        scanner.close();
    }

    // Método main
    public static void main(String[] args) {
        Blackjack game = new Blackjack();
        game.play();
    }
}

// Clase para representar una carta
class Card {
    private String suit;
    private String rank;

    public Card(String suit, String rank) {
        this.suit = suit;
        this.rank = rank;
    }

    public String getRank() {
        return rank;
    }

    @Override
    public String toString() {
        return rank + suit;
    }
}