import java.util.ArrayList;
import java.util.Collections;

public class Deck {
	
	private final int[] VALUES = 
		{1, 2, 3, 4, 5, 6, 7, 8, 9, 10, 11, 12, 13};
	private final String[] SUITS = {"club", "diamond", "heart", "spade"};
	
	
	private ArrayList<Card> cards;
	
	public Deck() {
		cards = new ArrayList<Card>();
		for(int value = 0; value < 13; value++) {
			for(int suit = 0; suit < 4; suit++) {
				cards.add(new Card(VALUES[value], SUITS[suit]));
			}
		}
		
		this.shuffle();
	}
	
	public void shuffle() {
		Collections.shuffle(cards);
	}
	
	public Card top() {
		return cards.get(0);
	}
	
	public Card pop() {
		Card card = cards.get(0);
		this.remove();
		return card;
	}
	
	public void remove() {
		cards.remove(0);
	}
	
}
