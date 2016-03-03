package ie.wit.assignment.towers_of_hanoi.ver_02.model;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by Joe on 13/02/2016.
 */
// TODO: 03/03/2016 Create subclass for the fourth tower to handle enable/disable
public class Tower02 implements Serializable
{
	private int id;
	private List<Block02> list;

	public Tower02(int id)
	{
		this.id = id;
		list = new ArrayList<>();
	}

	public int getId()
	{
		return id;
	}

	public List<Block02> getList()
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
