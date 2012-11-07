package br.com.scrum.controller.mb;

import java.util.ArrayList;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.faces.bean.ViewScoped;
import javax.faces.model.SelectItem;
import javax.inject.Inject;
import javax.inject.Named;

import org.hibernate.exception.ConstraintViolationException;

import br.com.scrum.entity.Sprint;
import br.com.scrum.entity.Task;
import br.com.scrum.entity.enums.Status;
import br.com.scrum.service.SprintService;
import br.com.scrum.service.TaskService;

@SuppressWarnings("serial")
@Named
@ViewScoped
public class TaskMB extends BaseBean
{
	@Inject private TaskService taskService;
	@Inject private SprintService sprintService;

	private Task task = new Task();	
	private List<Task> tasks;
	private List<Sprint> sprints;
	private List<SelectItem> taskItems;
	
	@PostConstruct
	public void init()
	{
		findAll();
	}

	public void createOrSave() 
	{
		try {
			if ( task.getId() == null ) {
				task.setStatus(Status.INPROGRESS);
				taskService.save(task);
				task = new Task();
				addInfoMessage("task successfully created");
			} else {
				taskService.save(task);
				addInfoMessage("task successfully updated");
			}
			findAll();
		} catch ( ConstraintViolationException pe ) {
			addInfoMessage("task already exists");			
		} catch ( Exception e ) {
			addErrorMessage("unexcepted error has ocurred");
		}			
	}

	public void delete() 
	{		
		try {
			taskService.delete(task);
			findAll();
			addInfoMessage("task romoved");
		} catch ( Exception e ) {
			addErrorMessage(e.getMessage());
		}		
	}

	public List<Sprint> completeSprint(String query) 
	{
		try {
			if ( sprints == null ) 
			{
				sprints = new ArrayList<Sprint>();
			}
			return sprintService.searchBy(query);			
		} catch ( Exception e ) {
			addErrorMessage(e.getMessage());
		}
		return sprints = new ArrayList<Sprint>();
	}
	
	public List<SelectItem> getTaskItems() {
		if (taskItems == null) 
		{
			taskItems = new ArrayList<SelectItem>();
			taskItems.add(new SelectItem(null, ""));
			for (Status s : Status.values()) 
			{
				taskItems.add(new SelectItem(s, s.getDescription()));
			}
		}
		return taskItems;
	}
	
	private void findAll()
	{
		tasks = taskService.findAll();
	}
	
	public Task getTask() 
	{
		return task;
	}

	public void setTask(Task task) 
	{
		this.task = task;
	}

	public List<Task> getTasks() 
	{
		return tasks;
	}

	public void setTasks(List<Task> tasks) 
	{
		this.tasks = tasks;
	}

}
