package br.com.scrum.controller.mb;

import java.io.Serializable;

import javax.faces.application.FacesMessage;
import javax.faces.application.FacesMessage.Severity;
import javax.faces.context.FacesContext;
import javax.inject.Inject;

import org.jboss.logging.Logger;

@SuppressWarnings("serial")
public class BaseBean implements Serializable
{		
	@Inject protected Logger logger;
	
	protected void addErrorMessage(String componentId, String errorMessage) 
	{
		addMessage(componentId, errorMessage, FacesMessage.SEVERITY_ERROR);
	}

	protected void addErrorMessage(String errorMessage)
	{
		addErrorMessage(null, errorMessage);
	}

	protected void addInfoMessage(String componentId, String infoMessage)
	{
		addMessage(componentId, infoMessage, FacesMessage.SEVERITY_INFO);
	}

	protected void addInfoMessage(String infoMessage)
	{
		addInfoMessage(null, infoMessage);
	}		
	
	private void addMessage(String componentId, String errorMessage, Severity severity)
	{
		FacesMessage message =  new FacesMessage(errorMessage);
		message.setSeverity(severity);
		FacesContext.getCurrentInstance().addMessage(componentId, message);		
	}
	
	public static Object getManagedBean(String name, Class<?> model) 
	{
		return FacesContext.getCurrentInstance().getApplication()
				.evaluateExpressionGet(FacesContext.getCurrentInstance(), "#{" + name + "}", model);
	}

	public static Object getFromSession(String param)
	{
		return FacesContext.getCurrentInstance().getExternalContext().getSessionMap().get(param);
	}

	public static void setInSession(String param, String value)
	{
		FacesContext.getCurrentInstance().getExternalContext().getSessionMap().put(param, value);
	}
	
}
