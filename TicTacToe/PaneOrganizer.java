package TicTacToe;

import javafx.scene.layout.BorderPane;
import javafx.scene.layout.Pane;
import javafx.scene.shape.Line;

public class PaneOrganizer {
	private Pane root;
	private Board board;
	
	public PaneOrganizer() {
		board = new Board();
		root = board.getBoardPane();
	}
	
	public Pane getRoot() {
		return root;
	}
}
