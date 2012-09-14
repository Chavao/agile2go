package br.com.scrum.service;

import java.io.Serializable;
import java.util.List;

import javax.inject.Inject;

import org.jboss.solder.logging.Logger;

import br.com.scrum.dao.PersistenceUtil;
import br.com.scrum.entity.Task;

public class TaskService extends PersistenceUtil implements Serializable 
{
	@Inject private Logger logger;
	
	public void create(Task task) throws Exception 
	{
		try {
			super.create(task);				
		} catch (Exception e ) {
			throw e;	
		}
	}
	
	public void save(Task task) throws Exception 
	{
		try {
			super.save(task);				
		} catch (Exception e) {
			throw e;	
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
