package br.com.scrum.service;

import java.util.List;

import javax.persistence.NoResultException;

import br.com.scrum.dao.PersistenceUtil;
import br.com.scrum.entity.User;

@SuppressWarnings("serial")
public class UserService extends PersistenceUtil
{
	public void create(User user) throws Exception 
	{
		try {
			super.create(user);			
		}
		catch (Exception e) {
			throw e;
		}
	}
	
	public void save(User user) throws Exception 
	{
		try {
			super.save(user);				
		}
		catch (Exception e) {
			throw e;
		}
	} 
	
	public void delete(User user) throws Exception
	{
		try {
			super.delete(getEntityManager.getReference(User.class, user.getId()));				
		} catch (Exception e) {
			throw e;
		}
	}
	
	public List<User> findAll() 
	{
		return super.findAll(User.class);
	}
	
	public User getUserByCredential(String username, String password)
	{
		try {
			return super.findUniqueByNamedQuery("User.getByLogin", username, password);
		}
		catch (NoResultException e) {
			return null;
		}
	}
	
}
