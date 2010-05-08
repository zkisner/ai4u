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

	/**
	 * Constructor.
	 * @param i The row number.
	 * @param j The column number.
	 */
	public TicTacToeMove(int i, int j) {
		this.i = i;
		this.j = j;
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
	
	@Override
	public boolean equals(Object other) {
		if (!(other instanceof TicTacToeMove)) return false;
		
		TicTacToeMove compared = (TicTacToeMove) other;
		return (i == compared.i) && (j == compared.j);
	}
	
	@Override
	public String toString() {
		return "(" + i + "," + j + ")";
	}
	
}
