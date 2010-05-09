/**
 * 10/03/10
 */
package com.ai4u.core.logic.computer.evaluation;

import com.ai4u.core.Move;

/**
 * @param <M> The type of moves.
 *  
 * @author igalk
 */
public interface GameStateScore<M extends Move>
extends Comparable<GameStateScore<M>> {
	// nothing here
}
