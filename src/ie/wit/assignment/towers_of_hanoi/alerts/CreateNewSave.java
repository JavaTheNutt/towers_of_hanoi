package ie.wit.assignment.towers_of_hanoi.alerts;

import javafx.scene.control.TextInputDialog;

import java.util.Optional;

/**
 * This is the dialog for creating a new save
 */
public class CreateNewSave
{
	// TODO: 11/03/2016 create a new dialog to warn user if they enter a name that is already used currently just overwrites 

	/**
	 * display dialog
	 *
	 * @return the name of the new save
	 */
	public static String display()
	{
		TextInputDialog dialog = new TextInputDialog();
		dialog.setTitle("Create New Save");
		dialog.setContentText("Please enter a name for the save");

		Optional<String> result = dialog.showAndWait();
		if (result.isPresent()) {
			return result.get();
		}
		return null;
	}
}
