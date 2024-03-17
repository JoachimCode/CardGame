package no.ntnu.idatg2003.oblig3.cardgame;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import javafx.scene.control.Label;

import java.util.Arrays;

/**
 * A simple card game application
 */
public class CardGame extends Application {
    Button dealHandButton;
    Button checkHandButton;
    Hand currentHand;
    @Override
    public void start(Stage primaryStage) throws Exception {
        primaryStage.setTitle("Hello World!");
        GridPane rootNode = new GridPane();
        Scene scene = new Scene(rootNode, 800, 500);
        primaryStage.setScene(scene);

        rootNode.setGridLinesVisible(false);

        // Display Cards
        VBox handBox = new VBox();
        GridPane handGrid = new GridPane();
        handGrid.setHgap(30);
        handBox.getChildren().add(handGrid);
        handBox.setStyle("-fx-padding: 10; -fx-background-color: #4c8ec4;");
        handBox.setSpacing(10);
        handBox.setPrefWidth(500);
        handBox.setPrefHeight(200);
        rootNode.add(handBox, 0, 0);
        handGrid.setGridLinesVisible(false);

        // Display Results
        GridPane result = new GridPane();
        result.setStyle("-fx-padding: 10; -fx-background-color: #547894; -fx-translate-x: 100; -fx-translate-y: 60; -fx-border-color: black; -fx-border-width: 1px");
        result.setVgap(20);

        rootNode.add(result, 0, 1);

        // Display Buttons
        GridPane buttonGrid = new GridPane();
        dealHandButton = new Button("Deal Hand");
        checkHandButton = new Button("Check Hand");
        dealHandButton.setStyle("-fx-translate-x: 50; fx-padding:20");
        checkHandButton.setStyle("-fx-translate-x: 90");
        buttonGrid.add(dealHandButton, 0, 0);
        buttonGrid.add(checkHandButton, 1, 0);
        rootNode.add(buttonGrid, 1, 0);

        dealHandButton.setOnAction(e -> {
            handGrid.getChildren().clear();
            DeckOfCards deck = new DeckOfCards();
            PlayingCard[] hand = deck.dealHand(5);
            displayHand(handGrid, hand);
            currentHand = new Hand(hand);
        });

        checkHandButton.setOnAction(e -> {
            if(currentHand == null) {
                throw new IllegalArgumentException("No hand to check");
            }
            else {
                result.getChildren().clear();
                displayResult(result, currentHand);
            }
        });

        primaryStage.show();

    }


    @Override
    public void stop() {
        System.exit(0);
    }

    public void startFX(String[] args) {
        launch(args);
    }

    /**
     * Display the hand of cards
     * @param handGrid The grid to display the hand. It is nested into the rootNode
     * @param hand The hand of cards to display
     */
    public void displayHand(GridPane handGrid, PlayingCard[] hand) {
        for(int i = 0; i < hand.length; i++) {
            Label cardLabel = new Label(hand[i].getAsString());
            cardLabel.setStyle("-fx-border-color: black; -fx-border-width: 1px; -fx-padding: 10px 23px; -fx-end-margin: 12px; -fx-translate-y: 80");
            handGrid.add(cardLabel, i, 0);
        }
    }

    /**
     * Display the result of the hand
     * @param result The grid to display the result. It is nested into the rootNode
     * @param hand The hand to display the result of
     */
    public void displayResult(GridPane result, Hand hand) {
        Label resultLabel = new Label("Hand value: " + hand.getHandValue());
        Hand heartHand = new Hand(Arrays.stream(hand.getHand()).filter(card -> card.getSuit() == 'H').toArray(PlayingCard[]::new));
        Label Hearts;
        if(heartHand.getHand().length > 0) {
            Hearts = new Label("Hearts: " + heartHand.getCards());
        }
        else
        {
            Hearts = new Label("Hearts: None");
        }

        Label isFlush = new Label("Flush: " + Arrays.stream(hand.getHand()).allMatch(card -> card.getSuit() == hand.getHand()[0].getSuit()));
        Label isQueenOfSpades = new Label("Queen of Spades: " + Arrays.stream(hand.getHand()).anyMatch(card -> card.getAsString().equals("S12")));
        result.add(resultLabel, 0, 0);
        result.add(Hearts, 0, 1);
        result.add(isFlush, 0, 2);
        result.add(isQueenOfSpades, 0, 3);
    }

}
