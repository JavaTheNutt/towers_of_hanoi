package ie.wit.assignment.towers_of_hanoi.alerts;

import ie.wit.assignment.towers_of_hanoi.ver_01.Main;
import javafx.scene.control.Alert;
import javafx.scene.control.ButtonBar;
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
	public static int display2(){
		Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
		alert.setTitle("Save Game");
		/*alert.setHeaderText("Look, a Confirmation Dialog with Custom Actions");*/
		alert.setContentText("You are about to exit. Would you like to save your game?");

		ButtonType buttonTypeOne = new ButtonType("Save Game");
		ButtonType buttonTypeTwo = new ButtonType("Exit without saving");
		ButtonType buttonTypeCancel = new ButtonType("Cancel", ButtonBar.ButtonData.CANCEL_CLOSE);

		alert.getButtonTypes().setAll(buttonTypeOne, buttonTypeTwo, buttonTypeCancel);

		Optional<ButtonType> result = alert.showAndWait();
		if (result.get() == buttonTypeOne){
			return NewSaveChoice.display();
		} else if (result.get() == buttonTypeTwo) {
			return 4;
		} else{
			return 5;
		}
	}
	public static boolean simpleClose(){
		Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
		alert.setTitle("Exit");
		/*alert.setHeaderText("Are you sure you wish to exit?");*/
		alert.setContentText("Are you sure you wish to exit?");

		Optional<ButtonType> result = alert.showAndWait();
		return result.get() == ButtonType.OK;
	}
}
