package ie.wit.assignment.towers_of_hanoi.ver_01;

import ie.wit.assignment.towers_of_hanoi.ver_01.model.State;
import ie.wit.assignment.towers_of_hanoi.ver_02.model.SaveList;

import java.io.*;

/**
 * This class will handle I/O
 */
public class FileManager02
{
	public static File file = new File("games02.dat");
	private static ObjectOutputStream oos;
	private static ObjectInputStream ois;

	/**
	 * Save to file
	 *
	 * @param game game to be saved
	 */
	public static void writeOut(SaveList game)
	{
		try {
			oos = new ObjectOutputStream(new FileOutputStream(file));
			oos.writeObject(game);
			oos.close();
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	/**
	 * Read file
	 *
	 * @return the saved state
	 */
	public static SaveList readIn()
	{
		SaveList temp;
		try {
			if (file.exists()) {
				ois = new ObjectInputStream(new FileInputStream(file));
				temp = (SaveList) ois.readObject();
				System.out.println(temp);
				return temp;
			} else {
				return null;
			}
		} catch (FileNotFoundException e) {
			e.printStackTrace();
			return null;
		} catch (IOException e) {
			e.printStackTrace();
			return null;
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
			return null;
		}

	}
}
