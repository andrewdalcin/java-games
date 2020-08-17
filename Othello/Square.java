package Othello;

import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;

public class Square {
	private Rectangle squareBody;
	private Disk disk;
	private int x;
	private int y;
	
	public Square(Color color, int squareSize, int x, int y) {
		setupInstances(color, squareSize, x, y);
	}
	
	public Square(Square savedSquare) {
		Rectangle savedRec = savedSquare.getSquareBody();
		this.squareBody = new Rectangle(savedRec.getX(), savedRec.getY(), savedRec.getWidth(), savedRec.getHeight());
		this.squareBody.setFill(savedRec.getFill());
		this.x = savedSquare.getXLoc();
		this.y = savedSquare.getYLoc();
		if (savedSquare.getDisk() != null) {
			this.disk = new Disk(savedSquare.getDisk());
		}
	}
	
	//setup Methods
	private void setupInstances(Color color, int squareSize, int x, int y) {
		squareBody = new Rectangle(squareSize * x , squareSize * y , squareSize, squareSize);
		squareBody.setFill(color);
		squareBody.setStroke(Color.BLACK);
		this.x = x;
		this.y = y;
	}
	
	//misc methods
	public void addDisk(Color color) {
		double diskSize = (squareBody.getHeight() / 2.) * (3. / 4.);
		disk = new Disk(color, (int) diskSize, x, y);
	}
	
	//set methods
	public void setSquareColor(Color color) {
		squareBody.setFill(color);
	}
	
	//get methods
	public Rectangle getSquareBody() {
		return squareBody;
	}
	
	public Disk getDisk() {
		return disk;
	}
	
	public int getXLoc() {
		return x;
	}
	
	public int getYLoc() {
		return y;
	}
}
