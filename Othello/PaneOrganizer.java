package Othello;

import java.util.HashSet;
import java.util.Hashtable;
import java.util.Map;
import java.util.Set;

import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.control.CheckBox;
import javafx.scene.control.Label;
import javafx.scene.control.RadioButton;
import javafx.scene.control.ToggleGroup;
import javafx.scene.layout.Background;
import javafx.scene.layout.BackgroundFill;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Pane;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;

public class PaneOrganizer {
	private BorderPane root;
	private Game game;
	
	public PaneOrganizer() {
		setupInstances();
		setupRightScreen();
		setupLeftScreen();
	}
	
	public PaneOrganizer(Game savedGame) {
		Game newSavedGame = new Game(savedGame);
		this.game = newSavedGame;
		Player newSavedPlayer1;
		Player newSavedPlayer2;
		if (savedGame.getReferee().getPlayer1() instanceof Human) {
			newSavedPlayer1 = new Human((Human)savedGame.getReferee().getPlayer1(), newSavedGame);
		} else {
			newSavedPlayer1 = new Computer((Computer) savedGame.getReferee().getPlayer1(), newSavedGame);
		}
		if (savedGame.getReferee().getPlayer2() instanceof Human) {
			newSavedPlayer2 = new Human((Human)savedGame.getReferee().getPlayer2(), newSavedGame);
		} else {
			newSavedPlayer2 = new Computer((Computer)savedGame.getReferee().getPlayer2(), newSavedGame);
		}
		Referee newReferee = new Referee(newSavedPlayer1, newSavedPlayer2, savedGame.getReferee().getIsPlayer1Turn());
		newSavedGame.setReferee(newReferee);
	}
	
	//setup methods
	private void setupInstances() {
		root = new BorderPane();
		root.requestFocus();
		root.setFocusTraversable(true);
		game = new Game();
	}
	
	private void setupLeftScreen() {
		game.updateLeftScreen();
		root.setLeft(game.getGameBoardPane());
	}
	
	private void setupRightScreen() {
		Settings settings = new Settings(game);
		root.setRight(settings.getSettingsPane());
	}
	
	//get methods
	public BorderPane getRoot() {
		return root;
	}
	
	public Game getGame() {
		return game;
	}
	
}
