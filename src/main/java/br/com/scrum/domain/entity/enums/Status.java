package br.com.scrum.domain.entity.enums;

public enum Status {
	
	TODO("1", "TODO"),
	
	INPROGRESS("2", "IN PROGRESS"),
	
	DONE("3", "DONE"),
	
	WAITTING("4", "WAITTING");
	
	private String code;
	private String description;
	
	private Status (final String code, final String description) {
		this.code = code;
		this.description = description;
	}

	public String getCode() {
		return code;
	}

	public String getDescription() {
		return description;
	}

}
