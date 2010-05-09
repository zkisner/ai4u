package com.ai4u.games.tictactoe;

import java.util.HashMap;
import java.util.Map;

import com.ai4u.core.Move;
import com.ai4u.core.Player;
import com.ai4u.core.game.AbstractSimpleGame;
import com.ai4u.core.logic.Logic;
import com.ai4u.games.tictactoe.ui.TicTacToeGraphicDisplayer;

/**
 * @param <M> The type of moves.
 * @param <P> The type of players.
 * @author igalk
 */
public class TicTacToeGame<M extends Move, P extends Player<M>>
extends AbstractSimpleGame<TicTacToeMove, ITicTacToeBoard, TicTacToeGraphicDisplayer, TicTacToePlayer> {

	/**
	 * Constructor.
	 * 
	 * @param board
	 *            The starting board of the game.
	 * @param displayer
	 *            The means of display.
	 * @param logic4x
	 *            The logic of the player playing x.
	 * @param logic4o
	 *            The logic of the player playing o.
	 */
	public TicTacToeGame(ITicTacToeBoard board,
			TicTacToeGraphicDisplayer displayer,
			Logic<TicTacToeMove, ITicTacToeBoard, TicTacToePlayer> logic4x,
			Logic<TicTacToeMove, ITicTacToeBoard, TicTacToePlayer> logic4o) {
		super(board, displayer, buildMap(logic4x, logic4o));
	}

	private static Map<TicTacToePlayer, Logic<TicTacToeMove, ITicTacToeBoard, TicTacToePlayer>> buildMap(
			Logic<TicTacToeMove, ITicTacToeBoard, TicTacToePlayer> logic4x,
			Logic<TicTacToeMove, ITicTacToeBoard, TicTacToePlayer> logic4o) {
		Map<TicTacToePlayer, Logic<TicTacToeMove, ITicTacToeBoard, TicTacToePlayer>> map = new HashMap<TicTacToePlayer, Logic<TicTacToeMove, ITicTacToeBoard, TicTacToePlayer>>();
		map.put(TicTacToePlayer.X, logic4x);
		map.put(TicTacToePlayer.O, logic4o);
		return map;
	}

	/**
	 * @see com.ai4u.core.game.Game#isGameOver()
	 */
	public boolean isGameOver() {
		try {
			Thread.sleep(500);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		return state.isGameOver();
	}

}
