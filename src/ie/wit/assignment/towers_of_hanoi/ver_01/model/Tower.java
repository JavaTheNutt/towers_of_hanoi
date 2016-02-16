package ie.wit.assignment.towers_of_hanoi.ver_01.model;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

/**
 * Created by Joe on 13/02/2016.
 */
public class Tower
{
	private int id;
	private ObservableList<Block> list;

	public Tower(int id)
	{
		this.id = id;
		list = FXCollections.observableArrayList();
	}

	public int getId()
	{
		return id;
	}

	public ObservableList<Block> getList()
	{
		return list;
	}

	public Integer[] getBlockIds(){
		Integer [] tempArray = new Integer[list.size()];
		for (int i = 0; i < list.size(); i++) {
			tempArray[i] = list.get(i).getIndex();
		}
		return tempArray;
	}
	public Block getBlockByIndex(int index){
		for (Block block: list) {
			if(index == block.getIndex()){
				return block;
			}
		}
		return null;
	}


}
