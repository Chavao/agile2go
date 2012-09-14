package br.com.scrum.entity;

import java.io.Serializable;
import java.util.Date;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import org.hibernate.validator.constraints.NotBlank;

import br.com.scrum.entity.enums.Const;

@Entity
@Table(name = "SPRINT", schema = Const.SCHEMA)
@NamedQueries({
		@NamedQuery(name="Sprint.getLastId", query="from Sprint s where s.id = (select max(s.id) from Sprint s)"),
		@NamedQuery(name="Sprint.getByName", query="from Sprint s where upper(s.name) like ?")
		})
public class Sprint implements Serializable 
{	
	public static final String ID = "id";
	public static final String NAME = "name";

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "SPRINT_ID")
	private Integer id;

	@NotBlank(message = "name is a required field")
	@Column(name = "NAME", nullable = false, length = 60)
	private String name;
	
	@Temporal(TemporalType.DATE)
	@Column(name = "START_DATE", nullable = false)
	private Date startDate;

	@Temporal(TemporalType.DATE)
	@Column(name = "END_DATE", nullable = false)
	private Date endDate;
	
	@NotBlank(message = "daily scrum is a required field")
	@Column(name = "DAILY_SCRUM", nullable = false, length = 5)
	private String dailyScrum;
	
	@NotBlank(message = "goal is a required field")
	@Column(name = "GOAL", nullable = false, length = 60)
	private String goal;

	@ManyToOne
	@JoinColumn(name = "PROJECT_ID", referencedColumnName = "PROJECT_ID")	
	private Project project;	
	
	@OneToMany(mappedBy = "sprint", cascade = CascadeType.ALL)
	private List<Task> tasks;

	public Sprint() 
	{
		project = new Project();
	}		

	public Integer getId() 
	{
		return id;
	}

	public void setId(Integer id) 
	{
		this.id = id;
	}

	public String getName() 
	{
		return name;
	}

	public void setName(String name) 
	{
		this.name = name;
	}

	public Date getStartDate() 
	{
		return startDate;
	}

	public void setStartDate(Date startDate) 
	{
		this.startDate = startDate;
	}

	public Date getEndDate() 
	{
		return endDate;
	}

	public void setEndDate(Date endDate) 
	{
		this.endDate = endDate;
	}			
	
	public String getDailyScrum() 
	{
		return dailyScrum;
	}

	public void setDailyScrum(String dailyScrum) 
	{
		this.dailyScrum = dailyScrum;
	}

	public String getGoal() 
	{
		return goal;
	}

	public void setGoal(String goal) 
	{
		this.goal = goal;
	}

	public Project getProject() 
	{
		return project;
	}

	public void setProject(Project project) 
	{
		this.project = project;
	}				
		
	public List<Task> getTasks() 
	{
		return tasks;
	}

	public void setTasks(List<Task> tasks) 
	{
		this.tasks = tasks;
	}

	@Override
	public int hashCode() 
	{
		final int prime = 31;
		int result = 1;
		result = prime * result + ((id == null) ? 0 : id.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) 
	{
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Sprint other = (Sprint) obj;
		if (id == null) {
			if (other.id != null)
				return false;
		} else if (!id.equals(other.id))
			return false;
		return true;
	}		

	private static final long serialVersionUID = 4897729582058383675L;	

}

