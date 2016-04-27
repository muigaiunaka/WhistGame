package cs3500.hw03;

import cs3500.hw02.Card;
import cs3500.hw02.Rank;
import cs3500.hw02.Suit;
import cs3500.hw04.CardComparator;
import org.junit.Test;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import static junit.framework.Assert.assertTrue;
import static junit.framework.TestCase.assertEquals;

/**
 * Tests for {@link WhistModel} .
 */
public class WhistModelTest {

  CardGameModel game;

  @Test
  public void testPlay() {
    game = new WhistModel();
    List<Card> gameDeck = game.getDeck();
    Collections.sort(gameDeck);
    Collections.reverse(gameDeck);

    game.startPlay(4, gameDeck);
    game.play(0, 4);
    game.play(1, 4);
    game.play(2, 4);
    game.play(3, 4);
    game.play(0, 0);
    assertEquals(game.getCurrentPlayer(), 1);
  }

  @Test (expected = IllegalArgumentException.class)
  public void playedOutOfTurn() {
    game = new WhistModel();

    List<Card> gameDeck = game.getDeck();
    Collections.sort(gameDeck);
    Collections.reverse(gameDeck);

    game.startPlay(7, gameDeck);
    game.play(3, 7);
  }

  @Test (expected = IllegalStateException.class)
  public void didNotCallStartPlay() {
    game = new WhistModel();
    game.getGameState();
  }

  @Test (expected = IllegalArgumentException.class)
  public void wrongCardPlayed() {
    game = new WhistModel();

    List<Card> gameDeck = game.getDeck();
    Collections.sort(gameDeck);
    Collections.reverse(gameDeck);

    game.startPlay(13, gameDeck);
    game.play(0, 0);
    game.play(1, 0);
    game.play(2, 2);
  }


  @Test
  public void gameEndsWithOddNumberOfPlayers() {
    game = new WhistModel();

    List<Card> gameDeck = game.getDeck();
    Collections.sort(gameDeck);
    Collections.reverse(gameDeck);

    game.startPlay(3, gameDeck);
    game.getGameState();

    int i = 0;
    while ( ! game.isGameOver() ) {
      game.play(i, 0);
      i = (i+1) % 3;
    }
    assertTrue(game.isGameOver());

  }

  @Test (expected = IllegalStateException.class)
  public void getCurrentPlayerWhenGameIsOver() {
    game = new WhistModel();

    List<Card> gameDeck = game.getDeck();
    Collections.sort(gameDeck);
    Collections.reverse(gameDeck);

    game.startPlay(3, gameDeck);
    game.getGameState();

    int i = 0;
    while ( ! game.isGameOver() ) {
      game.play(i, 0);
      i = (i+1) % 3;
    }
    //assertTrue(game.isGameOver());
    game.getCurrentPlayer();

  }

  @Test
  public void specialMessageMultipleWinnersTest() {
    // This tests that the getGameState method returns the proper message for when multiple
    // players win the game. Had to rig game so that player 1 and 2 had equal scores
    game = new WhistModel();

    List<Card> gameDeck = game.getDeck();
    Collections.sort(gameDeck);
    Collections.reverse(gameDeck);

    game.startPlay(4, gameDeck);
    for (int i = 0; i < 4; i++) {
      game.play(i, 0);
    }
    for (int i = 0; i < 4; i++) {
      game.play(i, 0);
    }
    for (int i = 0; i < 4; i++) {
      game.play(i, 0);
    }
    for (int i = 0; i < 4; i++) {
      game.play(i, 0);
    }
    game.play(0, 1);
    for (int i = 1; i < 4; i++) {
      game.play(i, 0);
    }
    for (int i = 1; i < 4; i++) {
      game.play(i, 6);
    }
    game.play(0, 7);

    for (int i = 1; i < 4; i++) {
      game.play(i, 5);
    }
    game.play(0, 6);
    for (int i = 1; i < 4; i++) {
      game.play(i, 0);
    }
    game.play(0, 0);

    game.play(0, 3);
    game.play(1, 2);
    game.play(2, 3);
    game.play(3, 2);

    for (int i = 1; i < 4; i++) {
      game.play(i, 1);
    }
    game.play(0, 2);

    game.play(1, 1);
    game.play(2, 0);
    game.play(3, 0);
    game.play(0, 1);

    game.play(2, 0);
    game.play(3, 1);
    game.play(0, 1);
    game.play(1, 0);

    game.play(2, 0);
    game.play(3, 0);
    game.play(0, 0);
    game.play(1, 0);

    game.getGameState();

    assertEquals("Number of Players: 4\n" + "Player 1: \n" + "Player 2: \n" + "Player 3: \n"
            + "Player 4: \n" + "Player 1: 5 hands won\n" + "Player 2: 5 hands won\n"
            + "Player 3: 2 hands won\n" + "Player 4: 1 hands won\n" + "Game over. Player 1, "
        + "Player 2 won",
        game.getGameState() );

  }

  @Test (expected = IllegalArgumentException.class)
  public void invalidCardIdx() {
    game = new WhistModel();

    List<Card> gameDeck = game.getDeck();
    Collections.sort(gameDeck);
    Collections.reverse(gameDeck);

    game.startPlay(4, gameDeck);
    game.play(0, 4);
    game.play(1, 4);
    game.play(2, 1738);
  }

  @Test (expected = IllegalArgumentException.class)
  public void invalidCardIdx2() {
    game = new WhistModel();

    List<Card> gameDeck = game.getDeck();
    Collections.sort(gameDeck);
    Collections.reverse(gameDeck);

    game.startPlay(4, gameDeck);
    game.play(0, 0);
    game.play(1, -1738);
  }

  @Test (expected = IllegalStateException.class )
  public void testPlayWhenGameIsOver() {
    game = new WhistModel();

    List<Card> gameDeck = game.getDeck();
    Collections.sort(gameDeck);
    Collections.reverse(gameDeck);

    game.startPlay(13, gameDeck);
    game.getGameState();

    int i = 0;
    while ( ! game.isGameOver() ) {
      game.play(i, 0);
      i = (i+1) % 13;
    }

    game.play(0, 0);
  }

  @Test (expected = IllegalArgumentException.class)
  public void deckHasDuplicateCards() {
    game = new WhistModel();
    ArrayList<Card> badDeck = new ArrayList<>();
    for (int i = 0; i < 52; i++) {
      badDeck.add(new Card(Rank.KING, Suit.DIAMONDS));
    }
    game.startPlay(2, badDeck);

  }

}
