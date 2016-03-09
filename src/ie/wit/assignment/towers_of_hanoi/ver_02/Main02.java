package ie.wit.assignment.towers_of_hanoi.ver_02;

import ie.wit.assignment.towers_of_hanoi.alerts.CloseAlert;
import ie.wit.assignment.towers_of_hanoi.alerts.CreateNewSave;
import ie.wit.assignment.towers_of_hanoi.alerts.NewSaveChoice;
import ie.wit.assignment.towers_of_hanoi.ver_01.FileManager02;
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
	public static boolean singleGame;
	private static boolean loadGame;
	public static boolean onMenu;
	private static Stage window;
	private static GameManager02 gameManager;
	private static int tempNumBlocks = 3;
	private static SaveList gameList;
	private static SaveGame gameToBeLoaded;

	public static void closeProgram(Event e)
	{
		// TODO: 09/03/2016 finish switch for all save/close options. 
		e.consume();
		if (onMenu){
			if(CloseAlert.simpleClose()){
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
				case 4:
					window.close();
			}
		}
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
	public static void setGameManager(GameManager02 manager){
		gameManager = manager;
	}

	@Override
	public void start(Stage window) throws Exception
	{
		gameList = FileManager02.readIn();
		if (gameList == null){
			gameList = new SaveList();
		}
		System.out.println(gameList);
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

	public static int getTempNumBlocks()
	{
		return tempNumBlocks;
	}

	public static void setTempNumBlocks(int tempNumBlocks)
	{
		Main02.tempNumBlocks = tempNumBlocks;
	}
	public static SaveList getGameList(){
		return gameList;
	}

	public static SaveGame getGameToBeLoaded()
	{
		return gameToBeLoaded;
	}

	public static void setGameToBeLoaded(SaveGame gameToBeLoaded)
	{
		Main02.gameToBeLoaded = gameToBeLoaded;
	}

	public static boolean isLoadGame()
	{
		return loadGame;
	}

	public static void setLoadGame(boolean loadGame)
	{
		Main02.loadGame = loadGame;
	}
	public static void saveGame(String name){
		State02 tempState = gameManager.getPlayer01Game().getCurrentState();
		SaveGame game = new SaveGame(gameManager.getPlayer01Game().getCurrentState(), name.toUpperCase());
		gameList.addSave(game);
		FileManager02.writeOut(gameList);
	}
}
