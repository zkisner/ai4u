package checkers.game;

import games.Game;
import games.Logic;
import games.Move;
import games.Player;

/**
 * This is an implementation of the {@link Game} interface.
 * 
 * @author kreich
 */
public class CheckersGame implements Game {

	/**
	 * Constructor.
	 * @param whitePlayerLogic The logic for the white player.
	 * @param blackPlayerLogic The logic for the black player.
	 */
	public CheckersGame(Logic whitePlayerLogic, Logic blackPlayerLogic) {
		board = new CheckersBoard();
		wLogic = whitePlayerLogic;
		bLogic = blackPlayerLogic;
	}
	
	/**
	 * @see games.Game#start(games.Player)
	 */
	@Override
	public void start(Player player) {
		CheckersPlayer p = (CheckersPlayer) player;
		while (!isGameOver()) {
			Move move = null;
			switch (p) {
			case WHITE:
				move = wLogic.pickMove(board);
				break;
			case BLACK:
				move = bLogic.pickMove(board);
				break;
			}
			board.makeMove(move);
		}
	}

	/**
	 * @see games.Game#isGameOver()
	 */
	@Override
	public boolean isGameOver() {
		// TODO not yet implemented
		return false;
	}
	
	/** The board the game is played upon. */
	private CheckersBoard board;

	/** The white player's logic. */
	private Logic wLogic;
	
	/** The black player's logic. */
	private Logic bLogic;
	
}
