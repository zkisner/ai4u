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

	private ITicTacToeBoard board;
	private TicTacToeGame game;

	@Before
	public void setUp() {
		board = new TestBoard(3);
		game = new TicTacToeGame(board, null, null, null);
	}
	
	/**
	 * Test method for {@link com.ai4u.games.tictactoe.TicTacToeGame#isGameOver()}.
	 */
	@Test
	public void testIsGameOver() {
		// check rows
		for (int i = 0; i < board.getSize(); i++) {
			checkRowForPlayer(i, ITicTacToeBoard.X, ITicTacToeBoard.O);
			checkRowForPlayer(i, ITicTacToeBoard.O, ITicTacToeBoard.X);
		}
		fail("Not yet implemented");
	}

	private void checkRowForPlayer(int row, char player, char otherPlayer) {
		
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
