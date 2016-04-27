package cs3500.hw04;

import cs3500.hw02.Card;
import cs3500.hw03.CardGameModel;
import org.junit.Test;

import java.util.Collections;
import java.util.List;

import static junit.framework.Assert.assertTrue;
import static junit.framework.TestCase.assertEquals;

/**
 * Tests forfor {@link WhistModelBenCarson}
 */
public class WhistModelBenCarsonTest {
  CardGameModel trumpGame = WhistModelCreator.create(WhistModelCreator.ModelType.TRUMP);

  @Test
  public void getGameStateShowsTrumpSuit() {

    List<Card> trumpCards = trumpGame.getDeck();
    Collections.sort(trumpCards, new CardComparator());
    trumpGame.startPlay(3, trumpCards);

    String result;
    StringBuilder printedString = new StringBuilder();
    printedString.append("Number of Players: 3\n");
    printedString.append("Player 1: A♣, J♣, 8♣, 5♣, 2♣, Q♦, 9♦, 6♦, 3♦, "
        + "K♥, 10♥, 7♥, 4♥, A♠, J♠, 8♠, 5♠, 2♠\n");
    printedString.append("Player 2: K♣, 10♣, 7♣, 4♣, A♦, J♦, 8♦, 5♦, 2♦, Q♥, 9♥, 6♥, 3♥, "
        + "K♠, 10♠, 7♠, 4♠\n");
    printedString.append("Player 3: Q♣, 9♣, 6♣, 3♣, K♦, 10♦, 7♦, 4♦, A♥, "
        + "J♥, 8♥, 5♥, 2♥, Q♠, 9♠, 6♠, 3♠\n");
    printedString.append("Player 1: 0 hands won\n" + "Player 2: 0 hands won\n" +
        "Player 3: 0 hands won\n");
    printedString.append("Turn: Player 1\n" + "Trump Suit: ♣");

    result = printedString.toString();
    assertTrue(trumpGame.getGameState().equals(result));
  }

  @Test
  public void testTrumpWorks() {
    List<Card> gameDeck = trumpGame.getDeck();
    Collections.sort(gameDeck, new CardComparator());

    trumpGame.startPlay(8, gameDeck);
    for (int i = 0; i < 8; i++) {
      trumpGame.play(i, 2);
    }
    trumpGame.play(0, 2);
    trumpGame.play(1, 2);
    trumpGame.play(2, 1);
    trumpGame.play(3, 0);
    trumpGame.play(4, 1);
    trumpGame.play(5, 1);
    trumpGame.play(6, 1);
    trumpGame.play(7, 1);

    // the winner of this previous round should be the current player
    assertEquals(trumpGame.getCurrentPlayer(), 3);
  }

  @Test (expected = IllegalArgumentException.class)
  public void testThrowsException1() {
    List<Card> gameDeck = trumpGame.getDeck();
    Collections.sort(gameDeck, new CardComparator());

    trumpGame.startPlay(8, gameDeck);
    for (int i = 0; i < 8; i++) {
      trumpGame.play(i, 2);
    }
    trumpGame.play(0, 2);
    trumpGame.play(1, 2);
    trumpGame.play(2, 1);
    trumpGame.play(3, 0);
    trumpGame.play(4, 1);
    // player has a card of the current suit in their hand but attempts to play the trump card
    trumpGame.play(5, 0);
  }

  @Test
  public void testWinner() {
    List<Card> gameDeck = trumpGame.getDeck();
    Collections.sort(gameDeck, new CardComparator());
    Collections.reverse(gameDeck);

    trumpGame.startPlay(13, gameDeck);

    for (int i = 0; i < 13; i++ ) {
      trumpGame.play(i, 0);
    }
    trumpGame.play(12, 0);
    for (int i = 0; i < 12; i++ ) {
      trumpGame.play(i, 0);
    }
    trumpGame.play(12, 0);
    for (int i = 0; i < 12; i++ ) {
      trumpGame.play(i, 0);
    }
    trumpGame.play(12, 0);
    for (int i = 0; i < 12; i++ ) {
      trumpGame.play(i, 0);
    }

    assertTrue(trumpGame.getGameState().equals("Number of Players: 13\n" + "Player 1: \n"
        + "Player 2: \n" + "Player 3: \n" + "Player 4: \n" + "Player 5: \n" + "Player 6: \n"
        + "Player 7: \n" + "Player 8: \n" + "Player 9: \n" + "Player 10: \n" + "Player 11: \n"
        + "Player 12: \n" + "Player 13: \n" + "Player 1: 0 hands won\n" + "Player 2: 0 hands won\n"
        + "Player 3: 0 hands won\n" + "Player 4: 0 hands won\n" + "Player 5: 0 hands won\n"
        + "Player 6: 0 hands won\n" + "Player 7: 0 hands won\n" + "Player 8: 0 hands won\n"
        + "Player 9: 0 hands won\n" + "Player 10: 0 hands won\n" + "Player 11: 0 hands won\n"
        + "Player 12: 0 hands won\n" + "Player 13: 4 hands won\n" + "Game over. Player 13 won"));
  }

}
