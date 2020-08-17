package Tetris;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import javafx.scene.paint.Color;

public class J_Shape extends Tetrimino {
	private List<List<Square>> j_Shape;
	
	public J_Shape() {
		setupInstances();
		createJShape();
	}
	
	private void setupInstances() {
		j_Shape = new ArrayList<List<Square>>();
	}
	
	private void createJShape() {
		j_Shape.add(new ArrayList<Square>(Arrays.asList(new Square(Color.BLUE))));
		j_Shape.add(new ArrayList<Square>());
		for (int i = 0; i < getNumTetriminoSquares() - 1; i++) {
			j_Shape.get(1).add(new Square(Color.BLUE));
		}
		setTetriminoBody(j_Shape);
	}
	
}
