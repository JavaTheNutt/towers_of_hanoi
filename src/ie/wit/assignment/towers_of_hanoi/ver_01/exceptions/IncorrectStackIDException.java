package ie.wit.assignment.towers_of_hanoi.ver_01.exceptions;

/**
 * Created by Joe on 12/02/2016.
 */
public class IncorrectStackIDException extends Exception
{
	public IncorrectStackIDException()
	{
		super("Incorrect stack id. Please enter 1, 2 or 3");
	}

	public IncorrectStackIDException(String message)
	{
		super(message);
	}
}
