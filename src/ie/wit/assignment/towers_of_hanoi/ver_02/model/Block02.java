package ie.wit.assignment.towers_of_hanoi.ver_02.model;

import java.io.Serializable;

/**
 * A single block
 */
public class Block02 implements Serializable
{
	// TODO: 11/03/2016 calculate the padding here instead of in the view
	// TODO: 11/03/2016 remove unnnessecary size variable and calculate padding form the index
	private double size; // used to calculate padding
	private int index;
	private boolean highlighted;

	/**
	 * constructor
	 *
	 * @param size  the size of the block
	 * @param index identifier
	 */
	public Block02(double size, int index)
	{
		this.size = size;
		this.index = index;
		highlighted = false;
	}

	/**
	 * accessor for size property
	 *
	 * @return size
	 */
	public double getSize()
	{
		return size;
	}

	/**
	 * mutator for size property
	 *
	 * @param size the new size
	 */
	public void setSize(double size)
	{
		this.size = size;
	}

	/**
	 * mutator for index property
	 *
	 * @return the index of the block
	 */
	public int getIndex()
	{
		return index;
	}


	/**
	 * used for debugging
	 *
	 * @return string value of index
	 */
	public String toString()
	{
		return "" + index;
	}
}
