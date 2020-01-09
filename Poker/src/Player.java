import java.util.ArrayList;

/**
 * 
 * @author Danny Yi
 *
 */
public class Player {
	
	/**
	 * The hand attribute pertains to the two cards a player holds when playing
	 * Texas Hold'em.
	 */
	private ArrayList<Card> hand;
	
	/**
	 * This attribute of cards includes both the cards in the player's hand and the cards
	 * that are on the board. In other words, it holds all the cards that the player
	 * has possession of.
	 */
	private ArrayList<Card> cards;
	
	/**
	 * Keeps track of the player's chip count in possession.
	 */
	private double chipCount;
	
	/**
	 * Through the HandStrength class and its static methods, a hand strength can be
	 * evaluated based on the player's cards.
	 */
	private int handStrength;
	
	/**
	 * The current amount of chips the player is bidding for a certain round.
	 */
	private int bidAmount;
	
	/**
	 * Especially for GUI purposes, this attribute will make it so that a player's hand
	 * can or cannot be seen.
	 */
	private boolean handVisible;
	
	/**
	 * Constructor method that initializes all the attributes in the Player Class.
	 * @param chipCount	The amount of chips the player will start off with.
	 */
	public Player(double chipCount) {
		handVisible = true;
		this.bidAmount = 0;
		this.chipCount = chipCount;
		hand = new ArrayList<Card>();
		cards = new ArrayList<Card>();
		this.handStrength = 0;
	}
	
	/**
	 * Returns the player's hand strength according to the cards the player has.
	 * @return an integer representing the player's hand strength.
	 */
	public int getHandStrength() {
		return handStrength;
	}
	
	/**
	 * This method removes all chip possession from the player and moves the chips into
	 * the player's bid amount.
	 */
	public void allIn() {
		bidAmount += chipCount;
		chipCount = 0;
	}
	
	/**
	 * This method uses a static class method (HandStrength.class) to determine what the 
	 * hand strength of the player's cards is.
	 */
	public void calcHandStrength() {
		handStrength = HandStrength.findHandStrength(cards);
	}
	
	
	/**
	 * Returns an ArrayList of the two cards the player is holding.
	 * @return	an ArrayList of the two cards the player is holding.
	 */
	public ArrayList<Card> getHand() {
		return hand;
	}
	
	
	/**
	 * Returns an ArrayList of both the cards on the table and the player's hand.
	 * @return	an ArrayList of all cards the player has.
	 */
	public ArrayList<Card> getCards(){
		return cards;
	}
	
	
	/**
	 * Adds a card to the player's pool of cards in possession.
	 * @param c	A card to be added to the player's possession.
	 */
	public void addCardToList(Card c) {
		cards.add(c);
	}
	
	
	/**
	 * Adds a card to the player's hand. Note that there should never
	 * be more than two cards.
	 * @param c	A card to be added to the player's hand.
	 */
	public void addCardToHand(Card c) {
		hand.add(c);
	}
	
	/**
	 * A getter method of whether or not the player's hand can be seen.
	 * @return	a boolean of whether the player's hand can be seen or not.
	 */
	public boolean isHandVisible() {
		return handVisible;
	}
	
	
	/**
	 * A setter method that gets the boolean of whether or not the player's hand can
	 * be seen.
	 * @param visible	a boolean that sets hand to be visible or not.
	 */
	public void setHandVisible(boolean visible) {
		handVisible = visible;
	}
	
	
	/**
	 * A getter method to find how much chips the player has in possession.
	 * @return	A double representing the amount of chips the player has.
	 */
	public double getChipCount() {
		return chipCount;
	}
	
	
	/**
	 * A setter method that sets a player chip count to the specific value.
	 * @param chips A double that the player's chip count is set to.
	 */
	public void setChipCount(double chips) {
		chipCount = chips;
	}
	
	
	/**
	 * Removes a certain amount of chips from the player's pool and adds it into the
	 * amount the player is betting (bidAmount). If the number of chips to bid exceeds or 
	 * equals the number of chips in possession, then the bid is treated as going all in.
	 * @param chips A double representing amount of chips to bid.
	 */
	public void bid(double chips) {
		
		if(chips >= chipCount) {
			allIn();
			return;
		}
		
		bidAmount += chips;
		chipCount -= chips;
	}
	
	
	/**
	 * This method returns the number of chips the player has bet this round.
	 * @return A double representing the amount of chips the player has bet.
	 */
	public int getBidAmount() {
		return bidAmount;
	}
	
	
	/**
	 * This method clears out the bidAmount attribute and sets it to zero.
	 */
	public void clearBidAmount() {
		bidAmount = 0;
	}
	
	
	/**
	 * Similar to poker, this method removes the cards from the player's possession 
	 * and the hand is "folded".
	 */
	public void fold() {
		hand = new ArrayList<Card>();
		cards = new ArrayList<Card>();
	}
	
	
	/**
	 * For console version testing purposes of representation of a player's hand.
	 */
	public void printHand() {
		if(hand.isEmpty()) {
			System.out.println("You do not have a hand.");
			return;
		}
		
		System.out.println("You have the " + hand.get(0).toString() + " and the " + hand.get(1).toString());    
	}
	
	
	
	
	
}
