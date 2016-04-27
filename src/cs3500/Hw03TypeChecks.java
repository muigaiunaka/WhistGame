package cs3500;
////
//// DO NOT MODIFY THIS FILE
////
//// You don't need to submit it, but you should make sure it compiles.
//// Further explanation appears below.
////

import cs3500.hw03.CardGameModel;
import cs3500.hw03.WhistModel;
import cs3500.hw03.IWhistController;
import cs3500.hw03.WhistController;

import java.io.Reader;
import java.io.StringReader;
import java.util.List;

/**
 * This class is provided to check that your code implements the expected API.
 * If your code compiles with an unmodified version of this class, then it
 * very likely will also compile with the tests that we use to evaluate
 * your code.
 */
public class Hw03TypeChecks {
  void checkSignatures() {
    checkNewModel(new WhistModel());
    checkNewController(new WhistModel());
  }

  <K> void checkNewController(CardGameModel<K> model) {
    String input = "4 3";
    Reader stringReader = new StringReader(input);
    StringBuffer out = new StringBuffer();
    IWhistController controller = new WhistController(stringReader, out);
    controller.startGame(model, 0);
  }

  <K> void checkNewModel(CardGameModel<K> model) {
    List<K> initialDeck = model.getDeck();
    model.startPlay(10, initialDeck);
    int player = model.getCurrentPlayer();
    model.play(player, -4);
    String result = model.getGameState();
    boolean done = model.isGameOver();
  }

  private Hw03TypeChecks() { throw new RuntimeException("Don't instantiate this: use it as a reference"); }
}
