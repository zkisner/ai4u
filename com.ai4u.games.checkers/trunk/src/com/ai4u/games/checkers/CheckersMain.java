package com.ai4u.games.checkers;

import com.ai4u.core.Game;
import com.ai4u.core.logic.computer.RandomMoveLogic;
import com.ai4u.util.RandomUtils;

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
