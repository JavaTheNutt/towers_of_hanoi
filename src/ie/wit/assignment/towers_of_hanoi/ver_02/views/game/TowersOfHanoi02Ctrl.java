package ie.wit.assignment.towers_of_hanoi.ver_02.views.game;

import ie.wit.assignment.towers_of_hanoi.alerts.CreateNewSave;
import ie.wit.assignment.towers_of_hanoi.alerts.GameWonAlert;
import ie.wit.assignment.towers_of_hanoi.alerts.NewSaveChoice;
import ie.wit.assignment.towers_of_hanoi.alerts.OverwriteSave;
import ie.wit.assignment.towers_of_hanoi.ver_02.Main02;
import ie.wit.assignment.towers_of_hanoi.ver_02.managers.GameManager02;
import ie.wit.assignment.towers_of_hanoi.ver_02.managers.SingleGameManager02;
import ie.wit.assignment.towers_of_hanoi.ver_02.model.Block02;
import ie.wit.assignment.towers_of_hanoi.ver_02.model.Game02;
import javafx.animation.*;
import javafx.application.Platform;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.collections.ObservableList;
import javafx.event.Event;
import javafx.fxml.FXML;
import javafx.geometry.Insets;
import javafx.scene.Node;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;
import javafx.util.Duration;

import java.util.ArrayList;
import java.util.Random;

/**
 * This will control the game view
 */
public class TowersOfHanoi02Ctrl
{
	@FXML
	private ComboBox<Integer> comboBox;
	@FXML
	private Button tower01Button;
	@FXML
	private Button tower02Button;
	@FXML
	private Button tower03Button;
	@FXML
	private Button tower04Button;
	@FXML
	private VBox tower01;
	@FXML
	private VBox tower02;
	@FXML
	private VBox tower03;
	@FXML
	private VBox tower04;
	@FXML
	private Label movesLabel;

	private Game02 game;
	private GameManager02 gameManager;

	private ObservableList<Node> tower01List, tower02List, tower03List, tower04List, lastList;
	private ArrayList<Node> allBlocks;

	private VBox lastClicked = null;

	@FXML
	private void initialize()
	{
		Main02.onMenu = false;
		comboBox.getItems().addAll(3, 4, 5, 6, 7, 8);
		comboBox.setValue(Main02.getTempNumBlocks());
		comboBox.valueProperty().addListener(new ChangeListener<Integer>()
		{
			@Override
			public void changed(ObservableValue<? extends Integer> observable, Integer oldValue, Integer newValue)
			{
				if (!oldValue.equals(newValue)) {
					startSingleGame();
				}
			}
		});

		tower01List = tower01.getChildren();
		tower02List = tower02.getChildren();
		tower03List = tower03.getChildren();
		tower04List = tower04.getChildren();
		allBlocks = new ArrayList<>();

		if (Main02.singleGame) {
			gameManager = new SingleGameManager02();
			Main02.setGameManager(gameManager);
			startSingleGame();
		}

	}

	/**
	 * handle button click
	 *
	 * @param e the event
	 */
	@FXML
	private void buttonClick(Event e)
	{
		if (game.isGameWon()) {
			return;
		}
		Node src = (Node) e.getSource();
		ObservableList<Node> clickedList;
		if (src instanceof Button) {
			clickedList = buttonToList(src);
		} else {
			clickedList = ((VBox) src).getChildren();
		}
		/**
		 * check if tower 4 is disabled by checking if the button is disabled
		 */
		if (clickedList == tower04List) {
			if (tower04Button.isDisable()) {
				return;
			}
		}
		if (checkMove(clickedList)) {
			moveBlock(clickedList);
			syncBlocks();
		}
	}

	/**
	 * called if the requested move is legal
	 *
	 * @param currentList the list that the block will be moved to
	 */
	private void moveBlock(ObservableList<Node> currentList)
	{
		if (game.moveBlock(getListId(lastList), getListId(currentList))) {
			syncBlocks();
			lastList = null;
		}
	}

	/**
	 * return an id for each tower to be passed to the game model to indicate which element in the towers array
	 * should be accessed
	 *
	 * @param currentList the list which the id needs to be gotten for
	 * @return an id
	 */
	private int getListId(ObservableList<Node> currentList)
	{
		if (currentList == tower01List) {
			return 0;
		} else if (currentList == tower02List) {
			return 1;
		} else if (currentList == tower03List) {
			return 2;
		}
		return 3;
	}

	/**
	 * Check the legality of moves and also apply/remove highlighting if required
	 *
	 * @param currentList the most recently clicked list
	 * @return flag to indicate if a move should be made
	 */
	private boolean checkMove(ObservableList<Node> currentList)
	{
		if (currentList.size() > 0) {
			StackPane block = (StackPane) currentList.get(0);
			if (lastList == null) {
				lastList = currentList;
				block.getChildren().get(0).getStyleClass().add("highlightedBlock");
				return false;
			} else if (lastList == currentList) {
				lastList = null;
				block.getChildren().get(0).getStyleClass().remove("highlightedBlock");
				return false;
			} else {
				return true;
			}
		}
		return lastList != null;
	}

	/**
	 * return a list based on a button that has been clicked
	 *
	 * @param src the button clicked (this method is only called if the Node clicked is an instance of button)
	 * @return the corresponding list
	 */
	private ObservableList<Node> buttonToList(Node src)
	{
		if (src == tower01Button) {
			return tower01List;
		} else if (src == tower02Button) {
			return tower02List;
		} else if (src == tower03Button) {
			return tower03List;
		} else {
			return tower04List;
		}
	}

	/**
	 * handle restart click
	 */
	@FXML
	private void restartClicked()
	{
		startSingleGame();
	}

	/**
	 * Begin a single game
	 */
	private void startSingleGame()
	{
		if (Main02.isLoadGame()) {
			gameManager.createGame(Main02.getGameToBeLoaded().getLastState());
		} else {
			gameManager.createGame(comboBox.getValue());
		}
		game = gameManager.getPlayer01Game();
		syncBlocks();
		allBlocks.clear();
		allBlocks.addAll(tower01List);
	}

	/**
	 * Sync the view with the model
	 */
	private void syncBlocks()
	{
		tower01List.clear();
		tower02List.clear();
		tower03List.clear();
		tower04List.clear();
		ObservableList<Node> currentList;
		for (int j = 0; j < 4; j++) {
			if (j == 0) {
				currentList = tower01List;
			} else if (j == 1) {
				currentList = tower02List;
			} else if (j == 2) {
				currentList = tower03List;
			} else {
				currentList = tower04List;
			}
			double step = calculateStep(game.getNumBlocks());
			for (int i = game.getTowerList(j).size(); i > 0; i--) {
				Block02 block = game.getTowerList(j).get(i - 1);
				Insets padding = calculatePadding(step, block);
				StackPane pane = new StackPane();
				StackPane outerPane = new StackPane(pane);
				outerPane.setPadding(padding);
				outerPane.setId("block" + i);
				pane.setPrefSize(100, 20);
				pane.getStyleClass().add("blocks");
				currentList.add(currentList.size(), outerPane);
			}
		}
		tower04Button.setDisable(game.isTowerDisabled());
		movesLabel.setText("Moves: " + game.getNumMoves());
		if (game.isGameWon()) {
			playWonAnimation();
			if (GameWonAlert.display(game)) {
				Main02.setLoadGame(false);
				restartClicked();
			} else {
				Platform.exit();
			}
		}
	}

	/**
	 * Called at the end of the game
	 */
	private void playWonAnimation()
	{
		Random rand = new Random();
		SequentialTransition seq = new SequentialTransition();
		for (Node block : tower02List) {
			double xTrans = rand.nextInt(800) + 700;
			double yTrans = rand.nextInt(800) + 700;

			int translateTime = 2500;
			int oneRotation = 1200;

			TranslateTransition translate = new TranslateTransition(Duration.millis(translateTime), block);
			/*TranslateTransition trans2 = new TranslateTransition(Duration.ZERO, block);*/
			xTrans = (xTrans > 1100) ? xTrans : -xTrans;
			/*trans2.setByZ(10);*/
			translate.setByX(xTrans);
			translate.setByY(-yTrans);

			RotateTransition rotate = new RotateTransition(Duration.millis(oneRotation), block);
			rotate.setByAngle(360 * (translateTime - (200)) / oneRotation);

			seq.getChildren().add(new ParallelTransition(translate, rotate));
			seq.getChildren().add(new PauseTransition(Duration.millis(200)));
		}
		seq.play();

	}

	/**
	 * calculate the padding for a specific block based on the step calculated below and the size property of the block
	 *
	 * @param step  the step calculated based on the number of blocks
	 * @param block the block the padding is to be applied to
	 * @return an Insets with a padding for a particular block
	 */
	private Insets calculatePadding(double step, Block02 block)
	{
		return new Insets(0,
				block.getSize() * step * (block.getIndex() * .5),
				0,
				block.getSize() * step * (block.getIndex() * .5));
	}

	/**
	 * Calculate the step based on the number of blocks to be used. Originally used in the calculate padding method. Seperated
	 * to increse performance as it is now calculated once per refresh rather than for each block
	 *
	 * @param numBlocks number of blocks
	 * @return the step
	 */
	private double calculateStep(int numBlocks)
	{
		if (numBlocks == 3) {
			return (numBlocks + 15) * (numBlocks + 1);
		} else if (numBlocks == 4) {
			return (numBlocks + 8) * (numBlocks);
		} else {
			return (9 - numBlocks) * (numBlocks);
		}
	}

	/**
	 * handle reset move click
	 */
	@FXML
	private void resetMove()
	{
		if (game.isGameWon() || game.getNumMoves() < 1) {
			return;
		}
		game.resetMove();
		syncBlocks();
	}

	/**
	 * handle save game click
	 */
	@FXML
	private void saveGame()
	{
		int choice = NewSaveChoice.display();
		if (choice != 3) {
			String name;
			if (choice == 1) {
				name = CreateNewSave.display();
			} else {
				name = OverwriteSave.display();
			}
			if (name == null) {
				return;
			}
			Main02.saveGame(name);
		}
	}
}
