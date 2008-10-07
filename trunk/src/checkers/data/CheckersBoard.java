package checkers.data;

import static checkers.calcs.BitBoardConsts.*;

/**
 * 
 * 
 * @author igalk
 */
public class CheckersBoard {

	/**
	 * @return The white's state.
	 */
	public int getW() { return w; }
	
	/**
	 * @return The white checkers' state.
	 */
	public int getWc() { return wc; }
	
	/**
	 * @return The black's state.
	 */
	public int getB() { return b; }
	
	/**
	 * @return The black checkers' state.
	 */
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
