package cs3500.hw02;

import cs3500.hw02.Card;
import cs3500.hw02.Player;
import cs3500.hw02.Rank;
import cs3500.hw02.Suit;
import org.junit.Test;

import static org.junit.Assert.*;

/**
 * Tests for {@link Player}
 */
public class PlayerTest {
  Player testPlayer;


  @Test
  public void testSortedHand() {
    testPlayer = new Player();
    testPlayer.addCard(new Card(Rank.EIGHT, Suit.SPADES));
    testPlayer.addCard(new Card(Rank.JACK, Suit.CLUBS));
    testPlayer.addCard(new Card(Rank.QUEEN, Suit.HEARTS));
    testPlayer.addCard(new Card(Rank.SEVEN, Suit.DIAMONDS));
    testPlayer.addCard(new Card(Rank.SIX, Suit.DIAMONDS));
    testPlayer.addCard(new Card(Rank.FIVE, Suit.DIAMONDS));
    testPlayer.addCard(new Card(Rank.FOUR, Suit.DIAMONDS));
    testPlayer.addCard(new Card(Rank.EIGHT, Suit.DIAMONDS));
    Player sortedPlayerHand = testPlayer;
    sortedPlayerHand.sortHand();
    assertNotEquals( testPlayer.hand, sortedPlayerHand );
  }

  @Test
  public void testScore() {
    testPlayer = new Player();
    assertEquals(0, testPlayer.score );
  }

  @Test
  public void testHandSize() {
    testPlayer = new Player();
    testPlayer.addCard(new Card(Rank.EIGHT, Suit.SPADES));
    testPlayer.addCard(new Card(Rank.JACK, Suit.CLUBS));
    testPlayer.addCard(new Card(Rank.QUEEN, Suit.HEARTS));
    testPlayer.addCard(new Card(Rank.SEVEN, Suit.DIAMONDS));
    assertEquals(4, testPlayer.cardsInHand());
  }



}
