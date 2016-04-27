package cs3500.hw03;

import java.util.List;

/**
 * Controller interface for the Whist game.
 */
public interface IWhistController {
  /**
   * Starts the provided game with the provided number of players,
   * and return only after the game has ended.
   *
   * @param game a generic card game
   * @param numPlayers the intended number of players to start the game
   */
  <K> void startGame(CardGameModel<K> game, int numPlayers);

  /**
   * Starts the provided game with the provided number of players and the provided deck,
   * and returns only after the game has ended. Implemented for testing purposes
   *
   * @param game a generic card game
   * @param numPlayers the intended number of players to start the game
   * @param deck the deck passed into the game
   */
  <K> void startGame(CardGameModel<K> game, int numPlayers, List<K> deck);
}
