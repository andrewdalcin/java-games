package Othello;

public class Constants {
	public static final int NUM_BOARD_SQUARES_ROW = 8;
	public static final int NUM_BOARD_SQUARES_COL = NUM_BOARD_SQUARES_ROW;
	public static final int SQUARE_HEIGHT = 75;
	public static final int APP_WIDTH = SQUARE_HEIGHT * (2 + NUM_BOARD_SQUARES_COL)  + (SQUARE_HEIGHT * (2 + NUM_BOARD_SQUARES_COL)) / 2;
	public static final int APP_HEIGHT = SQUARE_HEIGHT * (2 + NUM_BOARD_SQUARES_ROW);
}
