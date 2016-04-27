package cs3500.hw04;

import cs3500.hw02.Card;
import cs3500.hw02.Player;
import cs3500.hw02.Suit;
import cs3500.hw03.WhistModel;

import java.util.*;

/**
 * Variation of a Model for a Whist game that includes a Trump card component.
 * This Model implements the CardGameModel interface and extends the WhistModel.
 *
 * NOTE:
 * In this variation, one of the four suits is declared to be a trump suit at the start of a game.
 * As before, the player who starts a hand determines the suit of the hand. All other players must
 * play a card of the same suit if they have one. However, if a player does not have a card of the
 * same suit, he/she has two choices. The first choice is as before: play a card of some other suit
 * and essentially “forfeit” this hand. However the player can also play a card of the trump suit.
 * Once a card of the trump suit is played in a hand, the winner of the hand is the player who
 * played a card of the trump suit of the highest value. That is, once a card of the trump suit
 * is played, the values of the hand’s suit do not matter anymore for that hand. If the suit of the
 * current hand is the trump suit (i.e. the first player in the hand played a card of
 * the trump suit) then the hand proceeds normally as before: the card of this suit with the
 * highest value wins the hand.
 */
public class WhistModelBenCarson extends WhistModel {

  // the suit of the trump card for the game
  private Suit trumpSuit;

  public WhistModelBenCarson() {
    players = new ArrayList<>();
  }

  @Override
  public void play(int playerNo, int cardIdx) {
    ArrayList<Card> thisPlayersHand = this.players.get(playerNo).hand; // i.e. playerNo 2 = index 1

    if (isGameOver() ) {
      throw new IllegalStateException("Sorry mate, the game is over");
    }

    if ( invalidInput(thisPlayersHand, cardIdx) ) {
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
    addToPile(playerNo, cardIdx, thisPlayersHand, curCard);
    isActiveHand = true;

    // set the bestCard = to the first card played in the round
    if ( pile.isEmpty() ) {
      bestCard = curCard;
    }

    if ( pile.size() == 1 ) {
      gameHandSuit = curCard.getSuit();
    } else if ( pile.size() == numPlayers || pile.size() >= 51 ||
        pile.size() > 0 && pile.size() < numPlayers - 1 &&
            this.players.get(nextPlayer % numPlayers).hand.isEmpty() ) {
      bestCard = setBestCard(bestCard);

      ArrayList<Card> trumpHand = new ArrayList<>();

      bestCard = getBestTrumpCard(bestCard, trumpHand);

      winnerScores(bestCard);

      resetGameHand();
      isActiveHand = false;

    }

    setNextPlayer();

  }

  /**
   * Increments the players score and determines the next player to play and start the new
   * "trick" or round
   * @param bestCard the highest ranking card that is mapped to the winner
   */
  private void winnerScores(Card bestCard) {
    nextPlayer = pile.get(bestCard);
    int winner = pile.get(bestCard);
    this.players.get(winner).score += 1;
  }

  /**
   * Sets the next player
   */
  private void setNextPlayer() {
    nextPlayer = (nextPlayer % numPlayers) + 1;
  }

  /**
   * Resets the "Trick" or game pile and changes the suit of the trick/pile
   *
   * Clears the previous pile and resets the game hand to be null
   */
  private void resetGameHand() {
    this.gameHand.clear();
    pile.clear();
    gameHandSuit = null;
  }

  /**
   * Sets the suit of the "trick" or the table pile each round.
   * Returns the best card in that trick/pile
   * @param bestCard the best card of a round
   *
   * @return the best card of a round (not including Trump cards if a trump hand has been played)
   */
  private Card setBestCard(Card bestCard) {
    for ( Card c: pile.keySet() ) {
      if ( gameHandSuit == c.getSuit() ) {

        if (bestCard != null) {
          if (bestCard.compareTo(c) < 0) {
            bestCard = c;
          }
        } else {
          bestCard = c;
        }

      }
    }
    return bestCard;
  }

  /**
   * Adds a card to the "trick" or table pile, takes the card from this players hand
   *
   * @param playerNo the index of the player playing the card
   * @param cardIdx the index of the card to be played
   * @param thisPlayersHand the current players hand of cards
   * @param curCard the current card being played
   */
  private void addToPile(int playerNo, int cardIdx,
      ArrayList<Card> thisPlayersHand, Card curCard) {
    this.gameHand.add(curCard);
    pile.put(curCard, playerNo);
    thisPlayersHand.remove(cardIdx);
  }

  /**
   * Gets the highest ranking card that are of the trump suit
   *
   * @param bestCard the highest ranking card in a current trick
   * @param trumpHand the list of all the trump cards
   *
   * @return the highest ranking card of the trump suit
   */

  private Card getBestTrumpCard(Card bestCard, ArrayList<Card> trumpHand) {
    for (Card c : pile.keySet()) {
      if (c.getSuit() == trumpSuit) {
        trumpHand.add(c);
        Collections.sort(trumpHand, new CardComparator());
        bestCard = trumpHand.get(0);
      }
    }
    return bestCard;
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
   * "Trump suit: X" (where X is replaced by the character of the trump suit) <br>
   * or <br>
   * "Game over. Player X won" (if game is over and player X has won) <br>
   *
   * @return a string containing the entire state of the game <br>
   *
   * @throws IllegalArgumentException if start play has not been called yet! <br>
   *
   */
  @Override
  public String getGameState() {
    StringBuilder trumpGameState = new StringBuilder();
    trumpGameState.append(super.getGameState());
    if (!isGameOver() ) {
      trumpGameState.append("\nTrump Suit: ").append(trumpSuit.printSuit());
    }

    return trumpGameState.toString();
  }

  /**
   * Starts the game with the input number of players and the input deck of cards.
   * Distributes these cards in the specified order among the players in round-robin fashion.
   * For example, card dealt to player 1, card dealt to player 2 then card dealt to player 3, etc.
   *
   * Sets the trump suit to be the suit of the first card of the provided deck
   *
   * @param numPlayers the number of players in the game input as an int value
   * @param deck a deck of cards input as a List<K>
   *
   * @throws IllegalArgumentException if numPlayers is not greater than 1
   * @throws IllegalArgumentException if deck, the one passed to the method, is invalid
   */
  @Override
  public void startPlay(int numPlayers, List<Card> deck) {

    trumpSuit = deck.get(0).getSuit();

    super.startPlay(numPlayers, deck);
  }

  private boolean invalidInput(List<Card> hand, int input ) {
    return input > hand.size() || input < 0;
  }


}
