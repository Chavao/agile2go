package br.com.scrum.entity.enums;

public final class Const {
	
	/**
	 * representa o nome do schema do banco de dados
	 */
	public static final String SCHEMA = "scrum";		
	
	/**
	 * página principal do sistema 
	 */
	public static final String PRINCIPAL = "/principal/principal.jsf?faces-redirect=true";
	
	/**
	 * página de criação dos projetos 
	 */
	public static final String ADD_PROJECT = "/pages/add_project.jsf?faces-redirect=true";
	
	/**
	 * página de vizualização dos projetos 
	 */
	public static final String LIST_PROJECT = "/pages/list_project.jsf?faces-redirect=true";
	
	/**
	 * página de criação de sprints 
	 */	
	public static final String ADD_SPRINT = "/pages/project/add_sprint.jsf?faces-redirect=true";
	
	/**
	 * página de vizualização dos sprints 
	 */	
	public static final String LIST_SPRINT = "/pages/project/list_sprint.jsf?faces-redirect=true";

	
}
