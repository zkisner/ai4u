/**
 * 10/03/10
 */
package com.ai4u.core.logic.computer.evaluation;

import com.ai4u.core.GameState;

/**
 * @author igalk
 */
public interface GameStateEvaluator {

	/**
	 * @param state The current game state.
	 * @return A score for the given board.
	 */
	public GameStateScore evaluate(GameState state);
	
}
