package ie.wit.assignment.towers_of_hanoi.ver_01.model;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

/**
 * This will be an instance of a single game.
 */
public class Game implements Serializable
{
	private int numMoves;
	private int currentNumMoves;
	private boolean gameWon;
	private Tower tower01;
	private Tower tower02;
	private Tower tower03;
	private Tower[] towers;
	private int numBlocks;
	private List<Block> blockList;
	private List<State> stateList;
	private State currentState;

	/**
	 * Constructor
	 *
	 * @param numBlocks is the number of blocks to be in the game
	 */
	public Game(int numBlocks)
	{
		this.numBlocks = numBlocks;
		numMoves = 0;
		currentNumMoves = 0;
		blockList = new ArrayList<>();
		stateList = new ArrayList<>();
		gameWon = false;
		tower01 = new Tower(1);
		tower02 = new Tower(2);
		tower03 = new Tower(3);
		towers = new Tower[3];
		towers[0] = tower01;
		towers[1] = tower02;
		towers[2] = tower03;
		startGame();
	}

	public Game(State state)
	{
		numBlocks = state.getNumBlocks();
		numMoves = state.getNumMoves();
		currentNumMoves = 0;
		stateList = new ArrayList<>();
		gameWon = false;
		tower01 = new Tower(1);
		tower02 = new Tower(2);
		tower03 = new Tower(3);
		towers = new Tower[3];
		towers[0] = tower01;
		towers[1] = tower02;
		towers[2] = tower03;
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

	public State createDefaultState()
	{
		tower01.getList().clear();
		tower02.getList().clear();
		tower03.getList().clear();
		for (int i = 0; i < numBlocks; i++) {
			Block block = new Block(i * 0.5, i);
			towers[0].getList().add(block);
			blockList.add(block);
		}
		State state = new State(tower01, tower02, tower03, numMoves, numBlocks);
		stateList.add(state);
		state.printState();
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
		Block blockFrom = towers[from].getList().get(towers[from].getList().size() - 1);
		if (towers[to].getList().size() > 0) {
			Block blockTo = towers[to].getList().get(towers[to].getList().size() - 1);
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
		State state = new State(tower01, tower02, tower03, numMoves, numBlocks);
		stateList.add(state);
		currentState = state;
		state.printState();
		return true;
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
	public List<Block> getTowerList(int id)
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
		State state = stateList.get(stateList.size() - 2);
		resetMove(state);

		stateList.remove(stateList.size() - 1);
		currentNumMoves--;
	}

	public void resetMove(State state)
	{
		tower01.getList().clear();
		tower01.getList().addAll(state.getTower01Blocks());

		tower02.getList().clear();
		tower02.getList().addAll(state.getTower02Blocks());

		tower03.getList().clear();
		tower03.getList().addAll(state.getTower03Blocks());
		numMoves = state.getNumMoves();
	}

	public State getCurrentState()
	{
		return currentState;
	}
}
