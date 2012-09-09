package br.com.scrum.domain.service;

import java.io.Serializable;
import java.util.List;

import javax.inject.Inject;

import org.hibernate.exception.ConstraintViolationException;
import org.jboss.solder.logging.Logger;

import br.com.scrum.domain.entity.Task;
import br.com.scrum.infrastructure.dao.PersistenceUtil;

public class TaskService extends PersistenceUtil implements Serializable 
{
	@Inject private Logger logger;
	
	public void create(Task task) 
	{
		try {
			super.create(task);				
		} catch ( ConstraintViolationException cve ) {
			logger.error(cve.getCause().getLocalizedMessage());
			throw cve;	
		}
	}
	
	public void save(Task task) 
	{
		try {
			super.save(task);				
		} catch ( ConstraintViolationException cve ) {
			logger.error(cve.getCause().getLocalizedMessage());
			throw cve;	
		}
	}
	
	public void delete(Task task) 
	{
		super.delete(super.getEntityManager.getReference(Task.class, task.getId()));					
	}

	public Task withId(Integer id) 
	{
		return super.findById(Task.class, id);
	}

	public List<Task> findAll() 
	{
		return super.findAll(Task.class);
	}

	private static final long serialVersionUID = 9002969380414395854L;
	
}
