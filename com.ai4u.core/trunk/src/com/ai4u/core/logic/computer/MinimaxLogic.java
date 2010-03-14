/**
 * 10/03/10
 */
package com.ai4u.core.logic.computer;

import java.util.List;

import com.ai4u.core.GameState;
import com.ai4u.core.Move;
import com.ai4u.core.Player;
import com.ai4u.core.logic.Logic;
import com.ai4u.core.logic.computer.evaluation.GameStateEvaluator;
import com.ai4u.core.logic.computer.evaluation.GameStateScore;

/**
 * @author igalk
 */
public class MinimaxLogic implements Logic {

	private GameStateEvaluator evaluator;
	
	public MinimaxLogic(GameStateEvaluator evaluator) {
		this.evaluator = evaluator;
	}

	/**
	 * @see com.ai4u.core.logic.Logic#pickMove(com.ai4u.core.GameState)
	 */
	public Move pickMove(GameState gameState) {
		Player next = gameState.getNextPlaying();
		List<Move> moves = gameState.getMoves(next);
		
		// check for moves to make
		if (moves.isEmpty())
			throw new IllegalArgumentException("State has no available moves.");
		
		// set the first move as the temporary best
		Move bestMove = moves.get(0);
		GameStateScore bestScore = evaluator.evaluate(
				gameState.simulateMove(bestMove),
				next);
		// compare to the rest of the moves
		for (int i = 1; i < moves.size(); i++) {
			GameStateScore currScore = evaluator.evaluate(
					gameState.simulateMove(moves.get(i)),
					next);
			if (currScore.compareTo(bestScore) > 0) {
				bestMove = moves.get(i);
				bestScore = currScore;
			}
		}
		return bestMove;
	}
}
