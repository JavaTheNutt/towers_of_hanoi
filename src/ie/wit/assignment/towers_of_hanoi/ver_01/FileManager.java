package ie.wit.assignment.towers_of_hanoi.ver_01;

import ie.wit.assignment.towers_of_hanoi.ver_01.model.Game;

import java.io.*;
import java.util.List;

/**
 * This class will handle I/O
 */
public class FileManager
{
	private static ObjectOutputStream oos;
	private static ObjectInputStream ois;

	public static void writeOut(List<Game> games, File file) throws IOException
	{
		oos = new ObjectOutputStream(new FileOutputStream(file));
		oos.writeObject(games);
		oos.close();
	}

	public static List<Game> readIn(File file) throws IOException, ClassNotFoundException
	{
		List<Game> temp = null;
		ois = new ObjectInputStream(new FileInputStream(file));
		temp = (List<Game>) ois.readObject();
		ois.close();
		return temp;
	}
}
