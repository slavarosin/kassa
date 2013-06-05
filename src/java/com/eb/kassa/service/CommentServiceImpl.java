package com.eb.kassa.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.eb.kassa.beans.Comment;
import com.eb.kassa.dao.CommentDao;

@Service
@Transactional(readOnly = true)
public class CommentServiceImpl implements CommentService {

	private final CommentDao commentDao;

	@Autowired
	public CommentServiceImpl(CommentDao commentDao) {
		this.commentDao = commentDao;
	}

	public List<Comment> getComments() {
		return commentDao.findAll();
	}

	@Transactional
	public void remove(Integer id) {
		commentDao.remove(id);
	}

	public Comment getComment(Integer id) {
		return commentDao.find(id);
	}

	@Transactional
	public void addComment(Comment comment) {
		commentDao.save(comment);
	}
}
