package ie.wit.assignment.towers_of_hanoi.alerts;

import ie.wit.assignment.towers_of_hanoi.ver_01.model.Game;
import ie.wit.assignment.towers_of_hanoi.ver_02.model.Game02;
import javafx.scene.control.Alert;
import javafx.scene.control.ButtonType;

import java.util.Optional;

/**
 * Game won dialogs
 */
public class GameWonAlert
{
	// TODO: 11/03/2016 refactor to a single method that takes an integer

	/**
	 * Gam won dialog for version 1
	 *
	 * @param game the current game. Used to get the number of moves
	 * @return a flag to determine if the game should be restarted
	 */
	public static boolean display(Game game)
	{
		return displayAlert(game.getNumMoves());
	}

	/**
	 * Same as above ver 2
	 *
	 * @param game the curent game.
	 * @return a flag to determine if the game should be restarted
	 */
	public static boolean display(Game02 game)
	{
		return displayAlert(game.getNumMoves());
	}

	/**
	 * The acutal dialog used by both methods above
	 *
	 * @param numMoves the number of moves used
	 * @return a flag to determine if the game should be restarted
	 */
	private static boolean displayAlert(int numMoves)
	{
		Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
		alert.setTitle("Game02 Won");
		alert.setHeaderText("Congratulations... You won the game with " + numMoves + " moves!");
		alert.setContentText("Would you like to restart?");

		Optional<ButtonType> result = alert.showAndWait();
		return result.get() == ButtonType.OK;
	}
}
