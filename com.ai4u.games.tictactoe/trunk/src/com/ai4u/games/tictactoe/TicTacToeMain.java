package com.ai4u.games.tictactoe;

import com.ai4u.core.game.Game;
import com.ai4u.core.logic.Logic;
import com.ai4u.core.logic.computer.MinimaxLogic;
import com.ai4u.core.logic.computer.RandomMoveLogic;
import com.ai4u.games.tictactoe.logic.TicTacToeScore;
import com.ai4u.games.tictactoe.logic.TicTacToeStateEvaluator;
import com.ai4u.games.tictactoe.ui.TicTacToeGraphicDisplayer;

/**
 * @author igalk
 */
public class TicTacToeMain {

	/**
	 * @param args The arguments given to the program.
	 * @throws Exception If errors occur.
	 */
	public static void main(String[] args) throws Exception {
		TicTacToeBoard board = new TicTacToeBoard(3);
		TicTacToeGraphicDisplayer displayer = new TicTacToeGraphicDisplayer();
		Logic<TicTacToeMove, ITicTacToeBoard, TicTacToePlayer> logic4x =
			new RandomMoveLogic<TicTacToeMove, ITicTacToeBoard, TicTacToePlayer>(
				TicTacToePlayer.O);
		Logic<TicTacToeMove, ITicTacToeBoard, TicTacToePlayer> logic4o =
			new MinimaxLogic<TicTacToeMove, ITicTacToeBoard, TicTacToeScore,
					TicTacToePlayer, TicTacToeStateEvaluator>(
				new TicTacToeStateEvaluator(), 9);
		Game game = new TicTacToeGame<TicTacToeMove, TicTacToePlayer>(board,
				displayer, logic4x, logic4o);
		game.start();
	}

}
