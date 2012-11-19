package br.com.scrum.repository;

import java.io.Serializable;
import java.util.List;

import javax.inject.Inject;
import javax.persistence.EntityManager;
import javax.persistence.NoResultException;
import javax.persistence.PersistenceException;
import javax.persistence.Query;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;

import org.hibernate.Session;
import org.hibernate.exception.ConstraintViolationException;
import org.jboss.logging.Logger;

@SuppressWarnings("serial")
public abstract class PersistenceUtil implements Serializable
{
	@Inject protected EntityManager getEntityManager;
	@Inject protected Logger logger;
	
	protected <T> void create(final T entity)
	{
		getEntityManager.persist(entity);
	}
	
	protected <T> void delete(final T entity) throws NoResultException
	{
		getEntityManager.remove(entity);
	}
	
	protected <T> void save(final T entity) throws PersistenceException, ConstraintViolationException
	{
		if (getEntityManager == null)
		{
			throw new IllegalStateException("Must initialize EntityManager before using Services!");
		}
		getEntityManager.merge(entity);
	}

	protected <T> long count(final Class<T> type)
	{
		CriteriaBuilder cb = getEntityManager.getCriteriaBuilder();
		CriteriaQuery<Long> cq = cb.createQuery(Long.class);
		cq.select(cb.count(cq.from(type)));
		return getEntityManager.createQuery(cq).getSingleResult();
	}
	
	protected <T> List<T> findAll(final Class<T> type)
	{
		CriteriaQuery<T> query = getEntityManager.getCriteriaBuilder().createQuery(type);
		query.from(type);
		return getEntityManager.createQuery(query).getResultList();
	}

	@SuppressWarnings("unchecked")
	protected <T> T findById(final Class<T> type, final Integer id) throws NoResultException
	{
		Class<?> clazz = getObjectClass(type);
		T result = (T) getEntityManager.find(clazz, id);
		if (result == null)
		{
			throw new NoResultException("No object of type: " + type + " with ID: " + id);
		}
		return result;
	}

	@SuppressWarnings("unchecked")
	protected <T> T findUniqueByNamedQuery(final String namedQueryName) throws NoResultException
	{
		return (T) getEntityManager.createNamedQuery(namedQueryName).getSingleResult();
	}

	@SuppressWarnings("unchecked")
	protected <T> T findUniqueByNamedQuery(final String namedQueryName, final Object...params) throws NoResultException
	{
		Query query = getEntityManager.createNamedQuery(namedQueryName);
		int i = 1;
		for (Object p : params)
		{
			query.setParameter(i++, p);
		}
		return (T) query.getSingleResult();
	}
	
	@SuppressWarnings("unchecked")
	protected <T> List<T> findByNamedQuery(final String namedQueryName)
	{
		return getEntityManager.createNamedQuery(namedQueryName).getResultList();
	}

	@SuppressWarnings("unchecked")
	protected <T> List<T> findByNamedQuery(final String namedQueryName, final Object... params)
	{
		Query query = getEntityManager.createNamedQuery(namedQueryName);
		int i = 1;
		for (Object o : params)
		{
			query.setParameter(i++, "%" + o + "%");
		}
		return query.getResultList();
	}
	
	protected void evictCache(String region)
	{
		((Session)getEntityManager.getDelegate())
					              .getSessionFactory()
					              .getCache()
					              .evictQueryRegion(region);
	}
	
	protected Class<?> getObjectClass(final Object type) throws IllegalArgumentException
	{
		Class<?> clazz = null;
		if (type == null) {
			throw new IllegalArgumentException("Null has no type. You must pass an Object");
		} else if (type instanceof Class<?>) {
			clazz = (Class<?>) type;
		} else {
			clazz = type.getClass();
		}
		return clazz;
	}

}
