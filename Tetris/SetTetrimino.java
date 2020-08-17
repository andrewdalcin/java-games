package Tetris;

import java.util.ArrayList;
import java.util.List;

public class SetTetrimino {
	private Tetrimino tetrimino;
	
	public SetTetrimino(double random) {
		chooseRandomTetrimino(random, createTetriminoList());
	}
	
	private List<Tetrimino> createTetriminoList() {
		List<Tetrimino> tetriminos = new ArrayList<Tetrimino>();
		tetriminos.add(new J_Shape());
		tetriminos.add(new Z_Shape());
		tetriminos.add(new L_Shape());
		tetriminos.add(new S_Shape());
		tetriminos.add(new Line_Shape());
		tetriminos.add(new Square_Shape());
		tetriminos.add(new T_Shape());
		return tetriminos;
	}
	
	private void chooseRandomTetrimino(double random, List<Tetrimino> tetriminoList) {
		tetrimino = tetriminoList.get((int) (random * tetriminoList.size()));
	}
	
	//get methods
	public Tetrimino getTetrimino() {
		return tetrimino;
	}
}