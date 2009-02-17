package games.display;

import checkers.game.CheckersBoard;
import games.Board;

/**
 * This is an interface for a displayer which can display the current stay of
 * the game.
 * 
 * @author kreich
 */
public interface GameDisplayer {

	/**
	 * Displays the game board.
	 * @param board The board state to display.
	 */
	public void display(Board board);

	/**
	 * Announces the end of the game.
	 * @param board The board.
	 */
	public void gameOver(CheckersBoard board);
	
}
