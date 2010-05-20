package com.ai4u.core;

import java.util.List;

/**
 * This is an interface for the game board on which the game is played.
 * 
 * @param <M> The type of moves.
 * @param <S> The type of game states.
 * @param <P> The type of players.
 *  
 * @author kreich
 */
public interface GameState<M extends Move, S extends GameState<M,S,P>, P extends Player<M>>
extends Cloneable {

	/**
	 * This action performs the move on the actual board.
	 * @param move The move to make.
	 * @return The new game state, after the move is made.
	 */
	public S makeMove(M move);
	
	/**
	 * This action performs the move on a copy of the actual board.
	 * @param move The move to make.
	 * @return The new game state, after the move is made.
	 */
	public S simulateMove(M move);

	/**
	 * @param player The player whose turn it is.
	 * @return A List of the currently available moves.
	 */
	public List<M> getMoves(P player);
	
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
