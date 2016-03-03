package ie.wit.assignment.towers_of_hanoi.ver_02.model;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by Joe on 24/02/2016.
 */
// TODO: 03/03/2016 Make a state hold an extra tower 
// TODO: 03/03/2016 Track if tower04 is enabled/disabled

public class State02 implements Serializable
{
	private List<Block02> tower01Blocks;
	private List<Block02> tower02Blocks;
	private List<Block02> tower03Blocks;
	private List<Block02> tower04Blocks;
	private int numMoves;
	private int numBlocks;
	private boolean fourthActive;


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

	public List<Block02> getTower01Blocks()
	{
		return tower01Blocks;
	}

	public List<Block02> getTower02Blocks()
	{
		return tower02Blocks;
	}

	public List<Block02> getTower03Blocks()
	{
		return tower03Blocks;
	}

	public int getNumMoves()
	{
		return numMoves;
	}

	public int getNumBlocks()
	{
		return numBlocks;
	}

	public List<Block02> getTower04Blocks()
	{
		return tower04Blocks;
	}

	public boolean isFourthActive()
	{
		return fourthActive;
	}

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
