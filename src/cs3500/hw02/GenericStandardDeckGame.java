package cs3500.hw02;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
//import hw02.src.cs3500.hw02.Card;
//import package cs3500.hw02

/**
 * Generic deck game that implements the GenericCardGameModel interface.
 * It contains a number of players and a list of players
 */
public class GenericStandardDeckGame implements GenericCardGameModel<Card> {

  public ArrayList<Player> players; // final
  public int numPlayers;

  /**
   * Default constructor for a GenericStandardDeckGame
   */
  public GenericStandardDeckGame() {
    players = new ArrayList<>();
  } // this.players = new ArrayList<Player>()

  @Override
  public List<Card> getDeck() {
    ArrayList<Card> deck;
    deck = new ArrayList<>();

    for (Suit suit: Suit.values()) {
      for (Rank rank: Rank.values()) {
        Card card = new Card(rank, suit);
        deck.add(card);

        Collections.sort(deck);
        Collections.reverse(deck);

      }
    }
    //Collections.shuffle(deck);

    return deck; // returns a sorted deck of cards
  }

  @Override
  public void startPlay(int numPlayers, List<Card> deck){
    // .clear() previous set of players
    this.numPlayers = numPlayers;

    if (numPlayers < 2) {
      throw new IllegalArgumentException("Need at least two players to start the game!");
    }
    if (deck.size() != 52) {
      throw new IllegalArgumentException("Invalid deck size! Need a 52 card deck to begin play!");
    }

    for (Card c: deck) {
      if (Collections.frequency(deck, c) > 1) {
        throw new IllegalArgumentException("No duplicate cards allowed in deck");
      }
    }
    //if (!validCards(cards)) throw new IllegalArgumentException("bad deck of cards");
    //if (!validPlayers(numPlayers)) throw new IllegalArgumentException("bad num players");

    players = new ArrayList<>();
    for (int i = 0; i < numPlayers; i++) {
      players.add(new Player());
    }

    int j = 0;
    for (Card c: deck ) { // for (Card c: shuffled ) {
      players.get(j).addCard(c); // j % numPlayers
      j++;
      if (j == numPlayers) {
        j =0;
      }
    }

    for (Player p: players) { // sorts each players hand
      p.sortHand();
    }

  }

  @Override
  public String getGameState() {
    if (this.players.size() == 0) {
      throw new IllegalStateException("Start Play has not been called yet!");
    }

    StringBuilder gameState = new StringBuilder();
    gameState.append("Number of Players: ").append(this.numPlayers).append("\n");
    for (int i = 0; i < this.numPlayers; i++) {
      gameState.append("Player ");
      gameState.append(i+1).append(": ");
      boolean first = true;
      for (Card c : this.players.get(i).hand) {
        if (!first) { gameState.append(", "); }
        gameState.append(c);
        first = false;
      }
      gameState.append("\n");
    }
    return gameState.toString();
  }

  @Override
  public List<Card> shuffle(List<Card> unshuffled) {
    List<Card> shuffled = new ArrayList<>();

    while( !unshuffled.isEmpty() ) {
      int loc = (int)(Math.random()* unshuffled.size());
      shuffled.add(unshuffled.get(loc));
      unshuffled.remove(loc);
    }

    return shuffled;
  }

}
