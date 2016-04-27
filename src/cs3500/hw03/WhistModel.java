package cs3500.hw03;

import cs3500.hw02.Card;
import cs3500.hw02.GenericStandardDeckGame;
import cs3500.hw02.Player;
import cs3500.hw02.Suit;

import java.util.*;

/**
 * Model for a Whist game that implements the CardGameModel interface and
 * extends the GenericStandardDeckGame
 */
public class WhistModel extends GenericStandardDeckGame implements CardGameModel<Card> {

   // the table hand / pile of cards being added to each round
  protected ArrayList<Card> gameHand = new ArrayList<>();
  // the suit of the current hand
  protected Suit gameHandSuit;
  // pile to store players and their played card
  protected Map<Card, Integer> pile = new LinkedHashMap<>();
  // next player to play
  protected int nextPlayer;
  // is the hand currently in play?
  protected boolean isActiveHand;
  // current player
  protected int curPlayer;

  public WhistModel() {
    nextPlayer = 1;
    players = new ArrayList<>();

    for (Player p : players ) {
      p.sortHand();
    }
    isActiveHand = false;
  }

  @Override
  public void play(int playerNo, int cardIdx) {
    ArrayList<Card> thisPlayersHand = this.players.get(playerNo).hand; // i.e. playerNo 2 = index 1

    if (isGameOver() ) {
      throw new IllegalStateException("Sorry mate, the game is over");
    }

    if ( cardIdx > thisPlayersHand.size() || cardIdx < 0 ) {
      throw new IllegalArgumentException("Invalid input");
    }

    if (playerNo != this.getCurrentPlayer() ){
      throw new IllegalArgumentException("It is not your turn!");
    }

    if (thisPlayersHand.get(cardIdx).getSuit() != gameHandSuit) {
      for (Card c : thisPlayersHand) {
        if (c.getSuit().equals(gameHandSuit)) {
          throw new IllegalArgumentException("Must play card of the correct suit in your hand!");
        }
      }
    }

    // current players is the index in the array of players
    Player curPlayer = this.players.get(playerNo);
    // assigning the variable, curCard to be played is the card at cardIdx in this player's hand
    Card curCard = curPlayer.hand.get(cardIdx);
    Card bestCard = null;

    // adds the appropriate card to the current hand.
    this.gameHand.add(curCard);
    // adds the current card to the hashmap, assigns key (card) to value (playerNo)
    pile.put(curCard, playerNo);
    // removes this card from this player's hand
    thisPlayersHand.remove(cardIdx);
    isActiveHand = true;

    // set the bestCard = to the first card played in the round
    if ( pile.isEmpty() ) {
      bestCard = curCard;
    }

    // if the pile only has one card in it, set that card to the best card
    if ( pile.size() == 1 ) {
      // determines the hand's suit
      gameHandSuit = curCard.getSuit();
    } else if ( pile.size() == numPlayers || pile.size() >= 51 ||
        pile.size() > 0 && pile.size() < numPlayers - 1 &&
            this.players.get(nextPlayer % numPlayers).hand.isEmpty() ) {
      // conditional for if it is the last play begins
      for ( Card c: pile.keySet() ) {
        // if the suit is equal to the current hand/round's suit
        if ( gameHandSuit.ordinal() == c.getSuit().ordinal() ) {

          if (bestCard != null) {
            if (bestCard.compareTo(c) < 0) {
              bestCard = c;
            }
          } else {
            bestCard = c;
          }

        }
      }

      // sets the next player to be the winner of the previous hand
      nextPlayer = pile.get(bestCard);
      int winner = pile.get(bestCard);
      // round winner's score goes up 1
      this.players.get(winner).score += 1;

      // clears the hand since it's the last card in this round
      this.gameHand.clear();
      // clears the hashmap's pile since it's the last card in this round
      pile.clear();
      // reset the suit to no suit which will be determine in next round
      gameHandSuit = null;

      isActiveHand = false;

    } // conditional for if it is the last play ends

    // determine who will play next
    nextPlayer = (nextPlayer % numPlayers) + 1;

  }

  /**
   * Invariant: always return an int larger than or equal to 0, or less
   * than or equal to number of players in the hand
   *
   * @return the player whose turn it is to play.
   * @throws IllegalStateException if the game is over
   */

  @Override
  public int getCurrentPlayer() {

    if (isGameOver() ) {
      throw new IllegalStateException("GAME IS OVER SILLY!");
    }

    return (nextPlayer-1)%numPlayers;
  }

  @Override
  public boolean isGameOver() {

    boolean isOver = false;

    int playersWhoHaveCards = 0;

    for (int i = 0; i < numPlayers; i++) {
      if (!this.players.get(i).hand.isEmpty()) {
        playersWhoHaveCards += 1;
      }
    }

    if (playersWhoHaveCards < 2 && numPlayers > 2 &&
        this.players.get(nextPlayer-1).hand.isEmpty() &&
        pile.size() < 2 ||
        !isActiveHand &&
        playersWhoHaveCards < 2 &&
        this.players.get(nextPlayer-1).hand.isEmpty() &&
        this.players.get(nextPlayer-2%numPlayers).hand.isEmpty() && pile.size() < 2) {
      isOver = true;
    }
    // int count = 0
    // for each player in this list of players
    // if player hand size > 0
    // count++
    // !(count > 1 ||
    return isOver ;

  }

  /**
   * Returns a string that contains the entire state of the game,
   * with the following information on its own line as follows: <br>
   *
   * Number of Players: N (printed as normal decimal number) <br>
   * Player 1: cards in sorted order (as comma-separated list) <br>
   * Player 2: cards in sorted order (as comma-separated list) <br>
   * ... <br>
   * Player N: cards in sorted order (as comma-separated list)<br>
   * Player 1: X hands won (where X is the number of hands won) <br>
   * Player 2: Y hands won (where Y is the number of hands won) <br>
   * ... <br>
   * Player N: Z hands won (where Z is the number of hands won) <br>
   * {Special message} : <br>
   * "Turn: Player X" (if game is ongoing and it is player X's turn) <br>
   * or <br>
   * "Game over. Player X won" (if game is over and player X has won) <br>
   *
   * @return a string containing the entire state of the game <br>
   *
   * @throws IllegalArgumentException if start play has not been called yet! <br>
   */
  @Override
  public String getGameState() {

    StringBuilder gameState = new StringBuilder();
    gameState.append(super.getGameState());

    for (int j = 0; j < this.numPlayers; j++) {
      gameState.append("Player ").append((j+1)).append(": ");
      gameState.append(this.players.get(j).score).append(" hands won").append("\n");
    }
    if ( !this.isGameOver() ) {
      // the current players turn
      gameState.append("Turn: Player ").append( this.getCurrentPlayer() + 1 );
    } else {
      gameState.append("Game over. ");

      // find highest scorer then see if any players equal that score
      ArrayList<Player> gamePlayers = this.players;
      Player max = gamePlayers.get(0);
      ArrayList<Integer> winners = new ArrayList<>();

      // sets the highest score to max
      for (int i = 1; i < gamePlayers.size(); i++) {
        if (gamePlayers.get(i).score > max.score) {
          max = gamePlayers.get(i);
        }
      }
      // add the highest score(s) to the index
      for (Player p: gamePlayers) {
        if (p.score == max.score) {
          winners.add(gamePlayers.indexOf(p));
        }
      }

      int i = 0;
      while ( i < winners.size() ) {
        if (i == winners.size() - 1) {
          gameState.append("Player ");
          gameState.append(winners.get(i) + 1 + " ");
        } else if (i < winners.size()) {
          gameState.append("Player ");
          gameState.append(winners.get(i) + 1 + ", ");
        }
        i++;
      }

      gameState.append("won");
    }

    return gameState.toString();
  }

  private void advanceNextPlayer() {
    curPlayer = (curPlayer+1) % this.players.size();
    while (this.players.get(curPlayer).hand.size() == 0 &&
        !this.pile.containsKey(curPlayer)) {
      curPlayer = (curPlayer +1)%this.players.size();
    }
  }

  private boolean pileIsDone() {
    return this.pile.containsKey(this.curPlayer);
  }

  private void finishTrick() {
    this.pile.clear();
    this.gameHandSuit = null;
    if (this.players.get(curPlayer).hand.size() == 0) {
      advanceNextPlayer();
    }
  }
}
