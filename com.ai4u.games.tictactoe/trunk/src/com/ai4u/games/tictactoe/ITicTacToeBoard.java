/**
 * 11/03/10
 */
package com.ai4u.games.tictactoe;

import com.ai4u.core.GameState;

/**
 * @author igalk
 */
public interface ITicTacToeBoard extends GameState {

	/** An empty cell. */
	public static final char EMPTY = ' ';

	/** A cell occupied by X. */
	public static final char X = 'X';

	/** A cell occupied by O. */
	public static final char O = 'O';

	/**
	 * @return The size, n, of the board, when the board has n*n cells.
	 */
	public int getSize();

	/**
	 * @param row The cell row.
	 * @param col The cell column.
	 * @return The state of the cell: Empty, X or O.
	 */
	public int getCell(int row, int col);

}
