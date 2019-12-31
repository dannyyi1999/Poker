import java.util.ArrayList;

public class PokerRun {

	public static void main(String[] args) {
		ArrayList<Card> f = new ArrayList<Card>();
		ArrayList<Card> s = new ArrayList<Card>();
		
		f.add(new Card(1, "spade"));
		f.add(new Card(1, "diamond"));
		//f.add(new Card(2, "spade"));
		f.add(new Card(1, "heart"));
		s.add(new Card(1, "heart"));
		s.add(new Card(1, "club"));
		s.add(new Card(13, "club"));
		s.add(new Card(13, "club"));
		s.add(new Card(13, "club"));

		System.out.println("player 1 points: " + HandStrength.setHighCard(f));
		System.out.println("player 2 points: " + HandStrength.setHighCard(s));
		

		

	}


}
