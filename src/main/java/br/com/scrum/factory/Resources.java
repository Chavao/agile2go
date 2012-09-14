package br.com.scrum.factory;

import javax.enterprise.context.ConversationScoped;
import javax.enterprise.inject.Produces;
import javax.enterprise.inject.spi.InjectionPoint;
import javax.persistence.EntityManagerFactory;
import javax.persistence.PersistenceUnit;

import org.jboss.logging.Logger;
import org.jboss.solder.core.ExtensionManaged;

public class Resources {

	@ExtensionManaged
	@Produces
	@ConversationScoped
	@PersistenceUnit(name = "scrum")
	EntityManagerFactory producerField;

	@Produces
	public Logger produceLog(InjectionPoint injectionPoint) {
		return Logger.getLogger(injectionPoint.getMember().getDeclaringClass().getName());
	}


}
