package ie.wit.assignment.towers_of_hanoi.ver_02;

import ie.wit.assignment.towers_of_hanoi.alerts.CloseAlert;
import ie.wit.assignment.towers_of_hanoi.ver_02.managers.GameManager02;
import javafx.application.Application;
import javafx.event.Event;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.layout.BorderPane;
import javafx.stage.Stage;

/**
 * This will serve as the entry point for version 2
 */
// TODO: 03/03/2016 Create game instance in seperate gamemanager class. Create instance of gamemanager here
public class Main02 extends Application
{
	public static boolean singleGame;
	private static Stage window;
	private static GameManager02 gameManager;

	public static void closeProgram(Event e)
	{
		if (CloseAlert.display()) {
			window.close();
		}
		e.consume();
	}

	public static void main(String[] args)
	{
		launch(args);
	}

	public static Stage getWindow()
	{
		return window;
	}

	public static GameManager02 getGameManager()
	{
		return gameManager;
	}

	@Override
	public void start(Stage window) throws Exception
	{
		this.window = window;
		window.setOnCloseRequest(e -> closeProgram(e));
		BorderPane root = FXMLLoader.load(getClass().getResource("views/menu/Menu02.fxml"));
		window.setMaximized(true);
		/*window.setMinWidth(Double.MAX_VALUE);
		window.setMinHeight(Double.MAX_VALUE);*/
		window.setResizable(false);
		window.setTitle("Towers of Hanoi Version 2");
		window.setScene(new Scene(root, Double.MAX_VALUE, Double.MAX_VALUE));
		window.show();
	}
}
