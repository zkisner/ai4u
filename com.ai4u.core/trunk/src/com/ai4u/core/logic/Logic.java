package com.ai4u.core.logic;

import com.ai4u.core.GameState;
import com.ai4u.core.Move;

/**
 * This is an interface for playing logics (anything that can decide how to make
 * a move).
 * 
 * @author kreich
 */
public interface Logic {

	/**
	 * @param board The board the moves can be played upon.
	 * @return The best move this logic can get.
	 */
	public Move pickMove(GameState board);
	
}
