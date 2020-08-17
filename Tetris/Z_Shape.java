package Tetris;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import javafx.scene.paint.Color;

public class Z_Shape extends Tetrimino {
	private List<List<Square>> z_Shape;
	
	public Z_Shape() {
		setupInstances();
		createZShape();
	}
	
	private void setupInstances() {
		z_Shape = new ArrayList<List<Square>>();
	}
	
	private void createZShape() {
		z_Shape.add(new ArrayList<Square>());
		for (int i = 0; i < getNumTetriminoSquares() / 2; i++) {
			z_Shape.get(0).add(new Square(Color.GREEN));
		}
		z_Shape.add(new ArrayList<Square>(Arrays.asList(new Square(Color.WHITE))));
		for (int i = 0; i < getNumTetriminoSquares() / 2; i++) {
			z_Shape.get(1).add(new Square(Color.GREEN));
		}
		setTetriminoBody(z_Shape);
	}
	
}
