package br.com.scrum.domain.entity;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;
import javax.persistence.Transient;

import org.hibernate.validator.constraints.NotBlank;
import org.hibernate.validator.constraints.NotEmpty;

import br.com.scrum.domain.entity.enums.Const;
import br.com.scrum.domain.entity.enums.Status;

@Entity
@Table(name = "TASK", schema = Const.SCHEMA)
@NamedQueries({
		@NamedQuery(name="Task.getLastId", query = "from Task t where t.id = (select MAX( t.id ) from Task t)")
		})
public class Task implements Serializable 
{	
	public static final String STATUS = "status";

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "TASK_ID")
	private Integer id;

	@NotEmpty(message = "story is a required field") 
	@Column(name = "STORIE", nullable = false)
	private String storie;
		
	@Column(name = "PRIORITY", nullable = false, length = 6)
	private Integer priority;
	
	@NotBlank(message = "hours of the task is a required field")
	@Column(name = "HOURS", nullable = false, length = 5)
	private String hours;
	
	@Transient
	private Status status;
	
	@Column(name = "STATUS", length = 15)
	private String status_;
		
	@ManyToOne
	@JoinColumn(name = "SPRINT_ID", referencedColumnName = "SPRINT_ID")
	private Sprint sprint;		

	public Task() 
	{
		sprint = new Sprint();		
	}
	
	public Integer getId() 
	{
		return id;
	}

	public void setId(Integer id) 
	{
		this.id = id;
	}		

	public String getStorie() 
	{
		return storie;
	}

	public void setStorie(String storie) 
	{
		this.storie = storie;
	}

	public Integer getPriority() 
	{
		return priority;
	}

	public void setPriority(Integer priority) 
	{
		this.priority = priority;
	}		

	public String getHours() 
	{
		return hours;
	}

	public void setHours(String hours) 
	{
		this.hours = hours;
	}		

	public Status getStatus() 
	{
		for (Status s : Status.values()) 
			if (s.getCode().equals(status_))
				return status = s;
		return status;
	}

	public void setStatus(Status status) 
	{
		this.status_ = status.getCode();
		this.status = status;
	}
	
	public Sprint getSprint() 
	{
		return sprint;
	}

	public void setSprint(Sprint sprint) 
	{
		this.sprint = sprint;
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
		Task other = (Task) obj;
		if (id == null) {
			if (other.id != null)
				return false;
		} else if (!id.equals(other.id))
			return false;
		return true;
	}

	private static final long serialVersionUID = 3651157203865611931L;

}
