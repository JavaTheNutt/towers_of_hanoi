package ie.wit.assignment.towers_of_hanoi.ver_01.model;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by Joe on 13/02/2016.
 */
public class Tower implements Serializable
{
	private int id;
	private List<Block> list;

	public Tower(int id)
	{
		this.id = id;
		list = new ArrayList<>();
	}

	public int getId()
	{
		return id;
	}

	public List<Block> getList()
	{
		return list;
	}

	public Integer[] getBlockIds()
	{
		Integer[] tempArray = new Integer[list.size()];
		for (int i = 0; i < list.size(); i++) {
			tempArray[i] = list.get(i).getIndex();
		}
		return tempArray;
	}

	public Block getBlockByIndex(int index)
	{
		for (Block block : list) {
			if (index == block.getIndex()) {
				return block;
			}
		}
		return null;
	}


}
