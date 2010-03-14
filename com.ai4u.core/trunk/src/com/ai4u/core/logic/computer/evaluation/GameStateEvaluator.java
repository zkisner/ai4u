/**
 * 10/03/10
 */
package com.ai4u.core.logic.computer.evaluation;

import com.ai4u.core.GameState;
import com.ai4u.core.Player;

/**
 * @author igalk
 */
public interface GameStateEvaluator {

	/**
	 * @param state The current game state.
	 * @param player The player to evaluate for.
	 * @return A score for the given board.
	 */
	public GameStateScore evaluate(GameState state, Player player);
	
}
