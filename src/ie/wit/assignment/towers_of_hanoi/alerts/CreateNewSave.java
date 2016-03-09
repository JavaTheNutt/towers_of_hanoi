package ie.wit.assignment.towers_of_hanoi.alerts;

import javafx.scene.control.TextInputDialog;

import java.util.Optional;

/**
 * Created by Joe on 09/03/2016.
 */
public class CreateNewSave
{
	public static String display(){
		TextInputDialog dialog = new TextInputDialog();
		dialog.setTitle("Create New Save");
		/*dialog.setHeaderText("Look, a Text Input Dialog");*/
		dialog.setContentText("Please a name for the save");

		Optional<String> result = dialog.showAndWait();
		if (result.isPresent()){
			return result.get();
		}
		return null;
	}
}
