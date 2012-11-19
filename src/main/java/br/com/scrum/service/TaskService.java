package br.com.scrum.service;

import java.util.List;

import javax.persistence.NoResultException;

import br.com.scrum.entity.Sprint;
import br.com.scrum.entity.Task;
import br.com.scrum.repository.PersistenceUtil;

@SuppressWarnings("serial")
public class TaskService extends PersistenceUtil
{
	public void create(Task task) throws Exception 
	{
		try {
			super.create(task);				
		} catch (Exception e ) {
			logger.error(e);
			throw e;	
		}
	}
	
	public void save(Task task) throws Exception 
	{
		try {
			super.save(task);				
		} catch (Exception e) {
			logger.error(e);
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
	
	public List<Task> searchBy(Sprint sprint)
	{
		try {
			return getEntityManager.createNamedQuery("Task.getBySprint", Task.class)
								   .setParameter("sprint", sprint)
								   .getResultList();
		} catch (NoResultException nre) {
			logger.error("No sprint found with paramters [" + sprint + "] " + nre);
		} catch (Exception e) {
			logger.error("Error fetching the sprint " + e);
		}
		return null;
	}
	
}
