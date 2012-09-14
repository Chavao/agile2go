package br.com.scrum.domain.service;

import java.io.Serializable;
import java.util.List;

import javax.persistence.NoResultException;

import br.com.scrum.application.util.Assert;
import br.com.scrum.domain.entity.Project;
import br.com.scrum.infrastructure.dao.PersistenceUtil;
import br.com.scrum.infrastructure.dao.exception.BusinessException;

public class ProjectService extends PersistenceUtil implements Serializable
{			
	public void create(Project project) throws Exception 
	{
		try {
			if (super.exists("Project.countByName", project.getName())) {
				throw new BusinessException("project already exists");
			}
			super.create(project);			
		} catch (Exception e) {
			throw e;
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
