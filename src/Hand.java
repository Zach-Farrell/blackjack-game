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
	 * the soft variable tracks whether a hand's score can be reduced by ten if an
	 * ace would cause the hand to bust.
	 * True for if a hand has an ace which is an 11 or 1
	 */
	private ArrayList<Card> hand;
	private int score;
	boolean soft;

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
		soft = false;
	}

	// return the current number of cards in the hand
	public int getNumCards() {
		return hand.size();
	}

	public boolean isSoft() {
		return soft;
	}

	// return true if the hand is a blackjack
	public boolean isBlackJack() {
		return score == 21 && hand.size() == 2;
	}

	// adds the given card to the hand and updates score/hard variables accordingly.
	public void addCard(Card c) {
		if (c.getVal() > 10 && c.getVal() < 14) {
			// jack, queen, king facecards are added as 10 score.
			score += 10;
		} else if (c.getVal() == 14) {
			// if the card is an ace, check to see if the card should be added as 1 or 11.
			// set soft variable to true if ace is added as 11
			if (score < 11) {
				score += 11;
				soft = true;
			} else {
				score += 1;
			}
		} else {
			// if the card is neither ace nor facecard then add the card value to score.
			score += c.getVal();
		}
		// if the hand would be a bust and contains an ace of value 11 then subtract 10
		// from the score.
		if (score > 21 && soft) {
			score -= 10;
			soft = false;
		}
		hand.add(c);

	}

	// returns
	public ArrayList<Card> getCards() {
		return hand;
	}

	// returns true if the current hand is winner, false if the hand argument is the
	// winner.
	public boolean determineWinner(Hand h) {

		return false;
	}
}