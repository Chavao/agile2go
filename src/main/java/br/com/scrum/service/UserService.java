package br.com.scrum.service;

import java.io.Serializable;

import javax.persistence.NoResultException;

import br.com.scrum.dao.PersistenceUtil;
import br.com.scrum.entity.User;

public class UserService extends PersistenceUtil implements Serializable {
	
	public void create(User user) throws Exception 
	{
		try {
			create(user);			
		}
		catch (Exception e) {
			throw e;
		}
	}
	
	public void save(User user) throws Exception 
	{
		try {
			save(user);				
		}
		catch (Exception e) {
			throw e;
		}
	} 
	
	public User getUserByCredential(String username, String password)
	{
		try {
			return findUniqueByNamedQuery("User.getByLogin", username, password);
		}
		catch (NoResultException e) {
			return null;
		}
	}
	
	private static final long serialVersionUID = 4629428604267292464L;

}
