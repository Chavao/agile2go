package br.com.scrum.controller;

import java.io.Serializable;

import javax.enterprise.context.SessionScoped;
import javax.inject.Named;

import br.com.scrum.entity.User;

@SuppressWarnings("serial")
@Named
@SessionScoped
public class UserLogged implements Serializable 
{
	private User user;
	
	public boolean isLoggedIn()
	{
		return user != null;
	}

	public User getUser()
	{
		return user;
	}

	public void setUser(User user) 
	{
		this.user = user;
	}
	
}
