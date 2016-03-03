package ie.wit.assignment.towers_of_hanoi.ver_02.managers;

import ie.wit.assignment.towers_of_hanoi.ver_02.model.Game02;
import ie.wit.assignment.towers_of_hanoi.ver_02.model.State02;

/**
 * Created by Joe on 03/03/2016.
 */
public class SingleGameManager02 extends GameManager02
{
	public SingleGameManager02()
	{
		super();
	}

	@Override
	public void createGame(int numBlocks)
	{
		player01Game = new Game02(numBlocks);
	}

	@Override
	public void createGame(State02 state)
	{
		player01Game = new Game02(state);
	}
}
