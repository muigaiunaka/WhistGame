package cs3500.hw03;

/**
 * An interaction with the user consists of some input to send the program
 * and some output to expect.  We represent it as an object that takes in two
 * StringBuilders and produces the intended effects on them
 */
public interface Interaction {
    void apply(StringBuilder in, StringBuilder out);
}
