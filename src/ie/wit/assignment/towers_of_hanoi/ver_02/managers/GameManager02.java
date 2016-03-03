package ie.wit.assignment.towers_of_hanoi.ver_02.managers;

import ie.wit.assignment.towers_of_hanoi.ver_02.model.Game02;
import ie.wit.assignment.towers_of_hanoi.ver_02.model.State02;

/**
 * Created by Joe on 03/03/2016.
 */
public abstract class GameManager02
{
	protected Game02 player01Game;

	// TODO: 03/03/2016 create subclasses for single and two player games

	public GameManager02()
	{
		player01Game = null;
	}

	public abstract void createGame(int numBlocks);

	public abstract void createGame(State02 state);

	public Game02 getPlayer01Game()
	{
		return player01Game;
	}

}
