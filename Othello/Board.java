package Othello;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import javafx.scene.paint.Color;

public class Board {
	private List<List<Square>> boardArray;
	private int[] piecesCount;
	
	public Board() {
		setupInstances();
		setupBoard();
		setupBorders();
		setupDisks();
	}
	
	public Board(Board savedBoard) {
		setupBoard(savedBoard);
		int blackPieces = savedBoard.piecesCount[0];
		int whitePieces = savedBoard.piecesCount[1];
		this.piecesCount = new int[2];
		this.piecesCount[0] = blackPieces;
		this.piecesCount[1] = whitePieces;
	}
	
	//setup methods
	private void setupInstances() {
		boardArray = new ArrayList<List<Square>>();
		piecesCount = new int[2];
	}
	
	public void setupBoard(Board savedBoard) { //for board array copy
		this.boardArray = new ArrayList<List<Square>>();
		for (int row = 0; row < Constants.NUM_BOARD_SQUARES_ROW + 2; row++) {
			this.boardArray.add(new ArrayList<Square>());
			for (int col = 0; col < Constants.NUM_BOARD_SQUARES_COL + 3; col++) {
				this.boardArray.get(row).add(new Square(savedBoard.getBoardArray().get(row).get(col)));
			}
		}
	}
	
	private void setupBoard() {
		for (int row = 0; row < Constants.NUM_BOARD_SQUARES_ROW + 2; row++) {
			boardArray.add(new ArrayList<Square>());
			for (int col = 0; col < Constants.NUM_BOARD_SQUARES_COL + 3; col++) {
				boardArray.get(row).add(new Square(Color.GREEN, Constants.SQUARE_HEIGHT, col, row));
			}
		}
	}
	
	private void setupBorders() {
		for (int row = 0; row < boardArray.size(); row++) {
			for (int col = 0; col < boardArray.get(0).size(); col++) {
				if (row == 0 || row == boardArray.size() - 1 || col == 0 || col == boardArray.get(0).size() - 1) {
					boardArray.get(row).get(col).setSquareColor(Color.BLACK);
				}
			}
		}
	}
	
	private void setupDisks() {
		for (int row = (boardArray.size() / 2) - 1; row < (boardArray.size() / 2) + 1; row++) {
				int startCol = (boardArray.get(0).size() / 2) - 1;
				if (row == (boardArray.size() / 2) - 1) {
					boardArray.get(row).get(startCol).addDisk(Color.BLACK);
					boardArray.get(row).get(startCol + 1).addDisk(Color.WHITE);
				} else {
					boardArray.get(row).get(startCol).addDisk(Color.WHITE);
					boardArray.get(row).get(startCol + 1).addDisk(Color.BLACK);
				}
				piecesCount[0] += 1;
				piecesCount[1] += 1;
		}
	}
	
	//get methods
	public List<List<Square>> getBoardArray() {
		return boardArray;
	}
	
	public int[] getPiecesCount() {
		return piecesCount;
	}
}
