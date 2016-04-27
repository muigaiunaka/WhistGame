package cs3500.hw04;

import cs3500.hw02.Card;
import cs3500.hw02.Rank;
import cs3500.hw02.Suit;
import org.junit.Test;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

import static junit.framework.Assert.assertTrue;

/**
 * Tests for {@link CardComparator}
 */
public class CardComparatorTest {

  private <S, T extends Comparator<S>> List<S> testComparatorHelper(List<S> list, T comparator) {
    List<S> temp = new ArrayList<S>();
    for (S item : list) {
      temp.add(item);
    }
    Collections.sort(temp, comparator);
    return temp;
  }

  @Test
  public void testComparator() {
    cs3500.hw03.CardGameModel model = new WhistModelBenCarson();

    List<?> deck = testComparatorHelper(model.getDeck(), new CardComparator());
    model.startPlay(3, deck);

    String sortedDeck = "[A♣, K♣, Q♣, J♣, 10♣, 9♣, 8♣, 7♣, 6♣, 5♣, 4♣, 3♣, 2♣, A♦, K♦, Q♦, J♦,"
        + " 10♦, 9♦, 8♦, 7♦, 6♦, 5♦, 4♦, 3♦, 2♦, A♥, K♥, Q♥, J♥, 10♥, 9♥, 8♥, 7♥, 6♥, 5♥, 4♥, 3♥,"
        + " 2♥, A♠, K♠, Q♠, J♠, 10♠, 9♠, 8♠, 7♠, 6♠, 5♠, 4♠, 3♠, 2♠]";
    assertTrue(sortedDeck.equals(deck.toString()));
  }


  @Test
  public void testComparator2() {
    Card c1 = new Card(Rank.JACK, Suit.HEARTS);
    Card c2 = new Card(Rank.TEN, Suit.HEARTS);

    CardComparator comparator = new CardComparator();
    int comparison = comparator.compare(c1,c2);

    assertTrue(comparison < 0);
  }

  @Test
  public void testComparator3() {
    Card c1 = new Card(Rank.JACK, Suit.SPADES);
    Card c2 = new Card(Rank.TEN, Suit.CLUBS);

    CardComparator comparator = new CardComparator();
    int comparison = comparator.compare(c1,c2);

    assertTrue(comparison > 0);
  }

  @Test
  public void testComparator4() {
    Card c1 = new Card(Rank.ACE, Suit.SPADES);
    Card c2 = new Card(Rank.ACE, Suit.SPADES);

    CardComparator comparator = new CardComparator();
    int comparison = comparator.compare(c1,c2);

    assertTrue(comparison == 0);
  }

}
