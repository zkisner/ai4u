package checkers.game.display;

import checkers.game.CheckersBoard;
import games.Board;
import games.display.GameDisplayer;

/**
 * This is a displayer which prints the game state to the console.
 * 
 * @author kreich
 */
public class ConsoleDisplayer implements GameDisplayer {

	/**
	 * Prints the board state to the console.
	 * 
	 * @see games.display.GameDisplayer#display(games.Board)
	 */
	@Override
	public void display(Board board) {
		CheckersBoard b = (CheckersBoard) board;
		System.out.println("  a b c d e f g h");
		for (int i = 0; i < 8; i++) {
			int rowW  = b.getW()  >> (4*(7-i));
			int rowB  = b.getB()  >> (4*(7-i));
			int rowWc = b.getWk() >> (4*(7-i));
			int rowBc = b.getBk() >> (4*(7-i));
			
			System.out.print(8-i + "|");
			if ((i&1) == 0) System.out.print(" _");
			
			if ((rowW&8) != 0) System.out.print('w');
			else if ((rowB&8)  != 0) System.out.print('b');
			else if ((rowWc&8) != 0) System.out.print('W');
			else if ((rowBc&8) != 0) System.out.print('B');
			else System.out.print(' ');
			
			System.out.print("_ _");
			
			if ((rowW&4) != 0) System.out.print('w');
			else if ((rowB&4)  != 0) System.out.print('b');
			else if ((rowWc&4) != 0) System.out.print('W');
			else if ((rowBc&4) != 0) System.out.print('B');
			else System.out.print(' ');
			
			System.out.print("_ _");
			
			if ((rowW&2) != 0) System.out.print('w');
			else if ((rowB&2)  != 0) System.out.print('b');
			else if ((rowWc&2) != 0) System.out.print('W');
			else if ((rowBc&2) != 0) System.out.print('B');
			else System.out.print(' ');
			
			System.out.print("_ _");
			
			if ((rowW&1) != 0) System.out.print('w');
			else if ((rowB&1)  != 0) System.out.print('b');
			else if ((rowWc&1) != 0) System.out.print('W');
			else if ((rowBc&1) != 0) System.out.print('B');
			else System.out.print(' ');
			
			if ((i&1) == 1) System.out.print("_ ");
			System.out.print("|" + (8-i));
			
			System.out.println();
		}
		System.out.println("  a b c d e f g h");
	}
	
	/**
	 * Prints an announcement about the game's end.
	 * @see games.display.GameDisplayer#gameOver(checkers.game.CheckersBoard)
	 */
	@Override
	public void gameOver(CheckersBoard board) {
		System.out.println("\nGAME OVER!");
	}

}
