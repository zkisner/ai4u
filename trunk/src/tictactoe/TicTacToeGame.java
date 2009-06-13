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
		int size = board.getSize();
		// check rows
		for (int row = 0; row < size; row++) {
			int first = board.getCell(row, 0);
			if (first != TicTacToeBoard.EMPTY) {
				boolean same = true;
				for (int col = 1; col < size; col++) {
					if (board.getCell(row, col) != first) {
						same = false;
						break;
					}
				}
				if (same) {
					return true;
				}
			}
		}
		// check columns
		for (int col = 0; col < size; col++) {
			int first = board.getCell(0, col);
			if (first != TicTacToeBoard.EMPTY) {
				boolean same = true;
				for (int row = 1; row < size; row++) {
					if (board.getCell(row, col) != first) {
						same = false;
						break;
					}
				}
				if (same) {
					return true;
				}
			}
		}
		// check main diagonal
		int first = board.getCell(0, 0);
		if (first != TicTacToeBoard.EMPTY) {
			boolean same = true;
			for (int i = 1; i < size; i++) {
				if (board.getCell(i, i) != first) {
					same = false;
					break;
				}
			}
			if (same) {
				return true;
			}
		}
		// check secondary diagonal
		int last = size-1;
		first = board.getCell(0, last);
		if (first != TicTacToeBoard.EMPTY) {
			boolean same = true;
			for (int i = 1; i < size; i++) {
				if (board.getCell(i, last-i) != first) {
					same = false;
					break;
				}
			}
			if (same) {
				return true;
			}
		}
		// check whether there are empty cells left
		for (int i = 0; i < size; i++) {
			for (int j = 0; j < size; j++) {
				if (board.getCell(i, j) == TicTacToeBoard.EMPTY) {
					return false;
				}
			}
		}
		return true;
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
			p = p.equals(TicTacToePlayer.X) ?
					TicTacToePlayer.O : TicTacToePlayer.X;
		}
		disp.gameOver(board);
	}

}
