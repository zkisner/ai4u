package com.ai4u.games.mankala;

import java.util.HashMap;
import java.util.Map;

import com.ai4u.core.game.AbstractSimpleGame;
import com.ai4u.core.logic.Logic;

/**
 * Implementation for the Mankala game.
 * 
 * @author igalk
 */
public class MankalaGame
extends AbstractSimpleGame<MankalaMove, MankalaBoard, MankalaDisplayer,
			MankalaPlayer> {

	/**
	 * @param board The game's starting state.
	 * @param displayer The game displayer.
	 * @param redLogic The red player's logic.
	 * @param blueLogic The blue player's logic.
	 */
	public MankalaGame(MankalaBoard board, MankalaDisplayer displayer,
			Logic<MankalaMove, MankalaBoard, MankalaPlayer> redLogic,
			Logic<MankalaMove, MankalaBoard, MankalaPlayer> blueLogic) {
		super(board, displayer, makePlayerMap(redLogic, blueLogic));
	}
	
	private static Map<MankalaPlayer,
		Logic<MankalaMove, MankalaBoard, MankalaPlayer>> makePlayerMap(
			Logic<MankalaMove, MankalaBoard, MankalaPlayer> redLogic,
			Logic<MankalaMove, MankalaBoard, MankalaPlayer> blueLogic) {
		Map<MankalaPlayer, Logic<MankalaMove, MankalaBoard, MankalaPlayer>> map =
			new HashMap<MankalaPlayer, Logic<MankalaMove,MankalaBoard,MankalaPlayer>>();
		map.put(MankalaPlayer.RED, redLogic);
		map.put(MankalaPlayer.BLUE, blueLogic);
		
		return map;
	}

	@Override
	public boolean isGameOver() {
		try {
			Thread.sleep(500);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		return state.isGameOver();
	}

}
