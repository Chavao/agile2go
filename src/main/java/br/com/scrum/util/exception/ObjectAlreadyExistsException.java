package br.com.scrum.util.exception;

@SuppressWarnings("serial")
public class ObjectAlreadyExistsException extends Exception
{
	public ObjectAlreadyExistsException(String msg)
	{
		super(msg);
	}
	
	public ObjectAlreadyExistsException(Throwable cause)
	{
		super(cause);
	}
}
