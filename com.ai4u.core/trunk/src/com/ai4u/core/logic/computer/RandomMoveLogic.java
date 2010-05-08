package com.ai4u.core.logic.computer;

import com.ai4u.core.*;
import com.ai4u.core.logic.Logic;
import com.ai4u.util.RandomUtils;

/**
 * This computer logic picks a random move from the available moves.
 * 
 * @author kreich
 */
public class RandomMoveLogic<T extends Move, S extends GameState<T,S,P>, P extends Player<T>> implements Logic<T,S,P> {
	
	/** The player whose moves this logic attends. */
	private Player<T> role;
	
	/** A random generator for this logic. */
	private RandomUtils rand = new RandomUtils();

	/**
	 * Constructor.
	 * @param role The player whose moves this logic attends.
	 */
	public RandomMoveLogic(Player<T> role) {
		this.role = role;
	}
	
	/**
	 * @see com.ai4u.core.logic.Logic#pickMove(com.ai4u.core.GameState)
	 */
	public T pickMove(S board) {
		return rand.pickRandom(board.getMoves(role));
	}
	
}