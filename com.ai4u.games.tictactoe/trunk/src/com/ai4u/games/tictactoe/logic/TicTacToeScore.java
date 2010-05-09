/**
 * 14/03/10
 */
package com.ai4u.games.tictactoe.logic;

import com.ai4u.core.logic.computer.evaluation.GameStateScore;
import com.ai4u.games.tictactoe.TicTacToeMove;

/**
 * @author igalk
 */
public class TicTacToeScore implements GameStateScore<TicTacToeMove> {

	private int score;
	
	/**
	 * Constructor.
	 * @param score The numeric score value.
	 */
	public TicTacToeScore(int score) {
		this.score = score;
	}
	
	/**
	 * @see java.lang.Comparable#compareTo(java.lang.Object)
	 */
	public int compareTo(GameStateScore<TicTacToeMove> score) {
		return this.score - ((TicTacToeScore)score).score;
	}
	
	@Override
	public String toString() {
		return Integer.toString(score);
	}

}
