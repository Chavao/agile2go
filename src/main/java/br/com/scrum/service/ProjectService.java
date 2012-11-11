package br.com.scrum.service;

import java.util.List;

import javax.persistence.NoResultException;
import javax.persistence.TypedQuery;

import br.com.scrum.controller.util.Assert;
import br.com.scrum.dao.PersistenceUtil;
import br.com.scrum.entity.Project;
import br.com.scrum.util.exception.BusinessException;

@SuppressWarnings("serial")
public class ProjectService extends PersistenceUtil
{			
	public void create(Project project) throws Exception 
	{
		try {
			Project exist = exists(project.getName());
			if (exist != null) {
				throw new BusinessException("project already exists");
			}
			super.create(project);			
		} catch (Exception e) {
			throw e;
		}
	}

	private Project exists(String name)
	{
		TypedQuery<Project> query = getEntityManager.createQuery("from Project p where p.name = :name", Project.class);
		query.setParameter("name", name);
		try {
			return query.getSingleResult();
		}
		catch (NoResultException e) {
			return null;
		}
	}

	public void save(Project project) throws Exception 
	{	
		try {
			super.save(project);				
		} catch (Exception e ) {
			throw e;	
		}
	}

	public void delete(Project project) throws Exception
	{
		try {
			super.delete(getEntityManager.getReference(Project.class, project.getId()));				
		} catch (Exception e) {
			throw e;
		}
	}

	public Project withId(Integer id) 
	{
		return super.findById(Project.class, id);
	}

	public List<Project> findAll() 
	{
		return super.findAll(Project.class);
	}		

	public List<Project> searchBy(String query) 
	{
		Assert.notNull(query, "query was null");
		try {
			return getEntityManager.createQuery("from Project p where upper(p.name) like :name " +
												"or upper(p.company) like :company ", Project.class)
					.setParameter("name", "%" + query + "%")
					.setParameter("company", "%" + query + "%")
					.getResultList();
		} catch (NoResultException nre) {
			return null;
		}
	}

}
