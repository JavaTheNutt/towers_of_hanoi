package ie.wit.assignment.towers_of_hanoi.ver_02.model;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

/**
 * This will be an instance of a single game.
 */
public class Game02 implements Serializable
{
	// TODO: 11/03/2016 convert towers array to final property as they will always be in the same slot
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

	/**
	 * Constructor
	 *
	 * @param state is the state to be loaded
	 */
	public Game02(State02 state)
	{
		numBlocks = state.getNumBlocks();
		numMoves = state.getNumMoves();
		towerDisabled = !state.isFourthActive();
		assignTowers();
		initState(state);
	}

	/**
	 * Called by the constructor to place the towers in an array
	 */
	private void assignTowers()
	{
		towers[0] = tower01;
		towers[1] = tower02;
		towers[2] = tower03;
		towers[3] = tower04;
	}

	/**
	 * called by the constructor for initial setup
	 *
	 * @param state the state that the game is to be started in
	 */
	private void initState(State02 state)
	{
		Random random = new Random();
		toggleDisabledCounter = random.nextInt(6);
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

	/**
	 * called by the constructor when a state is not specified
	 *
	 * @return the default state based on the number of blocks to be used
	 */
	public State02 createDefaultState()
	{
		tower01.getList().clear();
		tower02.getList().clear();
		tower03.getList().clear();
		tower04.getList().clear();
		for (int i = 0; i < numBlocks; i++) {
			Block02 block = new Block02(i * 0.8, i);
			towers[0].getList().add(block);
			blockList.add(block);
		}
		State02 state = new State02(tower01, tower02, tower03, tower04, numMoves, numBlocks);
		stateList.add(state);
		/*state.printState();*/
		return state;
	}

	/**
	 * accessor for numblocks property
	 *
	 * @return numblocks
	 */
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
		if (from == 4 || to == 4) {
			if (towerDisabled) {
				return false;
			}
		}
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
			state.setFourthActive(!state.isFourthActive());
		}
		return true;
	}

	/**
	 * This will toggle the fourth tower and init a new random counter
	 */
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

	/**
	 * accessor for gamewon
	 *
	 * @return boolean to determine if game is won
	 */
	public boolean isGameWon()
	{
		return gameWon;
	}

	/**
	 * accesor for the number of moves
	 *
	 * @return the number of moves made
	 */
	public int getNumMoves()
	{
		return numMoves;
	}

	/**
	 * Default reset move which simply gets the previous state and then calls the overloaded version with that state as
	 * a parameter
	 */
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

	/**
	 * reset the move to a specifed state. called by both constructors and also by the default reset move method
	 *
	 * @param state the state that the game should be set to.
	 */
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
		tower04.setActive(state.isFourthActive());
		towerDisabled = !tower04.isActive();
		numMoves = state.getNumMoves();
	}

	/**
	 * accessor for the current state
	 *
	 * @return the current state
	 */
	public State02 getCurrentState()
	{
		return currentState;
	}

	/**
	 * accessor for the property determining if the tower is disabled
	 *
	 * @return
	 */
	public boolean isTowerDisabled()
	{
		return towerDisabled;
	}
}
