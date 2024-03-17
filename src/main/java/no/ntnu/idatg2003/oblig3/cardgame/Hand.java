package no.ntnu.idatg2003.oblig3.cardgame;

/**
 * A class representing a hand of playing cards.
 * It takes in an array of PlayingCard objects and has methods to get the value of the hand and the cards in the hand.
 */
public class Hand {
    /**
     * The hand of playing cards.
     */
    private PlayingCard[] hand;

    public Hand(PlayingCard[] hand) {
        this.hand = hand;
    }

    public PlayingCard[] getHand() {
        return hand;
    }

    public void setHand(PlayingCard[] hand) {
        this.hand = hand;
    }

    public int getHandValue() {
            int handValue = 0;
            for (PlayingCard card : hand) {
                handValue += card.getFace();
            }
            return handValue;
        }

    public String getCards() {
        StringBuilder cards = new StringBuilder();
        for (PlayingCard card : hand) {
            cards.append(card.getAsString()).append(" ");
        }
        return cards.toString();
    }
}


