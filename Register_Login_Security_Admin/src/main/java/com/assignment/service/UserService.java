package com.assignment.service;

import java.util.List;

import com.assignment.entity.User;

public interface UserService {

	public User saveUser(User user);

	public void removeSessionMessage();
	 public User getUserById(int id);
	 public List<User> getAllUsers();
	 public void save(User user);
}
