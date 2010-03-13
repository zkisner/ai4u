/**
 * 13/03/10
 */
package com.ai4u.core.game;

import java.util.Map;

import com.ai4u.core.GameState;
import com.ai4u.core.Move;
import com.ai4u.core.Player;
import com.ai4u.core.display.GameDisplayer;
import com.ai4u.core.logic.Logic;

/**
 * @author igalk
 */
public abstract class AbstractSimpleGame implements Game {

	protected final GameState state;
	private final GameDisplayer displayer;
	private final Map<Player, Logic> player2Logic;

	public AbstractSimpleGame(GameState state, GameDisplayer displayer,
			Map<Player, Logic> player2Logic) {
		this.state = state;
		this.displayer = displayer;
		this.player2Logic = player2Logic;
	}

	/**
	 * @see com.ai4u.core.game.Game#start()
	 */
	public void start() {
		displayer.display(state);
		while (!isGameOver()) {
			Player nextPlayer = state.getNextPlaying();
			Logic logic = player2Logic.get(nextPlayer);
			if (logic == null)
				throw new RuntimeException("Missing Logic for player: " + nextPlayer);
			
			Move move = logic.pickMove(state);
			state.makeMove(move);
			displayer.display(state);
		}
		displayer.gameOver(state);
	}
	
}
