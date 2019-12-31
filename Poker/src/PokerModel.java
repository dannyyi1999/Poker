import java.util.ArrayList;

public class PokerModel {
	private int playerCounter;
	private int bbCounter;
	private int sbCounter;
	private int bbValue;
	private int sbValue;
	
	private int pot = 0;
	private int highestBid = 0;
	private Deck deck;
	private ArrayList<Player> players;
	private ArrayList<Card> board;
	
	public PokerModel(int numPlayers, double startingChip) {
		this.bbValue = 2;
		this.sbValue = bbValue / 2;
		this.sbCounter = 0;
		this.bbCounter = 1;
		this.playerCounter = bbCounter;
		
		this.deck = new Deck();
		this.board = new ArrayList<Card>();
		this.players = new ArrayList<Player>();
		
		incrementPlayerCounter();
		
		for(int i = 0; i < numPlayers; i++) {
			players.add(new Player(startingChip));
		}
		
	}
	
	
	public int getPlayerCounter() {
		return playerCounter;
	}
	
	
	public void decrementPlayerCounter() {
		if(playerCounter - 1 < 0) {
			playerCounter = players.size() - 1;
			return;
		}
		
		playerCounter -= 1;
	}
	
	
	public void incrementPlayerCounter() {
		if(playerCounter + 1 > players.size()) {
			playerCounter = 0;
			return;
		}
		playerCounter += 1;
	}
	
	
	public void startRound() {
		forceBlindStakes();
		dealHands();
	}
	
	
	public void forceBlindStakes() {
		players.get(sbCounter).bid(sbValue);
		players.get(bbCounter).bid(bbValue);
		highestBid = bbValue;
	}
	
	
	public void incrementBlinds() {
		
		if(bbCounter + 1 == players.size()) {
			bbCounter = 0;
		}else {
			bbCounter += 1;
		}
		
		if(sbCounter + 1 == players.size()) {
			sbCounter = 0;
		}else {
			sbCounter += 1;
		}
		
	}
	
	
	public int getPot() {
		return pot;
	}
	
	
	public int getHighestBid() {
		return highestBid;
	}
	
	
	public void setHighestBid(int bid) {
		this.highestBid = bid;
	}
	
	
	public void collectBids(){
		for(int i = 0; i < players.size(); i++) {
			pot += players.get(i).getBidAmount();
			players.get(i).clearBidAmount();
		}
	}
	
	
	public boolean isGameOver() {
		if(players.size() <= 1) {
			return true;
		}
		return false;
	}
	
	
	
	public void addCardToBoard() {
		Card card = deck.pop();
		board.add(card);
		for(int i = 0; i < players.size(); i++) {
			players.get(i).addCardToList(card);
		}
	}
	
	public void dealHands() {
		for(int i = 0; i < players.size(); i++) {
			Card c = deck.pop();
			players.get(i).addCardToHand(c);
			players.get(i).addCardToList(c);
			c = deck.pop();
			players.get(i).addCardToHand(c);
			players.get(i).addCardToList(c);
		}
	}
	
	public void endRound() {
		for(int i = players.size() - 1; i >= 0; i--) {
			if(players.get(i).getChipCount() <= 0) {
				players.remove(i);
				continue;
			}
			players.get(i).fold();
		}
		board = new ArrayList<Card>();
		deck = new Deck();
	}
	
	
	public Player winningPlayer() {
		int currentHigh = 0;
		for(int i = 1; i < players.size(); i++) {
			if(HandStrength.findHandStrength(players.get(i).getCards()) >
			HandStrength.findHandStrength(players.get(currentHigh).getCards())) {
				currentHigh = i;
			}
		}
		
		return players.get(currentHigh);
	}
	
	
	public ArrayList<Player> getPlayers(){
		return players;
	}
	
	
	public ArrayList<Card> getBoard() {
		return board;
	}
	
	
	public void displayHands() {
		for(int i = 0; i < players.size(); i++) {
			System.out.print("Player " + i + ": ");
			players.get(i).printHand();
		}
	}
	
	
	public void displayHandStrengths() {
		for(int i = 0; i < players.size(); i++) {
			System.out.print("Player " + i + " hand strength: ");
			System.out.println(HandStrength.findHandStrength(players.get(i).getCards()));
		}
	}
	
	
	public void displayAllPlayerChips() {
		for(int i = 0; i < players.size(); i++) {
			System.out.println("Player " + i + " has " + players.get(i).getChipCount() + " chips.");       
		}
	}
	
	
	public void displayBoard() {
		if(board.isEmpty()) {
			System.out.println("There are no cards on the table.");
			return;
		}
		
		System.out.println("The cards on the board are as follows: ");
		for(int i = 0; i < board.size(); i++) {
			System.out.println(board.get(i).toString());
		}
	}
	
	
	
}
