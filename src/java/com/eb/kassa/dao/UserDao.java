package com.eb.kassa.dao;

import com.eb.kassa.beans.User;

public interface UserDao extends AbstractDao<User> {

	public User find(String login, String passwd);

}
