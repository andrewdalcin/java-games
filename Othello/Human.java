package Othello;

import javafx.scene.input.MouseEvent;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;

public class Human extends Player {
	private Rectangle currRecClicked;
	
	public Human(Color color, Game game) {
		super(color, game);
	}
	
	public Human(Human savedHuman, Game associatedSavedGame) {
		super(savedHuman, associatedSavedGame);
	}
	
	@Override
	public Square makeMove() {
		if (currRecClicked.getFill() == Color.GRAY) {
			int xLoc = (int) (currRecClicked.getX() / (Constants.SQUARE_HEIGHT));
			int yLoc = (int) (currRecClicked.getY() / (Constants.SQUARE_HEIGHT));
			return super.getGame().getBoard().getBoardArray().get(yLoc).get(xLoc);
		}
		return null;
	}
	
	//set method
	public void setSquareClicked(MouseEvent evt) {
		currRecClicked = (Rectangle) evt.getSource();
	}

}
