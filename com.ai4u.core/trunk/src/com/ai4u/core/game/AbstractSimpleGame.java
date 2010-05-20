/**
 * 13/03/10
 */
package com.ai4u.core.game;

import java.util.Map;

import com.ai4u.core.*;
import com.ai4u.core.display.GameDisplayer;
import com.ai4u.core.logic.Logic;

/**
 * @author igalk
 * @param <M>
 *            The type of moves.
 * @param <S>
 *            The type of game states.
 * @param <D>
 *            The type of displayer.
 * @param <P>
 *            The type of player.
 */
public abstract class AbstractSimpleGame<M extends Move, S extends GameState<M, S, P>, D extends GameDisplayer<M, S, P>, P extends Player<M>>
implements Game {

	protected final S state;
	private final D displayer;
	private final Map<P, Logic<M, S, P>> player2Logic;

	/**
	 * Constructor.
	 * 
	 * @param state
	 *            The starting state of the game.
	 * @param displayer
	 *            The displayer that shows the game.
	 * @param player2Logic
	 *            A map of player -> logic that gives a logic for each player.
	 */
	public AbstractSimpleGame(S state, D displayer, 
			Map<P, Logic<M, S, P>> player2Logic) {
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
			Player<M> nextPlayer = state.getNextPlaying();
			Logic<M, S, P> logic = player2Logic.get(nextPlayer);
			if (logic == null)
				throw new RuntimeException("Missing Logic for player: "
						+ nextPlayer);
			
			M move = logic.pickMove(state);
			state.makeMove(move);
			displayer.display(state);
		}
		displayer.gameOver(state);
	}
	
}
