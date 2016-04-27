package cs3500.hw02;

import java.util.ArrayList;
import java.util.Collections;

public class Player {

  public int score;
  // hand is never null
  public ArrayList<Card> hand; // private final

  public Player() {
    hand = new ArrayList<>();
    this.score = 0;
  }

  /**
   *
   * @return the number of cards in the hand
   */
  public int cardsInHand() {
    return hand.size();
  }

  /**
   * Adds a card into a hand, represented as a list of cards
   *
   * @param card a valid card
   */
  public void addCard(Card card) {
    this.hand.add(card);
  }

  /**
   * Sorts the cards in this player's hand. <br> <br>
   *
   * Sorted order is defined as: alphabetical order of suits
   * (i.e., clubs, diamonds, hearts, spades) <br>
   * Within each suit, cards should be ordered in descending order
   * by number with aces being highest
   */
  public void sortHand() {
    Collections.sort(this.hand);
    ArrayList<Card> reverseSorted = this.hand;
    Collections.reverse(reverseSorted);
  }
}
