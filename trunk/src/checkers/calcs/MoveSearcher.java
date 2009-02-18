package checkers.calcs;

import static checkers.calcs.BitBoardConsts.*;

import java.util.ArrayList;
import java.util.List;

import checkers.game.CheckersBoard;

/**
 * 
 * 
 * @author igalk
 */
public class MoveSearcher {

	public static List<CheckersBoard> findWhiteMoves(CheckersBoard board) {
		// Get moves right
		//  remove tools on right-most column,
		//  then shift the cells 4 right
		//  and cross the results with the empty cells on the board
		int possible = ((board.getW() & COLS_EMPTY[7]) >> 4) &
						findEmptyCells(board);
		
		List<CheckersBoard> moves = new ArrayList<CheckersBoard>();
		
		return moves;
	}
	
	/**
	 * Adds up all taken cells, then inverts the bits to light the free ones.
	 * @param board The board to search the cells on.
	 * @return A board where each lit bit represents an empty cell on the board.
	 */
	public static int findEmptyCells(CheckersBoard board) {
		return (board.getW() |
				board.getWk() |
				board.getB() |
				board.getBk()) ^ FULL;
	}
	
}
