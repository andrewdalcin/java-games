package TicTacToe;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

import javafx.event.EventHandler;
import javafx.scene.input.MouseEvent;
import javafx.scene.paint.Color;
import javafx.scene.shape.Line;

public class X {
	private boolean isPlayer2;
	private List<List<SquareBorder>> squareBorders;
	private Set<SquareBorder> hashSquareBorders;
	
	public X() {
		hashSquareBorders = new HashSet<SquareBorder>();
	}
	
	public List<List<SquareBorder>> getSquareBorders() {
		return squareBorders;
	}
	
	public void drawX(SquareBorder squareBorder, boolean isPlayer2) {
		
		Point topLeftPoint = new Point(squareBorder.leftBorder.start.get(0), squareBorder.leftBorder.start.get(1));
		Point bottomLeftPoint = new Point(squareBorder.leftBorder.end.get(0), squareBorder.leftBorder.end.get(1));
		Point topRightPoint = new Point(squareBorder.rightBorder.start.get(0), squareBorder.rightBorder.start.get(1));
		Point bottomRightPoint = new Point(squareBorder.rightBorder.end.get(0), squareBorder.rightBorder.end.get(0));
		Line line1 = new Line(topLeftPoint.x, topLeftPoint.y, bottomRightPoint.x, bottomRightPoint.y);
		Line line2 = new Line(bottomLeftPoint.x, bottomLeftPoint.y, topRightPoint.x, topRightPoint.y);
		// changes color of x depending on whose turn it is
		if (isPlayer2) {
			line1.setStroke(Color.RED);
			line2.setStroke(Color.RED);
		} else {
			line1.setStroke(Color.BLUE);
			line2.setStroke(Color.BLUE);
		}
		
		if (isPlayer2) {
			squareBorder.filledByPlayer2 = true;
		} else {
			squareBorder.filledByPlayer1 = true;
		}
		isPlayer2 = !isPlayer2;
	}
	
	public SquareBorder getSquare(List<List<SquareBorder>> squareBorders, MouseEvent evt) {
		double x = evt.getSceneX();
		double y = evt.getSceneY();
		for (int row = 0; 0 < squareBorders.size(); row++) {
			for (int col = 0; 0 < squareBorders.get(0).size(); col++) {
				SquareBorder squareBorder = squareBorders.get(row).get(col);
				double yMin = squareBorder.leftBorder.start.get(1);
				double yMax = squareBorder.leftBorder.end.get(1);
				double xMin = squareBorder.leftBorder.start.get(0);
				double xMax = squareBorder.leftBorder.end.get(0);
				if (x > xMin && x < xMax && y > yMin && y < yMax) {
					return squareBorder;
				}
			}
		}
		return null;
	}
	
	public class MouseClicker implements EventHandler<MouseEvent> {
		
		public MouseClicker() {}
		
		@Override
		public void handle(MouseEvent evt) {
			X draw = new X();
			boolean alreadyDrawn = false;
			SquareBorder squareBorder = draw.getSquare(squareBorders, evt);
			if (squareBorder != null) {
				if (hashSquareBorders.contains(squareBorders)) {
					alreadyDrawn = true;
				} else {
					hashSquareBorders.add(squareBorder);
					squareBorder.isFilled = true;
				}
			}
			if (alreadyDrawn){
				draw.drawX(squareBorder, isPlayer2);
			}
		}
		
	}
}
