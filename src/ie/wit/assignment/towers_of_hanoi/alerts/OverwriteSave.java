package ie.wit.assignment.towers_of_hanoi.alerts;

import ie.wit.assignment.towers_of_hanoi.ver_02.Main02;
import javafx.scene.control.ChoiceDialog;

import java.util.List;
import java.util.Optional;

/**
 * Created by Joe on 09/03/2016.
 */
public class OverwriteSave
{
	public static String display(){
		List<String> choices = getSaveNames();
		if (choices.size() == 0){
			return null;
		}
		ChoiceDialog<String> dialog = new ChoiceDialog<>(choices.get(0), choices);
		dialog.setTitle("Select game");
		dialog.setHeaderText("Please select a game to overwrite");
		dialog.setContentText("Select Game");

		// Traditional way to get the response value.
		Optional<String> result = dialog.showAndWait();
		if (result.isPresent()){
			return result.get();
		}
		return null;
	}
	private static List<String> getSaveNames(){
		return Main02.getGameList().getNameList();
}
}
