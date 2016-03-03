package ie.wit.assignment.towers_of_hanoi.ver_01.model;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by Joe on 24/02/2016.
 */
public class State implements Serializable
{
	private List<Block> tower01Blocks;
	private List<Block> tower02Blocks;
	private List<Block> tower03Blocks;
	private int numMoves;
	private int numBlocks;


	public State()
	{
		tower01Blocks = null;
		tower02Blocks = null;
		tower03Blocks = null;
		numMoves = 0;
		numBlocks = 3;
	}

	public State(Tower tower01, Tower tower02, Tower tower03, int numMoves, int numBlocks)
	{
		tower01Blocks = new ArrayList<>();
		tower01Blocks.addAll(tower01.getList());

		tower02Blocks = new ArrayList<>();
		tower02Blocks.addAll(tower02.getList());

		tower03Blocks = new ArrayList<>();
		tower03Blocks.addAll(tower03.getList());
		this.numMoves = numMoves;
		this.numBlocks = numBlocks;
	}

	public List<Block> getTower01Blocks()
	{
		return tower01Blocks;
	}

	public List<Block> getTower02Blocks()
	{
		return tower02Blocks;
	}

	public List<Block> getTower03Blocks()
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

	public void printState()
	{
		System.out.println("Tower01\n");
		for (Block block : tower01Blocks) {
			System.out.println(block.toString());
		}
		System.out.println("Tower02\n");
		for (Block block : tower02Blocks) {
			System.out.println(block.toString());
		}
		System.out.println("Tower03\n");
		for (Block block : tower03Blocks) {
			System.out.println(block.toString());
		}
	}

}
