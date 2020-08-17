package Othello;

import java.util.Iterator;
import java.util.List;

import javafx.scene.input.MouseEvent;
import javafx.scene.paint.Color;

public class Computer extends Player {
	private int difficulty;
	private PaneOrganizer paneOrganizer;
	
	public Computer(Color color, int difficulty, Game game) {
		super(color, game);
		this.difficulty = difficulty;
	}
	
	public Computer(Computer savedComputer, Game associatedSavedGame) {
		super(savedComputer, associatedSavedGame);
		this.difficulty = savedComputer.getDifficulty();
	}
	
	private int minMax(int difficulty, int start, Square prevMoveSquare, Game game, Referee referee) {
		//checks current player
		int numPieces;
		int numOpponentPieces;
		if (referee.getIsPlayer1Turn()) {
			numPieces = game.getBoard().getPiecesCount()[1];
			numOpponentPieces = game.getBoard().getPiecesCount()[0];
		} else {
			numPieces = game.getBoard().getPiecesCount()[0];
			numOpponentPieces = game.getBoard().getPiecesCount()[1];
		}
		List<Square> validSpaces = referee.returnValidSpaces(game.getBoard());
		if (validSpaces.isEmpty() || start == difficulty) {
			return numPieces - numOpponentPieces;
		}
		//makes copy of game and switches player
		PaneOrganizer newOrganizer = new PaneOrganizer(game);
		Game newGame = newOrganizer.getGame();
		Referee newReferee = newGame.getReferee();
		Square newPrevMoveSquare = newGame.getBoard().getBoardArray().get(prevMoveSquare.getYLoc()).get(prevMoveSquare.getXLoc());
		newGame.updateComputerTurn(newPrevMoveSquare, newReferee); //switches player from previous move
		Board newBoard = newGame.getBoard();
		List<Square> newValidSpaces = newReferee.returnValidSpaces(newBoard);
		Iterator<Square> valids = newValidSpaces.iterator();
		int bestSquareWeight = 0;
		while (valids.hasNext()) {
			Square currSquare = valids.next();
			int currSquareWeight = minMax(difficulty, start + 1, currSquare, newGame, newReferee);
			if (bestSquareWeight == 0 || currSquareWeight > bestSquareWeight) {
				bestSquareWeight = currSquareWeight;
			}
		}
		return -1 * bestSquareWeight;
	}
	
	/* public SquareWeight minMax (int difficulty, int start) {
	
	}*/
	
	@Override
	 public Square makeMove() {
		Computer comp = (Computer) super.getGame().getReferee().getCurrPlayer();
		List<Square> validSpaces = super.getGame().getReferee().returnValidSpaces(super.getGame().getBoard());
		Iterator<Square> valids = validSpaces.iterator();
		Square bestSquare = null;
		int bestSquareWeight = 0;
		while (valids.hasNext()) {
			Square currSquare = valids.next();
			int currSquareWeight = minMax(comp.getDifficulty(), 1, currSquare, super.getGame(), super.getGame().getReferee());
			if (bestSquare == null || currSquareWeight > bestSquareWeight) {
				bestSquare = currSquare;
				bestSquareWeight = currSquareWeight;
			}
		}
		return bestSquare;
	} 
	
	/* public Square makeMove() {
		return super.getGame().getBoard().getBoardArray().get(0).get(0);
	} */
	
	//get methods
	public int getDifficulty() {
		return difficulty;
	}
	
	
}
