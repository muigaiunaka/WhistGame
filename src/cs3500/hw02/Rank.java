package cs3500.hw02;

/**
 * Represents the ranks that can be used
 */
public enum Rank {
  // better to sort here and not in the method, A -> J, 10 -> 2 order
  TWO("2"), THREE("3"), FOUR("4"), FIVE("5"), SIX("6"), SEVEN("7"),
  EIGHT("8"), NINE("9"), TEN("10"), JACK("J"), QUEEN("Q"), KING("K"), ACE("A");

  private final String rankString;

  Rank(String rank) {
    this.rankString = rank;
  }

  /**
   * @return a string version of the rank
   */
  public String printRank() {
    return rankString;
  }
}
