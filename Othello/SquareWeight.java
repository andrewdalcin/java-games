package Othello;

public class SquareWeight {
	private Square square;
	private int weight;
	
	public SquareWeight() {};
	
	public SquareWeight(Square square, int weight) {
		this.square = square;
		this.weight = weight;
	}
	
	public void setSquare(Square square) {
		this.square = square;
	}
	
	public void setWeight(int weight) {
		this.weight = weight;
	}
	
	public Square getSquare() {
		return square;
	}
	
	public int getWeight() {
		return weight;
	}
}
