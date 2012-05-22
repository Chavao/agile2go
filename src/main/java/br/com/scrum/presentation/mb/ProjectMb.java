package br.com.scrum.presentation.mb;

import java.io.Serializable;
import java.util.List;

import javax.enterprise.context.RequestScoped;
import javax.inject.Inject;
import javax.inject.Named;

import org.hibernate.exception.ConstraintViolationException;

import br.com.scrum.domain.entity.Project;
import br.com.scrum.domain.service.ProjectService;

@Named
@RequestScoped
public class ProjectMb extends BaseBean implements Serializable {		

	@Inject ProjectService projectService;
	private Project project = new Project();
	private List<Project> projects;	

	public void saveOrUpdate () {
		try {			
			if ( project.getId() == null ) {
				projectService.save(project);
				project = new Project();
				addInfoMessage("project successfully created");
			} else {
				projectService.update(project);
				project = new Project();
				addInfoMessage("project successfully updated");
			}
		} catch ( ConstraintViolationException cve ) {
			cve.getCause().getLocalizedMessage();		
			addErrorMessage("project already exist");			
		} catch ( Exception e ) {
			addErrorMessage("unexcepted error has ocurred");
			e.getCause().getLocalizedMessage();
		}
	}
	
	public void cancelProject () {		
		project = new Project();		
	}

	public Project getProject() {
		return project;
	}

	public void setProject(Project project) {
		this.project = project;
	}

	public List<Project> getProjects() {
		return projects == null ? projectService.findAll() : projects;
	}

	public void setProjects(List<Project> projects) {
		this.projects = projects;
	}

	private static final long serialVersionUID = 844308314603679973L;

}
