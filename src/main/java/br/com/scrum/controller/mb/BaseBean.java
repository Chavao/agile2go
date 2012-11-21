package br.com.scrum.controller.mb;

import java.io.Serializable;
import java.text.MessageFormat;
import java.util.ResourceBundle;

import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;
import javax.inject.Inject;

import org.jboss.logging.Logger;

import br.com.scrum.qualifiers.BundleForMsg;

@SuppressWarnings("serial")
public abstract class BaseBean implements Serializable
{		
	@Inject protected Logger logger;
	
	@Inject @BundleForMsg private transient ResourceBundle bundle;
	
	protected void addErrorMsg(String key, Object... args)
	{
		String mensagem = MessageFormat.format(bundle.getString(key), args);
		FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, mensagem, mensagem));
	}

	protected void addInfoMsg(String key, Object... args)
	{
		String msg = MessageFormat.format(bundle.getString(key), args);
		FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, msg, msg));
	}

	protected void addWarnMsg(String key, Object... args)
	{
		String mensagem = MessageFormat.format(bundle.getString(key), args);
		FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_WARN, mensagem, mensagem));
	}

	protected void addWarnMsgForException(Exception exception)
	{
		FacesContext.getCurrentInstance().addMessage(null,
				new FacesMessage(FacesMessage.SEVERITY_WARN, exception.getMessage(), exception.getMessage()));
	}
	
	protected void addErrorMsgFromException(Exception exception)
	{
		FacesContext.getCurrentInstance().addMessage(null,
				new FacesMessage(FacesMessage.SEVERITY_ERROR, exception.getMessage(), exception.getMessage()));
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
