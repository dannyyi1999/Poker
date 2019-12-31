import java.util.Scanner;

public class PokerGame {
	
	public static void main(String[] args) {
		start();
	}
	
	
	public static  void start() {
		Scanner in = new Scanner(System.in);
		System.out.println("Welcome to the game of Texas Holdem! Enter number of players: ");
		int players = in.nextInt();
		PokerModel model = new PokerModel(players, 200);
		
		for(int i = 0; i < 100; i++) {
			System.out.println("Round: " + i);
			model.dealHands();
			model.displayHands();
			model.addCardToBoard();
			model.addCardToBoard();
			model.addCardToBoard();
			model.addCardToBoard();
			model.addCardToBoard();
			model.displayBoard();
			model.displayHandStrengths();
			model.endRound();
		}
		
	}

}
