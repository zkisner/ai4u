package checkers.calcs;

/**
 * 
 * 
 * @author igalk
 */
public interface BitBoardConsts {
	
	/**
	 * Each value in the array represents the board, empty, where the index
	 * represents the number of the full row.
	 */
	public static final int[] ROWS_FULL = {
		0xf0000000,
		0x0f000000,
		0x00f00000,
		0x000f0000,
		0x0000f000,
		0x00000f00,
		0x000000f0,
		0x0000000f
	};
	
	/**
	 * Each value in the array represents the board, empty, where the index
	 * represents the number of the full column.
	 */
	public static final int[] COLS_FULL = {
		0x80808080,
		0x08080808,
		0x40404040,
		0x04040404,
		0x20202020,
		0x02020202,
		0x10101010,
		0x01010101
	};
	
	/**
	 * Each value in the array represents the board, full, where the index
	 * represents the number of the empty column.
	 */
	public static final int[] COLS_EMPTY = {
		0x7f7f7f7f,
		0xf7f7f7f7,
		0xbfbfbfbf,
		0xfbfbfbfb,
		0xdfdfdfdf,
		0xfdfdfdfd,
		0xefefefef,
		0xfefefefe
	};
	
	/** The white's initial state - first 3 rows full. */
	public static final int WHITE_INITIAL =
		ROWS_FULL[0] |
		ROWS_FULL[1] |
		ROWS_FULL[2];
	
	/** The black's initial state - last 3 rows full. */
	public static final int BLACK_INITIAL =
		ROWS_FULL[7] |
		ROWS_FULL[6] |
		ROWS_FULL[5];
	
	/** An empty board. */
	public static final int EMPTY = 0;
	
	/** A full board. */
	public static final int FULL = 0xffffffff;
	
}
