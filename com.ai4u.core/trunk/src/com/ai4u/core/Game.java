package com.ai4u.core;

/**
 * This is an interface for a turn based game.
 * 
 * @author kreich
 */
public interface Game {

	/**
	 * This method invokes the game start.
	 * 
	 * @param player The starting player.
	 */
	public void start(Player player);
	
	/**
	 * @return Whether the game has ended or not.
	 */
	public boolean isGameOver();
	
}
