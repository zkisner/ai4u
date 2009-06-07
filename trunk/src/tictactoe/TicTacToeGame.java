package tictactoe;

import games.*;
import games.display.GameDisplayer;

/**
 * @author igalk
 */
public class TicTacToeGame implements Game {

	/** The board the game is played on. */
	private TicTacToeBoard board;
	/** The means of display for the game. */
	private GameDisplayer disp;
	/** The logic for the player playing x. */
	private Logic xLogic;
	/** The logic for the player playing o. */
	private Logic oLogic;

	/**
	 * Constructor.
	 * 
	 * @param size The size of the board to create.
	 * @param displayer The means of display.
	 * @param logic4x The logic of the player playing x.
	 * @param logic4o The logic of the player playing o.
	 */
	public TicTacToeGame(int size, GameDisplayer displayer, Logic logic4x,
			Logic logic4o) {
		board = new TicTacToeBoard(size);
		disp = displayer;
		xLogic = logic4x;
		oLogic = logic4o;
	}

	/**
	 * @see games.Game#isGameOver()
	 */
	public boolean isGameOver() {
		throw new RuntimeException("not yet implemented");
	}

	/**
	 * @see games.Game#start(games.Player)
	 */
	public void start(Player player) {
		disp.display(board);
		TicTacToePlayer p = (TicTacToePlayer) player;
		while (!isGameOver()) {
			Move move = null;
			switch (p) {
			case X:
				move = xLogic.pickMove(board);
				break;
			case O:
				move = oLogic.pickMove(board);
				break;
			}
			board.makeMove(move);
			disp.display(board);
		}
		disp.gameOver(board);
	}

}
