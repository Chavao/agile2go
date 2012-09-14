package br.com.scrum.domain.service;

import java.io.Serializable;
import java.util.List;

import javax.enterprise.event.Event;
import javax.inject.Inject;
import javax.persistence.NoResultException;

import org.hibernate.exception.ConstraintViolationException;
import org.jboss.solder.exception.control.ExceptionToCatch;
import org.jboss.solder.logging.Logger;

import br.com.scrum.application.util.Assert;
import br.com.scrum.domain.entity.Sprint;
import br.com.scrum.infrastructure.dao.PersistenceUtil;
import br.com.scrum.infrastructure.dao.exception.BusinessException;

public class SprintService extends PersistenceUtil implements Serializable 
{
	@Inject private Event<ExceptionToCatch> exception;
	@Inject private Logger logger;
	
	public void create(Sprint sprint) throws Exception 
	{
		try {
			if (exists(sprint)) {
				throw new BusinessException("sprint already exists");
			}
			super.create(sprint);			
		} catch (Exception e) {
			throw e;
		}
	}
	
	private boolean exists(Sprint sprint) {
		return !searchBy(sprint.getName()).isEmpty();
	}

	public void save(Sprint sprint) 
	{
		try {
			super.save(sprint);			
		} catch ( ConstraintViolationException cve) {
			exception.fire(new ExceptionToCatch(cve.getCause()));
		}
	}
	
	public void delete(Sprint sprint) 
	{
		super.delete(super.getEntityManager.getReference(Sprint.class, sprint.getId()));			
	}

	public Sprint withId(Integer id) 
	{
		return super.findById(Sprint.class, id);
	}

	public List<Sprint> findAll() {
		return super.findAll(Sprint.class);
	}

	public List<Sprint> searchBy(String query) 
	{
		Assert.notNull(query, "query was null");
		try {
			return super.findByNamedQuery("Sprint.getByName", query.toUpperCase());
		} catch (NoResultException nre) {
			exception.fire(new ExceptionToCatch(nre));
			logger.error("No sprint found with paramters [" + query + "]", nre);
		} catch (Exception e) {
			logger.error("Error fetching the sprint " + e);
		}
		return null;
	}
	
	private static final long serialVersionUID = 7484077875891258960L;

}
