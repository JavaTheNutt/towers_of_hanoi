package ie.wit.assignment.towers_of_hanoi.ver_02.model;

import ie.wit.assignment.towers_of_hanoi.ver_01.model.State;

import java.io.Serializable;

/**
 * Created by Joe on 09/03/2016.
 */
public class SaveGame implements Serializable
{
	private State02 lastState;
	private String name;

	public SaveGame(State02 lastState, String name)
	{
		this.lastState = lastState;
		this.name = name;
	}

	public State02 getLastState()
	{
		return lastState;
	}

	public String getName()
	{
		return name;
	}
}
