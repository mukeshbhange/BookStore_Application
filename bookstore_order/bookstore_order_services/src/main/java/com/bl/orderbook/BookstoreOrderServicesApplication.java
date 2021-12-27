package com.bl.orderbook;


/**
 * @author Mukesh_Bhange
 * @since 24/12/2021
 * purpose : BookStore Order MicroServices
 *
 */
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.web.client.RestTemplate;
import org.springframework.cloud.client.loadbalancer.LoadBalanced;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;

@SpringBootApplication
@EnableEurekaClient
public class BookstoreOrderServicesApplication {

	public static void main(String[] args) {
		SpringApplication.run(BookstoreOrderServicesApplication.class, args);
	}
	
	@Bean
	@LoadBalanced
	public RestTemplate restTemplate() {
		return new RestTemplate();
	}

}
