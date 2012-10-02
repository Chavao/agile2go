package br.com.scrum.factory;

import javax.enterprise.context.ConversationScoped;
import javax.enterprise.inject.Produces;
import javax.enterprise.inject.spi.InjectionPoint;
import javax.persistence.EntityManagerFactory;
import javax.persistence.PersistenceUnit;

import org.jboss.logging.Logger;
import org.jboss.solder.core.ExtensionManaged;

public class Resources {


	//production
	@Produces
	@ExtensionManaged
	@ConversationScoped
	@PersistenceUnit(name="scrum")
	EntityManagerFactory producerField;

	// tests
//	@SuppressWarnings("unused")
//	@Produces
//	@PersistenceContext(unitName = "primary")
//	private EntityManager em;

	@Produces
	public Logger produceLog(InjectionPoint injectionPoint) {
		return Logger.getLogger(injectionPoint.getMember().getDeclaringClass().getName());
	}

//	@Produces
//	public Resources create(InjectionPoint injectionPoint) {
//		ParameterizedType type = (ParameterizedType) injectionPoint.getType();
//		Class classe = (Class) type.getActualTypeArguments()[0];
//		return new PersistenceU;
//	}
}
