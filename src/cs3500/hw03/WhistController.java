package cs3500.hw03;

import java.io.IOException;
import java.util.InputMismatchException;
import java.util.List;
import java.util.Scanner;

/**
 * Controller for a Whist game that implements the IWhistController interface.
 */
public class WhistController implements IWhistController {

  private final Readable rd;
  private final Appendable ap;

  public WhistController(Readable rd, Appendable ap) {
    /**
     * Constructor for the WhistController class that takes in a Readable and Appendable object.
     * The Readable object will be used for handling user input and the Appendable object
     * will be used for transmitting output.
     *
     * @param rd Readable object
     * @param ap Appendable object
     */

    this.rd = rd;
    this.ap = ap;

  }
  @Override
  public <K> void startGame(CardGameModel<K> game, int numPlayers) {

    List<K> deck = game.getDeck();
    startGame(game, numPlayers, deck);

  }

  public <K> void startGame(CardGameModel<K> game, int numPlayers, List<K> deck) {

    // creates the game model for the provided number of players
    game.startPlay(numPlayers, deck);
    // while the game isn't over, run the method

    Scanner fromInput = new Scanner(rd);

    while (!game.isGameOver()) {
      // Displays the game state to the output stream
      try {
        ap.append(game.getGameState() + "\n");
      } catch (IOException e) {

      }

      try {

        Integer nextInput = fromInput.nextInt();
        game.play(game.getCurrentPlayer(), nextInput);

      } catch (InputMismatchException e1) {
        try {
          ap.append("Try again, that was an invalid input: Please put in an integer value "
              + "greater than 0 and within the index range of cards you have " + "\n");
          fromInput.next();
        } catch (IOException e) {

        }

      } catch (IllegalArgumentException e2) {
        try {
          ap.append(e2.getMessage() + "\n");
        } catch (IOException e) {

        }

      }
    }
    if (game.isGameOver()) {
      try {
        ap.append(game.getGameState());
      } catch (IOException e) {

      }
    }

    // if the game has been won, the method returns
    return;
  }

}
