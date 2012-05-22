package br.com.scrum.domain.entity;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Embedded;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;
import javax.persistence.UniqueConstraint;
import javax.validation.constraints.Size;

import org.hibernate.validator.constraints.NotEmpty;

import br.com.scrum.domain.entity.enums.Const;
import br.com.scrum.domain.entity.enums.UserRole;

@Entity
@Table(name = "USER", schema = Const.SCHEMA, uniqueConstraints = {
		@UniqueConstraint(columnNames = "NAME")})
@NamedQueries({
	@NamedQuery(name = "User.getByLogin", query = "SELECT u FROM User u WHERE u.login = :login and u.password = :password")})
public class User implements Serializable {	
	
	public static final String LOGIN = "login";
	public static final String PASSWORD = "password";

	@Id	
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "USER_ID")
	private int id;
	
	@NotEmpty(message = "name is required field")
	@Size(min = 3, max = 25)	
	@Column(name = "NAME", nullable = false, length = 100)
	private String name;
	
	@NotEmpty(message = "login do not match")
	@Column(name = "LOGIN", nullable = false, unique = true, length = 20)
	private String login;
	
	@NotEmpty(message = "password do not match")
	@Column(name = "PASSWORD", nullable = false, length = 15)
	private String password;

	@Embedded
	private Address address;

	@Enumerated(EnumType.STRING)
	@Column(length = 10)
	private UserRole role;	

	public User() { }
	
	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getLogin() {
		return login;
	}

	public void setLogin(String login) {
		this.login = login;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public Address getAddress() {
		return address;
	}

	public void setAddress(Address address) {
		this.address = address;
	}

	public UserRole getRole() {
		return role;
	}

	public void setRole(UserRole role) {
		this.role = role;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + id;
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (!(obj instanceof User))
			return false;
		User other = (User) obj;
		if (id != other.id)
			return false;
		return true;
	}	
	
	private static final long serialVersionUID = -2854296962122780992L;

}
