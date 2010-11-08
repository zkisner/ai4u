package com.ai4u.games.tictactoe.tests;

import org.junit.runner.RunWith;
import org.junit.runners.Suite;
import org.junit.runners.Suite.SuiteClasses;

/**
 * @author igalk
 */
@RunWith(Suite.class)
@SuiteClasses({
	TicTacToeGameTest.class,
	TicTacToeStateEvaluatorTest.class,
	TTTMinimaxLogicTest.class
})
public class TicTacToeSuite {
// nothing here
}
