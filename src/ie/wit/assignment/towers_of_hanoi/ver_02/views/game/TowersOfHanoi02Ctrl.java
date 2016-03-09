package ie.wit.assignment.towers_of_hanoi.ver_02.views.game;

import ie.wit.assignment.towers_of_hanoi.alerts.CreateNewSave;
import ie.wit.assignment.towers_of_hanoi.alerts.NewSaveChoice;
import ie.wit.assignment.towers_of_hanoi.alerts.OverwriteSave;
import ie.wit.assignment.towers_of_hanoi.ver_01.FileManager;
import ie.wit.assignment.towers_of_hanoi.ver_01.FileManager02;
import ie.wit.assignment.towers_of_hanoi.ver_01.Main;
import ie.wit.assignment.towers_of_hanoi.ver_02.Main02;
import ie.wit.assignment.towers_of_hanoi.ver_02.managers.GameManager02;
import ie.wit.assignment.towers_of_hanoi.ver_02.managers.SingleGameManager02;
import ie.wit.assignment.towers_of_hanoi.ver_02.model.Block02;
import ie.wit.assignment.towers_of_hanoi.ver_02.model.Game02;
import ie.wit.assignment.towers_of_hanoi.ver_02.model.SaveGame;
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

/**
 * Created by Joe on 03/03/2016.
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

		if (Main02.singleGame) {
			gameManager = new SingleGameManager02();
			Main02.setGameManager(gameManager);
			startSingleGame();
		}

	}
	@FXML
	private void buttonClick(Event e){
		Node src = (Node)e.getSource();
		ObservableList<Node> clickedList;
		if(src instanceof Button){
			clickedList = buttonToList(src);
		} else {
			clickedList = ((VBox) src).getChildren();
		}
		if (clickedList == tower04List){
			if (tower04Button.isDisable()){
				return;
			}
		}
		if(checkMove(clickedList)){
			moveBlock(clickedList);
			syncBlocks();
		}
	}
	private void moveBlock(ObservableList<Node> currentList){
		if(game.moveBlock(getListId(lastList), getListId(currentList))){
			syncBlocks();
			lastList = null;
		}
	}
	private int getListId(ObservableList<Node> currentList){
		if(currentList == tower01List){
			return 0;
		} else if(currentList == tower02List){
			return 1;
		}else if(currentList == tower03List){
			return 2;
		}
		return 3;
	}
	private boolean checkMove(ObservableList<Node> currentList){
		if (currentList.size() > 0){
			StackPane block  = (StackPane)currentList.get(0);
			if(lastList == null){
				lastList = currentList;
				block.getChildren().get(0).getStyleClass().add("highlightedBlock");
				return false;
			} else if(lastList == currentList){
				lastList = null;
				block.getChildren().get(0).getStyleClass().remove("highlightedBlock");
				return false;
			} else{
				return true;
			}
		}
		return lastList != null;
	}
	private ObservableList<Node> buttonToList(Node src){
		if (src == tower01Button){
			return tower01List;
		} else if(src == tower02Button){
			return tower02List;
		} else if(src == tower03Button){
			return tower03List;
		}else {
			return tower04List;
		}
	}
	@FXML
	private void restartClicked()
	{
		startSingleGame();
	}


	private void startSingleGame()
	{
		if (Main02.isLoadGame()){
			gameManager.createGame(Main02.getGameToBeLoaded().getLastState());
		} else{
			gameManager.createGame(comboBox.getValue());
		}
		game = gameManager.getPlayer01Game();
		syncBlocks();
	}

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
				pane.setPrefSize(100, 20);
				pane.getStyleClass().add("blocks");
				currentList.add(currentList.size(), outerPane);
			}
		}
		if(game.isTowerDisabled()){
			tower04Button.setDisable(true);
		} else{
			tower04Button.setDisable(false);
		}
		movesLabel.setText("Moves: " + game.getNumMoves());
	}

	private Insets calculatePadding(double step, Block02 block)
	{
		return new Insets(0, block.getSize() * step  * (block.getIndex() * .5), 0, block.getSize() * step * (block.getIndex() * .5));
	}
	private double calculateStep(int numBlocks){
		if (numBlocks == 3) {
			return (numBlocks + 15) * (numBlocks + 1);
		}else if(numBlocks == 4){
			return(numBlocks + 8) * (numBlocks);
		} else{
			return (9 - numBlocks) * (numBlocks);
		}
	}

	@FXML
	private void resetMove(){
		game.resetMove();
		syncBlocks();
	}
	@FXML
	private void saveGame(){
		int choice = NewSaveChoice.display();
		if(choice == 3){
			return;
		} else if(choice == 1){
			String name = CreateNewSave.display();
			if (name == null){
				return;
			}
			/*SaveGame game = new SaveGame(gameManager.getPlayer01Game().getCurrentState(), name.toUpperCase());
			Main02.getGameList().addSave(game);
			FileManager02.writeOut(Main02.getGameList());*/
			Main02.saveGame(name);
		} else{
			String name = OverwriteSave.display();
		}

	}
}
