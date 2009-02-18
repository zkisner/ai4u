package games;

import java.util.List;

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

	/**
	 * @param player The player whose turn it is.
	 * @return A List of the currently available moves.
	 */
	public List<Move> getMoves(Player player);
	
}
