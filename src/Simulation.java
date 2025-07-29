import java.util.ArrayList;
import javax.swing.JOptionPane;

/**
 * @class Simulation
 * @brief Handles all the processing and game logic for the Blackjack game.
 *
 *        This class manages the shoe, player and dealer hands, chip count,
 *        betting,
 *        and the flow of the game including hitting, standing, doubling down,
 *        and
 *        determining the winner.
 */
public class Simulation {
	private Shoe shoe; /// < The shoe containing multiple decks of cards
	private Hand player; /// < The player's current hand
	private Hand dealer; /// < The dealer's current hand
	private int chips; /// < Player's current chip count
	private int curBet; /// < Current bet amount
	private boolean dealerRevealed; /// < Whether the dealer's hidden card is revealed

	/**
	 * @brief Constructs a new Simulation, initializing shoe, hands, chips and
	 *        starting a round.
	 */
	public Simulation() {
		shoe = new Shoe();
		player = new Hand();
		dealer = new Hand();
		chips = 100;
		curBet = 5;
		startNewRound(curBet);
	}

	/**
	 * @brief Reveals the dealer's hidden card.
	 */
	public void revealDealer() {
		dealerRevealed = true;
	}

	/**
	 * @brief Checks if the dealer's hidden card is revealed.
	 * @return true if dealer's card is revealed; false otherwise.
	 */
	public boolean isDealerRevealed() {
		return dealerRevealed;
	}

	/**
	 * @brief Gets the player's hand.
	 * @return The player's Hand object.
	 */
	public Hand getPlayer() {
		return player;
	}

	/**
	 * @brief Gets the dealer's hand.
	 * @return The dealer's Hand object.
	 */
	public Hand getDealer() {
		return dealer;
	}

	/**
	 * @brief Sets the current bet amount.
	 * @param newBetAmount The new bet amount.
	 */
	public void setBetAmount(int newBetAmount) {
		curBet = newBetAmount;
	}

	/**
	 * @brief Gets the current bet amount.
	 * @return The current bet.
	 */
	public int getBetAmount() {
		return curBet;
	}

	/**
	 * @brief Starts a new round, resetting player and dealer hands and dealing new
	 *        cards.
	 *        Deducts the bet amount from chips.
	 * 
	 * @param bet The bet amount for the new round.
	 */
	public void startNewRound(int bet) {
		chips -= curBet;
		player.reset();
		dealer.reset();
		dealerRevealed = false;
		deal();
	}

	/**
	 * @brief Player hits and receives the next card from the shoe.
	 * 
	 * @return true if player busts (hand value > 21), false otherwise.
	 */
	public boolean hit() {
		player.addCard(shoe.getNextCard());
		if (checkForBust(player)) {
			chips -= curBet;
			return true;
		}
		return false;
	}

	/**
	 * @brief Player stands; dealer plays out their hand.
	 * 
	 * @return An int representing the win condition after dealer finishes.
	 */
	public int stand() {
		revealDealer();
		return simulaterDealer();
	}

	/**
	 * @brief Player doubles down, doubling bet and receiving exactly one more card.
	 * 
	 * @return An int representing the win condition after dealer finishes.
	 */
	public int doubleDown() {
		chips -= curBet;
		curBet *= 2;
		player.addCard(shoe.getNextCard());
		return simulaterDealer();
	}

	/**
	 * @brief Simulates the dealer's turn according to Blackjack rules.
	 *
	 *        Dealer draws cards until reaching at least 17, hitting soft 17.
	 *        Determines the winner after dealer finishes.
	 * 
	 * @return Integer indicating game result:
	 *         0 = dealer wins,
	 *         1 = player wins,
	 *         2 = dealer busts,
	 *         3 = player blackjack,
	 *         4 = push (tie).
	 */
	private int simulaterDealer() {
		if (player.isBlackJack() && !dealer.isBlackJack()) {
			chips += curBet * 1.5;
			return 3;
		}
		while (true) {
			if (dealer.getScore() < 17 || (dealer.getScore() == 17 && dealer.isSoft())) {
				dealer.addCard(shoe.getNextCard());
			} else if (checkForBust(dealer)) {
				return 2;
			} else {
				if (player.determineWinner(dealer) == 1) {
					return 1;
				} else if (player.determineWinner(dealer) == 0) {
					return 4;
				}
				return 0;
			}
		}
	}

	/**
	 * @brief Deals the initial cards to player and dealer.
	 */
	private void deal() {
		player.addCard(shoe.getNextCard());
		dealer.addCard(shoe.getNextCard());
		player.addCard(shoe.getNextCard());
		dealer.addCard(shoe.getNextCard());

		if (player.isBlackJack()) {
			simulaterDealer();
		}
	}

	/**
	 * @brief Checks if the given hand is bust (score exceeds 21).
	 * 
	 * @param h The hand to check.
	 * @return true if hand is bust, false otherwise.
	 */
	private boolean checkForBust(Hand h) {
		return h.getScore() > 21;
	}

	/**
	 * @brief Handles the outcome of a round by displaying a message and starting a
	 *        new round.
	 * 
	 * @param winCondition Integer representing the result of the round.
	 */
	public void handleWinner(int winCondition) {
		switch (winCondition) {
			case 0:
				displayBoard("You Lose " + dealer.getScore() + " to " + player.getScore());
				startNewRound(curBet);
				break;
			case 1:
				displayBoard("You Win " + player.getScore() + " to " + dealer.getScore());
				startNewRound(curBet);
				break;
			case 2:
				displayBoard("Dealer Busts, you win!");
				startNewRound(curBet);
				break;
			case 3:
				displayBoard("you win with BlackJack!");
				startNewRound(curBet);
				break;
			case 4:
				displayBoard("Push " + player.getScore());
				startNewRound(curBet);
				break;
		}
	}

	/**
	 * @brief Displays a message dialog with the round result.
	 * 
	 * @param msg The message to display.
	 */
	public void displayBoard(String msg) {
		JOptionPane.showConfirmDialog(null, "Hand Over", msg,
				JOptionPane.OK_CANCEL_OPTION);
	}
}