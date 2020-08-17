package TicTacToe;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Line;

public class Board {
	private Pane board;
	private List<LineBorder> horizontalLines;
	private List<LineBorder> verticalLines;
	private Lines lines;
	
	public Board() {
		board = new Pane();
		horizontalLines = new ArrayList<LineBorder>();
		verticalLines = new ArrayList<LineBorder>(); 
		this.createHorizontalLines();
		this.createVerticalLines();
		lines = new Lines(horizontalLines, verticalLines);
	}
	
	public Pane getBoardPane() {
		return board;
	}
	
	public Lines getLines() {
		return lines;
	}
	
	private void createHorizontalLines() {
		double yGridShiftFactor = 1.0 / (Constants.NUM_LINES + 1.0);
		double xGridShift = 0.0;
		double yGridShift = yGridShiftFactor * Constants.APP_HEIGHT;
		//adds top invisible line
		horizontalLines.add(new LineBorder(Arrays.asList(0.0, 0.0), Arrays.asList(Constants.APP_WIDTH, 0.0)));
		//board.getChildren().add(new Line(0, 250, 500, 250));
		//defines horizontal lines
		for (double i = 0; i < Constants.NUM_LINES; i++) {
			Line line = new Line();
			line.setStroke(Color.BLACK);
			line.setStartX(xGridShift);
			line.setEndX(Constants.APP_WIDTH - xGridShift);
			line.setStartY((i + 1.0) * yGridShift);
			line.setEndY((i + 1.0) * yGridShift);
			LineBorder lineBorder = new LineBorder(Arrays.asList(line.getStartX(), line.getStartY()), Arrays.asList(line.getEndX(), line.getEndY()));
			
			board.getChildren().add(line);
		}
		//adds bottom invisible line
		horizontalLines.add(new LineBorder(Arrays.asList(0.0, Constants.APP_HEIGHT), Arrays.asList(Constants.APP_WIDTH, Constants.APP_HEIGHT)));
		
	}
	
	private void createVerticalLines() {
		double xGridShiftFactor = 1.0 / (Constants.NUM_LINES + 1.0);
		double xGridShift = xGridShiftFactor * Constants.APP_WIDTH;
		double yGridShift = 0;
		//adds leftmost invisible line
		verticalLines.add(new LineBorder(Arrays.asList(0.0, 0.0), Arrays.asList(0.0, Constants.APP_HEIGHT)));
		//defines vertical lines
		for (double i = 0; i < Constants.NUM_LINES; i++) {
			Line line = new Line();
			line.setStroke(Color.BLACK);
			line.setStartX((i + 1.0) * xGridShift);
			line.setEndX((i + 1.0) * xGridShift); 
			line.setStartY(yGridShift);
			line.setEndY(Constants.APP_HEIGHT - yGridShift);
			LineBorder lineBorder = new LineBorder(Arrays.asList(line.getStartX(), line.getStartY()), Arrays.asList(line.getEndX(), line.getEndY()));
			verticalLines.add(lineBorder); 
			board.getChildren().add(line);
		}
		//adds rightmost invisible line
		verticalLines.add(new LineBorder(Arrays.asList(Constants.APP_WIDTH, 0.0), Arrays.asList(Constants.APP_WIDTH, Constants.APP_HEIGHT)));
	}
	
	public class Lines {
		public List<LineBorder> horizontalLines;
		public List<LineBorder> verticalLines;
		
		public Lines() {}
		
		public Lines(List<LineBorder> horizontalLines, List<LineBorder> verticalLines) {
			this.horizontalLines = horizontalLines;
			this.verticalLines = verticalLines;
		}
	}
}
