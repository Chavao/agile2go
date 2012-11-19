package br.com.scrum.service;

import java.util.List;
import java.util.ResourceBundle;

import javax.enterprise.event.Event;
import javax.inject.Inject;
import javax.persistence.NoResultException;

import org.jboss.solder.exception.control.ExceptionToCatch;

import br.com.scrum.entity.Project;
import br.com.scrum.repository.PersistenceUtil;

@SuppressWarnings("serial")
public class ProjectService extends PersistenceUtil
{	
	@Inject Event<ExceptionToCatch> catchEvent;
//	@Inject private transient ResourceBundle bundle;
	
	public void create(Project project)
	{
		try {
			super.create(project);
			getEntityManager.flush();
//			evictCache(Project.GET_BY_NAME);
		} catch (Exception e) {
			
		}
	}

	public void save(Project project) 
	{	
		try {
			super.save(project);
		} catch (Exception e) {
			try {
				throw e;
			} catch (Exception e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
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

	public Project findById(Integer id) 
	{
		return super.findById(Project.class, id);
	}

	public List<Project> findAll() 
	{
		return super.findAll(Project.class);
	}		

	public List<Project> searchBy(String query) 
	{
		try {
			return getEntityManager.createNamedQuery(Project.GET_BY_NAME_OR_COMPANY, Project.class)
					.setParameter(Project.NAME, "%" + query + "%")
					.setParameter(Project.COMPANY, "%" + query + "%")
					.getResultList();
		} catch (NoResultException nre) {
			return null;
		}
	}

}
