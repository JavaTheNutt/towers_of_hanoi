package ie.wit.assignment.towers_of_hanoi.ver_01.views.game;

import ie.wit.assignment.towers_of_hanoi.ver_01.alerts.GameWonAlert;
import ie.wit.assignment.towers_of_hanoi.ver_01.alerts.InvalidMoveAlert;
import ie.wit.assignment.towers_of_hanoi.ver_01.model.Block;
import ie.wit.assignment.towers_of_hanoi.ver_01.model.Game;
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

import java.util.List;

/**
 * Created by Joe on 09/02/2016.
 */
public class TowersOfHanoiCtrl
{
	@FXML
	private ComboBox<Integer> comboBox;
	@FXML
	private Button tower1Button;
	@FXML
	private Button tower2Button;
	@FXML
	private Button tower3Button;
	@FXML
	private VBox tower01;
	@FXML
	private VBox tower02;
	@FXML
	private VBox tower03;
	@FXML
	private Button newGameButton;
	@FXML
	private Label movesLabel;
	private Game game;

	private ObservableList<Node> tower01List;
	private ObservableList<Node> tower02List;
	private ObservableList<Node> tower03List;

	private ObservableList<Node> lastList;

	public static void gameWon()
	{
		System.out.println("won");
	}

	@FXML
	private void initialize()
	{
		comboBox.getItems().addAll(3, 4, 5, 6, 7, 8);
		comboBox.setValue(3);
		comboBox.valueProperty().addListener(new ChangeListener<Integer>()
		{
			@Override
			public void changed(ObservableValue<? extends Integer> observable, Integer oldValue, Integer newValue)
			{
				if(!oldValue.equals(newValue)){
					startGame();
				}
			}
		});
		tower01List = tower01.getChildren();
		tower02List = tower02.getChildren();
		tower03List = tower03.getChildren();
		startGame();
	}

	/**
	 * This method will be called by a user clicking on one of the three buttons or VBoxes and will match the list to the object clicked
	 *
	 * @param e is the event
	 */

	@FXML
	private void towerClicked(Event e)
	{
		if (game.isGameWon()){
			return;
		}
		ObservableList<Node> currentList = buttonToList((Node)e.getSource());
		if (checkMove(currentList)) {
			moveBlocks(currentList);
		}
	}
	@FXML
	private void blockDragged(Event e){
		System.out.println(e.getSource());
	}
	@FXML
	private void dragDropped(Event e){
		System.out.println(e.getSource());
	}
	/**
	 * This method will deal with highlighting the top block and checking whether a move needs to be made. It
	 * will also check if highlighting needs to be added or removed to a block
	 *
	 * @param currentList the most recently clicked list
	 * @return a boolean to check if a move should be made
	 */
	private boolean checkMove(ObservableList<Node> currentList)
	{
		if (currentList.size() > 0) {
			StackPane outerBlock = (StackPane) currentList.get(0);
			if (lastList == null) {
				lastList = currentList;
				outerBlock.getChildren().get(0).getStyleClass().add("highlightedBlock");
				return false;
			} else if (lastList == currentList) {
				outerBlock.getChildren().get(0).getStyleClass().remove("highlightedBlock");
				lastList = null;
				return false;
			} else {
				return true;
			}
		}
		return lastList != null;
	}

	/**
	 * This method will send the list that the block is to be moved from and the list that the block is to be moved to. If the
	 * move is legal, the view is re-rendered else an error message is displayed. This method will also check if the game has
	 * been won after each move.
	 *
	 * @param currentList is the most recently clicked list
	 */
	private void moveBlocks(ObservableList<Node> currentList)
	{
		if (game.moveBlock(getListId(lastList), getListId(currentList))) {
			addBlocks();
			lastList = null;
			if (game.isGameWon()) {
				if(GameWonAlert.display(game)){
					startGame();
				}
			}
		} else {
			InvalidMoveAlert.display();
		}
	}

	/**
	 * This will return a number that will match the list location in the model
	 *
	 * @param list is the list to be matched
	 * @return an integer to denote the corresponding list in the model
	 */
	private int getListId(ObservableList<Node> list)
	{
		if (list == tower01List) {
			return 0;
		} else if (list == tower02List) {
			return 1;
		}
		return 2;
	}

	/**
	 * This button will match a list to the button clicked
	 *
	 * @param btn is the button that was clicked
	 * @return the corresponding list
	 */
	private ObservableList<Node> buttonToList(Node btn)
	{
		if (btn instanceof Button){
			if (btn == tower1Button) {
				return tower01List;
			} else if (btn == tower2Button) {
				return tower02List;
			}
			return tower03List;
		} else {
			if(btn == tower01){
				return tower01List;
			} else if (btn == tower02){
				return tower02List;
			}
		}
		return tower03List;
	}

	/**
	 * This method is called to start the game and also when the restart game button is clicked
	 */
	@FXML
	private void startGame()
	{
		game = new Game(comboBox.getValue());
		addBlocks();
	}
	@FXML
	private void checkMoves(){
		game.resetMove();
		addBlocks();
	}

	/**
	 * This method will be called every time that the game model changes. All blocks will be re-rendered to match the model
	 */
	private void addBlocks()
	{
		tower01List.clear();
		tower02List.clear();
		tower03List.clear();
		ObservableList<Node> currentList;
		for (int j = 0; j < 3; j++) {
			if (j == 0) {
				currentList = tower01List;
			} else if (j == 1) {
				currentList = tower02List;
			} else {
				currentList = tower03List;
			}
			for (int i = game.getTowerList(j).size(); i > 0; i--) {
				Block block = game.getTowerList(j).get(i - 1);
				Insets padding = new Insets(0, block.getSize() * 20, 0, block.getSize() * 20);
				StackPane pane = new StackPane();
				StackPane outerPane = new StackPane(pane);
				outerPane.setPadding(padding);
				pane.setPrefSize(100, 20);
				pane.getStyleClass().add("blocks");
				currentList.add(currentList.size(), outerPane);
				if (i == game.getTowerList(j).size()){
					outerPane.setOnDragDetected(event -> blockDragged(event));
				}
			}
		}
		movesLabel.setText(Integer.toString(game.getNumMoves()));
	}
}
