package ie.wit.assignment.towers_of_hanoi.ver_01;

import ie.wit.assignment.towers_of_hanoi.ver_01.model.Game;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.layout.BorderPane;
import javafx.stage.Stage;

/**
 * This is the main class and serves as the enrty point into the application
 */
public class Main extends Application
{
	private static Stage window;

	public static void main(String[] args)
	{
		launch(args);
	}

	public static Stage getWindow()
	{
		return window;
	}

	@Override
	public void start(Stage window) throws Exception
	{
		this.window = window;
		BorderPane root = FXMLLoader.load(getClass().getResource("views/menu/Menu.fxml"));
		window.setTitle("Towers of Hanoi");
		window.setScene(new Scene(root, 600, 400));
		window.show();
	}

}
