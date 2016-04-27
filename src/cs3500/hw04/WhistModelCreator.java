package cs3500.hw04;

import cs3500.hw03.CardGameModel;
import cs3500.hw03.WhistModel;

/**
 * Represents the class for a function object that creates a variation of the Whist Model based on
 * the input model type
 */
public class WhistModelCreator {


  public enum ModelType{
    /**Invariant: only two options, any other input model type is invalid because finite set of
     * Model types includes only TRUMP and NOTRUMP */
    TRUMP, NOTRUMP,
  }

  /**
   * Creates a Whist Model with or without a trump component in the game
   *
   * @param type determines the type of whist game, either with trump functionality or without it
   * @return a Card Game based off of the input game model type
   */
  public static CardGameModel create(ModelType type) {
    CardGameModel game;

    switch(type) {
      case TRUMP: game = new WhistModelBenCarson();
        break;
      case NOTRUMP: game = new WhistModel();
        break;
      default: throw new IllegalArgumentException("Invalid type");
    }

    return game;
  }

}
