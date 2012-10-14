package br.com.scrum.controller.mb;

import java.io.IOException;
import java.io.Serializable;

import javax.enterprise.context.SessionScoped;
import javax.faces.application.FacesMessage;
import javax.faces.application.FacesMessage.Severity;
import javax.faces.context.ExternalContext;
import javax.faces.context.FacesContext;
import javax.inject.Inject;
import javax.inject.Named;

import org.jboss.seam.security.Authenticator;
import org.jboss.seam.security.BaseAuthenticator;
import org.jboss.seam.security.CredentialsImpl;
import org.jboss.seam.security.Identity;
import org.jboss.seam.security.management.IdmAuthenticator;
import org.picketlink.idm.impl.api.PasswordCredential;
import org.picketlink.idm.impl.api.model.SimpleUser;

import br.com.scrum.entity.User;
import br.com.scrum.service.UserService;

@Named
@SessionScoped
public class AuthenticationMB extends BaseAuthenticator implements Authenticator , Serializable 
{	
	@Inject private UserService userService;
	@Inject private CredentialsImpl credentials;
	@Inject private Identity identity;
	
	public void authenticate()
	{
		User user = userService.getUserByCredential(credentials.getUsername(), credentials.getPassword());
		if (user != null && credentials.getCredential() instanceof PasswordCredential && 
			user.getPassword().equals(((PasswordCredential) credentials.getCredential()).getValue())) 
		{
			setStatus(AuthenticationStatus.SUCCESS);
			identity.addRole(credentials.getUsername(), "USERS", "GROUP");
			setUser(new SimpleUser(user.getLogin()));
			redirectToViewId("/main/main.jsf");
			return;
		}
		setStatus(AuthenticationStatus.FAILURE);
		addLoginErrorMessage("Use not found");
		redirectToLoginIfNotLoggedIn();
	}

	public String logout()
	{
		identity.setAuthenticatorClass(IdmAuthenticator.class);
		identity.logout();
		redirectToViewId("/index.html");
		return "";
	}

	public AuthenticationStatus getStatus() 
	{
		return super.getStatus();
	}
	
	public boolean isLoggedIn() 
	{
		return getStatus().equals(AuthenticationStatus.SUCCESS);
	}

	public void redirectToLoginIfNotLoggedIn() 
	{
		if (!isLoggedIn()) 
		{
			credentials.setUsername("");
			redirectToViewId("/login.jsf");
			addLoginErrorMessage("User not found!");
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
		addMessage(null, infoMessage, FacesMessage.SEVERITY_ERROR);
	}		
	
	private void addMessage(String componentId, String errorMessage, Severity severity)
	{
		FacesMessage message =  new FacesMessage(errorMessage);
		message.setSeverity(severity);
		FacesContext.getCurrentInstance().addMessage(componentId, message);		
	}

	private static final long serialVersionUID = 8944449513389432047L;

}
