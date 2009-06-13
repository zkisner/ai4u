package com.ai4u.core.logic.computer;

import com.ai4u.core.*;
import com.ai4u.util.RandomUtils;

/**
 * This computer logic picks a random move from the available moves.
 * 
 * @author kreich
 */
public class RandomMoveLogic implements Logic {

	/**
	 * Constructor.
	 * @param role The player whose moves this logic attends.
	 */
	public RandomMoveLogic(Player role) {
		this.role = role;
	}
	
	/**
	 * @see com.ai4u.core.Logic#pickMove(com.ai4u.core.Board)
	 */
	public Move pickMove(Board board) {
		return rand.pickRandom(board.getMoves(role));
	}
	
	/** The player whose moves this logic attends. */
	private Player role;
	
	/** A random generator for this logic. */
	private RandomUtils rand = new RandomUtils();

}