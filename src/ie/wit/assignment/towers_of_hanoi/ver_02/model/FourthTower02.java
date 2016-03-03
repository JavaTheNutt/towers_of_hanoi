package ie.wit.assignment.towers_of_hanoi.ver_02.model;

/**
 * Created by Joe on 03/03/2016.
 */
public class FourthTower02 extends Tower02
{
	private boolean active;

	public FourthTower02()
	{
		super(4);
		this.active = false;
	}

	public FourthTower02(boolean active)
	{
		super(4);
		this.active = active;

	}

	public boolean isActive()
	{
		return active;
	}

	public void setActive(boolean active)
	{
		this.active = active;
	}
}
