package com.casestudy.stockmarket.service;


import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Service;

@Service
public class DataListener {
	@KafkaListener(topics="javamc", groupId="mygroup")
	public void consumeFromTopic(String message)
	{System.out.println("Consumer messages: "+ message);
		
	}
	
	
}
