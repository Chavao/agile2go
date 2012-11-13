package br.com.scrum.entity;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;
import javax.persistence.Transient;
import javax.persistence.UniqueConstraint;

import org.hibernate.validator.constraints.NotBlank;

import br.com.scrum.entity.enums.Const;
import br.com.scrum.entity.enums.UserRole;

@SuppressWarnings("serial")
@Entity
@Table(name = "USER", schema = Const.SCHEMA, uniqueConstraints = {
		@UniqueConstraint(columnNames = {"LOGIN", "NAME"})
		})
@NamedQueries({
	@NamedQuery(name = "User.getByLogin",
				query = "from User u where u.login = ? and u.password = ? ")
})
public class User implements Serializable
{	
	public static final String LOGIN = "login";
	public static final String PASSWORD = "password";
	
	@Id	
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "USER_ID")
	private Integer id;

	@NotBlank(message = "name is required field")
	@Column(name = "NAME", nullable = false, length = 60)
	private String name;

	@NotBlank(message = "login do not match")
	@Column(name = "LOGIN", nullable = false, length = 20)
	private String login;

	@NotBlank(message = "password do not match")
	@Column(name = "PASSWORD", nullable = false, length = 20)
	private String password;

	@Transient
	private UserRole role;
	
	@NotBlank(message = "membership role is required field")
	@Column(name = "ROLE", length = 10)
	private String role_;
	
	public User() 
	{}

	public User(String nome, String login, String password, UserRole role)
	{
		this.name = nome;
		this.login = login;
		this.password = password;
		this.role = role;
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

	public String getLogin() 
	{
		return login;
	}

	public void setLogin(String login) 
	{
		this.login = login;
	}

	public String getPassword() 
	{
		return password;
	}

	public void setPassword(String password) 
	{
		this.password = password;
	}

	public String getRole_() 
	{
		return role_;
	}

	public void setRole_(String role_) 
	{
		this.role_ = role_;
	}
	
	public UserRole getRole() 
	{
		for (UserRole u : UserRole.values()) 
			if (u.getCode().equals(role_))
				return role = u;
		return role;
	}

	public void setRole(UserRole role) 
	{
		this.role_ = role.getCode();
		this.role = role;
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
		User other = (User) obj;
		if (id == null) {
			if (other.id != null)
				return false;
		} else if (!id.equals(other.id))
			return false;
		return true;
	}

	@Override
	public String toString()
	{
		return id + "-" + name;
	}
	
}
