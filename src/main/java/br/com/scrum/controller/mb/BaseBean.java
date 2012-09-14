package br.com.scrum.controller.mb;

import java.io.Serializable;

import javax.faces.application.FacesMessage;
import javax.faces.application.FacesMessage.Severity;
import javax.faces.context.FacesContext;

public class BaseBean implements Serializable{		

	protected void addErrorMessage(String componentId, String errorMessage){
		addMessage(componentId, errorMessage, FacesMessage.SEVERITY_ERROR);
	}

	protected void addErrorMessage(String errorMessage){
		addErrorMessage(null, errorMessage);
	}

	protected void addInfoMessage(String componentId, String infoMessage){
		addMessage(componentId, infoMessage, FacesMessage.SEVERITY_INFO);
	}

	protected void addInfoMessage(String infoMessage){
		addInfoMessage(null, infoMessage);
	}		
	
	private void addMessage(String componentId, String errorMessage, Severity severity){
		FacesMessage message =  new FacesMessage(errorMessage);
		message.setSeverity(severity);
		FacesContext.getCurrentInstance().addMessage(componentId, message);		
	}
	
	private static final long serialVersionUID = 1L;
}
