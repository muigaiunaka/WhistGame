package cs3500.hw02;

import org.junit.Test;

import static junit.framework.Assert.assertTrue;
import static junit.framework.TestCase.assertEquals;

/**
 * Tests for {@link Card}
 */

public class CardTest {

  // examples of suits
  static Suit heart = Suit.HEARTS;
  static Suit spade = Suit.SPADES;
  static Suit diamond = Suit.DIAMONDS;
  static Suit club = Suit.CLUBS;
  // examples of ranks
  static Rank jack = Rank.JACK;
  static Rank ace = Rank.ACE;
  static Rank king = Rank.KING;
  static Rank four = Rank.FOUR;
  // examples of Cards
  static Card jackHeart = new Card(jack, heart);
  static Card aceSpade = new Card(ace, spade);
  static Card fourHeart = new Card(four , heart);
  static Card kingDiamond = new Card(king, diamond);
  static Card queenClub = new Card(Rank.QUEEN, club);

  @Test
  public void getRankAsStringValueWorks() {
    assertEquals("4", fourHeart.getRankAsString());
  }

  @Test
  public void getSuitAsStringValueWorks() {
    assertEquals("♦", kingDiamond.getSuitAsString());
  }

  @Test
  public void toStringWorks() {
    assertEquals("A♠", aceSpade.toString());
  }

  @Test
  public void lowerCardTest() {
    assertTrue(fourHeart.compareTo(jackHeart) < 0);
  }

  @Test
  public void higherCardTest() {
    assertTrue(queenClub.compareTo(new Card(jack, club)) > 0);
  }

  @Test
  public void sameCardTest() {
    assertTrue(aceSpade.equals(new Card(Rank.ACE, Suit.SPADES)));
  }

}
