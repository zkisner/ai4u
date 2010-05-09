package com.ai4u.games.tictactoe.logic;

import com.ai4u.core.logic.computer.evaluation.GameStateEvaluator;
import com.ai4u.games.tictactoe.*;

/**
 * An evaluator for {@link ITicTacToeBoard}s.
 * 
 * @author igalk
 */
public class TicTacToeStateEvaluator
implements GameStateEvaluator<TicTacToeMove, ITicTacToeBoard, TicTacToeScore, TicTacToePlayer> {

	public TicTacToeScore evaluate(ITicTacToeBoard state, TicTacToePlayer player) {
		return state.isGameOver() ?
				getWinnerScore(state, player) :
				new TicTacToeScore(0);
	}

	private TicTacToeScore getWinnerScore(ITicTacToeBoard state, TicTacToePlayer player) {
		TicTacToePlayer winner = state.getWinner();
		if (winner == null) {
			return new TicTacToeScore(0);
		}
		return new TicTacToeScore(player.equals(winner) ? 1 : -1);
	}

}
