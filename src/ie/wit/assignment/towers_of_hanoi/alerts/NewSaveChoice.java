package ie.wit.assignment.towers_of_hanoi.alerts;

import ie.wit.assignment.towers_of_hanoi.ver_02.Main02;
import javafx.scene.control.Alert;
import javafx.scene.control.ButtonBar;
import javafx.scene.control.ButtonType;

import java.util.Optional;

/**
 * Decide whether to create a new save or overwrite an old one
 */
public class NewSaveChoice
{
	/**
	 * display dialog
	 *
	 * @return a flag to determine next step
	 */
	public static int display()
	{
		Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
		alert.setTitle("Save Game");

		ButtonType buttonTypeOne = new ButtonType("New Save");
		ButtonType buttonTypeTwo = new ButtonType("Overwrite Save");
		ButtonType buttonTypeCancel = new ButtonType("Cancel", ButtonBar.ButtonData.CANCEL_CLOSE);
		ButtonType buttonTypeClose = new ButtonType("Exit without saving");
		if (Main02.numSaveGames() < 1) {
			alert.setContentText("There are no save games, would you like to create one?");
			alert.getButtonTypes().setAll(buttonTypeOne, buttonTypeClose, buttonTypeCancel);
		} else {
			alert.setContentText("Would you like to create a new save file or overwrite an old one?");

			alert.getButtonTypes().setAll(buttonTypeOne, buttonTypeTwo, buttonTypeClose, buttonTypeCancel);
		}
		Optional<ButtonType> result = alert.showAndWait();
		if (result.get() == buttonTypeOne) {
			return 1;
		} else if (result.get() == buttonTypeTwo) {
			return 2;
		} else if (result.get() == buttonTypeClose) {
			return 4;
		} else {
			return 3;
		}
	}
}
