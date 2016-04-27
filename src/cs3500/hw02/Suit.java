package cs3500.hw02;

/**
 * Represents the suits that can be used
 */
public enum Suit {

  SPADES("♠"), HEARTS("♥"), DIAMONDS("♦"), CLUBS("♣");

  private final String suitString;

  Suit(String suit) {
    this.suitString = suit;
  }

  /**
   * @return a string version of the suit
   */
  public String printSuit() {
    return suitString;
  }
}
