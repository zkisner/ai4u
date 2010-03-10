package com.ai4u.games.tictactoe;

import com.ai4u.core.Game;
import com.ai4u.core.Move;
import com.ai4u.core.display.GameDisplayer;
import com.ai4u.core.logic.Logic;

/**
 * @author igalk
 */
public class TicTacToeGame implements Game {

	/** The board the game is played on. */
	private TicTacToeBoard board;
	/** The means of display for the game. */
	private GameDisplayer disp;
	/** The logic for the player playing x. */
	private Logic xLogic;
	/** The logic for the player playing o. */
	private Logic oLogic;
	/** Pre Calculations. */
	private long[] rows;
	private long[] cols;
	private long mainDiag;
	private long secDiag;
	private long fullBoard;

	/**
	 * Constructor.
	 * 
	 * @param size The size of the board to create.
	 * @param displayer The means of display.
	 * @param logic4x The logic of the player playing x.
	 * @param logic4o The logic of the player playing o.
	 */
	public TicTacToeGame(int size, GameDisplayer displayer, Logic logic4x,
			Logic logic4o) {
		board = new TicTacToeBoard(size);
		preCalcs(size);
		disp = displayer;
		xLogic = logic4x;
		oLogic = logic4o;
	}

	private void preCalcs(int size) {
		rows = new long[size];
		cols = new long[size];
		// init counters
		for (int i = 0; i < size; i++) {
			rows[i] = 0;
			cols[i] = 0;
		}
		mainDiag = 0;
		secDiag = 0;
		// calc sums
		for (int i = 0; i < size*size; i++) {
			long val = 1 << i;
			int row = i/size;
			int col = i%size;
			rows[row] |= val;
			cols[col] |= val;
			if (row == col) mainDiag |= val;
			if (row+col == size-1) secDiag |= val;
		}
		fullBoard = (1 << size*size) - 1;
	}

	/**
	 * @see com.ai4u.core.Game#isGameOver()
	 */
	public boolean isGameOver() {
		int size = board.getSize();
		// sum all cells with powers of 2
		long sumX = 0;
		long sumO = 0;
		for (int i = 0; i < size; i++) {
			for (int j = 0; j < size; j++) {
				switch (board.getCell(i, j)) {
				case TicTacToeBoard.X:
					sumX += Math.pow(2, i*size+j);
					break;
				case TicTacToeBoard.O:
					sumO += Math.pow(2, i*size+j);
					break;
				default:
					break;
				}
			}
		}
		
		// check rows
		for (int row = 0; row < size; row++) {
			if (rows[row] == sumX || rows[row] == sumO)
				return true;
		}
		// check columns
		for (int col = 0; col < size; col++) {
			if (cols[col] == sumX || cols[col] == sumO)
				return true;
		}
		// check main diagonal
		if (mainDiag == sumX) return true;
		// check secondary diagonal
		if (secDiag == sumO) return true;
		// check whether there are empty cells left
		if ((sumX|sumO) == fullBoard) return true;
		return false;
	}

	/**
	 * @see com.ai4u.core.Game#start()
	 */
	public void start() {
		disp.display(board);
		while (!isGameOver()) {
			Move move = null;
			TicTacToePlayer nextPlayer = (TicTacToePlayer) board.getNextPlaying();			
			switch (nextPlayer) {
			case X:
				move = xLogic.pickMove(board);
				break;
			case O:
				move = oLogic.pickMove(board);
				break;
			}
			board.makeMove(move);
			disp.display(board);
		}
		disp.gameOver(board);
	}

}
