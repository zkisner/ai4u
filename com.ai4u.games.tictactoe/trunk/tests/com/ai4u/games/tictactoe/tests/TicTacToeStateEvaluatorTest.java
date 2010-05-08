package com.ai4u.games.tictactoe.tests;

import org.junit.Assert;
import org.junit.Test;

import com.ai4u.games.tictactoe.TicTacToeBoard;
import com.ai4u.games.tictactoe.TicTacToeMove;
import com.ai4u.games.tictactoe.TicTacToePlayer;
import com.ai4u.games.tictactoe.logic.TicTacToeScore;
import com.ai4u.games.tictactoe.logic.TicTacToeStateEvaluator;


public class TicTacToeStateEvaluatorTest {
	
	TicTacToeStateEvaluator evaluator = new TicTacToeStateEvaluator();
	
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
