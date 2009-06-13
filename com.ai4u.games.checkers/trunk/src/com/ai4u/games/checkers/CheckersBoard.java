package com.ai4u.games.checkers;

import static com.ai4u.games.checkers.BitBoardConsts.*;
import java.util.ArrayList;
import java.util.List;

import com.ai4u.core.*;

/**
 * This is an implementation of the {@link Board}  interface for the checkers
 * game.
 * 
 * @author kreich
 */
public class CheckersBoard implements Board {

	/**
	 * @see Board#makeMove(Move)
	 */
	public void makeMove(Move move) {
		throw new UnsupportedOperationException("not yet implemented");
	}

	/**
	 * The checkers game has forced moves when one player can jump over the
	 * other's piece.
	 * If no forced moves are available, simple moves are searched.
	 * 
	 * @see Board#getMoves(Player)
	 */
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
		List<Move> moves = getJumpsUpRight(tools, enemies);
		moves.addAll(getJumpsUpLeft(tools, enemies));
		return moves;
	}
	
	/**
	 * Finds all jumps that can be made by the given tools over the enemies.
	 * First removes all tools in columns g and h and rows 7 and 8 since they
	 * have no room to jump.
	 * Then checks for tools standing next to an enemy (up right).
	 * Finally checks the next step lands in a free square.
	 * 
	 * @param tools The players pieces.
	 * @param enemies The enemy's pieces.
	 * @return A list of the found moves.
	 */
	private List<Move> getJumpsUpRight(long tools, long enemies) {
		long cells = ((((tools & JUMP_UP_RIGHT_MASK) << 9) & enemies) << 9) &
					 ~(w|b|wk|bk);
		
		List<Move> moves = new ArrayList<Move>();
		while (cells != EMPTY) {
			long square = Long.lowestOneBit(cells);
			moves.add(new CheckersMove(square >>> 18, square));
			cells ^= square;
		}
		return moves;
	}
	
	/**
	 * Finds all jumps that can be made by the given tools over the enemies.
	 * First removes all tools in columns a and b and rows 7 and 8 since they
	 * have no room to jump.
	 * Then checks for tools standing next to an enemy (up left).
	 * Finally checks the next step lands in a free square.
	 * 
	 * @param tools The players pieces.
	 * @param enemies The enemy's pieces.
	 * @return A list of the found moves.
	 */
	private List<Move> getJumpsUpLeft(long tools, long enemies) {
		long cells = ((((tools & JUMP_UP_LEFT_MASK) << 7) & enemies) << 7) &
					 ~(w|b|wk|bk);
		
		List<Move> moves = new ArrayList<Move>();
		while (cells != EMPTY) {
			long square = Long.lowestOneBit(cells);
			moves.add(new CheckersMove(square >>> 14, square));
			cells ^= square;
		}
		return moves;
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
	 * First removes all tools in columns g and h and rows 1 and 2 since they
	 * have no room to jump.
	 * Then checks for tools standing next to an enemy (down right).
	 * Finally checks the next step lands in a free square.
	 * 
	 * @param tools The players pieces.
	 * @param enemies The enemy's pieces.
	 * @return A list of the found moves.
	 */
	private List<Move> getJumpsDownRight(long tools, long enemies) {
		long cells = ((((tools & JUMP_DOWN_RIGHT_MASK) >>> 7) & enemies) >>> 7) &
					 ~(w|b|wk|bk);
		
		List<Move> moves = new ArrayList<Move>();
		while (cells != EMPTY) {
			long square = Long.lowestOneBit(cells);
			moves.add(new CheckersMove(square << 14, square));
			cells ^= square;
		}
		return moves;
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
		long cells = ((((tools & JUMP_DOWN_LEFT_MASK) >>> 9) & enemies) >>> 9) &
		             ~(w|b|wk|bk);
		
		List<Move> moves = new ArrayList<Move>();
		while (cells != EMPTY) {
			long square = Long.lowestOneBit(cells);
			moves.add(new CheckersMove(square << 18, square));
			cells ^= square;
		}
		return moves;
	}

	/**
	 * Finds available slide moves for the given player.
	 * Slides are moves when a piece slides over to the next square.
	 * 
	 * @param player The player whose turn it is. 
	 * @return A list of the found moves.
	 */
	private List<Move> getSlides(Player player) {
		List<Move> moves = new ArrayList<Move>();
		if (player.equals(CheckersPlayer.WHITE)) {
			moves.addAll(getSlidesDown(w|wk));
			moves.addAll(getSlidesUp(wk));
		} else {
			moves.addAll(getSlidesUp(b|bk));
			moves.addAll(getSlidesDown(bk));			
		}
		return moves;
	}
	
	/**
	 * @param tools The players pieces.
	 * @return A list of the found moves.
	 */
	private List<Move> getSlidesUp(long tools) {
		List<Move> moves = getSlidesUpRight(tools);
		moves.addAll(getSlidesUpLeft(tools));
		return moves;
	}
	
	/**
	 * Finds all slides that can be made by the given tools.
	 * First removes all tools in column h and row 8 since they have no room to
	 * slide.
	 * Then checks for tools standing next to an empty square (up right).
	 * 
	 * @param tools The players pieces.
	 * @return A list of the found moves.
	 */
	private List<Move> getSlidesUpRight(long tools) {
		long cells = ((tools & SLIDE_UP_RIGHT_MASK) << 9) &
					 ~(w|b|wk|bk);
		
		List<Move> moves = new ArrayList<Move>();
		while (cells != EMPTY) {
			long square = Long.lowestOneBit(cells);
			moves.add(new CheckersMove(square >>> 9, square));
			cells ^= square;
		}
		return moves;
	}
	
	/**
	 * Finds all slides that can be made by the given tools.
	 * First removes all tools in column a and row 8 since they have no room to
	 * slide.
	 * Then checks for tools standing next to an empty square (up left).
	 * 
	 * @param tools The players pieces.
	 * @return A list of the found moves.
	 */
	private List<Move> getSlidesUpLeft(long tools) {
		long cells = ((tools & SLIDE_UP_LEFT_MASK) << 7) &
					 ~(w|b|wk|bk);
		
		List<Move> moves = new ArrayList<Move>();
		while (cells != EMPTY) {
			long square = Long.lowestOneBit(cells);
			moves.add(new CheckersMove(square >>> 7, square));
			cells ^= square;
		}
		return moves;
	}
	
	/**
	 * Finds slides down that can be made by the given tools.
	 * 
	 * @param tools The players pieces.
	 * @return A list of the found moves.
	 */
	private List<Move> getSlidesDown(long tools) {
		List<Move> moves = getSlidesDownRight(tools);
		moves.addAll(getSlidesDownLeft(tools));
		return moves;
	}

	/**
	 * Finds all slides that can be made by the given tools.
	 * First removes all tools in column h and row 1 since they have no room to
	 * slide.
	 * Then checks for tools standing next to an empty square (down right).
	 * 
	 * @param tools The players pieces.
	 * @return A list of the found moves.
	 */
	private List<Move> getSlidesDownRight(long tools) {
		long cells = ((tools & SLIDE_DOWN_RIGHT_MASK) >>> 7) &
					 ~(w|b|wk|bk);
		
		List<Move> moves = new ArrayList<Move>();
		while (cells != EMPTY) {
			long square = Long.lowestOneBit(cells);
			moves.add(new CheckersMove(square << 7, square));
			cells ^= square;
		}
		return moves;
	}
	
	/**
	 * Finds all slides that can be made by the given tools.
	 * First removes all tools in column a and row 1 since they have no room to
	 * slide.
	 * Then checks for tools standing next to an empty square (down left).
	 * 
	 * @param tools The players pieces.
	 * @return A list of the found moves.
	 */
	private List<Move> getSlidesDownLeft(long tools) {
		long cells = ((tools & SLIDE_DOWN_LEFT_MASK) >>> 9) &
		             ~(w|b|wk|bk);
		
		List<Move> moves = new ArrayList<Move>();
		while (cells != EMPTY) {
			long square = Long.lowestOneBit(cells);
			moves.add(new CheckersMove(square << 9, square));
			cells ^= square;
		}
		return moves;
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
