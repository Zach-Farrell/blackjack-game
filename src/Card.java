/**
 * @class Card
 * @brief Represents a single playing card in a standard 52-card deck.
 *
 *        Each card has a numeric value from 2 to 14 (where 11 = Jack, 12 =
 *        Queen, 13 = King, 14 = Ace)
 *        and a suit from 0 to 3 (0 = Spades, 1 = Hearts, 2 = Clubs, 3 =
 *        Diamonds).
 * 
 *        This class provides methods to access card data and generate image
 *        paths for use in GUI.
 *        It is used by the Shoe class to manage multiple decks.
 */
public class Card {

	/**
	 * @brief The value of the card (2-14).
	 */
	private int value;

	/**
	 * @brief The suit of the card (0-3), representing Spades, Hearts, Clubs, and
	 *        Diamonds.
	 */
	private int suit;

	/**
	 * @brief Constructs a new Card with the specified suit and value.
	 * 
	 * @param suit The suit of the card (0 = Spades, 1 = Hearts, 2 = Clubs, 3 =
	 *             Diamonds).
	 * @param val  The value of the card (2-14).
	 */
	public Card(int suit, int val) {
		this.value = val;
		this.suit = suit;
	}

	/**
	 * @brief Gets the numeric value of the card.
	 * @return The card's value (2-14).
	 */
	public int getVal() {
		return value;
	}

	/**
	 * @brief Gets the suit of the card.
	 * @return The card's suit (0-3).
	 */
	public int getSuit() {
		return suit;
	}

	/**
	 * @brief Generates the file path for the card's image based on its value and
	 *        suit.
	 *
	 *        The path format is "support/deck-images/<value>_of_<suit>.png", where
	 *        value may be
	 *        a number or a face card (jack, queen, king, ace) and suit is one of
	 *        the four suits.
	 * 
	 * @return A string representing the path to the card's image.
	 */
	public String getImagePath() {
		String imgPath = "support/deck-images/";
		String suit = "";

		switch (this.suit) {
			case 0:
				suit = "spades";
				break;
			case 1:
				suit = "hearts";
				break;
			case 2:
				suit = "clubs";
				break;
			case 3:
				suit = "diamonds";
				break;
			default:
				suit = "invalid suit";
				break;
		}

		switch (this.value) {
			case 11:
				imgPath += "jack";
				break;
			case 12:
				imgPath += "queen";
				break;
			case 13:
				imgPath += "king";
				break;
			case 14:
				imgPath += "ace";
				break;
			default:
				imgPath = imgPath + this.value;
				break;
		}

		return imgPath + "_of_" + suit + ".png";
	}

	/**
	 * @brief Gets the path to the back-of-card image.
	 * @return A string representing the file path for the card back image.
	 */
	public String getBackOfCard() {
		return "support/deck-images/back_of_card.png";
	}

	/**
	 * @brief Prints the string representation of the card to the console.
	 */
	public void print() {
		System.out.println(toString());
	}
}
