package no.ntnu.idatg2003.oblig3.cardgame;
import java.util.Random;
import java.util.LinkedList;

/**
 * A class representing a deck of playing cards.
 * It has a method to deal a hand of playing cards.
 */

public class DeckOfCards {
    private final char[] suits = {'S', 'H', 'D', 'C'};
    private final int[] faces = {1, 2, 3, 4, 5, 6, 7, 8, 9, 10, 11, 12, 13};
    private PlayingCard[] deck;
    private LinkedList<Integer> rolledIndexes = new LinkedList<Integer>();

    private Random random = new Random();

    /**
     * Deck of cards constructor.
     * The deck is created with 52 playing cards in the order of suits and faces.
     * It creates a new playing card for each suit and face combination
     * and adds it to the deck. The deck is an array of PlayingCard objects.
     */
    public DeckOfCards() {
        int i = 0;
        deck = new PlayingCard[52];
        for(char suit : suits) {
            for(int face : faces) {
                deck[i] = new PlayingCard(suit, face);
                i++;
            }
        }
    }

    /**
     * Deals a hand of playing cards from the deck.
     * The hand is represented as an array of PlayingCard objects.
     * It uses the random class to generate a random number between 0 and 51
     * and uses this number to draw a card from the deck. It then adds the card
     * to the hand and remembers the index of the card in the deck to avoid
     * drawing the same card again.
     * @param n The number of cards to deal
     * @return The hand of playing cards
     */
    public PlayingCard[] dealHand(int n) {
        PlayingCard[] hand = new PlayingCard[n];
        for(int i = 0; i < n; i++) {
            int randomIndex = random.nextInt(deck.length);
            while(rolledIndexes.contains(randomIndex)) {
                randomIndex = random.nextInt(deck.length);
            }
            PlayingCard drawnCard = deck[randomIndex];
            hand[i] = drawnCard;
            // Recreate the deck withouth the drawn card
            rolledIndexes.add(randomIndex);
        }
        return hand;
    }
}
