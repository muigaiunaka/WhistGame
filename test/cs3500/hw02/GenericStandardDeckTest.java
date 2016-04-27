package cs3500.hw02;

import org.junit.Test;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import static junit.framework.Assert.assertFalse;
import static junit.framework.TestCase.assertEquals;

/**
 * Tests for {@link GenericStandardDeckGame}
 */
public class GenericStandardDeckTest {
  GenericCardGameModel game1 = new GenericStandardDeckGame();

  @Test
  public void getGameStateTest() {
    List<Card> gameDeck = game1.getDeck();
    Collections.sort(gameDeck);
    Collections.reverse(gameDeck);

    game1.startPlay(4, gameDeck);
    game1.getGameState();
    String[] lines = game1.getGameState().split("\n");
    int numberOfLines = lines.length;

    assertEquals(5, numberOfLines);

  }

  @Test (expected = IllegalArgumentException.class)
  public void testDeckSizeTooSmall() {
    List<Card> gameDeck = game1.getDeck();
    Collections.sort(gameDeck);
    Collections.reverse(gameDeck);

    List<Card> c1 = gameDeck;
    for (int i = 0; i < 10; i++) {
      c1.remove(i);
    }
    game1.startPlay(4, c1);

  }

  @Test (expected = IllegalArgumentException.class)
  public void testInvalidNumberOfPlayers() {
    game1.startPlay(1, game1.getDeck());
  }

  @Test (expected = IllegalStateException.class)
  public void testHasStartPlayBeenCalled() {
    game1.getGameState();
  }

  @Test (expected = IllegalArgumentException.class)
  public void deckHasDuplicateCards() {
    ArrayList<Card> badDeck = new ArrayList<>();
    for (int i = 0; i < 52; i++) {
      badDeck.add(new Card(Rank.KING, Suit.DIAMONDS));
    }
    game1.startPlay(2, badDeck);
  }

  @Test
  public void testDeckSize() {
    assertEquals(52, game1.getDeck().size());
  }

  @Test
  public void testShuffle() {
    List<Card> c2 = game1.getDeck();
    List<Card> shuffled = game1.shuffle(c2);
    assertFalse( c2.equals(shuffled) );

  }
}
