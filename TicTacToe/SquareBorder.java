package TicTacToe;

import java.util.Objects;

public class SquareBorder {
	public LineBorder leftBorder;
	public LineBorder topBorder;
	public LineBorder bottomBorder;
	public LineBorder rightBorder;
	public Point midpoint;
	public boolean isFilled;
	public boolean filledByPlayer1;
	public boolean filledByPlayer2;
	
	public SquareBorder() {}
	
	public SquareBorder(LineBorder leftBorder, LineBorder topBorder, LineBorder bottomBorder, LineBorder rightBorder) {
		this.leftBorder = leftBorder;
		this.topBorder = topBorder;
		this.rightBorder = rightBorder;
		this.bottomBorder = bottomBorder;
		this.getSquareMiddle();
	}
	
	public void getSquareMiddle() {
		double xMid = (rightBorder.midpoint.x + leftBorder.midpoint.x) / 2;
		double yMid = (topBorder.midpoint.y + bottomBorder.midpoint.y) / 2;
		midpoint = new Point(xMid, yMid);
	}
	
	@Override
	public boolean equals(Object o) {
		if (!(o instanceof SquareBorder)) {
			return false;
		}
		
		SquareBorder that = (SquareBorder) o;
		boolean isLeftBorder = leftBorder.start.get(0).equals(that.leftBorder.start.get(0)) && leftBorder.start.get(1).equals(that.leftBorder.start.get(1)) && leftBorder.end.get(0).equals(that.leftBorder.end.get(0)) && leftBorder.end.get(1).equals(that.leftBorder.end.get(1));  
		boolean isRightBorder = rightBorder.start.get(0).equals(that.rightBorder.start.get(0)) && rightBorder.start.get(1).equals(that.rightBorder.start.get(1)) && rightBorder.end.get(0).equals(that.rightBorder.end.get(0)) && rightBorder.end.get(1).equals(that.rightBorder.end.get(1));
		boolean isTopBorder = topBorder.start.get(0).equals(that.topBorder.start.get(0)) && topBorder.start.get(1).equals(that.topBorder.start.get(1)) && topBorder.end.get(0).equals(that.topBorder.end.get(0)) && topBorder.end.get(1).equals(that.topBorder.end.get(1));
		boolean isBottomBorder = bottomBorder.start.get(0).equals(that.bottomBorder.start.get(0)) && bottomBorder.start.get(1).equals(that.bottomBorder.start.get(1)) && bottomBorder.end.get(0).equals(that.bottomBorder.end.get(0)) && bottomBorder.end.get(1).equals(that.bottomBorder.end.get(1));
		return isLeftBorder && isRightBorder && isTopBorder && isBottomBorder;
	}
	
	@Override 
	public int hashCode() {
		return Objects.hash(leftBorder.start.get(0), leftBorder.start.get(1), leftBorder.end.get(0), leftBorder.end.get(1), rightBorder.start.get(0), rightBorder.start.get(1), rightBorder.end.get(0), rightBorder.end.get(1), topBorder.start.get(0), topBorder.start.get(1), topBorder.end.get(0), topBorder.end.get(1), bottomBorder.start.get(0), bottomBorder.start.get(1), bottomBorder.end.get(0), bottomBorder.end.get(1));
	}
}
