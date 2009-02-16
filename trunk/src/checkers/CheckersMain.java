package checkers;

import games.Game;
import utils.RandomUtils;
import checkers.game.CheckersGame;
import checkers.game.CheckersPlayer;


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
		// TODO implement logics and init game with logics.
		Game game = new CheckersGame(null, null);
		game.start(startingPlayer);
	}

}
