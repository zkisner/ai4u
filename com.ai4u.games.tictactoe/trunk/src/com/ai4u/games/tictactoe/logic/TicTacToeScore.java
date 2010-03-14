/**
 * 14/03/10
 */
package com.ai4u.games.tictactoe.logic;

import com.ai4u.core.logic.computer.evaluation.GameStateScore;

/**
 * @author igalk
 */
public class TicTacToeScore implements GameStateScore {

	private int score;
	
	public TicTacToeScore(int score) {
		this.score = score;
	}
	
	/**
	 * @see java.lang.Comparable#compareTo(java.lang.Object)
	 */
	public int compareTo(GameStateScore score) {
		return this.score - ((TicTacToeScore)score).score;
	}

}
