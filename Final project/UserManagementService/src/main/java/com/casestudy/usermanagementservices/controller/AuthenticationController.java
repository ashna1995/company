package com.casestudy.usermanagementservices.controller;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.casestudy.usermanagementservices.model.User;
import com.casestudy.usermanagementservices.service.DataPublisher;
import com.casestudy.usermanagementservices.service.UserServiceImpl;

import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;

@RestController
@RequestMapping("auth/v1")
public class AuthenticationController 
{

	@Autowired
	DataPublisher dp;
    String s;
	private Map<String, String> map = new HashMap();
	
	private UserServiceImpl userServiceImpl;

	@Autowired
	public AuthenticationController(UserServiceImpl userServiceImpl) {
		super();
		this.userServiceImpl = userServiceImpl;
	}
	//-----------------------------Kafka-------------------------
	@GetMapping("/start")
	public String serviceStarted()
	{

		String result = "Authentication Service Started";
		dp.setTemp(result); 
		return result;
	}
	//-----------------------------------------------------

	public String generateToken(String username, String password) throws ServletException, Exception
	{
		String jwtToken ="";
		if(username == null || password == null)
		{
			throw new ServletException("Please send valid username and password");
		}
		//validate the user against DB values
		boolean flag = userServiceImpl.validateUser(username, password);
		if(!flag)
		{
			throw new ServletException("Invalid credentials");
		}
		else
		{
			jwtToken = Jwts.builder().setSubject(username)
					.setIssuedAt(new Date()).setExpiration(new Date(System.currentTimeMillis()+5000000))
					.signWith(SignatureAlgorithm.HS256, "secret key")
					.compact();
		}
        s=jwtToken;
		return jwtToken;
	}
	@GetMapping("/token")
	public String token ()
	{
		return s;
	}
	
	@PostMapping("/login")
	public ResponseEntity<?> doLogin(@RequestBody User user)
	{
		try
		{  
			String jwtToken = generateToken(user.getUsername(), user.getPassword());
			map.put("message", "User successfully logged in!");
			map.put("token", jwtToken);
			//------Kafka------
			String result = user.getUsername();
			dp.setTemp(result); 

		}
		catch(Exception e)
		{
			map.put("message", e.getMessage());
			map.put("token", null);
			return new ResponseEntity<>(map, HttpStatus.UNAUTHORIZED);
		}
		return new ResponseEntity<>(map, HttpStatus.OK);
	}
	
	@PostMapping(value="/addUser", consumes="application/json; charset=utf-8")
	public ResponseEntity<?> addUser(@RequestBody User user)
	{
		if(userServiceImpl.addUser(user)!=null)
		{
			return new ResponseEntity<User>(user, HttpStatus.CREATED);
		}
		return new ResponseEntity<String>("Data not inserted!", HttpStatus.CONFLICT);
	}
	
}




