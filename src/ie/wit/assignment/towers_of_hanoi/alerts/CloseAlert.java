package ie.wit.assignment.towers_of_hanoi.alerts;

import javafx.scene.control.Alert;
import javafx.scene.control.ButtonBar;
import javafx.scene.control.ButtonType;

import java.util.Optional;

/**
 * This class will hold the dialogs for closing the game in both versions
 */
public class CloseAlert
{
	/**
	 * close alert for version 1
	 *
	 * @return return whether to close the window
	 */
	public static boolean display()
	{
		Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
		alert.setTitle("Close?");
		alert.setHeaderText("Are you sure you wish to exit?");
		alert.setContentText("Your game will save on closing.");

		Optional<ButtonType> result = alert.showAndWait();
		return result.get() == ButtonType.OK;
	}

	/**
	 * Close dialog for version 2.
	 *
	 * @return a flag to determine whether to save the game or not
	 */
	public static int display2()
	{
		Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
		alert.setTitle("Save Game");
		/*alert.setHeaderText("Look, a Confirmation Dialog with Custom Actions");*/
		alert.setContentText("You are about to exit. Would you like to save your game?");

		ButtonType buttonTypeOne = new ButtonType("Save Game");
		ButtonType buttonTypeTwo = new ButtonType("Exit without saving");
		ButtonType buttonTypeCancel = new ButtonType("Cancel", ButtonBar.ButtonData.CANCEL_CLOSE);

		alert.getButtonTypes().setAll(buttonTypeOne, buttonTypeTwo, buttonTypeCancel);

		Optional<ButtonType> result = alert.showAndWait();
		if (result.get() == buttonTypeOne) {
			return NewSaveChoice.display();
		} else if (result.get() == buttonTypeTwo) {
			return 4;
		} else {
			return 3;
		}
	}

	/**
	 * This is the close handler for the menu of version 2. Used to prevent saving errors where a game does not exist
	 *
	 * @return a boolean to determine whether the application should close
	 */
	public static boolean simpleClose()
	{
		Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
		alert.setTitle("Exit");
		/*alert.setHeaderText("Are you sure you wish to exit?");*/
		alert.setContentText("Are you sure you wish to exit?");

		Optional<ButtonType> result = alert.showAndWait();
		return result.get() == ButtonType.OK;
	}
}
