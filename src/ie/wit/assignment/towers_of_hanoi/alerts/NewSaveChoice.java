package ie.wit.assignment.towers_of_hanoi.alerts;

import javafx.scene.control.Alert;
import javafx.scene.control.ButtonBar;
import javafx.scene.control.ButtonType;

import java.util.Optional;

/**
 * Created by Joe on 09/03/2016.
 */
public class NewSaveChoice
{
	public static int display(){
		Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
		alert.setTitle("Save Game");
		/*alert.setHeaderText("Look, a Confirmation Dialog with Custom Actions");*/
		alert.setContentText("Would you like to create a new save file or overwrite an old one?");

		ButtonType buttonTypeOne = new ButtonType("New Save");
		ButtonType buttonTypeTwo = new ButtonType("Overwrite Save");
		ButtonType buttonTypeCancel = new ButtonType("Cancel", ButtonBar.ButtonData.CANCEL_CLOSE);

		alert.getButtonTypes().setAll(buttonTypeOne, buttonTypeTwo, buttonTypeCancel);

		Optional<ButtonType> result = alert.showAndWait();
		if (result.get() == buttonTypeOne){
			return 1;
		} else if (result.get() == buttonTypeTwo) {
			return 2;
		} else {
			return 3;
		}
	}
}
