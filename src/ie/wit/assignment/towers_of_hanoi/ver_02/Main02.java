package ie.wit.assignment.towers_of_hanoi.ver_02;

import ie.wit.assignment.towers_of_hanoi.alerts.CloseAlert;
import ie.wit.assignment.towers_of_hanoi.alerts.CreateNewSave;
import ie.wit.assignment.towers_of_hanoi.alerts.OverwriteSave;
import ie.wit.assignment.towers_of_hanoi.ver_02.managers.FileManager02;
import ie.wit.assignment.towers_of_hanoi.ver_02.managers.GameManager02;
import ie.wit.assignment.towers_of_hanoi.ver_02.model.SaveGame;
import ie.wit.assignment.towers_of_hanoi.ver_02.model.SaveList;
import ie.wit.assignment.towers_of_hanoi.ver_02.model.State02;
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
	public static boolean singleGame; // whether a single or two player game is to be loaded
	public static boolean onMenu;  // whether the menu is open. Used to determine if the game should try save if the user exits
	public static boolean saveFileExists;
	private static boolean loadGame; // whether a new game is to be created or a save loaded
	private static Stage window; // reference to the main window
	private static GameManager02 gameManager; // instance of the gamemanager. Will be initialized to either a single player game or two player game
	private static int tempNumBlocks = 3; // this will hold the number of blocks to be instantiated in the game
	private static SaveList gameList; // the list of save games
	private static SaveGame gameToBeLoaded; //the next game to be loaded as selected from the menu

	/**
	 * handle program close and save
	 *
	 * @param e the click event
	 */
	public static void closeProgram(Event e)
	{
		e.consume();
		if (onMenu) {
			if (CloseAlert.simpleClose()) {
				window.close();
			}
		} else {
			int choice = CloseAlert.display2();
			switch (choice) {
				case 1:
					String name = CreateNewSave.display();
					if (name != null) {
						saveGame(name);
						window.close();
					}
					return;
				case 2:
					String gameName = OverwriteSave.display();
					if (gameName != null) {
						saveGame(gameName);
						window.close();
					}
				case 3:
					return;
				case 4:
					window.close();
			}
		}
	}

	public static void main(String[] args)
	{
		launch(args);
	}

	/**
	 * accessor for the window
	 *
	 * @return the stage
	 */
	public static Stage getWindow()
	{
		return window;
	}

	/**
	 * accessor for the game manager
	 *
	 * @return thee manager
	 */
	public static GameManager02 getGameManager()
	{
		return gameManager;
	}

	/**
	 * mutator for game manager. Called after manager created in game view
	 *
	 * @param manager the manager that
	 */
	public static void setGameManager(GameManager02 manager)
	{
		gameManager = manager;
	}

	/**
	 * accessor for the temporary number of blocks. Used to pass data between the menu and the game
	 *
	 * @return the number of blocks to be loaded
	 */
	public static int getTempNumBlocks()
	{
		return tempNumBlocks;
	}

	/**
	 * mutator of the temporary number of blocks
	 *
	 * @param tempNumBlocks the number of blocks to be loaded
	 */
	public static void setTempNumBlocks(int tempNumBlocks)
	{
		Main02.tempNumBlocks = tempNumBlocks;
	}

	/**
	 * get the list of save games
	 *
	 * @return the list of save games
	 */
	public static SaveList getGameList()
	{
		return gameList;
	}

	/**
	 * get the game to be loaded. called by the game view
	 *
	 * @return the game to be loaded
	 */
	public static SaveGame getGameToBeLoaded()
	{
		return gameToBeLoaded;
	}

	/**
	 * set the game to be loaded. Called by the menu view
	 *
	 * @param gameToBeLoaded the game to be loaded
	 */
	public static void setGameToBeLoaded(SaveGame gameToBeLoaded)
	{
		Main02.gameToBeLoaded = gameToBeLoaded;
	}

	/**
	 * used by the game view to determine if the game should be loaded or a new game started
	 *
	 * @return a flag
	 */
	public static boolean isLoadGame()
	{
		return loadGame;
	}

	/**
	 * called by the menu view  to let the game view know if it should start a new game
	 *
	 * @param loadGame
	 */
	public static void setLoadGame(boolean loadGame)
	{
		Main02.loadGame = loadGame;
	}

	/**
	 * handle save game
	 *
	 * @param name the name of the game to be saved
	 */
	public static void saveGame(String name)
	{
		State02 tempState = gameManager.getPlayer01Game().getCurrentState();
		SaveGame game = new SaveGame(gameManager.getPlayer01Game().getCurrentState(), name.toUpperCase());
		gameList.addSave(game);
		FileManager02.writeOut(gameList);
	}

	/**
	 * get the number of save games in the list
	 *
	 * @return the number of saves in the list
	 */
	public static int numSaveGames()
	{
		return gameList.getList().size();
	}

	@Override
	public void start(Stage window) throws Exception
	{
		gameList = FileManager02.readIn();
		if (gameList == null) {
			gameList = new SaveList();
			saveFileExists = false;
		} else {
			saveFileExists = true;
		}
		this.window = window;
		window.setOnCloseRequest(e -> closeProgram(e));
		BorderPane root = FXMLLoader.load(getClass().getResource("views/menu/Menu02.fxml"));
		window.setMaximized(true);
		window.setResizable(false);
		window.setX(0);
		window.setY(0);
		window.setTitle("Towers of Hanoi Version 2");
		window.setScene(new Scene(root, Double.MAX_VALUE, Double.MAX_VALUE));
		window.show();
	}
}
