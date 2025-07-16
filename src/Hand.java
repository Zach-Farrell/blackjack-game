import java.util.ArrayList;

/*
 * the Hand class represents the player and dealers hands in the blackjack game
 * it contains variables and functions to allow the total score to be calculated for a hand.
 */
public class Hand {
	/*
	 * The hand variable holds the card data for the hand.
	 * the score variable represents the total score of the hand based on which
	 * cards are in the hand.
	 * the hard variable tracks whether a hand's score can be reduced by ten if an
	 * ace would cause the hand to bust.
	 */
	private ArrayList<Card> hand;
	private int score;
	boolean hard;

	// constructor for initializing variables.
	public Hand() {
		reset();
	}

	// returns the total score of the hand.
	public int getScore() {
		return score;
	}

	// resets the hand.
	public void reset() {
		hand = new ArrayList<Card>();
		score = 0;
		hard = true;
	}

	// return the current number of cards in the hand
	public int getNumCards() {
		return hand.size();
	}

	// adds the given card to the hand and updates score/hard variables accordingly.
	public void addCard(Card c) {
		if (c.getVal() > 10 && c.getVal() < 14)
			score += 10;
		else if (c.getVal() == 14) {
			score += 11;
		}
		hand.add(c);

	}
}