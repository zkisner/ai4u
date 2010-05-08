package com.ai4u.core.logic;

import com.ai4u.core.GameState;
import com.ai4u.core.Move;
import com.ai4u.core.Player;

/**
 * This is an interface for playing logics (anything that can decide how to make
 * a move).
 * 
 * @author kreich
 */
public interface Logic<T extends Move,S extends GameState<T,S,P>, P extends Player<T>> {

	/**
	 * @param board The board the moves can be played upon.
	 * @return The best move this logic can get.
	 */
	public T pickMove(S board);
	
}
