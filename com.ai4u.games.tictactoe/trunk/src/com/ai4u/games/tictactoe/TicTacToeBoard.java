package com.ai4u.games.tictactoe;

import java.util.ArrayList;
import java.util.List;

import com.ai4u.core.*;

/**
 * @author igalk
 */
public class TicTacToeBoard implements ITicTacToeBoard {

	/** The size of the board. */
	private int size;
	
	/** The cells of the game. */
	private char[][] cells;
	
	/** The current player. */
	private TicTacToePlayer nextPlayer;
	
	/**
	 * @param size The size of the board to create.
	 */
	public TicTacToeBoard(int size) {
		this.size = size;
		cells = new char[size][];
		for (int i = 0; i < size; i++) {
			cells[i] = new char[size];
			for (int j = 0; j < size; j++) {
				cells[i][j] = EMPTY;
			}
		}
		nextPlayer = TicTacToePlayer.X;
	}
	
	/**
	 * @see com.ai4u.core.Board#getMoves(com.ai4u.core.Player)
	 */
	public List<Move> getMoves(Player player) {
		List<Move> moves = new ArrayList<Move>();
		for (int i = 0; i < size; i++) {
			for (int j = 0; j < size; j++) {
				if (cells[i][j] == EMPTY) {
					moves.add(new TicTacToeMove(i, j));
				}
			}
		}
		return moves;
	}

	/**
	 * @see com.ai4u.core.Board#makeMove(com.ai4u.core.Move)
	 */
	public TicTacToeBoard makeMove(Move move) {
		TicTacToeMove m = (TicTacToeMove) move;
		
		cells[m.getI()][m.getJ()] = nextPlayer.equals(TicTacToePlayer.X) ? X : O;
		nextPlayer = nextPlayer.equals(TicTacToePlayer.X) ?
				TicTacToePlayer.O : TicTacToePlayer.X;
		
		return this;
	}
	
	public GameState simulateMove(Move move) {
		TicTacToeBoard copy = this.clone();
		return copy.makeMove(move);
	}

	public Player getNextPlaying() {
		return nextPlayer;
	}
	
	@Override
	public TicTacToeBoard clone() {
		TicTacToeBoard clone = new TicTacToeBoard(this.size);
		
		for (int i = 0; i < this.cells.length; i++) {
			for (int j = 0; j < this.cells[i].length; j++) {
				clone.cells[i][j] = this.cells[i][j];
			}
		}
		
		clone.nextPlayer = this.nextPlayer;
		
		return clone;
	}

	/**
	 * @return The size of the board.
	 */
	public int getSize() {
		return size;
	}

	/**
	 * @param i The row of the cell.
	 * @param j The column of the cell.
	 * @return The value of the cell.
	 */
	public int getCell(int i, int j) {
		return cells[i][j];
	}

}
