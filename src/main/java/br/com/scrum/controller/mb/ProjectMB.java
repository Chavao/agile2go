package br.com.scrum.controller.mb;

import java.util.ArrayList;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.faces.bean.ViewScoped;
import javax.faces.model.SelectItem;
import javax.inject.Inject;
import javax.inject.Named;

import org.jboss.logging.Logger;

import br.com.scrum.entity.Project;
import br.com.scrum.entity.User;
import br.com.scrum.service.ProjectService;
import br.com.scrum.service.UserService;
import br.com.scrum.util.exception.BusinessException;

@SuppressWarnings("serial")
@Named
@ViewScoped
public class ProjectMB extends BaseBean 
{		
	@Inject private Logger logger;
	@Inject private ProjectService projectService;
	@Inject private UserService userService;
	
	private Project project = new Project();
	private User membership = new User();
	private String retorno;
	
	private List<Project> projects;
	private List<SelectItem> membershipsItems;
	
	@PostConstruct
	public void init()
	{
		findAll();
	}
	
	public void createOrSave() 
	{
		try {
			if (project.getId() == null) {
				projectService.create(project);
				project = new Project();
				addInfoMsg("project.successfully_created");
			} else {
				projectService.save(project);
				addInfoMsg("project.successfully_updated");
			}
			findAll();
		} catch (Exception e) {
			addErrorMsgFromException(e);
		}
	}

	public void delete() 
	{		
		try {
			projectService.delete(project);	
			findAll();
			addInfoMsg("project.successfully_removed");
		} catch (Exception e) {
			addErrorMsgFromException(e);
			logger.error(e);
		}		
	}
	
	public void addMember()
	{
		try {
			if (membership != null && membership.getId() != null) {
				project.addUser(membership);
				membership = new User();
				addInfoMsg("membership.successfully_added");
			}
		} catch (BusinessException be) {
			addErrorMsgFromException(be);
			logger.warn(be);
		} catch (Exception e) {
			addErrorMsgFromException(e);
			logger.error(e);
		}
	}
	
	public void removeMember()
	{
		project.removeUser(membership);
		membership = new User();
		addInfoMsg("membership.successfully_removed");
	}
	
	public String redirectToEdit()
	{
		return "/pages/project/add_project?id=" + project.getId() + "&amp;retorno=list_project" + "faces-redirect=true";
	}
	
	public String goBack()
	{
		if (retorno.equalsIgnoreCase("list_project"))
			return "/pages/project/list_project?" + "faces-redirect=true";
		return "";
	}
	
	public List<SelectItem> getMembershipsItems()
	{
		if (membershipsItems == null) {
			membershipsItems = new ArrayList<SelectItem>();
			membershipsItems.add(new SelectItem(null, ""));
			for (User u : userService.findAll()) {
				membershipsItems.add(new SelectItem(u, u.getName() + " - " + u.getRole()));
			}
		}
		return membershipsItems;
	}
	
	public void loadProject()
	{
		if (project != null && project.getId() != null) {
			project = projectService.findById(project.getId());
		}
	}

	private void findAll()
	{
		projects = projectService.findAll();
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
		return projects;
	}

	public void setProjects(List<Project> projects) 
	{
		this.projects = projects;
	}

	public User getMembership()
	{
		return membership;
	}

	public void setMembership(User membership)
	{
		this.membership = membership;
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
