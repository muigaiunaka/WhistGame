package cs3500.hw02;

/**
 * Represents rank and suit for a card object
 */
public class Card implements Comparable<Card> {

  private final Rank rank;
  private final Suit suit;

  /**
   * Constructs a {@code Card} object
   * @param rank the rank of the card, numbers 2 through 10, ace, jack, queen, king
   * @param suit the suit of the card, either heart, spade, diamond or club
   */
  public Card(Rank rank, Suit suit) {
    this.rank = rank;
    this.suit = suit;
  }

  /**
   * Gets the rank of this card
   *
   * @return rank of this card
   */
  public Rank getRank()  {
    return rank;
  }

  /**
   * Gets the suit of this card
   *
   * @return the suit of this card
   */
  public Suit getSuit()  {
    return suit;
  }

  /**
   * Takes the rank of a Card and returns the numeric or letter
   * representation of the rank as a String.
   *
   * @return rank of this card as a String
   */
  public String getRankAsString() {
    return rank.printRank();
  }

  /**
   * Takes the suit of a Card and returns the string representation of the suit
   *
   * @return the suit of this card as a String
   */
  public String getSuitAsString() {
    return suit.printSuit();
  }

  @Override
  public String toString() {
    String card = "";
    card += rank.printRank() + suit.printSuit();
    return card;
  }

  @Override
  public int compareTo(Card other) {
    int compareCard = this.suit.compareTo(other.suit);
    if (compareCard == 0) {
      compareCard = this.rank.compareTo(other.rank); // compares cards of same suit
    }
    return compareCard;
  }

  @Override
  public boolean equals(Object o) {
    if (this == o)
      return true;
    if (o == null || getClass() != o.getClass())
      return false;

    Card card = (Card) o;

    if (rank != card.rank)
      return false;
    return suit == card.suit;

  }

  @Override
  public int hashCode() {
    int result = rank != null ? rank.hashCode() : 0;
    result = 31 * result + (suit != null ? suit.hashCode() : 0);
    return result;
  }

}
