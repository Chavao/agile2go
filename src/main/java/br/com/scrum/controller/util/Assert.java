package br.com.scrum.controller.util;

public final class Assert {

	public static boolean notBlank(Object object) 
	{
		return object != null && !object.equals(""); 
	}

	public static void notNull(final Object object, final String message) throws IllegalStateException 
	{
		if (object == null)
		{
			throw new IllegalArgumentException(message);
		}
	}

	public static void notTrue(final boolean value, final String message) 
	{
		if (value != true) 
		{
			throw new IllegalArgumentException(message);
		}
	}

}


