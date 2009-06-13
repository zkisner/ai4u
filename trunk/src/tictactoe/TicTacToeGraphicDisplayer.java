package tictactoe;

import games.Board;
import games.display.GameDisplayer;

import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;
import javax.swing.JFrame;

/**
 * @author igalk
 */
public class TicTacToeGraphicDisplayer implements GameDisplayer {

	/** The size of the frame window. */
	private static final int HEADER_SIZE = 30;
	
	/* --- Members --- */
	
	/** Image for a cell taken by X. */
	private BufferedImage X_IMG;
	/** Image for a cell taken by O. */	
	private BufferedImage O_IMG;
	/** Image for a blank cell. */	
	private BufferedImage BLANK_IMG;
	/** Image for a vertical border. */	
	private BufferedImage VERT_IMG;
	/** Image for a horizontal border. */	
	private BufferedImage HOR_IMG;
	/** Image for an intersection of borders. */	
	private BufferedImage MID_IMG;
	/** Indicates whether the app was displayed or not. */
	private boolean wasDisplayed;
	/** The frame the game is displayed upon. */
	private JFrame frame;
	
	
	/* --- Constructor --- */
	
	/**
	 * Constructor.
	 * @throws IOException I the loading of the images fails.
	 */
	public TicTacToeGraphicDisplayer() throws IOException {
		X_IMG = ImageIO.read(new File("resources/tictactoe/X.JPG"));
		O_IMG = ImageIO.read(new File("resources/tictactoe/O.JPG"));
		BLANK_IMG = ImageIO.read(new File("resources/tictactoe/blank.JPG"));
		VERT_IMG = ImageIO.read(new File("resources/tictactoe/vert.JPG"));
		HOR_IMG = ImageIO.read(new File("resources/tictactoe/hor.JPG"));
		MID_IMG = ImageIO.read(new File("resources/tictactoe/mid.JPG"));
		wasDisplayed = false;
		
		frame = new JFrame("TicTacToe");
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setVisible(wasDisplayed);
	}
	
	
	/* --- GameDisplayer implementation --- */
	
	/**
	 * @see games.display.GameDisplayer#display(games.Board)
	 */
	public void display(Board board) {
		TicTacToeBoard b = (TicTacToeBoard) board;
		int n = b.getSize();
		if (!wasDisplayed) {
			frame.setSize(100*n + 10*(n-1), 100*n + 10*(n-1) + HEADER_SIZE);
			frame.setVisible(true);
			wasDisplayed = true;
		}
		Graphics g = frame.getGraphics();
		
		for (int i = 0; i < n; i++) {
			// draw horizontals
			if (i  > 0) {
				g.drawImage(HOR_IMG, 0, 110*i - 10 + HEADER_SIZE, null);
				for (int j = 1; j < n; j++) {
					g.drawImage(MID_IMG, 110*j - 10, 110*i - 10 + HEADER_SIZE, null);
					g.drawImage(HOR_IMG, 110*j, 110*i - 10 + HEADER_SIZE, null);					
				}
			}
			for (int j = 0; j < n; j++) {
				//draw verticals
				if (j > 0) {
					g.drawImage(VERT_IMG, 110*j-10, 110*i + HEADER_SIZE, null);
				}
				
				// draw cells
				BufferedImage img = null;
				switch (b.getCell(i,j)) {
				case TicTacToeBoard.EMPTY:
					img = BLANK_IMG;
					break;
				case TicTacToeBoard.X:
					img = X_IMG;
					break;
				case TicTacToeBoard.O:
					img = O_IMG;
					break;
				}
				g.drawImage(img, 110*j, 110*i + HEADER_SIZE, null);				
			}
		}
		frame.setVisible(true);
	}

	/**
	 * @see games.display.GameDisplayer#gameOver(games.Board)
	 */
	public void gameOver(Board board) {
		Graphics g = frame.getGraphics();
		g.setFont(new Font("Ariel", Font.BOLD, 52));
		g.setColor(Color.RED);
		int n = ((TicTacToeBoard)board).getSize();
		int textX = (110*n - 280) / 2;
		int textY = (110*n) / 2 + HEADER_SIZE;
		g.drawBytes("Game Over".getBytes(), 0, 9, textX, textY);
		frame.setVisible(true);
	}

}
