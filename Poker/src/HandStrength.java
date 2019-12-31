import java.util.ArrayList;

public class HandStrength {
	
	private final static int BASE = 14;
	
	private final static int STRAIGHT_FLUSH_POINTS = 800000000;
	
	private final static int QUADS_POINTS = 700000000;
	
	private final static int FULL_HOUSE_POINTS = 600000000;
	
	private final static int FLUSH_POINTS = 500000000;
	
	private final static int STRAIGHT_POINTS = 400000000;
	
	private final static int SET_POINTS = 300000000;
	
	private final static int TWO_PAIR_POINTS = 200000000;
	
	private final static int PAIR_POINTS = 100000000;
	
	public static int findHandStrength(ArrayList<Card> cards) {
		
		if(cards.isEmpty()) {
			return 0;
		}
		
		if(isStraightFlush(cards)) {
			return straightFlushHighCard(cards) + STRAIGHT_FLUSH_POINTS;
		}else if(isQuads(cards)) {
			return quadsHighCard(cards) + QUADS_POINTS;
		}else if(isFullHouse(cards)) {
			return fullHouseHighCard(cards) + FULL_HOUSE_POINTS;
		}else if(isFlush(cards)) {
			return flushHighCard(cards) + FLUSH_POINTS;
		}else if(isStraight(cards)) {
			return straightHighCard(cards) + STRAIGHT_POINTS;
		}else if(isSet(cards)) {
			return setHighCard(cards) + SET_POINTS;
		}else if(isTwoPair(cards)) {
			return twoPairHighCard(cards) + TWO_PAIR_POINTS;
		}else if(isPair(cards)) {
			return pairHighCard(cards) + PAIR_POINTS;
		}else {
			return highCard(cards);
		}
		
	}
	
	
	public static boolean isStraightFlush(ArrayList<Card> cards) {
		
		if(cards.size() < 5) {
			return false;
		}
		
		//Since aces can act as the start or the end of a straight and aces
		//are initially ranked as 1, we will add another ace card in the arrayList
		//with value of 14, representing the end
	
		@SuppressWarnings("unchecked")
		ArrayList<Card> tCards = (ArrayList<Card>) cards.clone();
		addAceToLargest(tCards);
		
		//Sorting algorithm is stable, so cards are separated by suit, but each
		//suit is in ascending order
		sortByValue(tCards);
		sortBySuit(tCards);
		
		int chain = 1;
		for(int i = 1; i < tCards.size(); i++) {
			//If the next card is the next sequence for a straight flush, add one
			//to the chain. If we have a chain of 5, then a straight flush exists.
			if(tCards.get(i).getSuit().equals(tCards.get(i - 1).getSuit()) && 
			(tCards.get(i).getValue() - 1 == tCards.get(i - 1).getValue())){
				chain += 1;
			}else {
				chain = 1;
			}
			
			if(chain == 5) {
				return true;
			}
		}
		
		return false;
		
	}
	
	
	public static boolean isQuads(ArrayList<Card> cards) {
		
		if(cards.size() < 4) {
			return false;
		}
		
		//creating an array for all 13 cards, then looping through the input cards
		//and incrementing the respective location in the array to keep track of repeats.
		int[] repeats = new int[13];
		for(int i = 0; i < cards.size(); i++) {
			repeats[cards.get(i).getValue() - 1] += 1;
		}
		
		for(int i = 0; i < repeats.length; i++) {
			if(repeats[i] == 4) {
				return true;
			}
		}
		return false;
		
	}
	
	
	public static boolean isFullHouse(ArrayList<Card> cards) {
		
		if(cards.size() < 5) {
			return false;
		}
		
		//same as finding quads, but now we need to check for a 3 of a kind and a pair.
		int[] repeats = new int[13];
		for(int i = 0; i < cards.size(); i++) {
			repeats[cards.get(i).getValue() - 1] += 1;
		}
		
		boolean set = false;
		boolean pair = false;
		for(int i = 0; i < repeats.length; i++) {
			if(repeats[i] == 3) {
				set = true;
			}else if(repeats[i] == 2) {
				pair = true;
			}
		}
		return set && pair;
	}
	

	public static boolean isFlush(ArrayList<Card> cards) {
		
		if(cards.size() < 5) {
			return false;
		}
		
		sortBySuit(cards);
		int chain = 1;
		for(int i = 1; i < cards.size(); i++) {
			if(cards.get(i).getSuit().equals(cards.get(i - 1).getSuit())){
				chain += 1;
			}else {
				chain = 1;
			}
			
			if(chain == 5) {
				return true;
			}
		}
		
		return false;
	}
	
	
	public static boolean isStraight(ArrayList<Card> cards) {
		
		if(cards.size() < 5) {
			return false;
		}
		
		//similar in the comment relating to straight flushes, we need to add
		// the back end of the aces.
		@SuppressWarnings("unchecked")
		ArrayList<Card> tCards = (ArrayList<Card>) cards.clone();
		addAceToLargest(tCards);
		
		sortByValue(tCards);
		
		int chain = 1;
		for(int i = 1; i < tCards.size(); i++) {
			if(tCards.get(i).getValue() - 1 == tCards.get(i - 1).getValue()) {
				chain += 1;
			}else if(tCards.get(i).getValue() == tCards.get(i - 1).getValue()) {
				tCards.remove(i);
				i--;
			}else {
				chain = 1;
			}
			
			if(chain == 5) {
				return true;
			}
		}
		
		return false;
	}
	
	
	public static boolean isSet(ArrayList<Card> cards) {
		
		if(cards.size() < 3) {
			return false;
		}
		
		//same as quads, but a set this time.
		int[] repeats = new int[13];
		for(int i = 0; i < cards.size(); i++) {
			repeats[cards.get(i).getValue() - 1] += 1;
		}
		
		for(int i = 0; i < repeats.length; i++) {
			if(repeats[i] >= 3) {
				return true;
			}
		}
		return false;

	}
	
	
	public static boolean isTwoPair(ArrayList<Card> cards) {
		
		if(cards.size() < 4) {
			return false;
		}
		
		//same as quads, but two pairs this time.
		int[] repeats = new int[13];
		for(int i = 0; i < cards.size(); i++) {
			repeats[cards.get(i).getValue() - 1] += 1;
		}
		
		int numPairs = 0;
		for(int i = 0; i < repeats.length; i++) {
			if(repeats[i] >= 2) {
				numPairs += 1;
			}
		}
		return numPairs > 1;
	}
	
	
	public static boolean isPair(ArrayList<Card> cards) {
		
		if(cards.size() < 2) {
			return false;
		}
		
		int[] repeats = new int[13];
		for(int i = 0; i < cards.size(); i++) {
			repeats[cards.get(i).getValue() - 1] += 1;
			if(repeats[cards.get(i).getValue() - 1] == 2) {
				return true;
			}
		}
		
		return false;
	}
	
	
	public static boolean isHighCard(ArrayList<Card> cards) {
		if(cards.size() == 0) {
			return false;
		}
		return true;
	}
	
	
	public static int straightFlushHighCard(ArrayList<Card> cards) {
		
		int value = 0;
		
		@SuppressWarnings("unchecked")
		ArrayList<Card> tCards = (ArrayList<Card>) cards.clone();
		addAceToLargest(tCards);
		sortByValue(tCards);
		sortBySuit(tCards);
		
		int chain = 1;
		for(int i = tCards.size() - 1; i > 3; i--) {
			if(tCards.get(i).getSuit().equals(tCards.get(i - 1).getSuit()) &&
					tCards.get(i).getValue() - 1 == tCards.get(i - 1).getValue()) {
				chain += 1;
			}else {
				chain = 1;
			}
			
			if(chain == 5) {
				value += tCards.get(i + 4).getValue();
			}
		}
		
		return value;
	}
	
	
	public static int quadsHighCard(ArrayList<Card> cards) {
		
		int value = 0;
		
		@SuppressWarnings("unchecked")
		ArrayList<Card> tCards = (ArrayList<Card>) cards.clone();
		replaceAceToLargest(tCards);
		sortByValue(tCards);
		
		for(int i = 0; i < tCards.size() - 3; i++) {
			if(tCards.get(i).getValue() == tCards.get(i + 1).getValue() &&
					tCards.get(i).getValue() == tCards.get(i + 2).getValue() &&
					tCards.get(i).getValue() == tCards.get(i + 3).getValue()) {
				value += tCards.get(i).getValue();
				break;
			}
			
		}
		return value;
	}
	
	
	public static int fullHouseHighCard(ArrayList<Card> cards) {
		
		int value = 0;
		
		@SuppressWarnings("unchecked")
		ArrayList<Card> tCards = (ArrayList<Card>) cards.clone();
		replaceAceToLargest(tCards);
		sortByValue(tCards);
		
		for(int i = tCards.size() - 1; i > 1; i--) {
			if(tCards.get(i).getValue() == tCards.get(i - 1).getValue() &&
					tCards.get(i).getValue() == tCards.get(i - 1).getValue()) {
				value += tCards.get(i).getValue() * Math.pow(BASE, 4);
				tCards.remove(i);
				tCards.remove(i - 1);
				tCards.remove(i - 2);
				break;
			}
		}
		
		for(int i = tCards.size() - 1; i > 0; i--) {
			if(tCards.get(i).getValue() == tCards.get(i - 1).getValue()) {
				value += tCards.get(i).getValue();
				break;
			}
		}
		
		
		return value;
	}
	
	
	public static int flushHighCard(ArrayList<Card> cards) {
		
		int value = 0;
		@SuppressWarnings("unchecked")
		ArrayList<Card> tCards = (ArrayList<Card>) cards.clone();
		replaceAceToLargest(tCards);
		sortByValue(tCards);
		sortBySuit(tCards);
		
		for(int i = tCards.size() - 1; i > 3; i--) {
			if(tCards.get(i).getSuit().equals(tCards.get(i - 1).getSuit()) &&
					tCards.get(i).getSuit().equals(tCards.get(i - 2).getSuit()) &&
					tCards.get(i).getSuit().equals(tCards.get(i - 3).getSuit()) &&
					tCards.get(i).getSuit().equals(tCards.get(i - 4).getSuit())) {
				for(int x = i; x > i - 5; x--) {
					value += tCards.get(x).getValue() * Math.pow(BASE, x - i + 4);
				}
				break;
				
			}
		}
		
		return value;
		
	}
	
	
	public static int straightHighCard(ArrayList<Card> cards) {
		
		int value = 0;
		
		@SuppressWarnings("unchecked")
		ArrayList<Card> tCards = (ArrayList<Card>) cards.clone();
		addAceToLargest(tCards);
		removeDuplicates(tCards);
		
		for(int i = tCards.size() - 1; i > 3; i--) {
			if(tCards.get(i).getValue() - 1 == tCards.get(i - 1).getValue() &&
					tCards.get(i).getValue() - 2 == tCards.get(i - 2).getValue() &&
					tCards.get(i).getValue() - 3 == tCards.get(i - 3).getValue() &&
					tCards.get(i).getValue() - 4 == tCards.get(i - 4).getValue()) {

				value += tCards.get(i).getValue();
				break;
			}
		}
		
		return value;
	}
	
	
	public static int setHighCard(ArrayList<Card> cards) {
		
		int value = 0;
		
		@SuppressWarnings("unchecked")
		ArrayList<Card> tCards = (ArrayList<Card>) cards.clone();
		replaceAceToLargest(tCards);
		sortByValue(tCards);
		
		for(int i = tCards.size() - 1; i > 1; i--) {
			if(tCards.get(i).getValue() == tCards.get(i - 1).getValue() &&
					tCards.get(i).getValue() == tCards.get(i - 2).getValue()) {
				value += tCards.get(i).getValue() * (Math.pow(BASE, 4));
				tCards.remove(i);
				tCards.remove(i - 1);
				tCards.remove(i - 2);
				break;
			}
		}
		
		if(tCards.size() > 1) {
			return value + tCards.get(tCards.size() - 1).getValue() * 14 + 
					tCards.get(tCards.size() - 2).getValue();
		}else if(tCards.size() == 1) {
			return value + tCards.get(tCards.size() - 2).getValue() * 14;
		}else {
			return value;
		}
		
	}
	
	
	public static int twoPairHighCard(ArrayList<Card> cards) {
		
		int value = 0;
		
		@SuppressWarnings("unchecked")
		ArrayList<Card> tCards = (ArrayList<Card>) cards.clone();
		replaceAceToLargest(tCards);
		sortByValue(tCards);
		
		int numPairs = 0;
		for(int i = tCards.size() - 1; i > 0; i--) {
			if(tCards.get(i).getValue() == tCards.get(i - 1).getValue()) {
				value += tCards.get(i).getValue() * (Math.pow(BASE, 4 - numPairs));
				tCards.remove(i);
				tCards.remove(i - 1);
				i--;
				numPairs += 1;
				
				if(numPairs == 2) {
					break;
				}
			}
		}
		
		if(tCards.isEmpty()) return value;
		return value + tCards.get(tCards.size() - 1).getValue();
		
	}
	
	
	public static int pairHighCard(ArrayList<Card> cards) {
		
		int value = 0;
		
		@SuppressWarnings("unchecked")
		ArrayList<Card> tCards = (ArrayList<Card>) cards.clone();
		replaceAceToLargest(tCards);
		sortByValue(tCards);
		
		for(int i = tCards.size() - 1; i > 0; i--) {
			if(tCards.get(i).getValue() == tCards.get(i - 1).getValue()) {
				value += tCards.get(i).getValue() * (Math.pow(BASE, 4));
				tCards.remove(i);
				tCards.remove(i - 1);
				break;
			}
		}
		
		for(int i = tCards.size() - 1; i >= 0 && i > tCards.size() - 4; i--) {
			value += tCards.get(i).getValue() * Math.pow(BASE, i - tCards.size() + 3);
		}
		
		return value;
	}

	
	public static int highCard(ArrayList<Card> cards) {
		
		@SuppressWarnings("unchecked")
		ArrayList<Card> tCards = (ArrayList<Card>) cards.clone();
		replaceAceToLargest(cards);
		
		sortByValue(tCards);
		int value = 0;
		for(int i = tCards.size() - 1; i > tCards.size() - 6 && i >= 0; i--) {
			value += tCards.get(i).getValue() * Math.pow(BASE, i - tCards.size() + 5);
		}
		return value;
	}
	
	
	public static void sortByValue(ArrayList<Card> cards){
		for(int i = 0; i < cards.size() - 1; i++) {
			for(int j = 0; j < cards.size() - i - 1; j++) {
				if(cards.get(j).getValue() > cards.get(j + 1).getValue()) {
					Card temp = cards.get(j);
					cards.set(j, cards.get(j + 1));
					cards.set(j + 1, temp);
				}
			}
		}
	}
	
	
	
	public static void sortBySuit(ArrayList<Card> cards) {
		for(int i = 0; i < cards.size() - 1; i++) {
			for(int j = 0; j < cards.size() - i - 1; j++) {
				if(cards.get(j).getSuit().compareTo(cards.get(j + 1).getSuit()) > 0) {
					Card temp = cards.get(j);
					cards.set(j, cards.get(j + 1));
					cards.set(j + 1, temp);
				}
			}
		}
	}
	
	private static void replaceAceToLargest(ArrayList<Card> cards) {
		for(int i = cards.size() - 1; i >= 0; i--) {
			if(cards.get(i).getValue() == 1) {
				cards.add(new Card(14,cards.get(i).getSuit()));
				cards.remove(i);
			}
		}
	}
	
	private static void addAceToLargest(ArrayList<Card> cards) {
		for(int i = cards.size() - 1; i >= 0; i--) {
			if(cards.get(i).getValue() == 1) {
				cards.add(new Card(14,cards.get(i).getSuit()));
			}
		}
	}
	
	private static void removeDuplicates(ArrayList<Card> cards) {
		sortByValue(cards);
		for(int i = cards.size() - 2; i >= 0; i--) {
			if(cards.get(i).getValue() == cards.get(i + 1).getValue()) {
				cards.remove(i);
			}
		}
	}
	
}
