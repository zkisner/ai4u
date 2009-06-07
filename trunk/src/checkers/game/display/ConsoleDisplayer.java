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
	public void display(Board board) {
		CheckersBoard b = (CheckersBoard) board;
		
		System.out.println("  a b c d e f g h");
		for (int i = 0; i < 8; i++) {
			long mask = 1L << (8*(7-i));
			System.out.print(8-i + "|");
			for (int j = 0; j < 8; j++) {
				if ((b.getW()&mask) != 0) System.out.print('w');
				else if ((b.getB()&mask)  != 0) System.out.print('b');
				else if ((b.getWk()&mask) != 0) System.out.print('W');
				else if ((b.getBk()&mask) != 0) System.out.print('B');
				else System.out.print(' ');
				System.out.print(' ');
				
				mask <<= 1;
			}
			System.out.print("|" + (8-i));
			
			System.out.println();
		}
		System.out.println("  a b c d e f g h");
	}
	
	/**
	 * Prints an announcement about the game's end.
	 * @see games.display.GameDisplayer#gameOver(Board)
	 */
	public void gameOver(Board board) {
		System.out.println("\nGAME OVER!");
	}

}
