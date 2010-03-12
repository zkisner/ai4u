package com.ai4u.games.tictactoe.ui;

import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;
import javax.swing.JFrame;

import com.ai4u.core.GameState;
import com.ai4u.core.display.GameDisplayer;
import com.ai4u.games.tictactoe.TicTacToeBoard;

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
		X_IMG = ImageIO.read(new File("resources/X.JPG"));
		O_IMG = ImageIO.read(new File("resources/O.JPG"));
		BLANK_IMG = ImageIO.read(new File("resources/blank.JPG"));
		VERT_IMG = ImageIO.read(new File("resources/vert.JPG"));
		HOR_IMG = ImageIO.read(new File("resources/hor.JPG"));
		MID_IMG = ImageIO.read(new File("resources/mid.JPG"));
		wasDisplayed = false;
		
		frame = new JFrame("TicTacToe");
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setVisible(wasDisplayed);
	}
	
	
	/* --- GameDisplayer implementation --- */
	
	/**
	 * @see com.ai4u.core.display.GameDisplayer#display(com.ai4u.core.Board)
	 */
	public void display(GameState state) {
		TicTacToeBoard b = (TicTacToeBoard) state;
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
	 * @see com.ai4u.core.display.GameDisplayer#gameOver(com.ai4u.core.Board)
	 */
	public void gameOver(GameState state) {
		Graphics g = frame.getGraphics();
		g.setFont(new Font("Ariel", Font.BOLD, 52));
		g.setColor(Color.RED);
		int n = ((TicTacToeBoard)state).getSize();
		int textX = (110*n - 280) / 2;
		int textY = (110*n) / 2 + HEADER_SIZE;
		g.drawBytes("Game Over".getBytes(), 0, 9, textX, textY);
		frame.setVisible(true);
	}

}
