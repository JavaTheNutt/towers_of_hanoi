package ie.wit.assignment.towers_of_hanoi.alerts;

import ie.wit.assignment.towers_of_hanoi.ver_02.Main02;
import javafx.scene.control.ChoiceDialog;

import java.util.List;
import java.util.Optional;

/**
 * Dialog for overwriting saves. only used in version 2
 */
public class OverwriteSave
{
	/**
	 * display combobox with names of saves
	 *
	 * @return the name of the save to be overwritten
	 */
	public static String display()
	{
		List<String> choices = getSaveNames();
		if (choices.size() == 0) {
			return null;
		}
		ChoiceDialog<String> dialog = new ChoiceDialog<>(choices.get(0), choices);
		dialog.setTitle("Select game");
		dialog.setHeaderText("Please select a game to overwrite");
		dialog.setContentText("Select Game");

		Optional<String> result = dialog.showAndWait();
		if (result.isPresent()) {
			return result.get();
		}
		return null;
	}

	/**
	 * get the names of the saves
	 *
	 * @return a list of save names
	 */
	private static List<String> getSaveNames()
	{
		return Main02.getGameList().getNameList();
	}
}
