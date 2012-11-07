package br.com.scrum.entity;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Date;
import java.util.Iterator;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.persistence.UniqueConstraint;

import org.hibernate.validator.constraints.NotBlank;

import br.com.scrum.entity.enums.Const;
import br.com.scrum.util.exception.BusinessException;

@SuppressWarnings("serial")
@Entity
@Table(name = "PROJECT", schema = Const.SCHEMA, uniqueConstraints = {
		@UniqueConstraint(columnNames = {"NAME"})
		})
@NamedQueries({
	@NamedQuery(name = "Project.getByName",
				query = "from Project p where upper(p.name) like ?")
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
	
	@NotBlank(message = "company is a required field")
	@Column(name = "COMPANY", nullable = false, length = 60)
	private String company;
	
	@OneToMany(mappedBy = "project", cascade = CascadeType.ALL, orphanRemoval = true)
	private List<Sprint> sprints;
	
	@ManyToMany
	@JoinTable(name = "user_project", schema = Const.SCHEMA,
	joinColumns = {
			@JoinColumn(name = "PROJECT_ID", referencedColumnName = "PROJECT_ID")},
			inverseJoinColumns = {
			@JoinColumn(name = "USER_ID", referencedColumnName = "USER_ID")})
	private List<User> users = new ArrayList<User>();
	
	public Project() 
	{ }

	public Project(String name) 
	{
		this.name = name;
	}
	
	public void addUser(User membership) throws BusinessException
	{
		if (userAlreadyExists(membership)) {
			throw new BusinessException("user already added");
		}
		users.add(membership);
	}

	private boolean userAlreadyExists(User membership)
	{
		if (users != null && !users.isEmpty()) {
			for (User u : users) {
				if (u.getName().equalsIgnoreCase(membership.getName()) && 
					u.getRole().equals(membership.getRole())) {
					return true;
				}
			}
		}
		return false;
	}
	
	public void removeUser(User membership)
	{
		if (users != null && !users.isEmpty()) {
			Iterator<User> it = users.iterator();
			while (it.hasNext()) {
				User member = (User) it.next();
				if (member.getName().equalsIgnoreCase(membership.getName()) &&
					member.getRole().equals(membership.getRole())) {
					it.remove();
					break;
				}
			}
		}
	}

	public Integer getId() 
	{
		return id;
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
		
	public List<User> getUsers() {
		return users;
	}

	public void setUsers(List<User> users) {
		this.users = users;
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

}
