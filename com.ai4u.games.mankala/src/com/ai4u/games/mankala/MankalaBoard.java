package com.ai4u.games.mankala;

import java.util.List;

import com.ai4u.core.GameState;

/**
 * The board of the game.
 * 
 * @author igalk
 */
public class MankalaBoard implements GameState<MankalaMove, MankalaBoard, MankalaPlayer> {

	private static final int NUM_OF_CUPS = 6;
	private static final int STARTING_SEEDS = 4;
	
	private final int[] redCups = new int[NUM_OF_CUPS];
	private final int redJug = 0;
	private final int[] blueCups = new int[NUM_OF_CUPS];
	private final int blueJug = 0;
	
	/**
	 * Constructor.
	 * puts the number of starting seeds into each
	 */
	public MankalaBoard() {
		for (int i = 0; i < NUM_OF_CUPS; i++) {
			redCups[i] = STARTING_SEEDS;
			blueCups[i] = STARTING_SEEDS;
		}
	}
	
	private MankalaBoard(int redJug, int blueJug, int[] redCups,
			int[] blueCups) {
		// TODO Auto-generated constructor stub
	}

	@Override
	public List<MankalaMove> getMoves(MankalaPlayer player) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public MankalaPlayer getNextPlaying() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public MankalaPlayer getWinner() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public boolean isGameOver() {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public MankalaBoard makeMove(MankalaMove move) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public MankalaBoard simulateMove(MankalaMove move) {
		// TODO Auto-generated method stub
		return null;
	}
	
	@Override
	public MankalaBoard clone(){
		return new MankalaBoard(redJug, blueJug, redCups, blueCups);
	}
	
	@Override
	public String toString() {
		StringBuilder buf = new StringBuilder();
		
		buf.append("    ");
		for (int i = 0; i < NUM_OF_CUPS; i++) {
			buf.append("(").append(redCups[NUM_OF_CUPS - 1 - i] > 9 ? "" : " ")
			   .append(redCups[NUM_OF_CUPS - 1 - i]).append(")");
		}
		buf.append('\n');
		
		buf.append("[").append(redJug > 9 ? "" : " ").append(redJug).append("]");
		for (int i = 0; i < NUM_OF_CUPS*4; i++) {
			buf.append(' ');
		}
		buf.append("[").append(blueJug > 9 ? "" : " ").append(blueJug)
		   .append("]").append('\n');
		
		buf.append("    ");
		for (int i = 0; i < NUM_OF_CUPS; i++) {
			buf.append("(").append(blueCups[i] > 9 ? "" : " ")
			   .append(blueCups[i]).append(")");
		}
		
		return buf.toString();
	}

}