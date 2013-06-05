package com.eb.kassa.dao;

import java.io.Serializable;
import java.util.List;

public interface AbstractDao<T> {

	List<T> findAll();

	void remove(Serializable id);

	T find(Serializable id);

	void save(T object);

	T findLast();

}
