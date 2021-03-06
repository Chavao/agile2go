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
	private String retorno;
	
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
				addInfoMsg("sprint.successfully_created");				
			} else {
				sprintService.save(sprint);
				addInfoMsg("sprint.successfully_updated");
			}
			findAll();
		} catch (Exception e) {
			if (e.getCause() instanceof ConstraintViolationException) {
				addErrorMsg("sprint.already_exists", sprint.getName());
				logger.warn(e);
			}
		}
	}
	
	private boolean validateSprint()
	{
		notOk = false;
		if (sprint.getStartDate().after(new Date())) {
			addErrorMsg("sprint.start_date_is_after_today");
			notOk = true;
		}
		if (sprint.getEndDate().before(sprint.getStartDate())) {
			addErrorMsg("sprint.end_date_is_before_today");
			notOk = true;
		}
		return notOk;
	}
	
	public void delete()
	{		
		try {
			sprintService.delete(sprint);
			findAll();
			addInfoMsg("sprint.successfully_deleted");
		} catch (Exception e) {
			addErrorMsgFromException(e);
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
				addInfoMsg("task.successfully_added");
			}
		} catch (BusinessException be) {
			addErrorMsgFromException(be);
			logger.warn(be);
		} catch (Exception e) {
			addErrorMsgFromException(e);
			logger.error(e);
		}
	}
	
	public void removeTask()
	{
		sprint.removeTask(task);
		task = new Task();
		addInfoMsg("task.successfully_removed");
	}
	
	public String redirectToEdit()
	{
		return "/pages/sprint/add_sprint?id=" + sprint.getId() + "&amp;retorno=list_sprint" + "faces-redirect=true";
	}
	
	public String goBack()
	{
		if (retorno.equalsIgnoreCase("list_sprint"))
			return "/pages/sprint/list_sprint?" + "faces-redirect=true";
		return "";
	}
	
	public void loadSprint()
	{
		if (sprint != null && sprint.getId() != null) 
			sprint = sprintService.findById(sprint.getId());
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
			if (projects == null) {
				projects = new ArrayList<Project>();
			}
			return projectService.searchBy(query);			
		} catch ( Exception e ) {
			addErrorMsgFromException(e);
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
			if (sprint != null && sprint.getId() != null) {
				for (Task t : taskService.searchBy(sprint)) {
					taskItems.add(new SelectItem(t, "#" + t.getId().toString() + " - " + t.getStorie()));
				}
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

	public String getRetorno()
	{
		return retorno;
	}

	public void setRetorno(String retorno)
	{
		this.retorno = retorno;
	}
	
}
