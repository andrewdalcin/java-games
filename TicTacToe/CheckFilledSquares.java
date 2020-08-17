package TicTacToe;

import java.util.List;

import javafx.event.ActionEvent;
import javafx.scene.input.MouseEvent;
import javafx.scene.text.Text;

public class CheckFilledSquares {
	private List<List<SquareBorder>> squareBorders;
	private SquareBorder winningSquareBorder;
	private Text winText;
	
	public CheckFilledSquares() {
		X squares = new X();
		squareBorders = squares.getSquareBorders();
		winText = new Text();
	}
	
	public static boolean isWinner() {
		
	}
	
	public class CheckClicker implements EventHandler<MouseEvent> {
		
		public CheckClicker() {}
		
		@Override
		public void handle(MouseEvent evt) {
			if (isWinner()) {
				if (winningSquareBorder.filledByPlayer1) {
					winText.setText("Player 1 is the winner!");
				} else {
					winText.setText("Player 2 is the winner!");
				}
			}
		}
		
	}
	
}
