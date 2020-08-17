package Othello;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Set;

import javafx.animation.Animation;
import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Pos;
import javafx.scene.control.Label;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Pane;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
import javafx.scene.text.Font;
import javafx.util.Duration;

public class Game {
	private Board board;
	private Pane gameBoardPane; 
	private Referee referee;
	private Label countLabel;
	private Settings settings;
	private Timeline computerTimeline;
	
	public Game() {
		board = new Board();
		this.gameBoardPane = new Pane();
		setupTileClicks();
	}
	
	public Game(Game savedGame){
		this.board = new Board(savedGame.getBoard()); //setup referee in paneOrganizer
	}
	
	private void setupTileClicks() {
		for (int row = 0; row < board.getBoardArray().size(); row++) {
			for (int col = 0; col < board.getBoardArray().size(); col++) {
				Square currSquare = board.getBoardArray().get(row).get(col);
				if (currSquare.getSquareBody().getFill() != Color.BLACK) {
					currSquare.getSquareBody().setOnMouseClicked(new clickTileHandler());
				}
			}
		}
	}
	
	public void setupTurn() {
		makeGraySquaresGreen();
		if (referee.getCurrPlayer() instanceof Human) {
			referee.highlightValidSpaces(board);
			referee.switchPlayer();
			if (referee.getCurrPlayer() instanceof Computer) {
				setupComputerTimeline();
			}
			referee.switchPlayer();
		} else if (referee.getCurrPlayer() instanceof Computer) {
			setupComputerTimeline();
		}
	}
	
	private void setupComputerTimeline() {
		KeyFrame kf = new KeyFrame(Duration.seconds(1),new computerTimeHandler());
		computerTimeline = new Timeline(kf);
		computerTimeline.setCycleCount(Animation.INDEFINITE);
		computerTimeline.play();
	}
	
	//update methods
	public void updateTurn() {
		updateColorCounts();
		referee.switchPlayer();
		if (referee.getCurrPlayer() instanceof Computer) {
				computerTimeline.play();
		} else if (referee.getCurrPlayer() instanceof Human) {
			if (computerTimeline != null) {
				computerTimeline.stop();
			}
		}
		updateBoard();
		updateLeftScreen();
		checkAndUpdateIfGameOver();
	}
	
	public void updateComputerTurn(Square currSquare, Referee referee) {
		if (currSquare != null) {
			this.referee = referee;
			currSquare.addDisk(referee.getCurrPlayer().getPieceColor());
			sandwichPieces(currSquare);
			makeGraySquaresGreen();
			updateColorCounts();
			referee.switchPlayer();
			referee.highlightValidSpaces(board);
		}
	}
	
	public void checkAndUpdateIfGameOver() {
		List<Square> validSpaces = referee.returnValidSpaces(board);
		if (validSpaces.isEmpty()) {
			Label gameOverLabel;
			if (board.getPiecesCount()[0] > board.getPiecesCount()[1]) {
				gameOverLabel = new Label("Black Wins");
			} else if (board.getPiecesCount()[0] < board.getPiecesCount()[1]) {
				gameOverLabel = new Label("White Wins");
			} else {
				gameOverLabel = new Label("Tied");
			}
			gameOverLabel.setFont(new Font("Garamond", 100));
			gameBoardPane.getChildren().clear();
			gameOverLabel.setTranslateX(Constants.APP_WIDTH / 6);
			gameOverLabel.setTranslateY(Constants.APP_HEIGHT / 3);
			gameBoardPane.getChildren().add(gameOverLabel);
			if (computerTimeline != null) {
				computerTimeline.stop();
			}
		}
	}
	
	public void updateLeftScreen() {
		gameBoardPane.getChildren().clear();
		for (int row = 0; row < board.getBoardArray().size(); row++) {
			for (int col = 0; col < board.getBoardArray().get(0).size(); col++) {
				Square currSquare = board.getBoardArray().get(row).get(col);
				gameBoardPane.getChildren().add(currSquare.getSquareBody());
				if (currSquare.getDisk() != null) {
					gameBoardPane.getChildren().add(currSquare.getDisk().getDiskBody());
				}
			}
		}
		updateCountLabel();
	}
	
	public void updateBoard() {
		makeGraySquaresGreen();
		if (referee.getCurrPlayer() instanceof Human) {
			referee.highlightValidSpaces(board);
		}
	}
	
	public void updateCountLabel() {
		countLabel.setText("White: " + board.getPiecesCount()[1] + ", Black: " + board.getPiecesCount()[0]);
	}
	
	
	//misc methods
	private void updateColorCounts() {
		int black = 0;
		int white = 0;
		for (int row = 0; row < board.getBoardArray().size(); row++) {
			for (int col = 0; col < board.getBoardArray().get(0).size(); col++) {
				if (board.getBoardArray().get(row).get(col).getDisk() != null) {
					if (board.getBoardArray().get(row).get(col).getDisk().getDiskBody().getFill() == Color.BLACK) {
						++black;
					} else {
						++white;
					}
				}
			}
		}
		board.getPiecesCount()[0] = black;
		board.getPiecesCount()[1] = white;
	}
	
	private void sandwichPath(int xLoc, int yLoc, int xTranslate, int yTranslate) {
		while (referee.getCurrPlayer().getPieceColor() != board.getBoardArray().get(yLoc).get(xLoc).getDisk().getDiskBody().getFill()) {
			board.getBoardArray().get(yLoc).get(xLoc).getDisk().getDiskBody().setFill(referee.getCurrPlayer().getPieceColor());
			xLoc += xTranslate;
			yLoc += yTranslate;
		}
	}
	
	private void sandwichPieces(Square square) { //only should be used if a checkValidSpace proves to be true
		for (int xTranslate = -1; xTranslate <= 1; xTranslate++) {
			for (int yTranslate = -1; yTranslate <= 1; yTranslate++) {
				if (referee.checkSandwich(square.getXLoc(), square.getYLoc(), xTranslate, yTranslate, board)) {
					sandwichPath(square.getXLoc() + xTranslate, square.getYLoc() + yTranslate, xTranslate, yTranslate);
				}
			}
		}
	}
	
	private void makeGraySquaresGreen() {
		for (int row = 0; row < board.getBoardArray().size(); row++) {
			for (int col = 0; col < board.getBoardArray().get(0).size(); col++) {
				Square currSquare = board.getBoardArray().get(row).get(col);
				if (currSquare.getSquareBody().getFill() == Color.GRAY) {
					currSquare.getSquareBody().setFill(Color.GREEN);
				}
			}
		}
	}
	
	
	//set methods
	public void setReferee(Referee referee) { //referee is set after settings have picked players
		this.referee = referee;
	}
	
	public void setCountLabel(Label countLabel) {
		this.countLabel = countLabel;
	}
	
	public void setComputerTimelineNull() {
		computerTimeline = null;
	}
	
	//get methods
	public Board getBoard() {
		return board;
	}
	
	public Pane getGameBoardPane() {
		return gameBoardPane;
	}
	
	public Settings getSettings() {
		return settings;
	}
	
	public Referee getReferee() {
		return referee;
	}
	
	//event handler classes
	
	private class computerTimeHandler implements EventHandler<ActionEvent> {
		
		private computerTimeHandler() {}
		
		@Override
		public void handle(ActionEvent evt) {
			if (referee.getCurrPlayer() instanceof Computer) {
				Square playerSquare = referee.getCurrPlayer().makeMove();
				if (playerSquare != null) {
					playerSquare.addDisk(referee.getCurrPlayer().getPieceColor());
					sandwichPieces(playerSquare);
					updateTurn();
				}
			}
		}
	}
	
	private class clickTileHandler implements EventHandler<MouseEvent>{
		
		private clickTileHandler() {
			
		}
		
		@Override
		public void handle(MouseEvent evt) {
			if (referee.getCurrPlayer() instanceof Human) {
				Human human = (Human) referee.getCurrPlayer();
				human.setSquareClicked(evt);
				Square playerSquare = referee.getCurrPlayer().makeMove();
				if (playerSquare != null) {
					playerSquare.addDisk(referee.getCurrPlayer().getPieceColor());
					sandwichPieces(playerSquare);
					updateTurn();
				}
			}
		}
	}
}
