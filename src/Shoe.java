import java.util.ArrayList;
import java.util.Collections;

/*
 * The shoe class represents the 6 decks of playing cards that are used for the blackjack game
 * Each deck has 52 cards in it and the shoe has 6 decks shuffled together
 * The class provides methods for printing, getting the next card, and resetting the shoe
 */
public class Shoe {
	// arraylist with Card objects
	private ArrayList<Card> shoe;

	Shoe() {
		// initialize the shoe ArrayList
		shoe = new ArrayList<Card>();
		setShoe();

	}

	// remove the Card from the front of the queue and return it
	public Card getNextCard() {
		return shoe.remove(0);
	}

	// print data for each card in the shoe in order
	public void printshoe() {
		for (Card c : shoe) {
			c.print();
		}
	}

	// sets the shoe with 6 decks of 52 cards shuffled together
	public void setShoe() {
		// loop i = 0 - 5 for the 6 shoes that casinos combine for a blackjack shoe
		// loop j = 0 - 3 for 4 suits of cards
		// loop k = 2 - 14 for 2 - A cards in each suit
		for (int i = 0; i < 6; i++) {
			for (int j = 0; j < 4; j++) {
				for (int k = 2; k < 15; k++) {
					shoe.add(new Card(j, k));
				}
			}
		}

		// shuffle 6 shoes together
		Collections.shuffle(shoe);
	}
}
