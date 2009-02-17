package checkers.game.logics.computer;

import utils.RandomUtils;
import games.Board;
import games.Logic;
import games.Move;

/**
 * This computer logic picks a random move from the available moves.
 * 
 * @author kreich
 */
public class RandomMoveLogic implements Logic {

	/**
	 * @see games.Logic#pickMove(games.Board)
	 */
	@Override
	public Move pickMove(Board board) {
		return rand.pickRandom(board.getMoves());
	}
	
	private RandomUtils rand = new RandomUtils();

}
