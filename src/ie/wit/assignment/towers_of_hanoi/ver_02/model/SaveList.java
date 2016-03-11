package ie.wit.assignment.towers_of_hanoi.ver_02.model;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

/**
 * List of save games
 */
public class SaveList implements Serializable
{
	private List<SaveGame> list;
	private List<String> nameList;

	/**
	 * default constructor
	 */
	public SaveList()
	{
		list = new ArrayList<>();
		nameList = new ArrayList<>();
	}

	/**
	 * add a save to the list. If that name exists, the original will be overwritten
	 *
	 * @param game the save game to be added
	 */
	public void addSave(SaveGame game)
	{
		if (nameList.contains(game.getName().toUpperCase())) {
			getGameByName(game.getName().toUpperCase()).updateState(game.getLastState());
		} else {
			list.add(game);
		}
		addNames();
	}

	/**
	 * accessor for list of saves
	 *
	 * @return list of saves
	 */
	public List<SaveGame> getList()
	{
		return list;
	}

	/**
	 * accessor for list of names. used in dialogs
	 *
	 * @return
	 */
	public List<String> getNameList()
	{
		return nameList;
	}

	/**
	 * get a save game based on the name passed
	 *
	 * @param name the name of the game
	 * @return the specified save, or null
	 */
	public SaveGame getGameByName(String name)
	{
		if (!nameList.contains(name)) {
			return null;
		}
		for (SaveGame game : list) {
			if (game.getName().toUpperCase().equals(name.toUpperCase())) {
				return game;
			}
		}
		return null;
	}

	/**
	 * add the names of all current saves to the list
	 */
	private void addNames()
	{
		nameList.clear();
		for (SaveGame game : list) {
			nameList.add(game.getName());
			System.out.println(game.getName());
		}
	}
}
