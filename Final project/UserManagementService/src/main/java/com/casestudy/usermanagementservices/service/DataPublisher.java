package com.casestudy.usermanagementservices.service;



import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Service;

@Service
public class DataPublisher
{
	public static final String topic = "javamc";
	
	@Autowired
	private KafkaTemplate<String, String> temp;

	public KafkaTemplate<String, String> getTemp() {
		return temp;
	}

	public void setTemp(String message)
	{
		System.out.println("Publishing to topic: "+topic);
		this.temp.send(topic,message);
	}
	
	public static String getTopic() {
		return topic;
	}
	
	
	

}
