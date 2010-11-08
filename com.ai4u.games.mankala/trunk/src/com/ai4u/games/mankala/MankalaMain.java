package com.ai4u.games.mankala;

import com.ai4u.core.game.Game;
import com.ai4u.core.logic.Logic;
import com.ai4u.core.logic.computer.MinimaxLogic;
import com.ai4u.core.logic.computer.RandomMoveLogic;

/**
 * Main running Mankala.
 * 
 * @author igalk
 */
public class MankalaMain {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		MankalaBoard board = new MankalaBoard();
		MankalaDisplayer displayer = new MankalaDisplayer();
		MankalaEvaluator evaluator = new MankalaEvaluator();
		Logic<MankalaMove, MankalaBoard, MankalaPlayer> redLogic =
			new MinimaxLogic<MankalaMove, MankalaBoard, MankalaScore,
					MankalaPlayer, MankalaEvaluator>(evaluator , 4);
		Logic<MankalaMove, MankalaBoard, MankalaPlayer> blueLogic =
			new RandomMoveLogic<MankalaMove, MankalaBoard, MankalaPlayer>(
					MankalaPlayer.BLUE);
		Game mankala = new MankalaGame(board, displayer, redLogic, blueLogic);
		mankala.start();
	}

}
