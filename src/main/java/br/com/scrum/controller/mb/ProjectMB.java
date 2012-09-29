package br.com.scrum.controller.mb;

import java.io.Serializable;
import java.util.Date;
import java.util.List;

import javax.faces.bean.ViewScoped;
import javax.inject.Inject;
import javax.inject.Named;

import org.jboss.logging.Logger;

import br.com.scrum.entity.Project;
import br.com.scrum.service.ProjectService;
import br.com.scrum.util.exception.BusinessException;

@Named
@ViewScoped
public class ProjectMB extends BaseBean implements Serializable 
{		
	@Inject private Logger logger;
	@Inject private ProjectService projectService;
	
	private Project project = new Project();
	private List<Project> projects;
	
	public void createOrSave() 
	{
		try {			
			if (project.getId() == null) {
				projectService.create(project);
				project = new Project();
				addInfoMessage("project successfully created");
			} else {
				projectService.save(project);
				addInfoMessage("project successfully updated");
			}
		} catch (BusinessException be) {
			addErrorMessage(null, be.getMessage().toString());	
		} catch ( Exception e ) {
			e.printStackTrace();
			addErrorMessage("unexcepted error has ocurred");
		}
	}

	public void delete() 
	{		
		try {
			logger.infof(project.getName() + " is being deleted ", new Date());
			projectService.delete(project);	
			projects = projectService.findAll();
			addInfoMessage("project removed");
		} catch ( Exception e ) {
			logger.error(e);
			addErrorMessage(e.getMessage());
		}		
	}

	public Project getProject() 
	{
		return project;
	}

	public void setProject(Project project) 
	{
		this.project = project;
	}

	public List<Project> getProjects() 
	{		              
		return projects == null ?  projects = projectService.findAll() : projects;
	}

	public void setProjects(List<Project> projects) 
	{
		this.projects = projects;
	}

	private static final long serialVersionUID = 844308314603679973L;

}
