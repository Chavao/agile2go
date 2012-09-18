package br.com.scrum.service;

import java.io.Serializable;
import java.util.List;

import javax.persistence.NoResultException;
import javax.persistence.TypedQuery;

import br.com.scrum.controller.util.Assert;
import br.com.scrum.dao.PersistenceUtil;
import br.com.scrum.entity.Sprint;
import br.com.scrum.util.exception.BusinessException;

public class SprintService extends PersistenceUtil implements Serializable 
{
	public void create(Sprint sprint) throws Exception 
	{
		try {
			Sprint exist = exists(sprint.getName());
			if (exist != null && !exist.equals("")) {
				throw new BusinessException("sprint already exists");
			}
			super.create(sprint);			
		} catch (Exception e) {
			throw e;
		}
	}
	
	private Sprint exists(String name)
	{
		TypedQuery<Sprint> query = getEntityManager.createQuery("from Sprint s where s.name = :name", Sprint.class);
		query.setParameter(Sprint.NAME, name);
		try {
			return query.getSingleResult();
		}
		catch (NoResultException e) {
			return null;
		}
	}

	public void save(Sprint sprint) throws Exception
	{
		try {
			super.save(sprint);			
		} catch (Exception e) {
			throw e;
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
			System.out.println("No sprint found with paramters [" + query + "] " + nre);
		} catch (Exception e) {
			System.out.println("Error fetching the sprint " + e);
		}
		return null;
	}
	
	private static final long serialVersionUID = 7484077875891258960L;

}
