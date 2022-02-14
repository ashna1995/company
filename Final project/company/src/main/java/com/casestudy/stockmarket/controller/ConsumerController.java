package com.casestudy.stockmarket.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.client.ServiceInstance;


import org.springframework.cloud.client.discovery.DiscoveryClient;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestClientException;
import org.springframework.web.client.RestTemplate;

import com.casestudy.stockmarket.model.StockMarket;
import com.casestudy.stockmarket.service.DataListener;


@RestController
public class ConsumerController 
{
    @Autowired
    private DiscoveryClient discoveryClient;
   String token;
	
	//------------------------------------------------
   @GetMapping("/login/{username}/{password}")//http://localhost:8082/login/Sona/Basil
   @ResponseBody
	 public String login(@PathVariable String username,@PathVariable String password) throws RestClientException, Exception
   //@GetMapping("/code")
  // public String login() throws RestClientException, Exception 
   {
    	  
	    	List<ServiceInstance> instances = discoveryClient.getInstances("user-producer");
	    	
	    	ServiceInstance serviceInstance = instances.get(0);
	    	
	    	String baseUrl = serviceInstance.getUri().toString();
	    	
	    	RestTemplate restTemplate = new RestTemplate();
	    	
	    	baseUrl= baseUrl+"/auth/v1/login";// http://localhost:8082/api/v1/getAllBooks
	    	
	    	ResponseEntity<String> response =null;
	    	
	    	try
	    	{         
	    		HttpHeaders headers = new HttpHeaders();
				headers.set("Accept", MediaType.APPLICATION_JSON_VALUE);
				Map<String,String> request=new HashMap<>();
		    	   //request.put("id", "102");
		           request.put("username",username);
		           request.put("password",password);
		           HttpEntity<Map<String,String>> entity=new HttpEntity<>(request,headers);
	    		response = restTemplate.postForEntity(baseUrl,entity,String.class);
	    		
	    	}
	    	catch(Exception e)
	    	{
	    		e.printStackTrace();
	    	}
	    	
	    	System.out.println(response.getBody());
	    	System.out.println(response.getHeaders()+"::"+ response.getStatusCode());
	    	String m=response.getBody().toString();
	    	token=m.substring(51,m.length()-2);
	    	System.out.println(token);
	    	return response.getBody().toString();
	    }
	 
    //-----------------------------------------
    
    
	 //-------------------------------
   @GetMapping("/adduser/{username}/{password}")//http://localhost:8082/adduser/Sona/Basil
   @ResponseBody
	 public String adduser(@PathVariable String username,@PathVariable String password) throws RestClientException, Exception
	 
	    {
	    
	    	List<ServiceInstance> instances = discoveryClient.getInstances("user-producer");
	    	
	    	ServiceInstance serviceInstance = instances.get(0);
	    	
	    	String baseUrl = serviceInstance.getUri().toString();
	    	
	    	RestTemplate restTemplate = new RestTemplate();
	    	
	    	baseUrl= baseUrl+"/auth/v1/addUser";
	    	
	    	ResponseEntity<String> response =null;
	    	
	    	try
	    	{     
	    	       
	    		    		HttpHeaders headers = new HttpHeaders();
	    					headers.set("Accept", MediaType.APPLICATION_JSON_VALUE);
	    					Map<String,String> request=new HashMap<>();
	    			    	
	    			           request.put("username",username);
	    			           request.put("password",password);
	    			           HttpEntity<Map<String,String>> entity=new HttpEntity<>(request,headers);
	    		    		response = restTemplate.postForEntity(baseUrl,entity,String.class);
	    	
	    		
	    	}
	    	catch(Exception e)
	    	{
	    		e.printStackTrace();
	    	}
	    	
	    	System.out.println(response.getBody());
	    	System.out.println(response.getHeaders()+"::"+ response.getStatusCode());
	    	
	    	return response.getBody().toString();
	    }
	 
	 
	 
	 //----------------------------------------

    
        @GetMapping("/getAllUsers")//http://localhost:8082/getAllUsers
	    public String getAllUsers() throws RestClientException, Exception
	    {
	    
	    	List<ServiceInstance> instances = discoveryClient.getInstances("user-producer");
	    	
	    	ServiceInstance serviceInstance = instances.get(0);
	    	
	    	String baseUrl = serviceInstance.getUri().toString();
	    	
	    	RestTemplate restTemplate = new RestTemplate();
	    	
	    	baseUrl= baseUrl+"/api/v1/getAllUsers";// http://localhost:8082/api/v1/getAllBooks
	    	
	    	ResponseEntity<String> response =null;
	    	
	    	String s1="Bearer " + token;
	    	HttpHeaders headers=getHeaders1();
	    	headers.set("Authorization",s1);
	    	HttpEntity<String> jwtEntity=new HttpEntity<String>(headers);
	    	try
	    	{                              
	    		response = restTemplate.exchange(baseUrl,HttpMethod.GET, jwtEntity,String.class);
	    		
	    	}
	    	catch(Exception e)
	    	{
	    		e.printStackTrace();
	    	}
	    	
	    	System.out.println(response.getBody());
	    	System.out.println(response.getHeaders()+"::"+ response.getStatusCode());
	    	
	    	return response.getBody().toString();
	    }

	/*	private static HttpEntity<?> getHeaders() throws Exception
		{
			HttpHeaders headers = new HttpHeaders();
			headers.set("Accept", MediaType.APPLICATION_JSON_VALUE);
			return new HttpEntity<>(headers);
		}
		*/
		private static HttpHeaders getHeaders1() throws Exception
		{
			HttpHeaders headers = new HttpHeaders();
			headers.set("Content-Type", MediaType.APPLICATION_JSON_VALUE);
			headers.set("Accept", MediaType.APPLICATION_JSON_VALUE);
			return headers;
		}
		
			   
		
		      
		
	
		      
}
