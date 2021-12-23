package com.bl.apigateway;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;

@SpringBootApplication
@EnableEurekaClient
public class BookstoreApiGatewayApplication {

	public static void main(String[] args) {
		SpringApplication.run(BookstoreApiGatewayApplication.class, args);
	}

}
