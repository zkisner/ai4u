package com.ai4u.games.tictactoe;

import java.util.HashMap;
import java.util.Map;

import com.ai4u.core.Player;
import com.ai4u.core.display.GameDisplayer;
import com.ai4u.core.game.AbstractSimpleGame;
import com.ai4u.core.logic.Logic;

/**
 * @author igalk
 */
public class TicTacToeGame extends AbstractSimpleGame {

	/**
	 * Constructor.
	 * 
	 * @param size The size of the board to create.
	 * @param displayer The means of display.
	 * @param logic4x The logic of the player playing x.
	 * @param logic4o The logic of the player playing o.
	 */
	public TicTacToeGame(ITicTacToeBoard board, GameDisplayer displayer, Logic logic4x,
			Logic logic4o) {
		super(board, displayer, buildMap(logic4x, logic4o));
	}

	private static Map<Player, Logic> buildMap(Logic logic4x, Logic logic4o) {
		Map<Player, Logic> map = new HashMap<Player, Logic>();
		map.put(TicTacToePlayer.X, logic4x);
		map.put(TicTacToePlayer.O, logic4o);
		return map;
	}

	/**
	 * @see com.ai4u.core.game.Game#isGameOver()
	 */
	public boolean isGameOver() {
		ITicTacToeBoard board = (ITicTacToeBoard)this.state;
		try {
			Thread.sleep(500);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		return board.isGameOver();
	}

}
