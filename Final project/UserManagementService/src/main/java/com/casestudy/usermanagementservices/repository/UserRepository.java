package com.casestudy.usermanagementservices.repository;

import javax.transaction.Transactional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.casestudy.usermanagementservices.model.User;

@Repository
@Transactional
public interface UserRepository extends JpaRepository<User, Integer>
{
	@Query(value="select u from User u where u.username= :username and u.password= :password")
	public User validateUser(String username, String password);
}
