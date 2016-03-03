package ie.wit.assignment.towers_of_hanoi.alerts;

import javafx.scene.control.Alert;
import javafx.scene.control.ButtonType;

import java.util.Optional;

/**
 * Created by Joe on 24/02/2016.
 */
public class CloseAlert
{
	public static boolean display()
	{
		Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
		alert.setTitle("Close?");
		alert.setHeaderText("Are you sure you wish to exit?");
		alert.setContentText("Your game will save on closing.");

		Optional<ButtonType> result = alert.showAndWait();
		return result.get() == ButtonType.OK;
	}
}
