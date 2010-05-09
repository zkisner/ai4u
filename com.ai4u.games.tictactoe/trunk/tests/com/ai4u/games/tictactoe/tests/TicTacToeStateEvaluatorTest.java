package com.ai4u.games.tictactoe.tests;

import org.junit.Assert;
import org.junit.Test;

import com.ai4u.games.tictactoe.*;
import com.ai4u.games.tictactoe.logic.TicTacToeScore;
import com.ai4u.games.tictactoe.logic.TicTacToeStateEvaluator;


/**
 * Test for the {@link TicTacToeStateEvaluator}.
 * 
 * @author igalk
 */
public class TicTacToeStateEvaluatorTest {
	
	TicTacToeStateEvaluator evaluator = new TicTacToeStateEvaluator();
	
	/**
	 * Tests that the evaluator recognizes a X win and checks the given score
	 * for the board:
	 * [x,o, ]
	 * [x,o, ]
	 * [x, , ]
	 */
	@Test
	public void checkWhiteWin() {
		TicTacToeBoard board = new TicTacToeBoard(3);
		board.makeMove(new TicTacToeMove(0,0));
		board.makeMove(new TicTacToeMove(0,1));
		board.makeMove(new TicTacToeMove(1,0));
		board.makeMove(new TicTacToeMove(1,1));
		board.makeMove(new TicTacToeMove(2,0));
		
		Assert.assertTrue(board.isGameOver());
		TicTacToeScore scoreX = evaluator.evaluate(board, TicTacToePlayer.X);
		Assert.assertEquals(scoreX.compareTo(new TicTacToeScore(1)), 0);
		TicTacToeScore scoreO = evaluator.evaluate(board, TicTacToePlayer.O);
		Assert.assertEquals(scoreO.compareTo(new TicTacToeScore(-1)), 0);
	}
	
}
