package Tetris;

import java.util.ArrayList;
import java.util.List;

import javafx.animation.Animation;
import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.animation.Animation.Status;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Pos;
import javafx.scene.control.Label;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
import javafx.scene.text.Font;
import javafx.util.Duration;

public class Game {
	private Board board;
	private Pane tetrisPane;
	private BorderPane root;
	private Timeline tetriminoFall;
	private int highScore;
	private Label pauseLabel;
	private Label gameOverLabel;
	
	public Game() {
		setupInstances();
		setupTetriminoFallingTimeline(Constants.TETRIMINO_FLOAT_DURATION);
	}
	
	private void setupInstances() {
		board = new Board();
		highScore = 0;
	}
	
	private void setupTetriminoFallingTimeline(double duration) {
		KeyFrame kf = new KeyFrame(Duration.seconds(duration), new TetriminoFall());
		tetriminoFall = new Timeline(kf);
		tetriminoFall.setCycleCount(Animation.INDEFINITE);
		tetriminoFall.play();
	}
	
	private void setupPauseLabel() {
		pauseLabel = new Label("Paused");
		pauseLabel.setFont(new Font("Garamond", 70));
		pauseLabel.setTextFill(Color.BLACK);
		pauseLabel.setAlignment(Pos.CENTER);
		pauseLabel.setVisible(true);
		root.setCenter(pauseLabel);
	}
	
	private void setupGameOverLabel() {
		gameOverLabel = new Label("Game Over");
		gameOverLabel.setFont(new Font("Garamond", 70));
		gameOverLabel.setTextFill(Color.BLACK);
		gameOverLabel.setAlignment(Pos.CENTER);
		gameOverLabel.setVisible(true);
		root.setCenter(gameOverLabel);
	}
	
	//miscellaneous methods
	private boolean isLeavesBoard(List<List<Square>> fallingTetriminoArray, int distance) {
		for (List<Square> tetriminoRow : fallingTetriminoArray) {
			for (Square tetriminoSquare : tetriminoRow) {
				int x = (int) tetriminoSquare.getBoardLocation().getX();
				int y = (int) tetriminoSquare.getBoardLocation().getY();
				if (x + distance < 0 || x + distance >= board.getBoardArray().get(0).size() || y + distance >= board.getBoardArray().size() || y < 0) {
					return true;
				}
			}
		}
		return false;
	}
	
	private boolean isLeavesBottom(List<List<Square>> fallingTetriminoArray, int distance) {
		for (List<Square> tetriminoRow : fallingTetriminoArray) {
			for (Square tetriminoSquare : tetriminoRow) {
				int x = (int) tetriminoSquare.getBoardLocation().getX();
				int y = (int) tetriminoSquare.getBoardLocation().getY();
				if (y + distance >= board.getBoardArray().size()) {
					return true;
				}
			}
		}
		return false;
	}
	
	private boolean isInTetrimino(Square square, List<List<Square>> fallingTetrimino) {//* excludes white pieces
		for (List<Square> rowTetrimino : fallingTetrimino) {
			for(Square squareTetrimino : rowTetrimino) {
				if (squareTetrimino == square) {
					return true;
				}
			}
		}
		return false;
	}
	
	private boolean isIntersectsTetrimino(List<List<Square>>fallingTetriminoArray, int distanceX, int distanceY) {
		for (List<Square> rowTetrimino : fallingTetriminoArray) {
			for (Square squareTetrimino : rowTetrimino) {
				if (squareTetrimino.getSquareBody().getFill() == Color.WHITE) {
					continue;
				}
				int newX = (int) squareTetrimino.getBoardLocation().getX() + distanceX;
				int newY = (int) squareTetrimino.getBoardLocation().getY() + distanceY;
				Square curr = board.getBoardArray().get(newY).get(newX);
				if (curr.getSquareBody().getFill() != Color.WHITE && !isInTetrimino(curr, fallingTetriminoArray)) {
					return true;
				}
			}
		}
		return false;
	}
	
	private boolean isTetriminoAtStart(List<List<Square>> fallingTetrimino) {
		for (List<Square> rowTetrimino : fallingTetrimino) {
			for (Square squareTetrimino : rowTetrimino) {
				if (squareTetrimino.getBoardLocation().getY() == 0) {
					if (squareTetrimino.getSquareBody().getFill() != Color.WHITE) {
						return true;
					}
				}
			}
		}
		return false;
	}
	
	private void deleteFullRows() {
		List<List<Square>> boardArray = board.getBoardArray();
		int rowFill;
		int count = 0;
		for (int row = boardArray.size() - 1; row >= 0; row--) {
			rowFill = 0;
			for (int col = 0; col < boardArray.get(0).size(); col++) {
				if (boardArray.get(row).get(col).getSquareBody().getFill() != Color.WHITE) {
					++rowFill;
				}
			}
			if (rowFill == boardArray.get(0).size()) {
				++count;
				//delete row
				for (int col = 0; col < boardArray.get(0).size(); col++) {
					Square currSquare = boardArray.get(row).get(col);
					Square replaceSquare = new Square(Color.WHITE);
					replaceSquare.getSquareBody().setX(currSquare.getSquareBody().getX());
					replaceSquare.getSquareBody().setY(currSquare.getSquareBody().getY());
					replaceSquare.setBoardLocation(col, row);
					boardArray.get(row).set(col, replaceSquare);
				}
				shiftRowsDown(row - 1, boardArray);
				++row;
			}
		}
		if (count != 0) {
			highScore += Math.pow(4, count);
		}
	}
	
	private void shiftRowsDown(int startRow, List<List<Square>> boardArray) {
		//shift rest of rows down
		for (int row = startRow; row >= 0; row--) {
			for (int col = 0; col < boardArray.get(0).size(); col++) {
				Square replaceSquare = boardArray.get(row).get(col);
				Square whiteSquare = new Square(Color.WHITE);
				whiteSquare.getSquareBody().setX(replaceSquare.getSquareBody().getX());
				whiteSquare.getSquareBody().setY(replaceSquare.getSquareBody().getY());
				whiteSquare.setBoardLocation(col, row);
				replaceSquare.getSquareBody().setX(replaceSquare.getSquareBody().getX());
				replaceSquare.getSquareBody().setY(replaceSquare.getSquareBody().getY() + Constants.SQUARE_HEIGHT);
				replaceSquare.setBoardLocation(col, row + 1);
				boardArray.get(row).set(col, whiteSquare);
				boardArray.get(row + 1).set(col, replaceSquare);
			}
		}
	}
	
	//Tetrimino movement methods
	private void moveTetriminoDown() {
		int distance = 1;
		List<List<Square>> fallingTetriminoArray = board.getCurrTetrimino().getTetriminoBody();
		List<List<Square>> boardArray = board.getBoardArray(); 
		if (isLeavesBottom(fallingTetriminoArray, distance)) {
			return;
		}
		if (isIntersectsTetrimino(fallingTetriminoArray, 0, distance)) {
			return;
		}
		List<Square> replaceSquares = new ArrayList<Square>();
		List<Square> fallingSquares = new ArrayList<Square>();
		for (int row = 0; row < fallingTetriminoArray.size(); row++) {
			for (int col = 0; col < fallingTetriminoArray.get(row).size(); col++) {
				Square fallingSquare = fallingTetriminoArray.get(row).get(col);
				if (fallingSquare.getSquareBody().getFill() == Color.WHITE) {
					continue;
				}
				Square replaceSquare = new Square(Color.WHITE);
				replaceSquare.getSquareBody().setX(fallingSquare.getSquareBody().getX());
				replaceSquare.getSquareBody().setY(fallingSquare.getSquareBody().getY());
				replaceSquare.setBoardLocation((int) fallingSquare.getBoardLocation().getX(), (int) fallingSquare.getBoardLocation().getY());
				int currX = (int) fallingSquare.getBoardLocation().getX();
				int prevY = (int) fallingSquare.getBoardLocation().getY();
				//sets new square features
				fallingSquare.getSquareBody().setY(fallingSquare.getSquareBody().getY() + Constants.SQUARE_HEIGHT);
				fallingSquare.setBoardLocation(currX, prevY + distance);
				replaceSquares.add(replaceSquare);
				fallingSquares.add(fallingSquare);
			}
		}
		for (Square replaceSquare : replaceSquares) {
			boardArray.get((int) replaceSquare.getBoardLocation().getY()).set((int) replaceSquare.getBoardLocation().getX(), replaceSquare);
		}
		for (Square fallingSquare : fallingSquares) {
			boardArray.get((int) fallingSquare.getBoardLocation().getY()).set((int) fallingSquare.getBoardLocation().getX(), fallingSquare);
		}
	}
	
	private void moveTetriminoLeft() {
		int distance = -1;
		List<List<Square>> fallingTetriminoArray = board.getCurrTetrimino().getTetriminoBody();
		List<List<Square>> boardArray = board.getBoardArray(); 
		if (isLeavesBoard(fallingTetriminoArray, distance)) {
			return;
		}
		if (isIntersectsTetrimino(fallingTetriminoArray, distance, 0)) {
			return;
		}
		List<Square> replaceSquares = new ArrayList<Square>();
		List<Square> fallingSquares = new ArrayList<Square>();
		for (int row = 0; row < fallingTetriminoArray.size(); row++) {
			for (int col = 0; col < fallingTetriminoArray.get(row).size(); col++) {
				Square fallingSquare = fallingTetriminoArray.get(row).get(col);
				if (fallingSquare.getSquareBody().getFill() == Color.WHITE) {
					continue;
				}
				Square replaceSquare = new Square(Color.WHITE);
				replaceSquare.getSquareBody().setX(fallingSquare.getSquareBody().getX());
				replaceSquare.getSquareBody().setY(fallingSquare.getSquareBody().getY());
				replaceSquare.setBoardLocation((int) fallingSquare.getBoardLocation().getX(), (int) fallingSquare.getBoardLocation().getY());
				int prevX = (int) fallingSquare.getBoardLocation().getX();
				int currY = (int) fallingSquare.getBoardLocation().getY();
				//sets new square features
				fallingSquare.getSquareBody().setX(fallingSquare.getSquareBody().getX() - Constants.SQUARE_WIDTH);
				fallingSquare.setBoardLocation(prevX + distance, currY);
				replaceSquares.add(replaceSquare);
				fallingSquares.add(fallingSquare);
			}
		}
		for (Square replaceSquare : replaceSquares) {
			boardArray.get((int) replaceSquare.getBoardLocation().getY()).set((int) replaceSquare.getBoardLocation().getX(), replaceSquare);
		}
		for (Square fallingSquare : fallingSquares) {
			boardArray.get((int) fallingSquare.getBoardLocation().getY()).set((int) fallingSquare.getBoardLocation().getX(), fallingSquare);
		}
	}
	
	private void moveTetriminoRight() {
		int distance = 1;
		List<List<Square>> fallingTetriminoArray = board.getCurrTetrimino().getTetriminoBody();
		List<List<Square>> boardArray = board.getBoardArray(); 
		if (isLeavesBoard(fallingTetriminoArray, distance)) {
			return;
		}
		if (isIntersectsTetrimino(fallingTetriminoArray, distance, 0)) {
			return;
		}
		List<Square> replaceSquares = new ArrayList<Square>();
		List<Square> fallingSquares = new ArrayList<Square>();
		for (int row = 0; row < fallingTetriminoArray.size(); row++) {
			for (int col = 0; col < fallingTetriminoArray.get(row).size(); col++) {
				Square fallingSquare = fallingTetriminoArray.get(row).get(col);
				if (fallingSquare.getSquareBody().getFill() == Color.WHITE) {
					continue;
				}
				Square replaceSquare = new Square(Color.WHITE);
				replaceSquare.getSquareBody().setX(fallingSquare.getSquareBody().getX());
				replaceSquare.getSquareBody().setY(fallingSquare.getSquareBody().getY());
				replaceSquare.setBoardLocation((int) fallingSquare.getBoardLocation().getX(), (int) fallingSquare.getBoardLocation().getY());
				int prevX = (int) fallingSquare.getBoardLocation().getX();
				int currY = (int) fallingSquare.getBoardLocation().getY();
				//sets new square features
				fallingSquare.getSquareBody().setX(fallingSquare.getSquareBody().getX() + Constants.SQUARE_WIDTH);
				fallingSquare.setBoardLocation(prevX + distance, currY);
				replaceSquares.add(replaceSquare);
				fallingSquares.add(fallingSquare);
			}
		}
		for (Square replaceSquare : replaceSquares) {
			boardArray.get((int) replaceSquare.getBoardLocation().getY()).set((int) replaceSquare.getBoardLocation().getX(), replaceSquare);
		}
		for (Square fallingSquare : fallingSquares) {
			boardArray.get((int) fallingSquare.getBoardLocation().getY()).set((int) fallingSquare.getBoardLocation().getX(), fallingSquare);
		}
	}
	
	private void rotateTetriminoCW() {
		List<List<Square>> fallingTetriminoArray = board.getCurrTetrimino().getTetriminoBody();
		List<List<Square>> boardArray = board.getBoardArray();
		int centerOfRotationX = (int) fallingTetriminoArray.get(0).get(0).getSquareBody().getX(); 
		int centerOfRotationY = (int) fallingTetriminoArray.get(0).get(0).getSquareBody().getY();
		if (fallingTetriminoArray.get(0).get(0).getSquareBody().getFill() == Color.WHITE) {
			centerOfRotationX = (int) fallingTetriminoArray.get(0).get(1).getSquareBody().getX(); 
			centerOfRotationY = (int) fallingTetriminoArray.get(0).get(1).getSquareBody().getY();
		}
		List<Square> replaceSquares = new ArrayList<Square>();
		List<Square> fallingSquares = new ArrayList<Square>();
		//makes sure tetrimino is within bounds for rotation
		for (int row = 0; row < fallingTetriminoArray.size(); row++) {
			for (int col = 0; col < fallingTetriminoArray.get(row).size(); col++) {
				Square fallingSquare = fallingTetriminoArray.get(row).get(col);
				if (fallingSquare.getSquareBody().getFill() == Color.WHITE) {
					continue;
				}
				int newXIndex = (centerOfRotationX - centerOfRotationY + (int) fallingSquare.getSquareBody().getY()) / Constants.SQUARE_WIDTH;
				int newYIndex = (centerOfRotationY + centerOfRotationX - (int) fallingSquare.getSquareBody().getX()) / Constants.SQUARE_HEIGHT;
				if (newXIndex < 0 || newXIndex >= board.getBoardArray().get(0).size() || newYIndex < 0 || newYIndex >= board.getBoardArray().size() || (board.getBoardArray().get(newYIndex).get(newXIndex).getSquareBody().getFill() != Color.WHITE && !isInTetrimino(board.getBoardArray().get(newYIndex).get(newXIndex), fallingTetriminoArray))) {
					return;
				}
			}
		} 
		//rotates tetrimino
		for (int row = 0; row < fallingTetriminoArray.size(); row++) {
			for (int col = 0; col < fallingTetriminoArray.get(row).size(); col++) {
				Square fallingSquare = fallingTetriminoArray.get(row).get(col);
				if (fallingSquare.getSquareBody().getFill() == Color.WHITE) {
					continue;
				}
				Square replaceSquare = new Square(Color.WHITE);
				//sets replacement square with correct coordinates
				replaceSquare.setBoardLocation((int) fallingSquare.getSquareBody().getX() / Constants.SQUARE_WIDTH, (int) fallingSquare.getSquareBody().getY() / Constants.SQUARE_HEIGHT);
				replaceSquare.getSquareBody().setX((int) fallingSquare.getSquareBody().getX());
				replaceSquare.getSquareBody().setY((int) fallingSquare.getSquareBody().getY());
				//sets falling square with rotated coordinates
				int newX = centerOfRotationX - centerOfRotationY + (int) fallingSquare.getSquareBody().getY();
				int newY = centerOfRotationY + centerOfRotationX - (int) fallingSquare.getSquareBody().getX();
				fallingSquare.getSquareBody().setX(newX);
				fallingSquare.getSquareBody().setY(newY);
				fallingSquare.setBoardLocation((int) fallingSquare.getSquareBody().getX() / Constants.SQUARE_WIDTH, (int) fallingSquare.getSquareBody().getY() / Constants.SQUARE_HEIGHT);
				replaceSquares.add(replaceSquare);
				fallingSquares.add(fallingSquare);
			}
		}
		for (Square replaceSquare : replaceSquares) {
			boardArray.get((int) replaceSquare.getBoardLocation().getY()).set((int) replaceSquare.getBoardLocation().getX(), replaceSquare);
		}
		for (Square fallingSquare : fallingSquares) {
			boardArray.get((int) fallingSquare.getBoardLocation().getY()).set((int) fallingSquare.getBoardLocation().getX(), fallingSquare);
		}
	}
	
	//update methods
	private void updateTetrisPane() {//XX O(n^2) time complexity
		tetrisPane.getChildren().clear();
		for (List<Square> boardRow : board.getBoardArray()) {
			for (Square boardSquare : boardRow) {
				tetrisPane.getChildren().add(boardSquare.getSquareBody());
			}
		}
	}
	
	//get methods
	public Board getBoard() {
		return board;
	}
	
	public int getHighScore() {
		return highScore;
	}
	
	public Timeline getTetriminoFallTimeline() {
		return tetriminoFall;
	}
	
	//set methods
	public void setTetrisPane(Pane tetrisPane) {
		//tetrisPane.addEventHandler(KeyEvent.KEY_PRESSED, new TetriminoMovements());
		this.tetrisPane = tetrisPane;
	}
	
	public void setRoot(BorderPane root) {
		root.addEventHandler(KeyEvent.KEY_PRESSED, new TetriminoMovements());
		this.root = root;
	}
	
	//event handler classes
	private class TetriminoFall implements EventHandler<ActionEvent> {
		
		public TetriminoFall() {
			
		}
		
		@Override
		public void handle(ActionEvent evt) {
			int distance = 1;
			List<List<Square>> fallingTetriminoArray = board.getCurrTetrimino().getTetriminoBody();
			if (!isLeavesBottom(fallingTetriminoArray, distance) && isIntersectsTetrimino(fallingTetriminoArray, 0, distance) && isTetriminoAtStart(fallingTetriminoArray)) {
				tetriminoFall.stop();
				setupGameOverLabel();
			}
			if (isLeavesBottom(fallingTetriminoArray, distance) || isIntersectsTetrimino(fallingTetriminoArray, 0, distance)) {
				tetriminoFall.stop();
				board.setRandomTetrimino();
				board.placeTetriminoInBoard();
				setupTetriminoFallingTimeline(Constants.TETRIMINO_FLOAT_DURATION);
				deleteFullRows();
				updateTetrisPane();
				return;
			}
			moveTetriminoDown();
			updateTetrisPane();
			evt.consume();
		}
		
	}
	
	private class TetriminoMovements implements EventHandler<KeyEvent> {
		
		public TetriminoMovements() {
			
		}
		
		@Override
		public void handle(KeyEvent evt) {
			if (evt.getCode() == KeyCode.RIGHT) {
				moveTetriminoRight();
			} else if (evt.getCode() == KeyCode.LEFT) {
				moveTetriminoLeft();
			} else if (evt.getCode() == KeyCode.DOWN) {
				moveTetriminoDown();
			} else if (evt.getCode() == KeyCode.UP) {
				rotateTetriminoCW();
			} else if (evt.getCode() == KeyCode.SPACE) {
				tetriminoFall.stop();
				setupTetriminoFallingTimeline(.01);
			} else if (evt.getCode() == KeyCode.P) {
				if (tetriminoFall.getStatus() == Status.RUNNING) {
					tetriminoFall.stop();
					setupPauseLabel();
				} else {
					root.setCenter(tetrisPane);
					tetriminoFall.play();
				}
			}
			updateTetrisPane();
			evt.consume();
		}
	}
	
}
