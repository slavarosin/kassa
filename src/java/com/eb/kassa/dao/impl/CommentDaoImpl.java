package com.eb.kassa.dao.impl;

import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.eb.kassa.beans.Comment;
import com.eb.kassa.dao.CommentDao;

@Repository
public class CommentDaoImpl extends AbstractDaoImpl<Comment> implements
		CommentDao {

	@Autowired
	public CommentDaoImpl(SessionFactory sessionFactory) {
		super(sessionFactory, Comment.class);
	}
}
