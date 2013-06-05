package com.eb.kassa.dao.impl;

import java.io.Serializable;
import java.util.List;

import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.criterion.Projections;
import org.hibernate.criterion.Restrictions;
import org.springframework.util.Assert;

import com.eb.kassa.dao.AbstractDao;

public abstract class AbstractDaoImpl<T> implements AbstractDao<T> {

	protected final SessionFactory sessionFactory;

	private final Class<T> targetClass;

	public AbstractDaoImpl(final SessionFactory sessionFactory,
			final Class<T> targetClass) {
		Assert.notNull(sessionFactory, "sessionFactory must not be null");
		Assert.notNull(targetClass, "targetClass must not be null");

		this.sessionFactory = sessionFactory;
		this.targetClass = targetClass;
	}

	@SuppressWarnings("unchecked")
	public List<T> findAll() {
		return createCriteria().list();
	}

	@SuppressWarnings("unchecked")
	public T find(final Serializable id) {
		return (T) createCriteria().add(Restrictions.eq("id", id))
				.uniqueResult();
	}

	protected final Session getSession() {
		return sessionFactory.getCurrentSession();
	}

	protected final Criteria createCriteria() {
		return getSession().createCriteria(targetClass);
	}

	public void remove(final Serializable id) {
		final Session s = getSession();
		final T entity = find(id);
		s.delete(entity);
	}

	public void save(final T entity) {
		final Session session = getSession();
		session.saveOrUpdate(entity);
		session.flush();
	}

	public T findLast() {
		Integer id = (Integer) createCriteria().setProjection(
				Projections.max("id")).uniqueResult();

		return find(id);
	}

	public void removeAll() {
		final Session s = getSession();
		List<T> list = findAll();
		for (T t : list) {
			s.delete(t);
		}
	}
}
