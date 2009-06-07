package tictactoe;

import games.Game;
import games.logics.computer.RandomMoveLogic;
import checkers.game.display.ConsoleDisplayer;

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
