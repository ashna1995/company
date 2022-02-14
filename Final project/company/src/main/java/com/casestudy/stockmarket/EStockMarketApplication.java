package com.casestudy.stockmarket;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.web.servlet.FilterRegistrationBean;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.Bean;
import org.springframework.web.client.RestClientException;

import com.casestudy.stockmarket.controller.ConsumerController;
import com.casestudy.stockmarket.filter.JWTFilter;


@SpringBootApplication
public class EStockMarketApplication {

	public static void main(String[] args) throws RestClientException, Exception {
		ApplicationContext ctx =SpringApplication.run(EStockMarketApplication.class, args);
		ConsumerController consumerController =ctx.getBean(ConsumerController.class);
		consumerController.getAllUsers();
	}


	@Bean
	public ConsumerController consumerController()
	{
		return new ConsumerController();
	}
	@Bean
	public FilterRegistrationBean jwtFilter()
	{
		FilterRegistrationBean fb = new FilterRegistrationBean();
		fb.setFilter(new JWTFilter());
		fb.addUrlPatterns("/api/v1/*");
		return fb;
	}
}
