package br.com.scrum.controller.mb;

import java.io.IOException;
import java.io.Serializable;

import javax.enterprise.context.RequestScoped;
import javax.faces.context.ExternalContext;
import javax.faces.context.FacesContext;
import javax.inject.Inject;
import javax.inject.Named;

import br.com.scrum.controller.UserLogged;
import br.com.scrum.entity.User;
import br.com.scrum.service.UserService;

@SuppressWarnings("serial")
@Named
@RequestScoped
public class AuthenticationMB extends BaseBean implements Serializable 
{	
	@Inject private UserService userService;
	@Inject private UserLogged userLogged;
	
	private User user = new User();
	
	public void authenticate()
	{
		if (!user.getLogin().isEmpty() && !user.getPassword().isEmpty()) {
			User logged = userService.getUserByCredential(user.getLogin(), user.getPassword());
			if (logged != null) {
				userLogged.setUser(logged);
				redirectToViewId("/main/main.jsf");
			}
		}
		redirectToLoginIfNotLoggedIn();
	}

	public String logout()
	{
		setInSession("userLogged", null);
        userLogged.setUser(null);
        return "/login.jsf";
	}

	public void redirectToLoginIfNotLoggedIn() 
	{
		if (!userLogged.isLoggedIn()) 
		{
			user.setLogin("");
			user.setPassword("");
			addLoginErrorMessage("User not found!");
			redirectToViewId("/login.jsf");
		}
	}

	private void redirectToViewId(String viewId) 
	{
		ExternalContext externalContext = FacesContext.getCurrentInstance().getExternalContext();
		try {
			externalContext.redirect(externalContext.getRequestContextPath() + viewId);
		} catch (IOException e)
		{ e.printStackTrace(); }
	}
	
	public void addLoginErrorMessage(String infoMessage) 
	{
		addErrorMessage(infoMessage);
	}

	public User getUser()
	{
		return user;
	}

	public void setUser(User user)
	{
		this.user = user;
	}

	public UserLogged getUserLogged()
	{
		return userLogged;
	}

	public void setUserLogged(UserLogged userLogged)
	{
		this.userLogged = userLogged;
	}
	
}
