package ie.wit.assignment.towers_of_hanoi.ver_02.model;

/**
 * Subclass of tower. This tower can be disabled
 */
public class FourthTower02 extends Tower02
{

	private boolean active; // is the tower active?

	/**
	 * default constructor. used for new games
	 */
	public FourthTower02()
	{
		super(4);
		this.active = false;
	}

	/**
	 * constructor used when loading game
	 *
	 * @param active is the fourth tower active?
	 */
	public FourthTower02(boolean active)
	{
		super(4);
		this.active = active;

	}

	/**
	 * accessor for active property
	 *
	 * @return active property
	 */
	public boolean isActive()
	{
		return active;
	}

	/**
	 * mutator for active property
	 *
	 * @param active activate/deactivate tower
	 */
	public void setActive(boolean active)
	{
		this.active = active;
	}
}
