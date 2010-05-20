package com.ai4u.games.tictactoe;

import java.util.ArrayList;
import java.util.List;

/**
 * @author igalk
 */
public class TicTacToeBoard implements ITicTacToeBoard {

	/** The size of the board. */
	protected int size;
	
	/** The cells of the game. */
	protected char[][] cells;
	
	/** The current player. */
	private TicTacToePlayer nextPlayer;
	
	/** The winner of the game. */
	private TicTacToePlayer winner;
	
	/** Pre Calculations. */
	private long[] rows;
	private long[] cols;
	private long mainDiag;
	private long secDiag;
	private long fullBoard;
	
	/**
	 * @param size The size of the board to create.
	 */
	public TicTacToeBoard(int size) {
		this.size = size;
		preCalcs(size);
		cells = new char[size][];
		for (int i = 0; i < size; i++) {
			cells[i] = new char[size];
			for (int j = 0; j < size; j++) {
				cells[i][j] = EMPTY;
			}
		}
		nextPlayer = TicTacToePlayer.X;
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
	 * @see com.ai4u.core.GameState#getMoves(com.ai4u.core.Player)
	 */
	public List<TicTacToeMove> getMoves(TicTacToePlayer player) {
		List<TicTacToeMove> moves = new ArrayList<TicTacToeMove>();
		for (int i = 0; i < size; i++) {
			for (int j = 0; j < size; j++) {
				if (cells[i][j] == EMPTY) {
					moves.add(new TicTacToeMove(i, j));
				}
			}
		}
		return moves;
	}

	/**
	 * @see com.ai4u.core.GameState#makeMove(com.ai4u.core.Move)
	 */
	public TicTacToeBoard makeMove(TicTacToeMove move) {
		cells[move.getI()][move.getJ()] = nextPlayer.equals(TicTacToePlayer.X) ?
				X : O;
		nextPlayer = nextPlayer.equals(TicTacToePlayer.X) ?
				TicTacToePlayer.O : TicTacToePlayer.X;
		
		return this;
	}
	
	public TicTacToeBoard simulateMove(TicTacToeMove move) {
		TicTacToeBoard copy = this.clone();
		return copy.makeMove(move);
	}

	public TicTacToePlayer getNextPlaying() {
		return nextPlayer;
	}
	
	public boolean isGameOver() {
		// sum all cells with powers of 2
		long sumX = 0;
		long sumO = 0;
		for (int i = 0; i < size; i++) {
			for (int j = 0; j < size; j++) {
				switch (cells[i][j]) {
				case ITicTacToeBoard.X:
					sumX += (1 << (i*size+j));
					break;
				case ITicTacToeBoard.O:
					sumO += (1 << (i*size+j));
					break;
				default:
					break;
				}
			}
		}
		
		// check rows an columns
		for (int i = 0; i < size; i++) {
			if ((rows[i]&sumX) == rows[i] ||
				(cols[i]&sumX) == cols[i]) {
				winner = TicTacToePlayer.X;
				return true;
			}
			if ((rows[i]&sumO) == rows[i] ||
				(cols[i]&sumO) == cols[i]) {
				winner = TicTacToePlayer.O;
				return true;
			}
		}
		// check main and secondary diagonals
		if ((mainDiag&sumX) == mainDiag ||
			(secDiag&sumX) == secDiag) {
			winner = TicTacToePlayer.X;
			return true;
		}
		if ((mainDiag&sumO) == mainDiag ||
			(secDiag&sumO) == secDiag) {
			winner = TicTacToePlayer.O;
			return true;
		}
		// check whether there are empty cells left
		if ((sumX|sumO) == fullBoard) {
			winner = null;
			return true;
		}
		
		winner = null;
		return false;
	}

	public TicTacToePlayer getWinner() {
		return isGameOver() ? winner : null;
	}

	/**
	 * @return The size of the board.
	 */
	public int getSize() {
		return size;
	}

	/**
	 * @param i The row of the cell.
	 * @param j The column of the cell.
	 * @return The value of the cell.
	 */
	public int getCell(int i, int j) {
		return cells[i][j];
	}

	@Override
	public TicTacToeBoard clone() {
		TicTacToeBoard clone = new TicTacToeBoard(this.size);
		
		for (int i = 0; i < this.cells.length; i++) {
			for (int j = 0; j < this.cells[i].length; j++) {
				clone.cells[i][j] = this.cells[i][j];
			}
		}
		
		clone.nextPlayer = this.nextPlayer;
		
		return clone;
	}
	
	@Override
	public String toString() {
		StringBuilder builder = new StringBuilder();
		for (char[] row : cells) {
			builder.append('[');
			for (char c : row) {
				builder.append(c).append(',');
			}
			int length = builder.length();
			builder.replace(length-1, length, "]\n");
		}
		return builder.toString();
	}

}
