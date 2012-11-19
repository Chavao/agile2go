package br.com.scrum.interceptor;

import java.io.Serializable;

import javax.inject.Inject;
import javax.interceptor.AroundInvoke;
import javax.interceptor.Interceptor;
import javax.interceptor.InvocationContext;
import javax.persistence.EntityManager;

import org.jboss.logging.Logger;

/**
 * @author rafael
 * 
 */
@SuppressWarnings("serial")
@Interceptor
@Transactional
public class TransactionInterceptor implements Serializable
{
	@Inject private EntityManager em;
	@Inject private Logger LOG;

	@AroundInvoke
	public Object intercept(InvocationContext ctx) throws Exception
	{
		try {
			LOG.info("Opening transaction...");
			em.getTransaction().begin();
			
			Object result = ctx.proceed();
			
			LOG.info("commiting...");
			em.getTransaction().commit();
			
			return result;
			
		} catch (Exception e) {
			LOG.error(e);
			e.printStackTrace();
			throw e;
		}
	}
}
