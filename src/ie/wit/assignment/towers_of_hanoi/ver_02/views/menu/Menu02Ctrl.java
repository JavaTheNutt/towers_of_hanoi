package ie.wit.assignment.towers_of_hanoi.ver_02.views.menu;

import ie.wit.assignment.towers_of_hanoi.alerts.OverwriteSave;
import ie.wit.assignment.towers_of_hanoi.ver_02.Main02;
import ie.wit.assignment.towers_of_hanoi.ver_02.model.SaveGame;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.layout.BorderPane;

import java.io.IOException;

/**
 * Control the menu view
 */
public class Menu02Ctrl
{
	@FXML
	private ComboBox<Integer> comboBox;
	@FXML
	private Button loadGameButton;

	@FXML
	private void initialize()
	{
		Main02.onMenu = true;
		comboBox.getItems().addAll(3, 4, 5, 6, 7, 8);
		comboBox.setValue(3);
		comboBox.valueProperty().addListener(new ChangeListener<Integer>()
		{
			@Override
			public void changed(ObservableValue<? extends Integer> observable, Integer oldValue, Integer newValue)
			{
				Main02.setTempNumBlocks(comboBox.getValue());
			}
		});
		if (Main02.numSaveGames() < 1) {
			loadGameButton.setDisable(true);
		} else {
			loadGameButton.setDisable(false);
		}
	}

	/**
	 * handle new game clicked
	 *
	 * @param e the event
	 */
	@FXML
	private void newGameClicked(ActionEvent e)
	{
		Main02.singleGame = true;
		Main02.setLoadGame(false);
		startGame();
	}

	/**
	 * start a new game
	 */
	private void startGame()
	{
		try {
			BorderPane root = FXMLLoader.load(getClass().getResource("../game/TowersOfHanoi02.fxml"));
			Main02.getWindow().setScene(new Scene(root, Main02.getWindow().getWidth(), Main02.getWindow().getHeight()));
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	/**
	 * handle load game clicked
	 */
	@FXML
	private void loadGame()
	{
		String gameName = OverwriteSave.display();
		if (gameName != null) {
			SaveGame tempGame = Main02.getGameList().getGameByName(gameName);
			Main02.setGameToBeLoaded(tempGame);
			Main02.setLoadGame(true);
			Main02.singleGame = true;
			startGame();
		}
	}
}
