package br.com.scrum.service;

import java.io.Serializable;
import java.util.List;

import br.com.scrum.dao.PersistenceUtil;
import br.com.scrum.entity.Task;

public class TaskService extends PersistenceUtil implements Serializable 
{
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
		super.delete(getEntityManager.getReference(Task.class, task.getId()));					
	}

	public Task findById(Integer id) 
	{
		return super.findById(Task.class, id);
	}

	public List<Task> findAll() 
	{
		return super.findAll(Task.class);
	}

	private static final long serialVersionUID = 9002969380414395854L;
	
}
