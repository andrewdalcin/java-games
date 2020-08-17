package Tetris;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import javafx.scene.paint.Color;

public class Square_Shape extends Tetrimino {
	private List<List<Square>> square_Shape;
	
	public Square_Shape() {
		setupInstances();
		createSquareShape();
	}
	
	private void setupInstances() {
		square_Shape = new ArrayList<List<Square>>();
	}
	
	private void createSquareShape() {
		for (int row = 0; row < getNumTetriminoSquares() / 2; row++) {
			square_Shape.add(new ArrayList<Square>());
			for (int col = 0; col < getNumTetriminoSquares() / 2; col++) {
				square_Shape.get(row).add(new Square(Color.RED));
			}
		}
		setTetriminoBody(square_Shape);
	}
	
}