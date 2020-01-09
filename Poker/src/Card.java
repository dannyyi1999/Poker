import javafx.scene.paint.Color;

/**
 * The Card class is a class that holds a specific value (Ace, 2, 3, etc.) while
 * also containing one of the the four suits(club, diamond, heart, spade)
 *
 * @author Danny Yi
 */
public class Card {
	
	/**
	 * The attribute that keeps track of the card's color.
	 */
	private Color color;
	
	/**
	 * The attribute that keeps track of the card's value.
	 */
	private int value;
	
	/**
	 * The attribute that keeps track of the card's suit.
	 */
	private String suit;
	
	
	/**
	 * Constructor method of creating a card. The constructor takes in a value
	 * and suit of a card.
	 * @param value		The value of a card between 1 and 13 representing Ace to King.
	 * @param suit		The suit of a card among club, diamond, heart, spade.
	 */
	public Card(int value, String suit) {
		this.value = value;
		this.suit = suit;
		
		if(suit.equals("club") || suit.equals("spade")) {
			this.color = Color.BLACK;
		}else if(suit.equals("club") || suit.equals("spade")) {
			this.color = Color.RED;
		}
		
	}
	
	
	/**
	 * Getter method of returning the value of the card.
	 * @return	an integer between the number 1 to 13 inclusive.
	 */
	public int getValue() {
		return value;
	}
	
	/**
	 * Getter method of returning the value of the suit.
	 * @return	a String representing club, diamond, heart, or spade.
	 */
	public String getSuit() {
		return suit;
	}
	
	/**
	 * Getter method of returning the color of the card.
	 * @return	a color between red or black depending on the suit.
	 */
	public Color getColor() {
		return color;
	}
	
	/**
	 * Method that returns the string representation of a card.
	 * @return	a string representation of the card. For example, "A of spade".
	 */
	public String toString() {
		String out = "";
		if(value == 1 || value == 14) {
			out += "A ";
		}else if(value == 11) {
			out += "J ";
		}else if(value == 12) {
			out += "Q ";
		}else if(value == 13) {
			out += "K ";
		}else {
			out += value + " ";
		}
		
		return out + "of " + suit;
	}
	
}
