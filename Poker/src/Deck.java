import java.util.ArrayList;
import java.util.Collections;


/**
 * The class deck acts as like a stack data structure but for holding cards specifically.
 * Cards will be from values Ace to King and also contain a suit among club, diamond, heart,
 * and spade.
 * @author Danny Yi
 *
 */
public class Deck {
	
	/**
	 * Arrays that keep track of the position to create certain specific cards. These
	 * should not be changed.
	 */
	private final int[] VALUES = 
		{1, 2, 3, 4, 5, 6, 7, 8, 9, 10, 11, 12, 13};
	private final String[] SUITS = {"club", "diamond", "heart", "spade"};
	
	/**
	 * An ArrayList attribute that keeps track of all the cards in the deck. Each time
	 * a card is removed from the deck or added to the deck, the cards attribute will
	 * change accordingly.
	 */
	private ArrayList<Card> cards;
	
	/**
	 * Constructor for the Deck class. The class creates a copy of each card in a normal
	 * deck of 52 cards. After each card is created, the deck is then shuffled.
	 */
	public Deck() {
		cards = new ArrayList<Card>();
		for(int value = 0; value < 13; value++) {
			for(int suit = 0; suit < 4; suit++) {
				cards.add(new Card(VALUES[value], SUITS[suit]));
			}
		}
		
		this.shuffle();
	}
	
	/**
	 * Method that shuffles the deck of cards.
	 */
	public void shuffle() {
		Collections.shuffle(cards);
	}
	
	/**
	 * This method returns the card on the top of the deck.
	 * @return	returns the card on the top of the deck.
	 */
	public Card top() {
		return cards.get(0);
	}
	
	/**
	 * This method deletes the card on the top of the deck but also returns it
	 * at the same time.
	 * @return	returns the card on the top of the deck.
	 */
	public Card pop() {
		Card card = cards.get(0);
		this.remove();
		return card;
	}
	
	/**
	 * This method removes the top card from the deck.
	 */
	public void remove() {
		cards.remove(0);
	}
	
}
