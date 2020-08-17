package Tetris;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import javafx.scene.paint.Color;

public class T_Shape extends Tetrimino {
	private List<List<Square>> t_Shape;
	
	public T_Shape() {
		setupInstances();
		createTShape();
	}
	
	private void setupInstances() {
		t_Shape = new ArrayList<List<Square>>();
	}
	
	private void createTShape() {
		for (int i = 0; i < getNumTetriminoSquares() - 1; i++) {
			t_Shape.add(new ArrayList<Square>(Arrays.asList(new Square(Color.YELLOW))));
		}
		t_Shape.get(getNumTetriminoSquares() / 2 - 1).add(new Square(Color.YELLOW));
		setTetriminoBody(t_Shape);
	}
	
}