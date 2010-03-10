package com.ai4u.core;

/**
 * This is an interface for a turn based game.
 * 
 * @author kreich
 */
public interface Game {

	/**
	 * This method invokes the game start.
	 */
	public void start();
	
	/**
	 * @return Whether the game has ended or not.
	 */
	public boolean isGameOver();
	
}
