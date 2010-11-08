package com.ai4u.games.mankala;

import com.ai4u.core.Move;

/**
 * A game move in mankala.
 * 
 * @author igalk
 */
public class MankalaMove implements Move {

	/** The cup to move from. */
	private int cup;

	/**
	 * Constructor.
	 * @param cup The cup to move from.
	 */
	public MankalaMove(int cup) {
		this.cup = cup;
	}
	
	/**
	 * @return The cup.
	 */
	public int getCup() {
		return cup;
	}
	
}
