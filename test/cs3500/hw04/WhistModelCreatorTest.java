package cs3500.hw04;

import cs3500.hw03.CardGameModel;
import cs3500.hw03.WhistModel;
import org.junit.Test;

import static junit.framework.Assert.assertTrue;

/**
 * Tests for {@link WhistModelCreator}
 */
public class WhistModelCreatorTest {

  @Test
  public void whistCreated() {

    CardGameModel noTrumpGame = WhistModelCreator.create(WhistModelCreator.ModelType.NOTRUMP);

    assertTrue(noTrumpGame.getClass().equals(( new WhistModel() ).getClass() ) );
  }

  @Test
  public void trumpWhistCreated() {
    CardGameModel trumpGame = WhistModelCreator.create(WhistModelCreator.ModelType.TRUMP);

    assertTrue(trumpGame.getClass().equals(( new WhistModelBenCarson() ).getClass() ) );
  }
}
