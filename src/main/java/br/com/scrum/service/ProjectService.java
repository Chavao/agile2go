package br.com.scrum.service;

import java.io.Serializable;
import java.util.List;

import javax.persistence.NoResultException;
import javax.persistence.TypedQuery;

import br.com.scrum.controller.util.Assert;
import br.com.scrum.dao.PersistenceUtil;
import br.com.scrum.entity.Project;
import br.com.scrum.util.exception.BusinessException;

public class ProjectService extends PersistenceUtil implements Serializable
{			
	public void create(Project project) throws Exception 
	{
		try {
			Project exist = exists(project.getName());
			if (exist != null && !exist.equals("")) {
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

	public void delete(Project project) 
	{
		super.delete(super.getEntityManager.getReference(Project.class, project.getId()));				
	}

	public Project withId(Integer id) 
	{
		return super.findById(Project.class, id);
	}

	public List<Project> findAll() 
	{
		return super.findAll(Project.class);
	}		

	public List<Project> searchBy(String name) 
	{
		Assert.notNull(name, "query was null");
		try {
			return super.findByNamedQuery("Project.getByName", name.toUpperCase());
		} catch (NoResultException nre) {
			return null;
		}
	}

	private static final long serialVersionUID = 973523347646521301L;
}
