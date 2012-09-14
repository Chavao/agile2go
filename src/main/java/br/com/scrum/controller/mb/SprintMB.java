package br.com.scrum.controller.mb;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import javax.faces.bean.ViewScoped;
import javax.inject.Inject;
import javax.inject.Named;

import org.hibernate.exception.ConstraintViolationException;

import br.com.scrum.entity.Project;
import br.com.scrum.entity.Sprint;
import br.com.scrum.service.ProjectService;
import br.com.scrum.service.SprintService;

@Named
@ViewScoped
public class SprintMB extends BaseBean implements Serializable {
			
	@Inject private SprintService sprintService;	
	@Inject private ProjectService projectService;
		
	private Sprint sprint = new Sprint();		
	private List<Sprint> sprints;
	private List<Project> projects;		
	
	public void createOrSave() {
		try {
			if ( sprint.getId() == null ) {					
				sprintService.create(sprint);
				sprint = new Sprint();
				addInfoMessage("sprint successfully created");				
			} else {
				sprintService.save(sprint);
				addInfoMessage("sprint successfully saved");
			}
		} catch ( ConstraintViolationException cve ) {
			addErrorMessage("sprint already exsists");
		} catch ( Exception e ) {
			addErrorMessage("a excepted has ocurred!");
		}
	}
	
	public void delete() {		
		try {
			sprintService.delete(sprint);
			sprints = sprintService.findAll();
			addInfoMessage("sprint removed");
		} catch ( Exception e ) {
			addErrorMessage(e.getMessage());
		}		
	}

	public List<Project> completeProject(String query) {
		try {
			if ( projects == null ) {
				projects = new ArrayList<Project>();
			}
			return projectService.searchBy(query);			
		} catch ( Exception e ) {
			addErrorMessage(e.getMessage());
		}
		return projects = new ArrayList<Project>();
	}
	
	public List<Sprint> getSprints() {
		return sprints == null ? sprints = sprintService.findAll() : sprints;		 
	}

	public Sprint getSprint() {
		return sprint;
	}

	public void setSprint(Sprint sprint) {
		this.sprint = sprint;
	}

	public void setSprints(List<Sprint> sprints) {
		this.sprints = sprints;
	}	
	
	private static final long serialVersionUID = 6429842878070655145L;

}
