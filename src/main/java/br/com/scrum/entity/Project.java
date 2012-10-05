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
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.persistence.UniqueConstraint;

import org.hibernate.validator.constraints.NotBlank;

import br.com.scrum.entity.enums.Const;

@Entity
@Table(name = "PROJECT", schema = Const.SCHEMA, uniqueConstraints = {
		@UniqueConstraint(columnNames = {"NAME"})})
@NamedQueries({
	@NamedQuery(name = "Project.getByName", query = "from Project p where upper(p.name) like ?")
	})
public class Project implements Serializable 
{	
	@Id	
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "PROJECT_ID")
	private Integer id;

	@NotBlank(message = "name is a required field")
	@Column(name = "NAME", nullable = false, length = 60)
	private String name;

	@NotBlank(message = "description is a required field")
	@Column(name = "DESCRIPTION", nullable = false)
	private String description;
	
	@Temporal(TemporalType.DATE)
	@Column(name = "LAST_DATE", nullable = false)
	private Date lastDate;

	@Column(name = "COMPANY", nullable = false, length = 60)
	private String company;
	
	@OneToMany(mappedBy = "project", cascade = CascadeType.ALL, orphanRemoval = true)
	private List<Sprint> sprints;
		
	public Project() 
	{ }

	public Project(String name) 
	{
		this.name = name;
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

	public String getDescription() 
	{
		return description;
	}

	public void setDescription(String description) 
	{
		this.description = description;
	}

	public Date getLastDate() 
	{
		return lastDate;
	}

	public void setLastDate(Date lastDate) 
	{
		this.lastDate = lastDate;
	}

	public String getCompany() 
	{
		return company;
	}

	public void setCompany(String company) 
	{
		this.company = company;
	}				
	
	public List<Sprint> getSprints() 
	{
		return sprints;
	}

	public void setSprints(List<Sprint> sprints) 
	{
		this.sprints = sprints;
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
		Project other = (Project) obj;
		if (id == null) {
			if (other.id != null)
				return false;
		} else if (!id.equals(other.id))
			return false;
		return true;
	}

	private static final long serialVersionUID = -2102744528226591109L;

}
