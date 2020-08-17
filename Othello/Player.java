package Othello;

import javafx.event.ActionEvent;
import javafx.scene.input.MouseEvent;
import javafx.scene.paint.Color;

abstract public class Player {
	private Game game;
	private Color pieceColor;
	
	public Player(Color pieceColor, Game game) {
		this.game = game;
		this.pieceColor = pieceColor;
	}
	
	public Player(Player savedPlayer, Game associatedSavedGame) {
		this.game = associatedSavedGame;
		this.pieceColor = savedPlayer.getPieceColor();
	}
	
	public abstract Square makeMove();
	
	//get methods
	public Color getPieceColor() {
		return pieceColor;
	}
	
	public Game getGame() {
		return game;
	}

}