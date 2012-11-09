package br.com.scrum.service;

import java.util.List;

import javax.persistence.NoResultException;

import br.com.scrum.controller.util.Assert;
import br.com.scrum.dao.PersistenceUtil;
import br.com.scrum.entity.Sprint;
import br.com.scrum.entity.Task;

@SuppressWarnings("serial")
public class TaskService extends PersistenceUtil
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
	
	public List<Task> searchBy(Sprint sprint)
	{
		Assert.notNull(sprint, "sprint was null");
		try {
			return super.findByNamedQuery("Task.getBySprint", sprint);
		} catch (NoResultException nre) {
			System.out.println("No sprint found with paramters [" + sprint + "] " + nre);
		} catch (Exception e) {
			System.out.println("Error fetching the sprint " + e);
		}
		return null;
	}
	
}
