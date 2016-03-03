package ie.wit.assignment.towers_of_hanoi.alerts;

import ie.wit.assignment.towers_of_hanoi.ver_01.model.Game;
import javafx.scene.control.Alert;
import javafx.scene.control.ButtonType;

import java.util.Optional;

/**
 * Created by Joe on 14/02/2016.
 */
public class GameWonAlert
{

	public static boolean display(Game game)
	{
		Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
		alert.setTitle("Game02 Won");
		alert.setHeaderText("Congratulations... You won the game with " + game.getNumMoves() + " moves!");
		alert.setContentText("Would you like to restart?");

		Optional<ButtonType> result = alert.showAndWait();
		return result.get() == ButtonType.OK;
	}
}
