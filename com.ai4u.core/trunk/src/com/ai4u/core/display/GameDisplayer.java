package com.ai4u.core.display;

import com.ai4u.core.GameState;
import com.ai4u.core.Move;
import com.ai4u.core.Player;

/**
 * This is an interface for a displayer which can display the current stay of
 * the game.
 * 
 * @param <M>
 *            The type of moves.
 * @param <S>
 *            The type of game states.
 * @param <P>
 *            The type of the players.
 * 
 * @author kreich
 */
public interface GameDisplayer<M extends Move, S extends GameState<M, S, P>, P extends Player<M>> {

	/**
	 * Displays the game board.
	 * 
	 * @param board
	 *            The board state to display.
	 */
	public void display(S board);

	/**
	 * Announces the end of the game.
	 * 
	 * @param board
	 *            The board.
	 */
	public void gameOver(S board);

}
