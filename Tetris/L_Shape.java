package Tetris;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import javafx.scene.paint.Color;

public class L_Shape extends Tetrimino {
	private List<List<Square>> l_Shape;
	
	public L_Shape() {
		setupInstances();
		createLShape();
	}
	
	private void setupInstances() {
		l_Shape = new ArrayList<List<Square>>();
	}
	
	private void createLShape() {
		for (int i = 0; i < getNumTetriminoSquares() - 1; i++) {
			l_Shape.add(new ArrayList<Square>(Arrays.asList(new Square(Color.PURPLE))));
			if (i == getNumTetriminoSquares() - 2) {
				l_Shape.get(i).add(new Square(Color.PURPLE));
			}
		}
		setTetriminoBody(l_Shape);
	}
	
}