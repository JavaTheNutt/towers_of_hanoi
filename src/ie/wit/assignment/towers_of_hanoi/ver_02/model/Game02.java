package ie.wit.assignment.towers_of_hanoi.ver_02.model;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

/**
 * This will be an instance of a single game.
 */
// TODO: 03/03/2016 Add a 4th tower. enable/disable tower at a random interval
// TODO: 03/03/2016 get 1st constructor to generate default state and call second constructor
// TODO: 03/03/2016 genereate random number for when tower 4 will become active
public class Game02 implements Serializable
{
	private final Tower02 tower01 = new Tower02(1);
	private final Tower02 tower02 = new Tower02(2);
	private final Tower02 tower03 = new Tower02(3);
	private final FourthTower02 tower04 = new FourthTower02(false);
	private final Tower02[] towers = new Tower02[4];
	private final List<Block02> blockList = new ArrayList<>();
	private int numMoves;
	private int currentNumMoves;
	private boolean gameWon = false;
	private boolean towerDisabled = true;
	private int toggleDisabledCounter;
	private int numBlocks;
	private List<State02> stateList = new ArrayList<>();
	private State02 currentState;

	/**
	 * Constructor
	 *
	 * @param numBlocks is the number of blocks to be in the game
	 */
	public Game02(int numBlocks)
	{
		this.numBlocks = numBlocks;
		numMoves = 0;
		assignTowers();
		State02 state = createDefaultState();
		initState(state);
	}

	public Game02(State02 state)
	{
		numBlocks = state.getNumBlocks();
		numMoves = state.getNumMoves();
		initState(state);
	}

	private void assignTowers()
	{
		towers[0] = tower01;
		towers[1] = tower02;
		towers[2] = tower03;
		towers[3] = tower04;
	}

	private void initState(State02 state)
	{
		Random random = new Random();
		toggleDisabledCounter = random.nextInt(6);
		System.out.println(toggleDisabledCounter);
		currentNumMoves = 0;
		assignTowers();
		stateList.add(state);
		resetMove(state);
	}

	/**
	 * This method is called by the constructor and will start the game and place the correct number of blocks in the first tower
	 */
	private void startGame()
	{
		currentState = createDefaultState();
	}

	public State02 createDefaultState()
	{
		tower01.getList().clear();
		tower02.getList().clear();
		tower03.getList().clear();
		tower04.getList().clear();
		for (int i = 0; i < numBlocks; i++) {
			Block02 block = new Block02(i * 0.5, i);
			towers[0].getList().add(block);
			blockList.add(block);
		}
		State02 state = new State02(tower01, tower02, tower03, tower04, numMoves, numBlocks);
		stateList.add(state);
		/*state.printState();*/
		return state;
	}

	public int getNumBlocks()
	{
		return numBlocks;
	}


	/**
	 * This method will check the legality of a move based on the size of the block in question, if the move is legal,
	 * it will be made.
	 *
	 * @param from the tower that the block will be moved from
	 * @param to   the tower that the block will be moved to
	 * @return a boolean to determine the legality of the move in question
	 */
	public boolean moveBlock(int from, int to)
	{
		Block02 blockFrom = towers[from].getList().get(towers[from].getList().size() - 1);
		if (towers[to].getList().size() > 0) {
			Block02 blockTo = towers[to].getList().get(towers[to].getList().size() - 1);
			if (blockTo.getSize() > blockFrom.getSize()) {
				return false;
			}
		}
		towers[from].getList().remove(blockFrom);
		towers[to].getList().add(blockFrom);
		numMoves++;
		currentNumMoves++;
		if (checkWon()) {
			gameWon = true;
		}
		State02 state = new State02(tower01, tower02, tower03, tower04, numMoves, numBlocks);
		stateList.add(state);
		currentState = state;
		state.printState();
		toggleDisabledCounter--;
		if (toggleDisabledCounter < 1) {
			toggleTowerDisabled();
		}
		return true;
	}

	private void toggleTowerDisabled()
	{
		towerDisabled = !towerDisabled;
		Random rand = new Random();
		toggleDisabledCounter = rand.nextInt(6);
	}

	/**
	 * This method will check if the game has been won
	 *
	 * @return a boolean to determine if the game has been won
	 */
	public boolean checkWon()
	{
		return tower02.getList().size() == numBlocks;
	}

	/**
	 * Return a tower based on the id passed
	 *
	 * @param id is the id of the tower
	 * @return the tower requested
	 */
	public List<Block02> getTowerList(int id)
	{
		return towers[id].getList();
	}

	public boolean isGameWon()
	{
		return gameWon;
	}

	public int getNumMoves()
	{
		return numMoves;
	}

	public void resetMove()
	{
		if (currentNumMoves < 1 || gameWon) {
			return;
		}
		State02 state = stateList.get(stateList.size() - 2);
		resetMove(state);

		stateList.remove(stateList.size() - 1);
		currentNumMoves--;
	}

	public void resetMove(State02 state)
	{
		tower01.getList().clear();
		tower01.getList().addAll(state.getTower01Blocks());

		tower02.getList().clear();
		tower02.getList().addAll(state.getTower02Blocks());

		tower03.getList().clear();
		tower03.getList().addAll(state.getTower03Blocks());

		tower04.getList().clear();
		tower04.getList().addAll(state.getTower04Blocks());

		towerDisabled = !tower04.isActive();
		numMoves = state.getNumMoves();
	}

	public State02 getCurrentState()
	{
		return currentState;
	}
}
