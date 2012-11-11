package br.com.scrum.controller.mb;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.faces.bean.ViewScoped;
import javax.faces.model.SelectItem;
import javax.inject.Inject;
import javax.inject.Named;

import org.hibernate.exception.ConstraintViolationException;
import org.jboss.logging.Logger;
import org.primefaces.event.SelectEvent;

import br.com.scrum.entity.Project;
import br.com.scrum.entity.Sprint;
import br.com.scrum.entity.Task;
import br.com.scrum.service.ProjectService;
import br.com.scrum.service.SprintService;
import br.com.scrum.service.TaskService;
import br.com.scrum.util.exception.BusinessException;

@SuppressWarnings("serial")
@Named
@ViewScoped
public class SprintMB extends BaseBean
{	
	@Inject private Logger logger;
	@Inject private SprintService sprintService;	
	@Inject private ProjectService projectService;
	@Inject private TaskService taskService;
		
	private Sprint sprint = new Sprint();
	private Task task = new Task();
	
	private List<Sprint> sprints;
	private List<Project> projects;
	private List<SelectItem> taskItems;
	
	private boolean notOk;
	
	@PostConstruct
	public void init()
	{
		findAll();
	}
	
	public void createOrSave()
	{
		try {
			if (validateSprint()) return;
			if ( sprint.getId() == null ) {
				sprintService.create(sprint);
				sprint = new Sprint();
				addInfoMessage("sprint successfully created");				
			} else {
				sprintService.save(sprint);
				addInfoMessage("sprint successfully saved");
			}
			findAll();
		} catch ( ConstraintViolationException cve ) {
			addErrorMessage("sprint already exsists");
			logger.error(cve);
		} catch ( Exception e ) {
			addErrorMessage("a excepted has ocurred!");
			logger.fatal(e);
		}
	}
	
	private boolean validateSprint()
	{
		notOk = false;
		if (sprint.getStartDate().after(new Date())) {
			addErrorMessage("you can not create a sprint with the start date after today");
			notOk = true;
		}
		if (sprint.getEndDate().before(sprint.getStartDate())) {
			addErrorMessage("you can not create a sprint with the end date before the start date");
			notOk = true;
		}
		return notOk;
	}
	
	public void delete()
	{		
		try {
			sprintService.delete(sprint);
			findAll();
			addInfoMessage("sprint removed");
		} catch ( Exception e ) {
			addErrorMessage(e.getMessage());
			logger.error(e);
		}		
	}
	
	private void findAll()
	{
		sprints = sprintService.findAll();
	}
	
	public void addTask()
	{
		try {
			if (task != null && task.getId() != null) {
				sprint.addTask(task);
				task = new Task();
				addInfoMessage("task successfully added");
			}
		} catch (BusinessException be) {
			addErrorMessage(be.getMessage());
			logger.warn(be);
		} catch (Exception e) {
			addErrorMessage(e.getMessage());
			logger.error(e);
		}
	}
	
	public void removeTask()
	{
		sprint.removeTask(task);
		task = new Task();
		addInfoMessage("task successfully removed");
	}
	
	public String redirectToEdit()
	{
		return "/pages/sprint/add_sprint?id=" + sprint.getId() + "faces-redirect=true";
	}
	
	public void loadSprint()
	{
		if (sprint != null && sprint.getId() != null) {
			sprint = sprintService.findById(sprint.getId());
		}
	}
	
	public void loadTask()
	{
		if (sprint != null && sprint.getId() != null) {
			sprint = sprintService.findById(sprint.getId());
		}
	}

	public List<Project> completeProject(String query)
	{
		try {
			if ( projects == null ) {
				projects = new ArrayList<Project>();
			}
			return projectService.searchBy(query.trim().toUpperCase());			
		} catch ( Exception e ) {
			addErrorMessage(e.getMessage());
			logger.error(e);
		}
		return projects = new ArrayList<Project>();
	}
	
	public void selectProject(SelectEvent e)
	{
		sprint.setProject((Project)e.getObject());
	}
	
	public List<SelectItem> getTaskItems()
	{
		if (taskItems == null) {
			taskItems = new ArrayList<SelectItem>();
			taskItems.add(new SelectItem(null, ""));
			for (Task t : taskService.findAll()) {
				taskItems.add(new SelectItem(t, "#" + t.getId().toString() + " - " + t.getStorie()));
			}
		}
		return taskItems;
	}
	
	public List<Sprint> getSprints()
	{
		return sprints;		 
	}

	public Sprint getSprint()
	{
		return sprint;
	}

	public void setSprint(Sprint sprint)
	{
		this.sprint = sprint;
	}

	public void setSprints(List<Sprint> sprints)
	{
		this.sprints = sprints;
	}

	public Task getTask()
	{
		return task;
	}

	public void setTask(Task task)
	{
		this.task = task;
	}

	public List<Project> getProjects() 
	{
		return projects;
	}

	public void setProjects(List<Project> projects)
	{
		this.projects = projects;
	}

	public boolean isNotOk()
	{
		return notOk;
	}

	public void setNotOk(boolean notOk)
	{
		this.notOk = notOk;
	}

}
