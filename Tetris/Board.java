package Tetris;

import java.util.ArrayList;
import java.util.List;

import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;

public class Board {
	private Tetrimino currTetrimino; //tetrimino currently falling
	private List<List<Square>> board;
	
	public Board() {
		setupInstances();
		setupBoard();
		setRandomTetrimino();
		placeTetriminoInBoard();
	}
	
	//setup methods
	private void setupInstances() {
		board = new ArrayList<List<Square>>();
	}
	
	public void setupBoard() {
		for (int row = 0; row < Constants.BOARD_COL_SQUARES; row++) {
			board.add(new ArrayList<Square>());
			for (int col = 0; col < Constants.BOARD_ROW_SQUARES; col++) {
				Square square = new Square(Color.WHITE);
				square.getSquareBody().setX(col * Constants.SQUARE_WIDTH);
				square.getSquareBody().setY(row * Constants.SQUARE_HEIGHT);
				board.get(row).add(square);
			}
		}
	}
	
	public void placeTetriminoInBoard() {
		List<List<Square>> tetriminoSquares = currTetrimino.getTetriminoBody();
		int startPosX = board.get(0).size() / 2 - 1;
		int startPosY = 0;
		for (int row = startPosY; row < startPosY + tetriminoSquares.size(); row++) {
			for (int col = startPosX; col < startPosX + tetriminoSquares.get(row - startPosY).size(); col++) { //XX if board is not even size then bug happens
				Square tetriminoSquare = tetriminoSquares.get(row - startPosY).get(col - startPosX);
				tetriminoSquare.getSquareBody().setX(col * Constants.SQUARE_WIDTH);
				tetriminoSquare.getSquareBody().setY(row * Constants.SQUARE_HEIGHT);
				tetriminoSquare.setBoardLocation(col, row);
				board.get(row).set(col, tetriminoSquare);
			}
		}
	}
	
	private void changeBoardSquare(Rectangle tetriminoSquare, int row, int col) {
		Rectangle boardSquare = board.get(row).get(col).getSquareBody();
		boardSquare.setFill(tetriminoSquare.getFill());
		boardSquare.setX(tetriminoSquare.getX());
		boardSquare.setY(tetriminoSquare.getY());
	}
	
	//set methods
	public void setRandomTetrimino() {
		SetTetrimino setTetrimino = new SetTetrimino(Math.random());
		currTetrimino = setTetrimino.getTetrimino(); 
	}
	
	//get methods
		public Tetrimino getCurrTetrimino() {
			return currTetrimino;
		}
		
		public List<List<Square>> getBoardArray() {
			return board;
		}
}
