package ie.wit.assignment.towers_of_hanoi.ver_02.views.menu;

import ie.wit.assignment.towers_of_hanoi.ver_02.Main02;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.layout.BorderPane;

import java.io.IOException;

/**
 * Created by Joe on 03/03/2016.
 */
public class Menu02Ctrl
{
	// TODO: 03/03/2016  create a combobox to pass values to the game

	@FXML
	private void newGameClicked(ActionEvent e)
	{
		Main02.singleGame = true;
		startGame();
	}

	private void startGame()
	{
		try {
			BorderPane root = FXMLLoader.load(getClass().getResource("../game/TowersOfHanoi02.fxml"));
			Main02.getWindow().setScene(new Scene(root, 6000, 4000));
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
}
