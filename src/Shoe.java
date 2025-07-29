import java.util.ArrayList;
import java.util.Collections;

/**
 * @class Shoe
 * @brief Represents a shoe containing six combined and shuffled decks of
 *        playing cards for Blackjack.
 * 
 *        This class manages the collection of Card objects, simulating the card
 *        shoe used in casinos.
 *        It supports dealing cards, resetting the shoe, and printing card data.
 */
public class Shoe {
	/**
	 * @brief The list of Card objects currently in the shoe.
	 */
	private ArrayList<Card> shoe;

	/**
	 * @brief Constructs a new Shoe, initializes and shuffles six decks of cards.
	 */
	Shoe() {
		shoe = new ArrayList<Card>();
		setShoe();
	}

	/**
	 * @brief Removes and returns the next card from the front of the shoe.
	 * 
	 * @return The next Card dealt from the shoe.
	 */
	public Card getNextCard() {
		return shoe.remove(0);
	}

	/**
	 * @brief Prints information about each card in the shoe in order.
	 */
	public void printshoe() {
		for (Card c : shoe) {
			c.print();
		}
	}

	/**
	 * @brief Returns the current number of cards remaining in the shoe.
	 * 
	 * @return The number of cards left in the shoe.
	 */
	public int getNumCards() {
		return shoe.size();
	}

	/**
	 * @brief Resets and refills the shoe with six standard decks of cards, then
	 *        shuffles them.
	 * 
	 *        Each deck contains 52 cards with values 2 through Ace (14) in four
	 *        suits.
	 *        Six decks are combined and shuffled together.
	 */
	public void setShoe() {
		// Clear any existing cards
		shoe.clear();

		// Add 6 decks of cards (6 * 52 cards)
		for (int i = 0; i < 6; i++) {
			for (int j = 0; j < 4; j++) {
				for (int k = 2; k < 15; k++) {
					shoe.add(new Card(j, k));
				}
			}
		}

		// Shuffle all cards in the shoe
		Collections.shuffle(shoe);
	}
}
