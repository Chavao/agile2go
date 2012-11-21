package br.com.scrum.factory;

import java.util.ResourceBundle;

import javax.enterprise.context.ConversationScoped;
import javax.enterprise.inject.Produces;
import javax.enterprise.inject.spi.InjectionPoint;
import javax.faces.context.FacesContext;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.PersistenceContext;
import javax.persistence.PersistenceUnit;

import org.jboss.logging.Logger;
import org.jboss.solder.core.ExtensionManaged;

import br.com.scrum.entity.enums.Const;
import br.com.scrum.qualifiers.BundleForMsg;

public class Resources {

	@Produces
	@ExtensionManaged
	@ConversationScoped
	@PersistenceUnit(name = "scrum")
	EntityManagerFactory em;

	@Produces
	public Logger produceLog(InjectionPoint injectionPoint)
	{
		return Logger.getLogger(injectionPoint.getMember().getDeclaringClass().getName());
	}
	
	@Produces
	public ResourceBundle createResourceBundle()
	{
		return ResourceBundle.getBundle(Const.PROPERTIES_PATH);
	}
	
	@Produces
	@BundleForMsg
	public ResourceBundle makeInjectableForMsg()
	{
		return ResourceBundle.getBundle(Const.PROPERTIES_PATH, FacesContext.getCurrentInstance().getViewRoot().getLocale());
	}

}
