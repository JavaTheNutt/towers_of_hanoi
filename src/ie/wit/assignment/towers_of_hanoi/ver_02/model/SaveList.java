package ie.wit.assignment.towers_of_hanoi.ver_02.model;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by Joe on 09/03/2016.
 */
public class SaveList implements Serializable
{
	private   List<SaveGame> list;
	private List<String> nameList;
	public SaveList(){
		list = new ArrayList<>();
		nameList = new ArrayList<>();
	}

	public  void addSave(SaveGame game)
	{
		if(nameList.contains(game.getName())){
			nameList.remove(game.getName());
			list.remove(game);
		}
		list.add(game);
		nameList.add(game.getName());
	}

	public  List<SaveGame> getList()
	{
		return list;
	}

	public List<String> getNameList()
	{
		return nameList;
	}
	public SaveGame getGameByName(String name){
		if(!nameList.contains(name)){
			return null;
		}
		for (SaveGame game: list) {
			if (game.getName().toUpperCase().equals(name.toUpperCase())){
				return game;
			}
		}
		return null;
	}
}
