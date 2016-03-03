package ie.wit.assignment.towers_of_hanoi.ver_01.managers;

import ie.wit.assignment.towers_of_hanoi.ver_01.FileManager;
import ie.wit.assignment.towers_of_hanoi.ver_01.model.State;

/**
 * This class will be responsible for writing the state out and reading it back in
 */
public class SaveManager
{
	private State game;

	/**
	 * When initialised, will read from the file
	 */
	public SaveManager()
	{
		game = FileManager.readIn();
	}


	/**
	 * Save the game
	 */
	public void saveGame()
	{
		FileManager.writeOut(game);
	}

	public State getGame()
	{
		return game;
	}

	public void setGame(State game)
	{
		this.game = game;
	}
}
