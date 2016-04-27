package cs3500;////
////
//// DO NOT MODIFY THIS FILE
////
//// You don't need to submit it, but you should make sure it compiles.
//// Further explanation appears below.
////

import cs3500.hw03.CardGameModel;
import cs3500.hw03.IWhistController;
import cs3500.hw03.WhistController;
import cs3500.hw04.WhistModelCreator;

import java.io.Reader;
import java.io.StringReader;

/**
 * This class is provided to check that your code implements the expected API.
 * If your code compiles with an unmodified version of this class, then it
 * very likely will also compile with the tests that we use to evaluate
 * your code.
 */
public class Hw04TypeChecks {
    void checkSignatures() {
        String input = "4 3";
        Reader stringReader = new StringReader(input);
        StringBuffer out = new StringBuffer();

        IWhistController controller = new WhistController(stringReader, out);
        CardGameModel<?> model = WhistModelCreator.create(WhistModelCreator.ModelType.TRUMP);
        controller.startGame(model, 0);
    }

    private Hw04TypeChecks() { throw new RuntimeException("Don't instantiate this: use it as a reference"); }
}
