import java.util.ArrayList;

public class Player {
	
	private ArrayList<Card> hand;
	private ArrayList<Card> cards;
	private double chipCount;
	private int handStrength;
	private int bidAmount;
	private boolean handVisible;
	
	public Player(double chipCount) {
		handVisible = true;
		this.bidAmount = 0;
		this.chipCount = chipCount;
		hand = new ArrayList<Card>();
		cards = new ArrayList<Card>();
		this.handStrength = 0;
	}
	
	
	public int getHandStrength() {
		return handStrength;
	}
	
	
	public void allIn() {
		bidAmount += chipCount;
		chipCount = 0;
	}
	
	
	public void calcHandStrength() {
		handStrength = HandStrength.findHandStrength(cards);
	}
	
	
	public ArrayList<Card> getHand() {
		return hand;
	}
	
	
	public ArrayList<Card> getCards(){
		return cards;
	}
	
	
	public void addCardToList(Card c) {
		cards.add(c);
	}
	
	
	public void addCardToHand(Card c) {
		hand.add(c);
	}
	
	
	public boolean isHandVisible() {
		return handVisible;
	}
	
	
	public void setHandVisible(boolean visible) {
		handVisible = visible;
	}
	
	
	public double getChipCount() {
		return chipCount;
	}
	
	
	public void setChipCount(double chips) {
		chipCount = chips;
	}
	
	
	public void bid(double chips) {
		bidAmount += chips;
		chipCount -= chips;
	}
	
	
	public int getBidAmount() {
		return bidAmount;
	}
	
	
	public void clearBidAmount() {
		bidAmount = 0;
	}
	
	
	public void fold() {
		hand = new ArrayList<Card>();
		cards = new ArrayList<Card>();
	}
	
	
	public void printHand() {
		if(hand.isEmpty()) {
			System.out.println("You do not have a hand.");
			return;
		}
		
		System.out.println("You have the " + hand.get(0).toString() + " and the " + hand.get(1).toString());    
	}
	
	
	
	
	
}
