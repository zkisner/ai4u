package checkers;

import games.Game;
import games.logics.computer.RandomMoveLogic;
import utils.RandomUtils;
import checkers.game.CheckersGame;
import checkers.game.CheckersPlayer;
import checkers.game.display.ConsoleDisplayer;

/**
 * @author kreich
 */
public class CheckersMain {

	/**
	 * @param args The arguments given to the program.
	 */
	public static void main(String[] args) {
		CheckersPlayer startingPlayer = rand.pickRandom(CheckersPlayer.class);
		Game game = new CheckersGame(
				new ConsoleDisplayer(),
				new RandomMoveLogic(CheckersPlayer.WHITE),
				new RandomMoveLogic(CheckersPlayer.BLACK));
		game.start(startingPlayer);
	}

	/** A random generator. */
	private static RandomUtils rand = new RandomUtils();
	
}
