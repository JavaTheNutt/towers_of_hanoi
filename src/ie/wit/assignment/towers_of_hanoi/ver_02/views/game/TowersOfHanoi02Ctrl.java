package ie.wit.assignment.towers_of_hanoi.ver_02.views.game;

import ie.wit.assignment.towers_of_hanoi.ver_02.Main02;
import ie.wit.assignment.towers_of_hanoi.ver_02.managers.GameManager02;
import ie.wit.assignment.towers_of_hanoi.ver_02.managers.SingleGameManager02;
import ie.wit.assignment.towers_of_hanoi.ver_02.model.Block02;
import ie.wit.assignment.towers_of_hanoi.ver_02.model.Game02;
import javafx.collections.ObservableList;
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
// TODO: 03/03/2016 create blocks based on model
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

	@FXML
	private void initialize()
	{
		comboBox.getItems().addAll(3, 4, 5, 6, 7, 8);
		comboBox.setValue(3);

		tower01List = tower01.getChildren();
		tower02List = tower02.getChildren();
		tower03List = tower03.getChildren();
		tower04List = tower04.getChildren();

		if (Main02.singleGame) {
			gameManager = new SingleGameManager02();
			startSingleGame();
		}

	}

	@FXML
	private void restartClicked()
	{
		startSingleGame();
	}

	private void startSingleGame()
	{
		gameManager.createGame(comboBox.getValue());
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
			for (int i = game.getTowerList(j).size(); i > 0; i--) {
				Block02 block = game.getTowerList(j).get(i - 1);

				Insets padding = calculatePadding(game.getNumBlocks(), i);
				StackPane pane = new StackPane();
				StackPane outerPane = new StackPane(pane);
				outerPane.setPadding(padding);
				pane.setPrefSize(100, 20);
				pane.getStyleClass().add("blocks");
				currentList.add(currentList.size(), outerPane);
			}
		}

		movesLabel.setText(Integer.toString(game.getNumMoves()));
	}

	private Insets calculatePadding(int numBlocks, int iter)
	{
		System.out.println(iter);
		int step;
		int multi = 1;
		switch (numBlocks) {
			case 8:
				step = 18;
				break;
			case 7:
				step = 21;
				break;
			case 6:
				step = 21;
				break;
			case 5:
				step = 18;
				break;
			case 4:
				step = 18;
				break;
			case 3:
				step = 40;
				break;
			default:
				step = 20;
				break;
		}
		if (iter == 1){
			multi = 0;
		}
		return new Insets(0, (iter * multi) * step, 0, (iter * multi) * step);
	}
}
