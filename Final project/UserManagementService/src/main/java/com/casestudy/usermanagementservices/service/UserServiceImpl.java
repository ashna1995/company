package com.casestudy.usermanagementservices.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.casestudy.usermanagementservices.model.User;
import com.casestudy.usermanagementservices.repository.UserRepository;

@Service
public class UserServiceImpl implements UserService
{
	@Autowired
	private UserRepository userRepo;

	@Override
	public List<User> getAllUsers() {
		List<User> userList = userRepo.findAll();
		if(userList!=null && userList.size()>0)
		{
			return userList;
		}
		else
			return null;
	}

	@Override
	public User addUser(User user) {
		if(user!=null)
		{
			return userRepo.saveAndFlush(user);
			
		}
		else
			return null;
	}

	@Override
	public boolean validateUser(String username, String password) {
		User user = userRepo.validateUser(username, password);
		System.out.println("User "+user);
		if(user!=null)
		{
			return true;
		}
		else
			return false;
	
	}
	

}
