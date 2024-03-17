package no.ntnu.idatg2003.oblig3.cardgame;
import java.util.Random;
import java.util.LinkedList;

public class DeckOfCards {
    private final char[] suits = {'S', 'H', 'D', 'C'};
    private final int[] faces = {1, 2, 3, 4, 5, 6, 7, 8, 9, 10, 11, 12, 13};
    private PlayingCard[] deck;
    private LinkedList<Integer> rolledIndexes = new LinkedList<Integer>();

    private Random random = new Random();

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
