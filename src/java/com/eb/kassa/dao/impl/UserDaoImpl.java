package com.eb.kassa.dao.impl;

import org.hibernate.SessionFactory;
import org.hibernate.criterion.Restrictions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.eb.kassa.beans.User;
import com.eb.kassa.dao.UserDao;

@Repository
public class UserDaoImpl extends AbstractDaoImpl<User> implements UserDao {

	@Autowired
	public UserDaoImpl(SessionFactory sessionFactory) {
		super(sessionFactory, User.class);
	}

	public User find(final String username, final String passwd) {
		return (User) createCriteria().add(
				Restrictions.and(Restrictions.eq("login", username),
						Restrictions.eq("passwd", passwd))).uniqueResult();
	}
}
