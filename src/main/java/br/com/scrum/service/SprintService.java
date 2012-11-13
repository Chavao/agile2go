package br.com.scrum.service;

import java.util.List;

import javax.persistence.NoResultException;
import javax.persistence.TypedQuery;

import br.com.scrum.dao.PersistenceUtil;
import br.com.scrum.entity.Sprint;
import br.com.scrum.util.exception.ObjectAlreadyExistsException;

@SuppressWarnings("serial")
public class SprintService extends PersistenceUtil
{
	public void create(Sprint sprint) throws Exception 
	{
		try {
			if (sprintNameAlreadyExists(sprint))
				throw new ObjectAlreadyExistsException(sprint.getName() + " already exists");
			super.create(sprint);			
		} catch (Exception e) {
			throw e;
		}
	}
	
	public void save(Sprint sprint) throws Exception
	{
		try {
			if (sprintNameAlreadyExists(sprint))
				throw new ObjectAlreadyExistsException(sprint.getName() + " already exists");
			super.save(sprint);			
		} catch (Exception e) {
			throw e;
		}
	}
	
	public void delete(Sprint sprint) 
	{
		super.delete(getEntityManager.getReference(Sprint.class, sprint.getId()));			
	}
	
	private boolean sprintNameAlreadyExists(Sprint sprint)
	{
		TypedQuery<Sprint> query = getEntityManager.createQuery("from Sprint s where s.name = :name", Sprint.class);
		query.setParameter(Sprint.NAME, sprint.getName());
		try {
			return query.getSingleResult() != null;
		}
		catch (NoResultException e) {
			return false;
		}
	}

	public Sprint findById(Integer id) 
	{
		return super.findById(Sprint.class, id);
	}

	public List<Sprint> findAll() {
		return super.findAll(Sprint.class);
	}

	public List<Sprint> searchBy(String query) 
	{
		try {
			return super.findByNamedQuery("Sprint.getByName", query.toUpperCase());
		} catch (NoResultException nre) {
			logger.warn("No sprint found with paramters [" + query + "] " + nre);
		} catch (Exception e) {
			logger.error("Error fetching the sprint " + e);
		}
		return null;
	}
	
}
