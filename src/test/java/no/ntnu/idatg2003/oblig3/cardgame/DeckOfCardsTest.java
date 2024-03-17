package no.ntnu.idatg2003.oblig3.cardgame;

import org.junit.jupiter.api.BeforeEach;

import static org.junit.jupiter.api.Assertions.*;

class DeckOfCardsTest {

    @BeforeEach
    void setUp() {
        DeckOfCards deck = new DeckOfCards();
    }

    @org.junit.jupiter.api.Test
    void dealHand() {
        DeckOfCards deck = new DeckOfCards();
        PlayingCard[] hand = deck.dealHand(4);
        assertEquals(4, hand.length);
    }
}