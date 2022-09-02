package com.stackroute.OrderHistoryService;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;

@SpringBootApplication

public class OrderHistoryServiceApplication {

	public static void main(String[] args) {
		SpringApplication.run(OrderHistoryServiceApplication.class, args);
	}

}
