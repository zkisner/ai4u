package com.ai4u.core;

import java.util.List;

/**
 * This is an interface for the game board on which the game is played.
 * 
 * @author kreich
 */
public interface GameState<T extends Move, S extends GameState<T,S,P>, P extends Player<T>>
extends Cloneable {

	/**
	 * This action performs the move on the actual board.
	 * @param move The move to make.
	 * @return The new game state, after the move is made.
	 */
	public S makeMove(T move);
	
	/**
	 * This action performs the move on a copy of the actual board.
	 * @param move The move to make.
	 * @return The new game state, after the move is made.
	 */
	public S simulateMove(T move);

	/**
	 * @param player The player whose turn it is.
	 * @return A List of the currently available moves.
	 */
	public List<T> getMoves(Player<T> player);
	
	/**
	 * @return The player whose next turn it is.
	 */
	public P getNextPlaying();
	
	/**
	 * @return True if the game has ended, false otherwise.
	 */
	public boolean isGameOver();
	
	/**
	 * @return The winner if there is one. <code>null</code> if the game didn't
	 *         end, or if there is a tie. 
	 */
	public P getWinner();
	
	/**
	 * @return Duplicates the game state.
	 */
	public S clone();
	
}
