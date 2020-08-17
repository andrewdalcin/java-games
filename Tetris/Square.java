package Tetris;

import javafx.geometry.Point2D;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;

public class Square {
	private Rectangle squareBody;
	private Point2D boardLocation;
	
	public Square(Color color) {
		setupInstances();
		setLook(color);
	}
	
	private void setupInstances() {
		int squareWidth = Constants.APP_WIDTH / Constants.BOARD_ROW_SQUARES;
		int squareHeight = squareWidth;
		squareBody = new Rectangle(squareWidth, squareHeight);
	}
	
	//set methods
	private void setLook(Color color) {
		squareBody.setStroke(Color.BLACK);
		squareBody.setFill(color);
	}
	
	public void setBoardLocation(int x, int y) {
		boardLocation = new Point2D(x, y);
	}
	
	//get methods
	public Rectangle getSquareBody() {
		return squareBody;
	}
	
	public Point2D getBoardLocation() {
		return boardLocation;
	}
}

