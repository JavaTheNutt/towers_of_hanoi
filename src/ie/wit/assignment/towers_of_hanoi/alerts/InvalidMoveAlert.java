package ie.wit.assignment.towers_of_hanoi.alerts;

import javafx.scene.control.Alert;

/**
 * Dialog for invalid moves
 */
public class InvalidMoveAlert
{
	/**
	 * display dialog
	 */
	public static void display()
	{
		Alert alert = new Alert(Alert.AlertType.ERROR);
		alert.setTitle("Invalid Move");
		alert.setHeaderText("That last move was invalid");
		alert.setContentText("Please ensure that you do not try to move a block on to a smaller block");
		alert.showAndWait();
	}
}
