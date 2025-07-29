import java.util.ArrayList;

/**
 * @class Hand
 * @brief Represents a hand of cards for the player or dealer in Blackjack.
 * 
 *        This class maintains the list of cards in the hand, calculates the
 *        total score,
 *        and tracks whether the hand is "soft" (i.e., contains an Ace counted
 *        as 11).
 */
public class Hand {
	/**
	 * @brief List of Card objects currently in the hand.
	 */
	private ArrayList<Card> hand;

	/**
	 * @brief The current total score of the hand.
	 */
	private int score;

	/**
	 * @brief True if the hand contains an Ace counted as 11 (soft hand).
	 */
	boolean soft;

	/**
	 * @brief Constructs a new empty hand.
	 */
	public Hand() {
		reset();
	}

	/**
	 * @brief Returns the current score of the hand.
	 * @return The total numeric score of the hand.
	 */
	public int getScore() {
		return score;
	}

	/**
	 * @brief Resets the hand by clearing all cards and resetting score and soft
	 *        flag.
	 */
	public void reset() {
		hand = new ArrayList<Card>();
		score = 0;
		soft = false;
	}

	/**
	 * @brief Returns the number of cards currently in the hand.
	 * @return The count of cards.
	 */
	public int getNumCards() {
		return hand.size();
	}

	/**
	 * @brief Returns whether the hand is soft (contains an Ace counted as 11).
	 * @return True if the hand is soft, false otherwise.
	 */
	public boolean isSoft() {
		return soft;
	}

	/**
	 * @brief Returns true if the hand is a blackjack (score 21 with exactly two
	 *        cards).
	 * @return True if blackjack, false otherwise.
	 */
	public boolean isBlackJack() {
		return score == 21 && hand.size() == 2;
	}

	/**
	 * @brief Adds a card to the hand and updates the score and soft flag.
	 * 
	 *        Face cards (Jack, Queen, King) count as 10.
	 *        Ace counts as 11 if it does not cause bust; otherwise as 1.
	 *        Adjusts score if hand would bust but contains a soft Ace.
	 * 
	 * @param c The Card to add to the hand.
	 */
	public void addCard(Card c) {
		if (c.getVal() > 10 && c.getVal() < 14) {
			// Jack, Queen, King count as 10
			score += 10;
		} else if (c.getVal() == 14) {
			// Ace counts as 11 if possible, otherwise 1
			if (score < 11) {
				score += 11;
				soft = true;
			} else {
				score += 1;
			}
		} else {
			// Number cards count as their face value
			score += c.getVal();
		}

		// Adjust if bust and soft hand (convert Ace from 11 to 1)
		if (score > 21 && soft) {
			score -= 10;
			soft = false;
		}

		hand.add(c);
	}

	/**
	 * @brief Returns the list of cards in the hand.
	 * @return An ArrayList of Card objects.
	 */
	public ArrayList<Card> getCards() {
		return hand;
	}

	/**
	 * @brief Compares this hand with another hand to determine the winner.
	 * 
	 * @param h The hand to compare against.
	 * @return 1 if this hand wins, -1 if the argument hand wins, 0 if tie.
	 */
	public int determineWinner(Hand h) {
		if (this.score > h.getScore())
			return 1;
		else if (this.score < h.getScore())
			return -1;
		return 0;
	}
}