package Pong;

import Border.LineBorder;
import Border.Point;
import Border.QuadBorder;
import javafx.scene.shape.Rectangle;

public class Borders {
	public QuadBorder appBorder;
	public QuadBorder rec1Border;
	public QuadBorder rec2Border;
		
	public Borders(Rectangle rec1, Rectangle rec2) {
		this.setupAppBorder();
		rec1Border = this.setupRecBorder(rec1);
		rec2Border = this.setupRecBorder(rec2);
	}
	
	public void setupAppBorder() {
		appBorder = new QuadBorder();
		double topLeftXPoint = 0;
		double topLeftYPoint = 0;
		double topRightXPoint = Constants.APP_WIDTH;
		double topRightYPoint = 0;
		double bottomRightXPoint = Constants.APP_WIDTH;
		double bottomRightYPoint = Constants.APP_HEIGHT;
		double bottomLeftXPoint = 0;
		double bottomLeftYPoint = Constants.APP_HEIGHT;
		// goes in cw direction from start -> end
		appBorder.leftBorder = new LineBorder(new Point(topLeftXPoint, topLeftYPoint), new Point(bottomLeftXPoint, bottomLeftYPoint));
		appBorder.bottomBorder = new LineBorder(new Point(bottomLeftXPoint, bottomLeftYPoint), new Point(bottomRightXPoint, bottomRightYPoint));
		appBorder.rightBorder = new LineBorder(new Point(bottomRightXPoint, bottomRightYPoint), new Point(topRightXPoint, topRightYPoint));
		appBorder.topBorder = new LineBorder(new Point(topRightXPoint, topRightYPoint), new Point(topLeftXPoint, topLeftYPoint));
	}
	
	public QuadBorder setupRecBorder(Rectangle rec) {
		QuadBorder recBorder = new QuadBorder();
		double topLeftXPoint = rec.getX();
		double topLeftYPoint = rec.getY();
		double topRightXPoint = topLeftXPoint + rec.getWidth();
		double topRightYPoint = topLeftYPoint;
		double bottomRightXPoint = topRightXPoint;
		double bottomRightYPoint = topRightYPoint + rec.getHeight();
		double bottomLeftXPoint = topLeftXPoint;
		double bottomLeftYPoint = bottomRightYPoint;
		// goes in cw direction from start -> end
		recBorder.leftBorder = new LineBorder(new Point(topLeftXPoint, topLeftYPoint), new Point(bottomLeftXPoint, bottomLeftYPoint));
		recBorder.bottomBorder = new LineBorder(new Point(bottomLeftXPoint, bottomLeftYPoint), new Point(bottomRightXPoint, bottomRightYPoint));
		recBorder.rightBorder = new LineBorder(new Point(bottomRightXPoint, bottomRightYPoint), new Point(topRightXPoint, topRightYPoint));
		recBorder.topBorder = new LineBorder(new Point(topRightXPoint, topRightYPoint), new Point(topLeftXPoint, topLeftYPoint));
		return recBorder;
	}
	
}
