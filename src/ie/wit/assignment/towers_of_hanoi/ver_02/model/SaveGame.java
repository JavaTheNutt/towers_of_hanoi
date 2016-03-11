package ie.wit.assignment.towers_of_hanoi.ver_02.model;

import java.io.Serializable;

/**
 * Instamce of a save game
 */
public class SaveGame implements Serializable
{

	private State02 lastState;
	private String name;

	/**
	 * constructor
	 *
	 * @param lastState the state to be saved
	 * @param name      the name of the save game
	 */
	public SaveGame(State02 lastState, String name)
	{
		this.lastState = lastState;
		this.name = name;
	}

	/**
	 * accessor for the state
	 *
	 * @return the state
	 */
	public State02 getLastState()
	{
		return lastState;
	}

	/**
	 * mutator for the state. used for overwriting
	 *
	 * @param state the state to be saved
	 */
	public void updateState(State02 state)
	{
		lastState = state;
	}

	/**
	 * accessor for the name property
	 *
	 * @return the name property
	 */
	public String getName()
	{
		return name;
	}
}
