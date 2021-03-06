package ie.wit.assignment.towers_of_hanoi.ver_02.managers;

import ie.wit.assignment.towers_of_hanoi.ver_02.model.Game02;
import ie.wit.assignment.towers_of_hanoi.ver_02.model.State02;

/**
 * Manager for single player games
 */
public class SingleGameManager02 extends GameManager02
{
	/**
	 * constructor
	 */
	public SingleGameManager02()
	{
		super();
	}

	/**
	 * create game
	 *
	 * @param numBlocks the number of blocks in the new game
	 */
	@Override
	public void createGame(int numBlocks)
	{
		player01Game = new Game02(numBlocks);
	}

	/**
	 * create game
	 *
	 * @param state the state of the game to be initialised
	 */
	@Override
	public void createGame(State02 state)
	{
		player01Game = new Game02(state);
	}
}
