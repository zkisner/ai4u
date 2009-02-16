package games;

/**
 * This is an interface for the game board on which the game is played.
 * 
 * @author kreich
 */
public interface Board {

	/**
	 * @param move The move to make.
	 */
	public void makeMove(Move move);
	
}
