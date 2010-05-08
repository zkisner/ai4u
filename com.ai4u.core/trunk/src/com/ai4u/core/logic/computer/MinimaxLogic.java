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
//public class MinimaxLogic<T extends Move, E extends GameStateEvaluator<T, GameState<T>, GameStateScore<T>, Player<T>>> implements Logic<T> {
public class MinimaxLogic<T extends Move, S extends GameState<T,S,P>, C extends GameStateScore<T>, P extends Player<T>, E extends GameStateEvaluator<T, S, C, P>>
implements Logic<T,S,P> {

	private E evaluator;
	private final int maxDepth;
	
	public MinimaxLogic(E evaluator, int maxDepth) {
		this.evaluator = evaluator;
		this.maxDepth = maxDepth;
	}

	/**
	 * @see com.ai4u.core.logic.Logic#pickMove(com.ai4u.core.GameState)
	 */
	public T pickMove(S gameState) {
		return innerPickMove(gameState, maxDepth);
	}
	
	private T innerPickMove(S gameState, int depth) {
		P next = gameState.getNextPlaying();
		List<T> moves = gameState.getMoves(next);
		
		// check for moves to make
		if (moves.isEmpty())
			throw new IllegalArgumentException("State has no available moves.");
		
		// set the first move as the temporary best
		T bestMove = moves.get(0);
		GameStateScore<T> bestScore = evaluate(gameState, bestMove, next, depth);
		// compare to the rest of the moves
		for (int i = 1; i < moves.size(); i++) {
			T currentMove = moves.get(i);
			S simulatedMove = gameState.simulateMove(currentMove);
			C currentScore = evaluator.evaluate(simulatedMove, next);
			if (currentScore.compareTo(bestScore) > 0) {
				bestMove = currentMove;
				bestScore = currentScore;
			}
		}
		return bestMove;
	}
	
	private C evaluate(S state, T move, P nextPlayer, int maxDepth) {
		int depth = maxDepth - 1;
		S stateAfterMove = state.simulateMove(move);
		if (depth == 0 || stateAfterMove.isGameOver()) {
			return evaluator.evaluate(stateAfterMove, nextPlayer);
		}

		P next = stateAfterMove.getNextPlaying();
		List<T> nextMoves = stateAfterMove.getMoves(next);
		// check for moves to make
		if (nextMoves.isEmpty())
			throw new IllegalArgumentException("State has no available moves.");
		
		C bestScore = evaluate(stateAfterMove, nextMoves.get(0),
				next, depth);
		for (int i = 1; i < nextMoves.size(); i++) {
			C currScore = evaluate(stateAfterMove,
					nextMoves.get(0), next, depth);
			bestScore = (currScore.compareTo(bestScore) > 0) ?
					currScore : bestScore;
		}
		return bestScore;
	}
	
}
