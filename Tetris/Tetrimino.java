package Tetris;

import java.util.List;

public class Tetrimino {
	private int numSquares;
	private List<List<Square>> tetriminoBody;
	
	public Tetrimino() {
		setupInstances();
	}
	
	private void setupInstances() {
		numSquares = Constants.TETRIMINO_NUM_SQUARES;
	}
	
	//set methods
	public void setTetriminoBody(List<List<Square>> tetriminoBody) {
		this.tetriminoBody = tetriminoBody;
	}
	
	//get methods
	public int getNumTetriminoSquares() {
		return numSquares;
	}
	
	public List<List<Square>> getTetriminoBody() {
		return tetriminoBody;
	}
	
}