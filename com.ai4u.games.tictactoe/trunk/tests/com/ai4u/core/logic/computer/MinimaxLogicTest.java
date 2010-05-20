package com.ai4u.core.logic.computer;
import org.junit.Assert;
import org.junit.Test;

import com.ai4u.core.logic.Logic;
import com.ai4u.games.tictactoe.*;
import com.ai4u.games.tictactoe.logic.TicTacToeScore;
import com.ai4u.games.tictactoe.logic.TicTacToeStateEvaluator;

/**
 * A test class that checks some of the minimax logic.
 * 
 * @author igalk
 */
public class MinimaxLogicTest {

	private Logic<TicTacToeMove, ITicTacToeBoard, TicTacToePlayer> logic =
		new MinimaxLogic<TicTacToeMove, ITicTacToeBoard, TicTacToeScore, TicTacToePlayer, TicTacToeStateEvaluator>(
			new TicTacToeStateEvaluator(), 9);

	/**
	 * Checks that the white makes a saving move and wins in 2 turns. checks the
	 * board:
	 *  [o,x,x]
	 *  [o,o,x]
	 *  [x, , ]
	 */
	@Test
	public void checkXWins() {
		TicTacToeBoard board = new TicTacToeBoard(3);
		board.makeMove(new TicTacToeMove(0,1));
		board.makeMove(new TicTacToeMove(0,0));
		board.makeMove(new TicTacToeMove(0,2));
		board.makeMove(new TicTacToeMove(1,0));
		board.makeMove(new TicTacToeMove(1,2));
		board.makeMove(new TicTacToeMove(1,1));
		board.makeMove(new TicTacToeMove(2,0));
		
		Assert.assertFalse(board.isGameOver());
		TicTacToeMove move = logic.pickMove(board);
		Assert.assertEquals(new TicTacToeMove(2,2), move);
	}
	
	/**
	 * Checks that the white makes a saving move and wins in 2 turns. checks the
	 * board:
	 *  [o,x, ]
	 *  [x, , ]
	 *  [ , ,o]
	 */
	@Test
	public void checkXWinIn2Moves() {
		TicTacToeBoard board = new TicTacToeBoard(3);
		board.makeMove(new TicTacToeMove(0,1));
		board.makeMove(new TicTacToeMove(0,0));
		board.makeMove(new TicTacToeMove(1,0));
		board.makeMove(new TicTacToeMove(2,2));
		
		Assert.assertFalse(board.isGameOver());
		TicTacToeMove move = logic.pickMove(board);
		Assert.assertEquals(new TicTacToeMove(1,1), move);
	}
	
	/**
	 * Checks that the white makes a saving move,
	 *  [x,o,x]
	 *  [ , , ]
	 *  [ ,o, ]
	 */
	@Test
	public void checkXSaves() {
		TicTacToeBoard board = new TicTacToeBoard(3);
		board.makeMove(new TicTacToeMove(0,0));
		board.makeMove(new TicTacToeMove(0,1));
		board.makeMove(new TicTacToeMove(0,2));
		board.makeMove(new TicTacToeMove(2,1));
		
		Assert.assertFalse(board.isGameOver());
		TicTacToeMove move = logic.pickMove(board);
		Assert.assertEquals(new TicTacToeMove(1,1), move);
	}

	/**
	 * Checks that o makes a saving move,
	 *  [x, ,x]
	 *  [ , , ]
	 *  [o, , ]
	 */
	@Test
	public void checkOSaves() {
		TicTacToeBoard board = new TicTacToeBoard(3);
		board.makeMove(new TicTacToeMove(0,0));
		board.makeMove(new TicTacToeMove(2,0));
		board.makeMove(new TicTacToeMove(0,2));
		
		Assert.assertFalse(board.isGameOver());
		TicTacToeMove move = logic.pickMove(board);
		Assert.assertEquals(new TicTacToeMove(0,1), move);
	}
	
}
