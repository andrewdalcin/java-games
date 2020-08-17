package Tetris;

import java.util.List;

import javafx.animation.Animation;
import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Pos;
import javafx.scene.control.Label;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.util.Duration;

public class PaneOrganizer {
	private BorderPane root;
	private Pane boardPane;
	private Game game;
	private Timeline highScoreTimeline;
	
	public PaneOrganizer() {
		setupInstances();
		Label lbl = setupLabelPane();
		setupHighScoreTimeline(lbl);
		setupBoardInPane(game.getBoard());
		game.setTetrisPane(boardPane);
		game.setRoot(root);
	}
	
	private void setupInstances() {
		root = new BorderPane();
		game = new Game();
	}
	
	private void setupBoardInPane(Board board) {
		boardPane = new Pane();
		List<List<Square>> boardArray = board.getBoardArray();
		for (int row = 0; row < boardArray.size(); row++) {
			for (int col = 0; col < boardArray.get(0).size(); col++) {
				boardPane.getChildren().add(boardArray.get(row).get(col).getSquareBody());
			}
		}
		root.setCenter(boardPane);
	}
	
	private Label setupLabelPane() {
		HBox bottomBox = new HBox();
		Label lbl = new Label("High Score: " + game.getHighScore());
		lbl.setTextFill(Color.BLACK);
		lbl.setFont(new Font("Garamond", 42));
		bottomBox.setAlignment(Pos.CENTER);
		bottomBox.getChildren().add(lbl);
		bottomBox.setStyle("-fx-background-color: blue;");
		root.setBottom(bottomBox);
		return lbl;
	}
	
	private void setupHighScoreTimeline(Label lbl) {
		KeyFrame kf = new KeyFrame(Duration.seconds(.3), new HighScore(lbl));
		highScoreTimeline = new Timeline(kf);
		highScoreTimeline.setCycleCount(Animation.INDEFINITE);
		highScoreTimeline.play();
	}
	
	
	//get methods
	public Pane getRoot() {
		return root;
	}
	
	public Pane getBoardPane() {
		return boardPane;
	}
	
	//Event Handler Class
	private class HighScore implements EventHandler<ActionEvent> {
		private Label lbl;
		
		private HighScore(Label lbl) {
			this.lbl = lbl;
		}
		
		@Override
		public void handle(ActionEvent evt) {
			lbl.setText("High Score: " + game.getHighScore());
		}
	}
}
