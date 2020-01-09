

import javafx.application.Application;
import javafx.geometry.Pos;
import javafx.scene.Group;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.Menu;
import javafx.scene.control.MenuBar;
import javafx.scene.control.ScrollBar;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.TextField;
import javafx.scene.control.ToolBar;
import javafx.scene.image.ImageView;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Pane;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.text.Text;
import javafx.stage.Stage;



public class PokerGUI extends Application{
	
	private Stage window;

	@Override
	public void start(Stage primaryStage) throws Exception {
		window = primaryStage;
		window.setScene(mainMenuScene());
		window.show();
	}
	
	
	public Scene mainMenuScene() {
		VBox g = new VBox();
		Button start = new Button("start");
		start.setMaxSize(50, 20);
		start.setTranslateX(200 - start.getMaxWidth() / 2);
		start.setTranslateY(200 - start.getMaxHeight() / 2);
		System.out.println(start.getMaxWidth());
		g.getChildren().addAll(start);
		start.setOnAction((e)->{
			window.setScene(gameInfoScene());
		});
		
		Scene scene = new Scene(g, 400, 400);
		return scene;
	}
	
	
	public Scene gameInfoScene() {
		VBox v = new VBox(10);
		HBox h1 = new HBox(10);
		HBox h2 = new HBox(10);
		v.getChildren().addAll(h1, h2);
		
		Text invalid = new Text();
		Button start = new Button("Start");
		Button back = new Button("Back");
		invalid.setFill(Color.RED);
		
		v.setAlignment(Pos.CENTER);
		
		Text pBox = new Text("Number of Players");
		Text cBox = new Text("Starting Chips");
		
		TextField players = new TextField();
		TextField chips = new TextField();
		
		v.getChildren().addAll(start, back, invalid);
		
		players.setOnAction(e -> {
			try {
				int numPlayers = Integer.parseInt(players.getText());
				int numChips = Integer.parseInt(chips.getText());
				window.setScene(gameScene(numPlayers, numChips));
			}catch (Exception e1) {
				invalid.setText("Sorry, please input a valid integer");
			}
		});
		
		chips.setOnAction(e ->{
			try {
				int numPlayers = Integer.parseInt(players.getText());
				int numChips = Integer.parseInt(chips.getText());
				window.setScene(gameScene(numPlayers, numChips));
			}catch (Exception e1) {
				invalid.setText("Sorry, please input a valid integer");
			}
		});
		
		start.setOnAction(e ->{
			try {
				int numPlayers = Integer.parseInt(players.getText());
				int numChips = Integer.parseInt(chips.getText());
				window.setScene(gameScene(numPlayers, numChips));
			}catch (Exception e1) {
				invalid.setText("Sorry, please input a valid integer");
			}
		});
		
		back.setOnAction(e ->{
			window.setScene(mainMenuScene());
		});
		
		
		
		h1.getChildren().addAll(pBox, players);
		h2.getChildren().addAll(cBox, chips);
		
		
		Scene scene = new Scene(v, 400, 400);
		return scene;
	}
	
	
	public Scene gameScene(int numPlayers, int chips) {
		ImageView img = new ImageView("cards/10C.png");
		img.setOnMouseClicked(e -> {
			System.out.println("hi");
		});
		Scene scene = new Scene(new Group(img), 500, 500);
		return scene;
	}
	
	
	public static void main(String[] args) {
		launch(args);
	}

}
