package com.ai4u.games.tictactoe;

import com.ai4u.core.Game;
import com.ai4u.core.logic.computer.RandomMoveLogic;

/**
 * @author igalk
 */
public class TicTacToeMain {

	/**
	 * @param args The arguments given to the program.
	 * @throws Exception If errors occur.
	 */
	public static void main(String[] args) throws Exception {
		TicTacToePlayer startingPlayer = TicTacToePlayer.X;
		Game game = new TicTacToeGame(3,
				new TicTacToeGraphicDisplayer(),
				new RandomMoveLogic(TicTacToePlayer.X),
				new RandomMoveLogic(TicTacToePlayer.O));
		game.start(startingPlayer);
	}

}
