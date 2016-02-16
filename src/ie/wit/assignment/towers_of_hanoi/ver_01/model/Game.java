package ie.wit.assignment.towers_of_hanoi.ver_01.model;

import javafx.collections.ObservableList;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Joe on 12/02/2016.
 */
public class Game
{
	private int numMoves;
	private boolean gameWon;
	private Tower tower01;
	private Tower tower02;
	private Tower tower03;
	private Tower[] towers;
	private int numBlocks;
	private List<Integer[]> tower1MoveList;
	private List<Integer[]> tower2MoveList;
	private List<Integer[]> tower3MoveList;
	private List<Block> blockList;
	/**
	 * Constructor
	 *
	 * @param numBlocks is the number of blocks to be in the game
	 */
	public Game(int numBlocks)
	{
		this.numBlocks = numBlocks;
		numMoves = 0;
		tower1MoveList = new ArrayList<>();
		tower2MoveList = new ArrayList<>();
		tower3MoveList = new ArrayList<>();
		blockList = new ArrayList<>();
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

	/**
	 * This method is called by the constructor and will start the game and place the correct number of blocks in the first tower
	 */
	private void startGame()
	{
		tower01.getList().clear();
		tower02.getList().clear();
		tower03.getList().clear();
		for (int i = 0; i < numBlocks; i++) {
			Block block = new Block(i * 0.5, i);
			towers[0].getList().add(block);
			blockList.add(block);
		}
		tower1MoveList.add(tower01.getBlockIds());
	}

	public int getNumBlocks()
	{
		return numBlocks;
	}



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
		if (checkWon()) {
			gameWon = true;
		}
		tower1MoveList.add(tower01.getBlockIds());
		tower2MoveList.add(tower02.getBlockIds());
		tower3MoveList.add(tower03.getBlockIds());
		return true;
	}

	public boolean checkWon()
	{
		return tower02.getList().size() == numBlocks;
	}

	public ObservableList<Block> getTowerList(int id)
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

	public void resetMove(){
		if (numMoves < 1 || gameWon){
			return;
		}
		resetMoveCtrl(tower01);
		resetMoveCtrl(tower02);
		resetMoveCtrl(tower03);
		numMoves--;
	}
	private void resetMoveCtrl(Tower tower){
		tower.getList().clear();
		List<Integer[]> list;
		if (tower == tower01){
			list = tower1MoveList;
		} else if(tower == tower02){
			list = tower2MoveList;
		} else{
			list = tower3MoveList;
		}
		if (list.size() <= 1){
			return;
		}
		Integer[] moveList = list.get(list.size() -2);
		for (Integer num: moveList) {
			System.out.println(num);
			for (Block block: blockList){
				if (block.getIndex() == num){
					tower.getList().add(block);
				}
			}
		}
		list.remove(list.size() -1);

	}
	public List<Integer[]> getTower1MoveList()
	{
		return tower1MoveList;
	}

	public List<Integer[]> getTower2MoveList()
	{
		return tower2MoveList;
	}

	public List<Integer[]> getTower3MoveList()
	{
		return tower3MoveList;
	}
}
