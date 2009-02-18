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
		// find moves for regular pieces
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
	
	/** @return The white king's state. */
	public int getWk() { return wk; }
	
	/** @return The black's state. */
	public int getB() { return b; }
	
	/** @return The black king's state. */
	public int getBk() { return bk; }
	
	/** The white's board state. */
	private int w = WHITE_INITIAL;
	
	/** The black's board state. */
	private int b = BLACK_INITIAL;
	
	/** The white king's board state. */
	private int wk = EMPTY;
	
	/** The black king's board state. */
	private int bk = EMPTY;

}
