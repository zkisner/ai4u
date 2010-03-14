/**
 * 11/03/10
 */
package com.ai4u.games.tictactoe.tests;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import org.junit.Before;
import org.junit.Test;

import com.ai4u.games.tictactoe.ITicTacToeBoard;
import com.ai4u.games.tictactoe.TicTacToeBoard;

/**
 * @author igalk
 */
public class TicTacToeGameTest extends TicTacToeBoard {

	public TicTacToeGameTest() {
		super(3);
	}

//	private TicTacToeBoard board;
//	private TicTacToeGame game;

	@Before
	public void setUp() {
//		board = new TestBoard(3);
//		game = new TicTacToeGame(board, null, null, null);
	}

	/**
	 * Test method for
	 * {@link com.ai4u.games.tictactoe.TicTacToeGame#isGameOver()}.
	 */
	@Test
	public void testIsGameOver() {
		fillCell(0, 0);
	}

	private void fillCell(int i, int j) {
		if (j == size) {
			checkBoard();
		} else if (i == size) {
			fillCell(0, j + 1);
		} else {
			cells[i][j] = ITicTacToeBoard.EMPTY;
			fillCell(i + 1, j);
			cells[i][j] = ITicTacToeBoard.X;
			fillCell(i + 1, j);
			cells[i][j] = ITicTacToeBoard.O;
			fillCell(i + 1, j);
		}
	}

	private void checkBoard() {
		if (isFullBoard()) {
			assertTrue("Board is full:\n" + printGame(), isGameOver());
			return;
		}

		// check rows
		for (int row = 0; row < size; row++) {
			if (cells[row][0] == ITicTacToeBoard.EMPTY)
				continue;

			boolean shouldCheck = true;
			for (int col = 1; col < size; col++) {
				if (cells[row][col] != cells[row][0]) {
					shouldCheck = false;
					break;
				}
			}
			if (shouldCheck) {
				assertTrue("Row " + row + " is full", isGameOver());
				return;
			}
		}
		// check columns
		for (int col = 0; col < size; col++) {
			if (cells[0][col] == ITicTacToeBoard.EMPTY)
				continue;

			boolean shouldCheck = true;
			for (int row = 1; row < size; row++) {
				if (cells[row][col] != cells[0][col]) {
					shouldCheck = false;
					break;
				}
			}
			if (shouldCheck) {
				assertTrue("Column " + col + " is full", isGameOver());
				return;
			}
		}
		// check main diagonal
		if (cells[0][0] != ITicTacToeBoard.EMPTY) {
			boolean shouldCheck = true;
			for (int i = 1; i < size; i++) {
				if (cells[i][i] != cells[0][0]) {
					shouldCheck = false;
					break;
				}
			}
			if (shouldCheck) {
				assertTrue("Main diagonal is full", isGameOver());
				return;
			}
		}
		// check secondary diagonal
		if (cells[0][size-1] != ITicTacToeBoard.EMPTY) {
			boolean shouldCheck = true;
			for (int i = 1; i < size; i++) {
				if (cells[i][size - 1 - i] != cells[0][size-1]) {
					shouldCheck = false;
					break;
				}
			}
			if (shouldCheck) {
				assertTrue("Secondary diagonal is full:\n" + printGame(), isGameOver());
				return;
			}
		}
		assertFalse("Game is not over:\n" + printGame(), isGameOver());
	}

	private String printGame() {
		StringBuilder sb = new StringBuilder();
		
		for (int i = 0; i < size; i++) {
			for (int j = 0; j < size; j++) {
				switch (cells[i][j]) {
				case ITicTacToeBoard.EMPTY:
					sb.append('_');
					break;

				case ITicTacToeBoard.X:
					sb.append('X');
					break;
					
				case ITicTacToeBoard.O:
					sb.append('O');
					break;
				}
				sb.append(' ');
			}
			sb.append('\n');
		}
		return sb.toString();
	}

	private boolean isFullBoard() {
		for (int i = 0; i < size; i++) {
			for (int j = 0; j < size; j++) {
				if (cells[i][j] == ITicTacToeBoard.EMPTY)
					return false;
			}
		}
		return true;
	}

//	private static class TestBoard implements ITicTacToeBoard {
//
//		private int size;
//		public int[][] cells;
//
//		public TestBoard(int size) {
//			this.size = size;
//			cells = new int[size][];
//			for (int i = 0; i < size; i++) {
//				cells[i] = new int[size];
//				for (int j = 0; j < size; j++) {
//					cells[i][j] = EMPTY;
//				}
//			}
//		}
//
//		public int getCell(int row, int col) {
//			return cells[row][col];
//		}
//
//		public int getSize() {
//			return size;
//		}
//
//		public List<Move> getMoves(Player player) {
//			throw new RuntimeException("should not be used");
//		}
//
//		public Player getNextPlaying() {
//			throw new RuntimeException("should not be used");
//		}
//
//		public GameState makeMove(Move move) {
//			throw new RuntimeException("should not be used");
//		}
//
//		public GameState simulateMove(Move move) {
//			throw new RuntimeException("should not be used");
//		}
//
//		@Override
//		public GameState clone() {
//			return null;
//		}
//	}

}
