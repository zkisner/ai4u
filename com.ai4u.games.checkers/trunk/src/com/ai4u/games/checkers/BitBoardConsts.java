package com.ai4u.games.checkers;

/**
 * 
 * 
 * @author kreich
 */
public interface BitBoardConsts {
	
	/** Row 1 of the board. */
	public static final int ROW1 = 0;
	/** Row 2 of the board. */
	public static final int ROW2 = 1;
	/** Row 3 of the board. */
	public static final int ROW3 = 2;
	/** Row 4 of the board. */
	public static final int ROW4 = 3;
	/** Row 5 of the board. */
	public static final int ROW5 = 4;
	/** Row 6 of the board. */
	public static final int ROW6 = 5;
	/** Row 7 of the board. */
	public static final int ROW7 = 6;
	/** Row 8 of the board. */
	public static final int ROW8 = 7;
	
	/** Column a of the board. */
	public static final int COLA = 0;
	/** Column b of the board. */
	public static final int COLB = 1;
	/** Column c of the board. */
	public static final int COLC = 2;
	/** Column d of the board. */
	public static final int COLD = 3;
	/** Column e of the board. */
	public static final int COLE = 4;
	/** Column f of the board. */
	public static final int COLF = 5;
	/** Column g of the board. */
	public static final int COLG = 6;
	/** Column h of the board. */
	public static final int COLH = 7;
	
	/**
	 * Each value in the array represents the board, empty, where the index
	 * represents the number of the full row.
	 */
	public static final long[] ROWS_FULL = {
		0x0000000000000055L,
		0x000000000000aa00L,
		0x0000000000550000L,
		0x00000000aa000000L,
		0x0000005500000000L,
		0x0000aa0000000000L,
		0x0055000000000000L,
		0xaa00000000000000L
	};
	
	/**
	 * Each value in the array represents the board, full, where the index
	 * represents the number of the empty row.
	 */
	public static final long[] ROWS_EMPTY = {
		0xaa55aa55aa55aa00L,
		0xaa55aa55aa550055L,
		0xaa55aa55aa00aa55L,
		0xaa55aa550055aa55L,
		0xaa55aa00aa55aa55L,
		0xaa550055aa55aa55L,
		0xaa00aa55aa55aa55L,
		0x0055aa55aa55aa55L
	};
	
	/**
	 * Each value in the array represents the board, empty, where the index
	 * represents the number of the full column.
	 */
	public static final long[] COLS_FULL = {
		0x0001000100010001L,
		0x0200020002000200L,
		0x0004000400040004L,
		0x0800080008000800L,
		0x0010001000100010L,
		0x2000200020002000L,
		0x0040004000400040L,
		0x8000800080008000L
	};
	
	/**
	 * Each value in the array represents the board, full, where the index
	 * represents the number of the empty column.
	 */
	public static final long[] COLS_EMPTY = {
		0xaa54aa54aa54aa54L,
		0xa855a855a855a855L,
		0xaa51aa51aa51aa51L,
		0xa255a255a255a255L,
		0xaa45aa45aa45aa45L,
		0x8a558a558a558a55L,
		0xaa15aa15aa15aa15L,
		0x2a552a552a552a55L
	};
	
	/** The white's initial state - first 3 rows full. */
	public static final long WHITE_INITIAL =
		ROWS_FULL[7] |
		ROWS_FULL[6] |
		ROWS_FULL[5];
	
	/** The black's initial state - last 3 rows full. */
	public static final long BLACK_INITIAL =
		ROWS_FULL[0] |
		ROWS_FULL[1] |
		ROWS_FULL[2];
	
	/** An empty board. */
	public static final long EMPTY = 0L;
	
	/** A full board. */
	public static final long FULL = 0xaa55aa55aa55aa55L;
	
	/**
	 * This is a mask for jumps down left:
	 * columns a and b are empty.
	 * rows 1 and 2 are empty. 
	 */
	public static final long JUMP_DOWN_LEFT_MASK =
		COLS_EMPTY[COLA] & COLS_EMPTY[COLB] & ROWS_EMPTY[ROW1] & ROWS_EMPTY[ROW2];
	
	/**
	 * This is a mask for jumps down right:
	 * columns g and h are empty.
	 * rows 1 and 2 are empty. 
	 */
	public static final long JUMP_DOWN_RIGHT_MASK =
		COLS_EMPTY[COLG] & COLS_EMPTY[COLH] & ROWS_EMPTY[ROW1] & ROWS_EMPTY[ROW2];
	
	/**
	 * This is a mask for jumps up left:
	 * columns a and b are empty.
	 * rows 7 and 8 are empty. 
	 */
	public static final long JUMP_UP_LEFT_MASK =
		COLS_EMPTY[COLA] & COLS_EMPTY[COLB] & ROWS_EMPTY[ROW7] & ROWS_EMPTY[ROW8];
	
	/**
	 * This is a mask for jumps up right:
	 * columns g and h are empty.
	 * rows 7 and 8 are empty. 
	 */
	public static final long JUMP_UP_RIGHT_MASK =
		COLS_EMPTY[COLG] & COLS_EMPTY[COLH] & ROWS_EMPTY[ROW7] & ROWS_EMPTY[ROW8];

	/**
	 * This is a mask for slides down left:
	 * column a is empty.
	 * row 1 is empty. 
	 */
	public static final long SLIDE_DOWN_LEFT_MASK =
		COLS_EMPTY[COLA] & ROWS_EMPTY[ROW1];
	
	/**
	 * This is a mask for slides down right:
	 * column h is empty.
	 * row 1 is empty. 
	 */
	public static final long SLIDE_DOWN_RIGHT_MASK =
		COLS_EMPTY[COLH] & ROWS_EMPTY[ROW1];
	
	/**
	 * This is a mask for slides up left:
	 * column a is empty.
	 * row 8 is empty. 
	 */
	public static final long SLIDE_UP_LEFT_MASK =
		COLS_EMPTY[COLA] & ROWS_EMPTY[ROW8];
	
	/**
	 * This is a mask for slides up right:
	 * column h is empty.
	 * row 8 is empty. 
	 */
	public static final long SLIDE_UP_RIGHT_MASK =
		COLS_EMPTY[COLH] & ROWS_EMPTY[ROW8];

	
}
