/**
 * 10/03/10
 */
package com.ai4u.core.logic.computer;

import java.util.List;

import com.ai4u.core.*;
import com.ai4u.core.logic.Logic;
import com.ai4u.core.logic.computer.evaluation.GameStateEvaluator;
import com.ai4u.core.logic.computer.evaluation.GameStateScore;

/**
 * @author igalk
 * @param <M>
 *            The type of moves.
 * @param <S>
 *            The type of game states.
 * @param <C>
 *            The type of scores.
 * @param <P>
 *            The type of players.
 * @param <E>
 *            The type of evaluator.
 */
public class MinimaxLogic<
	M extends Move,
	S extends GameState<M,S,P>,
	C extends GameStateScore<M>,
	P extends Player<M>,
	E extends GameStateEvaluator<M, S, C, P>>
implements Logic<M,S,P> {

	private E evaluator;
	private final int maxDepth;

	/**
	 * Constructor.
	 * 
	 * @param evaluator
	 *            The {@link GameState} evaluator that will be used by the
	 *            minimax algorithm.
	 * @param maxDepth
	 *            The max search depth.
	 */
	public MinimaxLogic(E evaluator, int maxDepth) {
		this.evaluator = evaluator;
		this.maxDepth = maxDepth;
	}

	/**
	 * @see com.ai4u.core.logic.Logic#pickMove(com.ai4u.core.GameState)
	 */
	public M pickMove(S gameState) {
		P next = gameState.getNextPlaying();
		List<M> moves = gameState.getMoves(next);
		
		// check for moves to make
		if (moves.isEmpty())
			throw new IllegalArgumentException("State has no available moves.");
		
		if (moves.size() == 1)
			return moves.get(0);
		
		// set the first move as the temporary best
		M bestMove = moves.get(0);
		GameStateScore<M> bestScore = evaluate(gameState, bestMove, next,
				maxDepth);
		// compare to the rest of the moves
		for (int i = 1; i < moves.size(); i++) {
			M currentMove = moves.get(i);
			C currentScore = evaluate(gameState, currentMove, next, maxDepth);
			if (currentScore.compareTo(bestScore) > 0) {
				bestMove = currentMove;
				bestScore = currentScore;
			}
		}
		return bestMove;
	}
	
	private C evaluate(S state, M move, P nextPlayer, int maxDepth) {
		int depth = maxDepth - 1;
		S stateAfterMove = state.simulateMove(move);
		if (depth == 0 || stateAfterMove.isGameOver()) {
			return evaluator.evaluate(stateAfterMove, nextPlayer);
		}

		P next = stateAfterMove.getNextPlaying();
		List<M> nextMoves = stateAfterMove.getMoves(next);
		// check for moves to make
		if (nextMoves.isEmpty())
			throw new IllegalArgumentException("State has no available moves.");
		
		C bestScore = evaluate(stateAfterMove, nextMoves.get(0), nextPlayer,
				depth);
		for (int i = 1; i < nextMoves.size(); i++) {
			C currScore = evaluate(stateAfterMove, nextMoves.get(0), nextPlayer,
					depth);
			bestScore = (currScore.compareTo(bestScore) > 0) ?
					currScore : bestScore;
		}
		return bestScore;
	}
	
}
