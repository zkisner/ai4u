package checkers.game;

import checkers.calcs.BitBoardConsts;
import games.Game;
import games.Logic;
import games.Move;
import games.Player;
import games.display.GameDisplayer;

/**
 * This is an implementation of the {@link Game} interface.
 * 
 * @author kreich
 */
public class CheckersGame implements Game {

	/**
	 * Constructor.
	 * @param displayer The game displayer to use.
	 * @param whitePlayerLogic The logic for the white player.
	 * @param blackPlayerLogic The logic for the black player.
	 */
	public CheckersGame(GameDisplayer displayer, Logic whitePlayerLogic,
			Logic blackPlayerLogic) {
		board = new CheckersBoard();
		disp = displayer;
		wLogic = whitePlayerLogic;
		bLogic = blackPlayerLogic;
	}
	
	/**
	 * @see games.Game#start(games.Player)
	 */
	@Override
	public void start(Player player) {
		CheckersPlayer p = (CheckersPlayer) player;
		disp.display(board);
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
			disp.display(board);
		}
		disp.gameOver(board);
	}

	/**
	 * @see games.Game#isGameOver()
	 */
	@Override
	public boolean isGameOver() {
		return (board.getW()|board.getWk()) == BitBoardConsts.EMPTY ||
			   (board.getB()|board.getBk()) == BitBoardConsts.EMPTY;
	}
	
	/** The board the game is played upon. */
	private CheckersBoard board;

	/** The {@link GameDisplayer} to use for display. */
	private GameDisplayer disp;
	
	/** The white player's logic. */
	private Logic wLogic;
	
	/** The black player's logic. */
	private Logic bLogic;
	
}
