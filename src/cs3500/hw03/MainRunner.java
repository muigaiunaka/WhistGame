package cs3500.hw03;

import cs3500.hw04.WhistModelCreator;

import java.io.InputStreamReader;
import java.util.InputMismatchException;
import java.util.Scanner;

public class MainRunner {
  public static void main(String[] args) {

    // Prompts the users to enter how many players are playing!
    System.out.println("How many players are playing?");

    Scanner thisInput = new Scanner(System.in);
    int numPlayers;

    boolean hasStarted = true;

    while (hasStarted) {

      try {

        numPlayers = thisInput.nextInt();
        new WhistController(new InputStreamReader(System.in), System.out)
            .startGame(new WhistModel(), numPlayers); //chooseGameType(gametype)
        // TODO: SET TO WHIST MODEL OR WHISTMODELBENCARSON above?

        hasStarted = false;

      } catch (IllegalArgumentException e3) {
        System.out.println(e3.getMessage());
        //numPlayers = thisInput.nextInt();
      } catch (InputMismatchException e4) {
        System.out.println("Must be a valid integer!");
        thisInput.next();

      }

    }


  }

  private static CardGameModel chooseGameType(int gameType) {
    CardGameModel game = new WhistModel();

    switch (gameType) {
      case 1:
        game = WhistModelCreator.create(WhistModelCreator.ModelType.TRUMP);
        break;
      case 2:
        game = WhistModelCreator.create(WhistModelCreator.ModelType.NOTRUMP);
        break;
    }
    return game;
  }

}
