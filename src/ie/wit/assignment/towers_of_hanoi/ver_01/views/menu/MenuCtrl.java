package ie.wit.assignment.towers_of_hanoi.ver_01.views.menu;

import ie.wit.assignment.towers_of_hanoi.ver_01.Main;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.layout.BorderPane;

import java.io.IOException;

/**
 * Created by Joe on 09/02/2016.
 */
public class MenuCtrl
{
	@FXML
	private Button newGameBtn;

	@FXML
	private void startNewGame()
	{
		try {
			BorderPane root = FXMLLoader.load(getClass().getResource("../game/TowersOfHanoi.fxml"));
			Main.getWindow().setScene(new Scene(root, 600, 400));
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
}
