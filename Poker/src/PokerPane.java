import javafx.scene.Group;
import javafx.scene.image.ImageView;

public class PokerPane extends Group{

	public final double CARD_WIDTH = 50;
	public final double CARD_HEIGHT = 75;
	
	public PokerModel model;
	public ImageView[][] playerCards;
	
	
	public PokerPane(PokerModel model) {
		playerCards = new ImageView[2][9];
		this.model = model;
	}
	
	
	
}
