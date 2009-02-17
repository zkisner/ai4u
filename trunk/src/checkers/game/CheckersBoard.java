package checkers.game;

import static checkers.calcs.BitBoardConsts.*;

import java.util.List;

import games.Board;
import games.Move;

/**
 * This is an implementation of the {@link Board}  interface for the checkers
 * game.
 * 
 * @author kreich
 */
public class CheckersBoard implements Board {

	/**
	 * @see games.Board#makeMove(games.Move)
	 */
	@Override
	public void makeMove(Move move) {
		// TODO not yet implemented
	}
	
	/**
	 * @see games.Board#getMoves()
	 */
	@Override
	public List<Move> getMoves() {
		// TODO not yet implemented
		return null;
	}
	
	/** @return The white's state. */
	public int getW() { return w; }
	
	/** @return The white checkers' state. */
	public int getWc() { return wc; }
	
	/** @return The black's state. */
	public int getB() { return b; }
	
	/** @return The black checkers' state. */
	public int getBc() { return bc; }
	
	/** The white's board state. */
	private int w = WHITE_INITIAL;
	
	/** The black's board state. */
	private int b = BLACK_INITIAL;
	
	/** The white checkers' board state. */
	private int wc = EMPTY;
	
	/** The black checkers' board state. */
	private int bc = EMPTY;

}
