package cs3500.hw03;

/**
 * Represents a user providing the program with  an input
 */
public class InputInteraction implements Interaction {
  String input;

  public InputInteraction(String input) {
    this.input = input;
  }

  public void apply(StringBuilder in, StringBuilder out) {
    in.append(input);
  }
}
