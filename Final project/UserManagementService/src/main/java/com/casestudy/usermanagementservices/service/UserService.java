package com.casestudy.usermanagementservices.service;

import java.util.List;

import com.casestudy.usermanagementservices.model.User;

public interface UserService 
{
	public List<User> getAllUsers();
	public User addUser(User user);
	public boolean validateUser(String username, String password);

}
