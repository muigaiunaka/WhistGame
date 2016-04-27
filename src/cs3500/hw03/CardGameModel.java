package cs3500.hw03;

import cs3500.hw02.GenericCardGameModel;

/**
 * Represents the abstract state of a Card Game
 * @param <K>
 */
public interface CardGameModel<K> extends GenericCardGameModel<K> {

  /**
   * Plays the card at index cardIdx in the set of cards for player number playerNo.
   * It is assumed that both player numbers and card indices begin with 0.
   * It is further assumed that players’ hands are sorted.
   *
   * @param playerNo number representing the player's position in the game
   * @param cardIdx the index number within the list of cards
   */
  void play(int playerNo, int cardIdx);

  /**
   * @return the player whose turn it is to play.
   */
  int getCurrentPlayer();

  /**
   * Determines whether the game is over.
   *
   * @return true if the game is over, false otherwise
   */
  boolean isGameOver();

}
