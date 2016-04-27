package cs3500.hw04;

import cs3500.hw02.Card;

import java.util.Comparator;

/**
 * Custom comparator for comparing Card objects for sorting. The sorting is performed first by
 * card name and then by card suit.
 */
public class CardComparator implements Comparator<Card> {
    /**
     * Allows for sorting with Collections.sort(List<Card>).
     * Compares cards by suit and rank
     *
     * @param card1 is a card to compare with the second provided card (card2)
     * @param card2 is a card to compare with the first provided card (card1)
     * @return a comparison value. If value > 0, card2 is higher ranked,
     * if value < 0, card1 is higher ranked.
     */
    @Override
    public int compare(Card card1, Card card2) {

      int cardComp = card2.getSuit().ordinal() - card1.getSuit().ordinal();

      if (cardComp == 0) {
        return card2.getRank().ordinal() - card1.getRank().ordinal();
      } else {
        return cardComp;
      }
    }

}
