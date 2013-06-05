package com.eb.kassa.service;

import java.math.BigInteger;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.eb.kassa.beans.User;
import com.eb.kassa.dao.UserDao;

@Service
@Transactional(readOnly = true)
public class UserServiceImpl implements UserService {

	private final UserDao userDao;

	@Autowired
	public UserServiceImpl(UserDao userDao) {
		this.userDao = userDao;
	}

	public List<User> getUsers() {
		return userDao.findAll();
	}

	public User getUser(String login, String passwd) {
		return userDao.find(login, passwd);
	}

	@Transactional
	public void remove(Integer id) {
		userDao.remove(id);
	}

	public User getUser(Integer id) {
		return userDao.find(id);
	}

	@Transactional
	public void addUser(User user) {
		userDao.save(user);
	}

	public String encrypt(String password) {
		try {
			MessageDigest md5 = MessageDigest.getInstance("MD5");
			md5.update(password.getBytes());
			BigInteger hash = new BigInteger(1, md5.digest());
			return hash.toString(16);

		} catch (NoSuchAlgorithmException e) {
			return password;
		}
	}
}
