package checkers.game;

import static checkers.calcs.BitBoardConsts.*;

import java.util.*;

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
	 * @return A list of the found moves.
	 */
	private List<Move> getJumps(Player player) {
		List<Move> moves = new ArrayList<Move>();
		// find moves for regular pieces
		if (player.equals(CheckersPlayer.WHITE)) {
			moves.addAll(getJumpsDown(w|wk, b|bk));
			moves.addAll(getJumpsUp(wk, b|bk));
		} else {
			moves.addAll(getJumpsUp(b|bk, w|wk));
			moves.addAll(getJumpsDown(bk, w|wk));			
		}
		return moves;
	}

	/**
	 * @param tools The players pieces.
	 * @param enemies The enemy's pieces.
	 * @return A list of the found moves.
	 */
	private List<Move> getJumpsUp(long tools, long enemies) {
		// TODO Auto-generated method stub
		return null;
	}
	
	/**
	 * Finds jumps down that can be made by the given tools over the enemies.
	 * 
	 * @param tools The players pieces.
	 * @param enemies The enemy's pieces.
	 * @return A list of the found moves.
	 */
	private List<Move> getJumpsDown(long tools, long enemies) {
		List<Move> moves = getJumpsDownRight(tools, enemies);
		moves.addAll(getJumpsDownLeft(tools, enemies));
		return moves;
	}

	/**
	 * Finds all jumps that can be made by the given tools over the enemies.
	 * First removes all tools in columns g and h since they have no room to jump.
	 * Then checks for tools standing next to an enemy (down right).
	 * Finally checks the next step lands in a free square.
	 * 
	 * @param tools The players pieces.
	 * @param enemies The enemy's pieces.
	 * @return A list of the found moves.
	 */
	private List<Move> getJumpsDownRight(long tools, long enemies) {
		// TODO Auto-generated method stub
		return null;
	}
	
	/**
	 * Finds all jumps that can be made by the given tools over the enemies.
	 * First removes all tools in columns a and b and rows 1 and 2 since they
	 * have no room to jump.
	 * Then checks for tools standing next to an enemy (down left).
	 * Finally checks the next step lands in a free square.
	 * 
	 * @param tools The players pieces.
	 * @param enemies The enemy's pieces.
	 * @return A list of the found moves.
	 */
	private List<Move> getJumpsDownLeft(long tools, long enemies) {
//		long cells = (tools & JUMP_DOWN_LEFT_MASK);
		return null;
	}

	/**
	 * Finds available slide moves for the given player.
	 * Slides are moves when a piece slides over to the next square.
	 * 
	 * @param player The player whose turn it is. 
	 * @return A list of the found moves.
	 */
	private List<Move> getSlides(Player player) {
		// TODO Auto-generated method stub
		return null;
	}
	
	/** @return The white's state. */
	public long getW() { return w; }
	
	/** @return The white king's state. */
	public long getWk() { return wk; }
	
	/** @return The black's state. */
	public long getB() { return b; }
	
	/** @return The black king's state. */
	public long getBk() { return bk; }
	
	/** The white's board state. */
	private long w = WHITE_INITIAL;
	
	/** The black's board state. */
	private long b = BLACK_INITIAL;
	
	/** The white king's board state. */
	private long wk = EMPTY;
	
	/** The black king's board state. */
	private long bk = EMPTY;

}
