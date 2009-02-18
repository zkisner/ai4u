package checkers.game;

import static checkers.calcs.BitBoardConsts.*;

import java.util.List;

import games.Board;
import games.Move;
import games.Player;

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
	 * The checkers game has forced moves when one player can jump over the
	 * other's piece.
	 * If no forced moves are available, simple moves are searched.
	 * 
	 * @see games.Board#getMoves(games.Player)
	 */
	@Override
	public List<Move> getMoves(Player player) {
		List<Move> moves = getJumps(player);
		if (! moves.isEmpty()) {
			return moves;
		}
		return getSlides(player);
	}
	
	/**
	 * Finds available jump moves for the given player.
	 * Jumps are moves when a piece jumps over another one and kills it.
	 * 
	 * @param player The player whose turn it is. 
	 */
	private List<Move> getJumps(Player player) {
		// TODO Auto-generated method stub
		return null;
	}

	/**
	 * Finds available slide moves for the given player.
	 * Slides are moves when a piece slides over to the next square.
	 * 
	 * @param player The player whose turn it is. 
	 */
	private List<Move> getSlides(Player player) {
		// TODO Auto-generated method stub
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
