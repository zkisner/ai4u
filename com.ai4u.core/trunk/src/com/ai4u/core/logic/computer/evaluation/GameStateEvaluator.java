/**
 * 10/03/10
 */
package com.ai4u.core.logic.computer.evaluation;

import com.ai4u.core.GameState;
import com.ai4u.core.Move;
import com.ai4u.core.Player;

/**
 * @author igalk
 * 
 * @param <T> the type of {@link Move}s.
 * @param <S> the type of {@link GameState}s.
 * @param <C> the type of {@link GameStateScore}s.
 * @param <P> the type of {@link Player}s.
 */
public interface GameStateEvaluator<T extends Move, S extends GameState<T,S,P>,
		C extends GameStateScore<T>, P extends Player<T>> {

	/**
	 * @param state The current game state.
	 * @param player The player to evaluate for.
	 * @return A score for the given board.
	 */
	public C evaluate(S state, P player);
	
}
