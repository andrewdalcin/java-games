package us.game.war;

import us.game.deck.CardFactory;
import us.game.deck.Deck;

public class WarGame {
    
    public static void playRound(Deck deck1, Deck deck2) {
        
    }
    
    /**
     * This function when called will play an entire game of War.
     * playRound will get called until either play is completely out of cards.
     * 
     * @param player1 Deck of cards representing player 1
     * @param player2 Deck of cards representing player 2
     */
    public static void playGame(Deck player1, Deck player2) {
        while( player1.getNumCards() > 0 && player2.getNumCards() > 0 )
            playRound(player1, player2);
        
        System.out.println("Player1: " + player1.getNumCards());
        System.out.println("Player2: " + player2.getNumCards());
        if( player1.getNumCards() > 0 )
            System.out.println("Player 1 Wins!");
        else if( player2.getNumCards() > 0 )
            System.out.println("Player 2 Wins!");
    }
    
    public static void main(String[] args) {
        // Get a new CardFactory instance
        CardFactory factory = new CardFactory();
        
        // Get a full deck, set as player 1
        Deck player1 = factory.createFullDeck();        
        // Make a new empty deck for player 2
        Deck player2 = new Deck();
        // Shuffle the player 1 deck
        player1.shuffle();

        // deal half of player 1's shuffled deck to player 2
        int deckSize = player1.getDrawPileSize() / 2;
        for(int i=0; i<deckSize; ++i)
            player2.addToDiscard(player1.draw());

        // shuffle both decks
        player1.shuffle();
        player2.shuffle();

        // play a single round of War
        playRound(player1, player2);
        
        // play an entire game of War
//        playGame(player1, player2);
    }
}
