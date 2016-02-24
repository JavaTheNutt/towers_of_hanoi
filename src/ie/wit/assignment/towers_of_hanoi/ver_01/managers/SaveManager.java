package ie.wit.assignment.towers_of_hanoi.ver_01.managers;

import ie.wit.assignment.towers_of_hanoi.ver_01.FileManager;
import ie.wit.assignment.towers_of_hanoi.ver_01.model.Game;

import java.io.File;
import java.io.IOException;
import java.util.List;

/**
 * Created by Joe on 24/02/2016.
 */
public class SaveManager
{
	private static List<Game> gameList;
	private static File gamesFile = new File("games.dat");

	public static void setGames()
	{
		try {
			gameList = gamesFile.exists() ? FileManager.readIn(gamesFile): null;
		} catch (IOException e) {
			e.printStackTrace();
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		}

	}


	public static Game getGame(int index){
		return gameList.get(index);
	}
}
