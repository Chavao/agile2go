package br.com.scrum.domain.service;

import java.io.Serializable;
import java.util.List;

import javax.enterprise.event.Event;
import javax.inject.Inject;
import javax.persistence.NoResultException;

import org.hibernate.exception.ConstraintViolationException;
import org.jboss.solder.exception.control.ExceptionToCatch;
import org.jboss.solder.logging.Logger;

import br.com.scrum.application.util.Assert;
import br.com.scrum.domain.entity.Project;
import br.com.scrum.infrastructure.dao.PersistenceUtil;

public class ProjectService extends PersistenceUtil implements Serializable
{			
	@Inject private Event<ExceptionToCatch> exception;
	@Inject private Logger logger;
	
	public void create(Project project) 
	{
		try {
			super.create(project);			
		} catch ( ConstraintViolationException cve ) {
			exception.fire(new ExceptionToCatch(cve.getCause()));
		}
	}
	
	public void save(Project project) 
	{
		try {
			super.save(project);				
		} catch ( ConstraintViolationException cve ) {
			exception.fire(new ExceptionToCatch(cve.getCause()));	
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
			exception.fire(new ExceptionToCatch(nre));
			logger.error("No project found with paramters [" + name + "]", nre);
		} catch (Exception e) {
			logger.error("Error fetching the project " + e.getCause().getLocalizedMessage());
		}
		return null;
	}

	private static final long serialVersionUID = 973523347646521301L;
}
