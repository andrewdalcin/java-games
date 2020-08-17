package Tetris;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import javafx.scene.paint.Color;

public class Line_Shape extends Tetrimino {
	private List<List<Square>> line_Shape;
	
	public Line_Shape() {
		setupInstances();
		createLineShape();
	}
	
	private void setupInstances() {
		line_Shape = new ArrayList<List<Square>>();
	}
	
	private void createLineShape() {
		for (int i = 0; i < getNumTetriminoSquares(); i++) {
			line_Shape.add(new ArrayList<Square>(Arrays.asList(new Square(Color.ORANGE))));
		}
		setTetriminoBody(line_Shape);
	}
	
}