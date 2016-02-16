package ie.wit.assignment.towers_of_hanoi.ver_01.alerts;

import javafx.scene.control.Alert;

/**
 * Created by Joe on 14/02/2016.
 */
public class InvalidMoveAlert
{
	public static void display()
	{
		Alert alert = new Alert(Alert.AlertType.ERROR);
		alert.setTitle("Invalid Move");
		alert.setHeaderText("That last move was invalid");
		alert.setContentText("Please ensure that you do not try to move a block on to a smaller block");
		alert.showAndWait();
	}
}
