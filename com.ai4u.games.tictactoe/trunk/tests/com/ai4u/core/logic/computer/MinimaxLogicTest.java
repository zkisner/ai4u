package com.ai4u.core.logic.computer;
import org.junit.Assert;
import org.junit.Test;

import com.ai4u.core.logic.Logic;
import com.ai4u.games.tictactoe.ITicTacToeBoard;
import com.ai4u.games.tictactoe.TicTacToeBoard;
import com.ai4u.games.tictactoe.TicTacToeMove;
import com.ai4u.games.tictactoe.TicTacToePlayer;
import com.ai4u.games.tictactoe.logic.TicTacToeScore;
import com.ai4u.games.tictactoe.logic.TicTacToeStateEvaluator;


public class MinimaxLogicTest {

	private Logic<TicTacToeMove, ITicTacToeBoard, TicTacToePlayer> logic =
		new MinimaxLogic<TicTacToeMove, ITicTacToeBoard, TicTacToeScore, TicTacToePlayer, TicTacToeStateEvaluator>(
			new TicTacToeStateEvaluator(), 9);
	
	@Test
	public void checkWhiteWinIn2Moves() {
		TicTacToeBoard board = new TicTacToeBoard(3);
		board.makeMove(new TicTacToeMove(0,1));
		board.makeMove(new TicTacToeMove(0,0));
		board.makeMove(new TicTacToeMove(1,0));
		board.makeMove(new TicTacToeMove(2,2));
		
		Assert.assertFalse(board.isGameOver());
		TicTacToeMove move = logic.pickMove(board);
		Assert.assertEquals(new TicTacToeMove(1,1), move);
	}

}
