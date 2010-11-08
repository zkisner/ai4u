package com.ai4u.core.tests.logic.computer;

import static org.easymock.EasyMock.createControl;
import static org.easymock.EasyMock.expect;
import static org.junit.Assert.assertTrue;

import java.util.*;

import org.easymock.IMocksControl;
import org.junit.Before;
import org.junit.Test;

import com.ai4u.core.*;
import com.ai4u.core.logic.Logic;
import com.ai4u.core.logic.computer.MinimaxLogic;
import com.ai4u.core.logic.computer.evaluation.GameStateEvaluator;

/**
 * @author igalk
 */
public class MinimaxLogicTest {

	private static final int MAX_NUM_OF_PLAYERS = 4;
	
	private Random random;	
	private IMocksControl control;

	/**
	 * Builds mocks and instantiates the logic
	 */
	@Before
	public void setUp() {
		random = new Random();
		control = createControl();
	}
	
	/**
	 * checks that a depth 1 best move is selected.
	 */
	@Test
	@SuppressWarnings("unchecked")
	public void testPickOnlyMoveForFirstPlayer() {
		// Randomize
		int numOfPlayers = random.nextInt(MAX_NUM_OF_PLAYERS) + 1;
		
		// configure the behavior
		GameStateEvaluator evaluator = control.createMock(GameStateEvaluator.class);
		
		// build the state that will be evaluated
		GameState state = control.createMock(GameState.class);

		// build the players
		Player<Move>[] players = generateRandomPlayers(numOfPlayers);
		expect(state.getNextPlaying()).andReturn(players[0]);
		
		// build the moves
		Move expectedMove = control.createMock(Move.class);
		List<Move> moves = Collections.singletonList(expectedMove);
		expect(state.getMoves(players[0])).andReturn(moves);
		
		// build the next state
		GameState nextState = control.createMock(GameState.class);
		expect(state.simulateMove(expectedMove)).andReturn(nextState);
		
		control.replay();
		
		// do the actual test
		Logic logic = new MinimaxLogic(evaluator, 2);
		Move actualMove = logic.pickMove(state);

		assertTrue("The actual move differs from the expected",
				actualMove == expectedMove);
	}

	@SuppressWarnings("unchecked")
	private Player<Move>[] generateRandomPlayers(int numOfPlayers) {
		Player<Move>[] players = new Player[numOfPlayers];
		for (int i = 0; i < numOfPlayers; i++) {
			players[i] = control.createMock(Player.class);
		}
		return players;
	}

}
