package com.bl.registration;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.loadbalancer.LoadBalanced;
import org.springframework.context.annotation.Bean;
import org.springframework.web.client.RestTemplate;
/**
 * @author Mukesh_Bhange
 * 
 * @version 1.0
 * @since 24/12/2021
 *
 */
@SpringBootApplication
public class BookstoreRegistrationApplication {

	public static void main(String[] args) {
		SpringApplication.run(BookstoreRegistrationApplication.class, args);
	}
	
	@Bean
	@LoadBalanced
	public RestTemplate restTemplate() {
		return new RestTemplate();
	}

}