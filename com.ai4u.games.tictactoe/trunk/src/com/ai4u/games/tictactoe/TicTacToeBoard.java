package com.ai4u.games.tictactoe;

import java.util.ArrayList;
import java.util.List;

import com.ai4u.core.*;

/**
 * @author igalk
 */
public class TicTacToeBoard implements Board {

	/** An empty cell. */
	public static final char EMPTY = 0;
	
	/** A cell occupied by X. */
	public static final char X = 1;
	
	/** A cell occupied by O. */
	public static final char O = 2;

	/** The size of the board. */
	private int size;
	
	/** The cells of the game. */
	private char[][] cells;
	
	/**
	 * @param size The size of the board to create.
	 */
	public TicTacToeBoard(int size) {
		this.size = size;
		cells = new char[size][];
		for (int i = 0; i < size; i++) {
			cells[i] = new char[size];
		}
	}
	
	/**
	 * @see com.ai4u.core.Board#getMoves(com.ai4u.core.Player)
	 */
	public List<Move> getMoves(Player player) {
		List<Move> moves = new ArrayList<Move>();
		for (int i = 0; i < size; i++) {
			for (int j = 0; j < size; j++) {
				if (cells[i][j] == EMPTY) {
					moves.add(new TicTacToeMove(i, j, (TicTacToePlayer) player));
				}
			}
		}
		return moves;
	}

	/**
	 * @see com.ai4u.core.Board#makeMove(com.ai4u.core.Move)
	 */
	public void makeMove(Move move) {
		TicTacToeMove m = (TicTacToeMove) move;
		cells[m.getI()][m.getJ()] = m.getPlayer().equals(TicTacToePlayer.X) ?
				X : O;
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

}
