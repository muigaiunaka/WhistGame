package cs3500.hw03;

/**
 * Represents the printing of a sequence of lines to output
 */
public class PrintInteraction implements Interaction {
  String[] lines;

  public PrintInteraction(String... lines) {
    this.lines = lines;
  }

  public void apply(StringBuilder in, StringBuilder out) {
    for (String line : lines) {
      out.append(line).append("\n");
    }
  }

}
