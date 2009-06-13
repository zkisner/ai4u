package com.ai4u.games.tictactoe;

import com.ai4u.core.Move;

/**
 * @author igalk
 */
public class TicTacToeMove implements Move {

	/** The row number. */
	private int i;
	/** The column number. */
	private int j;
	/** The player who made the move. */
	private TicTacToePlayer p;

	/**
	 * Constructor.
	 * @param i The row number.
	 * @param j The column number.
	 * @param p The player who made the move.
	 */
	public TicTacToeMove(int i, int j, TicTacToePlayer p) {
		this.i = i;
		this.j = j;
		this.p = p;
	}
	
	/**
	 * @return The row number.
	 */
	public int getI() {
		return i;
	}
	
	/**
	 * @return The column number.
	 */
	public int getJ() {
		return j;
	}
	
	/**
	 * @return The player who made the move.
	 */
	public TicTacToePlayer getPlayer() {
		return p;
	}
	
}
