package br.com.scrum.service;

import java.util.List;

import javax.persistence.NoResultException;
import javax.persistence.TypedQuery;

import br.com.scrum.dao.PersistenceUtil;
import br.com.scrum.entity.User;
import br.com.scrum.util.exception.ObjectAlreadyExistsException;

@SuppressWarnings("serial")
public class UserService extends PersistenceUtil
{
	public void create(User user) throws Exception 
	{
		try {
			if (loginAlreadyExists(user))
				throw new ObjectAlreadyExistsException(user.getName() + " with login [" + user.getLogin() + "] already exists");
			super.create(user);			
		} catch (Exception e) {
			throw e;
		}
	}
	
	public void save(User user) throws Exception 
	{
		try {
			if (loginAlreadyExists(user))
				throw new ObjectAlreadyExistsException(user.getName() + " with login [" + user.getLogin() + "] already exists");
			super.save(user);				
		} catch (Exception e) {
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
	
	private boolean loginAlreadyExists(User user)
	{
		TypedQuery<User> query = getEntityManager.createQuery("from User u where u.login = :login", User.class);
		query.setParameter(User.LOGIN, user.getLogin());
		try {
			return query.getSingleResult() != null;
		}
		catch (NoResultException e) {
			return false;
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
		} catch (NoResultException e) {
			logger.error("error fetching the [" + username + "] " + e);
			return null;
		}
	}
	
}
