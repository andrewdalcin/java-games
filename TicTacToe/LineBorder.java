package TicTacToe;

import java.util.List;

public class LineBorder {
	public List<Double> start;
	public List<Double> end;
	public Point midpoint; 
	
	public LineBorder() {}
	
	public LineBorder(List<Double> start, List<Double> end) {
		this.start = start;
		this.end = end;
		this.getMidpoint();
	}
	
	public void getMidpoint() {
		double xMid = (start.get(0) + end.get(0)) / 2;
		double yMid = (start.get(1) + end.get(1)) / 2;
		midpoint = new Point(xMid, yMid);
	}
}
