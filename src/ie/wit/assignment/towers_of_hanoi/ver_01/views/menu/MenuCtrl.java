package ie.wit.assignment.towers_of_hanoi.ver_01.views.menu;

import ie.wit.assignment.towers_of_hanoi.ver_01.Main;
import ie.wit.assignment.towers_of_hanoi.ver_01.managers.SaveManager;
import ie.wit.assignment.towers_of_hanoi.ver_01.model.State;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.layout.BorderPane;

import java.io.IOException;

/**
 * This class is the controller for the menu. It will handle all of the events on the menu screen and is
 * responsible for loading any previous save games
 */
public class MenuCtrl
{
	public SaveManager saveManager;
	public State lastState;
	@FXML
	private Button newGameBtn;
	@FXML
	private Button loadGameBtn;

	/**
	 * This method will set the flag in the main method to false to ensure that it is a new game rather than
	 * a loaded game. It will then start the game
	 */
	@FXML
	private void startNewGame()
	{
		Main.setLoadGame(false);
		startGame();
	}

	/**
	 * This method is called before the view is rendered. This wil get a reference to the save manager
	 * and get the last state. If the last state is null, the load game button will be disabled
	 */
	@FXML
	private void initialize()
	{
		saveManager = Main.saveManager;
		lastState = Main.getLastState();
		if (lastState == null) {
			loadGameBtn.setDisable(true);
		} else {
			loadGameBtn.setDisable(false);
		}
	}

	/**
	 * This method will set the flag in the main class to true so that the last state is loaded. It will
	 * also set the current state to the state that was loaded so that the game view can access it when
	 * that view is instantiated
	 */
	@FXML
	private void loadGame()
	{
		Main.setLoadGame(true);
		Main.setCurrentState(saveManager.getGame());
		startGame();
	}

	/**
	 * Load the fxml and set the scene
	 */
	private void startGame()
	{
		try {
			BorderPane root = FXMLLoader.load(getClass().getResource("../game/TowersOfHanoi.fxml"));
			Main.getWindow().setScene(new Scene(root, 600, 400));
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
}
