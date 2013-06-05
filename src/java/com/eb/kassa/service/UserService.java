package com.eb.kassa.service;

import java.util.List;

import com.eb.kassa.beans.User;

public interface UserService {

	List<User> getUsers();

	User getUser(String login, String passwd);

	void remove(Integer userId);

	User getUser(Integer id);

	void addUser(User user);

	String encrypt(String passwd);

}