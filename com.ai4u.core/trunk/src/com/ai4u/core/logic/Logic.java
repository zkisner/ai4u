package com.ai4u.core.logic;

import com.ai4u.core.*;

/**
 * This is an interface for playing logics (anything that can decide how to make
 * a move).
 * 
 * @param <M> The type of moves.
 * @param <S> The type of game states.
 * @param <P> The type of players.
 *  
 * @author kreich
 */
public interface Logic<M extends Move,S extends GameState<M,S,P>, P extends Player<M>> {

	/**
	 * @param board The board the moves can be played upon.
	 * @return The best move this logic can get.
	 */
	public M pickMove(S board);
	
}
