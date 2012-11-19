package br.com.scrum.service;

import java.util.List;

import javax.persistence.NoResultException;

import br.com.scrum.entity.Sprint;
import br.com.scrum.repository.PersistenceUtil;
import br.com.scrum.util.exception.ObjectAlreadyExistsException;

@SuppressWarnings("serial")
public class SprintService extends PersistenceUtil
{
	public void create(Sprint sprint) throws Exception 
	{
		try {
			if (sprintAlreadyExists(sprint))
				throw new ObjectAlreadyExistsException(sprint.getName() + " already exists");
			super.create(sprint);
//			evictCache(Sprint.GET_BY_NAME);
		} catch (Exception e) {
			throw e;
		}
	}

	public void save(Sprint sprint) throws Exception
	{
		try {
			if (sprintAlreadyExists(sprint))
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

	private boolean sprintAlreadyExists(Sprint sprint)
	{
		try {
			return getEntityManager.createNamedQuery(Sprint.GET_BY_NAME, Sprint.class)
					.setParameter(Sprint.NAME, "%" + sprint.getName().trim() + "%")
					.getResultList() != null;
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
			return getEntityManager.createNamedQuery(Sprint.GET_BY_NAME, Sprint.class)
					.setParameter(Sprint.NAME, "%" + query.trim().toUpperCase() + "%")
					.getResultList();
		} catch (NoResultException nre) {
			logger.warn("No sprint found with paramters [" + query + "] " + nre);
		}
		return null;
	}

}
