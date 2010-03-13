package com.ai4u.games.tictactoe;

import java.util.HashMap;
import java.util.Map;

import com.ai4u.core.Player;
import com.ai4u.core.display.GameDisplayer;
import com.ai4u.core.game.AbstractSimpleGame;
import com.ai4u.core.logic.Logic;

/**
 * @author igalk
 */
public class TicTacToeGame extends AbstractSimpleGame {

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
	public TicTacToeGame(ITicTacToeBoard board, GameDisplayer displayer, Logic logic4x,
			Logic logic4o) {
		super(board, displayer, buildMap(logic4x, logic4o));
		preCalcs(board.getSize());
	}

	private static Map<Player, Logic> buildMap(Logic logic4x, Logic logic4o) {
		Map<Player, Logic> map = new HashMap<Player, Logic>();
		map.put(TicTacToePlayer.X, logic4x);
		map.put(TicTacToePlayer.O, logic4o);
		return map;
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
	 * @see com.ai4u.core.game.Game#isGameOver()
	 */
	public boolean isGameOver() {
		ITicTacToeBoard board = (ITicTacToeBoard)this.state;
		
		int size = board.getSize();
		// sum all cells with powers of 2
		long sumX = 0;
		long sumO = 0;
		for (int i = 0; i < size; i++) {
			for (int j = 0; j < size; j++) {
				switch (board.getCell(i, j)) {
				case TicTacToeBoard.X:
					sumX += (1 << (i*size+j));
					break;
				case TicTacToeBoard.O:
					sumO += (1 << (i*size+j));
					break;
				default:
					break;
				}
			}
		}
		
		// check rows
		for (int row = 0; row < size; row++) {
			if ((rows[row]&sumX) == rows[row] ||
				(rows[row]&sumO) == rows[row])
				return true;
		}
		// check columns
		for (int col = 0; col < size; col++) {
			if ((cols[col]&sumX) == cols[col] ||
				(cols[col]&sumO) == cols[col])
				return true;
		}
		// check main diagonal
		if ((mainDiag&sumX) == mainDiag) return true;
		if ((mainDiag&sumO) == mainDiag) return true;
		// check secondary diagonal
		if ((secDiag&sumX) == secDiag) return true;
		if ((secDiag&sumO) == secDiag) return true;
		// check whether there are empty cells left
		if ((sumX|sumO) == fullBoard) return true;
		return false;
	}

	

}
