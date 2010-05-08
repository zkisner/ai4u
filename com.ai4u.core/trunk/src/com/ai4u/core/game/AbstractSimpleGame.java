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
public abstract class AbstractSimpleGame<T extends Move, S extends GameState<T,S,P>, D extends GameDisplayer<T, S, P>, P extends Player<T>>
implements Game {

	protected final S state;
	private final D displayer;
	private final Map<P, Logic<T,S,P>> player2Logic;

	public AbstractSimpleGame(S state, D displayer, 
			Map<P, Logic<T,S,P>> player2Logic) {
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
			Player<T> nextPlayer = state.getNextPlaying();
			Logic<T,S,P> logic = player2Logic.get(nextPlayer);
			if (logic == null)
				throw new RuntimeException("Missing Logic for player: " + nextPlayer);
			
			T move = logic.pickMove(state);
			state.makeMove(move);
			displayer.display(state);
		}
		displayer.gameOver(state);
	}
	
}
