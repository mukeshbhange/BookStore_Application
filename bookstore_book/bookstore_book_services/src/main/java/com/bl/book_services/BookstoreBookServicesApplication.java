package com.bl.book_services;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.web.client.RestTemplate;
import org.springframework.cloud.client.loadbalancer.LoadBalanced;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;


/**
 * @author Mukesh_Bhange
 * @since 24/12/2021
 * purpose : Book MicroServices
 */
@SpringBootApplication
@EnableEurekaClient
class BookstoreBookServicesApplication {

	public static void main(String[] args) {
		SpringApplication.run(BookstoreBookServicesApplication.class, args);
	}
	
	@Bean
	@LoadBalanced
	public RestTemplate restTemplate() {
		return new RestTemplate();
	}
}
