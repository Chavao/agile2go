package br.com.scrum.service;

import java.io.Serializable;

import javax.enterprise.event.Event;
import javax.inject.Inject;
import javax.persistence.NoResultException;
import javax.persistence.PersistenceException;

import org.jboss.solder.exception.control.ExceptionToCatch;

import br.com.scrum.dao.PersistenceUtil;
import br.com.scrum.entity.User;

public class UserService extends PersistenceUtil implements Serializable {
	
	@Inject private Event<ExceptionToCatch> exception;
	
	public void create(User user) 
	{
		try
		{
			super.create(user);			
		}
		catch (PersistenceException pe)
		{
			exception.fire(new ExceptionToCatch(pe));
		}
	}
	
	public void save(User user) 
	{
		try
		{
			super.save(user);				
		}
		catch (PersistenceException pe)
		{
			exception.fire(new ExceptionToCatch(pe));	
		}
	} 
	
	public User getUserByCredential(String username, String password) {
		try
		{
			return super.findUniqueByNamedQuery("User.getByLogin", username, password);
		}
		catch (NoResultException e)
		{
			exception.fire(new ExceptionToCatch(e));
			return null;
		}
	}
	
	private static final long serialVersionUID = 4629428604267292464L;

}
