/*
 * the Card class represents a single playing card in a standart 52 card deck.
 * Each card is assigned a number value 2-14 or 2-Ace and a suit 0-3 [Spades, hearts, clubs, diamonds]
 * this class provides methods to access card data and is used by the shoe class to manage a full 6 decks of cards
 */

public class Card {

	// variables for number value 2-14 of cards and 0-3 for card suit
	private int value;
	private int suit;

	// card constructor that initializes value and suit varaibles
	public Card(int suit, int val) {
		this.value = val;
		this.suit = suit;
	}

	public int getVal() {
		return value;
	}

	public void setVal(int val) {
		this.value = val;
	}

	public int getSuit() {
		return suit;
	}

	public void setSuit(int suit) {
		this.suit = suit;
	}

	// forms a string from the card data and has output <number> of <suit> for cards
	public String toString() {
		String valueString = this.value + "";
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
				valueString = "jack";
				break;
			case 12:
				valueString = "queen";
				break;
			case 13:
				valueString = "king";
				break;
			case 14:
				valueString = "ace";
				break;
			default:
				valueString = this.value + "";
				break;
		}

		return valueString + " of " + suit;
	}

	// print method prints string data of the card
	public void print() {
		System.out.println(toString());
	}
}
