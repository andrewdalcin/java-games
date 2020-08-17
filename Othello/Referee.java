package Othello;

import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;

import javafx.scene.paint.Color;

public class Referee {
	private boolean isPlayer1Turn;
	private Player player1;
	private Player player2;
	
	public Referee(Player player1, Player player2) {
		this(player1, player2, true);
	}
	
	public Referee(Player player1, Player player2, boolean isPlayer1Turn) {
		this.isPlayer1Turn = isPlayer1Turn;
		this.player1 = player1;
		this.player2 = player2;
	}
	
	//misc method
	public void switchPlayer() {
		isPlayer1Turn = !isPlayer1Turn;
	}
	
	public boolean checkSandwich(int xLoc, int yLoc, int xTranslate, int yTranslate, Board board) {
		boolean isPotentialSandwich = false;
		xLoc += xTranslate;
		yLoc += yTranslate;
		while(xLoc >= 0 && xLoc < board.getBoardArray().get(0).size() && yLoc >= 0 && yLoc < board.getBoardArray().size() && board.getBoardArray().get(yLoc).get(xLoc).getDisk() != null) {
			Square currSquare = board.getBoardArray().get(yLoc).get(xLoc);
			if (getCurrPlayer().getPieceColor() == currSquare.getDisk().getDiskBody().getFill()) {
				return isPotentialSandwich;
			}
			isPotentialSandwich = true;
			xLoc += xTranslate;
			yLoc += yTranslate;
		}
		return false;
	}
	
	public boolean checkValidSpace(Square square, Board board) {
		if (square.getDisk() == null) {
			for (int yTranslate = -1; yTranslate <= 1; yTranslate++) {
				for (int xTranslate = -1; xTranslate <= 1; xTranslate++) {
					if (checkSandwich(square.getXLoc(), square.getYLoc(), xTranslate, yTranslate, board)) {
						return true;
					}
				}
			}
		}
		return false;
	} 
	
	public void highlightValidSpaces(Board board) {
		for (int row = 0; row < board.getBoardArray().size(); row++) {
			for (int col = 0; col < board.getBoardArray().get(0).size(); col++) {
				Square currSquare = board.getBoardArray().get(row).get(col);
				if (currSquare.getSquareBody().getFill() != Color.BLACK && currSquare.getDisk() == null) {
					if(checkValidSpace(currSquare, board)) {
						currSquare.getSquareBody().setFill(Color.GRAY);
					}
				}
			}
		}
	}
	
	public List<Square> returnValidSpaces(Board board) {
		List<Square> validSpaces = new LinkedList<Square>();
		for (int row = 0; row < board.getBoardArray().size(); row++) {
			for (int col = 0; col < board.getBoardArray().get(0).size(); col++) {
				Square currSquare = board.getBoardArray().get(row).get(col);
				if (currSquare.getSquareBody().getFill() != Color.BLACK && currSquare.getDisk() == null) {
					if(checkValidSpace(currSquare, board)) {
						validSpaces.add(currSquare);
					}
				}
			}
		}
		return validSpaces;
	}
	
	//get methods
	public Player getCurrPlayer() {
		if (isPlayer1Turn) {
			return player1;
		} else {
			return player2;
		}
	}
	
	public Player getPlayer1() {
		return player1;
	}
	
	public Player getPlayer2(){
		return player2;
	}
	
	public boolean getIsPlayer1Turn() {
		return isPlayer1Turn;
	}
	
}