package ie.wit.assignment.towers_of_hanoi.ver_01;


import ie.wit.assignment.towers_of_hanoi.alerts.CloseAlert;
import ie.wit.assignment.towers_of_hanoi.ver_01.managers.SaveManager;
import ie.wit.assignment.towers_of_hanoi.ver_01.model.State;
import javafx.application.Application;
import javafx.event.Event;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.layout.BorderPane;
import javafx.stage.Stage;

/**
 * This is the main class and serves as the entry point into the application
 */
public class Main extends Application
{
	public static SaveManager saveManager;
	private static State lastState;
	private static Stage window;
	private static State currentState;
	private static boolean loadGame = false;

	public static void main(String[] args)
	{
		launch(args);
	}

	public static Stage getWindow()
	{
		return window;
	}


	/**
	 * Save the game when the window is closed
	 *
	 * @param e the event
	 */
	private static void closeProgram(Event e)
	{
		if (CloseAlert.display()) {
			saveManager.saveGame();
			window.close();
		}
		e.consume();
	}

	/**
	 * Set the current state so that it can be accessed by different views
	 *
	 * @param currentState
	 */
	public static void setCurrentState(State currentState)
	{
		Main.currentState = currentState;
	}

	public static State getLastState()
	{
		return lastState;
	}

	public static boolean isLoadGame()
	{
		return loadGame;
	}

	/**
	 * Flag to check if a new game should be started, or the last game should be loaded
	 *
	 * @param loadGame flag
	 */
	public static void setLoadGame(boolean loadGame)
	{
		Main.loadGame = loadGame;
	}

	@Override
	public void start(Stage window) throws Exception
	{
		this.window = window;
		saveManager = new SaveManager();
		lastState = saveManager.getGame();
		BorderPane root = FXMLLoader.load(getClass().getResource("views/menu/Menu.fxml"));
		window.setTitle("Towers of Hanoi");
		window.setOnCloseRequest(e -> closeProgram(e));
		window.setScene(new Scene(root, 600, 400));
		window.show();
	}
}
