package Tetris;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import javafx.scene.paint.Color;

public class S_Shape extends Tetrimino {
	private List<List<Square>> s_Shape;
	
	public S_Shape() {
		setupInstances();
		createSShape();
	}
	
	private void setupInstances() {
		s_Shape = new ArrayList<List<Square>>();
	}
	
	private void createSShape() {
		s_Shape.add(new ArrayList<Square>(Arrays.asList(new Square(Color.WHITE))));
		for (int i = 0; i < getNumTetriminoSquares() / 2; i++) {
			s_Shape.get(0).add(new Square(Color.CYAN));
		}
		s_Shape.add(new ArrayList<Square>());
		for (int i = 0; i < getNumTetriminoSquares() / 2; i++) {
			s_Shape.get(1).add(new Square(Color.CYAN));
		}
		setTetriminoBody(s_Shape);
	}
}