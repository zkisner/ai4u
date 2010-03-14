package com.ai4u.games.tictactoe.logic;

import com.ai4u.core.GameState;
import com.ai4u.core.Player;
import com.ai4u.core.logic.computer.evaluation.GameStateEvaluator;
import com.ai4u.core.logic.computer.evaluation.GameStateScore;

public class TicTacToeStateEvaluator implements GameStateEvaluator {

	public GameStateScore evaluate(GameState state, Player player) {
		return state.isGameOver() ?
				getWinnerScore(state, player) :
				new TicTacToeScore(0);
	}

	private GameStateScore getWinnerScore(GameState state, Player player) {
		Player winner = state.getWinner();
		if (winner == null)
			return new TicTacToeScore(0);
		else
			return new TicTacToeScore(player.equals(winner) ? 1 : -1);
	}

}
