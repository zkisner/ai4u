package com.ai4u.core.logic.computer;

import com.ai4u.core.*;
import com.ai4u.core.logic.Logic;
import com.ai4u.util.RandomUtils;

/**
 * This computer logic picks a random move from the available moves.
 * 
 * @param <M> The type of moves.
 * @param <S> The type of game states.
 * @param <P> The type of players.
 *  
 * @author kreich
 */
public class RandomMoveLogic<M extends Move, S extends GameState<M,S,P>, P extends Player<M>> implements Logic<M,S,P> {
	
	/** The player whose moves this logic attends. */
	private Player<M> role;
	
	/** A random generator for this logic. */
	private RandomUtils rand = new RandomUtils();

	/**
	 * Constructor.
	 * @param role The player whose moves this logic attends.
	 */
	public RandomMoveLogic(Player<M> role) {
		this.role = role;
	}
	
	/**
	 * @see com.ai4u.core.logic.Logic#pickMove(com.ai4u.core.GameState)
	 */
	public M pickMove(S board) {
		return rand.pickRandom(board.getMoves(role));
	}
	
}