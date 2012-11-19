package br.com.scrum.factory;

import java.util.ResourceBundle;

import javax.enterprise.inject.Produces;
import javax.enterprise.inject.spi.InjectionPoint;
import javax.faces.context.FacesContext;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import org.jboss.logging.Logger;

import br.com.scrum.entity.enums.Const;
import br.com.scrum.qualifiers.BundleForMsg;

public class Resources {

	@SuppressWarnings("unused")
	@Produces
	@PersistenceContext
	private EntityManager em;

	@Produces
	public Logger produceLog(InjectionPoint injectionPoint)
	{
		return Logger.getLogger(injectionPoint.getMember().getDeclaringClass().getName());
	}
	
	@Produces
	public ResourceBundle createResourceBundle()
	{
		return ResourceBundle.getBundle("br.com.scrum.mensagens.messages_en_US");
	}
	
	@Produces
	@BundleForMsg
	public ResourceBundle makeInjectableForMsg()
	{
		return ResourceBundle.getBundle(Const.PROPERTIES_PATH, FacesContext.getCurrentInstance().getViewRoot().getLocale());
	}

}
