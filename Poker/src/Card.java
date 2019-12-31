import javafx.scene.paint.Color;

public class Card {
	
	private Color color;
	private int value;
	private String suit;
	
	public Card(int value, String suit) {
		this.value = value;
		this.suit = suit;
		
		if(suit.equals("club") || suit.equals("spade")) {
			this.color = Color.BLACK;
		}else if(suit.equals("club") || suit.equals("spade")) {
			this.color = Color.RED;
		}
	}
	
	public int getValue() {
		return value;
	}
	
	public String getSuit() {
		return suit;
	}
	
	public Color getColor() {
		return color;
	}
	
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
