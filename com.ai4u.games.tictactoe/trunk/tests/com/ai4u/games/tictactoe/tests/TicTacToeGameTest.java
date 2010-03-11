/**
 * 11/03/10
 */
package com.ai4u.games.tictactoe.tests;

import static org.junit.Assert.*;

import java.util.List;

import org.junit.Before;
import org.junit.Test;

import com.ai4u.core.GameState;
import com.ai4u.core.Move;
import com.ai4u.core.Player;
import com.ai4u.games.tictactoe.ITicTacToeBoard;
import com.ai4u.games.tictactoe.TicTacToeGame;

/**
 * @author igalk
 */
public class TicTacToeGameTest {

	private TestBoard board;
	private TicTacToeGame game;

	@Before
	public void setUp() {
		board = new TestBoard(3);
		game = new TicTacToeGame(board, null, null, null);
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
		if (j == board.size) {
			checkBoard();
		} else if (i == board.size) {
			fillCell(0, j + 1);
		} else {
			board.cells[i][j] = ITicTacToeBoard.EMPTY;
			fillCell(i + 1, j);
			board.cells[i][j] = ITicTacToeBoard.X;
			fillCell(i + 1, j);
			board.cells[i][j] = ITicTacToeBoard.O;
			fillCell(i + 1, j);
		}
	}

	private void checkBoard() {
		if (isFullBoard()) {
			assertTrue("Board is full:\n" + printGame(), game.isGameOver());
			return;
		}

		// check rows
		for (int row = 0; row < board.size; row++) {
			if (board.cells[row][0] == ITicTacToeBoard.EMPTY)
				continue;

			boolean shouldCheck = true;
			for (int col = 1; col < board.size; col++) {
				if (board.cells[row][col] != board.cells[row][0]) {
					shouldCheck = false;
					break;
				}
			}
			if (shouldCheck) {
				assertTrue("Row " + row + " is full", game.isGameOver());
				return;
			}
		}
		// check columns
		for (int col = 0; col < board.size; col++) {
			if (board.cells[0][col] == ITicTacToeBoard.EMPTY)
				continue;

			boolean shouldCheck = true;
			for (int row = 1; row < board.size; row++) {
				if (board.cells[row][col] != board.cells[0][col]) {
					shouldCheck = false;
					break;
				}
			}
			if (shouldCheck) {
				assertTrue("Column " + col + " is full", game.isGameOver());
				return;
			}
		}
		// check main diagonal
		if (board.cells[0][0] != ITicTacToeBoard.EMPTY) {
			boolean shouldCheck = true;
			for (int i = 1; i < board.size; i++) {
				if (board.cells[i][i] != board.cells[0][0]) {
					shouldCheck = false;
					break;
				}
			}
			if (shouldCheck) {
				assertTrue("Main diagonal is full", game.isGameOver());
				return;
			}
		}
		// check secondary diagonal
		if (board.cells[0][board.size-1] != ITicTacToeBoard.EMPTY) {
			boolean shouldCheck = true;
			for (int i = 1; i < board.size; i++) {
				if (board.cells[i][board.size - 1 - i] != board.cells[0][board.size-1]) {
					shouldCheck = false;
					break;
				}
			}
			if (shouldCheck) {
				assertTrue("Secondary diagonal is full:\n" + printGame(), game.isGameOver());
				return;
			}
		}
		assertFalse("Game is not over:\n" + printGame(), game.isGameOver());
	}

	private String printGame() {
		StringBuilder sb = new StringBuilder();
		
		for (int i = 0; i < board.size; i++) {
			for (int j = 0; j < board.size; j++) {
				switch (board.cells[i][j]) {
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
		for (int i = 0; i < board.size; i++) {
			for (int j = 0; j < board.size; j++) {
				if (board.cells[i][j] == ITicTacToeBoard.EMPTY)
					return false;
			}
		}
		return true;
	}

	private static class TestBoard implements ITicTacToeBoard {

		private int size;
		public int[][] cells;

		public TestBoard(int size) {
			this.size = size;
			cells = new int[size][];
			for (int i = 0; i < size; i++) {
				cells[i] = new int[size];
				for (int j = 0; j < size; j++) {
					cells[i][j] = EMPTY;
				}
			}
		}

		public int getCell(int row, int col) {
			return cells[row][col];
		}

		public int getSize() {
			return size;
		}

		public List<Move> getMoves(Player player) {
			throw new RuntimeException("should not be used");
		}

		public Player getNextPlaying() {
			throw new RuntimeException("should not be used");
		}

		public GameState makeMove(Move move) {
			throw new RuntimeException("should not be used");
		}

		public GameState simulateMove(Move move) {
			throw new RuntimeException("should not be used");
		}

		@Override
		public GameState clone() {
			return null;
		}
	}

}
