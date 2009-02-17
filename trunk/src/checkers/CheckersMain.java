package checkers;

import games.Game;
import utils.RandomUtils;
import checkers.game.CheckersGame;
import checkers.game.CheckersPlayer;
import checkers.game.display.ConsoleDisplayer;
import checkers.game.logics.computer.RandomMoveLogic;


/**
 * @author kreich
 */
public class CheckersMain {

	private static RandomUtils rand = new RandomUtils();

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		CheckersPlayer startingPlayer = rand.pickRandom(CheckersPlayer.class);
		Game game = new CheckersGame(
				new ConsoleDisplayer(),
				new RandomMoveLogic(),
				new RandomMoveLogic());
		game.start(startingPlayer);
	}

}
