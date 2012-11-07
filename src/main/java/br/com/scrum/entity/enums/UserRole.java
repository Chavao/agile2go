package br.com.scrum.entity.enums;

public enum UserRole
{
	MASTER("1", "Scrum Master"),
	TEAM("2", "Dev Team"),
	PO("3", "Product Owner");
	
	private String code;
	private String description;
	
	private UserRole (final String code, final String description)
	{
		this.code = code;
		this.description = description;
	}

	public String getCode()
	{
		return code;
	}

	public String getDescription()
	{
		return description;
	}
}
