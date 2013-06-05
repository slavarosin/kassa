package com.eb.kassa.service;

import java.util.List;

import com.eb.kassa.beans.Comment;

public interface CommentService {

	List<Comment> getComments();

	void remove(Integer id);

	Comment getComment(Integer id);

	void addComment(Comment comment);

}