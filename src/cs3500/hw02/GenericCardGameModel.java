package cs3500.hw02;

import java.util.List;



/**
 * Represents the Generic state of a Card Game
 */
public interface GenericCardGameModel<K> {
  /**
   * Inputs a deck of cards, as an arrayList of cards, into the game
   *
   * @return an entire deck of cards, as a Generic List
   */
  List<K> getDeck();

  /**
   * Starts the game with the input number of players and the input deck of cards.
   * Distributes these cards in the specified order among the players in round-robin fashion.
   * For example, card dealt to player 1, card dealt to player 2 then card dealt to player 3, etc.
   *
   * @param numPlayers the number of players in the game input as an int value
   * @param deck a deck of cards input as a List<K>
   *
   * @throws IllegalArgumentException if numPlayers is not greater than 1
   * @throws IllegalArgumentException if deck, the one passed to the method, is invalid
   */
  void startPlay(int numPlayers, List<K> deck);

  /**
   * Returns a string that contains the entire state of the game,
   * with the following information on its own line as follows:
   *
   * Number of Players: N (printed as normal decimal number)
   * Player 1: cards in sorted order (as comma-separated list)
   * Player 2: cards in sorted order (as comma-separated list)
   * ...
   * Player N: cards in sorted order (as comma-separated list)
   *
   * @return a string containing the entire state of the game
   */
  String getGameState();


  /**
   * Shuffles the deck of cards into an arraylist of the cards in random order
   */
  List<K> shuffle(List<K> deck);
}
