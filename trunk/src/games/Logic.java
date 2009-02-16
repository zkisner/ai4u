package games;

/**
 * This is an interface for playing logics (anything that can decide how to make
 * a move).
 * 
 * @author kreich
 */
public interface Logic {

	/**
	 * @param board The board the moves can be played upon.
	 * @return The best move this logic can get.
	 */
	public Move pickMove(Board board);
	
}
