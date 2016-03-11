package ie.wit.assignment.towers_of_hanoi.ver_02.managers;

import ie.wit.assignment.towers_of_hanoi.ver_02.model.Game02;
import ie.wit.assignment.towers_of_hanoi.ver_02.model.State02;

/**
 * Super class for game managers
 */
public abstract class GameManager02
{
	protected Game02 player01Game;

	// TODO: 03/03/2016 create subclasses for single and two player games

	/**
	 * default constructor
	 */
	public GameManager02()
	{
		player01Game = null;
	}

	/**
	 * create a new game based on a number of blocks
	 *
	 * @param numBlocks the number of blocks in the new game
	 */
	public abstract void createGame(int numBlocks);

	/**
	 * create a game based on a preloaded state. Used for loading games
	 *
	 * @param state the state of the game to be initialised
	 */
	public abstract void createGame(State02 state);

	/**
	 * return the current game
	 *
	 * @return the current game
	 */
	public Game02 getPlayer01Game()
	{
		return player01Game;
	}

}
