import java.util.ArrayList;

import javax.swing.JOptionPane;

/*
 * The Simulation class handles all of the processing for the blackjack game.
 * 
 */
public class Simulation {
	private Shoe shoe;
	private Hand player;
	private Hand dealer;
	private int chips;
	private int curBet;

	public Simulation() {
		shoe = new Shoe();
		player = new Hand();
		dealer = new Hand();
		chips = 100;
		curBet = 5;
		startNewRound(curBet);
	}

	public Hand getPlayer() {
		return player;
	}

	public Hand getDealer() {
		return dealer;
	}

	public void setBetAmount(int newBetAmount) {
		curBet = newBetAmount;
	}

	public int getBetAmount() {
		return curBet;
	}

	// reset the game state other than the shoe which stays constant until empty.
	public void startNewRound(int bet) {
		chips -= curBet;
		player.reset();
		dealer.reset();
		deal();
	}

	// the player hits and the next card in the shoe is dealt to them. Returns true
	// if the player busts.
	public boolean hit() {
		player.addCard(shoe.getNextCard());
		if (checkForBust(player)) {
			chips -= curBet;
			return true;
		}
		return false;
	}

	// simulate the dealer when the player chooses the stand option.
	public int stand() {
		return simulaterDealer();
	}

	public int doubleDown() {
		chips -= curBet;
		curBet *= 2;
		player.addCard(shoe.getNextCard());
		return simulaterDealer();
	}

	// plays the hand of the dealer once the player is done. Return 0 if the dealer
	// wins, 1 if the player's score beats the dealer's, 2 if the dealer busts, and
	// 3 if the player had blackjack
	private int simulaterDealer() {
		if (player.isBlackJack() && !dealer.isBlackJack()) {
			// if the player has a blackjack and the dealer does not then there is no need
			// for dealer to draw cards.
			chips += curBet * 1.5;
			return 3;
		}
		while (true) {
			if (dealer.getScore() < 17 || (dealer.getScore() == 17 && dealer.isSoft())) {
				// dealer is not done drawing cards. Add card to hand and go to next iteration.
				dealer.addCard(shoe.getNextCard());
			} else if (checkForBust(dealer)) {
				// dealer busts.
				return 2;
			} else {
				// dealer is done drawing cards. Time to determine the winner.
				if (player.determineWinner(dealer) == 1) {
					// player wins
					return 1;
				} else if (player.determineWinner(dealer) == 0) {
					// push
					return 4;
				}
				// no other ways for player to win, dealer wins.
				return 0;
			}
		}
	}

	// simulates the initial deal of the game before the player chooses to hit,
	// stand, double, etc...
	private void deal() {
		player.addCard(shoe.getNextCard());
		dealer.addCard(shoe.getNextCard());
		player.addCard(shoe.getNextCard());
		dealer.addCard(shoe.getNextCard());

		if (player.isBlackJack()) {
			simulaterDealer();
			// BlackJack!
		}
	}

	// return true if the hand is a bust (over 21)
	private boolean checkForBust(Hand h) {
		return h.getScore() > 21;
	}

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

	public void displayBoard(String msg) {
		JOptionPane.showConfirmDialog(null, "Hand Over", msg,
				JOptionPane.OK_CANCEL_OPTION);
	}

}
