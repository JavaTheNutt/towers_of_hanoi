package ie.wit.assignment.towers_of_hanoi.ver_02.model;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

/**
 * This class will hold info about the state of a game at a specified period of time. Used for saving
 * and also resetting a move
 */

public class State02 implements Serializable
{
	private List<Block02> tower01Blocks;
	private List<Block02> tower02Blocks;
	private List<Block02> tower03Blocks;
	private List<Block02> tower04Blocks;
	private int numMoves;
	private int numBlocks;
	private boolean fourthActive;


	/**
	 * default constructor
	 */
	public State02()
	{
		tower01Blocks = null;
		tower02Blocks = null;
		tower03Blocks = null;
		tower04Blocks = null;
		fourthActive = false;
		numMoves = 0;
		numBlocks = 3;
	}

	/**
	 * constructor
	 *
	 * @param tower01   tower1
	 * @param tower02   tower2
	 * @param tower03   tower3
	 * @param tower04   tower4
	 * @param numMoves  number of moves
	 * @param numBlocks number of blocks
	 */
	public State02(Tower02 tower01,
	               Tower02 tower02,
	               Tower02 tower03,
	               FourthTower02 tower04,
	               int numMoves,
	               int numBlocks)
	{
		tower01Blocks = new ArrayList<>();
		tower01Blocks.addAll(tower01.getList());

		tower02Blocks = new ArrayList<>();
		tower02Blocks.addAll(tower02.getList());

		tower03Blocks = new ArrayList<>();
		tower03Blocks.addAll(tower03.getList());

		tower04Blocks = new ArrayList<>();
		tower04Blocks.addAll(tower04.getList());

		this.fourthActive = tower04.isActive();
		this.numMoves = numMoves;
		this.numBlocks = numBlocks;
	}

	/**
	 * accessor for tower 1
	 *
	 * @return list of blocks in tower 1
	 */
	public List<Block02> getTower01Blocks()
	{
		return tower01Blocks;
	}

	/**
	 * accessor for tower 2
	 *
	 * @return list of blocks in tower 2
	 */
	public List<Block02> getTower02Blocks()
	{
		return tower02Blocks;
	}

	/**
	 * accessor for tower 3
	 *
	 * @return list of blocks in tower 3
	 */
	public List<Block02> getTower03Blocks()
	{
		return tower03Blocks;
	}

	/**
	 * accessor for num moves
	 *
	 * @return num moves
	 */
	public int getNumMoves()
	{
		return numMoves;
	}

	/**
	 * accesor for num blocks
	 *
	 * @return num blocks
	 */
	public int getNumBlocks()
	{
		return numBlocks;
	}

	/**
	 * accessor for tower 4
	 *
	 * @return list of blocks in tower 4
	 */
	public List<Block02> getTower04Blocks()
	{
		return tower04Blocks;
	}

	/**
	 * accesor for fourth active flag
	 *
	 * @return is the fourth tower active
	 */
	public boolean isFourthActive()
	{
		return fourthActive;
	}

	/**
	 * mutator for fourth active flag
	 *
	 * @param fourthActive is fourth active
	 */
	public void setFourthActive(boolean fourthActive)
	{
		this.fourthActive = fourthActive;
	}

	/**
	 * used for debugging
	 */
	public void printState()
	{
		System.out.println("Tower01\n");
		for (Block02 block : tower01Blocks) {
			System.out.println(block.toString());
		}
		System.out.println("Tower02\n");
		for (Block02 block : tower02Blocks) {
			System.out.println(block.toString());
		}
		System.out.println("Tower03\n");
		for (Block02 block : tower03Blocks) {
			System.out.println(block.toString());
		}
	}

}
