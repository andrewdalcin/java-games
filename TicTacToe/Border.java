package TicTacToe;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import TicTacToe.Board.Lines;

public class Border {
	
	private List<List<SquareBorder>> squareBorders;
	private Lines lines;
	
	public Border() {
		squareBorders = new ArrayList<List<SquareBorder>>();
		Board board = new Board();
		lines = board.getLines();
		this.makeSquareBorders();
	}
	
	public List<List<SquareBorder>> getSquareBorders() {
		return squareBorders;
	}
	
	private void makeSquareBorders() {
		List<LineBorder> horizontalLines = lines.horizontalLines;
		double numRowGrids = Constants.NUM_LINES + 1.0;
		double numColumnGrids = numRowGrids;
		for (double row = 0.0; 0 < numRowGrids; row++) {
			squareBorders.add(new ArrayList<SquareBorder>());
			LineBorder topLine = horizontalLines.get((int) row);
			double squareSideLength = (topLine.end.get(0) - topLine.start.get(0)) / numRowGrids;
			for (double col = 0.0; 0 < numColumnGrids; col++) {
				LineBorder leftBorder = new LineBorder(Arrays.asList(col, row * squareSideLength), Arrays.asList(col, (row + 1.0) * squareSideLength));
				LineBorder topBorder = new LineBorder(Arrays.asList(col * squareSideLength, row), Arrays.asList((col + 1.0) * squareSideLength, row));
				LineBorder bottomBorder = new LineBorder(Arrays.asList(col * squareSideLength, row + 1.0), Arrays.asList((col + 1.0) * squareSideLength, row + 1.0));
				LineBorder rightBorder = new LineBorder(Arrays.asList((col + 1.0), row * squareSideLength), Arrays.asList((col + 1.0), (row + 1.0) * squareSideLength));
				SquareBorder squareBorder = new SquareBorder(leftBorder, topBorder, bottomBorder, rightBorder);
				squareBorders.get((int) row).add(squareBorder);
			}
		}
	}
	
}
