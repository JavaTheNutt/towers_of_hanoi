package ie.wit.assignment.towers_of_hanoi.ver_02.model;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

/**
 * Instance of a tower
 */

public class Tower02 implements Serializable
{
	private int id;
	private List<Block02> list;

	/**
	 * constructor
	 *
	 * @param id the id of the tower
	 */
	public Tower02(int id)
	{
		this.id = id;
		list = new ArrayList<>();
	}

	/**
	 * accesor for id
	 *
	 * @return id
	 */
	public int getId()
	{
		return id;
	}

	/**
	 * accesor for list of blocks
	 *
	 * @return the list of blocks
	 */
	public List<Block02> getList()
	{
		return list;
	}

	/**
	 * get the ids of all the blocks in the current towers
	 *
	 * @return an array of all the ids
	 */
	public Integer[] getBlockIds()
	{
		Integer[] tempArray = new Integer[list.size()];
		for (int i = 0; i < list.size(); i++) {
			tempArray[i] = list.get(i).getIndex();
		}
		return tempArray;
	}

	/**
	 * return a block based on its index
	 *
	 * @param index of block
	 * @return block
	 */
	public Block02 getBlockByIndex(int index)
	{
		for (Block02 block : list) {
			if (index == block.getIndex()) {
				return block;
			}
		}
		return null;
	}
}
