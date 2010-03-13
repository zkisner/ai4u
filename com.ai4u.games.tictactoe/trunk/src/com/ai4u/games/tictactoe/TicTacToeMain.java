package com.ai4u.games.tictactoe;

import com.ai4u.core.game.Game;
import com.ai4u.core.logic.computer.RandomMoveLogic;
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
		Game game = new TicTacToeGame(new TicTacToeBoard(3),
				new TicTacToeGraphicDisplayer(),
				new RandomMoveLogic(TicTacToePlayer.X),
				new RandomMoveLogic(TicTacToePlayer.O));
		game.start();
	}

}
