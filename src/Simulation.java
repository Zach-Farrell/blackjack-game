import java.util.ArrayList;

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
		startNewRound();
	}

	public void startNewRound() {
		chips -= curBet;
		player.reset();
		dealer.reset();
		deal();
	}

	public void hit() {
		player.addCard(shoe.getNextCard());
		if (checkForBust()) {
			// lose
		}
	}

	private void deal() {
		System.out.println("size of shoe: " + shoe.getNumCards());
		player.addCard(shoe.getNextCard());
		dealer.addCard(shoe.getNextCard());
		player.addCard(shoe.getNextCard());
		dealer.addCard(shoe.getNextCard());
		System.out.println("size of shoe: " + shoe.getNumCards());
		System.out.println("size of player hand: " + player.getNumCards());
	}

	private boolean checkForBust() {
		return false;
	}

}
